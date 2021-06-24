package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cidade;
import model.MySQLConnection;

import java.sql.ResultSet;

public class MainController {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade, String> colCidade;

    @FXML
    private TableColumn<Cidade, String> colPais;

    private ObservableList<Cidade> listaCidades;

    private MySQLConnection connection;

    public void initialize(){
        listaCidades = FXCollections.observableArrayList();
        this.tblCidades.setItems(listaCidades);
        this.colCidade.setCellValueFactory(new PropertyValueFactory<Cidade,String>("nomeCidade"));
        this.colPais.setCellValueFactory(new PropertyValueFactory<Cidade,String>("nomePais"));

        connection = new MySQLConnection();

    }

    public void tabelaInicial(){
        ResultSet result = connection.getCidades();
    }

}