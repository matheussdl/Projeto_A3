package controller;

import java.sql.*;
import connection.Conexao;
import model.Funcionario;

public class FuncionarioDAO {
    public void cadastrarFuncionario(Funcionario f) {
        String sql = "INSERT INTO Funcionario (nome, login, senha) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getLogin());
            stmt.setString(3, f.getSenha());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Funcionário cadastrado com sucesso.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public Funcionario autenticarFuncionario(String login, String senha) {
        String sql = "SELECT * FROM Funcionario WHERE login = ? AND senha = ?";
        Funcionario funcionario = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao autenticar funcionário: " + e.getMessage());
        }

        return funcionario;
    }

    public void listarFuncionarios(){
        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== Lista de Funcionários ===");
            while (rs.next()) {
                int id = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");
                String login = rs.getString("login");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Login: " + login);
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }
    public Funcionario buscarFuncionarioPorId(int id) {
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        Funcionario funcionario = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setLogin(rs.getString("login"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }

        return funcionario;
    }
    public void atualizarFuncionario(Funcionario f) {
        String sql = "UPDATE Funcionario SET nome = ?, login = ?, senha = ? WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getLogin());
            stmt.setString(3, f.getSenha());
            stmt.setInt(4, f.getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Funcionário atualizado com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }
    public void excluirFuncionario(int id) {
        String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Funcionário excluído com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
    public void alterarSenha(int id, String novaSenha) {
        String sql = "UPDATE Funcionario SET senha = ? WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setInt(2, id);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Senha alterada com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
        }
    }
}
