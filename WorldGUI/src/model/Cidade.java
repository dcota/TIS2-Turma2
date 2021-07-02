package model;

import java.util.Objects;

public class Cidade {
    //atributos
    private int ID;
    private String nomeCidade;
    private String nomePais;
    private String codPais;
    private String distrito;
    private int pop;

    //construtor
    public Cidade(int ID, String nomeCidade, String nomePais) {
        this.nomeCidade = nomeCidade;
        this.nomePais = nomePais;
        this.ID = ID;
    }

    public Cidade(String nomeCidade, String codPais, String distrito, int pop) {
        this.nomeCidade = nomeCidade;
        this.codPais = codPais;
        this.distrito = distrito;
        this.pop = pop;
    }

    //getters e setters

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
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

    //hash e equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return ID == cidade.ID && Objects.equals(nomeCidade, cidade.nomeCidade) && Objects.equals(nomePais, cidade.nomePais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nomeCidade, nomePais);
    }
}
