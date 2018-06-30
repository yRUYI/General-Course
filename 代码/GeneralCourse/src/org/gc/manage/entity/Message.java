package org.gc.manage.entity;

import javax.persistence.criteria.CriteriaBuilder;

@Table(name = "message", caption = "留言")
public class Message {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "content", caption = "留言内容", nullable = true, length = 255)
    private String content;
    @Column(isId = false, name = "createTime", caption = "留言时间", nullable = true)
    private String createTime;
    @Column(isId = false, name = "reply", caption = "回复内容", nullable = true, length = 255)
    private String reply;
    @Column(isId = false, name = "employeeID", caption = "回复人员ID", nullable = true)
    private String employeeId;
    @Column(isId = false, name = "replyTime", caption = "回复时间", nullable = true)
    private String replyTime;
    @Column(isId = false, name = "status", caption = "回复状态", nullable = true, length = 11)
    private Integer status;
    @Column(isId = false, name = "moduleID", caption = "模板ID", nullable = true)
    private String moduleId;

    public Message() {
    }

    public Message(String id, String content, String createTime, String reply, String employeeId, String replyTime, Integer status, String moduleId) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.reply = reply;
        this.employeeId = employeeId;
        this.replyTime = replyTime;
        this.status = status;
        this.moduleId = moduleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", reply='" + reply + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", replyTime='" + replyTime + '\'' +
                ", status=" + status +
                ", moduleId='" + moduleId + '\'' +
                '}';
    }
}
