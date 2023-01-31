Esse é um projeto de um Backend completo para uma loja fictícia

Foram utilizadas as seguintes tecnologias:
- Spring Boot.
- Spring Security.
- Spring Data JPA.

Também foi aplicado:
- Tratamento de exceções
- Utilização de DTO.
- O padrão de segurança JWT.
- Testes unitarios com Mockito.
- Utilização do docker para integrar o Spring boot com o banco de dados Mysql.

## O Projeto:

Aqui está uma imagem para ilustrar o banco de dados criado

![Diagrama sem nome drawio (2)](https://user-images.githubusercontent.com/68133954/215809175-afd5c05b-829e-4c52-a4eb-0ec53833cf92.png)

Observação:
- A coluna status criada na tabela Carrinho foi pensada com o intuito de quando o usuario tiver adicionado um produto ao carrinho o status por padrão será 0.
Caso ele conclua a venda o status será modificado para 1,seria uma solução para não ser necessário excluir esse dado.Com isso,teriamos um histórico de qual produto foi comprado.


