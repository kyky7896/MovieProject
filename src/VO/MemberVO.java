package VO;

public class MemberVO {
    private String id;
    private int pwd;
    private boolean type;

    public MemberVO(String id, int pwd, boolean type) {
        this.id = id;
        this.pwd = pwd;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id='" + id + '\'' +
                ", pwd=" + pwd +
                ", type=" + type +
                '}';
    }

}
