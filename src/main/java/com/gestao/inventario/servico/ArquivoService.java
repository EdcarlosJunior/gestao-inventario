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
}