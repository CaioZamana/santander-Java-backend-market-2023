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

