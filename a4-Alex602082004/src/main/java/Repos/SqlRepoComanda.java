package Repos;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

import Domain.Comanda;
import Domain.ComandaStr;
import Domain.Tort;
import Exceptions.RepoException;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.util.List;

public class SqlRepoComanda extends RepoComanda implements AutoCloseable {
    private static final String JDBC_URL = "jdbc:sqlite:src/main/java/Files/comanda.db";

    private Connection conn = null;
    List<ComandaStr> comenzistr;

    public List<ComandaStr> getComenzistr() {
        return comenzistr;
    }

    public SqlRepoComanda() throws SQLException, FileNotFoundException {
        comenzistr = new ArrayList<>();
        openConnection();
        createSchema();
        loadData();
    }


    public void loadData() throws SQLException, FileNotFoundException {

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM Comenzi");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ComandaStr comanda = new ComandaStr(rs.getInt("id"), rs.getString("Torturi"), rs.getDate("Date"));

                comenzistr.add(comanda);
            }
        }
    }


    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la conectarea cu baza de date", e);
        }
    }

    private void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Comenzi(id int PRIMARY KEY, Torturi varchar(1000),date Date);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    public void add(Comanda elem) throws IOException, RepoException {
        super.add(elem);
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Comenzi VALUES (?,?,?)")) {
                statement.setInt(1, elem.getId());
                statement.setString(2, elem.getCakes().toString());
                java.util.Date utilDate = elem.getData();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                statement.setDate(3, sqlDate);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepoException("Eroare la salvarea in baza de date");
        }
    }

    public void addstr(ComandaStr elem) throws IOException, RepoException {
        comenzistr.add(elem);
        // daca se ajunge aici, trebuie actualizata baza de date

        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Comenzi VALUES (?,?,?)")) {
                statement.setInt(1, elem.getId());
                statement.setString(2, elem.getTorturi());
                java.util.Date utilDate = elem.getDate();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                statement.setDate(3, sqlDate);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepoException("Eroare la salvarea in baza de date");
        }
    }

    public void update(int id, List<Tort> cakes) throws RepoException {
//        super.updateLista(id, cakes);
        for (ComandaStr comanda : comenzistr) {
            if (comanda.getId() == id) {
                comanda.setTorturi(cakes.toString());
            }
        }
        try {
            try (var statement = conn.prepareStatement("UPDATE Comenzi SET Torturi=(?) WHERE id=(?)")) {
                statement.setString(1, cakes.toString());
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) throws RepoException {
        comenzistr.removeIf(comanda -> comanda.getId() == id);

        try (var statement = conn.prepareStatement("DELETE FROM Comenzi WHERE id = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // NOTE
//            e.printStackTrace();
            throw new RepoException("Eroare la stergerea comenzii: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return comenzistr.toString();
    }

    @Override
    public void close() throws RepoException {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            throw new RepoException("Eroare la conexiune!");
        }
    }
}

