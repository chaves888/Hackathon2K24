package Vacina.service;


import Vacina.dao.IdosoDao;
import Vacina.dao.VacinaDao;
import Vacina.model.Idoso;
import Vacina.model.Vacina;

import java.util.Collections;
import java.util.List;

public class VacinaService {
    public List<Vacina> listarVacina() {
        try {
            var dao = new VacinaDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void salvar(Vacina vacina) {
        try {
            var dao = new VacinaDao();
            if (vacina.getId() == null) {
                dao.inserir(vacina);
            }else {
                dao.atualizar(vacina);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir (Vacina vacina) {
        try {
            var dao = new VacinaDao();
            dao.deletar(vacina);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

