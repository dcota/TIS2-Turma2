package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondController {

    @FXML
    private Button btnFechar;

    @FXML
    private Label lblResposta;

    @FXML
    public void getResposta(boolean valor){
        if(valor){
            this.lblResposta.setText("O número introduzido é par.");
        } else this.lblResposta.setText("O número introduzido é ímpar.");

    }

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

}

