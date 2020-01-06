package com.sunland.new_im.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;

/**
 * 表情收藏
 */
public class Emoji extends BaseDaoEnabled<Emoji, Long> {

    @DatabaseField(id = true, columnName = "collectId")
    private String collectId;   //服务端生成的表情id
    @DatabaseField(columnName = "emojiUrl")
    private String emojiUrl;    //表情图片url
    @DatabaseField(columnName = "emojiWidth")
    private int emojiWidth;
    @DatabaseField(columnName = "emojiHeight")
    private int emojiHeight;

    public Emoji() {
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public void setEmojiUrl(String emojiUrl) {
        this.emojiUrl = emojiUrl;
    }

    public Emoji(String collectId, String emojiUrl) {
        this.collectId = collectId;
        this.emojiUrl = emojiUrl;
    }

    public String getCollectId() {
        return collectId;
    }

    public String getEmojiUrl() {
        return emojiUrl;
    }

    public int getEmojiWidth() {
        return emojiWidth;
    }

    public void setEmojiWidth(int emojiWidth) {
        this.emojiWidth = emojiWidth;
    }

    public int getEmojiHeight() {
        return emojiHeight;
    }

    public void setEmojiHeight(int emojiHeight) {
        this.emojiHeight = emojiHeight;
    }
}
