//O instanta a acestei clase reprezinta un judet.
import java.util.ArrayList;

public class County
{
  //Numele judetului.
  private String name;

  //Orasele ce apartin acestui judet.
  private ArrayList<City> cities;

  //Constructor.
  public County(String requiredname)
  {
    name = requiredName;
    cities = new ArrayList<City>;
  } //County

  //METODE DE ACCESARE

  public String getName()
  {
    return name;
  } //getName

  public ArrayList<City> getCities()
  {
    return cities;
  } //getCities

  //Metoda pentru a adauga un oras.
  public void addCity(City newCity)
  {
    cities.add(newCity);
  } //addCity

  //Metoda pentru a elimina un oras.
  public void removeCity(String nameOfCityToRemove)
  {
    for(int index = 0; index < cities.size(); index++)
      if(cities[index].getName() == nameOfCityToRemove)
        cities.remove(index);
  } //removeCity
} //class County
