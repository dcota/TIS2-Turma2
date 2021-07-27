package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalheController {

    @FXML
    private TextField tfPriNome;

    @FXML
    private TextField tfUltNome;

    @FXML
    private TextField tfDataNasc;

    @FXML
    private TextField tfMorada;

    @FXML
    private TextField tfTelemovel;

    @FXML
    private Button btnFechar;

    public void getNumAluno (int ID){
        try{
            MySQLConnection connection = new MySQLConnection();
            ResultSet result = connection.getDetalhe(ID);
            while(result.next()){
                this.tfPriNome.setText(result.getString(1));
                this.tfUltNome.setText(result.getString(2));
                this.tfMorada.setText(result.getString(3));
                this.tfDataNasc.setText(result.getString(4));
                this.tfTelemovel.setText(result.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

}

