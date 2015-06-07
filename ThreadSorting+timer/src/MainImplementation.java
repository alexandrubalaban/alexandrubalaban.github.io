import controller.Controller;
import controller.ControllerImplementation;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelImplementation;
import view.View;
import view.ViewImplementation;

/**
 * Created by Ionut on 24.04.2015.
 */
public class MainImplementation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view= new ViewImplementation(primaryStage);
        Model model = new ModelImplementation();

        Controller controller = new ControllerImplementation(view,model);
        controller.startApp();

    }
}
