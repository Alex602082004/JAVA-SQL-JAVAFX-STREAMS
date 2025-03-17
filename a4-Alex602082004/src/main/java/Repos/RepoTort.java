package Repos;

import Domain.Tort;
import Exceptions.RepoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class RepoTort implements RepoInter<Tort> {
    protected Vector<Tort> torturi;

    public RepoTort() throws IOException {
        torturi = new Vector<>();
    }

    @Override
    public void add(Tort t) throws RepoException {
        torturi.add(t);
    }

    @Override
    public void remove(int i) throws RepoException {
        for (Tort t : torturi) {
            if (t.getId() == i) {
                torturi.remove(t);
                return;
            }
        }
        throw new RepoException("Invalid ID");
    }

    @Override
    public void createComanda(List<Tort> l, Date data) throws FileNotFoundException {

    }

    public void deleteTortit(int it) throws RepoException {
        for (int i = 0; i < torturi.size(); i++) {
            if (torturi.get(i) == torturi.get(it)) {
                torturi.remove(torturi.get(i));
                return;
            }
        }
        throw new RepoException("Invalid ID");
    }

    @Override
    public int size() {
        return torturi.size();
    }

    @Override
    public void createTort(String CakeType) throws IOException, RepoException {
        Tort t = new Tort(CakeType);
        add(t);
    }

    public List<Tort> getTorturi() {
        return torturi;
    }

    public Tort getTortit(int it) throws RepoException {
        for (Tort tort : torturi) {
            if (tort == torturi.get(it)) {
                return tort;
            }
        }
        throw new RepoException("Invalid id");
    }

    @Override
    public Tort get(int index) throws RepoException {

        for (Tort tort : torturi)
            if (index == tort.getId())
                return tort;

        throw new RepoException("Invalid id");
    }

    public void update(int index, String CakeType) throws RepoException {
        for (Tort t : torturi) {
            if (t.getId() == index) {
                t.setCakeType(CakeType);
                return;
            }
        }
        throw new RepoException("Invalid id");
    }


    @Override
    public String toString() {
        return "Torturi = " + torturi;
    }
}
