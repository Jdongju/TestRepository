package ch17.exam38_Task;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RootController implements Initializable {

	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label label;
	@FXML
	private Label lblWorkDone;
	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;
	@FXML
	private Label lblResult;

	private Task<Integer> task;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnStart.setOnAction(e -> handleBtnStart(e));
		btnStop.setOnAction(e -> handleBtnStop(e));
	}

	private void handleBtnStart(ActionEvent e) {
		task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int result = 0;
				for (int i = 0; i <= 100; i++) {
					result += i;
					//how1
//					updateProgress(i, 100);	
//					updateMessage(String.valueOf(i));

					//how2
					double value=i;
					Platform.runLater(()->{		//익명객체안의 필드는 항상 Final이기 때문에 value변수에 i를 받아서 value를 매개변수로 넣어야한다.
						progressBar.setProgress(value/100);
						lblWorkDone.setText(String.valueOf(value));
					});
					
					
					System.out.println(i);
					if (isCancelled()) {
						break;
					}
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						break;
					}
				}
				return result;
			}

			@Override
			protected void succeeded() {
				int result = getValue();//getValue(): Task의 결과값을 리턴해주는 메소드
				lblResult.setText(String.valueOf(result));
			}

			@Override
			protected void cancelled() {
				lblResult.setText("작업취소됨");
			}

			@Override
			protected void failed() {
			}

		};
		//how1
		//progressBar.progressProperty().bind(task.progressProperty());
		//lblWorkDone.textProperty().bind(task.messageProperty());

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	private void handleBtnStop(ActionEvent e) {
		task.cancel();
	}

}
