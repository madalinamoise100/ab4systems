/* O instanta a acestei clase reprezinta o destinatie de vacanta
   ce are urmatoarele caracteristici:
   - nume
   - oras
   - pret mediu pe zi
   - o colectie de activitati ce se pot practica acolo
   - o perioada in care se poate merge in vacanta acolo */
import java.util.ArrayList;

public class Destination
{
  private String name;
  private City city;
  private int dailyCost;
  private ArrayList<Activity> activities;
  private Period period;

  //Constructorul ia un String de forma:
  //nume ; oras ; judet ; tara ; cost ; activitati despartite prin "," ;
  //data de incepere a perioadei - data de terminare a perioadei
  public Destination(String requiredDetails)
  {
    String elements = requiredDetails.split(";");
    name = elements[0];
    city = new City(elements[1]);
    County county = new County(elements[2]);
    Country country = new Country(elements[3]);
    if(countries.contains(country))
      if(country.counties.contains(county))
        if(!county.cities.contains(city))
          //Daca orasul nu exista in judet, trebuie adaugat.
          county.addCity(city);
      else
      {
        //Daca judetul nu exista in tara, trebuie adaugat.
        country.addCounty(county);
        //Apoi trebuie adaugat orasul.
        county.addCity(city);
      } //else
    else
    {
      //Daca tara nu exista, trebuie adaugata.
      countries.add(country);
      //Apoi trebuie adaugat judetul.
      country.addCounty(county);
      //Apoi trebuie adaugat orasul.
      county.addCity(city);
    } //else
    //Trebuie adaugata destinatia orasului.
    city.destinations.add(Destination);
    dailyCost = Integer.parseInt(elements[4]);
    activities = new ArrayList<Activity>;
    String[] activityNames = elements[5].split(",");
    for(int index = 0; index < activityNames.length; index++)
      activities.add(new Acitvity(activityNames[index]);
    String[] dates = elements[6].split("-");
    period.setStartDate(new Date(dates[0]));
    period.setEndDate(new Date(dates[1]));
  } //Destination

  public void setName(String newName)
  {
    name = newName;
  } //setName

  public void setCity(City newCity)
  {
    city = newCity;
  } //setCity

  public void setDailyCost(int newDailyCost)
  {
    dailyCost = newDailyCost;
  } //setDailyCost

  //Metoda pentru a adauga o activitate.
  public void addActivity(Activity newActivity)
  {
    activites.add(newActivity);
  } //addActivity

  //Metoda pentru a elimina o activitate.
  public void removeActivity(String nameOfActivityToRemove)
  {
    for(int index = 0; index < activities.size(); index++)
      if(activities[index].getName() == nameOfActivityToRemove)
        activities.remove(index);
  } //removeActivity

  public void setPeriod(Period newPeriod)
  {
    period = newPeriod;
  } //setPeriod

  //METODE DE ACCESARE

  public String getName()
  {
    return name;
  } //getName

  public City getCity()
  {
    return city;
  } //getCity

  public int getDailyCost()
  {
    return dailyCost;
  } //getDailyCost

  public int getPeriod()
  {
    return period;
  } //getPeriod

  public ArrayList<Activty> getActivities()
  {
    return activities;
  } //getActivites
} //class Destination
