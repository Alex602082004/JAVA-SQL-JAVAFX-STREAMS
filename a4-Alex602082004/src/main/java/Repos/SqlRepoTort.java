package Repos;

import Domain.Tort;
import Exceptions.RepoException;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlRepoTort extends RepoTort implements AutoCloseable {
    private static final String JDBC_URL = "jdbc:sqlite:src/main/java/Files/torturi.db";

    private Connection conn = null;

    public SqlRepoTort() throws IOException {
        openConnection();
        createSchema();
        loadData();
    }

    private void loadData() {
        try {

            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Torturi");
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Tort tort = new Tort(rs.getInt("id"), rs.getString("CakeType"));
                    torturi.add(tort);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Torturi(id int PRIMARY KEY, CakeType varchar(100));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    @Override
    public void add(Tort elem) throws RepoException {
        super.add(elem);
        // daca se ajunge aici, trebuie actualizata baza de date

        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Torturi VALUES (?, ?)")) {
                statement.setInt(1, elem.getId());
                statement.setString(2, elem.getCakeType());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepoException("Eroare la salvarea in baza de date");
        }
    }

    public void update(int id, String cakeType) throws RepoException {
        super.update(id, cakeType);
        try {
            try (var statement = conn.prepareStatement("UPDATE Torturi SET Caketype=(?) WHERE id=(?)")) {
                statement.setString(1, cakeType);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) throws RepoException {
        super.remove(id);

        try (var statement = conn.prepareStatement("DELETE FROM Torturi WHERE id = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // NOTE
//            e.printStackTrace();
            throw new RepoException("Eroare la stergerea tortului: " + e.getMessage());
        }
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
