package ch17.exam42_Animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootController implements Initializable {

	@FXML
	private Button btnLogin;
	@FXML
	private StackPane stackPane;
	
	//다른 클래스에서 접근 가능하도록 하기 위해 rootPane은 public static으로 선언
	public static StackPane rootPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//rootPane에 stackPane을 대입해서 사용
		rootPane=stackPane;
		btnLogin.setOnAction(e -> handlerBtnLogin(e));
	}

	private void handlerBtnLogin(ActionEvent e) {
		try {
			//root의 stackPane에 login을 올려놓는다.
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			stackPane.getChildren().add(parent);
			
			parent.setTranslateX(350);	//초기값 : 350
			
			KeyValue keyValue= new KeyValue(parent.translateXProperty(),0); //무엇을: translateX, 종료값:0
			KeyFrame keyFrame=new KeyFrame(Duration.millis(100), keyValue); //애니메이션 진행 시간: 0.1초
			Timeline timeline= new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
