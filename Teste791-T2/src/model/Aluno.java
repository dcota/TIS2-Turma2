package model;

public class Aluno {
    private int numAluno;
    private String primNome;
    private String ultNome;
    private int idade;
    private String genero;

    public Aluno(int numAluno, String primNome, String ultNome, int idade, String genero) {
        this.numAluno = numAluno;
        this.primNome = primNome;
        this.ultNome = ultNome;
        this.idade = idade;
        this.genero = genero;
    }

    public int getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
