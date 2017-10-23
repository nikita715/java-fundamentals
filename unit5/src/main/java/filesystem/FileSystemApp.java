package filesystem;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSystemApp {

    private File currentFile = new File("/");
    private static Scanner scanner = new Scanner(System.in);
    private PrintStream printStream = new PrintStream(System.out);

    private void run() {
        boolean isRunning = true;

        printStream.println("Type \"help\" for list of commands");

        while (isRunning) {

            printStream.printf("%s > ", currentFile.getAbsolutePath());

            String input = scanner.nextLine();

            String action = "";
            String arguments = "";

            Pattern inputPattern = Pattern.compile("([^\\s-]*) *(.*)");
            Matcher matcher = inputPattern.matcher(input);

            matcher.find(0);
            action = matcher.group(1);
            arguments = matcher.group(2);

            switch (action) {
                case ("help"):
                    printListOfCommands();
                    break;
                case ("cd"):
                    changePath(arguments);
                    break;
                case ("create"):
                    createNewFile(arguments);
                    break;
                case ("contents"):
                    showContentsOfDirectory();
                    break;
                case ("delete"):
                    deleteFile();
                    break;
                case ("open"):
                    openCurrentFile();
                    break;
                case ("newline"):
                    writeNewLine(arguments);
                    break;
                case ("exit"):
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void openCurrentFile() {
        FileSystemApp fileSystemApp = new FileSystemApp();
        String lines = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines = lines.concat(line).concat("\n");
            }
            printStream.println(lines);
        } catch (IOException e) {
            printStream.println("Reading of this file is not supported");
        }
    }

    private void printListOfCommands() {
        printStream.print("cd\n" +
                "create\n" +
                "contents\n" +
                "delete\n" +
                "open\n" +
                "newline\n" +
                "exit\n");
    }

    private void changePath(String path) {
        File newFile;
        if (path.charAt(0) == '/') {
            newFile = new File(path);
        } else {
            newFile = new File(currentFile.getAbsolutePath() + '/' + path);
        }
        try {
            if (newFile.exists()) {
                currentFile = newFile;
                if (currentFile.isFile()) {
                    openCurrentFile();
                }
            } else {
                throw new FileNotFoundException("File does not exist");
            }
        } catch (FileNotFoundException e) {
            printStream.println(e.getMessage());
        }
    }

    private void showContentsOfDirectory() {
        String[] contents = currentFile.list();
        if (contents == null || contents.length == 0) {
            printStream.println("Empty directory");
        } else {
            for (String content : contents) {
                printStream.println(content);
            }
        }
    }

    private void writeNewLine(String text) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(currentFile, true))) {
            writer.write(text);
        } catch (IOException e) {
            printStream.println("Text insertion is not allowed for this file");
        }
    }

    private boolean createNewFile(String name) {
        try {
            String pathToNewFile = String.format("%s/%s", currentFile.getAbsolutePath(), name);
            File newFile = new File(pathToNewFile);
            return newFile.createNewFile();
        } catch (IOException e) {
            printStream.println("Unable to create new file");
        }
        return false;
    }

    private boolean deleteFile() {
        return currentFile.delete();
    }

    public static void main(String[] args) {
        FileSystemApp fileSystemApp = new FileSystemApp();
        fileSystemApp.run();
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
}
