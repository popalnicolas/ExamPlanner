package View;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import Module.ExamDomainModel;
import Module.Exam;
import Module.Room;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExamWindowController
{

  @FXML private Button backButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;
  @FXML private Button manageStudentButton;
  @FXML private DatePicker datePicker;
  @FXML private ChoiceBox semesterTextField;
  @FXML private ChoiceBox<String> formTextField;
  @FXML private ChoiceBox<String> typeTextField;
  @FXML private TextField courseTextField;
  @FXML private ChoiceBox<Room> roomChoiceBox;
  @FXML private TableView<Exam> tableView;
  @FXML private TableColumn<Exam, LocalDate> dateColumn;
  @FXML private TableColumn<Exam,Integer> semesterColumn;
  @FXML private TableColumn<Exam, String> formColumn;
  @FXML private TableColumn<Exam, String> typeColumn;
  @FXML private TableColumn<Exam, String> courseColumn;
  @FXML private TableColumn<Exam, Room> roomColumn;



  private Region root;
  private ExamDomainModel model;
  private ViewHandler viewHandler;

  //Initialization method for Exam Window
  public void init(ViewHandler viewHandler, ExamDomainModel model, Region root)
  {
    this.root=root;
    this.model=model;
    this.viewHandler=viewHandler;
    //Adding options for semester ChoiceBox
    semesterTextField.getItems().add(1);
    semesterTextField.getItems().add(2);
    semesterTextField.getItems().add(3);
    semesterTextField.getItems().add(4);
    semesterTextField.getItems().add(5);
    semesterTextField.getItems().add(6);
    semesterTextField.getItems().add(7);
    //Adding options for type ChoiceBox
    typeTextField.getItems().add("Oral");
    typeTextField.getItems().add("Written");
    //Adding options for form ChoiceBox
    formTextField.getItems().add("Ordinary");
    formTextField.getItems().add("Re-exam");

    tableView.getItems().addAll(model.getExams());
    semesterColumn.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("semester"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Exam, LocalDate>("date"));
    courseColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("course"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("type"));
    formColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("form"));
    roomColumn.setCellValueFactory(new PropertyValueFactory<Exam, Room>("room"));
    tableView.setEditable(true);
    roomChoiceBox.getItems().addAll(model.getAllRooms());
    dateColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
    typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    formColumn.setCellFactory(TextFieldTableCell.forTableColumn());

  }
  //Reset method for Exam Window
  public void reset()
  {
    roomChoiceBox.getItems().clear();
    roomChoiceBox.getItems().addAll(model.getAllRooms());
  }
  //Get root method for ViewHandler
  public Region getRoot()
  {
    return root;
  }
  //Back button method
  public void backButtonPressed(){
    if(model.isConsecutiveDays())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Consecutive days");
      a1.setHeaderText("");
      a1.setContentText("Some of students have consecutive days exam");
      a1.showAndWait();
    }

    if(!model.areDatesLegal())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid dates");
      a1.setHeaderText("");
      a1.setContentText("Some of the exams have invalid dates");
      a1.showAndWait();
    }

    if(!model.isLegalType())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid type");
      a1.setHeaderText("");
      a1.setContentText("Some of the exams have invalid type");
      a1.showAndWait();
    }
    if(!model.isLegalForm())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid form");
      a1.setHeaderText("");
      a1.setContentText("Some of the exams have invalid form");
      a1.showAndWait();
    }
    if(!model.checkDateRoomTypeAll())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid type");
      a1.setHeaderText("");
      a1.setContentText("Room is already reserved for another exam");
      a1.showAndWait();
    }
    if(!model.checkWrittenOralAll())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid type");
      a1.setHeaderText("");
      a1.setContentText("Written exams must be before oral");
      a1.showAndWait();
    }

    if(model.areDatesLegal()&&!model.isConsecutiveDays()&&model.isLegalType()&&model.isLegalForm()&&model.checkDateRoomTypeAll()&&model.checkWrittenOralAll()){
      System.out.println(model.allExams());
      viewHandler.openView("Main"); }
  }
  //Manage Students button method
  public void manageStudentButtonPressed()
  {try{
    model.setSelectedExam(tableView.getSelectionModel().getSelectedItem());
    viewHandler.openView("ExamStudents");}
  catch (Exception e){Alert a1=new Alert(Alert.AlertType.ERROR);
    a1.setTitle("Invalid selection");
    a1.setHeaderText("");
    a1.setContentText("You did not select an exam");
    a1.showAndWait();}
  }
  //Add button method
  public void addButtonPressed()
  {
    try{
      int sem=Integer.parseInt(semesterTextField.getValue().toString());
      System.out.println(roomChoiceBox.getValue());
      Exam newExam = new Exam(datePicker.getValue(),sem,(String)formTextField.getValue(),(String)typeTextField.getValue(),(String)courseTextField.getText(),roomChoiceBox.getValue());
      if(!model.isSemesterLegal(sem)){ Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid semester");
        a1.setHeaderText("");
        a1.setContentText("You entered invalid semester");
        a1.showAndWait();}

      if(!model.checkEquipment(roomChoiceBox.getValue(),typeTextField.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid type to room");
        a1.setHeaderText("");
        a1.setContentText("Room does not have equipment");
        a1.showAndWait();
      }
      if(!model.isDateLegal(datePicker.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid date");
        a1.setHeaderText("");
        a1.setContentText("You picked date which is not in exam period");
        a1.showAndWait();
      }
      if(!model.checkDateSeventhSemester(sem,datePicker.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid date");
        a1.setHeaderText("");
        a1.setContentText("Exam for seventh semester cannot be 3 days before end of exam period");
        a1.showAndWait();
      }
      if(!model.checkCapacity(roomChoiceBox.getValue(),typeTextField.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid type to room");
        a1.setHeaderText("");
        a1.setContentText("Room does not have the capacity for written exam");
        a1.showAndWait();
      }
      if(!model.legalSameDateRoomOralWritten(datePicker.getValue(),roomChoiceBox.getValue(),typeTextField.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid room");
        a1.setHeaderText("");
        a1.setContentText("Room is already taken for another exam");
        a1.showAndWait();
      }
      if(!model.legalDateType(datePicker.getValue(),typeTextField.getValue()))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid type");
        a1.setHeaderText("");
        a1.setContentText("Written exams must occur before oral exams");
        a1.showAndWait();
      }
      if(model.isSemesterLegal(sem)&&model.checkEquipment(roomChoiceBox.getValue(),typeTextField.getValue())&&model.isDateLegal(datePicker.getValue())&&model.checkDateSeventhSemester(sem,datePicker.getValue())&&model.checkCapacity(roomChoiceBox.getValue(),typeTextField.getValue())&&model.legalSameDateRoomOralWritten(datePicker.getValue(),roomChoiceBox.getValue(),typeTextField.getValue())&&model.legalDateType(datePicker.getValue(),typeTextField.getValue()))
      {
        model.addExam(datePicker.getValue(),sem,formTextField.getValue(),typeTextField.getValue(),courseTextField.getText(),roomChoiceBox.getValue());
        tableView.getItems().add(newExam);
      }
    }
    catch (Exception e)
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid Input");
      a1.setHeaderText("");
      a1.setContentText("You entered invalid input");
      a1.showAndWait();
    }
  }
  //Remove button method
  public void removeButtonPressed()
  {
    Exam selectedExam=tableView.getSelectionModel().getSelectedItem();
    if(model.checkDate(selectedExam)){
      model.removeExam(selectedExam);
      tableView.getItems().remove(selectedExam);
    }
    else    {Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid Date");
      a1.setHeaderText("");
      a1.setContentText("Cannot remove exam one day before");
      a1.showAndWait();}


  }
  //Changing date method
  public void changeDate(TableColumn.CellEditEvent editedCell)
  {
    Exam selectedExam=tableView.getSelectionModel().getSelectedItem();
    model.setDate(selectedExam,(LocalDate) editedCell.getNewValue());
    selectedExam.setDate((LocalDate) editedCell.getNewValue());
  }
  //Changing type method
  public void changeType(TableColumn.CellEditEvent editedCell)
  {
    Exam selectedExam=tableView.getSelectionModel().getSelectedItem();
    model.setType(selectedExam,editedCell.getNewValue().toString());
    selectedExam.setType(editedCell.getNewValue().toString());
  }
  //Changing form method
  public void changeForm(TableColumn.CellEditEvent editedCell)
  {
    Exam selectedExam=tableView.getSelectionModel().getSelectedItem();
    model.setForm(selectedExam,editedCell.getNewValue().toString());
    selectedExam.setForm(editedCell.getNewValue().toString());
  }
}
