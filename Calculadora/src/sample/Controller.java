package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML TextField tfNum1;
    @FXML TextField tfNum2;
    @FXML TextField tfResultado;
    @FXML RadioButton rbSoma;
    @FXML RadioButton rbSubtr;
    @FXML RadioButton rbMult;
    @FXML RadioButton rbDiv;
    @FXML Button btnCalcular;
    @FXML Button btnLimpar;

    private ToggleGroup tg = new ToggleGroup();

    public void initialize() {
        //criar toogle group
        this.rbDiv.setToggleGroup(tg);
        this.rbSoma.setToggleGroup(tg);
        this.rbSubtr.setToggleGroup(tg);
        this.rbMult.setToggleGroup(tg);
    }

    public void calcular(ActionEvent event) {
        try {
            //ir buscar os valores às caixas de texto Num1 e Num2
            String num1 = this.tfNum1.getText();
            String num2 = this.tfNum2.getText();
            //converter os valores de string para double
            double n1 = 0;
            double n2 = 0;
            if (num1.isEmpty() || num2.isEmpty()) {
                alertaErro("Os valores não podem estar vazios!");
            }
            else {
                n1 = Double.parseDouble(num1);
                n2 = Double.parseDouble(num2);
                double resultado = 0;
                //verificar qual foi a operação escolhida
                if (this.rbSoma.isSelected()) {
                    resultado = n1 + n2;
                    this.tfResultado.setText(String.valueOf(resultado));
                    alertaSucesso("Soma");
                    limpar();
                } else if (this.rbSubtr.isSelected()) {
                    resultado = n1 - n2;
                    this.tfResultado.setText(String.valueOf(resultado));
                    alertaSucesso("Subtração");
                    limpar();
                } else if (this.rbMult.isSelected()) {
                    resultado = n1 * n2;
                    this.tfResultado.setText(String.valueOf(resultado));
                    alertaSucesso("Multiplicação");
                    limpar();
                } else if (this.rbDiv.isSelected()) {
                    if (n2!=0) {
                        resultado = (double) n1 / n2;
                        this.tfResultado.setText(String.valueOf(resultado));
                        alertaSucesso("Divisão");
                        limpar();
                    }
                    else {
                        alertaErro("Divisão por zero!");
                    }
                }
            }
        }
        catch (NumberFormatException e) {
            alertaErro("Formato de números inválido! Verifique os valores...");
        }
    }

    public void alertaSucesso (String texto) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText(null);
        alerta.setContentText(texto + " efetuada com sucesso!");
        alerta.showAndWait();
    }

    public void alertaErro (String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        alerta.setHeaderText(null);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    public void limpar() {
        this.tfNum1.clear();
        this.tfNum2.clear();
        this.tfResultado.clear();
        this.rbDiv.setSelected(false);
        this.rbSubtr.setSelected(false);
        this.rbSoma.setSelected(false);
        this.rbMult.setSelected(false);
    }

}
