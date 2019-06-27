package king.easyfileX.model;

import org.springframework.stereotype.Repository;

@Repository 
public class StorageProperties{
   public String getLocation(){
      return "/home/test0/maven/upload";
   }
}
