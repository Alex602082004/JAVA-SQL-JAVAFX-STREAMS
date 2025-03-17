package Repos;

import Domain.Tort;
import Exceptions.RepoException;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class RepoTxtTort extends RepoTort {
    private final String filePath;

    public RepoTxtTort(String filePath) throws IOException {
        super();
        this.filePath = filePath;
        loadFromFile();
    }

    @Override
    public void add(Tort tort) throws RepoException {
        super.add(tort);
        saveToFile();
    }

    @Override
    public void remove(int i) throws RepoException {
        super.remove(i);
        saveToFile();
    }

    @Override
    public void update(int index, String CakeType) throws RepoException {
        super.update(index, CakeType);
        saveToFile();
    }

    private void loadFromFile() {
        try (ObjectInputStream br = new ObjectInputStream(new FileInputStream(filePath))) {
            this.torturi = (Vector<Tort>) br.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la citirea din fișierul text: " + e.getMessage());
        }
    }


    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            List<Tort> torturi = super.getTorturi();

            for (Tort tort : torturi) {
                bw.write(tort.toString());
            }


        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul text: " + e.getMessage());
        }
    }
}

