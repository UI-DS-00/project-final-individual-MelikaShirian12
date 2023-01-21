package org.example.main;

import org.example.classes.Edges;
import org.example.classes.Graph;
import org.example.classes.SpecifiedInfo;
import org.example.classes.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.HTMLDocument;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Files {


    private static String filePath = "D://university/programs-data struct/linkedIn projcet/users.json";

    public static void readingFiles(){


        int num=6;
        System.out.printf("loading . . .");
        while (num > 0)
        {
            try {
                Thread.sleep(500);
            }catch (Exception e){}
            System.out.printf(" .");
            num--;
        }
        System.out.println("");

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //reading all files
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            userList.forEach( emp -> eachUser( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //adding edges and connections
        addEdges();

    }

    private static void eachUser(JSONObject user){

        String id= (String)user.get("id");
        String name = (String) user.get("name");
        String dateOfBirth = (String) user.get("dateOfBirth");
        String universityLocation =  (String) user.get("universityLocation");
        String field =  (String) user.get("field");
        String workplace =  (String) user.get("workplace");

        JSONArray specialitiesArr =  (JSONArray) user.get("specialties");
        Iterator iterator = specialitiesArr.iterator();

        ArrayList<String> specialities = new ArrayList<>();
        while (iterator.hasNext())
            specialities.add(iterator.next().toString());

        String email = (String) user.get("email");

        SpecifiedInfo specifiedInfo = new SpecifiedInfo(universityLocation , field , workplace);
        User new_user = new User(name ,dateOfBirth , specifiedInfo ,"0000" ,email , Integer.parseInt(id));

        new_user.setSpecialties(specialities);


        JSONArray connectionIdArr =  (JSONArray) user.get("connectionId");

        iterator = connectionIdArr.iterator();
        while (iterator.hasNext()){
            int userConnection = Integer.parseInt(iterator.next().toString());
            new_user.getConnectedPeople().add(userConnection);
        }
        //adding new
        Graph.getLinkedInUsers().getVertices().add(new_user);
    }

    private static void addEdges(){

        for (User eachUser : Graph.getLinkedInUsers().getVertices()){
            for (Integer oppositeUserId : eachUser.getConnectedPeople()){

                User oppositeUser = Graph.getLinkedInUsers().findUser(oppositeUserId);
                if (!oppositeUser.getLinkedPeople().containsKey(eachUser)){

                    Edges edges = new Edges(eachUser,oppositeUser);

                    //adding connected people
                    oppositeUser.getLinkedPeople().put(eachUser , edges);
                    eachUser.getLinkedPeople().put(oppositeUser , edges);

                    //adding the new edge
                    Graph.getLinkedInUsers().getEdges().add(edges);
                }
            }
        }

    }
}
