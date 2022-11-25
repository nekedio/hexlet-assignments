package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

class App {

    // BEGIN
    public synchronized static CompletableFuture<String> unionFiles(String file1, String file2, String newFile) {

        return CompletableFuture.supplyAsync(() -> {

            Path path1 = Paths.get(file1);

            String text1 = "";

            try {
                text1 = Files.readString(path1);
            } catch (IOException ex) {
                System.out.println("NoSuchFileException");
            }

            Path path2 = Paths.get(file2);

            String text2 = "";

            try {
                text2 = Files.readString(path2);
            } catch (IOException ex) {
                System.out.println("NoSuchFileException");
            }

            Path newPath = Paths.get(newFile);
            try {
                Files.deleteIfExists(newPath);
                Files.write(newPath, (text1 + text2).getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                System.out.println("NoSuchFileException");
            }

            return "Result of async computation";
        }).exceptionally(ex -> "Ошибка! \"" + ex.getMessage() + "\"");
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("file1.txt", "file2.txt", "dest.txt");

        result.get();
        // END
    }
}
