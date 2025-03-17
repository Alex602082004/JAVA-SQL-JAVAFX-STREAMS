package Domain;

import java.io.*;
import java.util.Scanner;

  // Adnotăm clasa pentru a o face compatibilă cu JAXB
public abstract class ID implements Serializable {

    File file = new File("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\id");
    protected int id;
    static int k = 100;

    public ID() throws FileNotFoundException {
        try (Scanner sc = new Scanner(file)) {
            if (sc.hasNextInt())
                k = sc.nextInt();
        }
        k++;
        id = k;
        try (FileWriter fw = new FileWriter("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\id", false)) {
            fw.write(String.valueOf(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

      // Adnotăm câmpul id pentru a fi inclus în serializare
    public int getId() {
        return id;
    }
}
