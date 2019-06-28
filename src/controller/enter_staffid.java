package controller;

import animatefx.animation.SlideInRight;
import database_ueps.ConnectivityClass;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class enter_staffid implements Initializable {

    public AnchorPane anchor_enter;
    public TextField enter_staff;
    public static String id;
    public Label staff_results;
    public Button okay_button;
    static String id_hold;

    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(anchor_enter).play();
    }


    public void check(MouseEvent mouseEvent){

        ConnectivityClass connectionClass=new ConnectivityClass();
        Connection connection= connectionClass.getConnection();

        id = enter_staff.getText();
        if  ( id.isEmpty())
        {
            staff_results.setText("INVALID INPUT!!");
        }else if(id.length() < 5){
            staff_results.setText("INVALID INPUT!!");


        }
        else {
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql = "select staff_id from logs where staff_id = '"+enter_staff.getText()+"'";
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (resultSet.next()) {
                    FXMLLoader loader= new  FXMLLoader(getClass().getResource("../fxml/generate_salary.fxml"));
                    Parent root= loader.load();
                    generate_salary status = loader.getController();

                    status.retrieve_id(enter_staff.getText());
                    Stage stage_app = new Stage();
                    stage_app.initModality(Modality.APPLICATION_MODAL);
                    stage_app.focusedProperty().addListener(e-> {
                        stage_app.toFront();
                    });
                    stage_app.setScene(new Scene(root));

                    stage_app.showAndWait();

                }
                else {
                    staff_results.setText("         INVALID INPUT!!\n OR EMPLOYEE ALREADY PAYED!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void okay(MouseEvent mouseEvent) { check(mouseEvent);
    }
















       /* Parent change = null;
        change = FXMLLoader.load(getClass().getResource("../fxml/generate_salary.fxml"));
        Scene change_scene = new Scene(change);
        Stage stage_app = new Stage();
        stage_app.setScene(change_scene);

        stage_app.show();*/

}
