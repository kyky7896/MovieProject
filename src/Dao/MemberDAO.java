package Dao;


import Conn.MysqlConnect;
import Service.MemberService;
import VO.MemberVO;
import VO.MovieVO;
import VO.ReserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
    private final MysqlConnect dbconn;

    public MemberDAO() {
        dbconn = MysqlConnect.getInstance();
    } //객체를 받아오는 메서드

    //member회원가입시 insert
    public void insert(MemberVO m) {
        Connection conn = dbconn.getConn();
        String sql = "insert ignore into member(id,pwd,type) values(?,?,false)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, m.getId());
            pstmt.setInt(2, m.getPwd());

            int cnt = pstmt.executeUpdate();// insert, update, delete 문 실행 함수
            if(cnt>0){
                System.out.println("register success");
            }else{
                System.out.println("register failed");
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

    public int duplicateID(String id){
        ResultSet rs;
        int count = 0;
        //1. db연결
        Connection conn = dbconn.getConn(); //db연결

        //2. 실행할 sql문 작성
        String sql = "select count(*) from member where id=?";
        try {
            //3. sql 실행할 preparestmt객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ? 매칭
            pstmt.setString(1,id);

            //5. 실행
            rs = pstmt.executeQuery(); //select문은 executeQuery로 실행. 반환값은 검색결과인 ResultSet
            if (rs.next()) {
                count=rs.getInt(1);
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
        return count;


    }

    public MemberVO select(String id,int pwd) {
        ResultSet rs;
        MemberVO m = null;

        //1. db연결
        Connection conn = dbconn.getConn(); //db연결

        //2. 실행할 sql문 작성
        String sql = "select * from member where pwd=? and id=?";
        try {
            //3. sql 실행할 preparestmt객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ? 매칭
            pstmt.setInt(1, pwd);
            pstmt.setString(2,id);

            //5. 실행
            rs = pstmt.executeQuery(); //select문은 executeQuery로 실행. 반환값은 검색결과인 ResultSet
            if (rs.next()) { //next() 다음줄로 이동, 맨 처음 호출하면 첫 번재 줄로 이동
//                int numm=rs.getInt(1);
//                String id=rs.getString(2);
//                String pwd=rs.getString(3);

                m = new MemberVO(rs.getString(1), rs.getInt(2), rs.getBoolean(3));
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
        return m;
    }//select끝

    public ArrayList<MemberVO> printAllByAdmin() {
        ArrayList<MemberVO> list = new ArrayList<MemberVO>();
        ResultSet rs = null;
        Connection conn = dbconn.getConn();
        String sql = "select * from member where id not like '%admin%'";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new MemberVO(rs.getString(1),rs.getInt(2),rs.getBoolean(3)));
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
    }//printAllByAdmin

    public MemberVO selMemByAdmin(String id){//admin이 하는 select
        MemberVO mv = null;
        ResultSet rs = null;
        // db연결
        Connection conn = dbconn.getConn();
        // sql 문 작성
        String sql = "select * from member where id=?";

        try {
            // preparedstmt 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?매칭
            pstmt.setString(1, id);

            // sql 실행
            rs = pstmt.executeQuery();// 검색한 결과를 ResultSet으로 반환

            if (rs.next()) {
                mv = new MemberVO(rs.getString(1),rs.getInt(2),rs.getBoolean(3));
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
        return mv;

    }

    public boolean delMemByAdmin(String id){
        // 1. db연결
        Connection conn = dbconn.getConn();
        // 2. sql
        String sql = "delete from member where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println("delete!");
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


}
