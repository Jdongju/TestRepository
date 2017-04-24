package ch17.exam41_SceneMove2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
