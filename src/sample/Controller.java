package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.image.ImageView;

public class Controller{

    @FXML
    private ImageView img;




    @FXML
    public void toSecondeF(ActionEvent actionEvent) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("2em.fxml"));
        Scene scene = new Scene(page);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }


  @FXML
    public void tothreeF(ActionEvent actionEvent) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("3em.fxml"));
        Scene scene = new Scene(page);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }




//    @FXML
//    public  void event (ActionEvent allonsy) throws IOException{
//
////        System.out.println("clicccccccc");
//    }
}
