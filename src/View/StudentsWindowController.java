package View;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import Module.ExamDomainModel;
import Module.Student;
import javafx.util.converter.IntegerStringConverter;

public class StudentsWindowController
{
  @FXML private Button backButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;
  @FXML private TextField idTextField;
  @FXML private TextField nameTextField;
  @FXML private TextField coursesTextField;
  @FXML private TableView<Student> tableView;
  @FXML private TableColumn<Student, Integer> idColumn;
  @FXML private TableColumn<Student,String> nameColumn;
  @FXML private TableColumn<Student, String> coursesColumn;

  private Region root;
  private ExamDomainModel model;
  private ViewHandler viewHandler;

  //Initialization method for students window
  public void init(ViewHandler viewHandler, ExamDomainModel model, Region root)
  {
    this.root=root;
    this.model=model;
    this.viewHandler=viewHandler;
    //Sets columns to store type of object in Student
    tableView.getItems().addAll(model.getAllStudents());
    idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
    coursesColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courses"));
    //sets the table to be editable
    tableView.setEditable(true);
    //changes the value if cell is edited
    idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    coursesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
  }

  public void reset()
  {
  }
  //Get root method for ViewHandler
  public Region getRoot()
  {
    return root;
  }
  public void backButtonPressed(){
    if(!model.areIDlegal())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid ID");
      a1.setHeaderText("");
      a1.setContentText("Some students have invalid ID");
      a1.showAndWait();
    }
    if(model.isSameIDAll()){
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Same ID");
      a1.setHeaderText("");
      a1.setContentText("Two or more students have the same ID");
      a1.showAndWait();}
    if(!model.isSameIDAll()&&model.areIDlegal()){
      viewHandler.openView("Main");}
  }
  //Add button method
  public void addButtonPressed()
  {
    try
    {
      int id = Integer.parseInt(idTextField.getText());
      Student newStudent = new Student(id, coursesTextField.getText(),
          nameTextField.getText());
      if (newStudent.isLegalID()&&model.isSameID(newStudent))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Same ID");
        a1.setHeaderText("");
        a1.setContentText("Student already exists");
        a1.showAndWait();

      }
      else if(!newStudent.isLegalID())
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid ID");
        a1.setHeaderText("");
        a1.setContentText("You entered invalid ID");
        a1.showAndWait();
      }
      else if(newStudent.isLegalID()&&!model.isSameID(newStudent))
      {
        model.addStudent(id, coursesTextField.getText(), nameTextField.getText());
        tableView.getItems().add(newStudent);
      }
    }catch(Exception e)
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid ID");
      a1.setHeaderText("");
      a1.setContentText("You entered invalid ID");
      a1.showAndWait();
    }
  }
  //Remove button method
  public void removeButtonPressed()
  {
    Student selectedStudent=tableView.getSelectionModel().getSelectedItem();
    model.removeStudent(selectedStudent);
    tableView.getItems().remove(selectedStudent);
  }

  //Changes the id in model and table simultaneously
  public void changeID(TableColumn.CellEditEvent editedCell)
  {
    Student selectedStudent=tableView.getSelectionModel().getSelectedItem();
    model.setId(selectedStudent,(Integer)editedCell.getNewValue());
    selectedStudent.setId((Integer)editedCell.getNewValue());
  }
  //Changing method for courses
  public void changeCourses(TableColumn.CellEditEvent editedCell)
  {
    Student selectedStudent=tableView.getSelectionModel().getSelectedItem();
    model.setCourses(selectedStudent,editedCell.getNewValue().toString());
    selectedStudent.setCourses(editedCell.getNewValue().toString());
  }
  //Changing method for name
  public void changeName(TableColumn.CellEditEvent editedCell)
  {
    Student selectedStudent=tableView.getSelectionModel().getSelectedItem();
    model.setName(selectedStudent, editedCell.getNewValue().toString());
    selectedStudent.setName(editedCell.getNewValue().toString());
  }
}
