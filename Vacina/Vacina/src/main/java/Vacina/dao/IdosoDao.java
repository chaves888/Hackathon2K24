package Vacina.dao;

import Vacina.model.Idoso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdosoDao {
    private Connection connection;

    public IdosoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void inserir(Idoso idoso) throws SQLException {
        String sql = "insert into tabela_idoso (nome,observacao) values(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idoso.getNome());
        ps.setString(2, idoso.getObservacao());
        ps.execute();
    }

    public List<Idoso> listarTodos() throws SQLException {
        List<Idoso> idosos = new ArrayList<Idoso>();

        ResultSet rs = connection.prepareStatement("select * from tabela_idoso").executeQuery();
        while (rs.next()) {
            idosos.add(new Idoso(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("observacao")
            ));
        }
        rs.close();

        return idosos;
    }
    public void atualizar(Idoso idoso) throws SQLException {
        String sql = "update tabela_idoso set nome = ?, observacao = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idoso.getNome());
        ps.setString(2, idoso.getObservacao());
        ps.setInt(3,idoso.getId());
        ps.execute();
    }

    public void deletar(Idoso idoso) throws SQLException {
        String sql = "delete from tabela_idoso where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idoso.getId());
        ps.execute();
    }

}
