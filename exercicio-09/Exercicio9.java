import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercicio9 {
    private static final String ENDPOINT = "https://apichallenges.eviltester.com/sim/entities/9";

    public static void main(String[] args) {
        executar("DELETE");
        System.out.println("\nVerificando a exclusão com GET:");
        executar("GET");
    }

    private static void executar(String metodo) {
        HttpURLConnection conexao = null;
        try {
            conexao = (HttpURLConnection) new URL(ENDPOINT).openConnection();
            conexao.setRequestMethod(metodo);
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);
            conexao.setRequestProperty("Accept", "application/json");

            int status = conexao.getResponseCode();
            System.out.println("Método: " + metodo);
            System.out.println("URL: " + ENDPOINT);
            System.out.println("Código HTTP: " + status);
            if (metodo.equals("GET") && status == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Entidade não encontrada (404).");
            }
            if (metodo.equals("DELETE") && (status == 403 || status == 405)) {
                System.out.println("A exclusão da entidade não é permitida.");
            }
            System.out.println("Resposta do servidor:");

            InputStream resposta = status >= 400
                    ? conexao.getErrorStream()
                    : conexao.getInputStream();

            if (resposta == null) {
                System.out.println("(Sem corpo de resposta)");
            } else {
                try (BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(resposta, StandardCharsets.UTF_8))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        System.out.println(linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na requisição " + metodo + ": " + e.getMessage());
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
