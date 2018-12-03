package sample;

import com.company.*;

import javax.xml.crypto.Data;
import java.io.File;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

class DataFrameDB extends DataFrame {


    DataFrameDB(File file, String[] types, boolean header) {
        super(file, types, header);
    }


    static DataFrame create(String types[], String req) throws Exception {
        DataFrame res = new DataFrame(types, types);
        SQLRunner sqlRunner = new SQLRunner();
        sqlRunner.connect();
        sqlRunner.request = sqlRunner.conn.createStatement();
        sqlRunner.result = sqlRunner.request.executeQuery(req);
        while (sqlRunner.result.next()) {
            ArrayList<Value> row = new ArrayList<>();
            for (int i = 1; i <= types.length; i++) {
                String out = sqlRunner.result.getString(i);
                Value tmp;
                switch (types[i - 1]) {
                    case "NewInteger":
                        tmp = new NewInteger.Builder().val(Integer.parseInt(out)).build();
                        break;
                    case "NewDouble":
                        tmp = new NewDouble.Builder().val(Double.parseDouble(out)).build();
                        break;
                    case "NewDateTime":
                        tmp = new NewDateTime.Builder().val(out).build();
                        break;
                    case "NewString":
                        tmp = new NewString.Builder().val(out).build();
                        break;
                    default:
                        tmp = new NewString.Builder().val(out).build();
                        break;
                }
                row.add(tmp);
            }
            res.add(row);
        }
        return res;
    }

    static DataFrame max(String types[], String req, int col) throws Exception {
        String request = req + " ORDER BY " + col + " DESC LIMIT 1;";
        return DataFrameDB.create(types, request);
    }

    static DataFrame min(String types[], String req, int col) throws Exception {
        String request = req + " ORDER BY " + col + " ASC LIMIT 1;";
        return DataFrameDB.create(types, request);
    }

    static DataFrame group(String types[], String req, int col) throws Exception {
        String request = req + " GROUP BY " + col + ";";
        return DataFrameDB.create(types, request);
    }

}
