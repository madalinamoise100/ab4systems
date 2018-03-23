import java.util.ArrayList;

//O instanta a acestei clase reprezinta o tara.
public class Country
{
  //Numele tarii.
  private String name;

  //Toate judetele ce apartin acestei tari.
  public ArrayList<County> counties;

  //Constructor.
  public Country(String requiredName)
  {
    name = requiredName;
    counties = new ArrayList<County>();
  } //Country

  //Metoda pentru a adauga un judet acestei tari.
  public void addCounty(County newCounty)
  {
    counties.add(newCounty);
  } //addCounty

  public County getCounty(County countyToGet)
  {
    if(counties.contains(countyToGet))
      return countyToGet;
    else return null;
  } //getCounty

  public String getName()
  {
    return name;
  } //getName
} //class Country
