package ch17.exam20_special;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RootController implements Initializable {

    @FXML
    private ListView<Phone> listView;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Phone>, ListCell<Phone>>() {
            @Override
            public ListCell<Phone> call(ListView<Phone> param) {
                ListCell<Phone> listCell=new ListCell<Phone>(){
                    @Override
                    protected void updateItem(Phone item, boolean empty) {
                        
                        super.updateItem(item,empty);   //선택된 항목에서 이벤트가 발생할 수 있게 해주는것
                        if(empty) return;
                        try {
                            HBox hbox=(HBox)FXMLLoader.load(getClass().getResource("item.fxml"));
                            ImageView phoneImage=(ImageView) hbox.lookup("#image");
                            Label phoneName=(Label) hbox.lookup("#name");
                            Label phoneContent=(Label) hbox.lookup("#content");
                            
                            
                            phoneImage.setImage(new Image(getClass().getResource("images/"+item.getImageName()).toString()));
                            phoneName.setText(item.getName());
                            phoneContent.setText(item.getContent());
                            
                            setGraphic(hbox);
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                return listCell;
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                System.out.println(newValue.getName()+":"+newValue.getImageName());
            }
        });
        
        listView.setOnMouseClicked(e->{
           Phone phone= listView.getSelectionModel().getSelectedItem();
            System.out.println(phone.getName());
        });
        
        
        
        ObservableList <Phone> value=FXCollections.observableArrayList();
        value.add(new Phone("phone01.png","갤럭시1","삼성스마트폰의 최초모델입니다."));
        value.add(new Phone("phone02.png","갤럭시2","삼성스마트폰의 두번째모델입니다."));
        value.add(new Phone("phone03.png","갤럭시3","삼성스마트폰의 세번째모델입니다."));
        listView.setItems(value);
    
        
    
    }
}
