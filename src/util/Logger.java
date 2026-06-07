package util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String ARQUIVO_LOG = "log_sistema.txt";

    public static void registrar(String mensagem) {
        try (FileWriter fw = new FileWriter(ARQUIVO_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            pw.println(dataHora + " - " + mensagem);
        } catch (IOException e) {
            System.out.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}