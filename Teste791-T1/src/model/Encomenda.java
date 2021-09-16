package model;

public class Encomenda {
    private int ID;
    private String cliente;
    private String dataEnc;
    private String dataEnv;

    public Encomenda(int ID, String cliente, String dataEnc, String dataEnv) {
        this.ID = ID;
        this.cliente = cliente;
        this.dataEnc = dataEnc;
        this.dataEnv = dataEnv;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataEnc() {
        return dataEnc;
    }

    public void setDataEnc(String dataEnc) {
        this.dataEnc = dataEnc;
    }

    public String getDataEnv() {
        return dataEnv;
    }

    public void setDataEnv(String dataEnv) {
        this.dataEnv = dataEnv;
    }
}
