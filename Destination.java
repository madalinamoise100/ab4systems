/* O instanta a acestei clase reprezinta o destinatie de vacanta
   ce are urmatoarele caracteristici:
   - nume
   - oras
   - pret mediu pe zi
   - o colectie de activitati ce se pot practica acolo
   - o perioada in care se poate merge in vacanta acolo */
import java.util.ArrayList;
import java.util.HashMap;

public class Destination implements Comparable<Destination>
{
  private String name;
  private City city;
  private County county;
  private Country country;
  private int dailyCost;
  private ArrayList<Activity> activities;
  private Period period;
  public static HashMap<String, ArrayList<Destination>> activitiesMap;

  //Constructorul ia un String de forma:
  //nume ; oras ; judet ; tara ; cost ; activitati despartite prin "," ;
  //data de incepere a perioadei - data de terminare a perioadei
  public Destination(String requiredDetails) throws Exception
  {
    String[] elements = requiredDetails.split(";");
    name = elements[0];
    city = new City(elements[1]);
    county = new County(elements[2]);
    country = new Country(elements[3]);
    if(Test.countries.contains(country))
    {
      if(country.counties.contains(county))
      {
        if(!county.cities.contains(city))
        {
          //Daca orasul nu exista in judet, trebuie adaugat.
          county.addCity(city);
        }
      } //if
      else
      {
        //Daca judetul nu exista in tara, trebuie adaugat.
        country.addCounty(county);
        //Apoi trebuie adaugat orasul.
        county.addCity(city);
      } //else
    } //if
    else
    {
      //Daca tara nu exista, trebuie adaugata.
      Test.countries.add(country);
      //Apoi trebuie adaugat judetul.
      country.addCounty(county);
      //Apoi trebuie adaugat orasul.
      county.addCity(city);
    } //else

    dailyCost = Integer.parseInt(elements[4]);
    activities = new ArrayList<Activity>();
    String[] activityNames = elements[5].split(",");
    for(int index = 0; index < activityNames.length; index++)
    {
      activities.add(new Activity(activityNames[index]));
      if(!activitiesMap.containsKey(activities.get(index).getName()))
      {
        ArrayList<Destination> values = new ArrayList<Destination>();
        values.add(this);
        activitiesMap.put(activities.get(index).getName(), values);
      } //if
      else
      {
        ArrayList<Destination> currentValues = activitiesMap.get(activities.get(index).getName());
        currentValues.add(this);
        activitiesMap.put(activities.get(index).getName(), currentValues);
      } //else
    } //for
    String[] dates = elements[6].split("-");
    period = new Period(new Date(dates[0]), new Date(dates[1]));
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
    activities.add(newActivity);
  } //addActivity

  //Metoda pentru a elimina o activitate.
  public void removeActivity(String nameOfActivityToRemove)
  {
    for(int index = 0; index < activities.size(); index++)
      if(activities.get(index).getName() == nameOfActivityToRemove)
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

  public County getCounty()
  {
    return county;
  } //getCountry

  public Country getCountry()
  {
    return country;
  } //getCountry

  public int getDailyCost()
  {
    return dailyCost;
  } //getDailyCost

  public Period getPeriod()
  {
    return period;
  } //getPeriod

  public ArrayList<Activity> getActivities()
  {
    return activities;
  } //getActivites

  public String toString()
  {
    String activitiesString = "";
    for(int index = 0; index < activities.size(); index++)
      activitiesString += activities.get(index).getName() + ", ";

    String description = "Destinatia " + name + ", din orasul " + city.getName()
                          + ", necesita un buget zilnic de " + dailyCost
                          + ". Aici se pot practica urmatoarele activitati: "
                          + activitiesString + " in perioada "
                          + period.getStartDate() + " - " + period.getEndDate();
  return description;
} //toString

@Override
public int compareTo(Destination other)
{
  return this.dailyCost - other.getDailyCost();
} //compareTo

} //class Destination
