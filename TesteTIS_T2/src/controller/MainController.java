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
    private Button btnValidar;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfAno;

    private String resposta;

    @FXML
    void validar(ActionEvent event) {
        try {
            //começar por verificar se a caixa de texto está vazia
            if(this.tfAno.getText().isEmpty()){
                alertaAtencao("Tem de indicar um ano!");
            } else {
                try {
                    //tenta converter o valor introduzido para inteiro
                    //se contém caracteres inválidos lança exceção apanhada na linha 71
                    long ano = Long.parseLong(this.tfAno.getText());
                    //se o valor da caixa tiver mais do que 6 caracteres informa
                    if(this.tfAno.getText().length() > 6){
                        alertaAtencao("No máximo, o ano pode ter 6 dígitos");
                    }
                    //se não verifica se é ano bissexto e preenche a variável resposta
                    else {
                        if ( ( ano % 4 == 0 && ano % 100 != 0 ) || (ano % 400 == 0) ) {
                            resposta = "O ano " + ano + " é bissexto.";
                        } else {
                            resposta = "O ano " + ano + " não é bissexto.";
                        }
                        //prepara e envia a resposta para a segunda vista
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/second.fxml"));
                        Parent root = loader.load();
                        SecondController controller = loader.getController();
                        controller.getResposta(resposta);
                        //lança a segunda vista depois de passar a resposta
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();
                        //depois de voltar da segunda vista limpa o formulário
                        this.tfAno.setText("");
                    }
                } catch (NumberFormatException e) {
                    alertaAtencao("Valor com caracteres inválidos!");
                }
            }
        } catch (IOException e) {
            alertaAtencao("Ocorreu um erro, tente de novo.");
        }
    }

    //método para lançar alertas com mensagem variável
    @FXML
    void alertaAtencao(String txt){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ATENÇÃO");
        alerta.setContentText(txt);
        alerta.showAndWait();
        this.tfAno.setText("");
    }

    //método para fechar a vista
    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }
}