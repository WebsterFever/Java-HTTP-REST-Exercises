import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercicio3 {
    public static void main(String[] args) {
        HttpURLConnection conexao = null;

        try {
            URL url = new URL("https://apichallenges.com/sim/entities/13");
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);

            int status = conexao.getResponseCode();
            System.out.println("Código de status HTTP: " + status);

            if (status == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Entidade não encontrada: o ID 13 não existe.");
            } else if (status >= 200 && status < 300) {
                System.out.println("Requisição realizada com sucesso.");
            } else {
                System.out.println("A requisição retornou um status inesperado.");
            }

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
