package ch17.exam39_Serviece;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
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
	private Label lblWorkDone;
	@FXML
	private Label lblResult;
	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;

	private TimeService timeService;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnStart.setOnAction(e -> handleBtnStart(e));
		btnStop.setOnAction(e -> handleBtnStop(e));
	}

	//JAVA FX APP Thread에서 start실행
	private void handleBtnStart(ActionEvent e) {
		if (timeService == null) {
			timeService = new TimeService();
			timeService.start();
		} else {
			timeService.restart();
		}
	}

	private void handleBtnStop(ActionEvent e) {
		timeService.cancel();
	}

	class TimeService extends Service<Integer> {

		@Override
		//WorkThread가 실행.
		protected Task<Integer> createTask() {
			Task<Integer> task = new Task<Integer>() {
				@Override
				protected Integer call() throws Exception {
					int sum = 0;
					for (int i = 1; i <= 100; i++) {
						sum += i;
						if (isCancelled()) {
							break;
						}
						try {
							Thread.sleep(100);
						} catch (Exception e) {
						}
						//람다식에는 final이 매개변수로 들어가야하니까 value가 final특성 갖게 하고 i는 계속 변하게 한다.
						int value = i;
						Platform.runLater(() -> {
							progressBar.setProgress(value / 100.0);
							lblWorkDone.setText(String.valueOf(value));
						});
					}
					return sum;
				}
			};
			return task;
		}

		@Override
		//JAVA FX ApplicationThread가 succeeded실행.
		//그래서 UI변경 가능
		protected void succeeded() {
			lblResult.setText(String.valueOf(getValue()));
		}

	}

}
