package com.sunland.new_im.entity;


/**
 * 传给IM手机列表的数据结构
 *
 * @author Fanbeibei
 */
public class MsgData {
    /**
     * 消息类型。配置在t_dict表中
     */
    private Integer type;

    /**
     * 消息名称(应用名称)
     */
    private String name;

    /**
     * logo地址
     */
    private String logoUrl;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;


    /**
     * 消息链接
     */
    private String link;

    /**
     * 接收消息的员工ID
     */
    private Long employeeId;

    /**
     * 消息的额外信息.格式为json，用于对特殊消息附加一些参数或其他额外信息。
     */
    private String extra;


    /**
     * 消息类型。
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 消息类型。
     *
     * @param type 消息类型。
     */
    public void setType(Integer type) {
        this.type = type;
    }


    /**
     * 消息名称(应用名称)
     *
     * @return name 消息名称(应用名称)
     */
    public String getName() {
        return name;
    }

    /**
     * 消息名称(应用名称)
     *
     * @param name 消息名称(应用名称)
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * logo地址
     *
     * @return logo_url logo地址
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * logo地址
     *
     * @param logoUrl logo地址
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * 消息标题
     *
     * @return title 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 消息标题
     *
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 消息内容
     *
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容
     *
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    /**
     * 消息链接
     *
     * @return link 消息链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 消息链接
     *
     * @param link 消息链接
     */
    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    /**
     * 接收消息的员工ID
     *
     * @return employee_id 接收消息的员工ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * 接收消息的员工ID
     *
     * @param employeeId 接收消息的员工ID
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    /**
     * 消息的额外信息.格式为json，用于对特殊消息附加一些参数或其他额外信息。
     *
     * @return extra 消息的额外信息.格式为json，用于对特殊消息附加一些参数或其他额外信息。
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 消息的额外信息.格式为json，用于对特殊消息附加一些参数或其他额外信息。
     *
     * @param extra 消息的额外信息.格式为json，用于对特殊消息附加一些参数或其他额外信息。
     */
    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}