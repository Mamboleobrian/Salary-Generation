package controller;

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
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
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
        stage_app.show();

    }

    public void click_search(MouseEvent mouseEvent) {


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
