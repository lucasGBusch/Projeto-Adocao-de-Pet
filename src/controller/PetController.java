package controller;

import interfaces.ICrud;
import model.Animal;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class PetController implements ICrud<Animal, Integer> {
    private ArrayList<Animal> pets;
    private final String ARQUIVO = "pets.dat";

    public PetController() {
        this.pets = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Animal entidade) {
        pets.add(entidade);
        salvarDados();
        Logger.registrar("Pet cadastrado: " + entidade.getNome());
    }

    @Override
    public ArrayList<Animal> listar() {
        return pets;
    }

    public ArrayList<Animal> listarDisponiveis() {
        ArrayList<Animal> disponiveis = new ArrayList<>();
        for (Animal p : pets) {
            if (!p.isAdotado()) disponiveis.add(p);
        }
        return disponiveis;
    }

    @Override
    public boolean atualizar(Integer id, Animal entidadeAtualizada) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                pets.set(i, entidadeAtualizada);
                salvarDados();
                Logger.registrar("Pet atualizado. ID: " + id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Integer id) {
        boolean removido = pets.removeIf(p -> p.getId() == id);
        if (removido) {
            salvarDados();
            Logger.registrar("Pet deletado. ID: " + id);
        }
        return removido;
    }

    public Animal buscarPet(int id) {
        for (Animal p : pets) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Animal buscarPet(String nome) {
        for (Animal p : pets) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(pets);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar pets: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                pets = (ArrayList<Animal>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar pets: " + e.getMessage());
            }
        }
    }
}