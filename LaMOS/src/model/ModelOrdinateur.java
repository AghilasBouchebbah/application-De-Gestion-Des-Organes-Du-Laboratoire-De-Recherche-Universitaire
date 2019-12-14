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


public class ModelOrdinateur {
    public  String getselection(){
    return "SELECT idEquipement,marqordi,prix,date,etatordi"
                     + " FROM equipement where typeconso='' AND  etatimpri=''";
            }
    public String getajoutordi(){
        return "INSERT INTO `equipement-lamos`.`equipement`"
                          + " (`typeconso`, `marqequi`, `marqordi`, `prix`, `date`, `etatordi`, `etatimpri`) "
                          + "VALUES ('', '',?, ?, ?, ?, '')";
    }
     
    public  String getmodi(){
    return "UPDATE equipement SET "
                               + " marqordi = ?, prix = ?, date = ?, etatordi=? "
                               ;
    }
    public  String getsupp(){
        return "delete from equipement where idEquipement=?";
    
    }
    
     public String getrech(String rech) {
       return  "SELECT idEquipement,marqordi,prix,date,etatordi "
                     + "FROM equipement where  etatimpri='' and typeconso =''  and  marqordi like '" +rech+ "%'"; 
    }
   
    
}
