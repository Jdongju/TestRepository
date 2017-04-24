package ch17.exam28_Dialogue;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

    public static Stage primaryStage;   //처음 실행되는 스테이지 스태틱으로 선언. 이후 컨트롤러에서 AppMain.primaryStage에 대입

    @Override
    public void start(Stage primaryStage) throws Exception {
        //how1
        AppMain.primaryStage = primaryStage;
        
        //how2  컨트롤러 안에 primaryStage를 넣어준다. 로더객체를 별도로 만들어준다.
//        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
        Parent parent = loader.load();
        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("창제목");

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Close...");
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
