package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.User;
import lombok.*;

import java.io.*;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {

//        writeObjectToFile();
        readObjectFromFile();


    }

    private static void writeObjectToFile() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("object.txt")) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(
                        new Test("mohsen")
                );
            }
        }
    }

    @SneakyThrows
    private static void readObjectFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("object.txt")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object readObject = objectInputStream.readObject();
                User test = (User) readObject;
                System.out.println(test.getFirstName());
            }
        }
    }

}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Test implements Serializable {
    private String name;
}