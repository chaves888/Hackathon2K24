package Vacina.service;


import Vacina.dao.AgenteDao;
import Vacina.dao.VacinaDao;
import Vacina.model.AgenteDeSaude;
import Vacina.model.Vacina;

import java.util.Collections;
import java.util.List;

public class AgenteDeSaudeService {

    public List<AgenteDeSaude> listarAgente() {
    try {
        var dao = new AgenteDao();
        return dao.listarTodos();
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return Collections.emptyList();
    }
}

    public void salvar(AgenteDeSaude agenteDeSaude) {
        try {
            var dao = new AgenteDao();
            if (agenteDeSaude.getId() == null) {
                dao.inserir(agenteDeSaude);
            }else {
                dao.atualizar(agenteDeSaude);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir (AgenteDeSaude agenteDeSaude) {
        try {
            var dao = new AgenteDao();
            dao.deletar(agenteDeSaude);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
