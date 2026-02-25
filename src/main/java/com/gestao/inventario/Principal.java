package com.gestao.inventario;

import java.math.BigDecimal;
import java.util.Scanner; // Novo import para ler o teclado
import com.gestao.inventario.modelo.Produto;
import com.gestao.inventario.servico.ArquivoService;

public class Principal {

	public static void main(String[] args) {
	    Scanner teclado = new Scanner(System.in);
	    ArquivoService arquivoService = new ArquivoService();
	    
	    System.out.println("=== GESTÃO DE INVENTÁRIO (MODO PROTEGIDO) ===");
	    
	    // O ID continua automático e seguro
	    int idGerado = arquivoService.obterProximoId();
	    System.out.println("Novo Registo - ID: " + idGerado);

	    // Variáveis para armazenar os dados validados
	    String nome = "";
	    BigDecimal preco = BigDecimal.ZERO;
	    int qtd = 0;
	    boolean entradaValida = false;

	    // Validação do Nome
	    System.out.print("Nome do produto: ");
	    nome = teclado.nextLine();

	    // Ciclo de validação do PREÇO
	    while (!entradaValida) {
	        try {
	            System.out.print("Preço (use ponto para decimais, ex: 10.50): ");
	            String input = teclado.next();
	            preco = new BigDecimal(input);
	            entradaValida = true; // Se chegar aqui sem erro, a entrada é válida
	        } catch (Exception e) {
	            System.out.println("Erro: Digite um valor numérico válido para o preço!");
	            teclado.nextLine(); // Limpa o erro do teclado
	        }
	    }

	    entradaValida = false; // Reinicia para a próxima validação

	    // Ciclo de validação da QUANTIDADE
	    while (!entradaValida) {
	        try {
	            System.out.print("Quantidade em stock (número inteiro): ");
	            qtd = teclado.nextInt();
	            entradaValida = true;
	        } catch (Exception e) {
	            System.out.println("Erro: Digite apenas números inteiros para a quantidade!");
	            teclado.next(); // Limpa o erro do teclado
	        }
	    }

	    // Gravação Final
	    Produto p = new Produto(idGerado, nome, preco, qtd);
	    arquivoService.salvarProduto(p);

	    System.out.println("\n--- LISTAGEM ATUALIZADA ---");
	    arquivoService.lerProdutos().forEach(prod -> 
	        System.out.println("ID: " + prod.getId() + " | Nome: " + prod.getNome() + " | Preço: " + prod.getPreco()));
	    
	    teclado.close();
	}
}