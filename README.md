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
classDef PaymentMethod fill:#6FB1FC,stroke:#333,stroke-width:2px;
classDef Category fill:#FC9E6F,stroke:#333,stroke-width:2px;
classDef Product fill:#9DFC6F,stroke:#333,stroke-width:2px;
classDef CartItem fill:#FC6F6F,stroke:#333,stroke-width:2px;
classDef Checkout fill:#F1C40F,stroke:#333,stroke-width:2px;

PaymentMethod --|> Enum: CARTAO_CREDITO\nCARTAO_DEBITO\nDINHEIRO\nPIX

Category: <<Class>>\nid: Long\nname: String
Product: <<Class>>\nid: Long\nname: String\nunit: String\nprice: Double
CartItem: <<Class>>\nid: Long\nproductId: Long\nquantity: Int\nsalePrice: Double
Checkout: <<Class>>\nid: Long\ntotal: Double\npaymentMethod: PaymentMethod

Category --* Product: Contains
Product --|> Category: Belongs to
Product --* CartItem: Contains
CartItem --* Product: Contains
CartItem --> Checkout: Part of
Checkout --> PaymentMethod: Payment Method

```

