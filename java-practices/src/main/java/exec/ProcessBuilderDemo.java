package exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessBuilderDemo {
    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "ls");
        Process process = processBuilder.start();
        output(process);
        System.out.println("----------");

        Map<String, String> env = processBuilder.environment();
        env.put("VAR", "David");
        processBuilder.command("bash", "-c", "echo hello $VAR");
        process = processBuilder.start();
        output(process);
        System.out.println("----------");

        processBuilder.command("bash", "-c", "ls").directory(new File("/"));
        process = processBuilder.start();
        output(process);
    }

    private static void output(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
