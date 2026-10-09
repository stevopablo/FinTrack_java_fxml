package capacita.fintrackintermediario.Repository;

import java.util.List;

public interface GenericsRepository<T> {
    void adicionar(T elemento);

    void remover(T elemento);

    List<T> listar();
}
