## Versões de Tecnologia ##
Spring Boot: 3.3.3<br>
Java 21<br>

# Aplicação de Gerenciamento de Compras
Esta é uma aplicação para gerenciamento de compras que expõe alguns endpoints para consultar dados sobre compras, clientes e recomendações. Todos os endpoints disponíveis são do tipo GET.

## Endpoints
### 1. Listar Todas as Compras ###
URL: http://localhost:8080/compras

**Método:  GET**

Descrição: Retorna uma lista das compras ordenadas de forma
crescente por valor, deve conter o nome dos clientes, cpf dos clientes,
dado dos produtos, quantidade das compras e valores totais de cada
compra.

Resposta: Um array de objetos representando as compras.

### 2. Obter Maior Compra de um Ano ###
URL: http://localhost:8080/maior-compra/{ano}

**Método: GET**

Parâmetros de URL:

ano (obrigatório): O ano para o qual se deseja obter a maior compra.
Descrição: Retorna a maior compra registrada no ano especificado.

Resposta: Um objeto representando a maior compra do ano.

### 3. Listar Clientes Fiéis ###
URL: http://localhost:8080/clientes-fieis

**Método: GET**

Descrição: - Retorna o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores..

Resposta: Um array de objetos representando os clientes fiéis.

### 4. Obter Recomendação para um Cliente ### 
URL: http://localhost:8080/recomendacao/cliente/tipo/{cpf}

**Método: GET**

Parâmetros de URL:

cpf (obrigatório): O CPF do cliente para o qual se deseja obter a recomendação.
Descrição: Retorna uma recomendação de compra com base no perfil do cliente identificado pelo CPF.

Resposta: Um objeto representando a recomendação de compra.
