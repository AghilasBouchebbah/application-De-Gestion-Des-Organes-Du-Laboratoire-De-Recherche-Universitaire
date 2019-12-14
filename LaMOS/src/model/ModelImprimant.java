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


public class ModelImprimant {
    
        public  String getSelection(){
           return "SELECT idEquipement,marqequi,prix,date,etatimpri"
                     + " FROM equipement where typeconso='' AND  etatordi=''";
            }
    public String getajoutordi(){
        return "INSERT INTO `equipement-lamos`.`equipement`"
                          + " (`typeconso`, `marqequi`, `marqordi`, `prix`, `date`, `etatordi`, `etatimpri`) "
                          + "VALUES ('', ?,'', ?, ?, '', ?)";
    }
     
    public  String getmodi(){
    return    "UPDATE equipement SET "
                               + " marqequi = ?, prix = ?, date = ?, etatimpri=?";
                               
    }
    public  String getsupp(){
        return "delete from equipement where marqequi=?";
    
    }
    
     public String getrech(String rech) {
       return "SELECT idEquipement,marqequi,prix,date,etatimpri"
                     + " FROM equipement where typeconso='' AND  etatordi='' and marqequi like '" +rech+ "%'"; 
    }
    
}
