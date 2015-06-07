import controller.MazeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MazeModel;
import model.MazeModelClass;
import view.MazeView;
import view.MazeViewClass;

/**
 * Created by Ionut on 20.04.2015.
 */
public class MainClass extends Application{
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

            MazeView view = new MazeViewClass();
            MazeModel mazeModel = new MazeModelClass(primaryStage);
            MazeController controller = new MazeController(view,mazeModel);
            controller.show();

    }
}

