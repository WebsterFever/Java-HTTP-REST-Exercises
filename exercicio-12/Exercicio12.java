import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercicio12 {
    private static final String ITEMS = "https://apichallenges.eviltester.com/simpleapi/items";
    private static final String RANDOM_ISBN = "https://apichallenges.eviltester.com/simpleapi/randomisbn";

    public static void main(String[] args) {
        System.out.println("a) GET - Todos os itens");
        requisicao("GET", ITEMS, null);

        System.out.println("\nb) GET - Gerar ISBN aleatório");
        Resultado isbnResposta = requisicao("GET", RANDOM_ISBN, null);
        if (isbnResposta == null || isbnResposta.status < 200 || isbnResposta.status >= 300) {
            System.out.println("Não foi possível obter um ISBN. Operações seguintes canceladas.");
            return;
        }

        String isbn = extrairIsbn(isbnResposta.corpo);
        if (isbn == null) {
            System.out.println("ISBN não identificado na resposta. Operações seguintes canceladas.");
            return;
        }
        System.out.println("ISBN gerado: " + isbn);

        String jsonCriacao = "{\"type\":\"book\",\"isbn13\":\"" + isbn
                + "\",\"price\":5.99,\"numberinstock\":5}";

        System.out.println("\nc) POST - Criar item");
        Resultado criacao = requisicao("POST", ITEMS, jsonCriacao);
        if (criacao == null || criacao.status < 200 || criacao.status >= 300) {
            System.out.println("Criação não confirmada. PUT e DELETE cancelados.");
            return;
        }

        System.out.println("\nd) PUT - Atualizar item");
        String jsonAtualizacao = "{\"type\":\"book\",\"isbn13\":\"" + isbn
                + "\",\"price\":7.99,\"numberinstock\":10}";
        requisicao("PUT", ITEMS, jsonAtualizacao);

        System.out.println("\ne) DELETE - Remover item");
        requisicao("DELETE", ITEMS + "/" + isbn, null);
    }

    private static String extrairIsbn(String corpo) {
        // A API pode retornar o ISBN como texto puro ou dentro de um objeto JSON.
        Matcher campo = Pattern.compile("\"isbn13\"\\s*:\\s*\"?([0-9]{13})\"?")
                .matcher(corpo);
        if (campo.find()) {
            return campo.group(1);
        }
        Matcher numero = Pattern.compile("\\b[0-9]{13}\\b").matcher(corpo);
        return numero.find() ? numero.group() : null;
    }

    private static Resultado requisicao(String metodo, String endereco, String json) {
        HttpURLConnection conexao = null;
        try {
            conexao = (HttpURLConnection) new URL(endereco).openConnection();
            conexao.setRequestMethod(metodo);
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);
            conexao.setRequestProperty("Accept", "application/json");

            if (json != null) {
                conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conexao.setDoOutput(true);
                try (DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {
                    saida.write(json.getBytes(StandardCharsets.UTF_8));
                    saida.flush();
                }
            }

            int status = conexao.getResponseCode();
            System.out.println("Método: " + metodo);
            System.out.println("URL: " + endereco);
            System.out.println("Código de status HTTP: " + status);
            System.out.println("Resposta do servidor:");

            InputStream resposta = status >= 400
                    ? conexao.getErrorStream()
                    : conexao.getInputStream();
            StringBuilder corpo = new StringBuilder();

            if (resposta != null) {
                try (BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(resposta, StandardCharsets.UTF_8))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        corpo.append(linha).append(System.lineSeparator());
                        System.out.println(linha);
                    }
                }
            } else {
                System.out.println("(Sem corpo de resposta)");
            }
            return new Resultado(status, corpo.toString());
        } catch (IOException e) {
            System.err.println("Erro na requisição " + metodo + ": " + e.getMessage());
            return null;
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }

    private static class Resultado {
        final int status;
        final String corpo;

        Resultado(int status, String corpo) {
            this.status = status;
            this.corpo = corpo;
        }
    }
}
