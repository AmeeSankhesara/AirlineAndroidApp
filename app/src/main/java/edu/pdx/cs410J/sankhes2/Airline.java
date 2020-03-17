package edu.pdx.cs410J.sankhes2;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Airline class having airline info
 */
public class Airline extends AbstractAirline {
    /**
     * @param airlineName
     *        name of airline
     */
    String airlineName;

    /**
     * Checking that airline name is not null and setting its value to airline variable
     * @param airlineName
     */
    public Airline(String airlineName)
    {
        if (airlineName.length() == 0)
        {
            throw new UnsupportedOperationException("Please enter airline name");
        }
        this.airlineName = airlineName;
    }

    /**
     * get function for airline name
     * @return - airline name
     */

    @Override
    public String getName() {
        return airlineName;
    }

    List<AbstractFlight> flights = new ArrayList<>();

    /**
     * function to add flight in list of flights
     * @param abstractFlight
     */
    @Override
    public void addFlight(AbstractFlight abstractFlight) {
        flights.add(abstractFlight);
    }

    /**
     * get function to get flights
     * @return - list of flights
     */

    @Override
    public Collection getFlights() {
        return flights;
    }
}