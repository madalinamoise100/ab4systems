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

      Destination destination1 = new Destination("AfiPalace;Bucuresti;Bucuresti;Romania;300;shopping,mancat;12/03/2017-15/03/2017");
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
      } //else if





    } //try
    catch(IOException exception)
    {
      System.err.println(exception);
    }
  } //main
} //class Test
