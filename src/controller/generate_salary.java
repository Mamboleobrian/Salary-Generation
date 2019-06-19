package controller;

import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class generate_salary  implements Initializable {


    public Label entered_id;
    public AnchorPane anchor_generate;
    public TableColumn _date;
    public TableColumn _card_id;
    public TableColumn _arrival_time;
    public TableColumn _departure_time;
    public TableView tableview_generate;
    public TableColumn _staff;
    public TextArea salary_display;
    String id_holder;

    ObservableList<TableModelgen> observableList = FXCollections.observableArrayList();

    public generate_salary(){
        id_holder=enter_staffid.id;

    }

    public void retrieve_id (String Users) {
        entered_id.setText(Users); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();
        retrieve_id(id_holder);

       // ResultSet resultSet = null;
        try {


            ResultSet resultSet = connection.createStatement().executeQuery("select date, card_id, staff_id, time_in, time_out from logs where staff_id ='" + entered_id.getText() + "'");

            while (resultSet.next()) {
                observableList.add(new TableModelgen(resultSet.getString("date"), resultSet.getString("card_id"),resultSet.getString("staff_id"), resultSet.getString("time_in"), resultSet.getString("time_out")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }



        _date.setCellValueFactory(new PropertyValueFactory<>("dates"));
        _card_id.setCellValueFactory(new PropertyValueFactory<>("card"));
        _staff.setCellValueFactory(new PropertyValueFactory<>("staffs"));
        _arrival_time.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        _departure_time.setCellValueFactory(new PropertyValueFactory<>("departure"));

        tableview_generate.setItems(observableList);


    }

    public void back_home(MouseEvent mouseEvent) {

        Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        close(anchor_generate);
    }

        private void close(AnchorPane anchorMain) {

            Stage stage = (Stage) anchorMain.getScene().getWindow();
            stage.close();
        }

    public void generate(MouseEvent mouseEvent) {


    }
}
