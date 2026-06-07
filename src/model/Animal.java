package model;

import java.io.Serializable;

public abstract class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private int idade;
    private String cpfDono;

    public Animal(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpfDono = null;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getCpfDono() { return cpfDono; }
    public void setCpfDono(String cpfDono) { this.cpfDono = cpfDono; }

    public boolean isAdotado() { return cpfDono != null; }

    public abstract String emitirSom();
}