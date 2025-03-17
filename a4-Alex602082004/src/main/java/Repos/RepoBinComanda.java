package Repos;

import Domain.Comanda;

import java.util.*;

import Domain.Tort;
import Exceptions.RepoException;

import java.io.*;
import java.util.List;

public class RepoBinComanda extends RepoComanda {
    private final String filePath;

    public RepoBinComanda(String filePath) {
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
            System.out.println("Eroare la citirea din fișierul text: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(filePath))) {
            List<Comanda> comenzi = super.getComenzi();
            bw.writeObject(comenzi);
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul text: " + e.getMessage());
        }
    }
}
