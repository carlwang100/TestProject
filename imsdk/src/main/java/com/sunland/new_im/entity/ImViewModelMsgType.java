package com.sunland.new_im.entity;

import com.sunland.new_im.websocket.packet.ImMsgType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kai on 2018/8/16
 * Email：kaihu1989@gmail.com
 * Feature:
 * 消息类型：用于recyclerview绘制不同类型的item，区分接收和发送的
 * 接收到的消息类型  1：文本，2：音频，3：图片，4：视频，5：文件"
 * 发送的消息类型  10001：文本，10002：音频，10003：图片，10004：视频，10005：文件"
 * */
public class ImViewModelMsgType {

    public static final int TIME_LINE = 0;
    public static final int RECEIVED_SYS_MSG = ImMsgType.SYSTEM;    // 系统消息显示在正中间

    public static final int RECEIVED_TEXT = ImMsgType.TEXT;
    public static final int RECEIVED_AUDIO = ImMsgType.AUDIO;
    public static final int RECEIVED_IMG = ImMsgType.IMG;
    public static final int RECEIVED_VIDEO = ImMsgType.VIDEO;
    public static final int RECEIVED_FILE = ImMsgType.FILE;
    public static final int RECEIVED_CUSTOM_MSG = ImMsgType.CUSTOM;

    public static final int SEND_TEXT = 10000 + ImMsgType.TEXT;
    public static final int SEND_AUDIO = 10000 + ImMsgType.AUDIO;
    public static final int SEND_IMG = 10000 + ImMsgType.IMG;
    public static final int SEND_VIDEO = 10000 + ImMsgType.VIDEO;
    public static final int SEND_FILE = 10000 + ImMsgType.FILE;
    public static final int SEND_CUSTOM_MSG = 10000 + ImMsgType.CUSTOM; // 适配网页端发送的自定义消息类型

    public static final int REVOKED_MSG = 100; // 撤回的消息

    public static final int UNKNOWN_SENT_TYPE = -1;  //发送的未知类型，可能是web发出手机端同步到的消息

    public static final int UNKNOWN_RECEIVED_TYPE = -2;  //接收的未知类型

    //当前支持的消息类型，后续加入语音、视频和其他类型
    private static final List<Integer> SUPPORT_TYPES = Arrays.asList(RECEIVED_TEXT, RECEIVED_IMG,
            RECEIVED_FILE, RECEIVED_SYS_MSG, REVOKED_MSG,RECEIVED_CUSTOM_MSG, SEND_CUSTOM_MSG, RECEIVED_AUDIO, SEND_AUDIO);

    public static int convert2ImMsgType(int viewMsgType) {
        if (viewMsgType > 10000) {
            return viewMsgType - 10000;
        } else {
            return viewMsgType;
        }
    }

    public static int convert2ViewItemType(int imMsgType, boolean isReceive) {
        if (imMsgType >  10000) {
            throw new IllegalArgumentException("wrong imMsgType, you passed in a viewMsgType");
        }

        if (!SUPPORT_TYPES.contains(imMsgType)) {
            return isReceive ? UNKNOWN_RECEIVED_TYPE : UNKNOWN_SENT_TYPE;
        }

        if (isReceive) {
            return imMsgType;
        } else {
            return 10000 + imMsgType;
        }
    }
}
