package controller;

import java.sql.*;
import connection.Conexao;
import model.Material;

public class MaterialDAO {
    public static void cadastrarMaterial(Material material) {
        String sql = "INSERT INTO Material (nome, tipo, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getTipo());
            stmt.setInt(3, material.getQuantidade());
            stmt.setDouble(4, material.getPreco());

            stmt.executeUpdate();
            System.out.println("Material cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar material: " + e.getMessage());
        }
    }
    public static void atualizarEstoque(int id, int novaQuantidade) {
        String sql = "UPDATE Material SET quantidade = ? WHERE idMaterial = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Estoque atualizado com sucesso.");
            } else {
                System.out.println("Material não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
        }
    }
    public static void atualizarMaterial(Material m) {
        String sql = "UPDATE Material SET nome = ?, tipo = ?, preco = ?, quantidade = ? WHERE idMaterial = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getTipo());
            stmt.setDouble(3, m.getPreco());
            stmt.setInt(4, m.getQuantidade());
            stmt.setInt(5, m.getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Material atualizado com sucesso.");
            } else {
                System.out.println("Nenhum material foi encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar material: " + e.getMessage());
        }
    }

    public static Material consultarMaterial(int id) {
        String sql = "SELECT * FROM Material WHERE idMaterial = ?";
        Material material = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                material = new Material();
                material.setId(rs.getInt("idMaterial"));
                material.setNome(rs.getString("nome"));
                material.setTipo(rs.getString("tipo"));
                material.setQuantidade(rs.getInt("quantidade"));
                material.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar material: " + e.getMessage());
        }
        return material;
    }
    public static void excluirMaterial(int id) {
        String sql = "DELETE FROM Material WHERE idMaterial = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Material excluído com sucesso!");
            } else {
                System.out.println("Material não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir material: " + e.getMessage());
        }
    }
    public static void listarMateriais(){
        String sql = "SELECT * FROM Material";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== Lista de Materiais ===");
            while (rs.next()) {
                int id = rs.getInt("idMaterial");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");


                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Descrição: " + tipo);
                System.out.println("Quantidade em estoque: " + quantidade);
                System.out.println("Preço: R$ " + preco);
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao Listar Materiais: " + e.getMessage());
        }
    }
}
