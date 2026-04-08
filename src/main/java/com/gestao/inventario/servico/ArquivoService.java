package com.gestao.inventario.servico;

import java.io.BufferedWriter;
import java.io.BufferedReader; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.gestao.inventario.modelo.Produto;

public class ArquivoService {

    private static final String NOME_ARQUIVO = "estoque.csv";

    public void salvarProduto(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            String linha = produto.getId() + ";" + 
                           produto.getNome() + ";" + 
                           produto.getPreco() + ";" + 
                           produto.getQuantidade();
            writer.write(linha);
            writer.newLine();
            System.out.println("LOG: Produto [" + produto.getNome() + "] gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    } // FECHAMENTO CORRETO DO MÉTODO SALVAR

    public List<Produto> lerProdutos() {
        List<Produto> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                BigDecimal preco = new BigDecimal(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);

                Produto p = new Produto(id, nome, preco, quantidade);
                lista.add(p);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lista;
    }
    public int obterProximoId() {
        List<Produto> produtos = lerProdutos();
        int maiorId = 0;
        for (Produto p : produtos) {
            if (p.getId() > maiorId) {
                maiorId = p.getId();
            }
        }
        return maiorId + 1; // O próximo será o maior encontrado + 1
    }
    public List<Produto> buscarPorNome(String termoBusca) {
        List<Produto> todos = lerProdutos();
        // Filtra a lista: converte tudo para minúsculas para a busca não diferenciar "A" de "a"
        return todos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(termoBusca.toLowerCase()))
                .toList();
    }
    public void removerProdutoPorId(int id) {
        List<Produto> listaOriginal = lerProdutos();
        
        // Filtra a lista mantendo apenas quem NÃO tem o ID que queremos apagar
        List<Produto> listaAtualizada = listaOriginal.stream()
                .filter(p -> p.getId() != id)
                .toList();

        // Sobrescreve o arquivo com a nova lista
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(NOME_ARQUIVO))) {
            for (Produto p : listaAtualizada) {
                writer.println(p.getId() + ";" + p.getNome() + ";" + p.getPreco() + ";" + p.getQuantidade());
            }
        } catch (java.io.IOException e) {
            System.out.println("Erro ao atualizar arquivo após remoção: " + e.getMessage());
        }
    }
}