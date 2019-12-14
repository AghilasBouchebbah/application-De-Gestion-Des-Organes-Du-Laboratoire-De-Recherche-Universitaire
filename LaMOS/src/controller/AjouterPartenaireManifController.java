/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ListePartenaireManifModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import fonctions.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AjouterPartenaireManifController implements Initializable {

    @FXML
    private JFXComboBox typepar;
    ObservableList<String> Tpart = FXCollections.observableArrayList("Economique", "Sociale");

    @FXML
    private JFXTextField intitule;
    @FXML
    private JFXTextField domaine;
    @FXML
    private JFXDatePicker datecreat;
    @FXML
    private JFXTextField infospart;
    @FXML
    private JFXTextField tarif;
    ListePartenaireManifModel model = new ListePartenaireManifModel();
    @FXML
    private JFXTextField idPart;
    
    
    @FXML
    private Label labelajp;
    
    navigation nav=new navigation();
    String t[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String e[] = {"com", "dz", "fr"};

    int i = 0, k = 4;
    @FXML
    private Label labelerreur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typepar.setItems(Tpart);

    }

    public void textfieldClearpartenaire() {
        intitule.setText(" ");
        domaine.setText(" ");
        ((TextField) datecreat.getEditor()).setText(" ");
        infospart.setText(" ");
        tarif.setText(" ");
        setTypePart();
        intitule.requestFocus();
        labelerreur.setVisible(false);
        infospart.setStyle("-fx-text-content:erreur;-jfx-unfocus-color:Black;");

    }

    private void setTypePart() {
        typepar.setValue("Type_Partenaire");
        typepar.setItems(Tpart);
    }

    public boolean verifier_mobile() {
        String txt = infospart.getText();

        if ((txt.substring(0, 1).equals("0") && (txt.length() == 10 || txt.length() == 9))) {
            if (txt.length() == 10) {
                if (txt.substring(1, 2).equals("5") || txt.substring(1, 2).equals("6") || txt.substring(1, 2).equals("7")) {
                    while (txt.substring(3, k).equals(t[i])) {
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

    public boolean verifier_fix() {
        String txt = infospart.getText();
        if (txt.substring(0, 3).equals("034")) {
            if (txt.length() == 9) {
                while (txt.substring(3, k).equals(t[i])) {
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

    public boolean verifier_email() {
        String txt = infospart.getText();
        if ((txt.substring(0, 1).equals("0") && (txt.length() > 10 || txt.length() > 9))) {
            return false;
        } else if (txt.contains("@")) {
            if (txt.substring(0, 1).matches("[a-zA-Z]") == true) {
                int o = 1;
                int j = 0;
                while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true || txt.substring(o, o + 1).equals(t[j])) {
                    o++;
                    j++;

                }
                if (txt.substring(o, o + 1).equals("@")) {
                    o++;
                    if (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                        o++;
                        while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                            o++;
                        }
                        if (txt.substring(o, o + 1).equals(".")) {
                            o++;
                            int h = 0;
                            if (txt.substring(o).length() > 2) {
                                while (txt.substring(o).equals(e[h])) {
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
                infospart.setStyle("-fx-text-content:e-mail ou numero de téléphone;-fx-text-color:red");
            }
            return false;
        } else {
            infospart.setStyle("-fx-text:e-mail ou numero de téléphone;-fx-text-color:red");
            return false;
        }
    }

    @FXML
    private void Type(ActionEvent event) {
        if (typepar.getValue() == "Economique") {
            tarif.setVisible(true);
        } else {
            tarif.setVisible(false);
        }
    }

//********ajouterpartenaire***********s
    
    @FXML
    private void handajout(ActionEvent event) {
          if (tarif.getText().equals("") ||intitule.getText().equals("") || domaine.getText().equals("") || infospart.getText().equals("")) {
          nav.  showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
        } else {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = sdf.format(Date.valueOf(datecreat.getValue()));
        if (!verifier_mobile() && !verifier_fix() && !verifier_email()) {
            infospart.setStyle("-fx-text-content:erreur;-jfx-unfocus-color:red;");
            labelerreur.setVisible(true);
        } else if (typepar.getValue() == "Economique") {
            model.insertpartenaire(typepar.getSelectionModel().getSelectedItem().toString(), intitule.getText(), domaine.getText(), dateText, infospart.getText(), tarif.getText());
            textfieldClearpartenaire();
        } else {
            model.insertSpartenaire(typepar.getSelectionModel().getSelectedItem().toString(), intitule.getText(), domaine.getText(), dateText, infospart.getText());
            textfieldClearpartenaire();
        }

    }}
///******closefenetreajoutpartenaire**************

    @FXML
    private void CloseAjPart(MouseEvent event) {
        Stage stage = (Stage) labelajp.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SetCloseAjp(MouseEvent event) {
        labelajp.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefaultclose(MouseEvent event) {
        labelajp.setStyle("-fx-background-color: #f4eeee;");
    }

}
