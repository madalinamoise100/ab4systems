import java.util.ArrayList;

public class Country
{
  private String name;

  public ArrayList<County> counties;

  public Country(String requiredName)
  {
    name = requiredName;
    counties = new ArrayList<County>();
  }

  public void addCounty(County newCounty)
  {
    counties.add(newCounty);
  }

  public County getCounty(County countyToGet)
  {
    if(counties.contains(countyToGet))
      return countyToGet;
    else return null;
  }

  public String getName()
  {
    return name;
  }
}
