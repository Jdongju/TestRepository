package ch17.exam42_Animation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController implements Initializable {

	@FXML
	private BorderPane login;
	@FXML
	private Button btnMain;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnMain.setOnAction((event) -> {
			handlerBtnMain(event);
		});
	}

	private void handlerBtnMain(ActionEvent event) {
		//childeren의 index는 0부터 시작하므로 1번을 제거하고 0으로 되돌아감.
		//how1
//		RootController.rootPane.getChildren().remove(1);

		//how2
		//root는 root.fxml의 Stackpane을 의미.
		StackPane rootPane = (StackPane) btnMain.getScene().getRoot();
		//index번호는 항상 변동가능하기 때문에 인덱스 번호대신 fx:id인 login를 사용해야한다.
//		rootPane.getChildren().remove(login);

		login.setOpacity(1);

		KeyValue keyValue = new KeyValue(login.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(
						Duration.millis(500),
						e -> rootPane.getChildren().remove(login),
						keyValue
		);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}

}
