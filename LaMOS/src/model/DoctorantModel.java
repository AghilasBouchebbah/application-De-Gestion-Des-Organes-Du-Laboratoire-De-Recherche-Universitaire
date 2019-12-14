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
public class DoctorantModel {
    
     public String getref() {
        return "SELECT idChercheur,nomcher,prencher,intithes,respothes,domthes,datesouthes FROM chercheur where typecher='doctorant'";
    }

    public String getinserdoctorant() {
        return "insert into chercheur(nomcher,prencher,intithes,respothes,domthes,datesouthes,typecher) VALUES(?,?,?,'" + "Mr " + "'?,?,?,?)";
    }

    public String getsuppdoctorant() {
        return "update  chercheur set typecher=? where idChercheur=?";
    }

    public String getmodifier() {
        return "update chercheur  set nomcher=?,prencher=?,intithes=?,domthes=?,respothes=?,datesouthes=? where  idChercheur =?";
    }
    public String getmodifier1() {
        return "update chercheur  set intithes=?,domthes=?,respothes=?,datesouthes=? where  idChercheur =?";
    }

    public String getrechrche(String txt) {
        return "Select * from chercheur where nomcher like'" + txt + "%'";
    }
    
}
