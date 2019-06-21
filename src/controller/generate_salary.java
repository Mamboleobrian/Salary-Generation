package controller;

import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
    public DatePicker date_from;
    public DatePicker date_to;
    public Label _from;
    public Label _to;
    String id_holder;

    ObservableList<TableModelgen> observableList = FXCollections.observableArrayList();

    validator validator = new validator();


    public generate_salary(){
        id_holder=enter_staffid.id;

    }

    public void retrieve_id (String Users) {
        entered_id.setText(Users); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        validator.changeDatePickerFormat(date_from);
        validator.changeDatePickerFormat(date_to);




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

    public void generate(MouseEvent mouseEvent) throws SQLException {

        validate_date();

        double mamboleo = 0;
        double cash_rate = 0;
        double cash_ovr = 0;
        double ans = 0;

        if (validator.pastDates(date_from)&&(validator.pastDates(date_to))){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure your want to generate salary");
            alert.setTitle("Generate");
            alert.setHeaderText(null);

            Optional<ButtonType> answer = alert.showAndWait();

            if (answer.get() == ButtonType.OK) {

                ConnectivityClass connectionClass = new ConnectivityClass();
                Connection connection = connectionClass.getConnection();


                Statement statement = null;
                Statement statement2 = null;
                try {
                    statement = connection.createStatement();
                    statement2 = connection.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String sql = "select sum(diff) from logs where staff_id = '"+entered_id.getText()+"' and date >= '"+date_from.getValue()+"' and date <= '"+date_to.getValue()+"'";
                String sql2 = "select payment_rate.rate, payment_rate.ovr from payment_rate inner join details on payment_rate.jurisdiction = details.jurisdiction where details.ID = '"+entered_id.getText()+"'";
                ResultSet resultSet = null;
                ResultSet resultSet2 = null;
                try {
                    resultSet = statement.executeQuery(sql);
                    resultSet2 = statement2.executeQuery(sql2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                while (resultSet.next() ) {
                    mamboleo = resultSet.getDouble("sum(diff)");

                }
                while (resultSet2.next()){
                    cash_rate = resultSet2.getDouble("payment_rate.rate");
                    cash_ovr = resultSet2.getDouble("payment_rate.ovr");

                }
                if (mamboleo <= 40){
                    ans = mamboleo * cash_rate;
                }else {
                    ans = 40 * cash_rate;
                    ans += (mamboleo - 40) * cash_ovr;
                }

                salary_display.setText(String.valueOf(ans));

            }
        }



    }

    public void validate_date(){

        if (validator.pastDates(date_from))
        {
            _from.setStyle("-fx-text-fill: #19355E;");

        }else {
            _from.setStyle("-fx-text-fill: red;");
        }
        if (validator.pastDates(date_to))
        {
            _to.setStyle("-fx-text-fill: #19355E;");

        }else {
            _to.setStyle("-fx-text-fill: red;");
        }

    }

    public void from_date(ActionEvent actionEvent) {
        validator.pastDates(date_from);



    }

    public void to_date(ActionEvent actionEvent) {

    }
}
