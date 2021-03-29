package Module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ExamDomainModelManager implements ExamDomainModel
{
  ArrayList<Student> students;
  ArrayList<Room> rooms;
  ArrayList<Exam> exams;
  LocalDate startDate;
  LocalDate endDate;
  Exam selectedExam;

  public ExamDomainModelManager()
  {
    students=new ArrayList<Student>();
    rooms=new ArrayList<Room>();
    exams=new ArrayList<Exam>();
  }
  //Get method for last day of exam period
  public LocalDate getEndDate() { return endDate; }
  //Get method for first day of exam period
  public LocalDate getStartDate() { return startDate; }
  //Get method for ArrayList of all exams
  public ArrayList<Exam> getExams() { return exams; }

  ///////////////////////////////////////////////////////////////////////////////////
  // Student starts
  @Override public boolean areIDlegal()
  {boolean isIt=true;
    if(students.size()==0){return true;}
    for (int i = 0; i < students.size(); i++)
    {
      if (!students.get(i).isLegalID()){isIt=false; break;}
    }
    return isIt;
  }
  //Adds student to ArrayList in model
  @Override public void addStudent(int id, String course, String name)
  {
    students.add(new Student(id, course, name));
  }
  //Get method for ArrayList of all students
  @Override public ArrayList<Student> getAllStudents(){return students;}
  //Method to check if there are students with same ID
  @Override public boolean isSameID(Student student)
  {
    boolean a=false;
    if(students.size()==0){return false;}
    for (int i = 0; i <students.size() ; i++)
    {
      if(student.equals(students.get(i))){a=true;break;}
      else a=false;
    }
    return a;
  }
  //Checks if there are two same id in ArrayList
  @Override public boolean isSameIDAll()
  {
    boolean isIt=false;
    for (int i = 0; i < students.size()-1; i++)
    {
      for (int j = i+1; j < students.size(); j++)
      {
        if(students.get(i).equals(students.get(j)))
        {
          isIt= true;
          break;
        }
      }
    }
    return isIt;
  }
  //Sets id of student in table, when edited
  @Override public void setId(Student student,int id)
  {
    for (int i = 0; i < students.size(); i++)
    {
      if(student.absoluteEquals(students.get(i))){students.get(i).setId(id); break;}
    }
  }
  //Method to remove student from ArrayList of all students
  @Override public void removeStudent(Student student)
  {
    students.remove(student);
  }

  //Sets name when student in table edited
  @Override public void setName(Student student, String name)
  {
    for (int i = 0; i < students.size(); i++)
    {
      if(student.absoluteEquals(students.get(i))){students.get(i).setName(name); break;}
    }
  }

  //Returns courses of student
  public ArrayList<String> studentCourses(Student student)
  {
    String[] courses= student.getCourses().split(",");
    ArrayList list= new ArrayList(Arrays.asList(courses));
    return list;
  }

  //Sets courses when student in table edited
  @Override public void setCourses(Student student, String courses)
  {
    for (int i = 0; i < students.size(); i++)
    {
      if(student.absoluteEquals(students.get(i))){students.get(i).setCourses(courses); break;}
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  // Room starts
  //Method that checks if there are rooms with same name in ArrayList of all Rooms
  @Override public boolean isSameNameAll()
  {boolean isIt=false;
    for (int i = 0; i < rooms.size()-1; i++)
    {
      for (int j = i+1; j < rooms.size(); j++)
      {
        if(rooms.get(i).equals(rooms.get(j))){isIt=true; break;}
      }
    }
    return isIt;
  }
  //Method to set capacity of room
  @Override public void setCapacity(Room room,int capacity)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if(room.absoluteEquals(rooms.get(i))){rooms.get(i).setCapacity(capacity); break;}
    }
  }
  //Method that sets equipment of room
  @Override public void setEquipment(Room room,boolean equipment)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if(room.absoluteEquals(rooms.get(i))){rooms.get(i).setEquipment(equipment); break;}
    }
  }
  //Method that sets name of room
  @Override public void setName(Room room,String name)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if(room.absoluteEquals(rooms.get(i))){rooms.get(i).setName(name); break;}
    }
  }
  //Method that removes room from ArrayList of rooms
  @Override public void removeRoom(Room room)
  {
    rooms.remove(room);
  }
  //Method that checks if room has same name as room in ArrayList of all rooms
  @Override public boolean isSameName(Room room)
  {
    boolean a=false;
    if(rooms.size()==0){return false;}
    for (int i = 0; i <rooms.size() ; i++)
    {
      if(room.equals(rooms.get(i))){a=true;break;}
      else a=false;
    }
    return a;
  }
  //Get method for ArrayList of all rooms
  @Override public ArrayList<Room> getAllRooms()
  {
    return rooms;
  }
  //Adding method for room
  @Override public void addRoom(String name, int capacity, boolean equipment)
  {
    rooms.add(new Room(name,capacity,equipment));
  }

  /////////////////////////////////////////////////////////////////
  //Exam Starts
  //Checks if exam is not one day before today date
  @Override public boolean checkDate(Exam exam)
  {
    LocalDate todaysDate=LocalDate.now();
    boolean isIt =true;
    if(exam.getDate().equals(todaysDate.plusDays(1))){isIt=false;}
    return isIt;
  }
  //Checks if the exams are continuous
  @Override public boolean isExamNextToEachOther(Exam exam, Exam exam2)
  {
    if(exam.getDate().minusDays(1).equals(exam2.getDate())||exam.getDate().plusDays(1).equals(exam2.getDate())||exam.getDate().equals(exam2.getDate())){return true;}
    else return false;
  }
  //Checks for continuous exams for students
  @Override public boolean isConsecutiveDays()
  {
    boolean isIt=false;
    if(exams.size()==0){return false;}
    for (int i = 0; i < exams.size()-1; i++)
    {
      for (int j = i+1; j < exams.size(); j++)
      {
        if(isExamNextToEachOther(exams.get(i),exams.get(j)))
        {
          ArrayList<Student> allstudents=new ArrayList<Student>();
          allstudents.addAll(exams.get(i).getCourse_student());
          allstudents.addAll(exams.get(j).getCourse_student());
          for (int k = 0; k < allstudents.size()-1; k++)
          {
            for (int l = k+1; l < allstudents.size(); l++)
            {
              if(allstudents.get(k).equals(allstudents.get(l)))
              {
                isIt=true;
                break;
              }
            }
          }
        }
      }
    }
    return isIt;
  }
  //Checks if Date and Type are legal
  @Override public boolean legalDateType(LocalDate date, String type)
  {boolean isIt=true;
    if(exams.size()==0){return true;}
    for (int i = 0; i < exams.size(); i++)
    {
      if(date.isBefore(exams.get(i).getDate())&&type.equals("Oral")&&exams.get(i).getType().equals("Written")){isIt=false; break;}
      if(date.isAfter(exams.get(i).getDate())&&type.equals("Written")&&exams.get(i).getType().equals("Oral")){isIt=false; break;}
    }
    return isIt;
  }
  //Checks the type of exams in one day
  @Override public boolean legalSameDateRoomOralWritten(LocalDate date, Room room, String type)
  {boolean isIt=true;
    if(exams.size()==0){return true;}
    for (int i = 0; i <exams.size() ; i++)
    {
      if(date.equals(exams.get(i).getDate())&&room==exams.get(i).getRoom()&&type.equals("Oral")&&exams.get(i).getType().equals("Written")){isIt=false;break;}
      if(date.equals(exams.get(i).getDate())&&room==exams.get(i).getRoom()&&type.equals("Written")&&exams.get(i).getType().equals("Oral")){isIt=false;break;}
      if(date.equals(exams.get(i).getDate())&&room==exams.get(i).getRoom()&&type.equals("Oral")&&exams.get(i).getType().equals("Oral")){isIt=false;break;}
    }
    return isIt;
  }
  //Checks if rooms are not already occupied
  @Override public boolean checkDateRoomTypeAll()
  {
    boolean isIt=true;
    if(exams.size()==0){return true;}
    for (int i = 0; i < exams.size()-1; i++)
    {
      for (int j = i+1; j < exams.size(); j++)
      {
        if(exams.get(i).getDate().equals(exams.get(j).getDate())&&exams.get(i).getRoom()==exams.get(j).getRoom()&&exams.get(i).getType().equals("Oral")&&exams.get(j).getType().equals("Written")){isIt=false;}
        if(exams.get(i).getDate().equals(exams.get(j).getDate())&&exams.get(i).getRoom()==exams.get(j).getRoom()&&exams.get(i).getType().equals("Written")&&exams.get(j).getType().equals("Oral")){isIt=false;}
        if(exams.get(i).getDate().equals(exams.get(j).getDate())&&exams.get(i).getRoom()==exams.get(j).getRoom()&&exams.get(i).getType().equals("Oral")&&exams.get(j).getType().equals("Oral")){isIt=false;}
      }
    }
    return isIt;
  }
  public boolean checkWrittenOralAll()
  {boolean isIt=true;
    if(exams.size()==0){return true;}
    for (int i = 0; i < exams.size()-1; i++)
    {
      for (int j = i+1; j < exams.size(); j++)
      {
        if(exams.get(i).getType().equals("Oral")&&exams.get(j).getType().equals("Written")&&exams.get(i).getDate().isBefore(exams.get(j).getDate())){isIt=false;}
        if(exams.get(i).getType().equals("Written")&&exams.get(j).getType().equals("Oral")&&exams.get(i).getDate().isAfter(exams.get(j).getDate())){isIt=false;}
      }
    }
    return isIt;
  }
  //Removes exam from ArrayList of all exams
  @Override public void removeExam(Exam exam)
  {
    exams.remove(exam);
  }
  //Adds exam into ArrayList of all exams
  @Override public void addExam(LocalDate date, int semester, String form,
      String type, String course, Room room)
  {
    exams.add(new Exam(date,semester,form,type,course,room));
  }
  //Checks if value of semester is legal
  @Override public boolean isSemesterLegal(int semester)
  {
    if(semester>7||semester<1){return false;}
    else return true;
  }
  //Checks if room has equipment for certain type of exams
  @Override public boolean checkEquipment(Room room, String type)
  {boolean isIt=false;
    if(type.equals("Written")&&room.getEquipment()){isIt= true;}
    if(type.equals("Written")&&!room.getEquipment()){isIt= true;}
    if(type.equals("Oral")&&room.getEquipment()){isIt= true;}
    if(type.equals("Oral")&&!room.getEquipment()){isIt= false;}
    return isIt;
  }
  //Checks if students of seventh semester do not have exams three days before end of exam period
  @Override public boolean checkDateSeventhSemester(int semester, LocalDate date)
  {
    if(semester==7&&date.isAfter(endDate.minusDays(3))){return false;}
    else return true;
  }
  //Set method for selected Exam
  @Override public void setSelectedExam(Exam exam){selectedExam=exam;}
  //Get method for selected Exam
  @Override public Exam getSelectedExam(){return selectedExam;}
  //Checks if there is enough capacity for written exam
  @Override public boolean checkCapacity(Room room, String type)
  {
    if(type.equals("Written")&&room.getCapacity()<40){return false;}
    else return true;
  }
  //Returns arrayList of students automatically assigned to exam by course
  @Override public ArrayList<Student> studentsOfExam(Exam exam)
  {
    ArrayList<Student> returnStudents= new ArrayList<Student>();
    for (int i = 0; i < students.size(); i++)
    {
      ArrayList<String> courses=studentCourses(students.get(i));
      for (int j = 0; j < courses.size(); j++)
      {
        if(courses.get(j).equals(exam.getCourse()))
        {
          returnStudents.add(students.get(i));
        }
      }
    }
    return returnStudents;
  }
  //Get method of ArrayList of all exams
  public ArrayList<Exam> allExams(){return exams;}
  //Set method for students in exam
  public void setStudents(Exam exam, ArrayList<Student> students)
  {
    for (int i = 0; i < exams.size(); i++)
    {
      if(exam.equals(exams.get(i)))
      {
        exams.get(i).setCourse_student(students);
      }
    }
  }
  //Checks if students have same id in table of students in exam
  public boolean checkSameIDTable(Student student, ArrayList<Student> students)
  {boolean isIt=false;
    if(students.size()==0){return false;}
    for (int i = 0; i < students.size(); i++)
    {
      if (student.getId()==(students.get(i).getId()))
      {
        isIt=true;
      }
    }
    return isIt;
  }
  //Save method for data in exam
  public void saveData() throws IOException
  {
    File file = new File("data.bin");
    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream out = new ObjectOutputStream(fos);

    ArrayList<Object> data = new ArrayList<>();
    data.add(startDate); //0
    data.add(endDate); //1
    data.add(students); //2
    data.add(rooms); //3
    data.add(exams); //4

    out.writeObject(data);
  }
  //Load method for data in exam
  public void loadData() throws IOException, ClassNotFoundException
  {
    File file = new File("data.bin");

    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fis);

    ArrayList<Object> data = (ArrayList<Object>) in.readObject();

    startDate = (LocalDate) data.get(0);
    endDate = (LocalDate) data.get(1);
    students = (ArrayList<Student>) data.get(2);
    rooms = (ArrayList<Room>) data.get(3);
    exams = (ArrayList<Exam>) data.get(4);
  }
  //Export method for xml
  public boolean exportToXML() throws FileNotFoundException
  {
    if(exams.size() < 1)
      return false;

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\">\n<exams>";
    for(int i = 0;i<exams.size();i++)
      xml += ("<exam>\n"
          + "<date>" + exams.get(i).getDate() + "</date>"
          + "<type>" + exams.get(i).getType() + "</type>"
          + "<course>" + exams.get(i).getCourse() + "</course>"
          + "<semester>" + exams.get(i).getSemester() + "</semester>"
          + "<students>" + exams.get(i).getSizeStudents() + "</students>"
          + "<form>" + exams.get(i).getForm() + "</form>"
          + "<room>" + exams.get(i).getRoom().getName() + "</room>"
          + "</exam>");
    xml += "</exams>";
    PrintWriter out = new PrintWriter("examData.xml");
    out.println(xml);
    out.close();

    return true;
  }

  //Set date method of exam
  public void setDate(Exam exam, LocalDate date)
  {
    for (int i = 0; i <exams.size() ; i++)
    {
      if(exams.get(i).equals(exam)){exams.get(i).setDate(date);break;}
    }
  }
  //Set form method of exam
  public void setForm(Exam exam, String form)
  {
    for (int i = 0; i <exams.size() ; i++)
    {
      if(exams.get(i).equals(exam)){exams.get(i).setForm(form);break;}
    }
  }
  //Set type method of exam
  public void setType(Exam exam, String type)
  {
    for (int i = 0; i <exams.size() ; i++)
    {
      if(exams.get(i).equals(exam)){exams.get(i).setType(type);break;}
    }
  }
  //Checks if dates of exams are legal
  public boolean areDatesLegal()
  {boolean isIt=true;
    if(exams.size()==0){return true;}
    for (int i = 0; i < exams.size(); i++)
    {
      if(!isDateLegal(exams.get(i).getDate())){isIt=false; break;}
    }
    return isIt;
  }
  //Checks if type of exam are legal
  public boolean legalType(String type){
    if(type.equals("Written")||type.equals("Oral")){return true;}
    else return false;
  }
  //Checks if type of exams in exam list are legal
  public boolean isLegalType()
  {
    boolean isIt=true;
    for (int i = 0; i < exams.size(); i++)
    {
      if(!(legalType(exams.get(i).getType()))){isIt=false; break;}
    }
    return isIt;
  }
  //Checks if form in exam is legal
  public boolean legalForm(String form)
  {
    if(form.equals("Ordinary")||form.equals("Re-exam")){return true;}
    else return false;
  }
  //Checks if form of exams in exam list are legal
  public boolean isLegalForm()
  {
    boolean isIt=true;
    for (int i = 0; i < exams.size(); i++)
    {
      if(!(legalForm(exams.get(i).getForm()))){isIt=false; break;}
    }
    return isIt;
  }
  //Method to return additional not automatically assigned students
  public ArrayList<Student> additionalStudentsOfExam(Exam exam)
  {
    exam.getCourse_student().removeAll(studentsOfExam(exam));
    return exam.getCourse_student();
  }

  ////////////////////////////////////////////////////////////
  //Date Starts
  //Method to check if date is legal
  @Override public boolean isDateLegal(LocalDate date)
  {
    if(date.isBefore(startDate)||date.isAfter(endDate)){return false;}
    else return true;
  }
  //Set method for starting of exam period
  @Override public void setStartDate(LocalDate date)
  {
    startDate=date;
  }
  //Set method for ending date of exam period
  @Override public void setEndDate(LocalDate date)
  {
    endDate=date;
  }
  //Checks if exam period is not set
  @Override public boolean checkIfNotBlank(LocalDate date1, LocalDate date2)
  {
    if(date1==null||date2==null){return true;}
    else return false;
  }



}
