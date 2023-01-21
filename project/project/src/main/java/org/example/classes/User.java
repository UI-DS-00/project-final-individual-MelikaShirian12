package org.example.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class User {

    private static int idNumber = 0;

    private Integer IdNumber;
    private String name;
    private String birthDate;
    private String password;
    private String email;

    private SpecifiedInfo specifiedInfo;
    private ArrayList<String> specialties;
    //make stickers

    private Set <Integer> connectedPeople = new HashSet<>(); //using it only for reading from files
    private HashMap <User , Edges> linkedPeople;
    private ArrayList <User> suggestions;

    public User(String name, String birthDate, SpecifiedInfo specifiedInfo ,String password , String email ,Integer idNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.specifiedInfo = specifiedInfo;
        this.specialties = new ArrayList<>();
        this.linkedPeople = new HashMap<>();
        this.suggestions = new ArrayList<>();
        this.IdNumber = idNumber;
        this.password = password;
        this.email = email;
        if (idNumber == null)
            this.IdNumber = ++this.idNumber;
        else this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "IdNumber=" + IdNumber +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", specifiedInfo=" + specifiedInfo +
                ", specialties=" + specialties +
                ", connectedPeople=" + connectedPeople +
                ", suggestions=" + suggestions +
                '}';
    }

//getter setter ====================================================================================================


    public Set<Integer> getConnectedPeople() {
        return connectedPeople;
    }

    public void setConnectedPeople(Set<Integer> connectedPeople) {
        this.connectedPeople = connectedPeople;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public SpecifiedInfo getSpecifiedInfo() {
        return specifiedInfo;
    }

    public void setSpecifiedInfo(SpecifiedInfo specifiedInfo) {
        this.specifiedInfo = specifiedInfo;
    }

    public ArrayList<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }

    public HashMap<User, Edges> getLinkedPeople() {
        return linkedPeople;
    }

    public void setLinkedPeople(HashMap<User, Edges> linkedPeople) {
        this.linkedPeople = linkedPeople;
    }

    public ArrayList<User> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<User> suggestions) {
        this.suggestions = suggestions;
    }

    public void setIdNumber(Integer idNumber) {
        IdNumber = idNumber;
    }

    public Integer getIdNumber() {
        return IdNumber;
    }
}
