package Service;


import Dao.MovieDAO;
import Dao.ReserDAO;
import VO.MovieVO;
import VO.ReserVO;
import VO.ReviewVO;

import java.util.ArrayList;
import java.util.Scanner;

public class ReserService {

    ReserDAO dao;
    MovieDAO mdao;

    public ReserService() {
        dao = new ReserDAO();
        mdao = new MovieDAO();
    }

    public void ShowSeat(int m_num){ //
        String num1;
        int num2;
        ArrayList<String> SnumList =dao.printSeats(m_num);

        int[][] seat=new int[3][4];
        int row;
        for(int i=0;i< SnumList.size();i++) {
            num1 = SnumList.get(i).substring(0, 1);
            num2 = Integer.parseInt(SnumList.get(i).substring(1, 2));

            System.out.println(num1 + "&" + num2);

            if (num1.equals("a") && (num2 < 4)) {
                row = 0;
                if (seat[row][num2] == 1) {
                    System.out.println("already assigned seats");
                } else if (seat[row][num2] == 0) {
                    seat[row][num2] = 1;
                }
            } else if (num1.equals("b") && (num2 < 4)) {
                row = 1;
                if (seat[row][num2] == 1) {
                    System.out.println("already assigned seats");
                } else if (seat[row][num2] == 0) {
                    seat[row][num2] = 1;
                }
            } else if (num1.equals("c") && (num2 < 4)) {
                row = 2;
                if (seat[row][num2] == 1) {
                    System.out.println("already assigned seats");
                } else if (seat[row][num2] == 0) {
                    seat[row][num2] = 1;
                }
            } else {
                System.out.println("no seats in here!!");
                return;
            }
        }
            System.out.println("  0 1 2 3");
            for (int k = 0; k < seat.length; k++) {
                if(k==0) {
                    System.out.print("a ");
                }else if(k==1){
                    System.out.print("b ");
                }else if(k==2){
                    System.out.print("c ");
                }
                for (int j=0; j <seat[k].length;j++){
                    System.out.print(seat[k][j] + " ");
                }
                System.out.println();
            }
        }



    public void AddReser(Scanner sc){
        System.out.println("---reservation---");
        System.out.println("Movie num:");
        int m_num=sc.nextInt();
        //보여줄 좌석 list//
        ShowSeat(m_num);
        System.out.println("seat number you want(a ~ c && 0 ~ 3 ):");
        System.out.println("1: not available 0: available");
        String seatNumber=sc.next();
        //seatnumber 처리
//        String num1=seatNumber.substring(0, 1);
//        int num2=Integer.parseInt(seatNumber.substring(1, 2));

        //받아온 seatNumber가 db안에 있을때는 못넣게해야함.
        boolean duplicate=dao.dupSeatNum(m_num,seatNumber);
        if(duplicate==true){
            System.out.println("Duplicate seats exist");
            return;
        }
        String time=mdao.SelectTimeByNum(m_num); //num으로 movie의 time받아오게하기

        boolean insert=dao.insert(new ReserVO(0, m_num, MemberService.loginId,seatNumber,time));
        if(insert==true){
            dao.updateAudi(m_num);
        }else{//insert==false일때
            System.out.println("audience update failed");
            return;
        }

    }

    public void DeleteReser(Scanner sc){
        System.out.println("----delete reservation----");
        System.out.println("Movie num to cancel:");
        int num=sc.nextInt();
        System.out.println("seatnumber");
        String seatnumber=sc.next();
        ReserVO rv=dao.selectByMnum(num,seatnumber);
        if(rv==null){
            System.out.println("no movie num");
            return;
        }
        System.out.println("cancel y or n");
        String ans=sc.next();
        if(ans.equals("y")){
            boolean suc=dao.delete(num,seatnumber);
            if(suc==true) {
                System.out.println("cancel success");
                mdao.plusSeat(num);
            }else{
                System.out.println("cancel failed");
            }
        }else if(ans.equals("n")){
            System.out.println("stop cancel");
            return;
        }
    }

    public void printReserid(){
        ArrayList<ReserVO> list =dao.selectAllByMem(); //id가 예약한 영화들 print
        for(ReserVO rv:list){
            System.out.println(rv);
        }
    }

    public void printReserAdmin(){
        ArrayList<ReserVO> list = dao.selectAllByAdmin();
        for(ReserVO rv:list){
            System.out.println(rv);
        }
    }

    public void DeleteReserByAdmin(Scanner sc){
        System.out.println("----delete reservation----");
        System.out.println("id to cancel:");
        String id=sc.next();
        System.out.println("Movie num to cancel:");
        int num=sc.nextInt();
        System.out.println("seatnum to cancel :");
        String seatnum=sc.next();
        ReserVO rv=dao.selectByAdmin(num,id,seatnum);
        if(rv==null){
            System.out.println("no movie num");
            return;
        }
        System.out.println("cancel y or n");
        String ans=sc.next();
        if(ans.equals("y")){
            boolean del=dao.deleteByAdmin(num,id,seatnum);
            if(del==true){
                System.out.println("cancel success");
            }else{
                System.out.println("id or m_num not correct");
            }
        }else if(ans.equals("n")){
            System.out.println("stop cancel");
            return;
        }
    }//DeleteReserByAdmin

    public void UpdateSeat(Scanner sc){
        System.out.println("-----Changing Seat------");
        System.out.println("Select Movie_num");
        int m_num=sc.nextInt();
        System.out.println("the past Seat");
        String oldseat=sc.next();
        ReserVO rv=dao.selectByMnum(m_num,oldseat);
        if(rv==null){
            System.out.println("no movie num");
            return;
        }
        System.out.println("Seat you want");
        //보여줄 좌석 list//
        ShowSeat(m_num);
        System.out.println("seat number you want(a ~ c && 0 ~ 3 ):");
        System.out.println("1: not available 0: available");
        //보여줄 좌석 list//
        String NewSeat=sc.next(); //원하는 seat

        dao.updateSeat(m_num, NewSeat,oldseat);


    }//UpdateSeat

}
