package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    @FXML Button btnEliminar;
    @FXML Button btnAlterar;

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
        if (!this.tfPrimNome.getText().isEmpty() &&
            !this.tfUltNome.getText().isEmpty() &&
            this.cbGenero.getValue()!=null) {
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
                alertaInforma("Pessoa adicionada com sucesso!");
                limpar();
            }
            else {
                //se objeto já existe lança alerta
                alertaAviso("Pessoa existente...");
                limpar();
            }
        }
        else{
            alertaAviso("Todos os campos devem estar preenchidos!");
        }

    }

    @FXML
    public void eliminar (ActionEvent event){
        Pessoa p = this.tblPessoas.getSelectionModel().getSelectedItem();
        if (p==null){
            alertaAviso("Tem de selecionar uma linha para eliminar...");
        }
        else{
            this.listaPessoas.remove(p);
            this.tblPessoas.refresh();
            alertaInforma("Pessoa eliminada com sucesso!");
        }
    }

    @FXML
    public void alterar (ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vistaAlterar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            System.out.println("Alterar");
            stage.showAndWait();
        }
        catch (Exception e){

        }



    }



    public void limpar(){
        this.tfPrimNome.setText(null);
        this.tfUltNome.setText(null);
        this.cbGenero.setValue(null);
    }

    public void alertaInforma (String texto){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("SUCESSO");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    public void alertaAviso (String texto) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setTitle("ERRO");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }


}
