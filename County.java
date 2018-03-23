//O instanta a acestei clase reprezinta un judet.
import java.util.ArrayList;

public class County
{
  //Numele judetului.
  private String name;

  //Orasele ce apartin acestui judet.
  public ArrayList<City> cities;

  //Constructor.
  public County(String requiredName)
  {
    name = requiredName;
    cities = new ArrayList<City>();
  } //County

  //METODE DE ACCESARE

  public String getName()
  {
    return name;
  } //getName

  //Metoda pentru a adauga un oras.
  public void addCity(City newCity)
  {
    cities.add(newCity);
  } //addCity

  public City getCity(City cityToGet)
  {
    if(cities.contains(cityToGet))
      return cityToGet;
    else return null;
  }
} //class County
