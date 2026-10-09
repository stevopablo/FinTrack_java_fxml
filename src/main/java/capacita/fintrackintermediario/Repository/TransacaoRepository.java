package capacita.fintrackintermediario.Repository;

import capacita.fintrackintermediario.DAO.TransacaoDAO;
import capacita.fintrackintermediario.Model.TransacaoModel;

import java.util.List;

public class TransacaoRepository implements GenericsRepository<TransacaoModel> {

    private final TransacaoDAO dao;

    public TransacaoRepository() {
        this.dao = new TransacaoDAO();
    }

    @Override
    public void adicionar(TransacaoModel elemento) {
        dao.salvar(elemento);
    }

    @Override
    public void remover(TransacaoModel elemento) {
        dao.remover(elemento.getId());
    }

//    @Override
//    public void remover(Integer idElemento) {
//        dao.remover(idElemento);
//    }


    @Override
    public List<TransacaoModel> listar() {
        return dao.listar();
    }
}