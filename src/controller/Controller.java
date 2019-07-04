package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    public AnchorPane anchorMain;
    public BorderPane borderpane;
    public Label staffprofile;
    public Label prof;
    public TextField search_id;
    public JFXTextField search_staff;
    public Label staff_label;
    public Label card_label;
    public Label name_label;
    public Label Jurisdiction_label;
    public Label _staff;
    public Label _card;
    public Label _name;
    public Label _jurisdiction;
    public JFXTextArea search_results;
    public Label employee_search;
    public AnchorPane search_anchor;


    public void new_card(MouseEvent mouseEvent) throws IOException {
      change("new_smartcard");
    }

    public void profile(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader= new  FXMLLoader(getClass().getResource("../fxml/profile.fxml"));
        Parent root= loader.load();
        profile pro = loader.getController();
        pro.displayId(prof.getText());
        Stage stage_app = new Stage();
        stage_app.setScene(new Scene(root));
        stage_app.initModality(Modality.APPLICATION_MODAL);
        stage_app.focusedProperty().addListener(e-> {
            stage_app.toFront();
        });
        stage_app.setResizable(false);
        stage_app.setMaximized(false);

        //stage_app.getIcons().add(image1);
        stage_app.show();


       // Parent change = FXMLLoader.load(getClass().getResource("../fxml/profile.fxml"));
        //Scene change_scene = new Scene(change);
        //Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        //stage_app.setScene(change_scene);
        //stage_app.show();
    }
    public void loadUi(String ui) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/"+ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

    public void change( String achor){

        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/"+achor+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = new Stage();
        stage_app.setScene(change_scene);
        borderpane.setCenter(change);
//        stage_app.show();
    }

    public void logs(ActionEvent actionEvent) {
        change("logs");
    }

    public void details(ActionEvent actionEvent) {
        change("card_details");
    }

    public void search_staff(MouseEvent mouseEvent) {
    }



    public void pay(ActionEvent actionEvent) { change("enter_staffid");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUi("logs");

    }
     public void getProfileId(String userId){

        prof.setText(userId);
    }

    public void button_rates(ActionEvent actionEvent) { change("rates");
    }

    public void logout(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to log out?");
        alert.setTitle("Log out");
        alert.setHeaderText(null);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Parent change = FXMLLoader.load(getClass().getResource("../fxml/authentication.fxml"));
            Scene change_scene = new Scene(change);
            Stage stage_app = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
            stage_app.setScene(change_scene);
            stage_app.show();
            close(anchorMain);
        }
    }

    private void close(AnchorPane anchorMain) {

        Stage stage = (Stage) anchorMain.getScene().getWindow();
        stage.close();
    }

    public void add_jurisdiction(MouseEvent mouseEvent) {
    }

    public void delete_employee(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("../fxml/delete_employee.fxml"));
        Parent root= loader.load();
        Stage stage_app = new Stage();
        stage_app.setScene(new Scene(root));
        stage_app.initModality(Modality.APPLICATION_MODAL);
        stage_app.focusedProperty().addListener(e-> {
            stage_app.toFront();
        });
        stage_app.setResizable(false);
        stage_app.setMaximized(false);
        stage_app.showAndWait();

    }
    public void payed(MouseEvent mouseEvent) { change("payed");
    }

    public void click_search(MouseEvent mouseEvent) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM details WHERE ID ='" + search_staff.getText() + "'";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()) {

                try {
                    employee_search.setText(null);
                    staff_label.setText(null);
                    card_label.setText(null);
                    name_label.setText(null);
                    Jurisdiction_label.setText(null);
                    _staff.setText(null);
                    _card.setText(null);
                    _name.setText(null);
                    _jurisdiction.setText(null);
                    String staff = resultSet.getString("ID");
                    String card = resultSet.getString("Card_ID");
                    String name =resultSet.getString("Name");
                    String jury = resultSet.getString("jurisdiction");
                    employee_search.setText("EMPLOYEE DETAILS:");
                    staff_label.setText("Staff ID:");
                    card_label.setText("Card ID:");
                    name_label.setText("Employee Name:");
                    Jurisdiction_label.setText("Job Jurisdiction:");
                    _staff.setText(staff);
                    _card.setText(card);
                    _name.setText(name);
                    _jurisdiction.setText(jury);
                    borderpane.setCenter(search_anchor);
                    search_results.clear();
                    search_results.appendText("UNIVERSITY OF ELDORET EMPLOYEE DETAILS"+
                                             "\nAddress: P.O. Box 1125-30100 Eldoret, Kenya" +
                                             "\n  Phone: +254(0) 788 232 004,"+
                                            "\n          +254(0) 740 354 966,"+
                                             "\n         +254(0) 774 249 552"+
                                            "\n   Email: info@uoeld.ac.ke"+
                                              "\n\nStaff ID         " +_staff.getText()+
                                            "\nCard ID   " +_card.getText()+
                                            "\nEmployee Name          " +_name.getText()+
                                             "\nJob Jurisdiction       " +_jurisdiction.getText());



                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                staff_label.setText(null);
                card_label.setText(null);
                name_label.setText(null);
                Jurisdiction_label.setText(null);
                _staff.setText(null);
                _card.setText(null);
                _name.setText(null);
                _jurisdiction.setText(null);
                search_results.clear();
                employee_search.setText("Invalid input");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void relief_pa(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("KENYAN TAX RELIEF");
        alert.setHeaderText(null);
        alert.setContentText("THE TAX RELIEF IS KSH 16896 PER ANNUM");
        alert.showAndWait();
    }

    public void print_employee(MouseEvent mouseEvent) {
        printer emp_print= new printer();
        emp_print.Printing(search_results);
    }

    public void Allowances(MouseEvent mouseEvent) { change("allowances"); }

    public void deductions(MouseEvent mouseEvent) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("DEDUCTIONS");
        alert1.setHeaderText(null);
        alert1.setContentText("DEDUCTIONS INCLUDE: \n KSH 500 FOR NHIF \n KSH 500 FOR NSSF \n\n OTHER DEDUCTIONS MAY BE INCLUDED WHERE APPLICABLE");
        alert1.showAndWait();
    }

    public void paye_rates(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader= new  FXMLLoader(getClass().getResource("../fxml/paye_rates.fxml"));
        Parent root= loader.load();
        Stage stage_app = new Stage();
        stage_app.setScene(new Scene(root));
        stage_app.initModality(Modality.APPLICATION_MODAL);
        stage_app.focusedProperty().addListener(e-> {
            stage_app.toFront();
        });
        stage_app.setResizable(false);
        stage_app.setMaximized(false);
        stage_app.show();

    }


    // public void retrive_id(String users){
      //  staffprofile.setText(users);
      //  Text text = new Text(""+users);
       // marquee((javafx.scene.text.Text) text,staffprofile);

    //}

   /* private void marquee(javafx.scene.text.Text string, Label label){
        label.setText(string.getText());
        //getting width of scene and text
        double sceneWidth = label.getWidth();
        double textwidth = string.getLayoutBounds().getWidth();}*/

}
