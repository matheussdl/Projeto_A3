package view;

import controller.VendaDAO;
import controller.ProdutoDAO;
import controller.FuncionarioDAO;
import model.Funcionario;
import model.Produto;
import model.Venda;

import java.util.Scanner;

public class MenuVenda {

    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        VendaDAO vendaDAO = new VendaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        int opcao;

        do {
            System.out.println("\n=== MENU VENDA ===");
            System.out.println("1. Registrar venda");
            System.out.println("2. Listar todas as vendas");
            System.out.println("3. Listar vendas por funcionário");
            System.out.println("4. Buscar venda por ID");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("--- Registro de Venda ---");

                    System.out.print("Login do funcionário: ");
                    String login = sc.nextLine();
                    System.out.print("Senha do funcionário: ");
                    String senha = sc.nextLine();
                    Funcionario funcionario = funcionarioDAO.autenticarFuncionario(login, senha);

                    if (funcionario == null) {
                        System.out.println("Funcionário não autenticado.");
                        break;
                    }

                    System.out.print("ID do produto vendido: ");
                    int idProduto = sc.nextInt();
                    Produto produto = ProdutoDAO.buscarProdutoPorId(idProduto);

                    if (produto == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Quantidade vendida: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data (YYYY-MM-DD): ");
                    String data = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();

                    double precoUnitario = produto.getPreco();

                    Venda venda = new Venda();
                    venda.setFuncionario(funcionario);
                    venda.setProduto(produto);
                    venda.setQuantidade(quantidade);
                    venda.setData(data);
                    venda.setPrecoUnitario(precoUnitario);
                    venda.setDescricao(descricao);

                    vendaDAO.registrarVenda(venda);
                    break;

                case 2:
                    vendaDAO.listarVendas();
                    break;

                case 3:
                    System.out.print("ID do funcionário: ");
                    int idFuncionario = sc.nextInt();
                    vendaDAO.listarVendasPorFuncionario(idFuncionario);
                    break;

                case 4:
                    System.out.print("ID da venda: ");
                    int idVenda = sc.nextInt();
                    vendaDAO.buscarVendaPorId(idVenda);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
