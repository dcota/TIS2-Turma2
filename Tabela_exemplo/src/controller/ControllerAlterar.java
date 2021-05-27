package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pessoa;

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

    private Pessoa pAlterada;

    public void initialize () {
        //inserir as opções na combobox
        this.cbGenero.getItems().addAll("Feminino", "Masculino", "Outro");
    }

    public void getPessoa(Pessoa p){
        this.tfPrimNome.setText(p.getPrimNome());
        this.tfUltNome.setText(p.getUltNome());
        this.cbGenero.setValue(p.getGenero());
    }

    @FXML
    void alterar(ActionEvent event) {
        if (!this.tfPrimNome.getText().isEmpty() &&
                !this.tfUltNome.getText().isEmpty() &&
                this.cbGenero.getValue()!=null) {
            //ler os valores introduzidos e guardar em variáveis
            String primNome = this.tfPrimNome.getText();
            String ultNome = this.tfUltNome.getText();
            String genero = this.cbGenero.getValue();
            //instanciar novo objeto da classe Pessoa com as alterações
            pAlterada = new Pessoa(primNome, ultNome, genero);
            //fechar a vista e voltar à vista principal
            Stage stage = (Stage) this.btnAlterar.getScene().getWindow();
            stage.close();
        }
        else{
            alertaAviso("Todos os campos devem estar preenchidos!");
        }
        ;
    }

    @FXML
    void cancelar(ActionEvent event) {
        //fechar a vista e voltar à vista principal
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void alertaAviso (String texto) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setTitle("ERRO");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    //método que retorna o objeto com os dados alterados
    public Pessoa getPessoaAlterada (){
        return pAlterada;
    }

}

