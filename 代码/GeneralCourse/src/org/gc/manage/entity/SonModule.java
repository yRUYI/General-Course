package org.gc.manage.entity;

@Table(name = "sonModule", caption = "子模块")
public class SonModule {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "name", caption = "模块名", nullable = true)
    private String name;
    @Column(isId = false, name = "status", caption = "状态", nullable = true)
    private Integer status;
    @Column(isId = false, name = "parentID", caption = "父模块ID", nullable = true)
    private String parentId;

    public SonModule() {
    }

    public SonModule(String id, String name, Integer status, String parentId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SonModule{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
