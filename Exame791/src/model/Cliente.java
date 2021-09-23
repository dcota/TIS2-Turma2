package model;

public class Cliente {
    private String ID;
    private String cliente;
    private String cidade;
    private String pais;

    public Cliente(String ID, String cliente, String cidade, String pais) {
        this.ID = ID;
        this.cliente = cliente;
        this.cidade = cidade;
        this.pais = pais;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


}
