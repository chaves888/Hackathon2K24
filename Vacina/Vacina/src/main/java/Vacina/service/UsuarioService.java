package Vacina.service;

import Vacina.dao.IdosoDao;
import Vacina.dao.UsuarioDao;
import Vacina.model.Idoso;
import Vacina.model.Usuario;

import java.util.Collections;
import java.util.List;

public class UsuarioService {
    public List<Usuario> listarUsuario() {
        try {
            var dao = new UsuarioDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void salvar(Usuario usuario) {
        try {
            var dao = new UsuarioDao();
            if (usuario.getId() == null) {
                dao.inserir(usuario);
            }else {
                dao.atualizar(usuario);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir (Usuario usuario) {
        try {
            var dao = new UsuarioDao();
            dao.deletar(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
