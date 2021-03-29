package Module;

import java.io.Serializable;

public class Student implements Serializable
{
  private int Id;
  private String courses;
  private String name;

  public Student(int id, String courses, String name)
  {
    this.name = name;
    Id = id;
    this.courses = courses;
  }

  public int getId()
  {
    return Id;
  }

  public void setId(int id)
  {
    Id = id;
  }

  public String getCourses()
  {
    return courses;
  }

  public void setCourses(String courses)
  {
    this.courses = courses;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isLegalID()
  {
    if (Id > 999999 || Id < 100000)
    {
      return false;
    }
    else
      return true;
  }
  //Checks if this is the particular student
  public boolean absoluteEquals(Object obj)
  {
    if (!(obj instanceof Student))
    {
      return false;
    }
    Student other = (Student) obj;
    return Id == other.Id && name.equals(other.name) && courses
        .equals(other.courses);
  }
  //Checks if Id is the same
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Student))
    {
      return false;
    }
    Student other = (Student) obj;
    return Id == other.Id;
  }

  public String toString()
  {
    return "ID: " + Id + " Name: " + name + " Courses: " + courses;
  }
}
