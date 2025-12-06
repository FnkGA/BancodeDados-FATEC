package br.edu.fatecfranca;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        MonumentoDAO dao = new MonumentoDAO();
        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU MONUMENTO =====");
            System.out.println("1 - Cadastrar Monumento");
            System.out.println("2 - Atualizar Monumento");
            System.out.println("3 - Excluir Monumento");
            System.out.println("4 - Listar Monumentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); 

            switch(opcao) {
                case 1:
                    System.out.println("Informe o nome do monumento:");
                    String nome = entrada.nextLine();

                    System.out.println("Informe o endereço:");
                    String endereco = entrada.nextLine();

                    System.out.println("Informe o artista:");
                    String artista = entrada.nextLine();

                    System.out.println("Informe a descrição:");
                    String descricao = entrada.nextLine();

                    System.out.println("Informe o decreto:");
                    String decreto = entrada.nextLine();

                    System.out.println("Informe o ID da localidade:");
                    String idLocalidade = entrada.nextLine();

                    dao.inserir(nome, endereco, artista, descricao, decreto, idLocalidade);
                    break;

                case 2:
                    System.out.println("Informe o ID do monumento:");
                    int id = entrada.nextInt();
                    entrada.nextLine();

                    System.out.println("Informe o novo nome:");
                    nome = entrada.nextLine();

                    System.out.println("Informe o novo endereço:");
                    endereco = entrada.nextLine();

                    System.out.println("Informe o novo artista:");
                    artista = entrada.nextLine();

                    System.out.println("Informe a nova descrição:");
                    descricao = entrada.nextLine();

                    System.out.println("Informe o novo decreto:");
                    decreto = entrada.nextLine();

                    System.out.println("Informe o novo ID da localidade:");
                    idLocalidade = entrada.nextLine();

                    dao.atualizar(id, nome, endereco, artista, descricao, decreto, idLocalidade);
                    break;

                case 3:
                    System.out.println("Informe o ID do monumento a excluir:");
                    id = entrada.nextInt();
                    dao.remover(id);
                    break;

                case 4:
                    System.out.println("===== LISTA DE MONUMENTOS =====");
                    List<String> lista = dao.listar();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum monumento cadastrado.");
                    } else {
                        for (String m : lista) {
                            System.out.println(m);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
