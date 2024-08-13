package Vacina.service;


import Vacina.dao.IdosoDao;
import Vacina.model.Idoso;

import java.util.Collections;
import java.util.List;

public class IdosoService {
    public List<Idoso> listarIdoso() {
        try {
            var dao = new IdosoDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void salvar(Idoso idoso) {
        try {
            var dao = new IdosoDao();
            if (idoso.getId() == null) {
                dao.inserir(idoso);
            }else {
                dao.atualizar(idoso);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir (Idoso idoso) {
        try {
            var dao = new IdosoDao();
            dao.deletar(idoso);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}