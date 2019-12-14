/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BOUCHEBAH
 */
public class GroupeModel {
      public String getrechrche(String txt) {
        return "Select * from equipe where nomequipe like'" + txt + "%'";
    }
    
     
    public String getUpdate="update groupe set intitgroup= ?,acrogroup= ? where idGroupe= ? and idEquipe= ?";

  public String getInsert="insert into groupe(intitgroup,acrogroup,idEquipe) VALUES(?,?,?)";

  public String getDelete(Integer txt) {
       return "delete from groupe where idGroupe='"+txt+"'";
    }
    
    
}
