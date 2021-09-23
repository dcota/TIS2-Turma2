package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField tfNumero;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnSair;

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verificar(ActionEvent event) {
        try {
            //começar por verificar se a caixa de texto está vazia
            if (this.tfNumero.getText().isEmpty()) {
                alerta("Tem de indicar um número!");
            } else {
                try {
                    //tenta converter o valor introduzido para inteiro
                    //se contém caracteres inválidos lança exceção apanhada na linha 54
                    Long num = Long.parseLong(this.tfNumero.getText());
                    //chama o método checkPar e guarda o valor de retorno
                    boolean resultado =checkPar(num);
                    //prepara e envia a resposta para a segunda vista
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/secondView.fxml"));
                    Parent root = loader.load();
                    SecondController controller = loader.getController();
                    controller.getResposta(resultado);
                    //lança a segunda vista depois de passar o resultado da verificação
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                    //depois de voltar da segunda vista limpa o formulário
                    this.tfNumero.setText("");
                } catch (NumberFormatException e) {
                    alerta("Valor com caracteres inválidos!");
                }
            }
        } catch (IOException e) {
            alerta("Ocorreu um erro, tente de novo.");
        }
    }

    public static boolean checkPar(Long numero){
        boolean ver = numero % 2 == 0 ? true : false;
        return ver;
        /*if(numero%2==0) {
            return true;
        }
        else return false;*/
    }

    @FXML
    void alerta(String txt){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ATENÇÃO");
        alerta.setContentText(txt);
        alerta.showAndWait();
        this.tfNumero.setText("");
    }

}

