# Java HTTP REST Exercises

Exercícios práticos de requisições HTTP e APIs REST utilizando Java e `HttpURLConnection`.

## Exercícios

| Exercício | Objetivo | Método | Endpoint |
| --- | --- | --- | --- |
| [01](exercicio-01/Exercicio1.java) | Listar todas as entidades | GET | `https://apichallenges.eviltester.com/sim/entities` |

Cada exercício fica em sua própria pasta. Há apenas este README na raiz.

## Executar o exercício 1

Com o JDK instalado, execute na raiz do repositório:

```bash
javac exercicio-01/Exercicio1.java
java -cp exercicio-01 Exercicio1
```

O programa imprime o código de status HTTP e a resposta do servidor. Em caso de sucesso, espera-se status 200 e um JSON com as entidades.

## APIs utilizadas

- https://apichallenges.eviltester.com/sim/entities
- https://apichallenges.eviltester.com/simpleapi/items
