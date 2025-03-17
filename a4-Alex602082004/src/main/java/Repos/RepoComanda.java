package Repos;

import Domain.Comanda;
import Domain.ComandaStr;
import Domain.Tort;
import Exceptions.RepoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class RepoComanda implements RepoInter<Comanda> {

    protected Vector<Comanda> comenzi;

    public RepoComanda() {
        comenzi = new Vector<>();
    }

    @Override
    public void add(Comanda c) throws IOException, RepoException {
        comenzi.add(c);
    }

    @Override
    public void createComanda(List<Tort> l, Date data) throws FileNotFoundException {
        Comanda c = new Comanda(l, data);
        comenzi.add(c);
    }

    public Vector<Comanda> getComenzi() {
        return comenzi;
    }

    public List<Comanda> getListComenzi() {
        return comenzi;
    }

    public Comanda getIDComanda(int id) throws RepoException {
        for (Comanda c : comenzi) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new RepoException("Invalid ID");
    }

    public Comanda getComandait(int it) throws RepoException {
        if (comenzi.isEmpty()) return null;
        for (int i = 0; i < comenzi.size(); i++) {
            if (comenzi.elementAt(i) == comenzi.elementAt(it)) {
                return comenzi.elementAt(i);
            }
        }
        throw new RepoException("Invalid comanda!");
    }

    @Override
    public int size() {
        return comenzi.size();
    }

    @Override
    public void createTort(String name) {
    }

    @Override
    public Comanda get(int i) throws RepoException {
        for (Comanda c : comenzi) {
            if (c.getId() == i) {
                return c;
            }
        }
        throw new RepoException("Invalid id");
    }

    public Vector<Date> getDate() {
        Vector<Date> date = new Vector<>();
        for (Comanda c : comenzi) {
            date.add(c.getData());
        }
        return date;
    }

    @Override
    public void remove(int i) throws RepoException {
        for (Comanda c : comenzi) {
            if (c.getId() == i) {
                comenzi.remove(c);
                return;
            }
        }
        throw new RepoException("Invalid id");
    }

    public void updateLista(int i, List<Tort> l) throws RepoException {
        for (Comanda c : comenzi) {
            if (c.getId() == i) {
                c.setCakes(l);
                return;
            }
        }
        throw new RepoException("Invalid id");

    }


    public Map<Date, Long> numarTorturiPeZi() {
        return comenzi.stream()
                .collect(Collectors.groupingBy(
                        Comanda::getData, // Grupăm după Date
                        Collectors.summingLong(c -> c.getCakes().size()) // Numărăm torturile pe zi
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Date, Long>comparingByValue().reversed()) // Sortăm descrescător după numărul de torturi
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Rezolvăm eventualele coliziuni (nu ar trebui să existe)
                        LinkedHashMap::new // Păstrăm ordinea sortării
                ));
    }


    public Map<String, Long> numarTorturiPeLuna() {
        return comenzi.stream()
                .collect(Collectors.groupingBy(
                        c -> {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(c.getData());
                            // Obținem numele lunii folosind Month din java.time
                            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                        },
                        Collectors.summingLong(c -> c.getCakes().size()) // Numărăm torturile
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Sortăm descrescător
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Nu e nevoie de rezolvare a coliziunilor în acest caz
                        LinkedHashMap::new // Păstrăm ordinea sortării
                ));
    }



    // 3. Cele mai des comandate torturi
    public Map<Tort, Long> celeMaiDesComandateTorturi() {
        return comenzi.stream()
                .flatMap(c -> c.getCakes().stream()) // Obținem un stream cu toate torturile din toate comenzile
                .collect(Collectors.groupingBy(
                        tort -> tort, // Grupăm după obiectul Tort
                        Collectors.counting() // Numărăm de câte ori apare fiecare tort
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Tort, Long>comparingByValue().reversed()) // Sortăm descrescător după numărul de comenzi
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Păstrăm ordinea sortării
                ));
    }

    @Override
    public String toString() {
        return "Comenzi = " + comenzi;
    }
}
