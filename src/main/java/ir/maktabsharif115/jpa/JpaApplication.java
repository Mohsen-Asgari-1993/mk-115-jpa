package ir.maktabsharif115.jpa;

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
                        Test.builder()
                                .name("mohsen")
                                .build()
                );
            }
        }
    }

    @SneakyThrows
    private static void readObjectFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("object.txt")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                System.out.println(objectInputStream.readObject());
            }
        }
    }

}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
class Test implements Serializable {

    @Serial
    private static final long serialVersionUID = 5;

    private String name;

    private String id;

}