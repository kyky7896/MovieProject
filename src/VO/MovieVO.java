package VO;

public class MovieVO {
    private int num;
    private int ranks;
    private String movieName;
    private String time;
    private int audience;

    public MovieVO(int num, int ranks, String movieName, String time, int audience) {
        this.num = num;
        this.ranks = ranks;
        this.movieName = movieName;
        this.time = time;
        this.audience = audience;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public String getMovie_name() {
        return movieName;
    }

    public void setMovie_name(String movie_name) {
        this.movieName = movie_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "MovieVO{" +
                "num=" + num +
                ", ranks=" + ranks +
                ", movie_name='" + movieName + '\'' +
                ", time='" + time + '\'' +
                ", audience=" + audience +
                '}';
    }
}
