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


import java.net.URL;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import db.ConnexionEQ;



public class StatistiqueController implements Initializable {

    @FXML
    private Pane idpane;
    @FXML
    private PieChart cercle;
    ObservableList<PieChart.Data> pieChartdata;
     
    public Statement stat;
    public ResultSet res;

      ConnexionEQ con = new ConnexionEQ();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lowdata();
        loadeData();
        cercle.setData(pieChartdata);

    }
//***LineChart*********
    public void lowdata() {

        idpane.getChildren().clear();
        NumberAxis xnbr = new NumberAxis(2007, 2020, 1);
        xnbr.setLabel("Année");
        NumberAxis ynbr = new NumberAxis(0, 50, 5);

        LineChart<?, ?> markChart = new LineChart(xnbr, ynbr);
        markChart.setTitle("Les productions Scientifiques de LaMOS");

        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series.setName("Nbre de Manifestations");
        series1.setName("Nbre de publications par années");
        series2.setName("Nbre des équipes");

        try {

            stat = con.obtenirconx().createStatement();

            res = stat.executeQuery("SELECT date_format(datemanif,'%Y') ,Count(idManifestation) FROM manifestation group by date_format(datemanif,'%Y')");

            while (res.next()) {
                String date = res.getString("date_format(datemanif,'%Y')");

                int dat = Integer.parseInt(date);
                series.getData().add(new XYChart.Data<>(dat, res.getInt(2)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            stat = con.obtenirconx().createStatement();

            res = stat.executeQuery("SELECT date_format(date,'%Y') ,Count(idpub) FROM publication group by date_format(date,'%Y')");

            while (res.next()) {
                String date = res.getString("date_format(date,'%Y')");

                int dat = Integer.parseInt(date);
                series1.getData().add(new XYChart.Data<>(dat, res.getInt(2)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            stat = con.obtenirconx().createStatement();

            res = stat.executeQuery("SELECT date_format(datecreat,'%Y'),Count(idEquipe) FROM equipe group by date_format(datecreat,'%Y') ");

            while (res.next()) {
                String date = res.getString("date_format(datecreat,'%Y')");

                int dat = Integer.parseInt(date);
                series2.getData().add(new XYChart.Data<>(dat, res.getInt(2)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        markChart.getData().addAll(series, series1, series2);

        markChart.setMaxWidth(350);
        markChart.setMaxHeight(350);
        idpane.getChildren().add(markChart);

    }
///****pieChart****
    private void loadeData() {
        pieChartdata = FXCollections.observableArrayList();
        try {
            stat = con.obtenirconx().createStatement();
            res = stat.executeQuery("SELECT count( idChercheur ) , acroequip FROM equipe, chercheur WHERE idEquipe = idEq GROUP BY acroequip");
            while (res.next()) {
                pieChartdata.addAll(new PieChart.Data(res.getString(2), res.getInt(1)));

            }

       

    }   catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
