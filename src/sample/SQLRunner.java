package sample;

import java.io.File;
import java.sql.*;

public class SQLRunner {
    Connection conn = null;
    Statement request = null;
    ResultSet result = null;

    public static void main(String[] args) {
        SQLRunner sqlRunner = new SQLRunner();
        DataFrameDB t = new DataFrameDB(new File("aa"), new String[]{"aa"}, true);
        try {
            t.create("SELECT isbn, title, author, year FROM books");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/grygorie",
                            "grygorie", "H6uqco1CFVaynk14");


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void listNames() {
        try {
            connect();
            System.out.println("Connected");
            request = conn.createStatement();
            result = request.executeQuery("SELECT isbn, title, author, year FROM books");
            ResultSetMetaData resultMetaData = result.getMetaData();
            int columnsNumber = resultMetaData.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String out = result.getString(i);
                    System.out.print(out + ",");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException sqlEx) {
                } // ignore
                result = null;
            }

            if (request != null) {
                try {
                    request.close();
                } catch (SQLException sqlEx) {
                } // ignore

                request = null;
            }
        }
    }

    public void createTable() throws Exception {
        request = conn.createStatement();
        request.executeUpdate(
                "CREATE TABLE tabela1 ("
                        + "priKey INT NOT NULL AUTO_INCREMENT, "
                        + "nazwisko VARCHAR(64), PRIMARY KEY (priKey))");

    }

    public void addUser() throws Exception {
        request = conn.createStatement();
        request.executeUpdate(
                "INSERT INTO tabela1 (nazwisko) "
                        + "values ('Bobek')");
    }
}
