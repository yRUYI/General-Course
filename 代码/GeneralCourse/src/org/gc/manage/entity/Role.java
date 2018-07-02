package org.gc.manage.entity;

@Table(name = "role", caption = "角色")
public class Role {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "name", caption = "角色名", nullable = true)
    private String name;
    @Column(isId = false, name = "createTime", caption = "创建时间", nullable = true)
    private String createTime;

    public Role() {
    }

    public Role(String id, String name, String createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
