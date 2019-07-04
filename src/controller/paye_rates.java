package controller;

import animatefx.animation.SlideInRight;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class paye_rates implements Initializable {

    public AnchorPane anchor_paye;
    public TableView table_view_paye;
    public TableColumn taxable;
    public TableColumn tax_rate;

    ObservableList<TableModelPaye_rates> observableList = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {

        new SlideInRight(anchor_paye).play();

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery("select taxable_income, tax_rate from paye_display");


            while (resultSet.next()) {

                observableList.add(new TableModelPaye_rates(resultSet.getString("taxable_income"), resultSet.getString("tax_rate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        taxable.setCellValueFactory(new PropertyValueFactory<>("_tax"));
        tax_rate.setCellValueFactory(new PropertyValueFactory<>("_rate"));

        table_view_paye.setItems(observableList);

    }
    private void close(AnchorPane anchorMain) {

        Stage stage = (Stage) anchorMain.getScene().getWindow();
        stage.close();
    }

        public void paye_home(MouseEvent mouseEvent) {

            Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            close(anchor_paye);

    }
}
