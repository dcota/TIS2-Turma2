package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML TextField tfNum1;
    @FXML TextField tfNum2;
    @FXML TextField tfResultado;
    @FXML RadioButton rbSoma;
    @FXML RadioButton rbSubtr;
    @FXML RadioButton rbMult;
    @FXML RadioButton rbDiv;
    @FXML Button btnCalcular;

    private ToggleGroup tg = new ToggleGroup();

    public void initialize() {
        //criar toogle group
        this.rbDiv.setToggleGroup(tg);
        this.rbSoma.setToggleGroup(tg);
        this.rbSubtr.setToggleGroup(tg);
        this.rbMult.setToggleGroup(tg);

    }

    public void calcular(ActionEvent event) {
        //ir buscar os valores às caixas de texto Num1 e Num2
        String num1 = this.tfNum1.getText();
        String num2 = this.tfNum2.getText();
        //converter os valores de string para double
        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);
        //
    }

}
