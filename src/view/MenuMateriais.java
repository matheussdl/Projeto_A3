package view;

import controller.MaterialDAO;
import model.Material;

import java.util.Scanner;

public class MenuMateriais {
    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n=== MENU MATERIAL ===");
            System.out.println("1. Cadastrar Material");
            System.out.println("2. Atualizar Material");
            System.out.println("3. Consultar Material");
            System.out.println("4. Listar Materiais");
            System.out.println("5. Excluir Material");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op){
                case 1:
                    Material m = new Material();
                    System.out.print("Nome do material: ");
                    m.setNome(sc.nextLine());
                    System.out.print("Tipo: ");
                    m.setTipo(sc.nextLine());
                    System.out.print("Preço: ");
                    m.setPreco(sc.nextDouble());
                    System.out.print("Quantidade: ");
                    m.setQuantidade(sc.nextInt());
                    sc.nextLine();
                    MaterialDAO.cadastrarMaterial(m);
                    break;
                case 2:
                    System.out.println("1. Atualizar material completo");
                    System.out.println("2. Atualizar apenas o estoque");
                    System.out.print("Escolha uma opção: ");
                    int opM = sc.nextInt();
                    sc.nextLine();

                    if (opM == 1) {
                        System.out.print("ID do material: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Novo preço: ");
                        double preco = sc.nextDouble();
                        System.out.print("Nova quantidade: ");
                        int qtd = sc.nextInt();

                        Material mAtulaiza = new Material(id, nome, tipo, qtd, preco);
                        MaterialDAO.atualizarMaterial(mAtulaiza);
                    } else if (opM == 2) {
                        System.out.print("ID do material: ");
                        int id = sc.nextInt();
                        System.out.print("Nova quantidade: ");
                        int qtd = sc.nextInt();

                        MaterialDAO.atualizarEstoque(id, qtd);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do material: ");
                    int idM = sc.nextInt();
                    Material material = MaterialDAO.consultarMaterial(idM);
                    if (material != null) {
                        System.out.println("=== Material Encontrado ===");
                        System.out.println("ID: " + material.getId());
                        System.out.println("Nome: " + material.getNome());
                        System.out.println("Tipo: " + material.getTipo());
                        System.out.println("Quantidade: " + material.getQuantidade());
                        System.out.println("Preço: R$ " + material.getPreco());
                    } else {
                        System.out.println("Material com ID " + idM + " não encontrado.");
                    }
                    break;
                case 4:
                    MaterialDAO.listarMateriais();
                    break;
                case 5:
                    System.out.print("ID do material a excluir: ");
                    int idExcluiM = sc.nextInt();
                    MaterialDAO.excluirMaterial(idExcluiM);
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
