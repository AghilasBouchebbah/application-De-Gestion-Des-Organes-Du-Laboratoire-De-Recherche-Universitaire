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

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author PR'O'PC
 */
public class Model_requete {

    Connection con = null;
    public PreparedStatement pst = null;

    public String getinserper() {
        return "insert into personnel(typeperso,nomperso,prenomperso,dateemboperso) values(?,?,?,?)";
    }

    public String getupdateper() {
        return "update personnel Set typeperso=?,nomperso=?,prenomperso=?,dateemboperso=?  where  idpersonnel = ?";
    }

    public String getdeleteper(int idp) {

        return "delete from personnel where idpersonnel='" + idp + "'";
    }

    public String getAllselectper() {
        return "SELECT * FROM personnel";
    }

    public String getrechercheper(String txt) {
        return "Select * from personnel where typeperso like'" + txt + "%'";
    }

}
