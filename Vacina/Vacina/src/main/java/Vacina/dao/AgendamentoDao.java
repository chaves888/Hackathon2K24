package Vacina.dao;

import Vacina.model.Agendamento;
import Vacina.model.AgenteDeSaude;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDao {
    private Connection connection;

    public AgendamentoDao() throws SQLException {
    try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
} catch (Exception e) {
    throw new SQLException(e.getMessage());
}
}
    public void inserir(Agendamento agendamento) throws SQLException {
        String sql = "insert into agendamento (nome_idoso, nome_agente, nome_vacina, agendamento, status) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agendamento.getIdoso());
        ps.setString(2,agendamento.getAgente());
        ps.setString(3,agendamento.getVacina());
        ps.setDate(4, Date.valueOf(agendamento.getAtendimento()));
        ps.setString(5,agendamento.getStatus());
        ps.execute();
    }

    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<Agendamento>();

        ResultSet rs = connection.prepareStatement("select * from agendamento").executeQuery();
        while (rs.next()) {
           agendamentos.add(new Agendamento(
                    rs.getInt("id"),
                    rs.getString("nome_idoso"),
                    rs.getString("nome_agente"),
                    rs.getString("nome_vacina"),
                    rs.getDate("agendamento").toLocalDate(),
                    rs.getString("status")));
        }
        rs.close();

        return agendamentos;
    }
    public void atualizar(Agendamento agendamento) throws SQLException {
        String sql = "update agendamento set nome_idoso = ?, nome_agente = ?,nome_vacina = ?,agendamento = ?,status = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agendamento.getIdoso());
        ps.setString(2, agendamento.getAgente());
        ps.setString(3,agendamento.getVacina());
        ps.setDate(4, Date.valueOf(agendamento.getAtendimento()));
        ps.setString(5,agendamento.getStatus());
        ps.setInt(6,agendamento.getId());

        ps.execute();
    }

    public void deletar(Agendamento agendamento) throws SQLException {
        String sql = "delete from agendamento where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agendamento.getId());
        ps.execute();
    }
}
