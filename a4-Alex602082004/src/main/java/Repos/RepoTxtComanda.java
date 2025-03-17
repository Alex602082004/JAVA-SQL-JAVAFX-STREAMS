package Repos;

import Domain.Comanda;
import Domain.Tort;
import Exceptions.RepoException;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class RepoTxtComanda extends RepoComanda {
    private final String filePath;

    public RepoTxtComanda(String filePath) {
        super();
        this.filePath = filePath;
        loadFromFile();
    }

    @Override
    public void add(Comanda comanda) throws IOException, RepoException {
        super.add(comanda);
        saveToFile();
    }

    @Override
    public void remove(int i) throws RepoException {
        super.remove(i);
        saveToFile();
    }

    @Override
    public void updateLista(int i, List<Tort> l) throws RepoException {
        super.updateLista(i, l);
        saveToFile();
    }

    private void loadFromFile() {
        try (ObjectInputStream br = new ObjectInputStream(new FileInputStream(filePath))) {
            this.comenzi = (Vector<Comanda>) br.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la citirea din fișierul binar: " + e.getMessage());
        }
    }


    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            List<Comanda> comenzi = super.getComenzi();
            for (Comanda c : comenzi) {
                bw.write(c.toString());
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul text: " + e.getMessage());
        }
    }
}

