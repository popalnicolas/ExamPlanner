import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import Module.ExamDomainModel;
import Module.ExamDomainModelManager;

import java.io.IOException;

public class MyApplication extends Application
{
  ExamDomainModel model;
  public void start(Stage primaryStage)
  {
    model = new ExamDomainModelManager();
    try
    {
      model.loadData();
    }
    catch(IOException | ClassNotFoundException e)
    {
      System.out.println("Error loading file");
    }
    ViewHandler view= new ViewHandler(model);
    view.start(primaryStage);
  }
  public void stop()
  {
    try
    {
      model.saveData();
    }
    catch (IOException e)
    {
      System.out.println("Error saving file");
    }
  }
}
