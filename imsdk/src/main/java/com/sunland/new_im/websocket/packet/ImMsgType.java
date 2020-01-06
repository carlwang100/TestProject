package com.sunland.new_im.websocket.packet;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImMsgType {
    // 1：文本，2：音频，3：图片，4：视频，5：文件, 6：用户自定义消息JSON（详见企业APP消息迁移 单聊，用户自定义JSON消息说明），8：客户自定义类型
    public static final int EMPTY = 0;// 用于拉取的group session没有消息的情况
    public static final int TEXT = 1;
    public static final int AUDIO = 2;
    public static final int IMG = 3;
    public static final int VIDEO = 4;
    public static final int FILE = 5;
    public static final int NOTIFICATION = 6;
    public static final int SYSTEM = 7;
    public static final int CUSTOM = 8; // 2019/2/26 V1.3.2 消息催办添加
}
