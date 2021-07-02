package model;

public class Pais {
    private String nomePais;
    private String codPais;

    public Pais(String nomePais, String codPais) {
        this.nomePais = nomePais;
        this.codPais = codPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }
}
