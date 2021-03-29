package Module;

import java.io.Serializable;

public class Room implements Serializable
{
  private String name;
  private int capacity;
  private boolean equipment;

  public Room(String name, int capacity, boolean equipment)
  {
    this.name = name;
    this.capacity = capacity;
    this.equipment = equipment;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public void setCapacity(int capacity)
  {
    this.capacity = capacity;
  }

  public boolean getEquipment()
  {
    return equipment;
  }

  public void setEquipment(boolean equipment)
  {
    this.equipment = equipment;
  }

  public boolean absoluteEquals(Object obj)
  {
    if (!(obj instanceof Room))
    {
      return false;
    }
    Room other = (Room) obj;
    return name.equals(other.name) && capacity == other.capacity
        && equipment == other.equipment;
  }
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Room))
    {
      return false;
    }
    Room other = (Room) obj;
    return name.equals(other.name);
  }

  public String toString()
  {
    return "Name: " + name+ " Capacity: " + capacity + " Equipment: "+ equipment;
  }
}
