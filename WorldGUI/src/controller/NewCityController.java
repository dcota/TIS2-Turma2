package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cidade;
import model.MySQLConnection;
import model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewCityController {

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfDistrito;

    @FXML
    private TextField tfPopulacao;

    @FXML
    private Button btnFechar;

    @FXML
    private ComboBox<String> cbPais;

    @FXML
    private Button btnInserir;

    ArrayList<Pais> listaPaises = new ArrayList<Pais>();

    MySQLConnection connection;

    public void initialize(){
        connection = new MySQLConnection();
        try{
            ResultSet resultado = connection.getPaises();
            while(resultado.next()){
                String codPais = resultado.getString(1);
                String nomePais = resultado.getString(2);
                Pais p = new Pais(nomePais,codPais);
                listaPaises.add(p);
                this.cbPais.getItems().add(p.getNomePais());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void inserir(ActionEvent event) {
        String nomeCidade = this.tfCidade.getText();
        String nomePais = this.cbPais.getValue();
        String distrito = this.tfDistrito.getText();
        String pop_s = this.tfPopulacao.getText();
        int pop = Integer.parseInt(pop_s);
        String codPais = null;
        for(Pais p :listaPaises){
            if(nomePais.equals(p.getNomePais())){
                codPais=p.getCodPais();
            }
        }
        Cidade c = new Cidade(nomeCidade,codPais,distrito,pop);
        if(connection.inserirCidade(c)) {
            alerta(Alert.AlertType.INFORMATION,"SUCESSO","Cidade adicionada com sucesso!");
        } else {
            alerta(Alert.AlertType.ERROR,"ATENÇÃO","Ocorreu um problema!");
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
