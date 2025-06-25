package view;

import controller.FuncionarioDAO;
import model.Funcionario;
import java.util.Scanner;

public class MenuFuncionario {
    public static void exibir(Scanner sc) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        int op;

        do {
            System.out.println("\n=== MENU FUNCIONÁRIOS ===");
            System.out.println("1. Cadastrar funcionário");
            System.out.println("2. Autenticar funcionário");
            System.out.println("3. Listar funcionários");
            System.out.println("4. Buscar funcionário por ID");
            System.out.println("5. Atualizar funcionário");
            System.out.println("6. Excluir funcionário");
            System.out.println("7. Alterar senha do funcionário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Login: ");
                    String login = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Funcionario f = new Funcionario();
                    f.setNome(nome);
                    f.setLogin(login);
                    f.setSenha(senha);

                    funcionarioDAO.cadastrarFuncionario(f);
                    break;

                case 2:
                    System.out.print("Login: ");
                    String loginA = sc.nextLine();
                    System.out.print("Senha: ");
                    String senhaA = sc.nextLine();

                    Funcionario autenticado = funcionarioDAO.autenticarFuncionario(loginA, senhaA);
                    if (autenticado != null) {
                        System.out.println("Autenticado com sucesso: " + autenticado.getNome());
                    } else {
                        System.out.println("Login ou senha incorretos.");
                    }
                    break;

                case 3:
                    funcionarioDAO.listarFuncionarios();
                    break;

                case 4:
                    System.out.print("ID do funcionário: ");
                    int idBusca = sc.nextInt();
                    sc.nextLine();
                    Funcionario encontrado = funcionarioDAO.buscarFuncionarioPorId(idBusca);
                    if (encontrado != null) {
                        System.out.println("ID: " + encontrado.getId());
                        System.out.println("Nome: " + encontrado.getNome());
                        System.out.println("Login: " + encontrado.getLogin());
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("ID do funcionário a atualizar: ");
                    int idAtualiza = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nomeNovo = sc.nextLine();
                    System.out.print("Novo login: ");
                    String loginNovo = sc.nextLine();
                    System.out.print("Nova senha: ");
                    String senhaNova = sc.nextLine();

                    Funcionario atualiza = new Funcionario();
                    atualiza.setId(idAtualiza);
                    atualiza.setNome(nomeNovo);
                    atualiza.setLogin(loginNovo);
                    atualiza.setSenha(senhaNova);

                    funcionarioDAO.atualizarFuncionario(atualiza);
                    break;

                case 6:
                    System.out.print("ID do funcionário a excluir: ");
                    int idExclui = sc.nextInt();
                    sc.nextLine();
                    funcionarioDAO.excluirFuncionario(idExclui);
                    break;

                case 7:
                    System.out.print("ID do funcionário: ");
                    int idSenha = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = sc.nextLine();
                    funcionarioDAO.alterarSenha(idSenha, novaSenha);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }
}
