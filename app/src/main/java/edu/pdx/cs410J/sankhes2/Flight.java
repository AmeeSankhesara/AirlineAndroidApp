package edu.pdx.cs410J.sankhes2;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Flight class having flight info
 */
public class Flight extends AbstractFlight {

  /**
   * @param flightNumber
   *        The flight number
   * @param source
   *         Three-letter code of departure airport
   * @param destination
   *        Three-letter code of arrival airport
   * @param arrivalTime
   *        Arrival date and time (24-hour time)
   * @param departureTime
   *        Departure date and time (24-hour time)
   */
  int flightNumber;
  String source;
  String destination;
  String arrivalTime;
  String departureTime;

  public Flight(String fNum) {
    if (fNum.matches("[0-9]+"))
    {
      this.flightNumber = Integer.parseInt(fNum);
    }
    else
    {
      throw new UnsupportedOperationException("Please enter integer flight number only");
    }
  }

  /**
   * setSource is validating departure airport and also setting it's value to source variable
   * @param source - Three-letter code of departure airport
   */
  public void setSource(String source)
  {
    if (source.length() == 3 && source.matches("^[a-zA-Z]*$"))
    {
      this.source = source;
    }
    else
    {
      throw new UnsupportedOperationException("Please enter only three letter code of departure airport");
    }
  }

  /**
   * setDestination is validating destination airport and also setting it's value to destination variable
   * @param destination - Three-letter code of arrival airport
   */
  public void setDestination(String destination) {
    if(destination.length() == 3 && destination.matches("^[a-zA-Z]*$"))
    {
      this.destination = destination;
    }
    else
    {
      throw new UnsupportedOperationException("Please enter only three letter code of arrival airport");
    }
  }

  /**
   * setArrivalTime is validating arrival date time of flight and setting it to arrivalTime variable
   * @param arrivalDate - Arrival date in format mm/dd/yyyy
   * @param arrivalTime - Arrival time (24-hour time)
   */
  public void setArrivalTime(String arrivalDate,String arrivalTime,String ampm)
  {
      DatetimeValidation(arrivalDate,arrivalTime,ampm);
      this.arrivalTime = arrivalDate+" "+arrivalTime+" "+ampm;
  }

  /**
   * setDepartureTime is validating departure date time of flight and setting it to departureTime variable
   * @param departureDate - departure date in format mm/dd/yyyy
   * @param departureTime - departure time (24-hour time)
   */
  public void setDepartureTime(String departureDate,String departureTime,String ampm)
  {
      DatetimeValidation(departureDate,departureTime,ampm);
      this.departureTime = departureDate+" "+departureTime+" "+ampm;
  }


  public static void DatetimeValidation(String date, String time, String ampm) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    String finaldatetime = date + " " + time + " " + ampm;

    try{
      Date d = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      throw  new UnsupportedOperationException("Date should be in MM/dd/yyyy hh:mm am/pm format");

    }
  }

  /**
   * get function for flight number
   * @return - flight number
   */
  @Override
  public int getNumber() {
    return this.flightNumber;
  }

  /**
   * get function for departure airport
   * @return - departure airport
   */
  @Override
  public String getSource() {
    if (source==null)
    {
      throw new UnsupportedOperationException("Source is not set");
    }
    return this.source;
  }

  /**
   * get function for departure time of flight
   * @return - departure time
   */
  @Override
  public String getDepartureString() {
    if (departureTime==null)
    {
      throw new UnsupportedOperationException("Departure time is not set");
    }
    String[] depart = this.departureTime.split("\\s+");
    DatetimeValidation(depart[0],depart[1],depart[2]);
    return this.departureTime;
  }

  /**
   * get function for destination airport
   * @return - destination
   */
  @Override
  public String getDestination()
  {
    if (destination==null)
    {
      throw new UnsupportedOperationException("Destination is not set");
    }
    return this.destination;
  }

  /**
   * get function for arrival time
   * @return - arrival time
   */
  @Override
  public String getArrivalString()
  {
    if (arrivalTime==null)
    {
      throw new UnsupportedOperationException("Arrival time is not set");
    }
    String[] arrivalStr = this.arrivalTime.split("\\s+");
    DatetimeValidation(arrivalStr[0],arrivalStr[1],arrivalStr[2]);
    return this.arrivalTime;
  }
}