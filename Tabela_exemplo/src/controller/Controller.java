package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Pessoa> listaPessoas;

    public void initialize () {
        //inserir as opções na combobox
        this.cbGenero.getItems().addAll("Feminino","Masculino","Outro");

        //preparar a tabela para receber os objetos da classe pessoa
        listaPessoas = FXCollections.observableArrayList();
        this.tblPessoas.setItems(listaPessoas);
        this.colPrimNome.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("primNome"));
        this.colUltNome.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("ultNome"));
        this.colGenero.setCellValueFactory(new PropertyValueFactory<Pessoa,String>("genero"));

    }

    @FXML
    public void adicionar (ActionEvent event){
        //ler os valores introduzidos e guardar em variáveis
        String primNome = this.tfPrimNome.getText();
        String ultNome = this.tfUltNome.getText();
        String genero = this.cbGenero.getValue();
        //instanciar objeto da classe Pessoa
        Pessoa p = new Pessoa(primNome,ultNome,genero);
        //se o novo objeto não estiver na lista adiciona o novo objeto
        if(this.listaPessoas.contains(p)==false){
            this.listaPessoas.add(p);
            this.tblPessoas.setItems(listaPessoas);
            //lança alerta de sucesso
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("SUCESSO");
            alerta.setContentText("Pessoa adicionada com sucesso!");
            alerta.showAndWait();
            limpar();
        }
        else {
            //se objeto já existe lança alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("PROBLEMA");
            alerta.setContentText("Pessoa já existente...");
            alerta.showAndWait();
            limpar();
        }
    }

    public void limpar(){
        this.tfPrimNome.setText(null);
        this.tfUltNome.setText(null);
        this.cbGenero.setValue(null);
    }


}
