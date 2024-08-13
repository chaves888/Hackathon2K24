package Vacina.dao;

import Vacina.model.Idoso;
import Vacina.model.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao {
    private Connection connection;

    public VacinaDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vacina?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void inserir (Vacina vacina) throws SQLException {
        String sql = "insert into vacina (nome) values(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, vacina.getNome());
        ps.execute();
    }

    public List<Vacina> listarTodos() throws SQLException {
        List<Vacina> vacinas = new ArrayList<Vacina>();

        ResultSet rs = connection.prepareStatement("select * from vacina").executeQuery();
        while (rs.next()) {
            vacinas.add(new Vacina(
                    rs.getInt("id"),
                    rs.getString("nome")
            ));
        }
        rs.close();

        return vacinas;
    }
    public void atualizar(Vacina vacina) throws SQLException {
        String sql = "update vacina set nome = ? where id = ?" ;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, vacina.getNome());
        ps.setInt(2,vacina.getId());
        ps.execute();
    }
    public void deletar(Vacina vacina) throws SQLException {
        String sql = "delete from vacina where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, vacina.getId());
        ps.execute();
    }
}
