/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.exam20_ViewControl;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class RootController implements Initializable {
    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> value= FXCollections.observableArrayList();
        value.add("갤럭시S1");
        value.add("갤럭시S2");
        value.add("갤럭시S3");
        value.add("갤럭시S4");
        listView.setItems(value);
        
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
    }    
    
    
}
