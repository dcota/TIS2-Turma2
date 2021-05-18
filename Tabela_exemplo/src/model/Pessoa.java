package model;

import java.util.Objects;

public class Pessoa {
    String primNome;
    String ultNome;
    String genero;

    public Pessoa(String primNome, String ultNome, String genero) {
        this.primNome = primNome;
        this.ultNome = ultNome;
        this.genero = genero;
    }

    public String getPrimNome() {
        return primNome;
    }

    public void setPrimNome(String primNome) {
        this.primNome = primNome;
    }

    public String getUltNome() {
        return ultNome;
    }

    public void setUltNome(String ultNome) {
        this.ultNome = ultNome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(primNome, pessoa.primNome) && Objects.equals(ultNome, pessoa.ultNome) && Objects.equals(genero, pessoa.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primNome, ultNome, genero);
    }
}
