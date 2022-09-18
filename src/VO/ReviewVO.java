package VO;

public class ReviewVO {
    private int num;
    private String id;
    private String movie_name;
    private String title;
    private String content;

    public ReviewVO(int num, String id, String movie_name, String title, String content) {
        this.num = num;
        this.id = id;
        this.movie_name = movie_name;
        this.title = title;
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReviewVO{" +
                "num=" + num +
                ", id='" + id + '\'' +
                ", movie_name='" + movie_name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
