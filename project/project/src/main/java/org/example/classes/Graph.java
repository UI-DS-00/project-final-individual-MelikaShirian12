package org.example.classes;

import org.example.exceptions.NotFoundException;

import java.util.*;

public class Graph {

    private static Graph linkedInUsers;
    private Set <Edges> edges ;
    private Set <User> vertices;

    public Graph() {
        this.edges = new HashSet<>();
        this.vertices = new HashSet<>();

        if (linkedInUsers == null)
            linkedInUsers = this;
    }

    public User findUser(Integer username){

        for (User user : linkedInUsers.vertices){
            if (user.getIdNumber().equals(username))
                return user;
        }

        return null;
    }


    public Set<User> findUserByName(String name){

        Set<User> users = new HashSet<>();

        for (User user : linkedInUsers.vertices){
            if (user.getName().equals(name))
                users.add(user);
        }

        if (users.isEmpty())
            users=null;

        return users;
    }

    public MyTree BFS (User startingPoint , Set<User> known , MyHeap BfsHeap , Scoring scoring) throws NotFoundException {

        MyTree BFSTree = new MyTree();
        List <User> level = new ArrayList<>();
        known.add(startingPoint);
        level.add(startingPoint);


        BFSTree.setRoot(startingPoint);

        //making a tree with at last six levels
        int fiveRows = 5;
        while (!level.isEmpty()){

            if (fiveRows == 0)
                break;

            List <User> nextLevel = new ArrayList<>();

            for (User user : level)
                for (Map.Entry <User , Edges> linked : user.getLinkedPeople().entrySet()){

                    if (! known.contains(linked.getKey())){
                        known.add(linked.getKey());
                        // the more the level is higher ,  the more is the weight so we mines it from five
                        int weight=BFSTree.addNode(user , linked.getKey() , 5-fiveRows , scoring);
                        nextLevel.add(linked.getKey());

                        //finding the level of the user using bfs
                        BfsHeap.insert(weight , linked.getKey());
                    }
                }
            level = nextLevel;

            //we are going to the next level
            --fiveRows;
        }

        return BFSTree;
    }



    //priority starts from bigger number to smaller from range in 5 to 1
    //giving each of the priorities a place in a range of numbers using power 10 so they wont affect each other
    public static int scoring(User parent , User child , int bfsLevel, int levelScore , int specialitiesScore ,
                       int filedScore , int uniScore , int workstationScore) {

        int weight = 0;

        weight += Math.pow(10 , levelScore)* bfsLevel;

        int priority = 0;
        for (String speciality : parent.getSpecialties()) {
            ++priority;
            if (child.getSpecialties().contains(speciality)) {
                int m = parent.getSpecialties().size() - priority;
                weight += Math.pow(10, specialitiesScore) * m;
            }
        }


        try {
            if (parent.getSpecifiedInfo().getField().equals(child.getSpecifiedInfo().getField()))
                weight += Math.pow(10, filedScore);
        } catch (NullPointerException e) {
            //for when we have a null argument
        }
         try {
             if (parent.getSpecifiedInfo().getUniversity().equals(child.getSpecifiedInfo().getUniversity()))
                 weight += Math.pow(10, uniScore);
         } catch (NullPointerException e) {
             //for when we have a null argument
         }
         try {
             if (child.getSpecifiedInfo().getWorkStation().equals(child.getSpecifiedInfo().getWorkStation()))
                 weight += Math.pow(10, workstationScore);
         } catch (NullPointerException e) {
             //for when we have a null argument
         }

        return weight;
    }


    //getter setter =====================================================================


    public static Graph getLinkedInUsers() {
        return linkedInUsers;
    }

    public static void setLinkedInUsers(Graph linkedInUsers) {
        Graph.linkedInUsers = linkedInUsers;
    }

    public Set<Edges> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edges> edges) {
        this.edges = edges;
    }

    public Set<User> getVertices() {
        return vertices;
    }

    public void setVertices(Set<User> vertices) {
        this.vertices = vertices;
    }
}
