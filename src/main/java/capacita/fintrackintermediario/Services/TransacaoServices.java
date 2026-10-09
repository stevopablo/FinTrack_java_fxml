package capacita.fintrackintermediario.Services;


import capacita.fintrackintermediario.DAO.TransacaoDAO;
import capacita.fintrackintermediario.Model.TransacaoModel;
import capacita.fintrackintermediario.Repository.TransacaoRepository;

import java.util.List;

public class TransacaoServices {

    private TransacaoDAO dao;
    private final TransacaoRepository repository;

    public TransacaoServices(){
        this.repository = new TransacaoRepository();
    }


//    public TransacaoServices(){
//        this.dao = new TransacaoDAO();
//    }
    //TODO: validar essas entradas
    public void salvar(TransacaoModel transacaoModel){
        repository.adicionar(transacaoModel);
//        dao.salvar(transacaoModel);
    }

    public List<TransacaoModel> listar(){
        return repository.listar();
//        return dao.listar();
    }

    public void remover(TransacaoModel elemento){
        repository.remover(elemento);
//        dao.remover(idTransacao);
    }

    public void atualizar(TransacaoModel transacaoModel){
//        dao.atualizar(transacaoModel);
    }
}
