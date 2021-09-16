package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Encomenda;
import model.MySQLConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EncomendasController implements Initializable {

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Encomenda> tblEncomendas;

    @FXML
    private TableColumn<Encomenda, Integer> colID;

    @FXML
    private TableColumn<Encomenda, String> colCliente;

    @FXML
    private TableColumn<Encomenda, String> colDataEnc;

    @FXML
    private TableColumn<Encomenda, String> colDataEnv;

    @FXML
    private Button btnDetalhe;

    private MySQLConnection connection;

    private ObservableList<Encomenda> listaEncomendas;

    @FXML
    void detalhe(ActionEvent event) {
        Encomenda e = tblEncomendas.getSelectionModel().getSelectedItem();
        if(e==null) {
            alerta(Alert.AlertType.WARNING,"ATENÇÃO","Tem de selecionar uma encomenda...");
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/detalheView.fxml"));
                Parent root = loader.load();
                DetalheController controller = loader.getController();
                controller.getIDEnc(e.getID());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new MySQLConnection();
        listaEncomendas = FXCollections.observableArrayList();
        this.tblEncomendas.setItems(listaEncomendas);
        this.colID.setCellValueFactory(new PropertyValueFactory<Encomenda,Integer>("ID"));
        this.colCliente.setCellValueFactory(new PropertyValueFactory<Encomenda,String>("cliente"));
        this.colDataEnc.setCellValueFactory(new PropertyValueFactory<Encomenda,String>("dataEnc"));
        this.colDataEnv.setCellValueFactory(new PropertyValueFactory<Encomenda,String>("dataEnv"));

        fillTable();
    }

    private void fillTable(){
        try {
            ResultSet result = connection.getEncomendas();
            while(result.next()){
                int ID = result.getInt(1);
                String cliente = result.getString(2);
                String dataEnc = result.getString(3);
                String dataEnv = result.getString(4);
                Encomenda e = new Encomenda(ID,cliente,dataEnc,dataEnv);
                listaEncomendas.add(e);
            }
            this.tblEncomendas.setItems(listaEncomendas);
            this.tblEncomendas.refresh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void alerta(Alert.AlertType tipo, String tit, String txt) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(tit);
        alerta.setContentText(txt);
        alerta.showAndWait();
    }
}
