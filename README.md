# Java HTTP REST Exercises

Exercícios práticos de requisições HTTP e APIs REST utilizando Java e `HttpURLConnection`.

## Exercícios

| Exercício | Objetivo | Método | Endpoint |
| --- | --- | --- | --- |
| [01](exercicio-01/Exercicio1.java) | Listar todas as entidades | GET | `https://apichallenges.eviltester.com/sim/entities` |\n| [02](exercicio-02/Exercicio2.java) | Consultar entidades de ID 1 a 8 | GET | `https://apichallenges.eviltester.com/sim/entities/{id}` |

Cada exercício fica em sua própria pasta. Há apenas este README na raiz.

## Executar o exercício 1

Com o JDK instalado, execute na raiz do repositório:

```bash
javac exercicio-01/Exercicio1.java
java -cp exercicio-01 Exercicio1
```

O programa imprime o código de status HTTP e a resposta do servidor. Em caso de sucesso, espera-se status 200 e um JSON com as entidades.

## Executar o exercício 2\n\n```bash\njavac exercicio-02/Exercicio2.java\njava -cp exercicio-02 Exercicio2\n```\n\nO programa consulta cada ID de 1 a 8, imprime o status HTTP e a resposta JSON, permitindo comparar os resultados.\n\n## APIs utilizadas

- https://apichallenges.eviltester.com/sim/entities
- https://apichallenges.eviltester.com/simpleapi/items
