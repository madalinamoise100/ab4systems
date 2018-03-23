//O instanta a acestei clase reprezinta o activitate.
public class Activity
{
  //Numele activitatii.
  private String name;

  //Constructor
  public Activity(String requiredName)
  {
    name = requiredName;
  } //Activity

  public String getName()
  {
    return name;
  } //getName

  public void setName(String newName)
  {
    name = newName;
  } //setName
} //class Activity
