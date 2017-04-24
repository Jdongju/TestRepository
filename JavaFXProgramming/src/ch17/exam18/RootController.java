package ch17.exam18;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

    @FXML private Button btnClose;
    @FXML
    private CheckBox cb1;
    @FXML
    private CheckBox cb2;
    
    @FXML
    private ImageView checkImageView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClose.setOnAction(e->{
            Platform.exit();
        });
        
        cb1.setOnAction(e->{
            checkImageView.setImage(new Image(getClass().getResource("images/geek-glasses-hair.gif").toString()));
        });
        
    }

}
