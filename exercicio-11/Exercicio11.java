import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercicio11 {
    public static void main(String[] args) {
        HttpURLConnection conexao = null;

        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("OPTIONS");
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);

            int status = conexao.getResponseCode();
            String metodosPermitidos = conexao.getHeaderField("Allow");

            System.out.println("Método: OPTIONS");
            System.out.println("URL: " + url);
            System.out.println("Código de status HTTP: " + status);

            if (metodosPermitidos != null && !metodosPermitidos.isEmpty()) {
                System.out.println("Métodos HTTP permitidos (Allow): " + metodosPermitidos);
            } else {
                System.out.println("O servidor não retornou o cabeçalho Allow.");
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
            System.err.println("Erro na requisição: " + e.getMessage());
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
