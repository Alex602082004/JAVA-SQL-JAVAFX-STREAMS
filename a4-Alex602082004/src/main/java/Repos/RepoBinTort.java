package Repos;

import Domain.Tort;
import Exceptions.RepoException;


import java.io.*;
import java.util.List;
import java.util.Vector;

public class RepoBinTort extends RepoTort {

    private final String filePath;

    public RepoBinTort(String filePath) throws IOException {
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
            this.torturi = new Vector<>();

            while (true) {
                try {
                    Tort tort = (Tort) br.readObject();
                    this.torturi.add(tort);
                } catch (EOFException e) {

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea din fișier: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clasa Tort nu a fost găsită: " + e.getMessage());
        }
    }


    private void saveToFile() {
        try (ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(filePath, false))) {
            List<Tort> torturi = super.getTorturi();

            for (Tort tort : torturi) {
                bw.writeObject(tort);
                bw.flush();
                bw.reset();
            }

        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul text: " + e.getMessage());
        }
    }
}

