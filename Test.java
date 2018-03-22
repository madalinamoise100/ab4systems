import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Collections;

public class Test
{
  //Toate tarile.
  public static ArrayList<Country> countries;

  //Toate destinatiile.
  public static ArrayList<Destination> allDestinations;

  public static int min(int firstInt, int secondInt)
  {
    if(firstInt < secondInt)
      return firstInt;
    else return secondInt;
  }

  public static void main(String[] args) throws Exception
  {
    //BufferedReader destinationInput = null;
    BufferedReader userInput = null;
    PrintWriter output = null;

    try
    {
      //destinationInput = new BufferedReader(new FileReader(destinations.txt));
      userInput = new BufferedReader(new InputStreamReader(System.in));

      countries = new ArrayList<Country>();
      allDestinations = new ArrayList<Destination>();
      Destination.activitiesMap = new HashMap<String, ArrayList<Destination>>();

      Destination destination1 = new Destination("AfiPalace;Bucuresti;Bucuresti;Romania;300;shopping,mancat;12/03/2017-12/04/2017");
      Destination destination2 = new Destination("ShoppingCity;Ploiesti;Prahova;Romania;400;shopping,cinema;13/02/2017-10/03/2017");
      allDestinations.add(destination1);

      System.out.println("Alegeti o actiune: ");
      System.out.println("1. Afisati descrierea unei destinatii.");
      System.out.println("2. Top 5 locatii din tara/judetul/orasul X in care pot merge in perioada A-B ordonate"
                          + " dupa costul total pentru a merge toata perioada A-B acolo.");
      System.out.println("3. Unde este cel mai ieftin sa practic 10 zile o activitate.");

      String answer = userInput.readLine();
      if(Integer.parseInt(answer) == 1)
      {
        boolean destinationFound = false;
        System.out.println("Introduceti destinatia despre care doriti sa stiti mai multe.");
        answer = userInput.readLine();
        for(int index = 0; index < allDestinations.size(); index++)
          if(allDestinations.get(index).getName().equals(answer))
          {
            System.out.println(allDestinations.get(index));
            destinationFound = true;
          } //if
        if(destinationFound == false)
          System.out.println("Destinatia introdusa nu exista!");
      } //if
      else if(Integer.parseInt(answer) == 2)
      {
        ArrayList<Destination> validDestinations = new ArrayList<Destination>();
        System.out.println("Introduceti perioada sub forma: dd/mm/yyyy-dd/mm/yyyy");
        String[] dates = userInput.readLine().split("-");

        System.out.println("Alegeti: 1. tara; 2. judet; 3. oras.");
        answer = userInput.readLine();

        if(Integer.parseInt(answer) == 1)
        {
          System.out.println("Introduceti numele tarii.");
          boolean countryFound = false;
          boolean periodMatch = false;
          String name = userInput.readLine();
          for(int index = 0; index < countries.size(); index++)
            if(countries.get(index).getName().equals(name))
            {
              for(int count = 0; count < allDestinations.size(); count++)
                if(allDestinations.get(count).getCountry().getName().equals(name))
                {
                  countryFound = true;
                  if(allDestinations.get(count).getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                     && allDestinations.get(count).getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
                  {
                     validDestinations.add(allDestinations.get(count));
                     periodMatch = true;
                  } //if
                } //if
            } //if
          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
            System.out.println(validDestinations.get(index));
          if(countryFound == false)
            System.out.println("Tara aleasa nu exista.");
          else if(periodMatch == false)
            System.out.println("Tara pe care ati ales-o nu are destinatii ce se pot vizita in perioada aleasa.");
        } //if
        else if(Integer.parseInt(answer) == 2)
        {
          System.out.println("Introduceti judetul in format: tara-judet.");
          String[] names = userInput.readLine().split("-");
          boolean countryFound = false;
          boolean countyFound = false;
          boolean periodMatch = false;
          for(int index = 0; index < countries.size(); index++)
            if(countries.get(index).getName().equals(names[0]))
            {
              countryFound = true;
              Country chosenCountry = countries.get(index);
              for(int index2 = 0; index2 < chosenCountry.counties.size(); index2++)
              {
                if(chosenCountry.counties.get(index2).getName().equals(names[1]))
                {
                  countyFound = true;
                  for(int count = 0; count < allDestinations.size(); count++)
                    if(allDestinations.get(count).getCounty().getName().equals(names[1]))
                    {

                      if(allDestinations.get(count).getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                        && allDestinations.get(count).getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
                      {
                        validDestinations.add(allDestinations.get(count));
                        periodMatch = true;
                      } //if
                    } //if
                } //if
              } //for
            } //if
          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
            System.out.println(validDestinations.get(index));
          if(countryFound == false)
            System.out.println("Tara aleasa nu exista.");
          else if(countyFound == false)
            System.out.println("Judetul ales nu exista.");
          else if(periodMatch == false)
            System.out.println("Judetul pe care l-ati ales nu are destinatii ce se pot vizita in perioada aleasa.");
        } //else if
        else if(Integer.parseInt(answer) == 3)
        {
          System.out.println("Introduceti orasul in format: tara-judet-oras.");
          String[] names = userInput.readLine().split("-");
          boolean countryFound = false;
          boolean countyFound = false;
          boolean cityFound = false;
          boolean periodMatch = false;
          for(int index = 0; index < countries.size(); index++)
            if(countries.get(index).getName().equals(names[0]))
            {
              countryFound = true;
              Country chosenCountry = countries.get(index);
              for(int index2 = 0; index2 < chosenCountry.counties.size(); index2++)
              {
                if(chosenCountry.counties.get(index2).getName().equals(names[1]))
                {
                  County chosenCounty = chosenCountry.counties.get(index2);
                  countyFound = true;
                  for(int index3 = 0; index3 < chosenCounty.cities.size(); index3++)
                  {
                    if(chosenCounty.cities.get(index3).getName().equals(names[2]))
                    {
                      cityFound = true;
                      for(int count = 0; count < allDestinations.size(); count++)
                        if(allDestinations.get(count).getCity().getName().equals(names[2]))
                        {

                          if(allDestinations.get(count).getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                            && allDestinations.get(count).getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
                          {
                            validDestinations.add(allDestinations.get(count));
                            periodMatch = true;
                          } //if
                        } //if
                    } //if
                  } //for
                } //if
              } //for
            } //if
          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
            System.out.println(validDestinations.get(index));
          if(countryFound == false)
            System.out.println("Tara aleasa nu exista.");
          else if(countyFound == false)
            System.out.println("Judetul ales nu exista.");
          else if(cityFound == false)
            System.out.println("Orasul ales nu exista.");
          else if(periodMatch == false)
            System.out.println("Orasul pe care l-ati ales nu are destinatii ce se pot vizita in perioada aleasa.");
        } //else if
      } //else if
      else if(Integer.parseInt(answer) == 3)
      {
        System.out.println("Introduceti activitatea.");
        String activityName = userInput.readLine();
        int min = Integer.MAX_VALUE;
        Destination cheapest = allDestinations.get(0);
        if(!Destination.activitiesMap.containsKey(activityName))
          System.out.println("Activitatea aleasa nu exista.");
        else
        {
          ArrayList<Destination> mapValues = Destination.activitiesMap.get(activityName);
          System.out.println(mapValues.size());
          for(int index = 0; index < mapValues.size(); index++)
            if(mapValues.get(index).getDailyCost() < min)
            {
              min = mapValues.get(index).getDailyCost();
              cheapest = mapValues.get(index);
            } //if
          System.out.println(cheapest);
        } //else
      } //else if
    } //try
    catch(IOException exception)
    {
      System.err.println(exception);
    }
  } //main
} //class Test
