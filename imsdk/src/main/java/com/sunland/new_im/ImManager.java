package com.sunland.new_im;

import android.content.Context;
import android.support.annotation.Nullable;

import com.sunland.new_im.websocket.ImPacketListener;
import com.sunland.new_im.websocket.ImWsChannel;
import com.sunland.new_im.websocket.WebSocketClient;
import com.sunland.new_im.websocket.packet.ImGroupMsgDataReadAckResponsePacket;
import com.sunland.new_im.websocket.packet.ImGroupMsgDataReadNotifyPacket;
import com.sunland.new_im.websocket.packet.ImGroupSyncMsgPacket;
import com.sunland.new_im.websocket.packet.ImInviteGroupMemberNotifyPacket;
import com.sunland.new_im.websocket.packet.ImKickUserNotifyPacket;
import com.sunland.new_im.websocket.packet.ImLoginResPacket;
import com.sunland.new_im.websocket.packet.ImModifyGroupInfoNotify;
import com.sunland.new_im.websocket.packet.ImMsgReadAckResPacket;
import com.sunland.new_im.websocket.packet.ImMsgReadNotify;
import com.sunland.new_im.websocket.packet.ImReceivedGroupMsgPacket;
import com.sunland.new_im.websocket.packet.ImReceivedSingleMsgPacket;
import com.sunland.new_im.websocket.packet.ImRemoveGroupMemberNotifyPacket;
import com.sunland.new_im.websocket.packet.ImRevokeGroupMsgNotifyPacket;
import com.sunland.new_im.websocket.packet.ImRevokeMsgNotifyPacket;
import com.sunland.new_im.websocket.packet.ImSetGroupSessionOptionNotifyPacket;
import com.sunland.new_im.websocket.packet.ImSetSessionOptionNotifyPacket;
import com.sunland.new_im.websocket.packet.ImSyncMsgPacket;
import com.sunland.new_im.websocket.packet.ImTransferGroupIdentifyNotify;
import com.sunland.new_im.websocket.packet.ImTransferGroupLeaderResPacket;
import com.sunland.new_im.utils.AccountUtils;
import com.sunland.new_im.websocket.utils.NetworkMonitor;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature: 新IM WebSocket长连接管理类：封装WebSocket的业务接口，提供回调
 */
public class ImManager implements ImWsChannel.ImWsListener {
    private static final ImManager mInstance = new ImManager();
    private long imId;

    public static ImManager getInstance(){
        return mInstance;
    }
    private ImWsChannel imWsChannel;
    private Context mContext;
    private String mDomain;
    private LinkedList<WebSocketClient.WsClientListener> mListenerList = new LinkedList<>();

    public void init(Context context, long imId, String wsDomain){
        this.imId = imId;
        mContext = context;
        mDomain = wsDomain;
        NetworkMonitor.instance().register(context);

        if (imWsChannel != null) {
            imWsChannel.release();
            imWsChannel = null;
        }
        initImWsChannel();
    }

    private void initImWsChannel() {
        if (imId == 0){
            imId = AccountUtils.getEhrIMId(mContext);
        }
        synchronized (this) {
            imWsChannel = new ImWsChannel(this, mContext, mDomain, imId);
            imWsChannel.connect();

            for (WebSocketClient.WsClientListener wsClientListener : mListenerList) {
                imWsChannel.registerWsClientListener(wsClientListener);
            }
            mListenerList.clear();
        }
    }

    private void initImWsChannel(Context context, String wsDomain ) {
        if (imId == 0){
            imId = AccountUtils.getEhrIMId(context);
        }
        synchronized (this) {
            imWsChannel = new ImWsChannel(this, context, wsDomain, imId);
            imWsChannel.connect();

            for (WebSocketClient.WsClientListener wsClientListener : mListenerList) {
                imWsChannel.registerWsClientListener(wsClientListener);
            }
            mListenerList.clear();
        }
    }

    public synchronized void registerWsClientListener(WebSocketClient.WsClientListener listener) {
        if (imWsChannel != null) {
            imWsChannel.registerWsClientListener(listener);
        } else {
            mListenerList.add(listener);
        }
    }

    public synchronized void unregisterWsClientListener(WebSocketClient.WsClientListener listener) {
        if (imWsChannel != null) {
            imWsChannel.unregisterWsClientListener(listener);
        }
        mListenerList.remove(listener);
    }

    /**
     * 检查 im webscocket是否已连接
     * @return true: 已连接，false：未连接
     * */
    public boolean checkImConnection() {
        //先检查imWsChannel是否为空，低概率出现imWsChannel为null，无法复现
        if (imWsChannel == null){
            initImWsChannel();
            return false;
        }

        return imWsChannel.checkImConnection();
    }

    public boolean checkImConnectionFromDeg(Context context, String domain) {
        //先检查imWsChannel是否为空，低概率出现imWsChannel为null，无法复现
        if (imWsChannel == null){
            initImWsChannel(context, domain);
            return false;
        }

        return imWsChannel.checkImConnection();
    }

    public void release(){
        if (mContext != null){
            NetworkMonitor.instance().unRegister(mContext);
        }
        if (imWsChannel != null) {
            imWsChannel.release();
            imWsChannel = null;
        }
    }



    /************WebSocket发送消息***************/

    public void sendSingleMsg(long receiverImid, String content, int imMsgType, String uniqueKey, String fromUserName, String fromUserImageUrl, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed(uniqueKey);
            return;
        }
        imWsChannel.sendSingleMsg(receiverImid, content, imMsgType, uniqueKey, fromUserName, fromUserImageUrl, listener);
    }

    public void sendGroupMsg(long receiverImid, String content, int imMsgType, String uniqueKey, String fromUserName,
                             String fromUserImageUrl, String sessionName, String sessionImgUrl, ImPacketListener listener, int groupAtType, String groupAtMember) {
        if (!checkImConnection()){
            listener.onFailed(uniqueKey);
            return;
        }
        imWsChannel.sendGroupMsg(receiverImid, content, imMsgType, uniqueKey, fromUserName, fromUserImageUrl, sessionName, sessionImgUrl, listener, groupAtType, groupAtMember);
    }

    public void pullMsgRecord(int sessionType, long sessionid, int msgid, int orient, int pullNum, int needUserInfo, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.pullMsgRecord(sessionType, sessionid, msgid, orient, pullNum, needUserInfo, listener);
    }

    public void pullRecentSessionList(int num, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.pullRecentSessionList(num, listener);
    }

    //会话列表操作
    public void setSessionOption(long sessionId, int action, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.setSessionOption(sessionId, action, listener);
    }

    //会话列表操作
    public void setGroupSessionOption(long sessionId, int action, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.setGroupSessionOption(sessionId, action, listener);
    }

    //拉取会话属性
    public void pullSessionProperty(long sessionId, int sessionType ,ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.pullSessionProperty(sessionId, sessionType, listener);
    }

    /**
     * 用于消息接收方 发送消息已读回执给IM服务器
     * @param sessionId 单聊：消息发送方imid， 群聊：群组id
     * @param recvMsgId 目前用于文件已读标识：消息接收方 当前已接收的文件消息id
     * @param latestReadMsgId  消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
     * @param listener  回调
     * */
    public void sendMsgReadAck(long sessionId, int recvMsgId, int latestReadMsgId, ImPacketListener<ImMsgReadAckResPacket.IMMsgDataReadAckResponseBean> listener){
        if (imWsChannel != null) {
            imWsChannel.sendMsgReadAck(sessionId, recvMsgId, latestReadMsgId, listener);
        }else {
            listener.onFailed("");
        }
    }

    /**
     * 用于消息接收方 发送消息已读回执给IM服务器
     * @param sessionId 单聊：消息发送方imid， 群聊：群组id
     * @param recvMsgId 目前用于文件已读标识：消息接收方 当前已接收的文件消息id
     * @param latestReadMsgId  消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
     * @param listener  回调
     * */
    public void sendGroupMsgReadAck(long sessionId, int recvMsgId, int latestReadMsgId, ImPacketListener<ImGroupMsgDataReadAckResponsePacket.IMGroupMsgDataReadAckResponseBean> listener){
        if (imWsChannel != null) {
            imWsChannel.sendGroupMsgReadAck(sessionId, recvMsgId, latestReadMsgId, listener);
        }else {
            listener.onFailed("");
        }
    }

    //用户DeviceToken更新 [仅用于手机app推送]
    public void updateDeviceToken(boolean isXiaomi, String deviceToken, ImPacketListener listener){
        if (imWsChannel != null) {
            imWsChannel.updateUserDeviceToken(isXiaomi, deviceToken, listener);
        }else {
            listener.onFailed("");
        }
    }

    public void logout(ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.logoutWebSocket(listener);
    }

    /**
     * 创建群或邀请人入群
     * @param groupId :  说明:新建群设为0; 为已有群添加成员设置为已有群的groupId
     * ResultCode: 4001表示没有邀请权限，4002表示人数超过群成员上限
     * @param groupName : 说明:群名称
     * @param groupImg : 说明:群头像url
     * @param inviteeIdList : 说明:被邀请者imid列表
     * @param groupType   **/
    public void inviteGroupMember(long groupId, String groupName, String groupImg, List<Long> inviteeIdList, int groupType, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.inviteGroupMember(groupId, -1, groupName, groupImg, inviteeIdList, groupType, listener, 1 /*普通邀请群成员不填或填1， 二维码进群方式填2，二维码进群每次只能加一人*/);
    }

    public void inviteGroupMemberByQrCode(long groupId, long inviterId, String groupName, String groupImg, List<Long> inviteeIdList, int groupType, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.inviteGroupMember(groupId, inviterId, groupName, groupImg, inviteeIdList, groupType, listener, 2 /*普通邀请群成员不填或填1， 二维码进群方式填2，二维码进群每次只能加一人*/);
    }

    /**
     * 获取群成员列表
     * @param memberNum 0或大于群组人数时，拉取群全部人员
     * */
    public void fetchGroupMemberList(long mSessionId, int memberNum, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.fetchGroupMemberList( mSessionId,  memberNum,  listener);
    }

    /**
     * 修改群信息
     * @param modifyInfo 1:修改群名
     * */
    public void modifyGroupInfo(long groupId, Map<Integer, String> modifyInfo, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.modifyGroupInfo(groupId, modifyInfo, listener);
    }

    /**
     * 移除群成员
     * */
    @Override
    public void removeGroupMember(long groupId, long[] removedIdList, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.removeGroupMember(groupId, removedIdList, listener);
    }

    public void transferGroupLeader(long mSessionId, long toUserId, ImPacketListener<ImTransferGroupLeaderResPacket.ImTransferGroupLeaderRes> listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.transferGroupLeader(mSessionId, toUserId, listener);
    }

    public void setMessageNoDisturb(boolean isNoDisturb, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.setMessageNoDisturb(isNoDisturb, listener);
    }

    public void queryMessageNoDisturb(ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.queryMessageNoDisturb(listener);
    }

    /**
     *
     * @param listener
     * @param singleChatList 单聊会话id列表（1、传输列表时，会拉取列表内的人员会话信息（如果单纯想拉取头像和名字，此信息有些许冗余），2、列表内仅包含一个0，表示获取跟imid聊过的所有单聊会话）
     * @param groupChatList 群聊会话id列表（1、传输列表时，会拉取列表内imid当前所在的群组信息，2、列表内仅包含一个0，表示获取imid当前所在的所有群组信息）
     * @param getAtMsg False， 不获取群聊中的未读@消息id列表； True获取群聊中的未读@消息id列表。默认不填为False
     */
    public void querySessionBasicInfo(ImPacketListener listener, @Nullable List<Long> singleChatList, @Nullable List<Long> groupChatList, boolean getAtMsg) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        imWsChannel.querySessionBasicInfo(listener, singleChatList, groupChatList, getAtMsg);
    }

    // 查找聊天记录
    public void searchMsg(String keyword, ImPacketListener listener){
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }

        if (imWsChannel != null) {
            imWsChannel.searchMsg(keyword, listener);
        }
    }

    public void revokeMsg(long sessionId, int msgId, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        if (imWsChannel != null) {
            imWsChannel.revokeMsg(sessionId, msgId, listener);
        }
    }

    public void revokeGrpMsg(long sessionId, int msgId, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        if (imWsChannel != null) {
            imWsChannel.revokeGrpMsg(sessionId, msgId, listener);
        }
    }

    /**
     * CID7053 - 拉取群管理人员信息
     * @param sessionId
     * @param listener
     */
    public void queryGroupManager(long sessionId, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        if (imWsChannel != null) {
            List<Long> sessionIdList = new ArrayList<>(1);
            sessionIdList.add(sessionId);
            imWsChannel.queryGroupManager(sessionIdList, listener);
        }
    }

    /**
     * CID4019 - 拉取撤回消息记录
     * @param clientLatestAccessTime
     * @param listener
     */
    public void pullRevokeMsgInfoList(long clientLatestAccessTime, ImPacketListener listener) {
        if (!checkImConnection()){
            listener.onFailed("");
            return;
        }
        if (imWsChannel != null) {
            imWsChannel.pullRevokeMsgInfoList(clientLatestAccessTime, listener);
        }
    }

    /************WebSocket收到消息***************/
    @Override
    public void onReceiveSingleMsgNotify(ImReceivedSingleMsgPacket.IMMsgDataDeliverBean imMsgDataDeliver) {
        EventBus.getDefault().post(imMsgDataDeliver);
    }

    @Override
    public void onReceiveGroupMsgNotify(ImReceivedGroupMsgPacket.IMGroupMsgDataDeliverBean groupMsgDataDeliver) {
        EventBus.getDefault().post(groupMsgDataDeliver);
    }

    @Override
    public void onModifyGroupInfoNotify(ImModifyGroupInfoNotify.ModifyGroupInfoNotify modifyGroupInfoNotify) {
        EventBus.getDefault().post(modifyGroupInfoNotify);
    }

    @Override
    public void onSessionOperationNotify(ImSetSessionOptionNotifyPacket optionNotifyPacket) {
        EventBus.getDefault().post(optionNotifyPacket);
    }

    @Override
    public void onGroupSessionOperationNotify(ImSetGroupSessionOptionNotifyPacket optionNotifyPacket) {
        EventBus.getDefault().post(optionNotifyPacket);
    }

    @Override
    public void onKickOutNotify(ImKickUserNotifyPacket.IMKickUserBean imKickUser) {
        EventBus.getDefault().post(imKickUser);
    }

    @Override
    public void onSyncMsgNotify(ImSyncMsgPacket.IMMsgDataTerminalSyncBean syncMsg) {
        EventBus.getDefault().post(syncMsg);
    }

    @Override
    public void onInviteGroupMemberNotify(ImInviteGroupMemberNotifyPacket.InviteGroupMemberNotifyBean syncMsg) {
        EventBus.getDefault().post(syncMsg);
    }

    @Override
    public void onSyncGroupMsgNotify(ImGroupSyncMsgPacket.IMGroupMsgDataTerminalSyncBean imGroupMsgDataTerminalSync) {
        EventBus.getDefault().post(imGroupMsgDataTerminalSync);
    }

    @Override
    public void onRemoveGroupMemberNotify(ImRemoveGroupMemberNotifyPacket.RemoveGroupMemberNotifyBean removeGroupMemberNotify) {
        EventBus.getDefault().post(removeGroupMemberNotify);
    }

    @Override
    public void onGroupMsgDataReadNotify(ImGroupMsgDataReadNotifyPacket.IMGroupMsgDataReadNotifyBean imGroupMsgDataReadNotify) {
        EventBus.getDefault().post(imGroupMsgDataReadNotify);
    }

    @Override
    public void onRevokeMsgNotify(ImRevokeMsgNotifyPacket.RevokeMsgNotifyBean notifyPacket) {
        EventBus.getDefault().post(notifyPacket);
    }

    @Override
    public void onRevokeGroupMsgNotify(ImRevokeGroupMsgNotifyPacket.RevokeGroupMsgNotifyBean notifyPacket) {
        EventBus.getDefault().post(notifyPacket);
    }

    @Override
    public void onTransferGroupLeaderNotify(ImTransferGroupIdentifyNotify.TransferGroupIdentifyNotify transferGroupIdentifyNotify) {
        EventBus.getDefault().post(transferGroupIdentifyNotify);
    }

    @Override
    public void onLoginReply(ImLoginResPacket.UserLoginResBean userLoginRes) {
        EventBus.getDefault().post(userLoginRes);
    }

    @Override
    public void onMsgReadNotify(ImMsgReadNotify imMsgReadNotify) {
        EventBus.getDefault().post(imMsgReadNotify);
    }

}
