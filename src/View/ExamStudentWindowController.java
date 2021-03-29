package View;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import Module.ExamDomainModel;
import Module.Exam;
import Module.Student;

import java.util.ArrayList;
import java.util.List;

public class ExamStudentWindowController
{
  @FXML private Button backButton;
  @FXML private Button removeButton;
  @FXML private Button addButton;
  @FXML private TextField idTextField;
  @FXML private TextField nameTextField;
  @FXML private Label dateLabel;
  @FXML private Label courseLabel;
  @FXML private TableView<Student> tableView;
  @FXML private TableColumn<Student, Integer> idColumn;
  @FXML private TableColumn<Student,String> nameColumn;

  private Region root;
  private ExamDomainModel model;
  private ViewHandler viewHandler;
  private Exam selectedExam;
  //Initialization method for exam student window
  public void init(ViewHandler viewHandler, ExamDomainModel model, Region root)
  {
    this.root=root;
    this.model=model;
    this.viewHandler=viewHandler;
    selectedExam=model.getSelectedExam();
    dateLabel.setText(selectedExam.getDate().toString());
    courseLabel.setText(selectedExam.getCourse());
    idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("Id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
    tableView.setEditable(true);
    tableView.getItems().addAll(model.studentsOfExam(selectedExam));
    tableView.getItems().addAll(model.additionalStudentsOfExam(selectedExam));
  }
  //Reset method for exam student window
  public void reset()
  {
    selectedExam=model.getSelectedExam();
    dateLabel.setText(selectedExam.getDate().toString());
    courseLabel.setText(selectedExam.getCourse());
    tableView.getItems().clear();
    tableView.getItems().addAll(model.studentsOfExam(selectedExam));
    tableView.getItems().addAll(model.additionalStudentsOfExam(selectedExam));
  }
  //Back button method
  public void backButtonPressed()
  {try{
    List<Student> students=tableView.getItems();
    ArrayList<Student> returnlist=new ArrayList<Student>(students);
    model.setStudents(selectedExam,returnlist);
    viewHandler.openView("Exams");}
  catch (Exception e){viewHandler.openView("Exams");}

  }
  //Add button method
  public void addButtonPressed()
  {
    List<Student> students=tableView.getItems();
    ArrayList<Student> tableStudents=new ArrayList<Student>(students);
    try
    {
      int id=Integer.parseInt(idTextField.getText());
      Student newStudent= new Student(id,selectedExam.getCourse(),nameTextField.getText());
      if(model.checkSameIDTable(newStudent, tableStudents))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid ID");
        a1.setHeaderText("");
        a1.setContentText("There is already student with the same ID");
        a1.showAndWait();
      }
      if (!newStudent.isLegalID())
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Invalid ID");
        a1.setHeaderText("");
        a1.setContentText("You need to enter valid ID");
        a1.showAndWait();
      }
      if(newStudent.isLegalID()&&!model.checkSameIDTable(newStudent,tableStudents))
      {
        tableView.getItems().add(newStudent);
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
    Student selectedStudent =tableView.getSelectionModel().getSelectedItem();
    tableView.getItems().remove(selectedStudent);
  }
  //Get root method for ViewHandler
  public Region getRoot()
  {
    return root;
  }
}
