package service;

import database.Conexao;
import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    public Livro buscarPorTituloEAutor(String titulo, String autor) {
        String sql = "SELECT * FROM livro WHERE titulo ILIKE ? AND autor ILIKE ?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo.trim() + "%");
            stmt.setString(2, "%" + autor.trim() + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("codigo"),
                        rs.getBoolean("disponivel"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
        return null;
    }

    public boolean marcarComoEmprestado(String codigo) {
        String sql = "UPDATE livro SET disponivel = FALSE WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do livro: " + e.getMessage());
            return false;
        }
    }

    public boolean marcarComoDisponivel(String codigo) {
        String sql = "UPDATE livro SET disponivel = TRUE WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do livro: " + e.getMessage());
            return false;
        }
    }
}
