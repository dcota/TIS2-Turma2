package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Controller {

    @FXML Button btnMudaTexto;

    @FXML TextField tfOrigem;

    @FXML TextArea tfDestino;

    @FXML Label lbLinguagemEscolhida;

    @FXML ComboBox<String> cbLinguagem;

    @FXML CheckBox chkOp1;

    @FXML CheckBox chkOp2;

    @FXML CheckBox chkOp3;

    @FXML TextArea taLinguagensEscolhidas;

    @FXML Button btnMudaLinguagem;

    @FXML Label lblErroSelecaoChkBox;

    @FXML RadioButton rb1;

    @FXML RadioButton rb2;

    @FXML RadioButton rb3;

    @FXML Label lblRadioButton;

    private String textoOrigem="";

    private String textoLabel;

    private ToggleGroup tg;

    public void initialize() {

        //preencher combobox com linguagens de programação
        this.cbLinguagem.getItems().add("Java");
        this.cbLinguagem.getItems().add("C++");
        this.cbLinguagem.getItems().add("Python");
        this.cbLinguagem.getItems().add("C");
        this.cbLinguagem.getItems().add("Javascript");
        this.cbLinguagem.getItems().add("PHP");

        //guarda o texto da label
        textoLabel = this.lbLinguagemEscolhida.getText();

        //criar um togglegroup para os radiobuttons
        tg = new ToggleGroup();
        this.rb1.setToggleGroup(tg);
        this.rb2.setToggleGroup(tg);
        this.rb3.setToggleGroup(tg);

    }

    public void mudaTexto (ActionEvent event) {

        if(this.tfOrigem.getText().equals("") == false ){
            textoOrigem += this.tfOrigem.getText() + "\n";
            this.tfDestino.setText(textoOrigem);
            this.tfOrigem.setText("");
        }

    }

    public void selecionaLinguagem (ActionEvent event){

        this.lbLinguagemEscolhida.setText(textoLabel + " " + this.cbLinguagem.getValue());

    }

    public void selecionaLinguagemChkBox (ActionEvent event){
        String texto = "";
        this.taLinguagensEscolhidas.setText("");
        if(this.chkOp1.isSelected()){
            texto += this.chkOp1.getText() + "\n";
        }
        if(this.chkOp2.isSelected()){
            texto += this.chkOp2.getText() + "\n";
        }
        if(this.chkOp3.isSelected()) {
            texto += this.chkOp3.getText() + "\n";
        }
        if(texto.equals("")){
            this.lblErroSelecaoChkBox.setText("Deve escolher uma opção!");
        }
        else{
            this.lblErroSelecaoChkBox.setText("");
            this.taLinguagensEscolhidas.setText(texto);
        }
    }
}
