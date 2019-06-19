package controller;
import database_ueps.ConnectivityClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class authentication implements Initializable {


    public CustomTextField show_pass;
    public CustomPasswordField user_pass;
    public CheckBox check_pass;
    public Label connect_feedback;
    public Button sign_up;
    public Button staff_log;
    public AnchorPane anchor_auth;
    private ActionEvent event;
    public CustomTextField staff_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show_pass.setManaged(false);
        show_pass.setVisible(false);

        show_pass.managedProperty().bind(check_pass.selectedProperty());
        show_pass.visibleProperty().bind(check_pass.selectedProperty());

        user_pass.managedProperty().bind(check_pass.selectedProperty().not());
        user_pass.visibleProperty().bind(check_pass.selectedProperty().not());

        show_pass.textProperty().bindBidirectional(user_pass.textProperty());
        sign_up.isFocused();

    }




    public void signup(MouseEvent mouseEvent) throws IOException {

        Parent change = FXMLLoader.load(getClass().getResource("../fxml/staffreg.fxml"));
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();
    }


    public void login(ActionEvent actionEvent) {
        staff_log.setCursor(Cursor.WAIT);
      // Image image1 = new Image("/ueps/image/logo.jpg");
        ConnectivityClass connectionClass=new ConnectivityClass();
        Connection connection= connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT staff_id,password FROM registration WHERE staff_id ='"+staff_id.getText()+"' AND password ='"+user_pass.getText()+"';";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){


        FXMLLoader change = new  FXMLLoader(getClass().getResource("../fxml/uepsmain.fxml"));
                Parent root = null;
                try {
                    root = change.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage_app = new Stage();
     stage_app.setScene(new Scene(root));
//                Screen screen = Screen.getPrimary();
//                Rectangle2D bounds = screen.getVisualBounds();
//
//                stage_app.setX(bounds.getMinX());
//                stage_app.setY(bounds.getMinY());
//                stage_app.setWidth(bounds.getWidth());
//                stage_app.setHeight(bounds.getHeight());



               // stage_app.setScene(new Scene(root, 1500, 900));
     //stage_app.setResizable(false);
     stage_app.setMaximized(true);
       //stage_app.getIcons().add(image1);
        stage_app.show();
        close(anchor_auth);
        stage_app.setOnCloseRequest(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.getDialogPane().getScene().getWindow();

        alert.setTitle("Close UEPS");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait().filter(e->e!=ButtonType.OK).ifPresent(e->event1.consume());});

      Controller c = change.getController();
       c.getProfileId(staff_id.getText());
       // entry enter = new entry();
        close(anchor_auth);

           }else {
                connect_feedback.setText("Cannot log in! Please check details and try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void close(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }


}



