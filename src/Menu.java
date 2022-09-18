import Dao.ReserDAO;
import Dao.ReviewDAO;
import Service.MemberService;
import Service.MovieService;
import Service.ReserService;
import Service.ReviewService;
import VO.MovieVO;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private MemberService ms;
    private MovieService mos;
    private ReserService rs;

    private ReviewService res;


    public Menu(){
        ms=new MemberService();
        mos=new MovieService();
        rs=new ReserService();
        res=new ReviewService();
    }

    //상위메뉴 실행
    public void run(Scanner sc) {
        boolean flag = true;
        int m = 0;
        while (flag) {
            System.out.println(" hello! ");
            System.out.println("1. register 2. Member login 3. admin Login ");
            m = sc.nextInt();
            switch (m) {
                case 1:
                    //가입하기
                    ms.addMember(sc);
                    break;
                case 2:
                    //로그인
                    int num=ms.login(sc);
                    if(num==-1){
                        System.out.println("login X");
                        break;
                    }
                    AfterMemberlogin(sc);
                    break;
                case 3:
                    int num1=ms.Adminlogin(sc);
                    if(num1==-1){
                        System.out.println("Admin login X");
                        break;
                    }
                    AfterAdminlogin(sc);
                    break;
                case 4:
                    flag=false;
                    break;

            }
        }//run
    }

    public void AfterMemberlogin(Scanner sc) {
        boolean flag = true;
        int m = 0;
        while (flag) {
            System.out.println("1. Ticketing 2. now playing 3.Seat changing 4. Movie cancellation 5. The movie you reserved 6.Review Board 7.Member logout");
            m = sc.nextInt();
            switch (m) {
                case 1:
                    mos.printAll();
                    rs.AddReser(sc);
                    break;
                case 2:
                    mos.printAll();
                    break;
                case 3:
                    rs.printReserid();
                    rs.UpdateSeat(sc);
                    break;
                case 4:
                    rs.printReserid();
                    rs.DeleteReser(sc);
                    break;
                case 5:
                    rs.printReserid();
                    break;
                case 6: //게시판
                    Board(sc);
                    break;
                case 7:
                    ms.logout(sc);
                    flag = false;
                    break;
            }
        }
    }

    public void AfterAdminlogin(Scanner sc) {
        boolean flag = true;
        int m = 0;
        while (flag) {
            System.out.println("1. Add movie 2. now playing 3. (Member) Movie cancellation 4. (Member) delete account 5. Admin logout");
            m = sc.nextInt();
            switch (m) {
                case 1:
                    mos.addMovie(sc);
                    break;
                case 2:
                    mos.printAll();
                    break;
                case 3:
                    rs.printReserAdmin();
                    rs.DeleteReserByAdmin(sc);
                    break;
                case 4:
                    ms.printMemberByAdmin();
                    ms.DelMemberByAdmin(sc);
                    break;
                case 5:
                    ms.logout(sc);
                    flag = false;
                    break;
            }
        }
    }

    public void Board(Scanner sc) {
        boolean flag = true;
        int m = 0;
        while (flag) {
            System.out.println("1. Write Review 2.delete Review 3.Review list 4.Back");
            m = sc.nextInt();
            switch (m) {
                case 1:
                    res.WriteReview(sc);
                    break;
                case 2:
                    res.ReviewList(sc);
                    res.delReview(sc);
                    break;
                case 3:
                    res.ReviewList(sc);
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }




}
