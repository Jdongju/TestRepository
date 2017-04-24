/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.exam19_inputControl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class RootController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private PasswordField txtPassWord;
    @FXML
    private ComboBox<String> comboPublic;
    @FXML
    private DatePicker dateExit;
    @FXML
    private TextArea txtContents;
    @FXML
    private Button btnReg;
    @FXML
    private Button btnCalcel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> value=FXCollections.observableArrayList();
        value.add("공개");
        value.add("비공개");
        comboPublic.setItems(value);
    }    
    
    public void btnRegHandle(ActionEvent event){
        String title=txtTitle.getText();
        String passWord=txtPassWord.getText();
        String strPublic=comboPublic.getValue();
        LocalDate localDate = dateExit.getValue();
        String content = txtContents.getText();
        
        System.out.println("title: "+title);
        System.out.println("passWord: "+passWord);
        System.out.println("strPublic: "+strPublic);
        System.out.println("localDate: "+localDate);
        System.out.println("content: "+content);
    }
    
}
