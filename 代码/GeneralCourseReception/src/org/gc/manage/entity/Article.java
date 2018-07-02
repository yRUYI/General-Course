package org.gc.manage.entity;

@Table(name = "article", caption = "文章")
public class Article {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "title", caption = "文章标题", nullable = true)
    private String title;
    @Column(isId = false, name = "author", caption = "作者", nullable = true)
    private String author;
    @Column(isId = false, name = "columnID", caption = "栏目ID", nullable = true)
    private String columnId;
    @Column(isId = false, name = "content", caption = "文章内容", nullable = true, length = 255)
    private String content;
    @Column(isId = false, name = "createTime", caption = "创建时间", nullable = true)
    private String createTime;
    @Column(isId = false, name = "whetherTop", caption = "是否置顶", nullable = true, length = 11)
    private Integer whetherTop;
    @Column(isId = false, name = "status", caption = "文章状态", nullable = true, length = 11)
    private Integer status;

    public Article() {
    }

    public Article(String id, String title, String author, String columnId, String content, String createTime, Integer whetherTop, Integer status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.columnId = columnId;
        this.content = content;
        this.createTime = createTime;
        this.whetherTop = whetherTop;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
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

    public Integer getWhetherTop() {
        return whetherTop;
    }

    public void setWhetherTop(Integer whetherTop) {
        this.whetherTop = whetherTop;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", columnId='" + columnId + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", whetherTop=" + whetherTop +
                ", status=" + status +
                '}';
    }
}
