package com.sunland.new_im.db;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.sunland.new_im.websocket.packet.ImMsgType;
import com.sunland.new_im.websocket.packet.ImPullRevokeMsgInfoListResPacket;
import com.sunland.new_im.utils.AccountUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.sunland.new_im.db.Message.CONSTANT_PEER_UNREAD;
import static com.sunland.new_im.websocket.packet.ImSessionType.TYPE_GROUP_CHAT;


/**
 * Created by fengd on 2018/8/21.
 */

public class DefaultImDao {

    private final Context mContext;
    private final long mImId;
    NewImDBHelper mImDBHelper;

    public DefaultImDao(Context context, long imId) {
//        Log.d("FDL", "new DefaultImDao() imId is " + imId + " accountName is " + AccountUtils.getUserName(context));
        synchronized (DefaultImDao.class) {
            mImDBHelper = new NewImDBHelper(context, imId);
        }
        mContext = context.getApplicationContext();
        this.mImId = imId;
    }

    public long getImId() {
        return mImId;
    }

    /**
     * 获取会话列表
     *
     * @return Session列表
     */
    public List<Session> getSessionList() throws SQLException {
//        Log.d("FDL", "getSessionList db name is " + mImDBHelper.getDatabaseName());
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        return dao.queryBuilder().where().eq(Session.NEED_SHOW, true).query();
    }

    /**
     * 获取会话列表
     *
     * @return Session列表
     */
    public List<Session> getSessionListByType(int sessionType) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        return dao.queryBuilder().where().eq(Session.NEED_SHOW, true).and().eq(Session.SESSION_TYPE, sessionType).query();
    }

    /**
     * @param sessionId 单聊时为对方的imId
     * @return
     * @throws SQLException
     */
    public Session getSessionById(long sessionId) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        return dao.queryForId(sessionId);
    }

    /**
     * 更新session，注意sessionId要有值
     *
     * @param session
     * @return
     * @throws SQLException
     */
    public boolean updateSession(Session session) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        session.setDao(dao);
        boolean updated = session.update() > 0;
        DBChangeNotifier.notifyChanged(mContext, Session.class);
        return updated;
    }

    /**
     * 保存或更新session，下拉刷新会话列表时使用
     *
     * @param sessions
     */
    public void createOrUpdateSessions(final List<Session> sessions) throws SQLException {
//        Log.d("FDL", "createOrUpdateSessions# db name is" + mImDBHelper.getDatabaseName());
        final Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    List<Session> sessionList = getSessionList();
                    List<Session> toBeDelete = new ArrayList<>();
                    for (Session session : sessionList) {
                        long sessionId = session.sessionId;
                        boolean isFound = false;
                        for (Session s : sessions) {
                            if (s.sessionId == sessionId) {
                                isFound = true;
                                break;
                            }
                        }
                        if (!isFound && session.getSessionType() == TYPE_GROUP_CHAT) {
                            toBeDelete.add(session);
                        }
                    }
                    for (Session session : toBeDelete) {//同步得到差分数据  本地有  但是线上没有的数据  场景：本地有一个session , 服务器端手动改了数据，移除了该session(或者是在另外一端退出了该群)，没有通知到客户端，客户端就没办法删除这条数据
                        dao.delete(session);
                    }
                    for (Session session : sessions) {
                        dao.createOrUpdate(session);
                    }
                    return null;
                }
            });
            DBChangeNotifier.notifyChanged(mContext, Session.class);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void createOrUpdateSession(final Session session) throws SQLException {
//        Log.d("FDL", "createOrUpdateSessions# db name is" + mImDBHelper.getDatabaseName());
        final Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    dao.createOrUpdate(session);
                    return null;
                }
            });
            DBChangeNotifier.notifyChanged(mContext, Session.class);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }


    /**
     * 创建或更新session
     */
    @NonNull
    public void createOrUpdateSession(long sessionId, int sessionType, String sessionName, String sessionImg, int memberNumber, int memberNumberMaximum, long latestMsgCreateTime) throws SQLException {
        Dao<Session, Long> sessiondao = mImDBHelper.getDao(Session.class);
        Session session = sessiondao.queryForId(sessionId);
        if (session == null) {
            session = new Session();
        }
        session.setSessionId(sessionId);
        session.setSessionType(sessionType);
        session.setSessionName(sessionName);
        session.setImageUrl(sessionImg);
        session.setNeedShow(true);
        session.setMemberNum(memberNumber);
        session.setMemberNumberMaximum(memberNumberMaximum);
        session.setLatestMsgCreateTime(latestMsgCreateTime);
        sessiondao.createOrUpdate(session);
    }

    /**
     * 根据已有的Message，创建或更新session
     */
    @NonNull
    public int createOrUpdateSessionWithMsg(Message msg, String sessionName, String sessionImg, boolean updateUnRead) throws SQLException {
        int rows = 0;
        Dao<Session, Long> sessionDao = mImDBHelper.getDao(Session.class);
        Session session = sessionDao.queryForId(msg.getSessionId());
        int groupAtType = msg.getGroupAtType();
        boolean hasAt = Message.GroupAtType.NO_AT.ordinal() != groupAtType;
        boolean isAtAll = Message.GroupAtType.AT_ALL.ordinal() == groupAtType;
        if (session != null) {
            if (session.getLatestMsgId() < msg.getMsgId()) {
                session.setLastestMsgContent(msg.getMsgContent());
                session.setLatestMsgCreateTime(msg.getMsgCreateTime());
                session.setLatestMsgId(msg.getMsgId());
                session.setLatestMsgStatus(msg.getMsgStatus());
                session.setLatestMsgType(msg.getMsgType());
                session.setLatestSenderId(msg.getSenderId());
                session.setReceivedPeerMsg(msg.getReceivedPeerMsg());
                session.setLatestSenderName(msg.getSenderName());
                boolean isAtMe = isAtMe(msg);
                boolean iAmSender = iAmSender(msg);
                int unReadGroupAtMsgId = ((isAtAll && !iAmSender) || (hasAt && isAtMe)) ? msg.getMsgId() : -1;
                int oldUnReadAtMsgId = session.getFirstUnReadGroupAtMsgId();
                if (oldUnReadAtMsgId <= 0) {
                    session.setFirstUnReadGroupAtMsgId(unReadGroupAtMsgId);
                }
                if (updateUnRead) {
                    session.setUnReadNum(session.getUnReadNum() + 1);
                }
                session.setNeedShow(true);
                rows = sessionDao.update(session);
            }
        } else {
            session = new Session();
            session.setSessionId(msg.getSessionId());
            session.setSessionType(msg.getSessionType());
            session.setSessionName(sessionName);
            session.setImageUrl(sessionImg);
            session.setLatestMsgId(msg.getMsgId());
            session.setLatestMsgType(msg.getMsgType());
            session.setLastestMsgContent(msg.getMsgContent());
            session.setLatestMsgCreateTime(msg.getMsgCreateTime());
            session.setLatestMsgStatus(msg.getMsgStatus());
            session.setLatestSenderId(msg.getSenderId());
            session.setReceivedPeerMsg(msg.getReceivedPeerMsg());
            session.setLatestSenderName(msg.getSenderName());
            int unReadGroupAtMsgId = hasAt ? msg.getMsgId() : -1;
            session.setFirstUnReadGroupAtMsgId(unReadGroupAtMsgId);
            if (updateUnRead) {
                session.setUnReadNum(1);
            }
            session.setNeedShow(true);
            rows = sessionDao.create(session);
            DBChangeNotifier.notifyChanged(mContext, Session.class);
        }
        return rows;
    }

    private boolean isAtMe(Message message) {
        boolean isAtMe = false;
        try {
            Map<String, String> atMemberMap = new Gson().fromJson(message.getGroupAtMember(), new TypeToken<Map<String, String>>() {
            }.getType());
            if (atMemberMap != null && !atMemberMap.isEmpty()) {
                String value = atMemberMap.get(String.valueOf(getImId()));
                if (value != null) {
                    isAtMe = true;
                }
            }
        } catch (Exception e) {
        }
        return isAtMe;
    }

    private boolean iAmSender(Message message) {
        return getImId() == message.getSenderId();
    }

    /**
     * 保存或更新messages，进入对话界面时会请求最新消息，请求返回时要保存
     *
     * @param messages
     */
    public void createOrUpdateMessageAndSession(final List<Message> messages, final String sessionName, final String sessionImg) throws SQLException {
        final Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Message msg : messages) {
                        boolean isCreate = createOrUpdateMessage(msg);
                        createOrUpdateSessionWithMsg(msg, sessionName, sessionImg, isCreate && msg.getSenderId() != mImId && msg.getMsgType() != ImMsgType.SYSTEM);
                    }
                    return null;
                }
            });

            DBChangeNotifier.notifyChanged(mContext, Message.class);
            DBChangeNotifier.notifyChanged(mContext, Session.class);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private boolean createOrUpdateMessage(Message msg) throws SQLException {
        final Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        Message msgInDB = dao.queryBuilder().where().eq(Message.SESSION_ID, msg.getSessionId()).and().eq(Message.MESSAGE_ID, msg.getMsgId()).queryForFirst();
        boolean isCreate = false;
        if (msgInDB == null) {
            dao.create(msg);
            isCreate = true;
        } else {
            msg.setId(msgInDB.getId());
            dao.update(msg);
        }
        return isCreate;
    }

    /**
     * 获取最新消息列表，排序由调用端处理
     *
     * @param sessionId
     * @param sessionType
     * @param limit       限制取多少条，-1 表示不限制
     * @return Message 列表
     */
    public List<Message> getMessageList(long sessionId, int sessionType, long limit) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);

        QueryBuilder<Message, Long> qb = dao.queryBuilder();
        if (limit > 0) {
            qb.limit(limit);
        }
        return qb.orderBy(Message.MESSAGE_ID, false).where().eq(Message.SESSION_ID, sessionId).and().eq(Message.SESSION_TYPE, sessionType).query();
    }

    /**
     * 获取指定msgId之前的消息列表（从数据库获取时候按msgId降序排列，包含msgId）
     *
     * @param sessionId
     * @param sessionType
     * @param msgId
     * @param limit       限制取多少条，-1 表示不限制
     * @return Message 列表
     */
    public List<Message> getMessageList(long sessionId, int sessionType, long msgId, long limit) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);

        QueryBuilder<Message, Long> qb = dao.queryBuilder();
        if (limit > 0) {
            qb.limit(limit);
        }
        return qb.orderBy(Message.MESSAGE_ID, false).where().eq(Message.SESSION_ID, sessionId).and().eq(Message.SESSION_TYPE, sessionType).and().le(Message.MESSAGE_ID, msgId).query();
    }

    public List<Message> getLoadMessageList(long sessionId, int sessionType, long msgId, long limit) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);

        QueryBuilder<Message, Long> qb = dao.queryBuilder();
        if (limit > 0) {
            qb.limit(limit);
        }
        return qb.orderBy(Message.MESSAGE_ID, true).where().eq(Message.SESSION_ID, sessionId).and().eq(Message.SESSION_TYPE, sessionType).and().ge(Message.MESSAGE_ID, msgId).query();
    }

    /**
     * 获取指定msgId之前的消息列表（从数据库获取时候按msgId降序排列，包含msgId）
     * 且msgId是连续的，最多会取出limit条数据
     *
     * @param sessionId
     * @param sessionType
     * @param msgId
     * @param limit       限制取多少条，-1 表示不限制
     * @return Message 列表
     */
    public List<Message> getSerialMessageList(long sessionId, int sessionType, long msgId, long limit) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);

        QueryBuilder<Message, Long> qb = dao.queryBuilder();
        if (limit > 0) {
            qb.limit(limit);
        }
        return qb.orderBy(Message.MESSAGE_ID, false)
                .where().eq(Message.SESSION_ID, sessionId)
                .and().eq(Message.SESSION_TYPE, sessionType)
                .and().le(Message.MESSAGE_ID, msgId)
                .and().gt(Message.MESSAGE_ID, msgId - limit).query();
    }

    /**
     * 获取发送中的消息，需要和 {@link #getMessageList(long, int, long)}结果一起用以展示对话界面的消息列表
     *
     * @param sessionId
     * @param sessionType
     * @return
     */
    public List<TmpMessage> getTmpMessageList(long sessionId, int sessionType) throws SQLException {

        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);

        return dao.queryBuilder().where().eq(TmpMessage.SESSION_ID, sessionId).and().eq(TmpMessage.SESSION_TYPE, sessionType).query();

    }

    public List<TmpMessage> getTmpMessageList() throws SQLException {
        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
        return dao.queryForAll();
    }

    public List<TmpMessage> getTmpMessageListByType(int sessionType) throws SQLException {
        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
        return dao.queryBuilder().where().eq(TmpMessage.SESSION_TYPE, sessionType).query();
    }

    public List<Draft> getDraftList() throws SQLException {
        Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        return dao.queryForAll();
    }

    public List<Draft> getDraftListByType(int sessionType) throws SQLException {
        Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        return dao.queryBuilder().where().eq(Draft.SESSION_TYPE, sessionType).query();
    }

    public Draft getDraftById(long sessionId) throws SQLException {
        Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        return dao.queryForId(sessionId);
    }

    /**
     * 更新Draft，注意sessionId要有值
     *
     * @param draft
     * @return
     * @throws SQLException
     */
    public void createOrUpdateDraft(final Draft draft) throws SQLException {
        final Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    dao.createOrUpdate(draft);
                    return null;
                }
            });
            DBChangeNotifier.notifyChanged(mContext, Draft.class);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * 发送消息时调用：
     * <ul>客户端发送消息->插入tmp_message-></ul>
     * <li>发送成功收到后台回执->删除tmp_message数据插入message->更新session表->更新UI</li>
     * <li>发送失败未收到后台回执->更新sentTimes字段->更新UI</li>
     *
     * @return boolean 保存成功
     */
    public boolean createOrUpdateTmpMessage(TmpMessage tmpMessage) throws SQLException {
        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
        TmpMessage itemInDB = dao.queryBuilder().where().eq(TmpMessage.SESSION_ID, tmpMessage.getSessionId()).and().eq(TmpMessage.UNIQUE_KEY, tmpMessage.getUniqueKey()).queryForFirst();
        int result = -1;
        if (itemInDB != null) {
            tmpMessage.setId(itemInDB.getId());
            dao.update(tmpMessage);
        } else {
            result = dao.create(tmpMessage);
        }

        DBChangeNotifier.notifyChanged(mContext, TmpMessage.class);
        return result > 0;
    }

    /**
     * 标识TmpMessage发送成功：删除TmpMessage表对应数据，插入Message表，更新Session表,在一个事务里完成
     *
     * @param tmpMessage
     * @param msgId      cid 6002返回的msgId
     * @param msgStatus  msgStatus 1：正常， 2：撤销
     * @return boolean 是否更新成功
     */
    public boolean markTmpMessageSendOk(final TmpMessage tmpMessage, final long senderId, final int msgId, final int msgStatus) throws SQLException {
        return mImDBHelper.callInTransaction(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
                if (tmpMessage != null) {
                    dao.deleteById(tmpMessage.getId());
                }

                Message msg = new Message(tmpMessage, msgId, senderId, msgStatus);
                msg.setSenderImageUrl(AccountUtils.getAccountAvatarByUserId(AccountUtils.getUserId(mContext)));
                msg.setSenderName(AccountUtils.getUserName(mContext));
                msg.setPeerUnRead(Message.CONSTANT_PEER_UNREAD);
                Dao<Message, Long> msgDao = mImDBHelper.getDao(Message.class);
                msgDao.create(msg);

                int rows = createOrUpdateSessionWithMsg(msg, tmpMessage.getSessionName(), tmpMessage.getImageUrl(), false);
                DBChangeNotifier.notifyChanged(mContext, TmpMessage.class);
                DBChangeNotifier.notifyChanged(mContext, Message.class);

                return rows > 0;

            }
        });
    }

    /**
     * 标识TmpMessage发送失败：更新sendTimes>0
     *
     * @param tmpMessage
     * @param sendTimes  发送次数
     * @return boolean 是否更新成功
     */
    public boolean markTmpMessageSendFailed(final TmpMessage tmpMessage, final int sendTimes) throws SQLException {
        return mImDBHelper.callInTransaction(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
                if (tmpMessage == null) {
                    return false;
                }

                tmpMessage.setSentTimes(sendTimes);
                int update = dao.update(tmpMessage);
                DBChangeNotifier.notifyChanged(mContext, TmpMessage.class);
                return update > 0;
            }
        });
    }

    /**
     * 用户退出聊天界面时输入框中有文本内容时需要保存草稿
     * 保存新草稿时要删除旧草稿
     *
     * @return boolean 是否保存成功
     */
    public boolean saveDraft(Draft draft) throws SQLException {
        Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        int rows = dao.create(draft);
        return rows > 0;
    }

    /**
     * 用户删除输入框退出聊天界面，或者把消息发出都需要删除草稿
     * 点输入框旁边的发送按钮发消息时统一调用一下此接口
     *
     * @param sessionId
     * @param sessionType
     */
    public void deleteDrafts(long sessionId, int sessionType) throws SQLException {
        Dao<Draft, Long> dao = mImDBHelper.getDao(Draft.class);
        DeleteBuilder<Draft, Long> db = dao.deleteBuilder();
        db.where().eq(Draft.SESSION_ID, sessionId).and().eq(Draft.SESSION_TYPE, sessionType);
        db.delete();
    }

    /**
     * 用户删除输入框退出聊天界面，或者把消息发出都需要删除聊天记录
     * 点输入框旁边的发送按钮发消息时统一调用一下此接口
     *
     * @param sessionId
     * @param sessionType
     */
    public void deleteMessage(long sessionId, int sessionType) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        DeleteBuilder<Message, Long> db = dao.deleteBuilder();
        db.where().eq(Message.SESSION_ID, sessionId).and().eq(Message.SESSION_TYPE, sessionType);
        db.delete();
    }

    /**
     * 用户删除输入框退出聊天界面，或者把消息发出都需要删除聊天记录
     * 点输入框旁边的发送按钮发消息时统一调用一下此接口
     *
     * @param sessionId
     * @param sessionType
     */
    public void deleteTmpMessage(long sessionId, int sessionType) throws SQLException {
        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
        DeleteBuilder<TmpMessage, Long> db = dao.deleteBuilder();
        db.where().eq(TmpMessage.SESSION_ID, sessionId).and().eq(TmpMessage.SESSION_TYPE, sessionType);
        db.delete();
    }

    /**
     * 手动置顶或取消置顶
     *
     * @param sessionItem
     * @param top
     * @throws SQLException
     */
    public void setTop(SessionItem sessionItem, boolean top) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
        Session session = dao.queryForId(sessionItem.getSessionId());
        if (session != null) {
            session.setIsTop(top);
            session.setNeedShow(true);
            dao.update(session);
        } else {
            // session不存在，创建一个
            Session tmpSession = createEmptySession(sessionItem);
            tmpSession.setIsTop(top);
            tmpSession.setNeedShow(true);
            dao.create(tmpSession);
        }
        DBChangeNotifier.notifyChanged(mContext, Session.class);
        Dao<QueuedCmd, Long> cmdDao = mImDBHelper.getDao(QueuedCmd.class);
        QueuedCmd cmd = new QueuedCmd();
        cmd.setSessionId(sessionItem.getSessionId());
        cmd.setSessionType(sessionItem.getSessionType());
        cmd.setCid(QueuedCmd.CID_4005);
        cmd.setAction(top ? QueuedCmd.ACTION_SET_TOP : QueuedCmd.ACTION_CANCEL_TOP);
        cmdDao.create(cmd);
        DBChangeNotifier.notifyChanged(mContext, QueuedCmd.class);

    }

    /**
     * 被动置顶或取消置顶
     *
     * @param sessionId
     * @param sessionType
     * @param actionTime
     * @param top         @throws SQLException
     */
    public boolean setTop(long sessionId, int sessionType, long actionTime, boolean top) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);

        Session session = dao.queryForId(sessionId);
        if (session != null) {
            session.setIsTop(top);
            session.setTopTime(actionTime);
            session.setNeedShow(true);
            dao.update(session);
            DBChangeNotifier.notifyChanged(mContext, Session.class);
            return true;
        } else {
            // session不存在，仅有的信息也不足以创建session，只能忽略
            return false;
        }
    }

    private Session createEmptySession(SessionItem sessionItem) {
        long sessionId = sessionItem.getSessionId();
        int sessionType = sessionItem.getSessionType();
        String sessionName = sessionItem.getSessionName();
        String imageUrl = sessionItem.getImageUrl();
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setSessionType(sessionType);
        session.setSessionName(sessionName);
        session.setImageUrl(imageUrl);
        session.setStatus(Session.STATUS_ON_JOB);
        return session;
    }

    /**
     * 界面删除session操作使用，需要删除对应Draft TmpMessage，message
     *
     * @param sessionId
     * @param sessionType
     */
    public void deleteSession(final long sessionId, final int sessionType) throws SQLException {
        mImDBHelper.callInTransaction(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Dao<Draft, Long> draftDao = mImDBHelper.getDao(Draft.class);
                Dao<TmpMessage, Long> tmpMsgDao = mImDBHelper.getDao(TmpMessage.class);
                Dao<Session, Long> sessionDao = mImDBHelper.getDao(Session.class);
                Dao<Message, Long> messageDao = mImDBHelper.getDao(Message.class);

                DeleteBuilder<Draft, Long> db = draftDao.deleteBuilder();
                db.where().eq(Draft.SESSION_ID, sessionId).and().eq(Draft.SESSION_TYPE, sessionType);
                db.delete();

                DeleteBuilder<TmpMessage, Long> db2 = tmpMsgDao.deleteBuilder();
                db2.where().eq(TmpMessage.SESSION_ID, sessionId).and().eq(TmpMessage.SESSION_TYPE, sessionType);
                db2.delete();

                DeleteBuilder<Message, Long> db4 = messageDao.deleteBuilder();
                db4.where().eq(Message.SESSION_ID, sessionId).and().eq(Message.SESSION_TYPE, sessionType);
                db2.delete();

                DeleteBuilder<Session, Long> db3 = sessionDao.deleteBuilder();
                db3.where().eq(Session.SESSION_ID, sessionId).and().eq(Session.SESSION_TYPE, sessionType);
                db3.delete();
                return null;
            }
        });

        DBChangeNotifier.notifyChanged(mContext, Session.class);

    }

    public void setNoDisturb(long sessionId, int sessionType, boolean noDisturb) throws SQLException {
        Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);

        Session session = dao.queryForId(sessionId);
        if (session != null) {
            session.setNoDisturb(noDisturb);
            dao.update(session);
            DBChangeNotifier.notifyChanged(mContext, Session.class);
        } else {
            // session不存在，仅有的信息也不足以创建session，只能忽略
        }

    }

    public void deleteSessionItem(final long sessionId, final int sessionType) throws SQLException {
        mImDBHelper.callInTransaction(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                // 删除草稿，删除临时消息，不删除会话，会话要用来保存no_disturb状态，置顶状态要设为非置顶
                Dao<Session, Long> dao = mImDBHelper.getDao(Session.class);
                Session session = dao.queryForId(sessionId);

                if (session != null) {
                    session.setIsTop(false);
                    session.setNeedShow(false);
                    session.setUnReadNum(0);
                    session.setFirstUnReadGroupAtMsgId(0);
                    session.update();
                }

                Dao<TmpMessage, Long> tmpMsgDao = mImDBHelper.getDao(TmpMessage.class);
                DeleteBuilder<TmpMessage, Long> db1 = tmpMsgDao.deleteBuilder();
                db1.where().eq(TmpMessage.SESSION_ID, sessionId).and().eq(TmpMessage.SESSION_TYPE, sessionType);
                db1.delete();

                Dao<Draft, Long> draftDao = mImDBHelper.getDao(Draft.class);
                DeleteBuilder<Draft, Long> db2 = draftDao.deleteBuilder();
                db2.where().eq(Draft.SESSION_ID, sessionId).and().eq(Draft.SESSION_TYPE, sessionType);
                db2.delete();
                return null;
            }
        });
        DBChangeNotifier.notifyChanged(mContext, Session.class);
    }

    /**
     * "非文件消息"标识为已读
     *
     * @param sessionId   会话id
     * @param latestMsgId 最新的消息id
     */
    public void markAllMessageRead(long sessionId, int latestMsgId, long senderId) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        UpdateBuilder<Message, Long> ub = dao.updateBuilder();
        ub.updateColumnValue(Message.PEER_UNREAD, Message.CONSTANT_PEER_READ)
                .where().eq(Message.SESSION_ID, sessionId)
                .and().eq(Message.SENDER_ID, senderId)
                .and().le(Message.MESSAGE_ID, latestMsgId)
                .and().ne(Message.MESSAGE_TYPE, ImMsgType.FILE)
                .and().eq(Message.PEER_UNREAD, CONSTANT_PEER_UNREAD);
        ub.update();
        DBChangeNotifier.notifyChanged(mContext, Message.class);
    }

    /**
     * "文件消息"标识为已读
     *
     * @param sessionId 会话id
     * @param recvMsgId 文件消息id
     */
    public void markFileMessageRead(long sessionId, int recvMsgId, long senderId) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        UpdateBuilder<Message, Long> ub = dao.updateBuilder();

        ub.updateColumnValue(Message.PEER_UNREAD, Message.CONSTANT_PEER_READ)
                .where().eq(Message.SESSION_ID, sessionId)
                .and().eq(Message.SENDER_ID, senderId)
                .and().eq(Message.MESSAGE_ID, recvMsgId)
                .and().eq(Message.MESSAGE_TYPE, ImMsgType.FILE)
                .and().eq(Message.PEER_UNREAD, CONSTANT_PEER_UNREAD);
        ub.update();
        DBChangeNotifier.notifyChanged(mContext, Message.class);
        //clearSessionUnReadNum(sessionId);
    }

    /**
     * "文件消息"标识为已读
     *
     * @param sessionId 会话id
     * @param msgId 文件消息id
     */
    public void markAudioMessageRead(long sessionId, int msgId, long senderId) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        UpdateBuilder<Message, Long> ub = dao.updateBuilder();

        ub.updateColumnValue(Message.RECEIVED_PEER_MSG, Message.RECEIVED_PEER_READ)
                .where().eq(Message.SESSION_ID, sessionId)
                .and().eq(Message.SENDER_ID, senderId)
                .and().eq(Message.MESSAGE_ID, msgId)
                .and().eq(Message.MESSAGE_TYPE, ImMsgType.AUDIO)
                .and().eq(Message.RECEIVED_PEER_MSG, Message.RECEIVED_PEER_UNREAD);
        ub.update();
        DBChangeNotifier.notifyChanged(mContext, Message.class);
        //clearSessionUnReadNum(sessionId);
    }

    //更新session未读数为0
    public void clearSessionUnReadNum(long sessionId) throws SQLException {
        //Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        //int unReadNum = dao.queryBuilder()
        //        .where().eq(Message.SESSION_ID, sessionId)
        //        .and().eq(Message.PEER_UNREAD, CONSTANT_PEER_UNREAD)
        //        .and().ne(Message.SENDER_ID, mImId).query().size();

        Dao<Session, Long> sessionDao = mImDBHelper.getDao(Session.class);
        UpdateBuilder<Session, Long> sub = sessionDao.updateBuilder();
        sub.updateColumnValue(Session.UNREAD_NUM, 0)
                .updateColumnValue(Session.FIRST_UNREAD_GROUP_AT_MSG_ID, -1)
                .where().eq(Message.SESSION_ID, sessionId);
        sub.update();
        DBChangeNotifier.notifyChanged(mContext, Session.class);
    }

    public List<Message> getMessageByType(long sessionId, int msgType, int msgStatus) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        return dao.queryBuilder().where().eq(Message.SESSION_ID, sessionId).and().eq(Message.MESSAGE_TYPE, msgType)
                .and().eq(Message.MSG_STATUS, msgStatus).query();
    }

    public Message getMessageById(long sessionId, int msgId) throws SQLException {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        return dao.queryBuilder().where().eq(Message.SESSION_ID, sessionId).and().eq(Message.MESSAGE_ID, msgId).queryForFirst();
    }

    public List<TmpMessage> getTmpMessageByType(long sessionId, int msgType) throws SQLException {
        Dao<TmpMessage, Long> dao = mImDBHelper.getDao(TmpMessage.class);
        return dao.queryBuilder().where().eq(TmpMessage.SESSION_ID, sessionId).and().eq(TmpMessage.MESSAGE_TYPE, msgType).query();
    }

    public List<Session> getSessionList(int sessionType, String keyword) throws SQLException {
        Dao<Session, Long> sessionDao = mImDBHelper.getDao(Session.class);
        return sessionDao.queryBuilder().orderBy(Session.LATEST_MSG_CREATE_TIME, false).where().eq(Session.SESSION_TYPE, sessionType).and().like(Session.SESSION_NAME, "%" + keyword + "%").query();
    }

    public boolean markMsgRevoked(Context context, long sessionId, int msgId, long revokerId, String revokerName, long revokeTime) throws SQLException {
        String displayText = SessionDisplayUtil.generateRevokedMsgDisplayText(context, revokerName, revokerId);
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        UpdateBuilder<Message, Long> updateBuilder = dao.updateBuilder();
        updateBuilder.updateColumnValue(Message.MSG_STATUS, Message.Status.REVOKED)
                .updateColumnValue(Message.MSG_CONTENT, displayText)
                .where().eq(Message.SESSION_ID, sessionId).and().eq(Message.MESSAGE_ID, msgId);

        boolean updated = updateBuilder.update() > 0;

        // 如果是最后一条消息，需要更新session
        Session session = getSessionById(sessionId);
        if (session != null && session.getLatestMsgId() == msgId) {
            session.setLatestMsgStatus(Message.Status.REVOKED);
            session.setLastestMsgContent(displayText);
            updateSession(session);
        }

        return updated;
    }

    public int markMsgRevoked(final Context context, final List<ImPullRevokeMsgInfoListResPacket.PullRevokeMsgInfoListResBean.RevokeMsgInfoListBean> revokeMsgInfoList) throws Exception {
        Dao<Message, Long> dao = mImDBHelper.getDao(Message.class);
        return dao.callBatchTasks(new Callable<Integer>() {
            @Override
            public Integer call() throws SQLException {
                int line = 0;
                for (ImPullRevokeMsgInfoListResPacket.PullRevokeMsgInfoListResBean.RevokeMsgInfoListBean listBean : revokeMsgInfoList) {
                    long sessionId = listBean.getSessionId();
                    for (ImPullRevokeMsgInfoListResPacket.PullRevokeMsgInfoListResBean.RevokeMsgInfoListBean.RevokeRecordListBean subListBean : listBean.getRevokeRecordList()) {
                        int msgId = subListBean.getMsgId();
                        long revokerId = subListBean.getRevokerId();
                        String revokerName = subListBean.getRevokerName();
                        long revokeTime = subListBean.getRevokeTime();
                        boolean updated = markMsgRevoked(context, sessionId, msgId, revokerId, revokerName, revokeTime);
                        if (updated) {
                            line++;
                        }
                    }
                }
                return line;
            }
        });
    }

    public long getMaxMsgTime() throws SQLException {
        Dao<Message, Integer> dao = mImDBHelper.getDao(Message.class);
        Message msg = dao.queryBuilder().orderBy(Message.MSG_CREATE_TIME, false).queryForFirst();
        if (msg != null) {
            return msg.getMsgCreateTime();
        } else {
            return 0;
        }
    }

    /**
     * 创建或更新表情收藏列表
     * @param emojis 创建或更新的表情
     */
    public void createOrUpdateEmojis(final List<Emoji> emojis) throws SQLException {
        final Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Emoji emoji : emojis) {
                        if (!isEmojiUrlExist(emoji.getEmojiUrl())){
                            dao.createOrUpdate(emoji);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * 删除表情
     * @param emojis
     */
    public void deleteEmojis(final List<Emoji> emojis) throws SQLException {
        final Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Emoji emoji : emojis) {
                        if (isEmojiUrlExist(emoji.getEmojiUrl())){
                            dao.delete(emoji);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }


    /**
     * 增加单个表情
     * */
    public void createOrUpdateEmoji(final Emoji emoji) throws SQLException {
        final Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    dao.createOrUpdate(emoji);
                    return null;
                }
            });
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * 获取所有本地收藏表情
     * @return 收藏的表情列表
     */
    public List<Emoji> getEmojis() throws SQLException {
        Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
        return dao.queryForAll();
    }

    /**
     * 检查表情是否已收藏
     * @param url 表情url
     * @return 是否已收藏
     * */
    public boolean isEmojiUrlExist(String url) throws SQLException {
        Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
        List<Emoji> result = dao.queryForEq("emojiUrl", url);
        return result != null && result.size() > 0;
    }

    public void updateAllEmojis(final List<Emoji>  emojis){
        try {
            final Dao<Emoji, Long> dao = mImDBHelper.getDao(Emoji.class);
            final List<Emoji> emojiList = dao.queryForAll();
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    dao.delete(emojiList);
                    for (Emoji emoji : emojis){
                        dao.createOrUpdate(emoji);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
