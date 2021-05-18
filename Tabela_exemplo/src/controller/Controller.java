package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pessoa;

public class Controller {
    @FXML TextField tfPrimNome;
    @FXML TextField tfUltNome;
    @FXML ComboBox<String> cbGenero;
    @FXML Button btnAdicionar;
    @FXML TableView<Pessoa> tblPessoas;
    @FXML TableColumn<Pessoa,String> colPrimNome;
    @FXML TableColumn<Pessoa,String> colUltNome;
    @FXML TableColumn<Pessoa,String> colGenero;


    @FXML
    public void adicionar (ActionEvent event){



    }

}
