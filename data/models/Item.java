package data.models;

import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;


    public Item( String name, String description){
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }
}
