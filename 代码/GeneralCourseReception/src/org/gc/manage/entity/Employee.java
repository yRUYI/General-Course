package org.gc.manage.entity;

@Table(name = "employee", caption = "人员")
public class Employee {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "name", caption = "姓名", nullable = true)
    private String name;
    @Column(isId = false, name = "sex", caption = "性别", nullable = true)
    private String sex;
    @Column(isId = false, name = "account", caption = "账号", nullable = true)
    private String account;
    @Column(isId = false, name = "password", caption = "密码", nullable = true)
    private String password;
    @Column(isId = false, name = "roleId", caption = "角色ID", nullable = true)
    private String roleId;

    public Employee() {
    }

    public Employee(String id, String name, String sex, String account, String password, String roleId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.account = account;
        this.password = password;
        this.roleId = roleId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
