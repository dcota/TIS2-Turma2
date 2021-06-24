package model;

public class Cidade {

    private String nomeCidade;
    private String nomePais;

    public Cidade(String nomeCidade, String nomePais) {
        this.nomeCidade = nomeCidade;
        this.nomePais = nomePais;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

}
