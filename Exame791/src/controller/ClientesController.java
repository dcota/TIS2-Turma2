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
import model.Cliente;
import model.MySQLConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, String> colID;

    @FXML
    private TableColumn<Cliente, String> colCliente;

    @FXML
    private TableColumn<Cliente, String> colCidade;

    @FXML
    private TableColumn<Cliente, String> colPais;

    @FXML
    private Button btnDetalhe;

    private MySQLConnection connection;

    private ObservableList<Cliente> listaEncomendas;

    @FXML
    void detalhe(ActionEvent event) {
        Cliente c = this.tblClientes.getSelectionModel().getSelectedItem();
        if(c==null) {
            alerta(Alert.AlertType.WARNING,"ATENÇÃO","Tem de selecionar um cliente...");
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/detalheView.fxml"));
                Parent root = loader.load();

                DetalheController controller = loader.getController();
                controller.getIDEnc(c.getID());

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
        this.tblClientes.setItems(listaEncomendas);
        this.colID.setCellValueFactory(new PropertyValueFactory<Cliente,String>("ID"));
        this.colCliente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cliente"));
        this.colCidade.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cidade"));
        this.colPais.setCellValueFactory(new PropertyValueFactory<Cliente,String>("pais"));

        fillTable();
    }

    private void fillTable(){
        try {
            ResultSet result = connection.getClientes();
            while(result.next()){
                String ID = result.getString(1);
                String cliente = result.getString(2);
                String cidade = result.getString(3);
                String pais = result.getString(4);
                Cliente e = new Cliente(ID,cliente,cidade,pais);
                listaEncomendas.add(e);
            }
            this.tblClientes.setItems(listaEncomendas);
            this.tblClientes.refresh();
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
