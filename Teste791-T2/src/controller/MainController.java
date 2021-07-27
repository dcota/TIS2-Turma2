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
    private TextField tfNumAlunos;

    @FXML
    private TextField tfNumCursos;

    @FXML
    private Button btnVerAlunos;

    @FXML
    private Button btnSair;

    private MySQLConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new MySQLConnection();
        getTotalAlunos();
        getTotalCursos();
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verAlunos(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/alunosView.fxml"));
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

    private void getTotalAlunos(){
        try{
            ResultSet totalAlunos = connection.getTotalAlunos();
            while(totalAlunos.next())
                this.tfNumAlunos.setText(String.valueOf(totalAlunos.getInt(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void getTotalCursos(){
        try{
            ResultSet totalCursos = connection.getTotalCursos();
            while(totalCursos.next())
                this.tfNumCursos.setText(String.valueOf(totalCursos.getInt(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
