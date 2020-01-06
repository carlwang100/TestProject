package com.sunland.new_im.websocket;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.sunland.new_im.utils.AccountUtils;
import com.sunland.new_im.websocket.packet.ImCid;
import com.sunland.new_im.websocket.packet.ImErrorPacket;
import com.sunland.new_im.websocket.packet.ImGetGroupMemberListReqPacket;
import com.sunland.new_im.websocket.packet.ImGetGroupMemberListResPacket;
import com.sunland.new_im.websocket.packet.ImGroupMsgDataReadAckReqPacket;
import com.sunland.new_im.websocket.packet.ImGroupMsgDataReadAckResponsePacket;
import com.sunland.new_im.websocket.packet.ImGroupMsgDataReadNotifyPacket;
import com.sunland.new_im.websocket.packet.ImGroupSyncMsgPacket;
import com.sunland.new_im.websocket.packet.ImHeartbeatPacket;
import com.sunland.new_im.websocket.packet.ImHeartbeatResPacket;
import com.sunland.new_im.websocket.packet.ImInviteGroupMemberNotifyPacket;
import com.sunland.new_im.websocket.packet.ImInviteGroupMemberReqPacket;
import com.sunland.new_im.websocket.packet.ImKickUserNotifyPacket;
import com.sunland.new_im.websocket.packet.ImLoginPacket;
import com.sunland.new_im.websocket.packet.ImLoginResPacket;
import com.sunland.new_im.websocket.packet.ImLogoutPacket;
import com.sunland.new_im.websocket.packet.ImLogoutResPacket;
import com.sunland.new_im.websocket.packet.ImModifyGroupInfoNotify;
import com.sunland.new_im.websocket.packet.ImModifyGroupInfoReqPacket;
import com.sunland.new_im.websocket.packet.ImModifyGroupInfoResPacket;
import com.sunland.new_im.websocket.packet.ImMsgReadAckReqPacket;
import com.sunland.new_im.websocket.packet.ImMsgReadAckResPacket;
import com.sunland.new_im.websocket.packet.ImMsgReadNotify;
import com.sunland.new_im.websocket.packet.ImMsgSearchReqPacket;
import com.sunland.new_im.websocket.packet.ImMsgSearchResPacket;
import com.sunland.new_im.websocket.packet.ImPullGroupSessionPropertyReqPacket;
import com.sunland.new_im.websocket.packet.ImPullGroupSessionPropertyResPacket;
import com.sunland.new_im.websocket.packet.ImPullMsgRecordReqPacket;
import com.sunland.new_im.websocket.packet.ImPullMsgRecordResPacket;
import com.sunland.new_im.websocket.packet.ImPullRecentSessionListReqPacket;
import com.sunland.new_im.websocket.packet.ImPullRecentSessionListResPacket;
import com.sunland.new_im.websocket.packet.ImPullRevokeMsgInfoListReqPacket;
import com.sunland.new_im.websocket.packet.ImPullRevokeMsgInfoListResPacket;
import com.sunland.new_im.websocket.packet.ImPullSessionPropertyReqPacket;
import com.sunland.new_im.websocket.packet.ImPullSessionPropertyResPacket;
import com.sunland.new_im.websocket.packet.ImQueryGroupManagerReqPacket;
import com.sunland.new_im.websocket.packet.ImQueryGroupManagerResPacket;
import com.sunland.new_im.websocket.packet.ImQueryMessageNoDisturbStatusReqPacket;
import com.sunland.new_im.websocket.packet.ImQueryMessageNoDisturbStatusResPacket;
import com.sunland.new_im.websocket.packet.ImQuerySessionBasicInfoReqPacket;
import com.sunland.new_im.websocket.packet.ImQuerySessionBasicInfoResPacket;
import com.sunland.new_im.websocket.packet.ImReceivedGroupMsgPacket;
import com.sunland.new_im.websocket.packet.ImReceivedSingleMsgPacket;
import com.sunland.new_im.websocket.packet.ImRemoveGroupMemberNotifyPacket;
import com.sunland.new_im.websocket.packet.ImRemoveGroupMemberReqPacket;
import com.sunland.new_im.websocket.packet.ImRemoveGroupMemberResPacket;
import com.sunland.new_im.websocket.packet.ImRevokeGroupMsgNotifyPacket;
import com.sunland.new_im.websocket.packet.ImRevokeGroupMsgReqPacket;
import com.sunland.new_im.websocket.packet.ImRevokeGroupMsgResPacket;
import com.sunland.new_im.websocket.packet.ImRevokeMsgNotifyPacket;
import com.sunland.new_im.websocket.packet.ImRevokeMsgReqPacket;
import com.sunland.new_im.websocket.packet.ImRevokeMsgResPacket;
import com.sunland.new_im.websocket.packet.ImSendGroupMsgAckPacket;
import com.sunland.new_im.websocket.packet.ImSendGroupMsgPacket;
import com.sunland.new_im.websocket.packet.ImSendSingleMsgAckPacket;
import com.sunland.new_im.websocket.packet.ImSendSingleMsgPacket;
import com.sunland.new_im.websocket.packet.ImSessionType;
import com.sunland.new_im.websocket.packet.ImSetGroupSessionOptionNotifyPacket;
import com.sunland.new_im.websocket.packet.ImSetGroupSessionOptionReqPacket;
import com.sunland.new_im.websocket.packet.ImSetGroupSessionOptionResPacket;
import com.sunland.new_im.websocket.packet.ImSetMessageNoDisturbStatusReqPacket;
import com.sunland.new_im.websocket.packet.ImSetMessageNoDisturbStatusResPacket;
import com.sunland.new_im.websocket.packet.ImSetSessionOptionNotifyPacket;
import com.sunland.new_im.websocket.packet.ImSetSessionOptionReqPacket;
import com.sunland.new_im.websocket.packet.ImSetSessionOptionResPacket;
import com.sunland.new_im.websocket.packet.ImSyncMsgPacket;
import com.sunland.new_im.websocket.packet.ImTransferGroupIdentifyNotify;
import com.sunland.new_im.websocket.packet.ImTransferGroupLeaderReqPacket;
import com.sunland.new_im.websocket.packet.ImTransferGroupLeaderResPacket;
import com.sunland.new_im.websocket.packet.ImUpdateUserDeviceTokenReqPacket;
import com.sunland.new_im.websocket.packet.ImUpdateUserDeviceTokenResPacket;
import com.sunland.new_im.websocket.packet.InviteGroupMemberResPacket;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;
import com.sunland.new_im.websocket.utils.WsUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.sunland.new_im.websocket.packet.ImSetMessageNoDisturbStatusReqPacket.IS_DISTURBED;
import static com.sunland.new_im.websocket.packet.ImSetMessageNoDisturbStatusReqPacket.IS_NO_DISTURB;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature: IM的websocket通道，负责发送和接收、解析数据包
 */
public class ImWsChannel extends WebSocketChannel<ImBaseRequestPacket> {
    private static final String TAG = "WebSocket ImWsChannel";
    private static final long HEARTBEAT_INTERVAL = 30L; //心跳间隔
    private final ImWsListener imWsListener;
    private ImListenerQueue mListenerQueue = ImListenerQueue.instance(); //储存需要回调的请求，判断请求是否错误、超时
    private long imId;
    private boolean shouldNotReconnect = false; //如果账号被T、账号登出或服务器内部错误，无需触发重连
    private List<WebSocketClient.WsClientListener> mListenerList = new LinkedList<>();

    private Object mLock = new Object();

    public ImWsChannel(ImWsListener imWsListener, Context context, String host, long imId) {
        super(context, host, HEARTBEAT_INTERVAL);
        this.imWsListener = imWsListener;
        this.imId = imId;
    }

    /**
     * 检查IM长连接状态
     * @return 是否已连接
     * */
    public boolean checkImConnection(){
        Log.i(TAG, "------------检查WebSocket长连接状态: " + isConnected() + " ------------");

        if (super.shouldReConnect()){//手动检查长连接时，无需考虑shouldNotReconnect为true的情况
            getReConnector().reSet();
            getReConnector().reTry();
        }
        return isConnected();
    }

    /**
     * 长连接断开时，是否需要自动重连
     * */
    @Override
    public boolean shouldReConnect() {
        return super.shouldReConnect() && !shouldNotReconnect;
    }

    @Override
    void onStateChanged(WebSocketClient.State state) {
        synchronized (mLock) {
            for (WebSocketClient.WsClientListener wsClientListener : mListenerList) {
                if (wsClientListener != null) {
                    wsClientListener.onStateReceived(state);
                }
            }
        }
    }

    public void registerWsClientListener(WebSocketClient.WsClientListener socketListener) {
        synchronized (mLock) {
            mListenerList.add(socketListener);
        }
    }

    public void unregisterWsClientListener(WebSocketClient.WsClientListener socketListener) {
        synchronized (mLock) {
            mListenerList.remove(socketListener);
        }
    }

    @Override
    public void release() {
        super.release();
        mListenerQueue.onDestory();
    }

    /**
     * 发送数据包
     * */
    @Override
    void sendPacket(final ImBaseRequestPacket packet) {
        if (isThreadNotWork() || !isConnected()) return;
        if (getThreadExecutor() == null || getThreadExecutor().isShutdown()){
            return;
        }
        getThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                String msgJson = JsonParser.toJson(packet);
                if (getWsClient() != null){
                    getWsClient().sendPacket(msgJson);
                }
            }
        });
    }

    /**
     * 发送数据包
     * 需要得到回复的请求，带上uniqueKey，使response和request相匹配
     */
    private void sendPacket(ImBaseRequestPacket packet, ImPacketListener packetListener) {
        String uniqueKey = packet.getEntry().getUniqueKey();
        try {
            mListenerQueue.push(uniqueKey, packetListener);
            sendPacket(packet);
        } catch (Exception e) {
            if (packetListener != null) {
                packetListener.onFailed(uniqueKey);
            }
            mListenerQueue.pop(uniqueKey);
        }
    }

    /**
     * IM登录
     * */
    @Override
    void loginWebSocket() {
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.LOGIN_REQ);
        ImLoginPacket.UserLoginReqBean loginReqBean = new ImLoginPacket.UserLoginReqBean();
        loginReqBean.setAppid(NetConstant.getImAppId());
        //todo 暂时写死，正式环境要动态去改
        loginReqBean.setClientVersion("1440");
        loginReqBean.setOnlineStatus(1);
        loginReqBean.setUserType(1);
        sendPacket(new ImLoginPacket(entryBean, loginReqBean));

        //回调队列开始轮询
        mListenerQueue.onStart();
    }

    /**
     * IM登出
     * */
    public void logoutWebSocket(ImPacketListener packetListener){
        //退出登录时 不触发自动重连
        shouldNotReconnect = true;
        ImLogoutPacket imLogoutPacket = new ImLogoutPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.LOGOUT_REQ, WsUtil.generateUniqueKey()), new ImLogoutPacket.UserLogoutReqBean(5));
        sendPacket(imLogoutPacket, packetListener);
    }

    /**
     * 发送心跳包
     * */
    @Override
    void sendHeartbeatPacket() {
        ImHeartbeatPacket.ClientHeartBeatBean clientHeartBeat = new ImHeartbeatPacket.ClientHeartBeatBean();
        sendPacket(new ImHeartbeatPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.HEARTBEAT), clientHeartBeat));
    }

    /**
     * 发送单聊消息
     * */
    public void sendSingleMsg(long receiverImid, String content, int imMsgType, String uniqueKey, String fromUserName, String fromUserImageUrl, ImPacketListener listener) {
        ImSendSingleMsgPacket.IMMsgDataBean singleMsg = new ImSendSingleMsgPacket.IMMsgDataBean();
        singleMsg.setMsgType(imMsgType);
        singleMsg.setSessionType(ImSessionType.TYPE_SINGLE_CHAT);
        singleMsg.setSessionId(receiverImid);
        singleMsg.setFromUserName(fromUserName);
        singleMsg.setImageUrl(fromUserImageUrl);
        singleMsg.setMsgContent(content);
        ImSendSingleMsgPacket imSendSingleMsgPacket = new ImSendSingleMsgPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.SEND_SINGLE_MSG_REQ, uniqueKey), singleMsg);
        sendPacket(imSendSingleMsgPacket, listener);
    }

    /**
     * 发送群聊消息
     * */
    public void sendGroupMsg(long receiverImid, String content, int imMsgType, String uniqueKey, String fromUserName,
                             String fromUserImageUrl,String sessionName, String sessionImgUrl,ImPacketListener listener, int groupAtType, String groupAtMember) {
        ImSendGroupMsgPacket.IMGroupMsgDataBean groupMsg = new ImSendGroupMsgPacket.IMGroupMsgDataBean();
        groupMsg.setSessionId(receiverImid);
        groupMsg.setFromUserName(fromUserName);
        groupMsg.setFromUserImageUrl(fromUserImageUrl);
        groupMsg.setSessionName(sessionName);
        groupMsg.setSessionImageUrl(sessionImgUrl);
        groupMsg.setSessionType(ImSessionType.TYPE_GROUP_CHAT);
        groupMsg.setMsgType(imMsgType);
        groupMsg.setMsgContent(content);
        groupMsg.setGroupAtType(groupAtType);
        // TODO: 2018/11/2 @消息
        //只有type为1的时候才去设置群成员的@信息
        if (groupAtType == 1){
            try {
                JSONObject object = new JSONObject(groupAtMember);
                Map<Long, String> map = new HashMap<>();
                Iterator iterator = object.keys();
                while (iterator.hasNext()){
                    String imid = (String) iterator.next();
                    String name = object.optString(imid);
                    map.put(Long.valueOf(imid), name);
                }
                groupMsg.setGroupAtMember(map);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        ImSendGroupMsgPacket imSendGroupMsgPacket = new ImSendGroupMsgPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.SEND_GROUP_MSG_REQ, uniqueKey), groupMsg);
        sendPacket(imSendGroupMsgPacket, listener);
    }


    /**
     * 拉取历史聊天消息
     * */
    public void pullMsgRecord(int sessionType, long sessionid, int msgid, int orient, int pullNum, int needUserInfo, ImPacketListener listener) {
        ImBaseRequestPacket.EntryBean entry = new ImBaseRequestPacket.EntryBean(imId, ImCid.PULL_MSG_RECORD_REQ, WsUtil.generateUniqueKey());
        ImPullMsgRecordReqPacket.PullChatMsgRecordReqBean reqBean = new ImPullMsgRecordReqPacket.PullChatMsgRecordReqBean();
        reqBean.setSessionType(sessionType);
        reqBean.setSessionId(sessionid);
        reqBean.setMsgId(msgid);
        reqBean.setOrient(orient);
        reqBean.setNum(pullNum);
        reqBean.setNeedUserInfo(needUserInfo);
        ImPullMsgRecordReqPacket imPullMsgRecordReqPacket = new ImPullMsgRecordReqPacket(entry, reqBean);
        sendPacket(imPullMsgRecordReqPacket, listener);
    }

    /**
     * 更新设备token，第三方推送需要用
     * */
    public void updateUserDeviceToken(boolean isXiaoMi, String deviceToken, ImPacketListener listener){
        ImUpdateUserDeviceTokenReqPacket.UpdateUserDeviceTokenReqBean updateUserDeviceTokenReqBean = new ImUpdateUserDeviceTokenReqPacket.UpdateUserDeviceTokenReqBean();
        updateUserDeviceTokenReqBean.setPushPlatform(isXiaoMi ? 2 : 1);//1(信鸽)，2(小米)
        updateUserDeviceTokenReqBean.setDeviceToken(deviceToken);
        sendPacket(new ImUpdateUserDeviceTokenReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.UPDATE_DEVICE_TOKEN_REQ, WsUtil.generateUniqueKey()), updateUserDeviceTokenReqBean), listener);
    }

    /**
     * 拉取会话列表
     * @param num 拉取的会话数
     * */
    public void pullRecentSessionList(int num, ImPacketListener listener){
        ImPullRecentSessionListReqPacket.PullRecentSessionListReqBean pullRecentSessionListReqBean = new ImPullRecentSessionListReqPacket.PullRecentSessionListReqBean();
        pullRecentSessionListReqBean.setPullSessionNum(num);
        sendPacket(new ImPullRecentSessionListReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.PULL_SESSION_LIST_REQ, WsUtil.generateUniqueKey()), pullRecentSessionListReqBean), listener);
    }

    /**
     * 单聊会话列表操作
     * */
    public void setSessionOption(long sessionId, int action, ImPacketListener listener){
        ImSetSessionOptionReqPacket.SetSessionOptionReqBean setSessionOptionReq = new ImSetSessionOptionReqPacket.SetSessionOptionReqBean();
        setSessionOptionReq.setSessionType(ImSessionType.TYPE_SINGLE_CHAT);
        setSessionOptionReq.setAction(action);
        setSessionOptionReq.setSessionId(sessionId);
        sendPacket(new ImSetSessionOptionReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.SESSION_OPERATION_REQ, WsUtil.generateUniqueKey()), setSessionOptionReq), listener);
    }

    /**
     * 群聊会话列表操作
     * */
    public void setGroupSessionOption(long sessionId, int action, ImPacketListener listener){
        ImSetGroupSessionOptionReqPacket.SetGroupSessionOptionReqBean setSessionOptionReq = new ImSetGroupSessionOptionReqPacket.SetGroupSessionOptionReqBean();
        setSessionOptionReq.setSessionType(ImSessionType.TYPE_GROUP_CHAT);
        setSessionOptionReq.setAction(action);
        setSessionOptionReq.setSessionId(sessionId);
        sendPacket(new ImSetGroupSessionOptionReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.SESSION_GROUP_OPERATION_REQ, WsUtil.generateUniqueKey()), setSessionOptionReq), listener);
    }

    /**
     * 拉取会话列表属性
     * */
    public void pullSessionProperty(long sessionId, int sessionType ,ImPacketListener listener){
        if (sessionType == ImSessionType.TYPE_SINGLE_CHAT) {
            pullSingleSessionProperty(sessionId, listener);
        } else {
            pullGroupSessionProperty(sessionId, listener);
        }
    }

    private void pullSingleSessionProperty(long sessionId, ImPacketListener listener) {
        ImPullSessionPropertyReqPacket.PullSingleChatPropertyReqBean singleChatPropertyReq = new ImPullSessionPropertyReqPacket.PullSingleChatPropertyReqBean();
        singleChatPropertyReq.setSessionId(sessionId);
        singleChatPropertyReq.setSessionType(ImSessionType.TYPE_SINGLE_CHAT);
        ImPullSessionPropertyReqPacket pullSessionPropertyReqPacket = new ImPullSessionPropertyReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.PULL_SESSION_PROPERTY_REQ, WsUtil.generateUniqueKey()), singleChatPropertyReq);
        sendPacket(pullSessionPropertyReqPacket, listener);
    }

    private void pullGroupSessionProperty(long sessionId, ImPacketListener listener) {
        ImPullGroupSessionPropertyReqPacket packet
                = new ImPullGroupSessionPropertyReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.PULL_GROUP_SESSION_PROPERTY_REQ, WsUtil.generateUniqueKey()), sessionId);
        sendPacket(packet, listener);
    }

    /**
     * 发送消息已读回执
     * */
    public void sendMsgReadAck(long sessionId, int recvMsgId, int latestReadMsgId, ImPacketListener listener){
        ImMsgReadAckReqPacket.IMMsgDataReadAckBean imMsgDataReadAck = new ImMsgReadAckReqPacket.IMMsgDataReadAckBean();
        imMsgDataReadAck.setSessionId(sessionId);
        imMsgDataReadAck.setSessionType(ImSessionType.TYPE_SINGLE_CHAT);
        if (recvMsgId != -1) {
            imMsgDataReadAck.setRecvMsgId(recvMsgId);
        }
        if (latestReadMsgId != -1) {
            imMsgDataReadAck.setLatestReadMsgId(latestReadMsgId);
        }
        ImMsgReadAckReqPacket readAckReqPacket = new ImMsgReadAckReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.MSG_READ_REQ, WsUtil.generateUniqueKey()), imMsgDataReadAck);
        sendPacket(readAckReqPacket, listener);
    }

    /**
     * 发送群消息的已读回执
     */
    public void sendGroupMsgReadAck(long sessionId, int recvMsgId, int latestReadMsgId, ImPacketListener<ImGroupMsgDataReadAckResponsePacket.IMGroupMsgDataReadAckResponseBean> listener) {
        ImGroupMsgDataReadAckReqPacket.IMGroupMsgDataReadAckBean imMsgDataReadAck = new ImGroupMsgDataReadAckReqPacket.IMGroupMsgDataReadAckBean();
        imMsgDataReadAck.setSessionId(sessionId);
        imMsgDataReadAck.setSessionType(ImSessionType.TYPE_GROUP_CHAT);
        if (recvMsgId != -1) {
            imMsgDataReadAck.setRecvMsgId(recvMsgId);
        }
        if (latestReadMsgId != -1) {
            imMsgDataReadAck.setLatestReadMsgId(latestReadMsgId);
        }
        ImGroupMsgDataReadAckReqPacket readAckReqPacket = new ImGroupMsgDataReadAckReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.GROUP_MSG_READ_REQ, WsUtil.generateUniqueKey()), imMsgDataReadAck);
        sendPacket(readAckReqPacket, listener);
    }

    /**
     * 创建群，邀请成员入群
     * */
    public void inviteGroupMember(long groupId, long inviterId, String groupName, String sessionImg, List<Long> inviteeIdList, int groupType, ImPacketListener listener, int inviteMethod){
        ImInviteGroupMemberReqPacket.InviteGroupMemberReqBean inviteGroupMemberReq = new ImInviteGroupMemberReqPacket.InviteGroupMemberReqBean();
        inviteGroupMemberReq.setGroupId(groupId);
        inviteGroupMemberReq.setGroupName(groupName);
        inviteGroupMemberReq.setImageUrl(sessionImg);
        inviteGroupMemberReq.setInviteeIdList(inviteeIdList);
        inviteGroupMemberReq.setInviterName(AccountUtils.getUserName(getmContext()));
        inviteGroupMemberReq.setInviterId(inviteMethod == 2 ? inviterId : AccountUtils.getEhrIMId(getmContext()));
        inviteGroupMemberReq.setGroupType(groupType);
        inviteGroupMemberReq.setInviteMethod(inviteMethod);
        ImInviteGroupMemberReqPacket inviteGroupMemberReqPacket = new ImInviteGroupMemberReqPacket(new ImInviteGroupMemberReqPacket.EntryBean(imId, ImCid.INVITE_GROUP_MEMBER_REQ, WsUtil.generateUniqueKey()), inviteGroupMemberReq);
        sendPacket(inviteGroupMemberReqPacket, listener);
    }

    public void fetchGroupMemberList(long mSessionId, int memberNum, ImPacketListener listener){
        ImGetGroupMemberListReqPacket.GetGroupMemberListReqBean memberListReq = new ImGetGroupMemberListReqPacket.GetGroupMemberListReqBean();
        memberListReq.setGroupId(mSessionId);
        memberListReq.setFirstFewMembersNum(memberNum);
        ImGetGroupMemberListReqPacket reqPacket = new ImGetGroupMemberListReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.GET_GROUP_MEMBER_LIST_REQ, WsUtil.generateUniqueKey()), memberListReq);
        sendPacket(reqPacket, listener);
    }

    public void modifyGroupInfo(long groupId, Map<Integer, String> modifyInfo, ImPacketListener listener){
        ImModifyGroupInfoReqPacket.ModifyGroupInfoReqBean modifyGroupInfoReq = new ImModifyGroupInfoReqPacket.ModifyGroupInfoReqBean();
        modifyGroupInfoReq.setFromUserName(AccountUtils.getUserName(getmContext()));
        modifyGroupInfoReq.setGroupId(groupId);
        modifyGroupInfoReq.setModifyInfo(modifyInfo);
        ImModifyGroupInfoReqPacket infoReqPacket = new ImModifyGroupInfoReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.MODIFY_GROUP_INFO_REQ, WsUtil.generateUniqueKey()), modifyGroupInfoReq);
        sendPacket(infoReqPacket, listener);
    }

    public void removeGroupMember(long groupId, long[] removedIdList, ImPacketListener listener) {
        ImRemoveGroupMemberReqPacket.RemoveGroupMemberReqBean removeGroupMemberReq = new ImRemoveGroupMemberReqPacket.RemoveGroupMemberReqBean();
        removeGroupMemberReq.setGroupId(groupId);
        removeGroupMemberReq.setRemovedIdList(removedIdList);
        ImRemoveGroupMemberReqPacket memberReqPacket = new ImRemoveGroupMemberReqPacket(new ImBaseRequestPacket.EntryBean(imId, ImCid.REMOVE_GROUP_MEMBER_REQ, WsUtil.generateUniqueKey()), removeGroupMemberReq);
        sendPacket(memberReqPacket, listener);
    }

    public void setMessageNoDisturb(boolean isNoDisturb, ImPacketListener listener) {
        ImSetMessageNoDisturbStatusReqPacket.SetMessageNoDisturbStatusReq status = new ImSetMessageNoDisturbStatusReqPacket.SetMessageNoDisturbStatusReq();
        status.setNoDisturb(isNoDisturb? IS_NO_DISTURB : IS_DISTURBED);//  1、设置免打扰  0、取消免打扰
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.SET_MESSAGE_NO_DISTURB_REQ, WsUtil.generateUniqueKey());
        ImSetMessageNoDisturbStatusReqPacket req = new ImSetMessageNoDisturbStatusReqPacket(entryBean, status);
        sendPacket(req, listener);
    }

    public void queryMessageNoDisturb(ImPacketListener listener) {
        ImQueryMessageNoDisturbStatusReqPacket.QueryMessageNoDisturbStatusReqBean status = new ImQueryMessageNoDisturbStatusReqPacket.QueryMessageNoDisturbStatusReqBean();
        status.setExtralInfo("");
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.QUERY_MESSAGE_NO_DISTURB_REQ, WsUtil.generateUniqueKey());
        ImQueryMessageNoDisturbStatusReqPacket req = new ImQueryMessageNoDisturbStatusReqPacket(entryBean, status);
        sendPacket(req, listener);
    }

    public void searchMsg(String keyword, ImPacketListener listener) {
        ImMsgSearchReqPacket.MsgSearchReqBean reqBean = new ImMsgSearchReqPacket.MsgSearchReqBean();
        reqBean.setKeywords(keyword);
        reqBean.setDays(365);
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.MESSAGE_SEARCH_REQ, WsUtil.generateUniqueKey());
        ImMsgSearchReqPacket req = new ImMsgSearchReqPacket(entryBean, reqBean);
        sendPacket(req, listener);
    }


    public void transferGroupLeader(long mSessionId, long toUserId, ImPacketListener<ImTransferGroupLeaderResPacket.ImTransferGroupLeaderRes> listener) {
        ImTransferGroupLeaderReqPacket.ImTransferGroupLeaderReq req = new ImTransferGroupLeaderReqPacket.ImTransferGroupLeaderReq();
        req.setGroupId(mSessionId);
        req.setMemberDegree(2);
        req.setToUserId(toUserId);
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.TRANSFER_GROUP_LEADER, WsUtil.generateUniqueKey());
        ImTransferGroupLeaderReqPacket reqPacket = new ImTransferGroupLeaderReqPacket(entryBean, req);
        sendPacket(reqPacket, listener);
    }


    /**
     *
     * @param listener
     * @param singleChatList 单聊会话id列表（1、传输列表时，会拉取列表内的人员会话信息（如果单纯想拉取头像和名字，此信息有些许冗余），2、列表内仅包含一个0，表示获取跟imid聊过的所有单聊会话）
     * @param groupChatList 群聊会话id列表（1、传输列表时，会拉取列表内imid当前所在的群组信息，2、列表内仅包含一个0，表示获取imid当前所在的所有群组信息）
     * @param getAtMsg False， 不获取群聊中的未读@消息id列表； True获取群聊中的未读@消息id列表。默认不填为False
     */
    public void querySessionBasicInfo(ImPacketListener listener, @Nullable List<Long> singleChatList, @Nullable List<Long> groupChatList, boolean getAtMsg) {
        ImQuerySessionBasicInfoReqPacket.QuerySessionBasicInfoReqBean reqBean = new ImQuerySessionBasicInfoReqPacket.QuerySessionBasicInfoReqBean();
        if (singleChatList != null && !singleChatList.isEmpty()) {
            reqBean.setSingleChatList(singleChatList);
            reqBean.setBGetUnReadGroupAtMsgIdList(false);
        } else if (groupChatList != null && !groupChatList.isEmpty()) {
            reqBean.setGroupChatList(groupChatList);
            reqBean.setBGetUnReadGroupAtMsgIdList(getAtMsg);
        } else {
            throw new IllegalArgumentException("singleChatList and groupChatList both are null or empty");
        }
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.QUERY_SESSION_BASIC_INFO_REQ, WsUtil.generateUniqueKey());
        ImQuerySessionBasicInfoReqPacket packet = new ImQuerySessionBasicInfoReqPacket(entryBean, reqBean);
        sendPacket(packet, listener);
    }

    /**
     * 撤销单聊会话
     * @param sessionId
     * @param msgId
     * @param listener
     */
    public void revokeMsg(long sessionId, int msgId, ImPacketListener listener) {
        ImRevokeMsgReqPacket packet = new ImRevokeMsgReqPacket(this.imId, sessionId, msgId);
        sendPacket(packet, listener);
    }

    /**
     * 撤销群聊会话
     * @param sessionId
     * @param msgId
     * @param listener
     */
    public void revokeGrpMsg(long sessionId, int msgId, ImPacketListener listener) {
        ImRevokeGroupMsgReqPacket packet = new ImRevokeGroupMsgReqPacket(this.imId, sessionId, msgId);
        sendPacket(packet, listener);
    }

    /**
     * CID7053 - 拉取群管理人员信息
     * @param sessionIdList
     * @param listener
     */
    public void queryGroupManager(List<Long> sessionIdList, ImPacketListener listener) {
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.QUERY_GROUP_MANAGER_REQ, WsUtil.generateUniqueKey());
        ImQueryGroupManagerReqPacket packet = new ImQueryGroupManagerReqPacket(entryBean);
        ImQueryGroupManagerReqPacket.QueryGroupManagerReqBean reqBean = new ImQueryGroupManagerReqPacket.QueryGroupManagerReqBean();
        reqBean.setGroupIdList(sessionIdList);
        packet.setQueryGroupManagerReq(reqBean);
        sendPacket(packet, listener);
    }

    /**
     * CID4019 - 拉取撤回消息记录
     * @param clientLatestAccessTime
     * @param listener
     */
    public void pullRevokeMsgInfoList(long clientLatestAccessTime, ImPacketListener listener) {
        ImBaseRequestPacket.EntryBean entryBean = new ImBaseRequestPacket.EntryBean(imId, ImCid.PULL_REVOKE_MSG_INFO_LIST_REQ, WsUtil.generateUniqueKey());
        ImPullRevokeMsgInfoListReqPacket.PullRevokeMsgInfoListReqBean reqBean = new ImPullRevokeMsgInfoListReqPacket.PullRevokeMsgInfoListReqBean();
        reqBean.setClientLatestAccessTime(clientLatestAccessTime);
        ImPullRevokeMsgInfoListReqPacket packet = new ImPullRevokeMsgInfoListReqPacket(entryBean, reqBean);
        sendPacket(packet, listener);
    }

    @Override
    void handleData(String receivedData) {
        shouldNotReconnect = false;
        ImBaseResponsePacket responsePacket = JsonParser.parseJsonObject(receivedData, ImBaseResponsePacket.class);
        int cid = responsePacket.getCid();
        String uniqueKey = responsePacket.getUniqueKey();
        switch (cid) {
            case ImCid.LOGIN_RES:
                onLoginReply(receivedData);
                break;
            case ImCid.LOGOUT_RES:
                onLoginOutReply(receivedData, uniqueKey);
                break;
            case ImCid.HEARTBEAT:
                onHeartbeatReceived(receivedData);
                break;
            case ImCid.MESSAGE_SEARCH_RSP:
                onMessageSearchReceived(receivedData, uniqueKey);
                break;
            case ImCid.SEND_SINGLE_MSG_ACK:
                onSendSingleMsgResponse(receivedData, uniqueKey);
                break;
            case ImCid.PULL_MSG_RECORD_RES:
                onPullMsgRecordResponse(receivedData, uniqueKey);
                break;
            case ImCid.PULL_SESSION_LIST_RES:
                onPullSessionListResponse(receivedData, uniqueKey);
                break;
            case ImCid.SESSION_OPERATION_RES:
                onSessionOperationResponse(receivedData, uniqueKey);
                break;
            case ImCid.SESSION_GROUP_OPERATION_RES:
                onGroupSessionOperationResponse(receivedData, uniqueKey);
                break;
            case ImCid.RECEIVE_SINGLE_MSG_NOTIFY:
                onReceiveSingleMsgNotify(receivedData);
                break;
            case ImCid.RECEIVE_GROUP_MSG_NOTIFY:
                onReceiveGroupMsgNotify(receivedData);
                break;
            case ImCid.SESSION_OPERATION_NOTIFY:
                onSessionOperationNotify(receivedData);
                break;
            case ImCid.GROUP_SESSION_OPERATION_NOTIFY:
                onGroupSessionOperationNotify(receivedData);
                break;
            case ImCid.ERROR:
                onWsError(receivedData);
                break;
            case ImCid.KICK_OUT_NOTIFY:
                onKickOutNotify(receivedData);
                break;
            case ImCid.PULL_SESSION_PROPERTY_RES:
                onPullSessionProperty(receivedData, uniqueKey);
                break;
            case ImCid.PULL_GROUP_SESSION_PROPERTY_RES:
                onPullGroupSessionProperty(receivedData, uniqueKey);
                break;
            case ImCid.MSG_READ_RES:
                onMsgReadAckResponse(receivedData, uniqueKey);
                break;
            case ImCid.GROUP_MSG_READ_RES:
                onGroupMsgReadAckResponse(receivedData, uniqueKey);
                break;
            case ImCid.GROUP_MSG_READ_NOTIFY:
                onGroupMsgReadNotify(receivedData);
                break;
            case ImCid.MSG_READ_NOTIFY:
                onMsgReadNotify(receivedData);
                break;
            case ImCid.MSG_SINGLE_SYNC_RES:
                onMsgSyncNotify(receivedData);
                break;
            case ImCid.MSG_GROUP_SYNC_RES:
                onGroupMsgSyncNotify(receivedData);
                break;
            case ImCid.UPDATE_DEVICE_TOKEN_RSP:
                onUpdateTokenNotify(receivedData, uniqueKey);
                break;
            case ImCid.INVITE_GROUP_MEMBER_RES:
                onInviteGroupMemberResponse(receivedData, uniqueKey);
                break;
            case ImCid.INVITE_GROUP_MEMBER_NOTIFY:
                onInviteGroupMemberNotify(receivedData);
                break;
            case ImCid.REMOVE_GROUP_MEMBER_NOTIFY:
                onRemoveGroupMemberNotify(receivedData);
                break;
            case ImCid.SEND_GROUP_MSG_ACK:
                onSendGroupMsgResponse(receivedData, uniqueKey);
                break;
            case ImCid.GET_GROUP_MEMBER_LIST_RES:
                onGetGroupMemberListResponse(receivedData, uniqueKey);
                break;
            case ImCid.MODIFY_GROUP_INFO_RES:
                onModifyGroupInfoResponse(receivedData, uniqueKey);
                break;
            case ImCid.MODIFY_GROUP_INFO_NOTIFY:
                onModifyGroupInfoNotify(receivedData);
                break;
            case ImCid.REMOVE_GROUP_MEMBER_RES:
                onRemoveGroupMember(receivedData, uniqueKey);
                break;
            case ImCid.SET_MESSAGE_NO_DISTURB_RSP:
                onSetMessageNoDisturb(receivedData, uniqueKey);
                break;
            case ImCid.QUERY_MESSAGE_NO_DISTURB_RSP:
                onQueryMessageNoDisturb(receivedData, uniqueKey);
                break;
            case ImCid.QUERY_SESSION_BASIC_INFO_RES:
                onQuerySessionBasicInfo(receivedData, uniqueKey);
                break;
            case ImCid.REVOKE_MSG_RES:
                onRevokeMsgResponse(receivedData, uniqueKey);
                break;
            case ImCid.REVOKE_GRP_MSG_RES:
                onRevokeGrpMsgResponse(receivedData, uniqueKey);
                break;
            case ImCid.REVOKE_MSG_NOTIFY:
                onRevokeMsgNotify(receivedData);
                break;
            case ImCid.REVOKE_GRP_MSG_NOTIFY:
                onRevokeGrpMsgNotify(receivedData);
                break;
            case ImCid.QUERY_GROUP_MANAGER_RES:
                onQueryGroupManagerRes(receivedData, uniqueKey);
                break;
            case ImCid.PULL_REVOKE_MSG_INFO_LIST_RES:
                onPullRevokeMsgInfoListRes(receivedData, uniqueKey);
                break;
            case ImCid.TRANSFER_GROUP_LEADER_RSP:
                onTransferGroupLeaderRes(receivedData, uniqueKey);
                break;
            case ImCid.TRANSFER_GROUP_LEADER_NOTIFY:
                onTransferGroupLeaderNotify(receivedData, uniqueKey);
        }
    }

    private void onTransferGroupLeaderNotify(String receivedData, String uniqueKey) {
        ImTransferGroupIdentifyNotify imTransferGroupIdentifyNotify = JsonParser.parseJsonObject(receivedData, ImTransferGroupIdentifyNotify.class);
        ImTransferGroupIdentifyNotify.TransferGroupIdentifyNotify transferGroupIdentifyNotify = imTransferGroupIdentifyNotify.getTransferGroupIdentifyNotify();
        if (imWsListener != null && transferGroupIdentifyNotify != null){
            imWsListener.onTransferGroupLeaderNotify(transferGroupIdentifyNotify);
        }
    }

    private void onTransferGroupLeaderRes(String receivedData, String uniqueKey) {
        ImPacketListener<ImTransferGroupLeaderResPacket.ImTransferGroupLeaderRes> listener = mListenerQueue.pop(uniqueKey);
        ImTransferGroupLeaderResPacket transferGroupLeaderResPacket = JsonParser.parseJsonObject(receivedData, ImTransferGroupLeaderResPacket.class);
        if (listener != null && transferGroupLeaderResPacket != null && transferGroupLeaderResPacket.getImTransferGroupLeaderRes() != null) {
            listener.onSuccess(transferGroupLeaderResPacket.getImTransferGroupLeaderRes());
        }
    }

    private void onPullRevokeMsgInfoListRes(String receivedData, String uniqueKey) {
        ImPacketListener<ImPullRevokeMsgInfoListResPacket.PullRevokeMsgInfoListResBean> listener = mListenerQueue.pop(uniqueKey);
        ImPullRevokeMsgInfoListResPacket resPacket = JsonParser.parseJsonObject(receivedData, ImPullRevokeMsgInfoListResPacket.class);
        if (listener != null && resPacket != null && resPacket.getPullRevokeMsgInfoListRes() != null) {
            listener.onSuccess(resPacket.getPullRevokeMsgInfoListRes());
        }
    }

    private void onQueryGroupManagerRes(String receivedData, String uniqueKey) {
        ImPacketListener<ImQueryGroupManagerResPacket.QueryGroupManagerResBean> listener = mListenerQueue.pop(uniqueKey);
        ImQueryGroupManagerResPacket resPacket = JsonParser.parseJsonObject(receivedData, ImQueryGroupManagerResPacket.class);
        if (listener != null && resPacket != null && resPacket.getQueryGroupManagerRes() != null) {
            listener.onSuccess(resPacket.getQueryGroupManagerRes());
        }
    }

    private void onRevokeGrpMsgNotify(String receivedData) {
        ImRevokeGroupMsgNotifyPacket notifyPacket = JsonParser.parseJsonObject(receivedData, ImRevokeGroupMsgNotifyPacket.class);
        if (imWsListener != null && notifyPacket != null){
            imWsListener.onRevokeGroupMsgNotify(notifyPacket.getRevokeGroupMsgNotify());
        }
    }

    private void onRevokeMsgNotify(String receivedData) {
        ImRevokeMsgNotifyPacket notifyPacket = JsonParser.parseJsonObject(receivedData, ImRevokeMsgNotifyPacket.class);
        if (imWsListener != null && notifyPacket != null){
            imWsListener.onRevokeMsgNotify(notifyPacket.getRevokeMsgNotify());
        }
    }

    private void onQuerySessionBasicInfo(String receivedData, String uniqueKey) {
        ImPacketListener<ImQuerySessionBasicInfoResPacket.QuerySessionBasicInfoResBean> listener = mListenerQueue.pop(uniqueKey);
        ImQuerySessionBasicInfoResPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImQuerySessionBasicInfoResPacket.class);
        if (listener != null && updateResPacket != null && updateResPacket.getQuerySessionBasicInfoRes() != null) {
            listener.onSuccess(updateResPacket.getQuerySessionBasicInfoRes());
        }
    }

    private void onMessageSearchReceived(String receivedData, String uniqueKey) {
        ImPacketListener<ImMsgSearchResPacket.MsgSearchResBean> listener = mListenerQueue.pop(uniqueKey);
        ImMsgSearchResPacket packet = JsonParser.parseJsonObject(receivedData, ImMsgSearchResPacket.class);
        if (listener != null && packet != null && packet.getMsgSearchRes() != null) {
            listener.onSuccess(packet.getMsgSearchRes());
        }
    }

    private void onGroupMsgReadNotify(String receivedData) {
        ImGroupMsgDataReadNotifyPacket packet = JsonParser.parseJsonObject(receivedData, ImGroupMsgDataReadNotifyPacket.class);
        if (imWsListener != null && packet != null && packet.getIMGroupMsgDataReadNotify() != null) {
            imWsListener.onGroupMsgDataReadNotify(packet.getIMGroupMsgDataReadNotify());
        }
    }

    private void onSetMessageNoDisturb(String receivedData, String uniqueKey) {
        ImPacketListener<ImSetMessageNoDisturbStatusResPacket.SetMessageNoDisturbStatusResBean> listener = mListenerQueue.pop(uniqueKey);
        ImSetMessageNoDisturbStatusResPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImSetMessageNoDisturbStatusResPacket.class);
        if (listener != null && updateResPacket != null && updateResPacket.getSetMessageNoDisturbStatusRes() != null) {
            listener.onSuccess(updateResPacket.getSetMessageNoDisturbStatusRes());
        }
    }

    private void onQueryMessageNoDisturb(String receivedData, String uniqueKey) {
        ImPacketListener<ImQueryMessageNoDisturbStatusResPacket.QueryMessageNoDisturbStatusResBean> listener = mListenerQueue.pop(uniqueKey);
        ImQueryMessageNoDisturbStatusResPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImQueryMessageNoDisturbStatusResPacket.class);
        if (listener != null && updateResPacket != null && updateResPacket.getQueryMessageNoDisturbStatusRes() != null) {
            listener.onSuccess(updateResPacket.getQueryMessageNoDisturbStatusRes());
        }
    }

    private void onInviteGroupMemberNotify(String receivedData) {
        ImInviteGroupMemberNotifyPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImInviteGroupMemberNotifyPacket.class);
        if (imWsListener != null && updateResPacket != null && updateResPacket.getInviteGroupMemberNotify() != null) {
            imWsListener.onInviteGroupMemberNotify(updateResPacket.getInviteGroupMemberNotify());
        }
    }

    private void onRemoveGroupMemberNotify(String receivedData) {
        ImRemoveGroupMemberNotifyPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImRemoveGroupMemberNotifyPacket.class);
        if (imWsListener != null && updateResPacket != null && updateResPacket.getRemoveGroupMemberNotify() != null) {
            imWsListener.onRemoveGroupMemberNotify(updateResPacket.getRemoveGroupMemberNotify());
        }
    }

    private void onUpdateTokenNotify(String receivedData, String uniqueKey) {
        ImPacketListener<ImUpdateUserDeviceTokenResPacket.UpdateUserDeviceTokenResBean> listener = mListenerQueue.pop(uniqueKey);
        ImUpdateUserDeviceTokenResPacket updateResPacket = JsonParser.parseJsonObject(receivedData, ImUpdateUserDeviceTokenResPacket.class);
        if (listener != null && updateResPacket != null && updateResPacket.getUpdateUserDeviceTokenRes() != null) {
            listener.onSuccess(updateResPacket.getUpdateUserDeviceTokenRes());
        }
    }

    private void onMsgSyncNotify(String receivedData) {
        ImSyncMsgPacket syncAckResPacket = JsonParser.parseJsonObject(receivedData, ImSyncMsgPacket.class);
        if (imWsListener != null && syncAckResPacket != null && syncAckResPacket.getIMMsgDataTerminalSync() != null) {
            imWsListener.onSyncMsgNotify(syncAckResPacket.getIMMsgDataTerminalSync());
        }
    }

    private void onGroupMsgSyncNotify(String receivedData) {

        ImGroupSyncMsgPacket groupSyncMsgPacket = JsonParser.parseJsonObject(receivedData, ImGroupSyncMsgPacket.class);
        if (imWsListener != null && groupSyncMsgPacket != null && groupSyncMsgPacket.getIMGroupMsgDataTerminalSync() != null) {
            imWsListener.onSyncGroupMsgNotify(groupSyncMsgPacket.getIMGroupMsgDataTerminalSync());
        }
    }

    private void onSessionOperationNotify(String receivedData) {
        ImSetSessionOptionNotifyPacket optionNotifyPacket = JsonParser.parseJsonObject(receivedData, ImSetSessionOptionNotifyPacket.class);
        if (imWsListener  != null && optionNotifyPacket != null){
            imWsListener.onSessionOperationNotify(optionNotifyPacket);
        }
    }

    private void onGroupSessionOperationNotify(String receivedData) {
        ImSetGroupSessionOptionNotifyPacket optionNotifyPacket = JsonParser.parseJsonObject(receivedData, ImSetGroupSessionOptionNotifyPacket.class);
        if (imWsListener  != null && optionNotifyPacket != null){
            imWsListener.onGroupSessionOperationNotify(optionNotifyPacket);
        }
    }

    private void onKickOutNotify(String receivedData) {
        ImKickUserNotifyPacket imKickUserNotifyPacket = JsonParser.parseJsonObject(receivedData, ImKickUserNotifyPacket.class);
        ImKickUserNotifyPacket.IMKickUserBean imKickUser = imKickUserNotifyPacket.getIMKickUser();
        if(imKickUser != null && imKickUser.getImid() == imId){
            imWsListener.onKickOutNotify(imKickUser);
            shouldNotReconnect = true;
            getHeartbeatCenter().stopHeartbeat();
        }
    }

    private void onLoginOutReply(String receivedData, String uniqueKey) {
        ImLogoutResPacket imLogoutResPacket = JsonParser.parseJsonObject(receivedData, ImLogoutResPacket.class);
        ImPacketListener<ImLogoutResPacket> listener = mListenerQueue.pop(uniqueKey);
        if (listener != null && imLogoutResPacket != null) {
            listener.onSuccess(imLogoutResPacket);
        }
    }

    private void onReceiveSingleMsgNotify(String receivedData) {
        ImReceivedSingleMsgPacket imReceivedSingleMsgPacket = JsonParser.parseJsonObject(receivedData, ImReceivedSingleMsgPacket.class);
        ImReceivedSingleMsgPacket.IMMsgDataDeliverBean imMsgDataDeliver = imReceivedSingleMsgPacket.getIMMsgDataDeliver();
        if (imMsgDataDeliver != null && imWsListener != null){
            imWsListener.onReceiveSingleMsgNotify(imMsgDataDeliver);
        }
    }

    private void onReceiveGroupMsgNotify(String receivedData) {
        ImReceivedGroupMsgPacket imReceivedGroupMsgPacket = JsonParser.parseJsonObject(receivedData, ImReceivedGroupMsgPacket.class);
        ImReceivedGroupMsgPacket.IMGroupMsgDataDeliverBean groupMsgDataDeliver = imReceivedGroupMsgPacket.getIMGroupMsgDataDeliver();
        if (imWsListener != null && groupMsgDataDeliver != null){
            imWsListener.onReceiveGroupMsgNotify(groupMsgDataDeliver);
        }
    }

    //心跳
    private void onHeartbeatReceived(String receivedData) {
        ImHeartbeatResPacket imHeartbeatResPacket = JsonParser.parseJsonObject(receivedData, ImHeartbeatResPacket.class);
        if (imHeartbeatResPacket != null && imHeartbeatResPacket.getCid() == ImCid.HEARTBEAT) {
            getHeartbeatCenter().onHeartbeatPacketAck();
        }
    }

    //登录回复
    private void onLoginReply(String receivedData) {
        ImLoginResPacket imLoginResPacket = JsonParser.parseJsonObject(receivedData, ImLoginResPacket.class);
        ImLoginResPacket.UserLoginResBean userLoginRes = imLoginResPacket.getUserLoginRes();

        if (userLoginRes == null) return;
        if (imWsListener != null){
            imWsListener.onLoginReply(userLoginRes);
        }

        if (userLoginRes.getResultCode() == ImErrorCode.SUCCESS){
            getHeartbeatCenter().startHeartbeat();
        }else if (userLoginRes.getResultCode() == ImErrorCode.SvcReqRC_LOGIN_IMID_INVALID_ERROR || userLoginRes.getResultCode() == ImErrorCode.SvcReqRC_LOGIN_ERROR_ATTEMPT_LIMIT_ERROR){
            //登陆返回无效imid或登陆限制错误，无需重连
            shouldNotReconnect = true;
        }
    }

    /**
     * 发送单聊消息ack
     * */
    private void onSendSingleMsgResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImSendSingleMsgAckPacket> listener = mListenerQueue.pop(uniqueKey);
        ImSendSingleMsgAckPacket imSendSingleMsgAckPacket = JsonParser.parseJsonObject(receivedData, ImSendSingleMsgAckPacket.class);
        if (listener != null && imSendSingleMsgAckPacket != null) {
            listener.onSuccess(imSendSingleMsgAckPacket);
        }
    }

    /**
     * 发送群聊消息ack
     * */
    private void onSendGroupMsgResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImSendGroupMsgAckPacket> listener = mListenerQueue.pop(uniqueKey);
        try {
            ImSendGroupMsgAckPacket imSendGroupMsgAckPacket = JsonParser.parseJsonObject(receivedData, ImSendGroupMsgAckPacket.class);
            if (listener != null && imSendGroupMsgAckPacket != null) {
                listener.onSuccess(imSendGroupMsgAckPacket);
            }
        } catch (Throwable throwable) {
            if (listener != null) {
                listener.onFailed(uniqueKey);
            }
        }

    }

    //拉取消息记录响应
    private void onPullMsgRecordResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImPullMsgRecordResPacket> listener = mListenerQueue.pop(uniqueKey);
        ImPullMsgRecordResPacket imPullMsgRecordResPacket = JsonParser.parseJsonObject(receivedData, ImPullMsgRecordResPacket.class);
        if (listener != null && imPullMsgRecordResPacket != null) {
            listener.onSuccess(imPullMsgRecordResPacket);
        }
    }

    //拉取会话列表
    private void onPullSessionListResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImPullRecentSessionListResPacket> listener = mListenerQueue.pop(uniqueKey);
        ImPullRecentSessionListResPacket imPullRecentSessionListResPacket = JsonParser.parseJsonObject(receivedData, ImPullRecentSessionListResPacket.class);
        if (listener != null && imPullRecentSessionListResPacket != null) {
            listener.onSuccess(imPullRecentSessionListResPacket);
        }
    }

    private void onSessionOperationResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImSetSessionOptionResPacket> listener = mListenerQueue.pop(uniqueKey);
        ImSetSessionOptionResPacket imSetSessionOptionResPacket = JsonParser.parseJsonObject(receivedData, ImSetSessionOptionResPacket.class);
        if (listener != null && imSetSessionOptionResPacket != null) {
            listener.onSuccess(imSetSessionOptionResPacket);
        }
    }

    private void onGroupSessionOperationResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImSetGroupSessionOptionResPacket> listener = mListenerQueue.pop(uniqueKey);
        ImSetGroupSessionOptionResPacket imSetSessionOptionResPacket = JsonParser.parseJsonObject(receivedData, ImSetGroupSessionOptionResPacket.class);
        if (listener != null && imSetSessionOptionResPacket != null) {
            listener.onSuccess(imSetSessionOptionResPacket);
        }
    }

    private void onPullSessionProperty(String receivedData, String uniqueKey) {
        ImPacketListener<ImPullSessionPropertyResPacket.PullSingleChatPropertyResBean> listener = mListenerQueue.pop(uniqueKey);
        ImPullSessionPropertyResPacket sessionProperty = JsonParser.parseJsonObject(receivedData, ImPullSessionPropertyResPacket.class);
        if (listener != null && sessionProperty != null && sessionProperty.getPullSingleChatPropertyRes() != null) {
            listener.onSuccess(sessionProperty.getPullSingleChatPropertyRes());
        }
    }

    private void onPullGroupSessionProperty(String receivedData, String uniqueKey) {
        ImPacketListener<ImPullGroupSessionPropertyResPacket.PullGroupChatPropertyResBean> listener = mListenerQueue.pop(uniqueKey);
        ImPullGroupSessionPropertyResPacket sessionProperty = JsonParser.parseJsonObject(receivedData, ImPullGroupSessionPropertyResPacket.class);
        if (listener != null && sessionProperty != null && sessionProperty.getPullGroupChatPropertyRes() != null) {
            listener.onSuccess(sessionProperty.getPullGroupChatPropertyRes());
        }
    }

    private void onMsgReadAckResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImMsgReadAckResPacket.IMMsgDataReadAckResponseBean> listener = mListenerQueue.pop(uniqueKey);
        ImMsgReadAckResPacket readAckResPacket = JsonParser.parseJsonObject(receivedData, ImMsgReadAckResPacket.class);
        if (listener != null && readAckResPacket != null && readAckResPacket.getIMMsgDataReadAckResponse() != null) {
            listener.onSuccess(readAckResPacket.getIMMsgDataReadAckResponse());
        }
    }

    private void onGroupMsgReadAckResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImGroupMsgDataReadAckResponsePacket.IMGroupMsgDataReadAckResponseBean> listener = mListenerQueue.pop(uniqueKey);
        ImGroupMsgDataReadAckResponsePacket readAckResPacket = JsonParser.parseJsonObject(receivedData, ImGroupMsgDataReadAckResponsePacket.class);
        if (listener != null && readAckResPacket != null && readAckResPacket.getIMGroupMsgDataReadAckResponse() != null) {
            listener.onSuccess(readAckResPacket.getIMGroupMsgDataReadAckResponse());
        }
    }

    //消息已读通知
    private void onMsgReadNotify(String receivedData) {
        ImMsgReadNotify imMsgReadNotify = JsonParser.parseJsonObject(receivedData, ImMsgReadNotify.class);
        if (imWsListener != null && imMsgReadNotify != null){
            imWsListener.onMsgReadNotify(imMsgReadNotify);
        }
    }

    /**
     * 邀请群成员的回复
     * */
    private void onInviteGroupMemberResponse(String receivedData, String uniqueKey) {
        ImPacketListener<InviteGroupMemberResPacket.InviteGroupMemberResBean> listener = mListenerQueue.pop(uniqueKey);
        InviteGroupMemberResPacket groupMemberResPacket = JsonParser.parseJsonObject(receivedData, InviteGroupMemberResPacket.class);
        if (listener != null && groupMemberResPacket != null && groupMemberResPacket.getInviteGroupMemberRes() != null){
            listener.onSuccess(groupMemberResPacket.getInviteGroupMemberRes());
        }
    }

    /**
     * 返回群成员列表
     * */
    private void onGetGroupMemberListResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImGetGroupMemberListResPacket.GetGroupMemberListResBean> listener = mListenerQueue.pop(uniqueKey);
        ImGetGroupMemberListResPacket imGetGroupMemberListResPacket = JsonParser.parseJsonObject(receivedData, ImGetGroupMemberListResPacket.class);
        if (listener != null && imGetGroupMemberListResPacket.getGetGroupMemberListRes() != null){
            listener.onSuccess(imGetGroupMemberListResPacket.getGetGroupMemberListRes());
        }
    }

    private void onModifyGroupInfoResponse(String receivedData, String uniqueKey) {
        ImModifyGroupInfoResPacket groupInfoResPacket = JsonParser.parseJsonObject(receivedData, ImModifyGroupInfoResPacket.class);
        ImModifyGroupInfoResPacket.ModifyGroupInfoRes groupInfoRes = groupInfoResPacket.getModifyGroupInfoRes();
        ImPacketListener<ImModifyGroupInfoResPacket.ModifyGroupInfoRes> listener = mListenerQueue.pop(uniqueKey);
        if (listener != null && groupInfoRes != null){
            listener.onSuccess(groupInfoRes);
        }
    }

    private void onModifyGroupInfoNotify(String receivedData) {
        ImModifyGroupInfoNotify modifyGroupInfoNotify = JsonParser.parseJsonObject(receivedData, ImModifyGroupInfoNotify.class);
        ImModifyGroupInfoNotify.ModifyGroupInfoNotify groupInfoNotify = modifyGroupInfoNotify.getModifyGroupInfoNotify();
        if (imWsListener != null && groupInfoNotify != null){
            imWsListener.onModifyGroupInfoNotify(groupInfoNotify);
        }
    }

    private void onRemoveGroupMember(String receivedData, String uniqueKey) {
        ImPacketListener<ImRemoveGroupMemberResPacket.RemoveGroupMemberResBean> listener = mListenerQueue.pop(uniqueKey);
        ImRemoveGroupMemberResPacket imRemoveGroupMemberResPacket = JsonParser.parseJsonObject(receivedData, ImRemoveGroupMemberResPacket.class);
        ImRemoveGroupMemberResPacket.RemoveGroupMemberResBean groupMemberRes = imRemoveGroupMemberResPacket.getRemoveGroupMemberRes();
        if (listener != null && groupMemberRes != null){
            listener.onSuccess(groupMemberRes);
        }
    }

    private void onRevokeMsgResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImRevokeMsgResPacket.RevokeMsgResBean> listener = mListenerQueue.pop(uniqueKey);
        ImRevokeMsgResPacket resPacket = JsonParser.parseJsonObject(receivedData, ImRevokeMsgResPacket.class);
        if (listener != null && resPacket != null && resPacket.getRevokeMsgRes() != null) {
            listener.onSuccess(resPacket.getRevokeMsgRes());
        }
    }

    private void onRevokeGrpMsgResponse(String receivedData, String uniqueKey) {
        ImPacketListener<ImRevokeGroupMsgResPacket.RevokeGroupMsgResBean> listener = mListenerQueue.pop(uniqueKey);
        ImRevokeGroupMsgResPacket resPacket = JsonParser.parseJsonObject(receivedData, ImRevokeGroupMsgResPacket.class);
        if (listener != null && resPacket != null && resPacket.getRevokeGroupMsgRes() != null) {
            listener.onSuccess(resPacket.getRevokeGroupMsgRes());
        }
    }

    /**
     * websocket错误
     * 1002：协议错误，1004：内部错误，1006：请求被拒绝"
     */
    private void onWsError(String receivedData) {
        ImErrorPacket imErrorPacket = JsonParser.parseJsonObject(receivedData, ImErrorPacket.class);
        ImErrorPacket.ErrorBean error = imErrorPacket.getError();
        int resultCode = error.getResultCode();
        if (resultCode == ImErrorCode.INTERNAL_ERROR || resultCode == ImErrorCode.UNKNOWN_EXCEPTION) {
            shouldNotReconnect = true;
            //todo 是否是release版本，暂时写死，需要修改
            if (true){
                Toast.makeText(getmContext(), "IM服务器错误: imid: " + error.getImid() + " cid:"+ error.getCid() +
                        " resultCode: " + resultCode + "reason: " + error.getReason(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getmContext(), error.getResultCode() + "：" + error.getReason(), Toast.LENGTH_LONG).show();
            }
        }
        Log.e(TAG, "IM websocket error#imId:" + error.getImid() + " cid:"+ error.getCid() + " resultcode:" + resultCode + " reason: " + error.getReason());
    }

    public interface ImWsListener{

        void onReceiveSingleMsgNotify(ImReceivedSingleMsgPacket.IMMsgDataDeliverBean imMsgDataDeliver);

        void onSessionOperationNotify(ImSetSessionOptionNotifyPacket optionNotifyPacket);

        void onGroupSessionOperationNotify(ImSetGroupSessionOptionNotifyPacket optionNotifyPacket);

        void onKickOutNotify(ImKickUserNotifyPacket.IMKickUserBean imKickUser);

        void onSyncMsgNotify(ImSyncMsgPacket.IMMsgDataTerminalSyncBean syncMsg);

        void onInviteGroupMemberNotify(ImInviteGroupMemberNotifyPacket.InviteGroupMemberNotifyBean syncMsg);

        void onLoginReply(ImLoginResPacket.UserLoginResBean userLoginRes);

        void onMsgReadNotify(ImMsgReadNotify imMsgReadNotify);

        void onReceiveGroupMsgNotify(ImReceivedGroupMsgPacket.IMGroupMsgDataDeliverBean groupMsgDataDeliver);

        void onModifyGroupInfoNotify(ImModifyGroupInfoNotify.ModifyGroupInfoNotify modifyGroupInfoNotify);

        void removeGroupMember(long mSessionId, long[] removedIdList, ImPacketListener listener);

        void onSyncGroupMsgNotify(ImGroupSyncMsgPacket.IMGroupMsgDataTerminalSyncBean imGroupMsgDataTerminalSync);

        void onRemoveGroupMemberNotify(ImRemoveGroupMemberNotifyPacket.RemoveGroupMemberNotifyBean removeGroupMemberNotify);

        void onGroupMsgDataReadNotify(ImGroupMsgDataReadNotifyPacket.IMGroupMsgDataReadNotifyBean imGroupMsgDataReadNotify);

        void onRevokeMsgNotify(ImRevokeMsgNotifyPacket.RevokeMsgNotifyBean notifyPacket);

        void onRevokeGroupMsgNotify(ImRevokeGroupMsgNotifyPacket.RevokeGroupMsgNotifyBean revokeGroupMsgNotify);

        void onTransferGroupLeaderNotify(ImTransferGroupIdentifyNotify.TransferGroupIdentifyNotify transferGroupIdentifyNotify);
    }
}
