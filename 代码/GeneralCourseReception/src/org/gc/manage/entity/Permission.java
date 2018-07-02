package org.gc.manage.entity;

@Table(name = "permission", caption = "权限")
public class Permission {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "moduleID", caption = "模板ID", nullable = true)
    private String moduleId;
    @Column(isId = false, name = "createTime", caption = "创建时间", nullable = true)
    private String createTime;
    @Column(isId = false, name = "description", caption = "模板描述", nullable = true)
    private String description;

    public Permission() {
    }

    public Permission(String id, String moduleId, String createTime, String description) {
        this.id = id;
        this.moduleId = moduleId;
        this.createTime = createTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
