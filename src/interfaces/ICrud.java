package interfaces;

import java.util.ArrayList;

public interface ICrud<T, ID> {
    void cadastrar(T entidade);
    ArrayList<T> listar();
    boolean atualizar(ID id, T entidade);
    boolean deletar(ID id);
}