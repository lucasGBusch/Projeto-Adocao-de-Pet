package controller;

import interfaces.ICrud;
import model.Cliente;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class ClienteController implements ICrud<Cliente, String> {
    private ArrayList<Cliente> clientes;
    private final String ARQUIVO = "clientes.dat";

    public ClienteController() {
        this.clientes = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Cliente entidade) {
        clientes.add(entidade);
        salvarDados();
        Logger.registrar("Cliente cadastrado CPF: " + entidade.getCpf());
    }

    @Override
    public ArrayList<Cliente> listar() { return clientes; }

    @Override
    public boolean atualizar(String cpf, Cliente entidadeAtualizada) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                clientes.set(i, entidadeAtualizada);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = clientes.removeIf(c -> c.getCpf().equals(cpf));
        if (removido) salvarDados();
        return removido;
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(clientes);
        } catch (IOException e) { Logger.registrar("Erro ao salvar clientes."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                clientes = (ArrayList<Cliente>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar clientes."); }
        }
    }
}