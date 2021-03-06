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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cidade;
import model.MySQLConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnDetalhe;

    private ObservableList<Cidade> listaCidades; //lista com todas as cidades que existem na bd
    private ObservableList<Cidade>  cidadesFiltradas; //lista para armazenar as cidades pesquisadas

    private MySQLConnection connection;

    private Cidade linhaCidade;

    public void initialize(){
        //preparação da tabela
        listaCidades = FXCollections.observableArrayList();
        cidadesFiltradas = FXCollections.observableArrayList();
        this.tblCidades.setItems(listaCidades);
        this.colCidade.setCellValueFactory(new PropertyValueFactory<Cidade,String>("nomeCidade"));
        this.colPais.setCellValueFactory(new PropertyValueFactory<Cidade,String>("nomePais"));
        //obter a ligação à BD
        connection = new MySQLConnection();

        //chamar o método que preenche a tabela inicial
        tabelaInicial();

    }

    //método que preenche a tabela de cidades com os objetos da base de dados
    public void tabelaInicial(){
        ResultSet result = connection.getCidades();
        try {
            while(result.next()){
                int ID = result.getInt(1);
                String cidade = result.getString(2);
                String pais = result.getString(3);
                Cidade c = new Cidade(ID, cidade,pais);
                this.listaCidades.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.tblCidades.setItems(listaCidades);
        this.tblCidades.refresh();

    }
    //método para inserir nova cidade
    public void inserir(ActionEvent event){
        try{
            //preparar o lançamento da vista inserir cidade
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/newCityView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            tabelaInicial();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //método para pesquisar cidades na tabela
    public void pesquisar(KeyEvent event) {
        String textoPesquisa = this.tfPesquisa.getText();
        if(textoPesquisa==null){
            this.tblCidades.setItems(this.listaCidades);
        } else {
            this.cidadesFiltradas.clear();
            for(int i = 0 ; i<100; i++){

            }
            for(Cidade cidade : this.listaCidades){
                if(cidade.getNomeCidade().toLowerCase().contains(textoPesquisa.toLowerCase())){
                    this.cidadesFiltradas.add(cidade);
                }
            }
            this.tblCidades.setItems(this.cidadesFiltradas);
            this.tblCidades.refresh();
        }
    }

    //método para ver os detalhes de uma cidade
    public void detalhe (ActionEvent event){
        //seleciona o objeto da linha selecionada
        linhaCidade = this.tblCidades.getSelectionModel().getSelectedItem();
        if(linhaCidade==null) {
            alerta(Alert.AlertType.WARNING, "Atenção", "Tem de selecionar uma cidade...");
        } else {
            try{
                //preparar o lançamento da vista do detalhe da cidade
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cityView.fxml"));
                Parent root = loader.load();

                //ir buscar o método getID ao segundo controller
                CityController controllerDetalhe = loader.getController();
                //chamar o método getID e passar o ID da cidade a consultar
                controllerDetalhe.getID(linhaCidade.getID());


                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void alerta (Alert.AlertType type, String tit, String texto){
        Alert alerta = new Alert(type);
        alerta.setTitle(tit);
        alerta.setHeaderText(null);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

}