package Dao;

import Conn.MysqlConnect;
import Service.MemberService;
import VO.MovieVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {

    private final MysqlConnect dbconn;

    public MovieDAO() {
        dbconn = MysqlConnect.getInstance();
    } //객체를 받아오는 메서드

    //movie insert구문
    public void insert(MovieVO md) {
        Connection conn = dbconn.getConn();
        String sql = "insert into movie(ranks, movie_name,time,audience) values(?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, md.getRanks());
            pstmt.setString(2, md.getMovie_name());
            pstmt.setString(3, md.getTime());
            pstmt.setInt(4, md.getAudience());

            int cnt = pstmt.executeUpdate();// insert, update, delete 문 실행 함수
            if (cnt > 0) {
                System.out.println("insert success!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //insert

    //영화 list 받아옴
    public ArrayList<MovieVO> MovieSelectAll() {
        ArrayList<MovieVO> list = new ArrayList<MovieVO>();
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs;

        String sql = "select * from movie order by ranks";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new MovieVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    //num으로 movie객체의 time을 받아올 수 있게 함
    public String SelectTimeByNum(int num) {
        //1. db연결
        Connection conn = dbconn.getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;
        String time = null;
        String sql = "select time from movie where num=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                time = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return time;
    }


    public void plusSeat(int num) {
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt = null;

        String sql = "update movie set audience=audience+1 where num=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);

            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println("movie audience num update!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
