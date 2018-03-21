//O instanta a acestei clase reprezinta un oras.
import java.util.ArrayList;

public class City
{
  //Numele orasului.
  private String name;

  //Destinatiile din acest oras.
  private ArrayList<Destination> destinations;

  //Constructor.
  public City(String requiredName)
  {
    name = requiredName;
    destinations = new ArrayList<Destination>();
  } //City

  //Metoda de accesare a numelui.
  public String getName()
  {
    return name;
  } //getName

  //Metoda care returneaza destinatiile din oras.
  public ArrayList<Destination> getDestinations()
  {
    return destinations;
  } //getDestinations

  public void addDestination(Destination newDestination)
  {
    destinations.add(newDestination);
  }
} //class City
