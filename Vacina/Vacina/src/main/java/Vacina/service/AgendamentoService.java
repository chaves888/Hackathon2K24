package Vacina.service;


import Vacina.dao.AgendamentoDao;
import Vacina.dao.AgenteDao;
import Vacina.model.Agendamento;
import Vacina.model.AgenteDeSaude;

import java.util.Collections;
import java.util.List;

public class AgendamentoService {

    public List<Agendamento> listarAgendamento() {
        try {
            var dao = new AgendamentoDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void salvar(Agendamento agendamento) {
        try {
            var dao = new AgendamentoDao();
            if (agendamento.getId() == null) {
                dao.inserir(agendamento);
            }else {
                dao.atualizar(agendamento);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir (Agendamento agendamento) {
        try {
            var dao = new AgendamentoDao();
            dao.deletar(agendamento);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
