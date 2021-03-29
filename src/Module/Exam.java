package Module;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Exam implements Serializable
{
  private LocalDate date;
  private int semester;
  private String form;
  private String type;
  private String course;
  private ArrayList<Student> course_student;
  private Room room;

  public Exam(LocalDate date, int semester, String form, String type,
      String course, Room room)
  {
    this.date = date;
    this.semester = semester;
    this.form = form;
    this.type = type;
    this.course = course;
    this.course_student = new ArrayList<Student>();
    this.room = room;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public void setDate(LocalDate date)
  {
    this.date = date;
  }

  public int getSemester()
  {
    return semester;
  }

  public void setSemester(int semester)
  {
    this.semester = semester;
  }

  public String getForm()
  {
    return form;
  }

  public void setForm(String form)
  {
    this.form = form;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getCourse()
  {
    return course;
  }

  public void setCourse(String course)
  {
    this.course = course;
  }

  public int getSizeStudents(){return course_student.size();}
  public ArrayList<Student> getCourse_student()
  {
    return course_student;
  }

  public void setCourse_student(ArrayList<Student> course_student)
  {
    this.course_student = course_student;
  }

  public Room getRoom()
  {
    return room;
  }

  public void setRoom(Room room)
  {
    this.room = room;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Exam))
    {
      return false;
    }
    Exam other = (Exam) obj;
    return date.equals(other.date) && semester == other.semester && form
        .equals(other.form) && type.equals(other.type) && course
        .equals(other.course) && room.equals(other.room);
  }

  public String toString()
  {
    return "Date: " + date + " Semester: " + semester + " Form: " + form
        + " Type: " + type + " Course: " + course + " CourseStudents: "
        + course_student + " Room: " + room;
  }
}
