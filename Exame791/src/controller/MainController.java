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
    private Button btnConsultar;

    @FXML
    private Button btnSair;

    private MySQLConnection connection;



    @FXML
    void consultarClientes(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/clientesView.fxml"));
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
        getTotalClientes();
    }

    private void getTotalClientes(){
        try{
            ResultSet totalClientes = connection.getTotalClientes();
            while(totalClientes.next())
                this.tfTotal.setText(String.valueOf(totalClientes.getInt(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }


}

