package controller;

import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class profile implements Initializable {
    public Label help;
    public AnchorPane anchor_profile;
    public Label display_prof;
    public Label deleted;

    public JFXPasswordField confirm_new_password;
    public JFXPasswordField new_password;
    public JFXPasswordField old_password;
    public JFXTextField show_old;
    public JFXTextField show_new;
    public JFXCheckBox check_old;
    public JFXCheckBox check_new;

    public void initialize(URL location, ResourceBundle resources) {

        new SlideInRight(anchor_profile).play();

        show_old.setManaged(false);
        show_old.setVisible(false);

        show_old.managedProperty().bind(check_old.selectedProperty());
        show_old.visibleProperty().bind(check_old.selectedProperty());

        old_password.managedProperty().bind(check_old.selectedProperty().not());
        old_password.visibleProperty().bind(check_old.selectedProperty().not());

        show_old.textProperty().bindBidirectional(old_password.textProperty());


        show_new.setManaged(false);
        show_new.setVisible(false);

        show_new.managedProperty().bind(check_new.selectedProperty());
        show_new.visibleProperty().bind(check_new.selectedProperty());

        new_password.managedProperty().bind(check_new.selectedProperty().not());
        new_password.visibleProperty().bind(check_new.selectedProperty().not());

        show_new.textProperty().bindBidirectional(new_password.textProperty());


    }

    public void displayId(String user) {
        display_prof.setText(user);
    }

    public void help_popup(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HELP");
        alert.setHeaderText(null);
        alert.setContentText("Enter Old Password, New Password then Click on CHANGE to change password");
        alert.showAndWait();
    }

    public void account_delete(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this account?" + display_prof.getText() + " ?");
        alert.setTitle("Account deletion");
        alert.setHeaderText(null);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            ConnectivityClass connectivityClass = new ConnectivityClass();
            Connection connection = connectivityClass.getConnection();
            String sql = "DELETE FROM registration WHERE staff_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, display_prof.getText());
                preparedStatement.executeUpdate();
                deleted.setText("Account deleted");
            } catch (SQLException e) {
                deleted.setText("Account not deleted");
                e.printStackTrace();
            }
            Parent change = null;
            try {
                change = FXMLLoader.load(getClass().getResource("../fxml/authentication.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene change_scene = new Scene(change);
            Stage stage_app = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
            stage_app.setScene(change_scene);
            stage_app.show();
            authentication en = new authentication();
            en.close(anchor_profile);
        }
    }

    public void change_password(ActionEvent actionEvent) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();
        String query = "select password from registration where staff_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
           // PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement.setString(1,display_prof.getText());
           // preparedStatement1.setString(2,display_prof.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
           // ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String pass = resultSet.getString("password");
                if (pass.equals(old_password.getText())) {

                    String sql = "update registration set password=? where staff_id=?";
                   // String sql2 = "update registration set confirm_password where staff_id=?";
                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                       // PreparedStatement statement1 = connection.prepareStatement(sql2);
                        statement.setString(1, new_password.getText());
                        //statement1.setString(2, confirm_new_password.getText());
                        statement.setString(2, display_prof.getText());

                        statement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password change!!");
                    alert.setHeaderText(null);
                    alert.setContentText("You have successfully changed your password");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password change!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Password not changed");
                    alert.showAndWait();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void return_home(MouseEvent mouseEvent) {
        Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        //stage_app.setScene(change_scene);
        close(anchor_profile);
    }
    private void close(AnchorPane anchorMain) {

        Stage stage = (Stage) anchorMain.getScene().getWindow();
        stage.close();
    }
}
