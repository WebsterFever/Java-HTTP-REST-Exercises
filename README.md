# Java HTTP REST Exercises

Exercícios práticos de requisições HTTP e APIs REST utilizando Java e `HttpURLConnection`.

## Exercícios

| Exercício | Objetivo | Método | Endpoint |
| --- | --- | --- | --- |
| [01](exercicio-01/Exercicio1.java) | Listar todas as entidades | GET | `https://apichallenges.eviltester.com/sim/entities` |\n| [02](exercicio-02/Exercicio2.java) | Consultar entidades de ID 1 a 8 | GET | `https://apichallenges.eviltester.com/sim/entities/{id}` |\n| [03](exercicio-03/Exercicio3.java) | Consultar entidade inexistente e tratar 404 | GET | `https://apichallenges.eviltester.com/sim/entities/13` |\n| [04](exercicio-04/Exercicio4.java) | GET com parâmetros fictícios na URL | GET | `https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5` |

Cada exercício fica em sua própria pasta. Há apenas este README na raiz.

## Executar o exercício 1

Com o JDK instalado, execute na raiz do repositório:

```bash
javac exercicio-01/Exercicio1.java
java -cp exercicio-01 Exercicio1
```

O programa imprime o código de status HTTP e a resposta do servidor. Em caso de sucesso, espera-se status 200 e um JSON com as entidades.

## Executar o exercício 2\n\n```bash\njavac exercicio-02/Exercicio2.java\njava -cp exercicio-02 Exercicio2\n```\n\nO programa consulta cada ID de 1 a 8, imprime o status HTTP e a resposta JSON, permitindo comparar os resultados.\n\n## Executar o exercício 3\n\n```bash\njavac exercicio-03/Exercicio3.java\njava -cp exercicio-03 Exercicio3\n```\n\nO programa exibe o status HTTP, informa quando a entidade não existe (404) e imprime a resposta do servidor.\n\n## Executar o exercício 4\n\n```bash\njavac exercicio-04/Exercicio4.java\njava -cp exercicio-04 Exercicio4\n```\n\nO programa monta a URL com parâmetros, imprime a URL final, o status HTTP e a resposta do servidor. Os parâmetros são fictícios e podem ser ignorados pela API.\n\n## APIs utilizadas

- https://apichallenges.eviltester.com/sim/entities
- https://apichallenges.eviltester.com/simpleapi/items
