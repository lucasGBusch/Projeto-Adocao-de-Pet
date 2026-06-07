package model;

public class Gato extends Animal {
    private static final long serialVersionUID = 1L;
    private String pelagem;

    public Gato(int id, String nome, int idade, String pelagem) {
        super(id, nome, idade);
        this.pelagem = pelagem;
    }

    public String getPelagem() { return pelagem; }
    public void setPelagem(String pelagem) { this.pelagem = pelagem; }

    @Override
    public String emitirSom() { return "Miau!"; } // Polimorfismo de Sobrescrita
}