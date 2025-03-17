package Domain;

import java.io.FileNotFoundException;
import java.util.Date;

public class ComandaStr extends ID {
    String torturi;
    Date date;

    public ComandaStr(int id, String torturi, Date date) throws FileNotFoundException {
        this.id = id;
        this.torturi = torturi;
        this.date = date;
    }

    public ComandaStr(String torturi, Date date) throws FileNotFoundException {
        super();
        this.torturi = torturi;
        this.date = date;
    }

    public String getTorturi() {
        return torturi;
    }

    public Date getDate() {
        return date;
    }

    public void setTorturi(String torturi) {
        this.torturi = torturi;
    }

    @Override
    public String toString() {
        return "ComandaStr [id= " + id + ", torturi= " + torturi + ", date= " + date + "]\n";
    }


}
