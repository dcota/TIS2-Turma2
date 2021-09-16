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
    private TextField tfTotal;

    @FXML
    private Button btnFechar;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getIDEnc(int ID){
        try{
            MySQLConnection connection = new MySQLConnection();
            ResultSet result = connection.getDetalhe(ID);
            while(result.next()){
                this.tfID.setText(String.valueOf(result.getInt(1)));
                this.tfCliente.setText(result.getString(2));
                this.tfTotal.setText(String.valueOf(result.getDouble(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
