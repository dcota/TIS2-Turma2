package model;

public class Cidade {
    private int ID;
    private String nomeCidade;
    private String nomePais;

    public Cidade(int ID, String nomeCidade, String nomePais) {
        this.nomeCidade = nomeCidade;
        this.nomePais = nomePais;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
