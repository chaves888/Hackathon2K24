package Vacina.dao;

import Vacina.model.AgenteDeSaude;
import Vacina.model.Historico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao {
    private Connection connection;

    public HistoricoDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public List<Historico> listarTodos() throws SQLException {
        List<Historico> historicos = new ArrayList<Historico>();

        ResultSet rs = connection.prepareStatement("SELECT nome, observacao FROM tabela_idoso"
            ).executeQuery();
        while (rs.next()) {
            historicos.add(new Historico(
                    rs.getString("nome"),
                    rs.getString("observacao")));
        }
        rs.close();

        return historicos;
    }
}
