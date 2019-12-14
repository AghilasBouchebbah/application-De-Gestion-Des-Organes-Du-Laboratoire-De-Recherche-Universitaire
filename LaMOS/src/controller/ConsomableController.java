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

package controller;

import model.lConsommableTable;
import model.ConnexionEQ;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import model.Modelconsommable;
import sun.util.calendar.BaseCalendar;

/**
 * FXML Controller class
 *
 *
 */
public class ConsomableController implements Initializable {

    
    @FXML
    private JFXTextField TxtfPrixConso;
    @FXML
    private JFXTextField txtrecherche;
    @FXML
    private JFXDatePicker TxtfdatAchatConso;
   
    @FXML
    private TableView<lConsommableTable> tablevConso;
    @FXML
    private TableColumn<lConsommableTable, Integer> IdConsommable;
    @FXML
    private TableColumn<lConsommableTable, String> collmrqConso;
    @FXML
    private TableColumn<lConsommableTable, String> colltypeConsom;
    @FXML
    private TableColumn<lConsommableTable, Double> collPrixConsom;
    @FXML
    private TableColumn<lConsommableTable, Date> collDatAchatConsom;
   
    
   

     private ObservableList<lConsommableTable> data;
    
    ConnexionEQ con=new  ConnexionEQ();

    PreparedStatement pst;
   public Statement stat;
     public  ResultSet res;
    @FXML
    private JFXButton buttonAjouCosm;
    @FXML
    private JFXButton buttonModCosm;
    @FXML
    private JFXButton buttonSuppCosm;
    @FXML
    private Label labconsomclose;
    @FXML
    private JFXTextField TxtmarqConso;
    @FXML
    private JFXComboBox<String> ComboTypeConsom;
    ObservableList<String> list = FXCollections.observableArrayList("stylo", "papiers","craies");
    @FXML
    private JFXButton VIDER;
    Modelconsommable o=new Modelconsommable();
    
    
   
    
    
     

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
          ComboTypeConsom.setItems(list);
        
         //.setItems(list);
         
       
        
         try {
             stat = con.obtenirconx().createStatement();
             res = stat.executeQuery(o.getSelection());
            data=FXCollections.observableArrayList();
           while(res.next()){
            Integer idEquipement=res.getInt(1);
             String typeconso=res.getString(2);
            String marqequi=res.getString(3);
            Double prix=res.getDouble(4);
            String date=res.getString(5);
           
                lConsommableTable model=new lConsommableTable(idEquipement, typeconso,marqequi, prix, date);
        
         data.add(model);
          
        }
            IdConsommable.setCellValueFactory(new PropertyValueFactory<>("idEquipement"));
              colltypeConsom.setCellValueFactory(new PropertyValueFactory<>("typeconso"));
             collmrqConso.setCellValueFactory(new PropertyValueFactory<>("marqequi"));
               collPrixConsom.setCellValueFactory(new PropertyValueFactory<>("prix"));
                collDatAchatConsom.setCellValueFactory(new PropertyValueFactory<>("date"));
            
          tablevConso.setItems(data);
            
           
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
         onkey();
         
     } 
    

    @FXML
    public void AjouterConsom(ActionEvent event) {
        if ( TxtmarqConso.getText().equals("")||TxtfPrixConso.getText().equals("")
                ||TxtfdatAchatConso.getValue().equals("")) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplire touS les champs SVP!!");
              
            alert.show();
            
               } else {
                     String marque=TxtmarqConso.getText();
                     String prix=TxtfPrixConso.getText();
                     String type=ComboTypeConsom.getSelectionModel().getSelectedItem().toString();
                     SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                     String date = dateFormat.format(Date.valueOf(TxtfdatAchatConso.getValue()));
        
        
                try {
       
                   PreparedStatement pst;
                   
                 
            
            pst =  con.obtenirconx().prepareStatement(o.getajoutordi());
            
                 pst.setString(1,type );
                 pst.setString(2,marque );
                 pst.setString(3,prix );
                 pst.setString(4, date);
                 pst.executeUpdate();
                 pst.close();
                 ref();
               
         
                }   catch (SQLException ex) {
                         Logger.getLogger(ConsomableController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                     }
        }}
              
  
    
    @FXML
    private void modifierConsom(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de modification");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            
       String type= ComboTypeConsom.getSelectionModel().getSelectedItem().toString();
       String marque= TxtmarqConso.getText();
       String prix=TxtfPrixConso.getText();
       SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(Date.valueOf(TxtfdatAchatConso.getValue()));
        
        try {
                lConsommableTable p = tablevConso.getSelectionModel().getSelectedItem();
                   PreparedStatement pst = con.obtenirconx().prepareStatement(o.getmodi() );
                 pst.setString(1,type );
                 pst.setString(2,marque );
                 pst.setString(3,prix );
                 pst.setString(4, date);
                 pst.executeUpdate();
                 ref();
                 clear();
         
          
            } catch (SQLException ex) {
                System.err.println("erreur dans la modification" + ex);
            }
         } else {
            alert.close();
        }
        
        
                
        

    }
    
    
    public void ref() {
        data.clear();
        try {
             stat = con.obtenirconx().createStatement();
             res = stat.executeQuery(o.getSelection());
            data=FXCollections.observableArrayList();
     while(res.next()){
        lConsommableTable model=new lConsommableTable( 
            res.getInt("idEquipement"),
            res.getString("typeconso"),
            res.getString("marqequi"),
            res.getDouble("prix"),
           res.getString("date"));
        
         data.add(model);
         tablevConso.setItems(data);
                
     } 
     clear();
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        tablevConso.setItems(data);
    
    }
    
        
        
       
       
        
    

    @FXML
    private void supprimeCosom(ActionEvent event) {
        
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de Supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {
         ref();
                
                lConsommableTable p = tablevConso.getSelectionModel().getSelectedItem();
                
                pst = con.obtenirconx().prepareStatement("delete from equipement where typeconso = ?");
                 pst.setString(1, p.getTypeconso());
                pst.executeUpdate();
                pst.close();
                ref();
              clear();
            } catch (SQLException ex) {
                System.err.println("erreur modi" + ex);
            }
            
         } else {
            alert.close();
        }
    }
    
     public void clear(){
        
        ComboTypeConsom.setValue("");
        txtrecherche.clear();
        TxtfdatAchatConso.setValue(null);
        TxtmarqConso.clear();
        TxtfPrixConso.clear();
        
     }
    

 @FXML
    private void selectionner(MouseEvent event) {
        lConsommableTable c = (lConsommableTable) tablevConso.getSelectionModel().getSelectedItem();
        ComboTypeConsom.setValue(c.getTypeconso());
        TxtmarqConso.setText(c.getMarqequi());
        TxtfPrixConso.setText(c.getPrix()+"");
        TxtfdatAchatConso.setValue(LocalDate.parse(c.getDate()));
        
        }
    @FXML
    private void setDfClose(MouseEvent event) {
         labconsomclose.setStyle("-fx-background-color: #f4eeee;");
    }

    @FXML
    private void SetCloseInterf(MouseEvent event) {
         labconsomclose.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void closeInterf(MouseEvent event) {
         Stage stage = (Stage) labconsomclose.getScene().getWindow();
        stage.close();
    }

   
    
    
    
    
    public void onkey(){
      txtrecherche.setOnKeyReleased(e2 ->{
        if (txtrecherche.getSelectedText()=="") {
          
             //   ref();
           
            
        }else{
       
            data.clear();
        String rech = txtrecherche.getText().toLowerCase();
        
        try {
            res = con.obtenirconx().createStatement().executeQuery(o.getrech(rech));
            while (res.next()) {
                lConsommableTable model=new lConsommableTable( 
           res.getInt("idEquipement"),
            res.getString("typeconso"),
            res.getString("marqequi"),
            res.getDouble("prix"),
           res.getString("date"));
        
         data.add(model);
         tablevConso.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
         
        }
              });
    }

    @FXML
    private void recheaction(ActionEvent event) {
        
         data.clear();
        String rech = txtrecherche.getText().toLowerCase();
       
        try {
            res = con.obtenirconx().createStatement().executeQuery(o.getrech(rech));
            while (res.next()) {
                lConsommableTable model=new lConsommableTable( 
            res.getInt("idEquipement"),
            res.getString("typeconso"),
            res.getString("marqequi"),
            res.getDouble("prix"),
           res.getString("date"));
        
         data.add(model);
         tablevConso.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
    }

    @FXML
    private void VIDER(ActionEvent event) {
         ComboTypeConsom.setValue("");
        txtrecherche.clear();
        TxtfdatAchatConso.setValue(null);
        TxtmarqConso.clear();
        TxtfPrixConso.clear();
    }

  
    
}
