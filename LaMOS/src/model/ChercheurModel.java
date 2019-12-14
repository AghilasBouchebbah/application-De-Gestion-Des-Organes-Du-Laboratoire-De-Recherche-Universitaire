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
package model;


public class ChercheurModel {
     public String getrechrche(String txt) {
        return "Select * from chercheur where nomcher like'" + txt + "%'";
    }
    
     
    public String getUpdate="update chercheur set nomcher= ?,prencher= ?,typecher= ?,gradecher= ?,diplocher= ?,speciacher= ? where idChercheur= ?";

  public String getInsert="insert into chercheur(nomcher,prencher,typecher,gradecher,diplocher,speciacher,idGr,idEq) VALUES(?,?,?,?,?,?,?,?)";

  public String getDelete(Integer txt) {
       return "delete from chercheur where idChercheur='"+txt+"'";
    }
 public String getUpdate2="update chercheur set typecher= ?, tel= ?,email= ? where idChercheur= ? and idEq=? and idGr=?"; 
  public String getUpdate3="update chercheur set typecher= ? tel= ?,email= ? where idChercheur= ?  "; 
}
