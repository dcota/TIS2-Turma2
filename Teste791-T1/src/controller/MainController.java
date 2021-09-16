package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQLConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField tfTotal;

    @FXML
    private TextField tfMedia;

    @FXML
    private Button btnVerEncomendas;

    @FXML
    private Button btnSair;

    private MySQLConnection connection;

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verAlunos(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/encomendasView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new MySQLConnection();
        getTotalEncomendas();
        getMediaValor();
    }

    private void getTotalEncomendas(){
        try{
            ResultSet totalEncomendas = connection.getTotalEncomendas();
            while(totalEncomendas.next())
                this.tfTotal.setText(String.valueOf(totalEncomendas.getInt(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void getMediaValor(){
        try{
            ResultSet media = connection.getMedia();
            while(media.next())
                this.tfMedia.setText(String.valueOf(media.getDouble(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
