package VO;

import java.util.Random;

public class ReserVO {
    private int num;
    private int m_num;
    private String id;
    private String seatnumber;
    private String time;

    public ReserVO(int num, int m_num, String id, String seatnumber, String time) {
        this.num =num;
        this.m_num = m_num;
        this.id = id;
        this.seatnumber = seatnumber; //어디좌석인지
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getM_num() {
        return m_num;
    }

    public void setM_num(int m_num) {
        this.m_num = m_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReserVO{" +
                "num=" + num +
                ", m_num=" + m_num +
                ", id='" + id + '\'' +
                ", seatnumber='" + seatnumber + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
