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


public class ParticipantModel {
    public String getsearchparticipant(String searchpart){
           return "SELECT M.*,p.* FROM manifestation M,partenaire P,participe pr WHERE M.typemanif LIKE '%"+searchpart+"%'and M.idManifestation=pr.idManif and P.idpartenaire=pr.idPart UNION SELECT M.*,p.* FROM manifestation M,partenaire P,participe pr where M.intitulémanif LIKE '%"+searchpart+"%' and M.idManifestation=pr.idManif and P.idpartenaire=pr.idPart UNION SELECT M.*,p.* FROM manifestation M,partenaire P,participe pr where P.typepart LIKE '%"+searchpart+"%'and M.idManifestation=pr.idManif and P.idpartenaire=pr.idPart UNION SELECT M.*,p.* FROM manifestation M,partenaire P,participe pr where P.intitpart LIKE '%"+searchpart+"%' and M.idManifestation=pr.idManif and P.idpartenaire=pr.idPart ";}
    public String getrefrechparticipant(){
      return  "SELECT M.*,p.* FROM manifestation M,partenaire P,participe pr WHERE M.idManifestation=pr.idManif AND P.idpartenaire=pr.idPart";

    }

}
