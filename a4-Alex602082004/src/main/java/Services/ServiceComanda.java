package Services;

import Domain.Comanda;
import Domain.Tort;
import Exceptions.RepoException;
import Exceptions.ValidateDate;
import Repos.RepoComanda;
import Repos.RepoTort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ServiceComanda {
    RepoComanda repoComanda;
    RepoTort repoTort;

    public ServiceComanda(RepoComanda repoComanda, RepoTort repoTort) {
        this.repoComanda = repoComanda;
        this.repoTort = repoTort;
    }

    public void ServiceCitireComanda(List<Tort> l, Date data) throws FileNotFoundException {
        repoComanda.createComanda(l, data);
    }

    public RepoComanda getRepoComanda() {
        return repoComanda;
    }

    public void ServiceAddComanda(int nr, List<String> types, int luna, int zi) throws IOException, RepoException {
        //pot avea mai multe tipuri, dar clientul nu imi zice id-ul tortului

        ValidateDate val = new ValidateDate();
        List<Tort> t = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, luna, zi);
        if (val.validate(repoComanda.getComenzi(), luna, zi) == 1)
            throw new RepoException("Invalid Date");
        if (nr > repoTort.size()) throw new RepoException("Nu avem stock!");
        else {
            while (nr != 0) {
                for (int i = 0; i < types.size(); i++) {
                    for (int j = 0; j < repoTort.size(); j++) {
                        if (Objects.equals(types.get(i), repoTort.getTortit(j).getCakeType())) {
                            t.add(repoTort.getTortit(j));
                            repoTort.deleteTortit(j);
                            types.remove(i);
                            break;

                        }

                    }
                }
                nr--;
            }
            if (t.isEmpty()) throw new RepoException("Comanda gresita!");
            Comanda c = new Comanda(t, calendar.getTime());
            repoComanda.add(c);
        }
    }

    public void ServiceRemoveComanda(int i) throws RepoException, IOException {
        for (int j = 0; j < repoComanda.getComenzi().size(); j++)
            if (repoComanda.getComenzi().get(j).getId() == i) {
                int k = repoComanda.getComenzi().get(j).getCakes().size();
                int cnt = 0;
                while (k != 0) {
                    repoTort.add(repoComanda.getComenzi().get(j).getCakes().get(cnt));
                    cnt++;
                    k--;
                }
                repoComanda.remove(i);
                return;
            }
        throw new RepoException("Invalid ID");
    }

    public Comanda ServiceGetComanda(int i) throws RepoException {
        return repoComanda.get(i);
    }

    public void ServiceUpdateComandaTorturi(int id, int nr, List<String> types) throws RepoException, IOException {
        List<Tort> l = new ArrayList<>();
        if (nr > repoTort.size()) throw new RepoException("Nu avem stock!");
        for (Comanda c : repoComanda.getComenzi()) {
            if (c.getId() == id) {
                for (Tort t : c.getCakes())
                    repoTort.add(t);
                while (nr != 0) {
                    for (int i = 0; i < types.size(); i++) {
                        for (int j = 0; j < repoTort.size(); j++) {
                            if (Objects.equals(types.get(i), repoTort.getTortit(j).getCakeType())) {
                                l.add(repoTort.getTortit(j));
                                repoTort.deleteTortit(j);
                                types.remove(i);
                                break;
                            }
                        }
                        nr--;
                    }
                }
                repoComanda.updateLista(id, l);
                return;
            }

        }
        throw new RepoException("Invalid ID");
    }

    public void ServiceUpdateComandaData(int i, int luna, int ziua) throws RepoException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, luna, ziua);
        ValidateDate val = new ValidateDate();
        if (val.validate(repoComanda.getComenzi(), luna, ziua) == 1)
            throw new RepoException("Invalid Date");
        for (Comanda c : repoComanda.getComenzi()) {
            if (c.getId() == i) {
                repoComanda.getIDComanda(i).setData(calendar.getTime());
                return;
            }
        }
        throw new RepoException("Invalid ID");
    }

    public Map<Date, Long> NrTorturiPeZi() {
        return repoComanda.numarTorturiPeZi();
    }

    public Map<String, Long> NrTorturiPeLuna() {
        return repoComanda.numarTorturiPeLuna();
    }

    public Map<Tort, Long> CeleMaiDesComandateTorturi() {
        return repoComanda.celeMaiDesComandateTorturi();
    }

    @Override
    public String toString() {
        return repoComanda.toString();
    }
}
