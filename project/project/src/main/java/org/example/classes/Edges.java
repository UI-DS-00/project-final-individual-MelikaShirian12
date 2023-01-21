package org.example.classes;

import java.util.Map;

public class Edges {

    private User income;
    private User outgoing;
    private int weight = 0;

    public Edges(User income, User outgoing) {
        this.income = income;
        this.outgoing = outgoing;

        makeWeight();
    }

    public void makeWeight(){

        for (String speciality : outgoing.getSpecialties())
            if (income.getSpecialties().contains(speciality))
                ++weight;

        for (Map.Entry<User , Edges> entry : outgoing.getLinkedPeople().entrySet())
            for (Map.Entry<User , Edges> entry2 : income.getLinkedPeople().entrySet())
                if (entry2.getKey() == entry.getKey())
                    ++weight;

        try {
            if (outgoing.getSpecifiedInfo().getField().equals(income.getSpecifiedInfo().getField()))
                ++weight;
            if (outgoing.getSpecifiedInfo().getUniversity().equals(income.getSpecifiedInfo().getUniversity()))
                ++weight;
            if (outgoing.getSpecifiedInfo().getWorkStation().equals(income.getSpecifiedInfo().getWorkStation()))
                ++weight;

        }catch (NullPointerException e){
            //for when we have a null argument
        }
    }

    //we call this method when we change or update something in
    // vertices which should be called in all edges of that vertices
    public void remakeWeight(){
        weight = 0;
        makeWeight();
    }

}
