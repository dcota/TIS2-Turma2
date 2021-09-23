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
    private TextField tfID;

    @FXML
    private TextField tfCliente;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField tfContacto;

    @FXML
    private TextField tfMorada;

    @FXML
    private TextField tfTelefone;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getIDEnc(String ID){
        try{
            MySQLConnection connection = new MySQLConnection();
            ResultSet result = connection.getDetalhe(ID);
            while(result.next()){
                this.tfID.setText(result.getString(1));
                this.tfCliente.setText(result.getString(2));
                this.tfContacto.setText(result.getString(3));
                this.tfMorada.setText(result.getString(4));
                this.tfTelefone.setText(result.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
