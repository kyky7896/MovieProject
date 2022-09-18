package Dao;

import Conn.MysqlConnect;
import Service.MemberService;
import VO.ReserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ReserDAO {

    private final MysqlConnect dbconn;

    public ReserDAO() {
        dbconn = MysqlConnect.getInstance();
    } //객체를 받아오는 메서드


    public boolean insert(ReserVO re){
        System.out.println(re);
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt=null;
        String sql="insert into reservation(m_num,id,seatnumber,time) values(?,?,?,?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, re.getM_num()); //영화 객체의 num과 foreign key
            pstmt.setString(2, re.getId());
            pstmt.setString(3,re.getSeatnumber());
            pstmt.setString(4,re.getTime());

            int num=pstmt.executeUpdate();
            if(num>0){
                System.out.println("reservation insert success");
                return true;
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
        return false;

    }//insert

    public void updateAudi(int m_num){
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt=null;

        String sql="update movie set audience=audience-1 where num=?;";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,m_num);

            int cnt =pstmt.executeUpdate();
            if(cnt>0){
                System.out.println("audience update!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean dupSeatNum(int m_num,String seatNumber){
        ResultSet rs;
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "select * from reservation where m_num=? and seatnumber=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,m_num);
            pstmt.setString(2,seatNumber);

            rs=pstmt.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public boolean delete(int num,String seatnumber){
        // 1. db연결
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "delete from reservation where m_num=? and id=? and seatnumber=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setString(2,MemberService.loginId);
            pstmt.setString(3,seatnumber);
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
    }//delete

    public ReserVO selectByMnum(int m_num,String seatnumber){ //m_num과 id가 같은
        ReserVO rv;
        ResultSet rs;
        // db연결
        Connection conn = dbconn.getConn();
        // sql 문 작성
        String sql = "select * from reservation where m_num=? and id=? and seatnumber=?";

        try {
            // preparedstmt 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?매칭
            pstmt.setInt(1, m_num);
            pstmt.setString(2,MemberService.loginId);
            pstmt.setString(3,seatnumber);

            // sql 실행
            rs = pstmt.executeQuery();// 검색한 결과를 ResultSet으로 반환

            if (rs.next()) {
                rv=new ReserVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));

                return rv;
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
        return null;
    }//selectByMnum




    public ArrayList<ReserVO> selectAllByMem(){
        ArrayList<ReserVO> list=new ArrayList<ReserVO>();
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt= null;
        ResultSet rs;

        String sql="select * from reservation where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,MemberService.loginId);
            rs=pstmt.executeQuery();

            while(rs.next()){
                list.add(new ReserVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
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
    }//idselectAll

    public ArrayList<ReserVO> selectAllByAdmin(){
        ArrayList<ReserVO> list=new ArrayList<ReserVO>();
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt= null;
        ResultSet rs;

        String sql="select * from reservation";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                list.add(new ReserVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
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
    }//idselectAll

    public ReserVO selectByAdmin(int m_num,String id,String seatnum){ //m_num과 id가 같은 것 select
        ReserVO rv = null;
        ResultSet rs = null;
        // db연결
        Connection conn = dbconn.getConn();
        // sql 문 작성
        String sql = "select * from reservation where m_num=? and id=? and seatnumber=?";

        try {
            // preparedstmt 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?매칭
            pstmt.setInt(1, m_num);
            pstmt.setString(2,id);
            pstmt.setString(3,seatnum);

            // sql 실행
            rs = pstmt.executeQuery();// 검색한 결과를 ResultSet으로 반환

            if (rs.next()) {
                rv = new ReserVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
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
    }//selectByMnum

    public boolean deleteByAdmin(int num,String id,String seatnum){
        // 1. db연결
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "delete from reservation where m_num=? and id=? and seatnumber=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setString(2, id);
            pstmt.setString(3,seatnum);
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
    }//delete

    public ArrayList<String> printSeats(int m_num){
        ArrayList<String> list=new ArrayList<String>();
        ResultSet rs = null;

        // 1. db연결
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "select seatnumber from reservation where m_num=?";

        try {
            // preparedstmt 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?매칭
            pstmt.setInt(1, m_num);

            // sql 실행
            rs = pstmt.executeQuery();// 검색한 결과를 ResultSet으로 반환

            while (rs.next()) {
                list.add(rs.getString(1));
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
        return list;

    }

    public void updateSeat(int movieNum, String NewSeatNum, String OldSeatNum){
        Connection conn = dbconn.getConn();
        PreparedStatement pstmt=null;

        String sql="update reservation set seatnumber=? where m_num=? and id=? and seatnumber=?;";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,NewSeatNum);
            pstmt.setInt(2,movieNum);
            pstmt.setString(3,MemberService.loginId);
            pstmt.setString(4,OldSeatNum);

            int cnt =pstmt.executeUpdate();
            if(cnt>0){
                System.out.println("reservation update!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}//end of dao
