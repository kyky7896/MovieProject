package Dao;


import Conn.MysqlConnect;
import Service.MemberService;
import VO.MemberVO;
import VO.ReserVO;
import VO.ReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewDAO {

    private final MysqlConnect dbconn;

    public ReviewDAO() {
        dbconn = MysqlConnect.getInstance();
    } //객체를 받아오는 메서드


    public void insert(ReviewVO rev) {
        Connection conn = dbconn.getConn();
        String sql = "insert into review(id,movie_name,title,content) values(?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,rev.getId());
            pstmt.setString(2,rev.getMovie_name());
            pstmt.setString(3,rev.getTitle());
            pstmt.setString(4, rev.getContent());

            int cnt = pstmt.executeUpdate();// insert, update, delete 문 실행 함수
            if(cnt>0){
                System.out.println("write success");
            }else{
                System.out.println("write failed");
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

    public ArrayList<ReviewVO> ReviewList(){
        ArrayList<ReviewVO> list=new ArrayList<ReviewVO>();
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt= null;
        ResultSet rs;

        String sql="select * from review";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                list.add(new ReviewVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public ReviewVO selectBynum(int num){
        ReviewVO rv = null;
        ResultSet rs = null;
        // db연결
        Connection conn = dbconn.getConn();
        // sql 문 작성
        String sql = "select * from review where num=? and id=?";

        try {
            // preparedstmt 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?매칭
            pstmt.setInt(1, num);
            pstmt.setString(2,MemberService.loginId);

            // sql 실행
            rs = pstmt.executeQuery();// 검색한 결과를 ResultSet으로 반환

            if (rs.next()) {
                rv = new ReviewVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rv;
    }//selectBynum


    public boolean deleteBynum(int num){
        // 1. db연결
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "delete from review where num=? and id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setString(2, MemberService.loginId);
            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                return true;
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
        return false;
    }//deleteBynum




}
