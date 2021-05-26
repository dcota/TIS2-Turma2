package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerAlterar {

    @FXML
    private TextField tfPrimNome;

    @FXML
    private TextField tfUltNome;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnCancelar;

    public void initialize () {
        //inserir as opções na combobox
        this.cbGenero.getItems().addAll("Feminino", "Masculino", "Outro");
    }

    @FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

}

