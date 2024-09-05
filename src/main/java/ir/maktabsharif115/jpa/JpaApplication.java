package ir.maktabsharif115.jpa;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {
        String name = "\nAli alavi\n";

//        File file = new File();

        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("my-name.txt")
        );
        bufferedWriter.write(name);
        bufferedWriter.close();
    }

}
