
package model;

import javax.swing.JOptionPane;
import java.sql.*;


public class ConnexionEQ {
     Connection c;

    public ConnexionEQ() {
        
        
          try {
            Class.forName("com.mysql.jdbc.Driver");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "echec de cnx au serveur"+e.getMessage());
        }
        
        try {
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/lamos","root","");//@;psw.user
           
            
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "echec a la cnx au bdd"+e.getMessage());
            
        }
        
    }
    public  Connection obtenirconx() {
         return c;
    }
    
    
    
    
}
