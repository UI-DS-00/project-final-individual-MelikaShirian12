package org.example.main;

import org.example.classes.Graph;
import org.example.classes.SpecifiedInfo;
import org.example.classes.User;
import org.example.exceptions.DuplicateException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongPasswordException;

import java.util.Scanner;
import java.util.Set;

public class Main {

    public static User loggedIn_user = null;


    public static void main(String[] args) {

        Graph unlinkedOutGraph = new Graph();

        Files.readingFiles();

        Scanner sc = new Scanner(System.in);
        int order = 10 ;

        while (order != 0) {

            try {
                System.out.println("login 1\nsign up 2\nall users 3\nlogin page 4\nsearch 5\nfind by name 6\nexit 0");
                order =sc.nextInt();

                switch (order) {

                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        allUsers();
                        break;
                    case 4:
                        if (loggedIn_user != null)
                            LoggedInUserFeatures.loginPage();
                        else throw new NotFoundException("you are not logged in !");
                        break;
                    case 5:
                        try {
                            System.out.println(searchPerson());
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("enter the name");
                        Set<User> users =Graph.getLinkedInUsers().findUserByName(sc.nextLine());
                        for (User user : users)
                            System.out.println(user.toString());
                        break;
                    case 0:
                        return;

                    default:
                        System.out.println("wrong number !");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }


    public static void login() throws NotFoundException, WrongPasswordException {

        System.out.println("please enter username and password : ");
        Scanner sc = new Scanner(System.in);

        User user = Graph.getLinkedInUsers().findUser(Integer.parseInt(sc.nextLine()));
        String password = sc.nextLine();

        if (user == null)
            throw new NotFoundException("user not found");

        //else we should take the password
        if (password.equals(user.getPassword()))
            System.out.println("you are logged in !");
        else throw new WrongPasswordException();

        loggedIn_user = user;
    }

    public static void signUp(){

        Scanner sc = new Scanner(System.in);
        System.out.println("please fill in the form : ");

//        System.out.println("username and password:");
//        String username = sc.nextLine();
//        String password = sc.nextLine();
//
//        User user =Graph.getLinkedInUsers().findUser(username);

//        while (user != null) {
//            username = sc.nextLine();
//            if (user != null)
//                System.out.println("there is another user with this username please choose another username");
//        }


        System.out.println("name : ");
        String name = sc.nextLine();

        System.out.println("password");
        String password= sc.nextLine();

        System.out.println("birth date : ");
        String birth_date = sc.nextLine();

        System.out.println("university : ");
        String university = sc.nextLine();
        System.out.println("filed : ");
        String field = sc.nextLine();
        System.out.println("workstation :");
        String workstation = sc.nextLine();

        System.out.println("email: ");
        String email = sc.nextLine();

        SpecifiedInfo specifiedInfo = new SpecifiedInfo(university , field , workstation);

        User new_user = new User(name , birth_date , specifiedInfo , password , email , null);

        Graph.getLinkedInUsers().getVertices().add(new_user);

        System.out.println("specialties: ");
        System.out.println("number of specialities: ");
        int number = sc.nextInt();

        sc.nextLine();
        while (number > 0) {
            String input = sc.nextLine();
            new_user.getSpecialties().add(input);

            --number;
        }


        System.out.println("registered successfully!");
        System.out.println("your id :" + new_user.getIdNumber());

        loggedIn_user = new_user;
        Graph.getLinkedInUsers().getVertices().add(new_user);

    }

    public static void allUsers(){

        for (User user : Graph.getLinkedInUsers().getVertices())
            System.out.println(user.toString());

    }

    public static User searchPerson() throws NotFoundException {

        System.out.println("enter the username :");
        Scanner sc = new Scanner(System.in);

        User user =Graph.getLinkedInUsers().findUser(Integer.parseInt(sc.nextLine()));

        if (user == null)
            throw new NotFoundException("user not found !");


        return user;
    }
}
