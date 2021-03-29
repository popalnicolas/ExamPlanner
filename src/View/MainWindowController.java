package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Region;
import Module.ExamDomainModel;

import java.io.FileNotFoundException;

public class MainWindowController
{
  @FXML private Button manageStudentsButton;
  @FXML private Button manageRoomsButton;
  @FXML private Button manageExamsButton;
  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private Button confirmDateButton;

  private Region root;
  private ExamDomainModel model;
  private ViewHandler viewHandler;
  //Initialization method for main window
  public void init(ViewHandler viewHandler,ExamDomainModel model, Region root)
  {
    this.root=root;
    this.model=model;
    this.viewHandler=viewHandler;
    endDate.setValue(model.getEndDate());
    startDate.setValue(model.getStartDate());
  }

  public void reset()
  {
  }
  //Get root method for ViewHandler
  public Region getRoot()
  {
    return root;
  }
  //ManageStudents button method
  public void manageStudentsButtonPressed()
  {if(model.checkIfNotBlank(startDate.getValue(),endDate.getValue()))
  {
    Alert a1=new Alert(Alert.AlertType.ERROR);
    a1.setTitle("Blank Date");
    a1.setHeaderText("");
    a1.setContentText("You need to enter dates");
    a1.showAndWait();
  }
  else {viewHandler.openView("Students");}}
  //Manage rooms button method
  public void manageRoomsButtonPressed()
  {if(model.checkIfNotBlank(startDate.getValue(),endDate.getValue()))
  {
    Alert a1=new Alert(Alert.AlertType.ERROR);
    a1.setTitle("Blank Date");
    a1.setHeaderText("");
    a1.setContentText("You need to enter dates");
    a1.showAndWait();
  }
  else{viewHandler.openView("Rooms");}}
  //Manage exams button method
  public void manageExamsButtonPressed()
  {if(model.checkIfNotBlank(startDate.getValue(),endDate.getValue()))
  {
    Alert a1=new Alert(Alert.AlertType.ERROR);
    a1.setTitle("Blank Date");
    a1.setHeaderText("");
    a1.setContentText("You need to enter dates");
    a1.showAndWait();
  }
  else {viewHandler.openView("Exams");}}
  //Confirm button method
  public void confirmDateButtonPressed()
  {
    if(endDate.getValue().isBefore(startDate.getValue()))
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid Dates");
      a1.setHeaderText("");
      a1.setContentText("End date is before start date");
      a1.showAndWait();
    }
    else
    {
      model.setStartDate(startDate.getValue());
      model.setEndDate(endDate.getValue());
      System.out.println(startDate.getValue());
      System.out.println(endDate.getValue());
    }
  }
  //Export to xml button method
  public void exportToXMLButtonPressed()
  {
    try
    {
      if(model.exportToXML())
      {
        Alert a1=new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("Success");
        a1.setHeaderText("");
        a1.setContentText("Exams were successfully converted to XML file.");
        a1.showAndWait();
      }
      else
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Exams Empty");
        a1.setHeaderText("");
        a1.setContentText("Exams are empty. There must be at least 1 exam created.");
        a1.showAndWait();
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Creating xml error");
    }
  }
}
