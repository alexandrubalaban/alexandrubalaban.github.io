package controller;

import model.Model;

/**
 * Created by Ionut on 24.04.2015.
 */
public interface Controller {
    void startApp();

    void setFirstThreadView(Model model);
    void setSecondThreadView(Model model);
    void setThirdThreadView(Model model);
    Model getTheModel();

}
