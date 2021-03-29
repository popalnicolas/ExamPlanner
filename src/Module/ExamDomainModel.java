package Module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ExamDomainModel
{
  void addStudent(int id, String course, String name);
  void removeStudent(Student student);
  ArrayList<Student> getAllStudents();
  boolean isSameID(Student student);
  void setId(Student student,int id);
  void setName(Student student, String name);
  void setCourses(Student student, String courses);
  boolean isSameIDAll();
  ArrayList<String> studentCourses(Student student);
  boolean areIDlegal();

  void addRoom(String name, int capacity, boolean equipment);
  void removeRoom(Room room);
  boolean isSameName(Room room);
  ArrayList<Room> getAllRooms();
  void setName(Room room, String name);
  void setCapacity(Room room, int capacity);
  void setEquipment(Room room, boolean equipment);
  boolean isSameNameAll();

  void addExam(LocalDate date, int semester, String form, String type,
      String course, Room room);
  void removeExam(Exam exam);
  boolean isSemesterLegal(int semester);
  boolean checkEquipment(Room room,String type);
  boolean isDateLegal(LocalDate date);
  boolean checkDateSeventhSemester(int semester, LocalDate date);
  boolean checkCapacity(Room room, String type);
  boolean legalSameDateRoomOralWritten(LocalDate date,Room room, String type);
  boolean legalDateType(LocalDate date, String type);
  void setSelectedExam(Exam exam);
  Exam getSelectedExam();
  ArrayList<Student> studentsOfExam(Exam exam);
  ArrayList<Student> additionalStudentsOfExam(Exam exam);
  ArrayList<Exam> allExams();
  void setStudents(Exam exam,ArrayList<Student> students);
  boolean checkSameIDTable(Student student, ArrayList<Student> students);
  boolean isConsecutiveDays();
  boolean isExamNextToEachOther(Exam exam, Exam exam2);
  void setDate(Exam exam, LocalDate date);
  void setForm(Exam exam, String form);
  void setType(Exam exam, String type);
  boolean areDatesLegal();
  boolean legalType(String type);
  boolean isLegalType();
  boolean legalForm(String form);
  boolean isLegalForm();
  boolean checkDateRoomTypeAll();
  boolean checkWrittenOralAll();
  boolean checkDate(Exam exam);

  ArrayList<Exam> getExams();
  LocalDate getEndDate();
  LocalDate getStartDate();
  void saveData() throws IOException;
  void loadData() throws IOException, ClassNotFoundException;
  boolean exportToXML() throws FileNotFoundException;

  void setStartDate(LocalDate date);
  void setEndDate(LocalDate date);
  boolean checkIfNotBlank(LocalDate date1, LocalDate date2);

}
