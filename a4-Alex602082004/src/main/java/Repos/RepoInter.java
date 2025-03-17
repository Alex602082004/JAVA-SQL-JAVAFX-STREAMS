package Repos;

import Domain.ID;
import Domain.Tort;
import Exceptions.RepoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface RepoInter<T extends ID> {

    void add(T t) throws IOException, RepoException;

    void remove(int i) throws RepoException, IOException;

    void createComanda(List<Tort> l, Date data) throws FileNotFoundException;

    int size();

    void createTort(String name) throws IOException, RepoException;

    T get(int i) throws RepoException;
}

