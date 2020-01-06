package com.sunland.new_im.db;

import android.content.Context;
import android.util.Log;

import com.example.imsdk.R;
import com.google.gson.Gson;
import com.sunland.new_im.entity.MsgData;
import com.sunland.new_im.entity.MsgNotify;
import com.sunland.new_im.entity.SysMsg;
import com.sunland.new_im.utils.AccountUtils;
import com.sunland.new_im.websocket.packet.ImMsgType;
import com.sunland.new_im.websocket.utils.StringUtils;

/**
 * Created by fengd on 2018/10/19.
 */

public class SessionDisplayUtil {

    private static final String TAG = "SessionDisplayUtil";

    public static String getSessionDisplayContent(Context context, Message message) {
        int msgType = message.getMsgType();
        String msgContent = message.getMsgContent();
        return SessionDisplayUtil.getSessionDisplayContent(context, message.getMsgStatus(), msgType, msgContent);
    }

    public static String getSessionDisplayContent(Context context, Session session) {
        int msgType = session.getLatestMsgType();
        String msgContent = session.getLastestMsgContent();
        return SessionDisplayUtil.getSessionDisplayContent(context, session.getLatestMsgStatus(), msgType, msgContent);
    }

    public static String generateRevokedMsgDisplayText(Context context, String revokerName, long revokerId) {
        boolean revokeByMe = revokerId == AccountUtils.getEhrIMId(context);
        String tips;
        if (revokeByMe) {
            tips = context.getString(R.string.a_msg_revoke_by_you);
        } else {
            tips = context.getString(R.string.a_msg_revoke_by_someone, revokerName);
        }
        return tips;
    }

    public static String getSessionDisplayContent(Context context, TmpMessage tmpMessage) {
        int msgType = tmpMessage.getMsgType();
        String msgContent = tmpMessage.getMsgContent();
        return SessionDisplayUtil.getSessionDisplayContent(context, Message.Status.NORMAL, msgType, msgContent);
    }

    private static String getSessionDisplayContent(Context context, int msgStatus, int msgType, String originalContent) {
        String msgContent;

        if (msgStatus == Message.Status.REVOKED) {
            // 消息撤回操作，后台执行逻辑是直接改messageContent，
            // 客户端收到撤回成功请求应答后也直接修改messageContent;
            msgContent = originalContent;
        } else {
            switch (msgType) {
                case ImMsgType.EMPTY:
                    // 显示""，或者显示消息原内容，originalContent应该为""
                    msgContent = originalContent == null || originalContent.trim().length() == 0 ? "" : originalContent;
                    break;
                case ImMsgType.TEXT:
                    msgContent = originalContent;
                    break;
                case ImMsgType.AUDIO:
                    msgContent = context.getString(R.string.im_msg_audio);
                    break;
                case ImMsgType.FILE:
                    msgContent = context.getString(R.string.im_msg_file);
                    break;
                case ImMsgType.IMG:
                    msgContent = context.getString(R.string.im_msg_image);
                    break;
                //case ImMsgType.VIDEO:
                //    msgContent = contex.getString(R.string.im_msg_video);
                //    break;
                case ImMsgType.NOTIFICATION:
                    msgContent = context.getString(R.string.parse_msg_failed);
                    try {
                        MsgNotify notification = new Gson().fromJson(originalContent, MsgNotify.class);
                        MsgData msgData = new Gson().fromJson(notification.getMsgData(), MsgData.class);
                        msgContent = StringUtils.avoidNull(msgData.getContent());
                    } catch (Exception e) {
                        Log.e(TAG, "", e);
                    }
                    break;
                case ImMsgType.SYSTEM:
                    msgContent = formatSysMsg(originalContent);
                    break;
                case ImMsgType.CUSTOM:
                    msgContent = formatSysMsg(originalContent);
                    break;
                default:
                    msgContent = context.getString(R.string.unknown_msg_hint);
                    break;
            }
        }

        return msgContent;
    }

    private static String formatSysMsg(String originalContent) {
        String formatMsg = originalContent;/*contex.getString(R.string.unknown_msg_hint)*/;
        try {
            SysMsg sysMsg = new Gson().fromJson(originalContent, SysMsg.class);
            formatMsg = sysMsg.getSessionDisplay();
        } catch (Exception e) {
            Log.w(TAG, "", e);
        }
        return formatMsg;
    }
}
