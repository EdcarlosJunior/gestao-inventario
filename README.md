# Sistema de Gestão de Inventário

Este é um protótipo funcional de um sistema de inventário desenvolvido em **Java**. O projeto foi concebido para demonstrar práticas de persistência de dados em arquivos planos (CSV), lógica de auto-incremento de identificadores e tratamento de exceções.

## 2. Funcionalidades
- **Auto-ID:** O sistema identifica o último ID registrado no arquivo CSV e gera automaticamente o próximo, garantindo a unicidade dos registros.
- **Persistência em CSV:** Os dados são salvos no arquivo `estoque.csv`, permitindo que as informações sejam mantidas mesmo após o fechamento do programa.
- **Validação de Entrada:** Implementação de blocos `try-catch` para garantir que o usuário insira dados numéricos válidos (Preço e Quantidade).
- **Listagem em Tempo Real:** Após cada inserção, o sistema lê o arquivo e exibe o estoque atualizado no console.
- **Menu Interativo: Sistema:** de navegação por opções com proteção contra entradas inválidas.
- **Busca Inteligente:** Filtro de produtos por nome (Case Insensitive).
- **Remover Produto:** Implementada remoção de produtos por ID.
- **Interface de Saída:** Timer decrescente e limpeza de console ao encerrar.

## 2. Tecnologias Utilizadas
- **Java JDK 17+**
- **Maven** (Gerenciamento de dependências)
- **Git & GitHub** (Controle de versão e integridade de código)

## 2. Estrutura de Arquivos
- `Principal.java`: Ponto de entrada do sistema e interface via console.
- `Produto.java`: Modelo de dados.
- `ArquivoService.java`: Camada de serviço responsável pela lógica de leitura e escrita.
- `estoque.csv`: Banco de dados em formato de texto.

## 2. Como Executar
1. Clone o repositório.
2. Certifique-se de ter o Maven instalado.
3. Execute a classe `Principal.java` através do seu IDE (Eclipse/VS Code).
