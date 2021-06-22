package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondController {

    @FXML
    private TextField tfValida;

    @FXML
    private Button btnFechar;

    //método que recebe a resposta da vista principal e mostra na caixa de texto
    void getResposta(String resposta){
        this.tfValida.setText("");
        this.tfValida.setText(resposta);
    }

    //método para fechar a vista
    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

}
