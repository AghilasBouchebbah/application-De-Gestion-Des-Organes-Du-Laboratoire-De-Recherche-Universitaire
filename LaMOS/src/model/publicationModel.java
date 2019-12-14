package model;
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
public class publicationModel {
    
     public String getrechrche(String txt) {
        return "Select * from publication where auteur like'" + txt + "%'";
    }
    
      public String getUpdate1="update publication set titre= ?,auteur= ?,type= ?,date= ? where idpub= ?";
    public String getUpdate="update publication set titre= ?,auteur= ?,type= ?,date= ?,image= ? where idpub= ?";

  public String getInsert="insert into publication(titre,auteur,type,date,image) VALUES(?,?,?,?,?)";

  public String getDelete(Integer txt) {
       return "delete from publication where idpub='"+txt+"'";
    }
 
}


