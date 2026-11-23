# Java HTTP REST Exercises

Exercícios práticos de requisições HTTP e APIs REST utilizando Java e `HttpURLConnection`.

## Exercícios

| Exercício | Objetivo | Método | Endpoint |
| --- | --- | --- | --- |
| [01](exercicio-01/Exercicio1.java) | Listar todas as entidades | GET | `https://apichallenges.eviltester.com/sim/entities` |\n| [02](exercicio-02/Exercicio2.java) | Consultar entidades de ID 1 a 8 | GET | `https://apichallenges.eviltester.com/sim/entities/{id}` |\n| [03](exercicio-03/Exercicio3.java) | Consultar entidade inexistente e tratar 404 | GET | `https://apichallenges.eviltester.com/sim/entities/13` |\n| [04](exercicio-04/Exercicio4.java) | GET com parâmetros fictícios na URL | GET | `https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5` |

| [05](exercicio-05/Exercicio5.java) | Criar entidade com JSON e identificar ID retornado | POST | `https://apichallenges.eviltester.com/sim/entities` |\n| [06](exercicio-06/Exercicio6.java) | Consultar entidade 11 e verificar resposta | GET | `https://apichallenges.eviltester.com/sim/entities/11` |\n\n| [07](exercicio-07/Exercicio7.java) | POST para atualizar entidade 10 e GET de verificação | POST / GET | `/sim/entities/10` |
| [08](exercicio-08/Exercicio8.java) | PUT para atualizar entidade 10 e GET de verificação | PUT / GET | `/sim/entities/10` |

| [09](exercicio-09/Exercicio9.java) | DELETE da entidade 9 e GET para verificar 404 | DELETE / GET | `/sim/entities/9` |
| [10](exercicio-10/Exercicio10.java) | DELETE inválido da entidade 2 | DELETE | `/sim/entities/2` |

Cada exercício fica em sua própria pasta. Há apenas este README na raiz.

## Executar o exercício 1

Com o JDK instalado, execute na raiz do repositório:

```bash
javac exercicio-01/Exercicio1.java
java -cp exercicio-01 Exercicio1
```

O programa imprime o código de status HTTP e a resposta do servidor. Em caso de sucesso, espera-se status 200 e um JSON com as entidades.

## Executar o exercício 2\n\n```bash\njavac exercicio-02/Exercicio2.java\njava -cp exercicio-02 Exercicio2\n```\n\nO programa consulta cada ID de 1 a 8, imprime o status HTTP e a resposta JSON, permitindo comparar os resultados.\n\n## Executar o exercício 3\n\n```bash\njavac exercicio-03/Exercicio3.java\njava -cp exercicio-03 Exercicio3\n```\n\nO programa exibe o status HTTP, informa quando a entidade não existe (404) e imprime a resposta do servidor.\n\n## Executar o exercício 4\n\n```bash\njavac exercicio-04/Exercicio4.java\njava -cp exercicio-04 Exercicio4\n```\n\nO programa monta a URL com parâmetros, imprime a URL final, o status HTTP e a resposta do servidor. Os parâmetros são fictícios e podem ser ignorados pela API.\n\n## Executar o exercício 5\n\n```bash\njavac exercicio-05/Exercicio5.java\njava -cp exercicio-05 Exercicio5\n```\n\n## Executar o exercício 6\n\n```bash\njavac exercicio-06/Exercicio6.java\njava -cp exercicio-06 Exercicio6\n```\n\n**Observação:** a API de entidades é stateless; a criação via POST não garante que o GET seguinte encontre o mesmo registro.\n\n## Executar os exercícios 7 e 8

```bash
javac exercicio-07/Exercicio7.java
java -cp exercicio-07 Exercicio7
javac exercicio-08/Exercicio8.java
java -cp exercicio-08 Exercicio8
```

Ambos enviam o JSON `{"name":"atualizado"}` e fazem GET depois para comparar as respostas. A API de simulação pode não preservar alterações.

## Executar os exercícios 9 e 10

```bash
javac exercicio-09/Exercicio9.java
java -cp exercicio-09 Exercicio9
javac exercicio-10/Exercicio10.java
java -cp exercicio-10 Exercicio10
```

O exercício 9 executa DELETE seguido de GET e mostra o status real de ambas as respostas. Como a API é stateless, a exclusão pode não persistir e o GET pode não retornar 404. O exercício 10 verifica o status real do DELETE (esperado 403 ou 405).

## APIs utilizadas

- https://apichallenges.eviltester.com/sim/entities
- https://apichallenges.eviltester.com/simpleapi/items
