package view;
import model.Produto;
import controller.ProdutoDAO;

import java.util.Scanner;
public class MenuProdutos {
    public static void exibir(){
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n=== MENU PRODUTOS ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Atualizar produto");
            System.out.println("3. Consultar produto");
            System.out.println("4. Listar produtos");
            System.out.println("5. Excluir produto");
            System.out.println("6. Consultar produto por ID");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    Produto p = new Produto();
                    System.out.print("Nome do produto: ");
                    p.setNome(sc.nextLine());
                    System.out.print("Preço: ");
                    p.setPreco(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Descrição: ");
                    p.setDescricao(sc.nextLine());
                    System.out.print("Quantidade: ");
                    p.setQuantidade(sc.nextInt());
                    sc.nextLine();
                    produtoDAO.cadastrarProduto(p);
                    break;
                case 2:
                    System.out.println("1. Atualizar produto completo");
                    System.out.println("2. Atualizar apenas o estoque");
                    System.out.print("Escolha uma opção: ");
                    int escolha = sc.nextInt();
                    sc.nextLine();

                    if (escolha == 1) {
                        System.out.print("ID do produto: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo preço: ");
                        double preco = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Nova Descrição: ");
                        String descricao = sc.nextLine();
                        System.out.print("Nova quantidade: ");
                        int qtd = sc.nextInt();

                        Produto pAtualiza = new Produto();
                        pAtualiza.setId(id);
                        pAtualiza.setNome(nome);
                        pAtualiza.setPreco(preco);
                        pAtualiza.setDescricao(descricao);
                        pAtualiza.setQuantidade(qtd);
                        ProdutoDAO.atualizarProduto(pAtualiza);
                    }else if (escolha == 2) {
                        System.out.print("ID do produto: ");
                        int id = sc.nextInt();
                        System.out.print("Nova quantidade: ");
                        int qtd = sc.nextInt();

                        ProdutoDAO.atualizarEstoque(id, qtd);

                    } else {
                        System.out.println("Opção inválida.");
                    }
                        break;
                case 3:
                    System.out.print("Digite o ID do produto: ");
                    int idP = sc.nextInt();
                    ProdutoDAO.consultarProduto(idP);
                    break;
                case 4:
                    ProdutoDAO.listarProdutos();
                    break;
                case 5:
                    System.out.print("ID do produto a excluir: ");
                    int idExcluiP = sc.nextInt();
                    ProdutoDAO.excluirProduto(idExcluiP);
                    break;
                case 6:
                    System.out.print("Digite o ID do produto: ");
                    int id = sc.nextInt();
                    Produto produto = ProdutoDAO.buscarProdutoPorId(id);
                    if (produto != null) {
                        System.out.println("ID: " + produto.getId());
                        System.out.println("Nome: " + produto.getNome());
                        System.out.println("Descrição: " + produto.getDescricao());
                        System.out.println("Preço: R$" + produto.getPreco());
                        System.out.println("Quantidade: " + produto.getQuantidade());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        while (op != 0);
    }
}

