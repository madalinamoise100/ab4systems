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
    BufferedReader destinationInput = null;
    BufferedReader userInput = null;
    PrintWriter output = null;

    try
    {
      destinationInput = new BufferedReader(new FileReader("destinations.txt"));
      userInput = new BufferedReader(new InputStreamReader(System.in));

      countries = new ArrayList<Country>();
      allDestinations = new ArrayList<Destination>();
      Destination.activitiesMap = new HashMap<String, ArrayList<Destination>>();
      String currentLine;
      while((currentLine = destinationInput.readLine()) != null)
      {
        Destination destination = new Destination(currentLine);
        allDestinations.add(destination);
      } //while


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
          boolean locationMatch = false;
          boolean periodMatch = false;
          String name = userInput.readLine();

          for(Destination destination : allDestinations)
            if(destination.getCountry().getName().equals(name))
            {
              locationMatch = true;
              if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                 && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
              {
                 validDestinations.add(destination);
                 periodMatch = true;
              } //if
            } //if

          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          if(!locationMatch)
            System.out.println("Tara aleasa nu exista.");
          else if(!periodMatch)
            System.out.println("Tara pe care ati ales-o nu are destinatii ce se pot vizita in perioada aleasa.");
        } //if

        else if(Integer.parseInt(answer) == 2)
        {
          System.out.println("Introduceti judetul in format: tara-judet.");
          String[] names = userInput.readLine().split("-");
          boolean periodMatch = false;
          boolean locationMatch = false;

          for(Destination destination : allDestinations)
            if(destination.getCountry().getName().equals(names[0]) &&
               destination.getCounty().getName().equals(names[1]))
              {
                locationMatch = true;
                if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                   && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
                {
                  validDestinations.add(destination);
                  periodMatch = true;
                } //if
            } //if

          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          if(!locationMatch)
            System.out.println("Nu a fost gasita locatia aleasa.");
          else if(!periodMatch)
            System.out.println("Judetul pe care l-ati ales nu are destinatii ce se pot vizita in perioada aleasa.");
        } //else if

        else if(Integer.parseInt(answer) == 3)
        {
          System.out.println("Introduceti orasul in format: tara-judet-oras.");
          String[] names = userInput.readLine().split("-");
          boolean periodMatch = false;
          boolean locationMatch = false;

          for(Destination destination : allDestinations)
          {
            if(destination.getCountry().getName().equals(names[0]) &&
               destination.getCounty().getName().equals(names[1]) &&
               destination.getCity().getName().equals(names[2]))
            {
              locationMatch = true;
              if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                 && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
              {
                periodMatch = true;
                validDestinations.add(destination);
              } //if
            } //if
          } //for

          Collections.sort(validDestinations);
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          if(!locationMatch)
            System.out.println("Nu a fost gasita locatia aleasa.");
          else if(!periodMatch)
            System.out.println("Orasul ales nu are destinatii ce pot fi vizitate in perioada aleasa.");

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
