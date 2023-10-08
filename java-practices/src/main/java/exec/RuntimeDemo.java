package exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        // Runtime uses ProcessBuilder as the backend
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(new String[]{ "bash", "-c", "ls" });
        output(process);
        System.out.println("----------");

        process = runtime.exec(new String[]{ "bash", "-c", "echo Hello $VAR" }, new String[]{ "VAR=David" });
        output(process);
        System.out.println("----------");

        process = runtime.exec(new String[]{ "bash", "-c", "ls" }, null, new File("/"));
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
