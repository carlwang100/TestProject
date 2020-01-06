package com.sunland.new_im.websocket.packet.base;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImBaseRequestPacket {

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:1003","clientType":"类型:Number 说明:3（WEB）"}
     */
    private static final int TYPE_ANDROID = 1;
    private EntryBean Entry;

    public EntryBean getEntry() {
        return Entry;
    }

    public void setEntry(EntryBean Entry) {
        this.Entry = Entry;
    }

    public ImBaseRequestPacket(EntryBean entry) {
        this.Entry = entry;
    }

    public static class EntryBean {
        /**
         * imid : 类型:Number
         * cid : 类型:Number 说明:1003
         * clientType : 类型:Number 说明:3（WEB）
         */

        private long imid;
        private int cid;
        private String uniqueKey;
        private int clientType;

        public EntryBean(long imid, int cid) {
            this.imid = imid;
            this.cid = cid;
            this.clientType = TYPE_ANDROID;
        }

        public EntryBean(long imid, int cid, String uniqueKey) {
            this.imid = imid;
            this.cid = cid;
            this.uniqueKey = uniqueKey;
            this.clientType = TYPE_ANDROID;
        }

        public long getImid() {
            return imid;
        }

        public int getCid() {
            return cid;
        }

        public String getUniqueKey() {
            return uniqueKey;
        }

        public int getClientType() {
            return clientType;
        }
    }

}
