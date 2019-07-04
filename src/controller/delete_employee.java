package controller;

import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class delete_employee {
    public JFXTextField staff_delete;
    public AnchorPane anchor_delete_employee;
    public Label deleted_account;
    public Label delete_info;
    public static String id_del;


    public void delete_emp(ActionEvent actionEvent) {


        id_del = staff_delete.getText();
        if (id_del.length() < 5 && id_del.isEmpty())
        {
            delete_info.setText("INVALID INPUT!!");

        }else {

            ConnectivityClass connectionClass=new ConnectivityClass();
            Connection connection= connectionClass.getConnection();



                Statement statement = null;
                try {
                    statement = connection.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String sql = "select staff_id from logs where staff_id = '"+staff_delete.getText()+"'";
                ResultSet resultSet = null;
                try {
                    resultSet = statement.executeQuery(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }




        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this account?" + staff_delete.getText() + " ?");
        alert.setTitle("Account deletion");
        alert.setHeaderText(null);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {



            Statement statement2 = null;
            try {
                statement2 = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String sql2 = "DELETE FROM details WHERE ID = ?";
            ResultSet resultSet2 =null;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, staff_delete.getText());
                preparedStatement.executeUpdate();
                deleted_account.setText("Account deleted");
            } catch (SQLException e) {
                deleted_account.setText("Account not deleted");
                e.printStackTrace();
            }
            //Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //close(anchor_delete_employee);
        }else {
            delete_info.setText("INVALID INPUT!!");
        }
        }}

    private void close(AnchorPane anchorMain) {

        Stage stage = (Stage) anchorMain.getScene().getWindow();
        stage.close();
    }

    public void delete_home(MouseEvent mouseEvent) {
        Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        close(anchor_delete_employee);
    }
}
