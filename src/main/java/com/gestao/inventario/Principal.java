package com.gestao.inventario;

import java.math.BigDecimal;
import java.util.Scanner; // Novo import para ler o teclado
import com.gestao.inventario.modelo.Produto;
import com.gestao.inventario.servico.ArquivoService;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArquivoService arquivoService = new ArquivoService();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== GESTÃO DE ESTOQUE =====");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Listar Tudo");
            System.out.println("3 - Pesquisar por Nome");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    adicionarNovoProduto(teclado, arquivoService);
                    break;
                case 2:
                    listarProdutos(arquivoService);
                    break;
                case 3:
                    pesquisarProduto(teclado, arquivoService);
                    break;
                case 4:
                    removerProduto(teclado, arquivoService);
                    break;    
                case 0:
                	System.out.println("\nEncerrando o sistema...");
                    // Timer decrescente de 5 segundos
                    for (int i = 5; i > 0; i--) {
                        System.out.print(i + "... ");
                        try {
                            Thread.sleep(1000); // Pausa de 1 segundo
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    limparConsole();
                    System.out.println("Sistema encerrado com sucesso. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        teclado.close();
    }

    private static void adicionarNovoProduto(Scanner teclado, ArquivoService service) {
        int id = service.obterProximoId();
        System.out.println("\n--- Cadastrando Produto (ID: " + id + ") ---");
        
        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        BigDecimal preco = BigDecimal.ZERO;
        while (true) {
            try {
                System.out.print("Preço: ");
                preco = new BigDecimal(teclado.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Preço inválido! Use ponto para decimais.");
            }
        }

        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(teclado.nextLine());

        Produto p = new Produto(id, nome, preco, qtd);
        service.salvarProduto(p);
        System.out.println("Produto salvo com sucesso!");
    }

    private static void listarProdutos(ArquivoService service) {
        List<Produto> lista = service.lerProdutos();
        System.out.println("\n--- ESTOQUE ATUAL ---");
        if (lista.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            lista.forEach(p -> System.out.println(
                "ID: " + p.getId() + " | Nome: " + p.getNome() + 
                " | Preço: R$ " + p.getPreco() + " | Qtd: " + p.getQuantidade()
            ));
        }
    }
    private static void limparConsole() {
        try {
            // Comando específico para Linux (Ubuntu) e macOS
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // No Ubuntu, isso limpa a tela do terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar o console.");
        }
    }
    private static void pesquisarProduto(Scanner teclado, ArquivoService service) {
        System.out.print("\nDigite o nome (ou parte dele) para pesquisar: ");
        String termo = teclado.nextLine();
        
        List<Produto> resultados = service.buscarPorNome(termo);
        
        System.out.println("\n--- RESULTADOS DA PESQUISA ---");
        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o termo: " + termo);
        } else {
            resultados.forEach(p -> System.out.println(
                "ID: " + p.getId() + " | Nome: " + p.getNome() + " | Preço: R$ " + p.getPreco()
            ));
        }
    }
    private static void removerProduto(Scanner teclado, ArquivoService service) {
        System.out.print("\nDigite o ID do produto que deseja remover: ");
        try {
            int id = Integer.parseInt(teclado.nextLine());
            
            // Antes de remover, vamos listar para confirmar
            service.removerProdutoPorId(id);
            
            System.out.println("Processo de remoção concluído para o ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número de ID válido!");
        }
    }
}