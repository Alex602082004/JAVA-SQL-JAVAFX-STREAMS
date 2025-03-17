package com.example.lab4;

import Domain.Comanda;
import Domain.ComandaStr;
import Domain.Tort;
import Exceptions.RepoException;
import Repos.*;
import Services.ServiceComanda;
import Services.ServiceTort;
import UI.Settings;
import UI.UInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws IOException, RepoException, SQLException {
        Settings settings = Settings.getInstance();


        RepoComanda c = null;
        RepoTort r = null;

        if ("memory".equals(settings.getRepoType())) {
            r = new RepoTort();
            c = new RepoComanda();
        }
        if ("txt".equals(settings.getRepoType())) {
            r = new RepoTxtTort("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\torttxt.txt");
            c = new RepoTxtComanda("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\comandatxt.txt");
        }
        if ("bin".equals(settings.getRepoType())) {
            r = new RepoBinTort("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\tortbin.bin");
            c = new RepoBinComanda("C:\\Users\\alexp\\IdeaProjects\\lab4\\src\\main\\java\\Files\\comandabin.bin");
        }
        if ("sql".equals(settings.getRepoType())) {
            r = new SqlRepoTort();
            c = new SqlRepoComanda();
        }

        if (c == null && r == null) {
            throw new RepoException("Repo-uri nule");
        }


        Calendar calendar = Calendar.getInstance();
        Tort t1 = new Tort("vanilie");
        Tort t2 = new Tort("caramel");
        Tort t3 = new Tort("bezea");
        Tort t4 = new Tort("cioco");
        Tort t5 = new Tort("mousse");
        List<Tort> torturi1 = Arrays.asList(t1, t2, t3, t4, t5);
        List<Tort> torturi2 = Arrays.asList(t1, t2, t3);
        String torturi11 = torturi1.toString();
        String torturi22 = torturi2.toString();


        calendar.set(2024, Calendar.MARCH, 1);
        Comanda c1 = new Comanda(torturi1, calendar.getTime());
        ComandaStr c11 = new ComandaStr(torturi11, calendar.getTime());
        calendar.set(2024, Calendar.MAY, 3);
        Comanda c2 = new Comanda(torturi2, calendar.getTime());
        ComandaStr c22 = new ComandaStr(torturi22, calendar.getTime());
        calendar.set(2024, Calendar.MARCH, 3);
        Comanda c3 = new Comanda(torturi1, calendar.getTime());
        ComandaStr c33 = new ComandaStr(torturi11, calendar.getTime());
        calendar.set(2024, Calendar.MARCH, 4);
        Comanda c4 = new Comanda(torturi2, calendar.getTime());
        calendar.set(2024, Calendar.MARCH, 5);
        Comanda c5 = new Comanda(torturi1, calendar.getTime());


        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);
        c.add(c5);

        r.add(t1);
        r.add(t2);
        r.add(t3);
        r.add(t4);
        r.add(t5);

    
        ServiceTort st = new ServiceTort(r);
        ServiceComanda sc = new ServiceComanda(c, r);
        UInterface ui = new UInterface(sc, st);

        if ("console".equals(settings.getStart())) {
            ui.run();
        }
        if ("gui".equals(settings.getStart())) {
            HelloApplication.main(args);
        }


    }
}
