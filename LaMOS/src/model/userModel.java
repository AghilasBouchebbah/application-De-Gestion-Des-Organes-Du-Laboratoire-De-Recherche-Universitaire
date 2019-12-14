              
     /*            
                 
       
                 **             *************                       |************|            *
                *  *            |           *                       |            | 
               *    *           |                                   |            |
              *      *          |    *********                      |************|            *
             **********         |    |       *      ********        |            |
            *          *        |            *       ******         |            |
           *            *       **************        ****          **************
***
***|
*/ 
package model;

import db.Connexion;

public class userModel {
    
   
    Connexion kon = new Connexion();
    private boolean statusUpdate=false;
    
    public void updateUser(String id, String username, String password,  String email){
        try {
            kon.db();
            kon.stat.executeUpdate("update login set"
                    + " password='"+password+"',"
                    + " username='"+username+"',"
                    + " email='"+email+"'"
                    + " where id='"+id+"'");
            statusUpdate=true;
        } catch (Exception e) {
            System.out.println(e);
            statusUpdate=false;
        }
    }
    
    public boolean getStatus(){
        return statusUpdate;
    }
    
    
}
