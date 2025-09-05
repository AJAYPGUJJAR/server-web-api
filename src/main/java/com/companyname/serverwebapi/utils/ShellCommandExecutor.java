package com.companyname.serverwebapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ShellCommandExecutor {

    private ShellCommandExecutor() {
        // To Prevent instantiation
    }

    public static String executeShellCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);
        processBuilder.redirectErrorStream(true);
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            boolean finished = process.waitFor(60, java.util.concurrent.TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                return "Error: Command timed out after 60 seconds: " + command;
            }
            int exitCode = process.exitValue();
            if (exitCode != 0) {
                return "Error: Command exited with error code " + exitCode + ": " + output;
            }
            return output.toString();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error executing command: " + e.getMessage();
        }
    }


}
