package controller;

import interfaces.ICrud;
import model.DoacaoFinanceira;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class DoacaoFinanceiraController implements ICrud<DoacaoFinanceira, Integer> {

    private ArrayList<DoacaoFinanceira> doacoes;
    private final String ARQUIVO = "doacoes.dat";

    public DoacaoFinanceiraController() {
        this.doacoes = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(DoacaoFinanceira entidade) {
        doacoes.add(entidade);
        salvarDados();
        Logger.registrar("Doação registrada - Doador: " + entidade.getNome() + " | Valor: R$ " + entidade.getDoacao());
    }

    @Override
    public ArrayList<DoacaoFinanceira> listar() {
        return doacoes;
    }

    @Override
    public boolean atualizar(Integer indice, DoacaoFinanceira entidadeAtualizada) {
        if (indice >= 0 && indice < doacoes.size()) {
            doacoes.set(indice, entidadeAtualizada);
            salvarDados();
            Logger.registrar("Doação atualizada no índice " + indice + " - Novo Doador: " + entidadeAtualizada.getNome());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(Integer indice) {
        if (indice >= 0 && indice < doacoes.size()) {
            doacoes.remove(indice.intValue());
            salvarDados();
            Logger.registrar("Doação removida no índice " + indice);
            return true;
        }
        return false;
    }

    public boolean confirmarRecebimento(Integer indice) {
        if (indice >= 0 && indice < doacoes.size()) {
            DoacaoFinanceira d = doacoes.get(indice);
            d.setValorRecebido(true);
            salvarDados();
            Logger.registrar("Status da doação no índice " + indice + " alterado para RECEBIDO.");
            return true;
        }
        return false;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(doacoes);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar doações financeiras: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                doacoes = (ArrayList<DoacaoFinanceira>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar doações financeiras: " + e.getMessage());
            }
        }
    }
}