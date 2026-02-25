package com.gestao.inventario.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Produto {
    private Integer id;
    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private LocalDateTime dataRegistro;

    // Construtor padrão
    public Produto() {
        this.dataRegistro = LocalDateTime.now();
    }

    // Construtor completo
    public Produto(Integer id, String nome, BigDecimal preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataRegistro = LocalDateTime.now();
    }

    // Método para transformar em linha de CSV (útil para salvar no disco)
    public String toCsvRow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%d;%s;%s;%d;%s", 
            id, nome, preco.toString(), quantidade, dataRegistro.format(formatter));
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataRegistro() { return dataRegistro; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %-15s | Preço: R$ %8.2f | Qtd: %d", 
                id, nome, preco, quantidade);
    }
}