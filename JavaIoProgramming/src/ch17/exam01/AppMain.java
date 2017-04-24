package ch17.exam01;

import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {

    public AppMain() {
        System.out.println("Constructor");
    }

    @Override
    public void init() throws Exception {
        System.out.println("Init");
        Parameters params= getParameters();
        List<String> list= params.getRaw();
        for(String param : list){
            System.out.println(param);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName());
        stage.show();   //윈도우 보여주기
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }
    
    

    public static void main(String[] args) {
        launch(args); //Appmain객체생성 및 메인 윈도우 생성, start()호출
    }

}
