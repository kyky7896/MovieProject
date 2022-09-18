package Service;

import Dao.ReserDAO;
import Dao.ReviewDAO;
import VO.ReserVO;
import VO.ReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewService {

    ReviewDAO dao;

    public ReviewService() {
        dao = new ReviewDAO();
    }

    public void WriteReview(Scanner sc){
        System.out.println("------write Review-----");
        System.out.println("movie_name : ");
        String movieName = sc.next();
        System.out.println("title : ");
        String title = sc.next();
        System.out.println("content : no space");
        String content=sc.next();
        dao.insert(new ReviewVO(0, MemberService.loginId,movieName,title,content)); //db에 한줄추가

    }

    public void delReview(Scanner sc){
        System.out.println("----delete reservation----");
        System.out.println("review num to delete:");
        int num=sc.nextInt();
        ReviewVO rv=dao.selectBynum(num);
        if(rv==null){
            System.out.println("no review num or not your review");
            return;
        }
        System.out.println("delete? y or n");
        String ans=sc.next();
        if(ans.equals("y")){
            boolean suc=dao.deleteBynum(num);
            if(suc==true){
                System.out.println("delete success");
            }else{
                System.out.println("delete failed");
            }
        }else if(ans.equals("n")){
            System.out.println("stop delete");
            return;
        }
    }

    public void ReviewList(Scanner sc){
        ArrayList<ReviewVO> list=dao.ReviewList();
        for(ReviewVO rv:list){
            System.out.println(rv);
        }
    }


}
