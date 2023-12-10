package jfxpackager.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderTool {
    private static String operationalSystemIdentifier(){
        return System.getProperty("os.name").toLowerCase();
    }

    private static void redirectError(ProcessBuilder pb) throws IOException {
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }

    public static void RunProcess(String commands){
        String osName = operationalSystemIdentifier();

        if (osName.contains("win")) {
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", commands);
                redirectError(pb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            try {
                ProcessBuilder pb = new ProcessBuilder("bash", "-c", commands);
                redirectError(pb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
