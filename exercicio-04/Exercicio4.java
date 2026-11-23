import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Exercicio4 {
    public static void main(String[] args) {
        HttpURLConnection conexao = null;

        try {
            String endpoint = "https://apichallenges.com/sim/entities";
            String categoria = URLEncoder.encode("teste", StandardCharsets.UTF_8.name());
            String limite = URLEncoder.encode("5", StandardCharsets.UTF_8.name());
            String urlFinal = endpoint + "?categoria=" + categoria + "&limite=" + limite;

            System.out.println("URL final: " + urlFinal);

            URL url = new URL(urlFinal);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);

            int status = conexao.getResponseCode();
            System.out.println("Código de status HTTP: " + status);
            System.out.println("Resposta do servidor:");

            InputStream resposta = status >= 400
                    ? conexao.getErrorStream()
                    : conexao.getInputStream();

            if (resposta != null) {
                try (BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(resposta, StandardCharsets.UTF_8))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        System.out.println(linha);
                    }
                }
            } else {
                System.out.println("(Sem corpo de resposta)");
            }
        } catch (IOException e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
