package com.sunland.new_im.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;

/**
 * Created by fengd on 2018/11/2.
 */
public class GroupMenber extends BaseDaoEnabled<GroupMenber, Long> {
//    {
//        "imid": "类型:Number 说明:成员imid",
//            "memberDegree": "类型:Number 说明:成员身份。1：普通成员，2：群主",
//            "enterTime": "类型:Number 说明:进群时间。单位毫秒",
//            "userName": "类型:String 说明:用户名字（真实昵称）",
//            "nickName": "类型:String 说明:用户在本群的群昵称",
//            "status": "类型:Number 说明:用户状态（1：在职，2：离职）",
//            "imageUrl": "类型:String 说明:用户头像url"
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    // 自动生成Id, 这里实际主键是groupId + imId
    @DatabaseField(generatedId = true)
    long id;

    @DatabaseField(uniqueCombo = true)
    long groupId;

    @DatabaseField(uniqueCombo = true)
    long imId;

    int memberDegree;

    long enterTime;

    String userName;

    String nickName;

    int status;

    String imageUrl;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getImId() {
        return imId;
    }

    public void setImId(long imId) {
        this.imId = imId;
    }

    public int getMemberDegree() {
        return memberDegree;
    }

    public void setMemberDegree(int memberDegree) {
        this.memberDegree = memberDegree;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
