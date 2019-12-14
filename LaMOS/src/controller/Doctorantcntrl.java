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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FilterReader;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Doctorant;
import db.Connexion;
import fonctions.navigation;
import model.DoctorantModel;


public class Doctorantcntrl implements Initializable {
////declaration

    ObservableList<Doctorant> doc = FXCollections.observableArrayList();
    Connexion con = new Connexion();
   DoctorantModel model=new DoctorantModel();
    navigation nav=new navigation();
  
    @FXML
    private TableView<Doctorant> tabdoc;
    @FXML
    private TableColumn<Doctorant, String> col_id_d;
    @FXML
    private TableColumn<Doctorant, String> col_nom_d;
    private TableColumn<Doctorant, String> col_pre_d;
    private TableColumn<Doctorant, String> col_age_d;
    
    @FXML
    private TableColumn<Doctorant, String> col_inti_d;
    @FXML
    private TableColumn<Doctorant, String> col_dom_d;
    @FXML
    private TableColumn<Doctorant, String> col_resp_d;
    @FXML
    private TableColumn<Doctorant, String> col_date_sou_d;
    @FXML
    private JFXTextField txt_nom_d;
    @FXML
    private JFXTextField txt_pren_d;
    private JFXTextField txt_age_d;
    @FXML
    private JFXTextField txt_inti_d;
    @FXML
    private JFXTextField txt_dom_d;
    @FXML
    private JFXTextField txt_respo_d;
    private JFXDatePicker dt_presen_d;
    @FXML
    private JFXDatePicker dt_sout_d;
    @FXML
    private Button bnt_ajou_d;
    @FXML
    private Button bnt_supp_d;
    @FXML
    private Button bnt_annu_d;
    @FXML
    private Button bnt_modi_d;
    private JFXRadioButton rd_fem_d;
    private JFXRadioButton rd_mas_d;
    @FXML
    private TextField txt_rech_d;
    @FXML
    private AnchorPane DOCTORANT;
    
    String  nomcher,prencher,intithes,respothes,domthes,datesouthes;
   Integer idChercheur;

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabdoc.setEditable(true);

        /////////////////Load//////////////////////////////////////////
       

        loadTable();
        //select();
        action_rech_txt();
        
        

    }

    /////////////////////////methode///////////////////////////////////
    
    private void clear() {
        txt_nom_d.requestFocus();
        txt_nom_d.setText("");
        txt_pren_d.setText("");
      
        txt_inti_d.setText("");
        txt_respo_d.setText("");
        txt_dom_d.setText("");
        
        dt_sout_d.setValue(null);
       
    }

    private void loadTable() {
        doc.clear();
        con.db();

        try {
            con.res = con.stat.executeQuery("SELECT idChercheur,nomcher,prencher,intithes,respothes,domthes,datesouthes FROM chercheur where typecher='doctorant'");
            while (con.res.next()) {
                doc.add(new Doctorant(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6), con.res.getString(7)));

            }
             col_id_d.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom_d.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
   
       
        col_resp_d.setCellValueFactory(new PropertyValueFactory<>("respo"));
        col_dom_d.setCellValueFactory(new PropertyValueFactory<>("dom"));
        col_inti_d.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        
        col_date_sou_d.setCellValueFactory(new PropertyValueFactory<>("datesou"));
       
            tabdoc.setItems(doc);
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
    }

//////////////////////////////annuler//////////////////////////////:
    @FXML
    private void actionannuler(ActionEvent event) {
        clear();
        txt_nom_d.setStyle("-jfx-unfocus-color:black;");
        txt_pren_d.setStyle("-jfx-unfocus-color:black;");
        txt_inti_d.setStyle("-jfx-unfocus-color:black;");
        
        txt_respo_d.setStyle("-jfx-unfocus-color:black;");

    }

///////////////////AJOUT/////////////////////////////////////////////
    int i = 1, j = 0;

    @FXML
    private void actionajouter(ActionEvent event) {
        con.db();
        PreparedStatement pst;
        
         if (tabdoc.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setContentText("Veuillez sélectionner un doctorant");
            alert.showAndWait();
        } else 

        if (txt_nom_d.getText().equals("") || txt_dom_d.getText().equals("") || txt_pren_d.getText().equals("")) {
            if (txt_nom_d.getText().equals("")) {
                txt_nom_d.setStyle("-jfx-unfocus-color:red;");
            }
            if (txt_inti_d.getText().equals("")) {
                txt_inti_d.setStyle("-jfx-unfocus-color:red;");
            }
            if (txt_pren_d.getText().equals("")) {
                txt_pren_d.setStyle("-jfx-unfocus-color:red;");
            }
            
            if (txt_respo_d.getText().equals("")) {
                txt_respo_d.setStyle("-jfx-unfocus-color:red;");
            }

        

        } else {
            /////forme de la date/////////////
           
            ////recupperer les valeur des champs//////////
           
            String date_sout = dt_sout_d.getValue().toString();
            
            String nom = txt_nom_d.getText();
            String pre = txt_pren_d.getText();
            String intitule = txt_inti_d.getText();
            String dom = txt_dom_d.getText();
            String respo = txt_respo_d.getText();
           
                try {
                     pst = con.con.prepareStatement(model.getinserdoctorant());
                    pst.setString(1, nom);
                    pst.setString(2, pre);
                  
                    pst.setString(3, intitule);
                    pst.setString(4, respo);
                    pst.setString(5, dom);
                    
                   
                    pst.setString(6, date_sout);
                  pst.setString(7, "Doctorant");
                    pst.executeUpdate();
                     nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"doctorant ajouté avec succes");
                 loadTable();

        
                 clear();
                } catch (Exception e) {
                    System.err.println("erreur d'insertion:  " + e);
                }
            } 
               
            

         
    }
/////////////////////////////supp/////////////////////////////////////////////

    @FXML
    private void actionsupprimer(ActionEvent event) {
        con.db();
        PreparedStatement pst;
        
        int id = 0;
         if (tabdoc.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setContentText("Veuillez Selectionné un Doctorant");
            alert.showAndWait();
        } else {
        
      

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de supprimer ce doctorant?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            Doctorant p = (Doctorant) tabdoc.getSelectionModel().getSelectedItem();

            try {

              pst = con.con.prepareStatement(model.getsuppdoctorant());

                    
                    
                 
                    pst.setString(1, "chercheur simple");
                    
                   pst.setInt(2,idChercheur);
                    pst.executeUpdate();
                pst.close();
                txt_nom_d.setText("");
                txt_pren_d.setText("");
                 
                 txt_inti_d.setText("");
                  txt_dom_d.setText("");
                  txt_respo_d.setText("");
                    dt_sout_d.setValue(null);
                 
            } catch (SQLException e) {
                System.out.println( "erreur suppression doctorant "+e);
            }

             nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"doctorant supprimé avec succes");

        } else {
            alert.close();
        }
        loadTable();

    }}

////////////////////////////selectionner//////////////////////////
  
///////////////////////////////modifier//////////////////////////

    @FXML
    private void actionmodi(ActionEvent event) {
        
        con.db();
        PreparedStatement pst;
        
        if (tabdoc.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setContentText("Veuillez Selectionné un Doctorant");
            alert.showAndWait();
        } else {
           
                Doctorant d = tabdoc.getSelectionModel().getSelectedItem();

                try {
                    
                    pst = con.con.prepareStatement(model.getmodifier());

                    
                    pst.setString(1, txt_nom_d.getText());
                    pst.setString(2, txt_pren_d.getText());
                 
                    pst.setString(3, txt_inti_d.getText());
                    pst.setString(4, txt_dom_d.getText());
                    pst.setString(5, txt_respo_d.getText());
                    
                    pst.setString(6, dt_sout_d.getValue().toString());
                   pst.setInt(7, d.getId());
                    pst.executeUpdate();
                    nav.showAlert(Alert.AlertType.INFORMATION, "succes", ""," données modifiées avec succes");
                     loadTable();
                    
                    
                } catch (Exception e) {
                    System.err.println("erreur modi" + e);
                }
               
                   }
    }

    

    private void action_rech_txt() {
        con.db();
        PreparedStatement pst;
        txt_rech_d.setOnKeyReleased(e1 -> {
            if (txt_rech_d.getText().equals("")) {
                loadTable();
            } else {
                doc.clear();
                String rech = txt_rech_d.getText().toLowerCase();

                try {
                    con.res= con.stat.executeQuery(model.getrechrche(rech));
                    while (con.res.next()) {
                        doc.add(new Doctorant(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6), con.res.getString(7)));

                    }
                } catch (Exception e) {
                    System.out.println("doc" + e);
                }
                tabdoc.setItems(doc);
            }
        });
    }

    @FXML
    private void rowClick(MouseEvent event) {
          Doctorant d = tabdoc.getSelectionModel().getSelectedItem();
           
             con.db();
      idChercheur=d.getId();
       
            nomcher = d.getNom();
        intithes = d.getIntitule();
        domthes=d.getDom();
        respothes=d.getRespo();
        datesouthes=d.getDatesou();
         
                if(datesouthes==null &  intithes==null & respothes==null & domthes==null){txt_inti_d.setText("");txt_dom_d.setText("");txt_respo_d.setText("");dt_sout_d.setValue(null);}
                else if(datesouthes!=null &  intithes!=null & respothes!=null & domthes!=null){
                    txt_inti_d.setText(intithes);
            txt_dom_d.setText(domthes);
            txt_respo_d.setText(respothes);
             dt_sout_d.setValue(LocalDate.parse(datesouthes));}
        
            try {
                con.res=con.stat.executeQuery("select nomcher,prencher from chercheur where idChercheur='"+idChercheur+"'");
            if(con.res.next()){
        String nomm =con.res.getString(1);
       String prenomm =con.res.getString(2);
               
        txt_nom_d.setText(nomm);
        txt_pren_d.setText(prenomm);}
            
            } catch (SQLException ex) {
                Logger.getLogger(Doctorantcntrl.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
    }

    

}
