package model;

import java.io.Serializable;

public class DoacaoFinanceira implements Serializable {
    private static final long serialVersionUID = 1L;


    private String nome;
    private String CPF;
    private float doacao;
    private boolean valorRecebido;

    public DoacaoFinanceira(boolean valorRecebido, float doacao, String nome, String CPF) {
        this.valorRecebido = valorRecebido;
        this.doacao = doacao;
        this.nome = nome;
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public float getDoacao() {
        return doacao;
    }

    public void setDoacao(float doacao) {
        this.doacao = doacao;
    }

    public boolean isValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(boolean valorRecebido) {
        this.valorRecebido = valorRecebido;
    }


}