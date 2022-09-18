package Service;

import Dao.MemberDAO;
import VO.MemberVO;
import VO.ReserVO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {

    private MemberDAO dao;
    public static String loginId = "";

    public MemberService() {
        dao = new MemberDAO();
    }

    public void addMember(Scanner sc) { //join
        System.out.println("------register-----");
        System.out.println("id : ");
        String id = sc.next();
        System.out.println("pwd : ");
        int pwd = sc.nextInt();
        int count=dao.duplicateID(id);
        if(count>0){
            System.out.println("duplicated id exists");
            return;
        } else if (count==0) {
            dao.insert(new MemberVO(id, pwd, false));
        }


    }

    public int login(Scanner sc) { //MemberLogin
        System.out.println("login");
        if ((loginId.length())>0) {
            System.out.println("already login");
            return 1;
        }
        System.out.println("id : ");
        String id = sc.next();
        System.out.println("pwd : ");
        int pwd = sc.nextInt();
        MemberVO m = dao.select(id,pwd);
        if (m == null) {
            System.out.println("null");
        } else {
            if (m.getId().equals(id) && m.getPwd() == (pwd)) {
                System.out.println("success");
                //로그인 처리
                loginId = m.getId();
                return 1;
            } else {
                System.out.println("id or pw incorrect");

            }
        }
        return -1;
    }//login


    public int Adminlogin(Scanner sc) { //AdminLogin
        System.out.println("Admin login");
        if(loginId.equals("admin")){
            System.out.println("Admin already login");
            return 1;
        }

        System.out.println("id : admin");
        String id = "admin";
        System.out.println("pwd : ");
        int pwd = sc.nextInt();
        MemberVO m = dao.select(id,pwd);
        if (m == null) {
            System.out.println("null");
        } else {
            if (m.getId().equals(id) && m.getPwd() == (pwd)) {
                System.out.println("admin success");
                //로그인 처리
                loginId = m.getId();
                return 1;
            } else {
                System.out.println("admin pw incorrect");
                return -1;
            }
        }
        return -1;
    }

    public void logout(Scanner sc) {
        if (loginId.length() == 0) {
            System.out.println("login first please");
            return;
        }
        //로그아웃처리
        System.out.println("logout? y or n");
        String ans=sc.next();
        if(ans.equals("y")){
            loginId = "";
            System.out.println("logout success");
        }else{
            System.out.println("logout failed");
            return;
        }


    }//logout

    public void printMemberByAdmin(){
        ArrayList<MemberVO> list=dao.printAllByAdmin();
        for(MemberVO mv:list){
            System.out.println(mv);
        }
    }

    public void DelMemberByAdmin(Scanner sc){
        System.out.println("----delete account----");
        System.out.println("id to cancel:");
        String id=sc.next();
        MemberVO mv=dao.selMemByAdmin(id);
        if(mv==null){
            System.out.println("no id");
            return;
        }
        System.out.println("cancel y or n");
        String ans=sc.next();
        if(ans.equals("y")){
            boolean delmem=dao.delMemByAdmin(id);
            if(delmem==true){
                System.out.println("cancel success");
            }else{
                System.out.println("id not correct");
            }
        }else if(ans.equals("n")){
            System.out.println("stop cancel");
            return;
        }
    }


}
