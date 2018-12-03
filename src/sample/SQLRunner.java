package sample;

import com.company.DataFrame;

import java.io.File;
import java.sql.*;

public class SQLRunner {
    Connection conn = null;
    Statement request = null;
    ResultSet result = null;

    public static void main(String[] args) {
//        SQLRunner sqlRunner = new SQLRunner();
        try {
            DataFrame test;
            test = DataFrameDB.create(new String[]{"NewString", "NewString", "NewString", "NewInteger"}, "SELECT isbn, title, author, year FROM books");
//            test.dfprint();
            test = DataFrameDB.max(new String[]{"NewString", "NewString", "NewString", "NewInteger"}, "SELECT isbn, title, author, year FROM books", 4);
            test.dfprint();
            test = DataFrameDB.min(new String[]{"NewString", "NewString", "NewString", "NewInteger"}, "SELECT isbn, title, author, year FROM books", 4);
            test.dfprint();
            test = DataFrameDB.group(new String[]{"NewString", "NewString", "NewString", "NewInteger"}, "SELECT isbn, title, author, year FROM books", 4);
            test.dfprint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/grygorie",
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
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException sqlEx) {
                }
                result = null;
            }

            if (request != null) {
                try {
                    request.close();
                } catch (SQLException sqlEx) {
                }
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
