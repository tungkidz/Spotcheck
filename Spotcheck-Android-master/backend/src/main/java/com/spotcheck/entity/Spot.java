package com.spotcheck.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;

/**
 * Created by Tung on 4/12/2016.
 */

@Entity
public class Spot {
    @Id
    Long id;
    String name;
    String address;
    String type;
    ArrayList<String> tags;

    private Spot(){}

    public Spot(Long id, String name, String address, String type, ArrayList<String> tag){
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.tags = tag;
    }

    public Long getId(){return id;}
    public void setId(Long _id){this.id = _id;}

    public String getName(){return name;}
    public void setName(String _name){this.name = _name;}

    public String getAddress() {return address;}
    public void setAddress(String _address) {this.address = _address;}

    public String getType(){return type;}
    public void setType(String _type){this.type = _type;}

    public ArrayList<String> getTags(){return tags;}
    public void setTags(ArrayList<String> _tags){this.tags = _tags;}
}