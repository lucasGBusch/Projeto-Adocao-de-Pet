package model;

public class Cachorro extends Animal {
    private static final long serialVersionUID = 1L;
    private String porte;

    public Cachorro(int id, String nome, int idade, String porte) {
        super(id, nome, idade);
        this.porte = porte;
    }

    public String getPorte() { return porte; }
    public void setPorte(String porte) { this.porte = porte; }

    @Override
    public String emitirSom() { return "Au Au!"; }
}