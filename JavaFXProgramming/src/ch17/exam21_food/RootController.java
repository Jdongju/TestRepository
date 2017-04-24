package ch17.exam21_food;

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
    private ListView<Food> listView;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                ListCell<Food> listCell = new ListCell<Food>() {
                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (empty) {
                            return;
                        }
                        try {
                            HBox hBox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            ImageView foodImage = (ImageView) hBox.lookup("#image");
                            Label foodName = (Label) hBox.lookup("#name");
                            ImageView foodScore = (ImageView) hBox.lookup("#score");
                            Label foodContent = (Label) hBox.lookup("#content");

                            foodImage.setImage(new Image(getClass().getResource("images/" + item.getImage()).toString()));
                            foodName.setText(item.getName());
                            foodScore.setImage(new Image(getClass().getResource("images/star" + item.getScore()+".png").toString()));
                            foodContent.setText(item.getDescription());
                            setGraphic(hBox);

                        } catch (IOException ex) {
                        }

                    }
                };
                return listCell;
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Food>() {
            @Override
            public void changed(ObservableValue<? extends Food> observable, Food oldValue, Food newValue) {
                System.out.println(newValue.getName());
            }
        });

        ObservableList<Food> value = FXCollections.observableArrayList();
        value.add(new Food("food01.png", "삼겹살", 1, "정말 맛있어요"));
        listView.setItems(value);
    }

}
