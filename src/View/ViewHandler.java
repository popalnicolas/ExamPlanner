package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Module.ExamDomainModel;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ExamDomainModel model;
  private ExamWindowController examWindowController;
  private ExamStudentWindowController examStudentWindowController;
  private MainWindowController mainWindowController;
  private StudentsWindowController studentsWindowController;
  private RoomsWindowController roomsWindowController;

  public ViewHandler(ExamDomainModel model)
  {
    this.model=model;
    this.currentScene=new Scene(new Region());
    currentScene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm()); //CSS
  }
  public void start(Stage primaryStage)
  {
    this.primaryStage=primaryStage;
    openView("Main");
  }
  public void openView(String id)
  {
    Region root=null;
    switch(id)
    {
      case "Main":
        root= loadMain("MainWindow.fxml");break;
      case "Students":
        root= loadStudents("StudentsWindow.fxml"); break;
      case "Rooms":
        root= loadRooms("RoomsWindow.fxml"); break;
      case "Exams":
        root= loadExams("ExamsWindow.fxml");break;
      case "ExamStudents":
        root= loadExamStudents("ExamsStudentWindow.fxml");break;
    }
    currentScene.setRoot(root);
    String title="Exam Planner";
    if(root.getUserData()!=null)
    {
      title+=root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }
  private Region loadStudents(String fxmlFile)
  {
    if(studentsWindowController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        studentsWindowController = loader.getController();
        studentsWindowController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      studentsWindowController.reset();
    }
    return studentsWindowController.getRoot();
  }
  private Region loadMain(String fxmlFile)
  {
    if(mainWindowController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        mainWindowController = loader.getController();
        mainWindowController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      mainWindowController.reset();
    }
    return mainWindowController.getRoot();
  }
  private Region loadRooms(String fxmlFile)
  {
    if(roomsWindowController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        roomsWindowController = loader.getController();
        roomsWindowController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      roomsWindowController.reset();
    }
    return roomsWindowController.getRoot();
  }
  private Region loadExams(String fxmlFile)
  {
    if(examWindowController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        examWindowController = loader.getController();
        examWindowController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      examWindowController.reset();
    }
    return examWindowController.getRoot();
  }
  private Region loadExamStudents(String fxmlFile)
  {
    if(examStudentWindowController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        examStudentWindowController = loader.getController();
        examStudentWindowController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      examStudentWindowController.reset();
    }
    return examStudentWindowController.getRoot();
  }
}
