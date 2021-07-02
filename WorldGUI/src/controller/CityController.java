package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityController {

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfPais;

    @FXML
    private TextField tfDistrito;

    @FXML
    private TextField tfPopulacao;

    @FXML
    private Button btnFechar;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getID(int ID){
        MySQLConnection connection = new MySQLConnection();
        try {
            ResultSet resultado = connection.getDetalheCidade(ID);
            while(resultado.next()){
                this.tfCidade.setText(resultado.getString(1));
                this.tfPais.setText(resultado.getString(2));
                this.tfDistrito.setText(resultado.getString(3));

                int pop = resultado.getInt(4);
                String pop_s = String.valueOf(pop);
                this.tfPopulacao.setText(pop_s);

                this.tfPopulacao.setText(String.valueOf(resultado.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
