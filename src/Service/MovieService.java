package Service;


import Dao.MovieDAO;
import VO.MovieVO;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieService {

    MovieDAO dao;
    public static String loginId = "";

    public MovieService() {
        dao = new MovieDAO();
    }

    public void addMovie(Scanner sc) { //join
        System.out.println("------Add Movie information------");
        System.out.println("movie name : ");
        String name = sc.next();
        System.out.println("current ranking : ");
        int ranks=sc.nextInt();
        System.out.println("movie time : ");
        String time = sc.next();
        dao.insert(new MovieVO(0, ranks, name, time, 12)); //db에 한줄추가

    }

    public void printAll() {
        ArrayList<MovieVO> data = dao.MovieSelectAll();
        System.out.println("now playing list");
        for (MovieVO o : data) {
            System.out.println(o);
        }
    }



}
