package ch17.exam28_Dialogue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

    //
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleOpenFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AppMain.primaryStage);
        System.out.println(file.getPath());
    }

    @FXML
    private void hadleSaveFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

//        Button button= (Button) event.getSource();
//        Scene scene= button.getScene();
//        Stage stage=(Stage) scene.getWindow();
//        
//        File file = fileChooser.showSaveDialog(stage);
        //how2
        File file = fileChooser.showSaveDialog(primaryStage);
        System.out.println(file.getPath());
    }

    @FXML
    private void hadleDirectoryFileChooser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        //how3
        File file = directoryChooser.showDialog(((Button) event.getSource()).getScene().getWindow());
        System.out.println(file.getPath());
    }

    @FXML
    private void hadlePopUpChooser(ActionEvent event) throws IOException, Exception {
        ShowNotification("알림", "메시지가 도착했습니다.");
        ShowNotification("경고", "도둑이 침입했습니다.");
    }

    private void ShowNotification(String type, String message) throws Exception {
        Popup popup = new Popup();
        HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("popup.fxml"));
        ImageView imgMessage = (ImageView) hbox.lookup("#imgMessage");
        Label lbMessage = (Label) hbox.lookup("#lbMessage");
        if (type.equals("알림")) {
            imgMessage.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));
        } else if (type.equals("경고")) {
            imgMessage.setImage(new Image(getClass().getResource("images/dialog-warning.png").toString()));
        }
        lbMessage.setText(message);

        popup.getContent().add(hbox);
        popup.show(AppMain.primaryStage);
        popup.setAutoHide(true);
    }

    @FXML
    private void hadleCustomChooser(ActionEvent event) throws IOException {
//        ShowCustomDialog("help", "확인하셨습니까?");
            ShowCustomDialog("error", "네트워크 연결이 되지 않습니다.");
    }

    private void ShowCustomDialog(String type, String message) throws IOException {
        Stage dialogue = new Stage(StageStyle.UTILITY);
        Parent parent = FXMLLoader.load(getClass().getResource("custom-dialogue.fxml"));
        
        ImageView icon= (ImageView) parent.lookup("#icon");
        Label lbMessage=(Label) parent.lookup("#message");
        Button btnOk=(Button) parent.lookup("#btnOk");
        
        if(type.equals("error")){
            icon.setImage(new Image(getClass().getResource("images/dialog-error.png").toString()));
        }else if(type.equals("help")){
            icon.setImage(new Image(getClass().getResource("images/dialog-help.png").toString()));
        }else if(type.equals("info")){
            icon.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));
        }else if(type.equals("warning")){
            icon.setImage(new Image(getClass().getResource("images/dialog-warning.png").toString()));
        }
        
        lbMessage.setText(message);
        
        btnOk.setOnAction(e->{
            dialogue.hide();
        });
        
        Scene scene = new Scene(parent);
        dialogue.setScene(scene);
        dialogue.initOwner(AppMain.primaryStage);
        dialogue.initModality(Modality.WINDOW_MODAL);
        dialogue.show();
    }
}
