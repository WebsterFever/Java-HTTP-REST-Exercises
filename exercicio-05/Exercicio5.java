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

public class Exercicio5 {
    public static void main(String[] args) {
        HttpURLConnection conexao = null;

        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);

            String json = "{\"name\":\"aluno\"}";
            try (DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {
                saida.write(json.getBytes(StandardCharsets.UTF_8));
                saida.flush();
            }

            int status = conexao.getResponseCode();
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

            Matcher id = Pattern.compile("\"id\"\\s*:\\s*(\\d+)").matcher(corpo);
            if (id.find()) {
                System.out.println("ID gerado: " + id.group(1));
            } else {
                System.out.println("ID não identificado no corpo da resposta.");
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
