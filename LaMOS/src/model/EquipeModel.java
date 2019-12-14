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

/**
 *
 * @author BOUCHEBAH
 */
public class EquipeModel {
      public String getrechrche(String txt) {
        return "Select * from equipe where nomequipe like'" + txt + "%'";
    }
    
     
    public String getUpdate="update equipe set nomequip= ?,acroequip= ?,datecreat= ? where idEquipe= ?";

  public String getInsert="insert into equipe(nomequip,acroequip,datecreat) VALUES(?,?,?)";

  public String getDelete(Integer txt) {
       return "delete from equipe where idEquipe='"+txt+"'";
    }
    
}
