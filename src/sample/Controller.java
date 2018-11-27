package sample;


import com.company.DataFrame;
import com.company.Value;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    @FXML
    TextField col1;
    @FXML
    TextField col2;
    @FXML
    TextField col3;
    @FXML
    TextField col4;
    @FXML
    TextField colGroup1;
    @FXML
    TextField colGroup2;
    @FXML
    Button bSum;
    @FXML
    Button bAvg;
    @FXML
    Button bMed;
    @FXML
    Button bVar;
    @FXML
    Button bStd;
    @FXML
    TextArea tRes;
    @FXML
    TextField grouper;

    DataFrame data;
    ArrayList<DataFrame> groupedData;


    public void bOpen() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage chooser = new Stage();
        chooser.setTitle("File Selector");
        chooser.setScene(new Scene(root, 800, 600));
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(chooser);
        String[] types = new String[]{col1.getText(), col2.getText(), col3.getText(), col4.getText()};
        DataFrame dataFrame = new DataFrame(f, types, true);
        bAvg.setVisible(true);
        bMed.setVisible(true);
        bStd.setVisible(true);
        bSum.setVisible(true);
        bVar.setVisible(true);
        data = dataFrame;
    }

    public void bSum() throws Exception {
        ArrayList<Value> aggregated = DataFrame.getSum(groupedData, Integer.parseInt(grouper.getText()));
        tRes.setText("");
        for (Value data : aggregated) {
            tRes.setText(tRes.getText() + data.get() + "\n");
        }
    }

    public void bAvg() throws Exception {
        ArrayList<Value> aggregated = DataFrame.getMean(groupedData, Integer.parseInt(grouper.getText()));
        tRes.setText("");
        for (Value data : aggregated) {
            tRes.setText(tRes.getText() + data.get() + "\n");
        }
    }

    public void bStd() throws Exception {
        ArrayList<Value> aggregated = DataFrame.getStd(groupedData, Integer.parseInt(grouper.getText()));
        tRes.setText("");
        for (Value data : aggregated) {
            tRes.setText(tRes.getText() + data.get() + "\n");
        }
    }

    public void bVar() throws Exception {
        ArrayList<Value> aggregated = DataFrame.getVar(groupedData, Integer.parseInt(grouper.getText()));
        tRes.setText("");
        for (Value data : aggregated) {
            tRes.setText(tRes.getText() + data.get() + "\n");
        }
    }

    public void bMed() throws Exception {
        tRes.setText("");
        ArrayList<Value> aggregated = DataFrame.getMedian(groupedData, Integer.parseInt(grouper.getText()));
        for (Value data : aggregated) {
            tRes.setText(tRes.getText() + data.get() + "\n");
        }
    }

    public void bGroup() throws Exception {
        int a = Integer.parseInt(colGroup1.getText());
        int b = Integer.parseInt(colGroup2.getText());
        groupedData = data.groupby(new int[]{a, b});
        for (DataFrame d : groupedData) {
            d.dfprint();
        }
    }


}
