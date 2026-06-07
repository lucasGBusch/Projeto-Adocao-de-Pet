package controller;

import interfaces.ICrud;
import model.Funcionario;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class FuncionarioController implements ICrud<Funcionario, String> {
    private ArrayList<Funcionario> funcionarios;
    private final String ARQUIVO = "funcionarios.dat";

    public FuncionarioController() {
        this.funcionarios = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Funcionario entidade) {
        funcionarios.add(entidade);
        salvarDados();
        Logger.registrar("Funcionario cadastrado: " + entidade.getNome());
    }

    @Override
    public ArrayList<Funcionario> listar() { return funcionarios; }

    @Override
    public boolean atualizar(String cpf, Funcionario entidadeAtualizada) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(cpf)) {
                funcionarios.set(i, entidadeAtualizada);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = funcionarios.removeIf(f -> f.getCpf().equals(cpf));
        if (removido) salvarDados();
        return removido;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) { Logger.registrar("Erro ao salvar funcionarios."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                funcionarios = (ArrayList<Funcionario>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar funcionarios."); }
        }
    }
}