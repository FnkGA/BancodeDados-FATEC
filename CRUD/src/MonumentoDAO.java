package br.edu.fatecfranca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonumentoDAO {

    // CREATE
    public void inserir(String nome, String endereco, String artista, String descricao, String decreto, String idLocalidade) {

        String sql = "INSERT INTO monumento (nome, endereco, artista, descricao, decreto, id_localidade) VALUES (?,?,?,?,?,?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, artista);
            stmt.setString(4, descricao);
            stmt.setString(5, decreto);
            stmt.setString(6, idLocalidade);

            stmt.executeUpdate();
            System.out.println("Monumento cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir monumento: " + e.getMessage());
        }
    }

    
    public List<String> listar() {
        List<String> monumentos = new ArrayList<>();
        String sql = "SELECT * FROM monumento ORDER BY id_monumento";

        try (Connection conexao = ConnectionFactory.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                monumentos.add(
                        rs.getInt("id_monumento") + " - " +
                                rs.getString("nome") + " - " +
                                rs.getString("endereco") + " - " +
                                rs.getString("artista")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar monumentos: " + e.getMessage());
        }

        return monumentos;
    }

    // READ - buscar por nome
    public List<String> buscarPorNome(String nome) {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT * FROM monumento WHERE nome ILIKE ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado.add(
                            rs.getInt("id_monumento") + " - " +
                                    rs.getString("nome") + " - " +
                                    rs.getString("endereco")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar monumentos: " + e.getMessage());
        }

        return resultado;
    }

    // UPDATE
    public void atualizar(int id, String nome, String endereco, String artista, String descricao, String decreto, String idLocalidade) {

        String sql = "UPDATE monumento SET nome=?, endereco=?, artista=?, descricao=?, decreto=?, id_localidade=? WHERE id_monumento=?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, artista);
            stmt.setString(4, descricao);
            stmt.setString(5, decreto);
            stmt.setString(6, idLocalidade);
            stmt.setInt(7, id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0)
                System.out.println("Monumento atualizado com sucesso!");
            else
                System.out.println("Monumento não encontrado.");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar monumento: " + e.getMessage());
        }
    }

    // DELETE
    public void remover(int id) {
        String sql = "DELETE FROM monumento WHERE id_monumento = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0)
                System.out.println("Monumento removido com sucesso!");
            else
                System.out.println("Monumento não encontrado.");

        } catch (SQLException e) {
            System.out.println("Erro ao remover monumento: " + e.getMessage());
        }
    }
}
