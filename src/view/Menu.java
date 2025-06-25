package view;

import controller.ProdutoDAO;
import controller.MaterialDAO;
import model.Produto;
import model.Material;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        MaterialDAO materialDAO = new MaterialDAO();
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Produtos");
            System.out.println("2. Materiais");
            System.out.println("3. Funcionários");
            System.out.println("4. Vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    MenuProdutos.exibir();
                    break;
                case 2:
                    MenuMateriais.exibir();
                    break;
                case 3:
                    MenuFuncionario.exibir(sc);
                    break;
                case 4:
                    MenuVenda.exibir();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

}