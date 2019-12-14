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
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;


public class ListeResponsableController implements Initializable {

    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colPren;
    @FXML
    private TableColumn<?, ?> colGrad;
    @FXML
    private TableColumn<?, ?> colSps;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private JFXButton btn_quiter;
    @FXML
    private JFXTextField txt_tel;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private Label lb_idEqui;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Integer idE){
    lb_idEqui.setText(idE.toString());}

    @FXML
    private void rowsClick(MouseEvent event) {
    }

    @FXML
    private void handValider(ActionEvent event) {
    }

    @FXML
    private void handQuiter(ActionEvent event) {
    }

    
}
