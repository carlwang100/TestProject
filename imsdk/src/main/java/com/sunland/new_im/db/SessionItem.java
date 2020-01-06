package com.sunland.new_im.db;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.sunland.new_im.entity.Description;
import com.sunland.new_im.utils.IMTimeUtil;
import com.sunland.new_im.websocket.packet.ImMsgType;
import com.sunland.new_im.websocket.packet.ImSessionType;

import static com.sunland.new_im.db.Session.IS_NORMAL_GROUP_TYPE;
import static com.sunland.new_im.db.Session.IS_NORMAL_USER_TYPE;

/**
 * 会话列表展示元素
 */

public class SessionItem {

    Session mSession;
    // 待发送消息可以有多条,但显示会话列表只需要把时间最新的一条设置进来
    TmpMessage mTmpMessage;
    Draft mDraft;
    private int lastMsgId;

    public SessionItem(@NonNull Session session) {
        this.mSession = session;
    }

    /**
     * 适用于对话界面只有一条待发送消息的情况
     *
     * @param tmpMessage
     */
    public SessionItem(@NonNull TmpMessage tmpMessage) {
        this.mTmpMessage = tmpMessage;
    }

    /**
     * 适用于对话界面只有一条草稿的情况
     *
     * @param draft
     */
    public SessionItem(@NonNull Draft draft) {
        this.mDraft = draft;
    }

    private boolean belong(TmpMessage tmpMessage) {
        return tmpMessage.getSessionId() == getSessionId()
                && tmpMessage.getSessionType() == getSessionType();
    }

    private boolean belong(Draft draft) {
        return draft.getSessionId() == getSessionId()
                && draft.getSessionType() == getSessionType();
    }

    public long getSessionId() {
        if (mSession != null) {
            return mSession.getSessionId();
        } else if (mTmpMessage != null) {
            return mTmpMessage.getSessionId();
        } else if (mDraft != null) {
            return mDraft.getSessionId();
        }
        throw new IllegalStateException();
    }

    public int getSessionType() {
        if (mSession != null) {
            return mSession.getSessionType();
        } else if (mTmpMessage != null) {
            return mTmpMessage.getSessionType();
        } else if (mDraft != null) {
            return mDraft.getSessionType();
        }
        throw new IllegalStateException();
    }

    public int getGroupType() {
        if (mSession != null) {
            return mSession.getGroupType();
        }
        return IS_NORMAL_GROUP_TYPE;
    }

    public int getReceivedPeerMsg() {
        if (mSession != null) {
            return mSession.getReceivedPeerMsg();
        }
        return Message.RECEIVED_PEER_READ;
    }

    public long getLatestSenderId() {
        if (mSession != null) {
            return mSession.getLatestSenderId();
        }
        return -1l;
    }

    public int getUserType() {
        if (mSession != null) {
            return mSession.getUserType();
        }
        return IS_NORMAL_USER_TYPE;
    }

    /**
     * 获取显示的昵称，群名字
     * @return
     */
    public String getSessionName() {
        if (mSession != null) {
            return mSession.getSessionName();
        } else if (mTmpMessage != null) {
            return mTmpMessage.getSessionName();
        } else if (mDraft != null) {
            return mDraft.getSessionName();
        }
        throw new IllegalStateException();
    }

    /**
     * 获取显示的头像，群头像
     * @return
     */
    public String getImageUrl() {
        if (mSession != null) {
            return mSession.getImageUrl();
        } else if (mTmpMessage != null) {
            return mTmpMessage.getImageUrl();
        } else if (mDraft != null) {
            return mDraft.getImageUrl();
        }
        throw new IllegalStateException();
    }

    /**
     * 获取时间，显示在会话列表上时还需要格式化
     * @param ignoreDraft 忽略草稿
     * @return
     */
    public long getLatestMsgCreateTime(boolean ignoreDraft) {
        // 优先显示draft内容
        if (!ignoreDraft && mDraft != null) {
            return mDraft.getMsgCreateTime();
        }
        // 其次tmpMessage和session同时存在时显示时间最新的
        else if (mTmpMessage != null && mSession != null) {
            long time1 = mTmpMessage.getMsgCreateTime();
            long time2 = mSession.getLatestMsgCreateTime();
            return time1 - time2 > 0 ? time1 : time2;
        }
        // 再次，只有session
        else if (mSession != null) {
            return mSession.getLatestMsgCreateTime();
        }
        // 最次，只有tmpMessage
        else if (mTmpMessage != null) {
            return mTmpMessage.getMsgCreateTime();
        }
        throw new IllegalStateException();
    }

    /**
     * 获取显示的最后一条消息的发送者，只有是群的时候才显示
     * @return
     */
    public String getLastMsgSender() {
        // 优先显示draft内容
        if (mDraft != null) {
            // 有draft时不显示sender
            return "";
        }
        // 其次tmpMessage和session同时存在时显示时间最新的
        else if (mTmpMessage != null && mSession != null) {
            long time1 = mTmpMessage.getMsgCreateTime();
            long time2 = mSession.getLatestMsgCreateTime();
            return time1 - time2 > 0 ? "" : mSession.getLatestSenderName();
        }
        // 再次，只有session
        else if (mSession != null) {
            return mSession.getLatestSenderName();
        }
        // 最次，只有tmpMessage
        else if (mTmpMessage != null) {
            return "";
        }
        throw new IllegalStateException();
    }

    /**
     * 获取显示的最后一条消息内容
     * @return
     */
    public String getLastMsgDisplayContent(Context context, long curAccountImId) {
        // 没有@me消息时，优先显示draft内容
        if (!hasGroupAtMsg() && mDraft != null) {
            return mDraft.getMsgContent();
        }
        // 其次tmpMessage和session同时存在时显示时间最新的
        else if (mTmpMessage != null && mSession != null) {
            long time1 = mTmpMessage.getMsgCreateTime();
            long time2 = mSession.getLatestMsgCreateTime();
            return time1 - time2 > 0 ? mTmpMessage.getDisplayMsgContent(context) : getSessionLastMsgContent(context, curAccountImId, mSession);
        }
        // 再次，只有session
        else if (mSession != null) {
            return getSessionLastMsgContent(context, curAccountImId, mSession);
        }
        // 最次，只有tmpMessage，不可能出现的情况
        else if (mTmpMessage != null) {
            return mTmpMessage.getDisplayMsgContent(context);
        }
        else {
            return "";
        }
    }

    public String getLastMsgContentOriginal() {
        if (mTmpMessage != null && mSession != null) {
            long time1 = mTmpMessage.getMsgCreateTime();
            long time2 = mSession.getLatestMsgCreateTime();
            return time1 - time2 > 0 ? mTmpMessage.getMsgContent() : mSession.getLastestMsgContent();
        }
        // 再次，只有session
        else if (mSession != null) {
            return mSession.getLastestMsgContent();
        }
        return "";
    }

    private static String getSessionLastMsgContent(Context context, long curAccountImId, Session session) {
        long latestSenderId = session.getLatestSenderId();

        if (session.getSessionType() == ImSessionType.TYPE_GROUP_CHAT) {
            if (latestSenderId == 0) {
                return "";
            }
            else if (latestSenderId == curAccountImId) {
                return avoidNull(session.getDisplayMsgContent(context));
            }
            else {
                String lastSenderName = session.getLatestSenderName();
                if (session.getLatestMsgStatus() == Message.Status.NORMAL && lastSenderName != null && lastSenderName.length() > 0) {
                    return session.getLatestSenderName() + ":" + avoidNull(session.getDisplayMsgContent(context));
                } else {
                    return avoidNull(session.getDisplayMsgContent(context));
                }
            }
        } else {
            return avoidNull(session.getDisplayMsgContent(context));
        }
    }

    private static String avoidNull(String text) {
        if (text == null) {
            return "";
        }
        return text;
    }

    //最后一条消息状态（1：正常，2：被撤回, 3x:错误， x代表cid6002后台给的错误码）,
    public int getLatestMsgStatus() {
        if (mSession != null) {
            return mSession.getLatestMsgStatus();
        }
        return 1;
    }

    /**
     * 因为有草稿时要优先显示草稿状态，需要调用这个判断
     *
     * @return
     */
    public boolean hasDraft() {
        return mDraft != null;
    }

    /**
     * 按照微信的逻辑：
     * 有正在发送的消息也需要显示正在发送状态，发送失败如果不是时间最大的一条消息可以不显示Sending状态,
     * 照此逻辑这种情况返回false
     * @return 是否需要显示sending状态
     */
    public boolean hasSendingMsg() {
        if (mTmpMessage == null) {
            return false;
        }
        else if (mSession == null && mTmpMessage != null) {
            return true;
        }
        else if (mSession != null && mTmpMessage != null) {
            long time1 = mSession.getLatestMsgCreateTime();
            long time2 = mTmpMessage.getMsgCreateTime();
            return time1 - time2 > 0;
        } else {
            return false;
        }
    }

    /**
     * 是否免打扰
     * @return
     */
    public boolean isNoDisturb() {
        if (mSession != null) {
            return mSession.getNoDisturb();
        }
        // 设置了noDisturb需要创建一条不显示的session用来记录设置
        return false;
    }

    /**
     * 是否已置顶
     * @return
     */
    public boolean isTop() {
        if (mSession != null) {
            return mSession.getIsTop();
        }
        // 设置了置顶需要创建一条显示的session用来记录设置
        return false;
    }

    // 需要获取置顶时间

    // 需要获取是否显示
    public boolean isNeedShow() {
        if (mSession != null) {
            return mSession.isNeedShow();
        }
        return true;
    }

    /**
     * 是否有@自己的消息（群聊使用）
     */
    public boolean hasGroupAtMsg() {
        if (mSession != null) {
            return mSession.getFirstUnReadGroupAtMsgId() > 0;
        }
        return false;
    }

    public long getTopTime() {
        if (mSession != null) {
            return mSession.getTopTime();
        }
        return -1;
    }

    public int getUnReadNum() {
        if (mSession != null) {
            return mSession.getUnReadNum();
        }
        return 0;
    }

    public boolean merge(TmpMessage tmpMessage) {
        if (!belong(tmpMessage)) {
            return false;
        }
        if (getLatestMsgCreateTime(true) < tmpMessage.getMsgCreateTime()) {
            this.mTmpMessage = tmpMessage;
            return true;
        }
        return true;
    }

    public boolean merge(Draft draft) {
        if (!belong(draft)) {
            return false;
        }
        if (getLatestMsgCreateTime(false) < draft.getMsgCreateTime()) {
            this.mDraft = draft;
            return true;
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        long lastMsgCreateTime = getLatestMsgCreateTime(false);
        String dateTime = new IMTimeUtil().getSessionListDisplayDate(lastMsgCreateTime);
        builder.append(getSessionName()).append(" ").append("top=").append(isTop()).append(" ").append(lastMsgCreateTime).append(" ").append(dateTime);
        return builder.toString();
    }

    public boolean isOnJob() {
        if (mSession != null){
            return mSession.getStatus() == Session.STATUS_ON_JOB;
        }
        return true;
    }

    public int getLastMsgType() {
        if (mTmpMessage != null && mSession != null) {
            long time1 = mTmpMessage.getMsgCreateTime();
            long time2 = mSession.getLatestMsgCreateTime();
            return time1 - time2 > 0 ? mTmpMessage.getMsgType() : mSession.getLatestMsgType();
        }
        // 再次，只有session
        else if (mSession != null) {
            return mSession.getLatestMsgType();
        }
        // 最次，只有tmpMessage，不可能出现的情况
        else if (mTmpMessage != null) {
            return mTmpMessage.getMsgType();
        }
        return ImMsgType.TEXT;
    }

    public int getLastMsgId() {
        if (mSession != null) {
            return mSession.getLatestMsgId();
        }
        return -1;
    }

    public Description getDescription() {
        if (mSession != null) {
            String string = mSession.getDescription();
            try {
                return new Gson().fromJson(string, Description.class);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
