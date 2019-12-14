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

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;

import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Partenaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import java.io.InputStream;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import db.Connexion;
import fonctions.navigation;
import java.io.IOException;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import model.PartenaireModel;


public class Partenairecntrl implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList("Economique", "Social");

    @FXML
    public TableView<Partenaire> tabpart;
    @FXML
    private TableColumn<Partenaire, Date> col_date;
    ObservableList<Partenaire> part = FXCollections.observableArrayList();
    Connexion con = new Connexion();
    PartenaireModel model=new PartenaireModel();
    
    @FXML
    private TableColumn<Partenaire, Integer> col_id;
    @FXML
    private TableColumn<Partenaire, String> col_inti;
    @FXML
    private TableColumn<Partenaire, String> col_dom;
    @FXML
    private TableColumn<Partenaire, String> col_info;
    @FXML
    private TableColumn<Partenaire, String> col_type;
    @FXML
    private TableColumn<Partenaire, Double> col_tarif;
    @FXML
    private TableColumn<Partenaire, Image> col_logo;
    @FXML
    private Button bnt_ajou_p;
    @FXML
    private Button bnt_supp_p;
    @FXML
    private Button bnt_modi_p;
    @FXML
    private TextField txt_search_p;

   
    @FXML
    private Label lb_close_part;
    @FXML
    private AnchorPane partenaire;
  
    @FXML
    private ImageView image;
    private File file;
    FileChooser chooser;
    FileInputStream fis;
    
    
    String intitule;
        String domaine;
       String type ;
        String tarif;
        String date;
        String num;
    
    @FXML
    private TableColumn<Partenaire, String> col_url_image;
    @FXML
    private Label lb_inti_part;
    String t[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String e[] = {"com", "dz", "fr"};
    
    int i = 0, k = 4;
    @FXML
    private Button btn_ref;
    @FXML
    private AnchorPane blur;
    @FXML
    private StackPane caché1;
    @FXML
    private AnchorPane loadPane1;
    @FXML
    private JFXButton btn_exit;
   
    navigation nav=new navigation();
    /**/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        col_logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_inti.setCellValueFactory(new PropertyValueFactory<>("inti"));
        col_dom.setCellValueFactory(new PropertyValueFactory<>("dom"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_info.setCellValueFactory(new PropertyValueFactory<>("info"));
        col_tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_url_image.setCellValueFactory(new PropertyValueFactory<>("url_image"));
     
        ref();
//       select();
        actionrechtxt();
        mousered();
        btn_exit.setVisible(false);
        
    }

    
    
    
     private void clearParameter(){
        intitule="";
        domaine="";
        type="";
        date="";
        tarif="";
        num="";
    }

    public void ref() {
        part.clear();
        con.db();

        try {
            con.res = con.stat.executeQuery(model.getAllselect());
            while (con.res.next()) {
       
                part.add(new Partenaire(con.res.getBytes("logo"), con.res.getString("intitpart"), con.res.getString("dompart"), con.res.getString("typepart"), con.res.getString("infopart"), con.res.getDouble("tarifpart"), con.res.getString("dateajoutpart")));

            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        tabpart.setItems(part);
    }
    @FXML
    private void actionajout(ActionEvent event) throws IOException {

     
       FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/ajouterPartenairePart.fxml"));
        AnchorPane pane = Loader.load();
      
           
              blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
        btn_exit.setVisible(true);
          loadPane1.getChildren().setAll(pane);
       
    }
    
    @FXML
    private void actionsupp(ActionEvent event) {

        con.db();
         if (tabpart.getSelectionModel().isEmpty()) {
             Alert alert=new Alert(Alert.AlertType.WARNING);
             alert.setHeaderText("");
             alert.setContentText("Veuillez Selectionné un partenaire");
             alert.showAndWait();
        } else {
        PreparedStatement pst;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de supprimer?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            Partenaire p = (Partenaire) tabpart.getSelectionModel().getSelectedItem();

            try {

                
                pst = con.con.prepareStatement(model.getdeletepart());
                pst.setString(1, p.getInti());
                pst.executeUpdate();
                pst.close();
                ref();
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else {
            alert.close();
        }
         }
    }
    @FXML
    private void actionmodi(ActionEvent event) throws IOException {
       
         if (tabpart.getSelectionModel().isEmpty()) {
             Alert alert=new Alert(Alert.AlertType.WARNING);
             alert.setHeaderText("");
             alert.setContentText("Veuillez Selectionné un partenaire");
             alert.showAndWait();
        } else {
      
                   
            

        Partenaire p = tabpart.getSelectionModel().getSelectedItem();
        
      String intitule=p.getInti();
        String domaine=p.getDom();
       String type= p.getType();
        String tarif=p.getTarif() + "";
        String date=p.getDate();
//dt_ajou_p.setValue(LocalDate.parse(p.getDate()));
        String num=p.getInfo();
        
                  
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/modifierPartenaire.fxml"));
        
          blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
        AnchorPane pane = Loader.load();
        ModifierPartenaire mod=Loader.getController();
        mod.setData(intitule, domaine, type, tarif, date, num);
       
          
        try {
           
            btn_exit.setVisible(true);
          loadPane1.getChildren().setAll(pane);
       
        } catch (Exception e) {
         nav.   showAlert(Alert.AlertType.WARNING, "Warnnig", null, "Veuillez selectionner une manifestation scientifique !");

        }
         }
          }
   



    public boolean verifier_mobile(String text) {
       

        if ((text.substring(0, 1).equals("0") && (text.length() == 10 || text.length() == 9))) {
            if (text.length() == 10) {
                if (text.substring(1, 2).equals("5") || text.substring(1, 2).equals("6") || text.substring(1, 2).equals("7")) {
                    while (text.substring(3, k).equals(t[i])) {
                        i++;
                        k++;
                    }

                    return true;
                } else {
                    System.err.println("po de 5,6,7");
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean verifier_fix(String text ) {
      
        if (text.substring(0,3).equals("034")) {
            if (text.length() == 9) {
                while (text.substring(3, k).equals(t[i])) {
                    i++;
                    k++;
                    
                }
                System.err.println("1");
                return true;
                
            } else {
                System.err.println("2");
                return false;
            }
        } else {
            System.err.println("3");
            return false;
        }
    }

    public boolean verifier_email(String text) {
        
        if ((text.substring(0, 1).equals("0") && (text.length() > 10 || text.length() > 9))) {
            return false;
        } else if (text.contains("@")) {
            if (text.substring(0, 1).matches("[a-zA-Z]") == true) {
                int o = 1;
                int j = 0;
                while (text.substring(o, o + 1).matches("[a-zA-Z]") == true || text.substring(o, o + 1).equals(t[j])) {
                    o++;
                    j++;

                }
                if (text.substring(o, o + 1).equals("@")) {
                    o++;
                    if (text.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                        o++;
                        while (text.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                            o++;
                        }
                        if (text.substring(o, o + 1).equals(".")) {
                            o++;
                            int h = 0;
                            if (text.substring(o).length() > 2) {
                                while (text.substring(o).equals(e[h])) {
                                    h++;
                                }
                               return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                } else {
                    return false;

                }

            } else {
                System.out.println("taille depasse ou po num po email ");
              //  txt_num_email_p.setStyle("-fx-text-content:e-mail ou numero de téléphone;-fx-text-color:red");
            }
            return false;
        } else {
           // txt_num_email_p.setStyle("-fx-text:e-mail ou numero de téléphone;-fx-text-color:red");
            return false;
        }
    }

    
            
   
    @FXML
    private void close_p(MouseEvent event) {
      
    }
    public void mousered(){
        lb_close_part.setOnMouseExited(e ->{
        lb_close_part.setStyle("");});
        lb_close_part.setOnMouseMoved(e ->{
        lb_close_part.setStyle("-fx-text-fill:#d60000;");});
        
    }
    public void select (MouseEvent event) throws SQLException {
       
             //tabpart.setOnMouseClicked((MouseEvent event) -> {
            Partenaire d = tabpart.getSelectionModel().getSelectedItem();
            try {

                PreparedStatement pst;
                pst = con.con.prepareStatement(model.getselectpart());
                pst.setString(1,d.getInti());
                con.res = pst.executeQuery();
                if (con.res.next()) {
                    lb_inti_part.setText(d.getInti().toUpperCase());
                    InputStream is = con.res.getBinaryStream("logo");
                    Image imagee = new Image(is);
                    image.setImage(imagee);
                }
            } catch (Exception ex) {
                System.out.println("erreur dans la methode select "+ex);
            }
       // }
    }

        
       
    
    
  
    
    private void actionrechtxt() {
        
        txt_search_p.setOnKeyReleased(e1->{
        if (txt_search_p.getText().equals("")) {
            ref();
        } else {
            part.clear();
            String rech = txt_search_p.getText().toLowerCase();
           
            try {
                con.res = con.stat.executeQuery(model.getrecherchepart(rech));
                while (con.res.next()) {
                    part.add(new Partenaire(con.res.getBytes("logo"), con.res.getString("intitpart"), con.res.getString("dompart"), con.res.getString("typepart"), con.res.getString("infopart"), con.res.getDouble("tarifpart"), con.res.getString("dateajoutpart")));
                }
            } catch (Exception e) {
                System.out.println("doc" + e);
            }
            tabpart.setItems(part);
        }
               });
    }

 
    @FXML
    private void actionref(ActionEvent event) {
        ref();
    }

    @FXML
    private void onDefault(MouseEvent event) {
                btn_exit.setStyle("-fx-background-color:   ececec;");

    }

    @FXML
    private void onHover(MouseEvent event) {
                      btn_exit.setStyle("-fx-background-color: red;");

    }

    @FXML
    private void exitClick(ActionEvent event) {
          ref();
        blur.setEffect(null);
        btn_exit.setVisible(false);
        new FadeOutLeftTransition(caché1).play();
        
        clearParameter();
        
    }

    @FXML
    private void rowClick(MouseEvent event) throws SQLException {
       
             //tabpart.setOnMouseClicked((MouseEvent event) -> {
            Partenaire d = tabpart.getSelectionModel().getSelectedItem();
            try {

                PreparedStatement pst;
                pst = con.con.prepareStatement(model.getselectpart());
                pst.setString(1,d.getInti());
                con.res = pst.executeQuery();
                if (con.res.next()) {
                    lb_inti_part.setText(d.getInti().toUpperCase());
                    if(con.res.getBinaryStream("logo")!=null){
                    InputStream is = con.res.getBinaryStream("logo");
                    Image imagee = new Image(is);
                    image.setImage(imagee);
                }else
                    {image.setImage(null);}
                
                
                }
            } catch (Exception ex) {
                System.out.println("erreur dans la methode rowclick "+ex);
            }
       // }
    }

    
}
