//Clasa ce reprezinta o perioada de timp intre doua date.


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

  public void setStartDate(Date newStartDate) throws Exception
  {
    startDate = newStartDate;
  } //setStartDate

  public void setEndDate(Date newEndDate) throws Exception
  {
    endDate = newEndDate;
  } //setEndDate

  public Date getStartDate()
  {
    return startDate;
  } //getStartDate

  public Date getEndDate()
  {
    return endDate;
  } //getEndDate
} //class Period
