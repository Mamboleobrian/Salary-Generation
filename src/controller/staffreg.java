package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import database_ueps.ConnectivityClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import controller.validator;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class staffreg implements Initializable {
    public ActionEvent event;
    public JFXTextField name_field;
    public JFXTextField staff_id;
    public JFXPasswordField password;
    public JFXPasswordField admin_pass;
    public JFXPasswordField confirm_password;



    @FXML
    public Label PasswordErrorLabel;
    public JFXTextField show_password;
    public JFXTextField show_confirm;
    public JFXTextField show_admin;
    public JFXCheckBox pass_box;
    public JFXCheckBox confirm_box;
    public JFXCheckBox admin_box;


    validator validate= new validator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show_password.setManaged(false);
        show_password.setVisible(false);

        show_password.managedProperty().bind(pass_box.selectedProperty());
        show_password.visibleProperty().bind(pass_box.selectedProperty());

        password.managedProperty().bind(pass_box.selectedProperty().not());
        password.visibleProperty().bind(pass_box.selectedProperty().not());

        show_password.textProperty().bindBidirectional(password.textProperty());
        //sign_up.isFocused();

        show_confirm.setManaged(false);
        show_confirm.setVisible(false);

        show_confirm.managedProperty().bind(confirm_box.selectedProperty());
        show_confirm.visibleProperty().bind(confirm_box.selectedProperty());

        confirm_password.managedProperty().bind(confirm_box.selectedProperty().not());
        confirm_password.visibleProperty().bind(confirm_box.selectedProperty().not());

        show_confirm.textProperty().bindBidirectional(confirm_password.textProperty());


        show_admin.setManaged(false);
        show_admin.setVisible(false);

        show_admin.managedProperty().bind(admin_box.selectedProperty());
        show_admin.visibleProperty().bind(admin_box.selectedProperty());

        admin_pass.managedProperty().bind(admin_box.selectedProperty().not());
        admin_pass.visibleProperty().bind(admin_box.selectedProperty().not());

        show_admin.textProperty().bindBidirectional(admin_pass.textProperty());

    }


    public void staffreg(ActionEvent actionEvent) {

        event = actionEvent;
        ConnectivityClass connectionClass=new ConnectivityClass();
        Connection connection= connectionClass.getConnection();
        String query="select pass from chief";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
          ResultSet resultSet= pst.executeQuery();
          while (resultSet.next()){
              String getPass = resultSet.getString("pass");
              if(getPass.equals(admin_pass.getText())){


                  String sql="INSERT INTO registration(name, staff_id, password, confirm_password) VALUES('"+name_field.getText()+"','"+staff_id.getText()+"','"+password.getText()+"','"+confirm_password.getText()+"')";
                  Statement statement = null;
                  try {
                      statement = connection.createStatement();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  try {
                      statement.executeUpdate(sql);
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  try {
                      if(validate.validate(password.getText())&& validate.validate(confirm_password.getText())){
                          statement.executeUpdate(sql);


                      }else{
                          PasswordErrorLabel.setText("incorrect password");
                          PasswordErrorLabel.setStyle("-fx-text-fill:red;");
                      }
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  Parent change = null;
                  try {
                      change = FXMLLoader.load(getClass().getResource("../fxml/authentication.fxml"));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  Scene change_scene = new Scene(change);
                  Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                  stage_app.setScene(change_scene);
                  stage_app.show();
              }
              else {
                  System.out.println("Check your details and try again");
              }
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void back_entry(MouseEvent event) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/authentication.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        //stage_app.getIcons().add(image);
        stage_app.show();
    }
    




}
