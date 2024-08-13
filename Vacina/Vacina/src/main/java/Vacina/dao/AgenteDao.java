package Vacina.dao;

import Vacina.model.AgenteDeSaude;
import Vacina.model.Idoso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenteDao {
    private Connection connection;

    public AgenteDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
    public void inserir(AgenteDeSaude agente) throws SQLException {
        String sql = "insert into agente (nome) values(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agente.getNome());
        ps.execute();
    }

    public List<AgenteDeSaude> listarTodos() throws SQLException {
        List<AgenteDeSaude> agenteDeSaudes = new ArrayList<AgenteDeSaude>();

        ResultSet rs = connection.prepareStatement("select * from agente").executeQuery();
        while (rs.next()) {
            agenteDeSaudes.add(new AgenteDeSaude(
                    rs.getInt("id"),
                    rs.getString("nome")));
        }
        rs.close();

        return agenteDeSaudes;
    }
    public void atualizar(AgenteDeSaude agenteDeSaude) throws SQLException {
        String sql = "update agente set nome = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agenteDeSaude.getNome());
        ps.setInt(2,agenteDeSaude.getId());

        ps.execute();
    }

    public void deletar(AgenteDeSaude agenteDeSaude) throws SQLException {
        String sql = "delete from agente where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agenteDeSaude.getId());
        ps.execute();
    }
}
