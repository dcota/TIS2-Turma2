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
import model.Aluno;
import model.MySQLConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AlunosController implements Initializable {

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Aluno> tblAlunos;

    @FXML
    private TableColumn<Aluno, Integer> colNumAluno;

    @FXML
    private TableColumn<Aluno, String> colPriNome;

    @FXML
    private TableColumn<Aluno, String> colUltNome;

    @FXML
    private TableColumn<Aluno, Integer> colIdade;

    @FXML
    private TableColumn<Aluno, String> colGenero;

    @FXML
    private Button btnDetalhe;

    private MySQLConnection connection;

    private ObservableList<Aluno> listaAlunos;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new MySQLConnection();
        listaAlunos = FXCollections.observableArrayList();
        this.tblAlunos.setItems(listaAlunos);
        this.colNumAluno.setCellValueFactory(new PropertyValueFactory<Aluno,Integer>("numAluno"));
        this.colPriNome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("primNome"));
        this.colUltNome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("ultNome"));
        this.colIdade.setCellValueFactory(new PropertyValueFactory<Aluno,Integer>("idade"));
        this.colGenero.setCellValueFactory(new PropertyValueFactory<Aluno,String>("genero"));

        fillTable();

    }

    private void fillTable(){
        try {
            ResultSet result = connection.getAlunos();
            while(result.next()){
                int numAluno = result.getInt(1);
                String primNome = result.getString(2);
                String ultNome = result.getString(3);
                int idade = result.getInt(4);
                String genero = result.getString(5);
                Aluno a = new Aluno(numAluno,primNome,ultNome,idade,genero);
                listaAlunos.add(a);
            }
            this.tblAlunos.setItems(listaAlunos);
            this.tblAlunos.refresh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    private void detalhe(){
        Aluno a = tblAlunos.getSelectionModel().getSelectedItem();
        if(a==null) {
            alerta(Alert.AlertType.WARNING,"ATENÇÃO","Tem de selecionar um aluno...");
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/detalheView.fxml"));
                Parent root = loader.load();
                DetalheController controller = loader.getController();
                controller.getNumAluno(a.getNumAluno());
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

    private void alerta(Alert.AlertType tipo, String tit, String txt) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(tit);
        alerta.setContentText(txt);
        alerta.showAndWait();
    }
}
