package org.example.classes;

import org.example.exceptions.NotFoundException;
import org.example.main.Main;

import java.util.*;

public class  MyTree <D>{

    private Node root;
    public MyTree() {
        this.root = null;
    }

    static class Node <DATA>{

        private ArrayList <Node> children;
        private Node parent ;

        private int weight =0;
        private DATA data;


        //getter setter =====================================================================
        public Node(DATA data) {
            children = new ArrayList<>();
            this.parent = null;
            this.data = data;
        }


        public ArrayList<Node> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Node> children) {
            this.children = children;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public DATA getData() {
            return data;
        }

        public void setData(DATA data) {
            this.data = data;
        }
    }

    public int addNode (D parentData , D data , int levelScore , Scoring scoring) throws NotFoundException {

        if (root == null) {
            Node new_node = new Node<D>(data);
            this.root = new_node;
            root.parent=null;
        }

        Node parent = search(parentData , root);
        if (parent == null)
            throw new NotFoundException("inner exception : parent node not found");

        Node new_node = new Node<D>(data);
        parent.children.add(new_node);
        new_node.parent = parent;

        //making the node's score
        //5, 4 , 3 , 2, 1
       int weight = Graph.scoring(Main.loggedIn_user, (User)data , levelScore ,scoring.getLevelScore() , scoring.getSpecialitiesScore(),scoring.getFiledScore(),
               scoring.getUniScore() , scoring.getWorkstationScore());

       new_node.weight = weight;

       return weight;
    }



    public Node search (D data , Node currentNode){

        if (data.equals(currentNode.data))
            return currentNode;

        Node node = null;
        for (Object child : currentNode.children){

            if (node != null)
                return node;

           node=search(data , (Node)child);
        }
        return node;
    }


    //getter setter ====================================================================================================

    public Node getRoot() {
        return root;
    }

    public void setRoot(User root) {
        this.root = new Node<>(root);
    }


}