import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Exercicio7 {
    private static final String URL_API = "https://apichallenges.eviltester.com/sim/entities/10";

    public static void main(String[] args) {
        executar("POST", "{\"name\":\"atualizado\"}");
        System.out.println("\nVerificação por GET:");
        executar("GET", null);
        System.out.println("Compare as respostas: a API de simulação não garante persistência.");
    }

    private static void executar(String metodo, String json) {
        HttpURLConnection conexao = null;
        try {
            conexao = (HttpURLConnection) new URL(URL_API).openConnection();
            conexao.setRequestMethod(metodo);
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);
            conexao.setRequestProperty("Accept", "application/json");
            if (json != null) {
                conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conexao.setDoOutput(true);
                try (DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {
                    saida.write(json.getBytes(StandardCharsets.UTF_8));
                }
            }
            int status = conexao.getResponseCode();
            System.out.println("Método: " + metodo);
            System.out.println("Código HTTP: " + status);
            System.out.println("Resposta do servidor:");
            InputStream stream = status >= 400 ? conexao.getErrorStream() : conexao.getInputStream();
            if (stream == null) {
                System.out.println("(Sem corpo de resposta)");
            } else {
                try (BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        System.out.println(linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            if (conexao != null) conexao.disconnect();
        }
    }
}
