package org.example.main;

import org.example.classes.*;
import org.example.exceptions.NotFoundException;

import java.util.*;

public class LoggedInUserFeatures {

    public static void loginPage(){

        int order = 10;
        Scanner sc = new Scanner(System.in);

        while (order != 2) {
            try {
                System.out.println("log out 1\nback 2\n20 suggestion 3\nconnecting to another 4\nshow info 5\nsuggestion with giving  priority 6");
                order = sc.nextInt();

                switch (order) {
                    case 1:
                        Main.loggedIn_user = null;
                        return;
                    case 2:
                        return;
                    case 3:
                        //default mode
                        Scoring scoring = new Scoring(5 , 4, 3, 2, 1);
                        ArrayList<User> users=suggestions(scoring);
                        for (User user : users)
                            System.out.println(user.toString());

                        break;
                    case 4:
                        connect();
                        break;
                       // remove
                    case 5:
                        System.out.println(Main.loggedIn_user.toString());
                        break;
                    case 6:
                        System.out.println("enter your priorities for each of these from 1 to 5 : ");
                        System.out.println("level :\nspecialities Score :\nfiledScore :\nuni Score :\nworkstation Score :");

                        scoring = new Scoring(sc.nextInt(), sc.nextInt() , sc.nextInt() , sc.nextInt() ,sc.nextInt());
                        users = suggestions(scoring);
                        for (User user : users)
                            System.out.println(user.toString());

                        break;
                    default:
                        System.out.println("wrong input");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }


    public static ArrayList<User> suggestions(Scoring scoring) throws NotFoundException {

        if (Main.loggedIn_user.getConnectedPeople().size() == 0){

            MyHeap heap = new MyHeap();

            for (User user : Graph.getLinkedInUsers().getVertices()){
                int weight = Graph.scoring(Main.loggedIn_user, user  , 0 , scoring.getLevelScore(),scoring.getSpecialitiesScore(),
                        scoring.getFiledScore(),scoring.getUniScore(),scoring.getWorkstationScore());
                heap.insert(weight , user);
            }
            return twentyRecommender(heap);
        }

        MyHeap BfsHeap = new MyHeap();
        MyTree bfsTree = Graph.getLinkedInUsers().BFS(Main.loggedIn_user , new HashSet<>() , BfsHeap , scoring);


        return twentyRecommender(BfsHeap);
    }

    public static void connect() throws NotFoundException {
        System.out.println("please enter the user id you wanna connect to : ");
        Scanner sc= new Scanner(System.in);

        int id = sc.nextInt();

        User oppositeUser = Graph.getLinkedInUsers().findUser(id);

        if (Main.loggedIn_user == null)
            throw new NotFoundException("there is no person with this id");

        if (!oppositeUser.getLinkedPeople().containsKey(Main.loggedIn_user)){

            Edges edges = new Edges(Main.loggedIn_user,oppositeUser);

            //adding connected people
            oppositeUser.getLinkedPeople().put(Main.loggedIn_user , edges);
            Main.loggedIn_user.getLinkedPeople().put(oppositeUser , edges);

            //adding the new edge
            Graph.getLinkedInUsers().getEdges().add(edges);
        }
        else System.out.println("you are already connected to this person");
    }

    public static ArrayList <User> twentyRecommender(MyHeap heap){
        ArrayList <User> users = new ArrayList<>();

        int number = 20;
        while (! heap.isEmpty() ){

            if (number == 0)
                break;

            users.add(heap.removeMax());

            --number;
        }

        return users;
    }

    public static void removeEdge(){

        System.out.println("enter the user id");
        Scanner sc = new Scanner(System.in);

        int id = sc.nextInt();
        for (int Id : Main.loggedIn_user.getConnectedPeople()){

        }
    }
}
