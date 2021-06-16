package repository;

import data.Database;

public class RepositoryFactory {
    public Repository  getRepository(String repoType, Database db){
        if(repoType== null){
            return null;
         }	

         if(repoType.equalsIgnoreCase("AuctionRepository")){
            return (Repository) new AuctionRepository(db);
            
         } else if(repoType.equalsIgnoreCase("UserRepository")){
            return  (Repository) new UserRepository(db);
            
         }

         return null;
    }
}
