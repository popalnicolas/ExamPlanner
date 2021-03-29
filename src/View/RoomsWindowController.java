package View;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import Module.ExamDomainModel;
import Module.Room;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class RoomsWindowController
{
  @FXML private Button backButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;
  @FXML private TextField nameTextField;
  @FXML private TextField capacityTextField;
  @FXML private ChoiceBox equipmentTextField;
  @FXML private TableView<Room> tableView;
  @FXML private TableColumn<Room, String> nameColumn;
  @FXML private TableColumn<Room, Integer> capacityColumn;
  @FXML private TableColumn<Room, Boolean> equipmentColumn;

  private Region root;
  private ExamDomainModel model;
  private ViewHandler viewHandler;

  //Initialization method for Room window
  public void init(ViewHandler viewHandler, ExamDomainModel model, Region root)
  {
    this.root=root;
    this.model=model;
    this.viewHandler=viewHandler;
    tableView.getItems().addAll(model.getAllRooms());
    //setting values for columns
    nameColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
    capacityColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<Room, Boolean>("equipment"));
    //Setting table view to be editable
    tableView.setEditable(true);
    //Setting columns to be editable
    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    capacityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    equipmentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

    //Options in equipment choicebox
    equipmentTextField.getItems().add("true");
    equipmentTextField.getItems().add("false");
  }

  public void reset()
  {
  }
  //Get root method for ViewHandler
  public Region getRoot()
  {
    return root;
  }
  //Back button method
  public void backButtonPressed(){
    if(model.isSameNameAll())
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Same name");
      a1.setHeaderText("");
      a1.setContentText("Two or more rooms have the same name");
      a1.showAndWait();}
    else{viewHandler.openView("Main");}
  }
  //Add Room method
  public void addRoomButtonPressed()
  {
    try
    {
      int cap = Integer.parseInt(capacityTextField.getText());
      boolean equip =Boolean.parseBoolean(equipmentTextField.getValue().toString());
      Room newRoom = new Room(nameTextField.getText(),cap,equip);
      if(model.isSameName(newRoom))
      {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Same name");
        a1.setHeaderText("");
        a1.setContentText("Room already exists");
        a1.showAndWait();
      }
      else
      {
        tableView.getItems().add(newRoom);
        model.addRoom(nameTextField.getText(),cap,equip);
      }

    }catch(Exception e)
    {
      Alert a1=new Alert(Alert.AlertType.ERROR);
      a1.setTitle("Invalid input");
      a1.setHeaderText("");
      a1.setContentText("You entered invalid input");
      a1.showAndWait();
    }
  }
  //Remove Room method
  public void removeButtonPressed()
  {
    Room selectedRoom=tableView.getSelectionModel().getSelectedItem();
    model.removeRoom(selectedRoom);
    tableView.getItems().remove(selectedRoom);
  }
  //Changing name method
  public void changeName(TableColumn.CellEditEvent editedCell)
  {
    Room selectedRoom=tableView.getSelectionModel().getSelectedItem();
    model.setName(selectedRoom,editedCell.getNewValue().toString());
    selectedRoom.setName(editedCell.getNewValue().toString());
  }
  //Changing capacity method
  public void changeCapacity(TableColumn.CellEditEvent editedCell)
  {
    Room selectedRoom=tableView.getSelectionModel().getSelectedItem();
    model.setCapacity(selectedRoom,(Integer)editedCell.getNewValue());
    selectedRoom.setCapacity((Integer)editedCell.getNewValue());
  }
  //Changing equipment method
  public void changeEquipment(TableColumn.CellEditEvent editedCell)
  {
    Room selectedRoom=tableView.getSelectionModel().getSelectedItem();
    model.setEquipment(selectedRoom,(Boolean)editedCell.getNewValue());
    selectedRoom.setEquipment((Boolean)editedCell.getNewValue());
  }

}
