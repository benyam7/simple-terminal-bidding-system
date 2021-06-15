package data;

import java.util.HashMap;

public  class Database extends HashMap<String, Object>{
    private static  Database db;

    private Database(){}

    public static  Database  createDb(){
        if(db == null){
            synchronized(Database.class){
            db = new Database();
            return db;
            }
        }
        return db;
    }
}
