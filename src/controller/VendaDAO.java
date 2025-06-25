package controller;

import connection.Conexao;
import model.Funcionario;
import model.Produto;
import model.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDAO {

        public void registrarVenda(Venda v) {
                String sql = "INSERT INTO Venda (idFuncionario, idProduto, quantidade, data, precoUnitario, descricao) VALUES (?, ?, ?, ?, ?, ?)";
                String sqlEstoque = "UPDATE Produto SET quantidade = quantidade - ? WHERE idProduto = ? AND quantidade >= ?";

                try (Connection conn = Conexao.conectar()) {
                        conn.setAutoCommit(false);

                        // Atualizar estoque
                        try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque)) {
                                stmtEstoque.setInt(1, v.getQuantidade());
                                stmtEstoque.setInt(2, v.getProduto().getId());
                                stmtEstoque.setInt(3, v.getQuantidade());

                                int atualizou = stmtEstoque.executeUpdate();
                                if (atualizou == 0) {
                                        System.out.println("Venda não realizada: estoque insuficiente.");
                                        conn.rollback();
                                        return;
                                }
                        }

                        // Registrar venda
                        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                                stmt.setInt(1, v.getFuncionario().getId());
                                stmt.setInt(2, v.getProduto().getId());
                                stmt.setInt(3, v.getQuantidade());
                                stmt.setString(4, v.getData());
                                stmt.setDouble(5, v.getPrecoUnitario());
                                stmt.setString(6, v.getDescricao());

                                stmt.executeUpdate();
                                conn.commit();
                                System.out.println("Venda registrada com sucesso.");
                        }

                } catch (SQLException e) {
                        System.out.println("Erro ao registrar venda: " + e.getMessage());
                }
        }

        public void listarVendas() {
                String sql = "SELECT v.idVenda, f.nome AS funcionario, p.nome AS produto, v.quantidade, v.data, v.precoUnitario, v.descricao " +
                        "FROM Venda v " +
                        "JOIN Funcionario f ON v.idFuncionario = f.idFuncionario " +
                        "JOIN Produto p ON v.idProduto = p.idProduto";

                try (Connection conn = Conexao.conectar();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                        System.out.println("=== Lista de Vendas ===");
                        while (rs.next()) {
                                int qtd = rs.getInt("quantidade");
                                double precoUnitario = rs.getDouble("precoUnitario");
                                double total = qtd * precoUnitario;

                                System.out.println("ID da Venda: " + rs.getInt("idVenda"));
                                System.out.println("Funcionário: " + rs.getString("funcionario"));
                                System.out.println("Produto: " + rs.getString("produto"));
                                System.out.println("Quantidade: " + qtd);
                                System.out.println("Data: " + rs.getString("data"));
                                System.out.println("Preço Unitário: R$" + precoUnitario);
                                System.out.printf("Valor Total: R$ %.2f\n", total);
                                System.out.println("Descrição: " + rs.getString("descricao"));
                                System.out.println("--------------------------");
                        }

                } catch (SQLException e) {
                        System.out.println("Erro ao listar vendas: " + e.getMessage());
                }
        }

        public void listarVendasPorFuncionario(int idFuncionario) {
                String sql = "SELECT v.idVenda, p.nome AS produto, v.quantidade, v.data, v.precoUnitario, v.descricao " +
                        "FROM Venda v " +
                        "JOIN Produto p ON v.idProduto = p.idProduto " +
                        "WHERE v.idFuncionario = ?";

                try (Connection conn = Conexao.conectar();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                        stmt.setInt(1, idFuncionario);
                        ResultSet rs = stmt.executeQuery();

                        System.out.println("=== Vendas do Funcionário ===");
                        while (rs.next()) {
                                int qtd = rs.getInt("quantidade");
                                double preco = rs.getDouble("precoUnitario");
                                double total = qtd * preco;

                                System.out.println("ID da Venda: " + rs.getInt("idVenda"));
                                System.out.println("Produto: " + rs.getString("produto"));
                                System.out.println("Quantidade: " + qtd);
                                System.out.println("Data: " + rs.getString("data"));
                                System.out.println("Preço Unitário: R$" + preco);
                                System.out.printf("Valor Total: R$ %.2f\n", total);
                                System.out.println("Descrição: " + rs.getString("descricao"));
                                System.out.println("--------------------------");
                        }

                } catch (SQLException e) {
                        System.out.println("Erro ao listar vendas do funcionário: " + e.getMessage());
                }
        }

        public void buscarVendaPorId(int idVenda) {
                String sql = "SELECT v.idVenda, f.nome AS funcionario, p.nome AS produto, v.quantidade, v.data, v.precoUnitario, v.descricao " +
                        "FROM Venda v " +
                        "JOIN Funcionario f ON v.idFuncionario = f.idFuncionario " +
                        "JOIN Produto p ON v.idProduto = p.idProduto " +
                        "WHERE v.idVenda = ?";

                try (Connection conn = Conexao.conectar();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                        stmt.setInt(1, idVenda);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                                int qtd = rs.getInt("quantidade");
                                double preco = rs.getDouble("precoUnitario");
                                double total = qtd * preco;

                                System.out.println("ID da Venda: " + rs.getInt("idVenda"));
                                System.out.println("Funcionário: " + rs.getString("funcionario"));
                                System.out.println("Produto: " + rs.getString("produto"));
                                System.out.println("Quantidade: " + qtd);
                                System.out.println("Data: " + rs.getString("data"));
                                System.out.println("Preço Unitário: R$" + preco);
                                System.out.printf("Valor Total: R$ %.2f\n", total);
                                System.out.println("Descrição: " + rs.getString("descricao"));
                        } else {
                                System.out.println("Venda não encontrada.");
                        }

                } catch (SQLException e) {
                        System.out.println("Erro ao buscar venda: " + e.getMessage());
                }
        }


}
