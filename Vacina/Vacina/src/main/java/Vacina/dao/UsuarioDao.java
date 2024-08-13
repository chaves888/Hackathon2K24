package Vacina.dao;

import Vacina.model.Idoso;
import Vacina.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void inserir(Usuario usuario) throws SQLException {
        String sql = "insert into usuario (nome,email,login,senha,ativo) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getEmail());
        ps.setString(3, usuario.getLogin());
        ps.setString(4, usuario.getSenha());
        ps.setString(5, usuario.getAtivo());
        ps.execute();
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        ResultSet rs = connection.prepareStatement("select * from usuario").executeQuery();
        while (rs.next()) {
            usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("ativo")
            ));
        }
        rs.close();

        return usuarios;
    }
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "update usuario set nome = ?, email = ?, login = ?, senha = ?, ativo = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getEmail());
        ps.setString(3, usuario.getLogin());
        ps.setString(4, usuario.getSenha());
        ps.setString(5, usuario.getAtivo());
        ps.setInt(6,usuario.getId());
        ps.execute();
    }

    public void deletar(Usuario usuario) throws SQLException {
        String sql = "delete from usuario where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, usuario.getId());
        ps.execute();
    }

}

