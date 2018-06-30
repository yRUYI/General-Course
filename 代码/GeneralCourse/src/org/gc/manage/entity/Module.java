package org.gc.manage.entity;

@Table(name = "module", caption = "模板")
public class Module {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "name", caption = "模板名", nullable = true)
    private String name;
    @Column(isId = false, name = "status", caption = "状态", nullable = true, length = 11)
    private Integer status;

    public Module() {
    }

    public Module(String id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    @Override
    public String toString() {
        return "Module{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
