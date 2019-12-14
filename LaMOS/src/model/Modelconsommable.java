/**
 *****
 * *****
 
                         ****  @author BOUCHEBAH  AGHILAS  *****
                 
                 
       
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
package  model;


public class Modelconsommable {
    
         public  String getSelection(){
           return "SELECT idEquipement,typeconso,marqequi,prix,date "
                     + "FROM equipement where etatordi='' AND etatimpri='' ";
            }
    public String getajoutordi(){
        return "INSERT INTO `equipement-lamos`.`equipement`"
                          + " (`typeconso`, `marqequi`, `marqordi`, `prix`, `date`, `etatordi`, `etatimpri`) "
                          + "VALUES (?, ?,'', ?, ?, '', '')";
    }
     
    public  String getmodi(){
    return    "UPDATE equipement SET "
                               + "typeconso = ?, marqequi = ?, prix = ?, date = ?"
                               ;
                               
    }
    public  String getsupp(){
        return "delete from equipement where typeconso = ?";
    
    }
    
     public String getrech(String rech) {
       return "SELECT idEquipement,typeconso,marqequi,prix,date "
                     + "FROM equipement where etatordi='' and etatimpri='' and typeconso  like '" +rech+ "%'"; 
    }
    
    
    
    
}
