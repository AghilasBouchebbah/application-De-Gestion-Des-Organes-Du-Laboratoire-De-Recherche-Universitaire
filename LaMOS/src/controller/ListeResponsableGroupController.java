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
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;


public class ListeResponsableGroupController implements Initializable {

    @FXML
    private TableView<?> tabChefGroup;
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
    private Label lb_idGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rowsClick(MouseEvent event) {
    }

    @FXML
    private void handValider(ActionEvent event) {
    }

    @FXML
    private void handQuiter(ActionEvent event) {
    }

    @FXML
    private void handModifier(ActionEvent event) {
    }
    
}
