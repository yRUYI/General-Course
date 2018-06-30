package org.gc.manage.entity;

@Table(name = "columns", caption = "栏目")
public class Columns {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "name", caption = "栏目名称", nullable = true)
    private String name;
    @Column(isId = false, name = "level", caption = "栏目级别", nullable = true, length = 11)
    private String level;
    @Column(isId = false, name = "parent", caption = "父级栏目ID", nullable = true)
    private String parent;

    public Columns() {
    }

    public Columns(String id, String name, String level, String parent) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parent = parent;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Columns{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }
}
