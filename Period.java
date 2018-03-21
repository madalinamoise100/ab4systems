//Clasa ce reprezinta o perioada de timp intre doua date.
import java.util.Date;

public class Period
{
  private Date startDate;
  private Date endDate;

  //Constructor
  public Period(Date requiredStartDate, Date requiredEndDate)
  {
    startDate = requiredStartDate;
    endDate = requiredEndDate;
  } //Period

  public int getNoOfDays()
  {
    return startDate.daysFrom(endDate);
  } //getNoOfDays

  public void setStartDate(Date newStartDate)
  {
    startDate = newStartDate;
  } //setStartDate

  public void setEndDate(Date newEndDate)
  {
    endDate = newEndDate;
  } //setEndDate
} //class Period
