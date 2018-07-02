package org.gc.manage.entity;

@Table(name = "file", caption = "教育资源")
public class File {
    @Column(isId = true, name = "id", caption = "唯一标识符", nullable = false)
    private String id;
    @Column(isId = false, name = "articleID", caption = "文章ID", nullable = true)
    private String articleId;
    @Column(isId = false, name = "path", caption = "存放路径", nullable = true, length = 255)
    private String path;

    public File() {
    }

    public File(String id, String articleId, String path) {
        this.id = id;
        this.articleId = articleId;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
