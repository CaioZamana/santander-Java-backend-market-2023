# Santander Dev Week 2023

Java RESTful API criada para a Santander Dev Week.

## Principais Tecnologias
 - **Java 17**: Utilizaremos a versão LTS mais recente do Java para tirar vantagem das últimas inovações que essa linguagem robusta e amplamente utilizada oferece;
 - **Spring Boot 3**: Trabalharemos com a mais nova versão do Spring Boot, que maximiza a produtividade do desenvolvedor por meio de sua poderosa premissa de autoconfiguração;
 - **Spring Data JPA**: Exploraremos como essa ferramenta pode simplificar nossa camada de acesso aos dados, facilitando a integração com bancos de dados SQL;
 - **OpenAPI (Swagger)**: Vamos criar uma documentação de API eficaz e fácil de entender usando a OpenAPI (Swagger), perfeitamente alinhada com a alta produtividade que o Spring Boot oferece;
 - **Railway**: facilita o deploy e monitoramento de nossas soluções na nuvem, além de oferecer diversos bancos de dados como serviço e pipelines de CI/CD.


## Diagrama de Classes (Domínio da API)

```mermaid
classDiagram


  class Category {
    + id: Long
    + name: String
  }

  class Product {
    + id: Long
    + name: String
    + unit: String
    + price: Double
  }

  class CartItem {
    + id: Long
    + productId: Long
    + quantity: Int
    + salePrice: Double
  }

  class Checkout {
    + id: Long
    + total: Double
    + paymentMethod: PaymentMethod
  }

    class PaymentMethod {
    + id: Long
    + name: String
  }

  Category "1" --* "1..*" Product
  CartItem "1" --* "1..*" Product
  Checkout "1" --* "1..*" CartItem
  Checkout "1" --* "1" PaymentMethod


```

### Link documentação API em produção utilizando banco de dados relacional com PostgreSQL (Swagger)

A documentação da API do projeto de mercado é gerada utilizando o Swagger, uma ferramenta amplamente utilizada para a documentação e visualização de APIs RESTful.

https://santander-autoatendimento-compras.up.railway.app/swagger-ui/index.html

# Funcionalidades

**1. Gerenciamento de Categorias:**
- Criar uma nova categoria.
- Obter informações de uma categoria existente por ID.
- Atualizar informações de uma categoria existente.
- Excluir uma categoria.

**2. Gerenciamento de Produtos:**
- Criar um novo produto associado a uma categoria específica.
- Obter informações de um produto existente por ID.
- Obter informações de todos os produtos disponíveis.
- Atualizar informações de um produto existente.
- Excluir um produto.

**3. Gerenciamento do Carrinho de Compras:**
- Adicionar um novo item ao carrinho de compras.
- Remover um item do carrinho de compras por ID do produto.
- Obter informações de um item no carrinho de compras por ID do produto.
- Obter informações de todos os itens no carrinho de compras.

**4. Finalização da Compra:**
- Finalizar uma compra, calculando o total de vendas com base nos itens do carrinho de compras.
- Atualizar o método de pagamento de uma compra existente.

## Configurações do Banco de Dados

O projeto utiliza o banco de dados H2 para testes e desenvolvimento e PostgreSQL para produção. Configurações a partir dos arquivos application-dev.yml e da application-prd.yml.

## Tratamento de Erros e Exceções
A aplicação possui um mecanismo de tratamento de erros para fornecer respostas adequadas em caso de problemas. Os erros são tratados de forma global no GlobalExceptionHandler, e diferentes tipos de exceções são mapeados para respostas HTTP com códigos de status apropriados.

## Considerações Finais do projeto "santander-auto-atendimento-compras"
Este projeto tem o funcionamento de um auto atendimento, permitindo que os clientes façam suas compras de forma autônoma e eficiente. 
A estrutura do projeto segue as melhores práticas de desenvolvimento em Java com o uso do Spring Framework, proporcionando modularidade, reutilização de código e facilidade de manutenção.

Para quaisquer dúvidas, problemas ou sugestões, por favor, abra uma "Issue" no repositório do GitHub.

#
### Contato:
<a href="https://www.linkedin.com/in/caiozamana/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank">
<a href="https://api.whatsapp.com/send?phone=55048991477921" target="_blank"><img src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white">
<a href = "mailto:caiobzm@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>


<table>
  <tr>
    <td>
      <img width="80px" align="center" src="https://avatars.githubusercontent.com/caiobello"/>
    </td>
    <td align="left">
      <a href="https://github.com/caiobello">
        <span><b>Caio B. Zamana</b></span>
      </a>
      <br>
      <span>Desenvolvedor Full Stack</span>
    </td>
  </tr>
</table>



<!-- <img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=0487D9&height=120&section=footer"/> -->
