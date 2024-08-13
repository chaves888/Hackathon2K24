package Vacina.service;

import Vacina.dao.HistoricoDao;
import Vacina.dao.IdosoDao;
import Vacina.model.Historico;
import Vacina.model.Idoso;

import java.util.Collections;
import java.util.List;

public class HistoricoService {
    public List<Historico> listarHistorico() {
        try {
            var dao = new HistoricoDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
