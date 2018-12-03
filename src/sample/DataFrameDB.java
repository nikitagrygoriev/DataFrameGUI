package sample;

import com.company.DataFrame;

import java.io.File;
import java.sql.ResultSetMetaData;

class DataFrameDB extends DataFrame {


    DataFrameDB(File file, String[] types, boolean header) {
        super(file, types, header);
    }


    DataFrame create(String req) throws Exception {
        SQLRunner sqlRunner = new SQLRunner();
        sqlRunner.connect();
        sqlRunner.request = sqlRunner.conn.createStatement();
        sqlRunner.result = sqlRunner.request.executeQuery("SELECT isbn, title, author, year FROM books");
        ResultSetMetaData resultMetaData = sqlRunner.result.getMetaData();
        int columnsNumber = resultMetaData.getColumnCount();
        while (sqlRunner.result.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String out = sqlRunner.result.getString(i);
                System.out.print(out + ",");
            }
            System.out.println();
        }
        return null;
    }


}
