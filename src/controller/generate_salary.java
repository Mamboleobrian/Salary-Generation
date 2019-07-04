package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class generate_salary  implements Initializable {


    public Label entered_id;
    public AnchorPane anchor_generate;
    public TableColumn _date;
    public TableColumn _card_id;
    public TableColumn _arrival_time;
    public TableColumn _departure_time;
    public TableView tableview_generate;
    public TableColumn _staff;
    public Label _from;
    public Label _to;
    public ImageView logo_pay;
    public Label entered_id_Print;
    public TableColumn time_worked;
   
    public JFXTextArea salary_display;
    public DatePicker date_from;
    public DatePicker date_to;
    String id_holder;

    ObservableList<TableModelgen> observableList = FXCollections.observableArrayList();

    validator validator = new validator();


    public generate_salary(){
        id_holder=enter_staffid.id;

    }

    public void retrieve_id (String Users) {
        entered_id.setText(Users);
        entered_id_Print.setText(Users);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        validator.changeDatePickerFormat(date_from);
        validator.changeDatePickerFormat(date_to);




        retrieve_id(id_holder);

       // ResultSet resultSet = null;
        try {


            ResultSet resultSet = connection.createStatement().executeQuery("select date, card_id, time_in, time_out, diff from logs where staff_id ='" + entered_id.getText() + "' order by date desc");

            while (resultSet.next()) {
                observableList.add(new TableModelgen(resultSet.getString("date"), resultSet.getString("card_id"), resultSet.getString("time_in"), resultSet.getString("time_out"),resultSet.getString("diff")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }



        _date.setCellValueFactory(new PropertyValueFactory<>("dates"));
        _card_id.setCellValueFactory(new PropertyValueFactory<>("card"));
        _arrival_time.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        _departure_time.setCellValueFactory(new PropertyValueFactory<>("departure"));
        time_worked.setCellValueFactory(new PropertyValueFactory<>("time"));

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

       // Scanner reader = new Scanner(System.in);
       // char name = reader.next().charAt(0);

        double mamboleo = 0;
        double cash_rate = 0;
        double cash_ovr = 0;

        double ans = 0;

        //allowances
        double house = 0;
        double transport = 0;
        //dedactions
        double health = 0;
        double retire = 0;

        double taxable_income = 0;
        double paye_rate = 0;
        double gross_tax = 0;
        double relief = 0;
        double paye = 0;
        double net_sal = 0;
        double final_net = 0;
        double final_paye = 0;

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
               // Statement statement1 = null;
                Statement statement2 = null;
                Statement statement3 = null;
                Statement statement5 = null;
                Statement statement6 = null;
                Statement statement7 = null;
                Statement statement8 = null;

                try {
                    statement = connection.createStatement();
                   // statement1 = connection.createStatement();
                    statement2 = connection.createStatement();
                    statement3 = connection.createStatement();
                    statement5 = connection.createStatement();
                    statement6 = connection.createStatement();
                    statement7 = connection.createStatement();
                    statement8 = connection.createStatement();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String sql = "select sum(diff) from logs where staff_id = '"+entered_id.getText()+"' and date >= '"+date_from.getValue()+"' and date <= '"+date_to.getValue()+"'";
               // String sql1 = "select Name from details where ID = '"+entered_id.getText()+"'";
                String sql2 = "select payment_rate.rate, payment_rate.ovr from payment_rate inner join details on payment_rate.jurisdiction = details.jurisdiction where details.ID = '"+entered_id.getText()+"'";
                String sql5 = "select nhif from deductions";
                String sql6 = "select nssf from deductions";
                String sql7 = "select payment_rate.house_allowance, payment_rate.transport_allowance from payment_rate inner join details on payment_rate.jurisdiction = details.jurisdiction where details.ID = '"+entered_id.getText()+"' ";
                String sql8= "select ril from relief";

                ResultSet resultSet = null;
               // ResultSet resultSet1 = null;
                ResultSet resultSet2 = null;
                ResultSet resultset5 = null;
                ResultSet resultset6 = null;
                ResultSet resultset7 = null;
                ResultSet resultset8 = null;
                try {
                    resultSet = statement.executeQuery(sql);
                    resultSet2 = statement2.executeQuery(sql2);
                    resultset5 = statement5.executeQuery(sql5);
                    resultset6 = statement6.executeQuery(sql6);
                    resultset7 = statement7.executeQuery(sql7);
                    resultset8 = statement8.executeQuery(sql8);
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
                if (mamboleo <= 160){
                    ans = mamboleo * cash_rate;
                }else {
                    ans = 160 * cash_rate;
                    ans += (mamboleo - 160) * cash_ovr;
                }
                while (resultset5.next() ) {
                    health = resultset5.getDouble("nhif");
                }
                while (resultset6.next()) {
                    retire = resultset6.getDouble("nssf");
                }
                while (resultset7.next()) {
                    house = resultset7.getDouble("house_allowance");
                    transport = resultset7.getDouble("transport_allowance");
                }
                while (resultset8.next()){
                    relief = resultset8.getDouble("ril");
                }

                if (ans != 0)

                    taxable_income = ((ans + house +  transport) - (health + retire)) * 12;

                Statement statement9 = null;
                statement9 = connection.createStatement();

                String sql9 = "select rate from paye where range_from <= '"+taxable_income+"' and range_to >= '"+taxable_income+"' ";

                ResultSet resultSet9 = null;

                resultSet9 = statement9.executeQuery(sql9);

                while (resultSet9.next()) {
                    paye_rate = resultSet9.getDouble("rate");
                }

                if (paye_rate != 0)
                    gross_tax = (paye_rate/100) * (taxable_income);
                    paye = gross_tax - relief;
                    final_paye = paye/12;
                    net_sal = taxable_income - paye;
                    final_net = net_sal/12;




               // String query = "delete  from logs where staff_id= ?";
                //PreparedStatement prepareDelete = connection.prepareStatement(query);
                //prepareDelete.setString(1, entered_id.getText());
                //prepareDelete.executeUpdate();

                //logo();
                //java.util.Date date = new java.util.Date();

               // resultSet1 = statement1.executeQuery(sql1);

               // while (resultSet1.next()){
                  //  System.out.print(name);
                  //  name = resultSet1.get("Name");
               // }

                LocalDate dateToday = LocalDate.now();
                LocalTime timeNow = LocalTime.now();

                salary_display.appendText(
                        "\n              UNIVERSITY OF ELDORET PAYSLIP  "+
                        "\n\n      Address: P.O. Box 1125-30100 Eldoret, Kenya" +
                        "\n        Phone: +254(0) 788 232 004,"+
                        "\n               +254(0) 740 354 966,"+
                        "\n               +254(0) 774 249 552"+
                        "\n        Email: info@uoeld.ac.ke"+
                        "\n\n" +
                        "\n\nStaff_id               "+entered_id.getText()+"" +
                        "\n\nGROSS SALARY           "+String.valueOf(ans)+"" +
                        "\n\nHOUSE ALLOWANCE        "+String.valueOf(house)+"" +
                        "\nTRANSPORT ALLOWANCE    "+String.valueOf(transport)+"" +
                        "\n\nNHIF                   "+String.valueOf(health)+"" +
                        "\nNSSF                   "+String.valueOf(retire)+"" +
                        "\n\nPAYE                   "+String.valueOf(final_paye)+"" +
                        "\n\nNET SALARY             "+String.valueOf(final_net)+"" +
                        "\n\nDate payed             "+dateToday+"" +
                        "\nTime payed             "+timeNow+
                        "\n\nPayment from date "+date_from.getValue()+" to date "+date_to.getValue()+"");

                String sql4="INSERT INTO payed(staff_id, amount, house_allowance, transport_allowance, nhif, nssf, paye, net, date_from, date_to, time_payed, date_payed)" +
                        " VALUES('"+entered_id.getText()+"','"+String.valueOf(ans)+"','"+String.valueOf(house)+"','"+String.valueOf(transport)+"','"+String.valueOf(health)+"','"+String.valueOf(retire)+"','"+String.valueOf(final_paye)+"','"+String.valueOf(final_net)+"','"+date_from.getValue()+"','"+date_to.getValue()+"','"+timeNow+"','"+dateToday+"')";
                Statement statement4 = null;
                statement4 = connection.createStatement();

                statement4.executeUpdate(sql4);



                //salary_display.setText(String.valueOf(sql3));
               // salary_display.setText(String.valueOf(ans));
            }
        }



    }

    private void logo(Stage stage)throws FileNotFoundException {
        Image image = new Image(new FileInputStream("/images/eldorate.jpg"));
        logo_pay = new ImageView(image);
        stage.show();
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

    public void payslip_print(MouseEvent mouseEvent) {

        printer pay_printer = new printer();
        pay_printer.Printing(salary_display);
    }

    public void specific_logs_print(MouseEvent mouseEvent) {
        printer specific_logs_print = new printer();
        specific_logs_print.Printing(tableview_generate);
    }
}
