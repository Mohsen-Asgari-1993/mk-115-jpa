package ir.maktabsharif115.jpa;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {

        StringBuilder resultStringBuilder = new StringBuilder();
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("my-name.txt")
                        )
//                        new FileReader(
//                                "my-name.txt"
//                        )
                )
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        System.out.println(resultStringBuilder);
    }

}
