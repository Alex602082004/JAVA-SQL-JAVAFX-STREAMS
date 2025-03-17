package Services;

import Domain.Tort;
import Exceptions.RepoException;
import Repos.RepoTort;

import java.io.IOException;

public class ServiceTort {
    RepoTort repoTort;

    public ServiceTort(RepoTort repoTort) {
        this.repoTort = repoTort;
    }

    public RepoTort getRepoTort() {
        return repoTort;
    }

    public void ServiceAddTort(Tort t) throws IOException, RepoException {
        repoTort.add(t);
    }

    public void ServiceDeleteTort(int i) throws RepoException, IOException {
        repoTort.remove(i);
    }

    public void ServiceCitireTort(String CakeType) throws IOException, RepoException {
        repoTort.createTort(CakeType);
    }

    public Tort ServiceGetTort(int i) throws RepoException {
        return repoTort.get(i);
    }

    public void ServiceUpdateTort(int i, String CakeType) throws RepoException {
        repoTort.update(i, CakeType);
    }

    @Override
    public String toString() {
        return repoTort.toString();
    }
}
