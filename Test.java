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

  //Metoda ce gaseste valoarea minima.
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

    try
    {
      //Destinatiile sunt citite dintr-un fisier.
      destinationInput = new BufferedReader(new FileReader("destinations.txt"));

      userInput = new BufferedReader(new InputStreamReader(System.in));

      //Un ArrayList ce contine toate tarile.
      countries = new ArrayList<Country>();

      //Un ArrayList ce contine toate destinatiile.
      allDestinations = new ArrayList<Destination>();

      //Un HashMap ce contine nume de activitati drept key si destinatii ca values.
      Destination.activitiesMap = new HashMap<String, ArrayList<Destination>>();

      //Destinatiile din fisier sunt adaugate intr-un ArrayList.
      String currentLine;
      while((currentLine = destinationInput.readLine()) != null)
      {
        Destination destination = new Destination(currentLine);
        allDestinations.add(destination);
      } //while

      //Meniul principal din care poate alege un user.
      System.out.println("Alegeti o actiune: ");
      System.out.println("1. Afisati descrierea unei destinatii.");
      System.out.println("2. Top 5 locatii din tara/judetul/orasul X in care pot merge in perioada A-B ordonate"
                          + " dupa costul total pentru a merge toata perioada A-B acolo.");
      System.out.println("3. Unde este cel mai ieftin sa practic 10 zile o activitate.");

      //Se citeste raspunsul user-ului.
      String answer = userInput.readLine();

      //Daca se alege varianta 1:
      if(Integer.parseInt(answer) == 1)
      {
        //O variabila boolean ce retine daca destinatia aleasa exista sau nu.
        boolean destinationFound = false;

        System.out.println("Introduceti destinatia despre care doriti sa stiti mai multe.");
        answer = userInput.readLine();

        //Se verifica daca destinatia aleasa de user exista si se modifica variabila destinationFound corespunzator in caz pozitiv.
        for(Destination destination : allDestinations)
          if(destination.getName().equals(answer))
          {
            System.out.println(destination);
            destinationFound = true;
          } //if

        //Daca destinatia nu exista, se afiseaza un mesaj in consecinta.
        if(!destinationFound)
          System.out.println("Destinatia introdusa nu exista!");
      } //if

      //Daca user-ul alege varianta 2:
      else if(Integer.parseInt(answer) == 2)
      {
        //Destinatiile valide sunt retinute intr-un ArrayList.
        ArrayList<Destination> validDestinations = new ArrayList<Destination>();

        //Se cere user-ului perioada dorita.
        System.out.println("Introduceti perioada sub forma: dd/mm/yyyy-dd/mm/yyyy");
        //String-ul rezultat este parsat pentru a obtine startDate si endDate.
        String[] dates = userInput.readLine().split("-");

        //Se ofera alte 3 variante.
        System.out.println("Alegeti: 1. tara; 2. judet; 3. oras.");
        answer = userInput.readLine();

        //Daca se cauta prin tari:
        if(Integer.parseInt(answer) == 1)
        {
          //Se cere numele tarii.
          System.out.println("Introduceti numele tarii.");

          //O variabila ce retine daca tara a fost gasita.
          boolean locationMatch = false;

          //O variabila care retine daca perioada aleasa este disponibila.
          boolean periodMatch = false;

          String name = userInput.readLine();

          //Se cauta prin toate destinatiile din tara aleasa.
          for(Destination destination : allDestinations)
            if(destination.getCountry().getName().equals(name))
            {
              locationMatch = true;
              //Daca se gaseste tara, se verifica perioada.
              if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                 && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
              {
                //Sunt retinute destinatiile valide.
                 validDestinations.add(destination);
                 periodMatch = true;
              } //if
            } //if

          //Destinatiile valide sunt sortate dupa dailyCost.
          Collections.sort(validDestinations);

          //Se printeaza top 5 (sau cele mai bune variante, daca nu au fost gasite 5 destinatii valide).
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          //Se afiseaza mesaje corespunzatoare daca nu se gaseste locul cerut/ perioada nu este potrivita.
          if(!locationMatch)
            System.out.println("Tara aleasa nu exista.");
          else if(!periodMatch)
            System.out.println("Tara pe care ati ales-o nu are destinatii ce se pot vizita in perioada aleasa.");
        } //if

        //Daca se cauta prin judete:
        else if(Integer.parseInt(answer) == 2)
        {
          //Se cere numele tarii de care apartine judetrul si numele judetului, sub formatul corespunzator.
          System.out.println("Introduceti judetul in format: tara-judet.");

          //Raspunsul este retinut intr-un String ce va fi parsat.
          String[] names = userInput.readLine().split("-");

          //O variabila ce retine daca judetul a fost gasit.
          boolean locationMatch = false;

          //O variabila care retine daca perioada aleasa este disponibila.
          boolean periodMatch = false;

          //Se cauta prin toate destinatiile din judetul ales.
          for(Destination destination : allDestinations)
            if(destination.getCountry().getName().equals(names[0]) &&
               destination.getCounty().getName().equals(names[1]))
              {
                locationMatch = true;
                //Daca se gaseste judetul, se verifica perioada.
                if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                   && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
                {
                  validDestinations.add(destination);
                  periodMatch = true;
                } //if
            } //if

          //Destinatiile valide sunt sortate dupa dailyCost.
          Collections.sort(validDestinations);

          //Se printeaza top 5 (sau cele mai bune variante, daca nu au fost gasite 5 destinatii valide).
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          //Se afiseaza mesaje corespunzatoare daca nu se gaseste locul cerut/ perioada nu este potrivita.
          if(!locationMatch)
            System.out.println("Nu a fost gasita locatia aleasa.");
          else if(!periodMatch)
            System.out.println("Judetul pe care l-ati ales nu are destinatii ce se pot vizita in perioada aleasa.");
        } //else if

        else if(Integer.parseInt(answer) == 3)
        {
          System.out.println("Introduceti orasul in format: tara-judet-oras.");

          //Raspunsul este retinut intr-un String ce va fi parsat.
          String[] names = userInput.readLine().split("-");

          //O variabila care retine daca perioada aleasa este disponibila.
          boolean periodMatch = false;

          //O variabila ce retine daca orasul a fost gasit.
          boolean locationMatch = false;


          //Se cauta prin toate destinatiile din orasul ales.
          for(Destination destination : allDestinations)
          {
            if(destination.getCountry().getName().equals(names[0]) &&
               destination.getCounty().getName().equals(names[1]) &&
               destination.getCity().getName().equals(names[2]))
            {
              locationMatch = true;
              //Daca se gaseste orasul, se verifica perioada.
              if(destination.getPeriod().getStartDate().daysFrom(new Date(dates[0])) >= 0
                 && destination.getPeriod().getEndDate().daysFrom(new Date(dates[1])) <= 0)
              {
                periodMatch = true;
                validDestinations.add(destination);
              } //if
            } //if
          } //for

          //Destinatiile valide sunt sortate dupa dailyCost.
          Collections.sort(validDestinations);

          //Se printeaza top 5 (sau cele mai bune variante, daca nu au fost gasite 5 destinatii valide).
          for(int index = 0; index < min(5, validDestinations.size()); index++)
          {
            System.out.println(validDestinations.get(index));
            System.out.println();
          } //for

          //Se afiseaza mesaje corespunzatoare daca nu se gaseste locul cerut/ perioada nu este potrivita.
          if(!locationMatch)
            System.out.println("Nu a fost gasita locatia aleasa.");
          else if(!periodMatch)
            System.out.println("Orasul ales nu are destinatii ce pot fi vizitate in perioada aleasa.");
        } //else if
      } //else if

      else if(Integer.parseInt(answer) == 3)
      {
        //Se cere numele activitatii.
        System.out.println("Introduceti activitatea.");
        String activityName = userInput.readLine();

        //O variabila ce va retine costul minim.
        int min = Integer.MAX_VALUE;

        //O variabila ce va retine cea main ieftina destinatie unde se poate practica activitatea aleasa.
        Destination cheapest = allDestinations.get(0);

        //Se cauta in HashMap destinatiile unde se poate practica activitatea aleasa pentru cel putin 10 zile.
        if(!Destination.activitiesMap.containsKey(activityName))
          System.out.println("Activitatea aleasa nu exista.");
        else
        {
          ArrayList<Destination> mapValues = Destination.activitiesMap.get(activityName);

          for(int index = 0; index < mapValues.size(); index++)
            if(mapValues.get(index).getDailyCost() < min
               && mapValues.get(index).getPeriod().getStartDate().daysFrom(mapValues.get(index).getPeriod().getEndDate()) >= 10)
            {
              min = mapValues.get(index).getDailyCost();
              cheapest = mapValues.get(index);
            } //if
          System.out.println(cheapest);
        } //else
      } //else if

      else
        System.out.println("Alegeti una dintre variantele disponibile!");
    } //try
    catch(IOException exception)
    {
      System.err.println(exception);
    }
  } //main
} //class Test
