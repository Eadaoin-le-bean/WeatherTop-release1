package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

/**
 * The station class takes the methods built in weather, gives them try/catch responses
 * and gets back the most recent weather data for display on the latest weather section of
 * the dashboard
 *
 * @author Eadaoin
 * @version 1.0 (27.05.2022)
 */
@Entity
public class Station extends Model {
    @OneToMany(cascade = CascadeType.ALL)
    public List<Weather> weatherData = new ArrayList<Weather>();
    public String place;

    /**
     * Read in the name of the station
     *
     * @return station name (place)
     */
    public Station(String place) {
        this.place = place;
    }

    /**
     * Reads in weather data from code in Weather class
     *
     * @return the latest weather conditions
     */

    public String getLatestWeather() {
        String response = "";
        try {
            response = weatherData.get(weatherData.size() - 1).getWeatherFromCode();
        } catch (Exception e) {
            response = "Select folder below to enter weather Data for this station";
        }

        return response;
    }

    /**
     * Reads in the get icon method
     *
     * @return the latest weather icon for display on dashboard
     */

    public String getWeatherIcon() {
        String response = "";
        try {
            response = weatherData.get(weatherData.size() - 1).getIcon();
        } catch (Exception e) {
            response = e.getMessage();//"No icon available for this weather ";
        }
        return response;
    }

    /**
     * Reads in the getPressure method
     *
     * @return the latest pressure for display on dashboard
     */

    public String getLatestPressure() {
        String response = "";
        try {
            response = String.format("%,.2f", weatherData.get(weatherData.size() - 1).getPressure());
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Reads in the getTempInF method from Weather class
     *
     * @return the latest temperature in F for display on dashboard
     */

    public String getLatestTempF() {
        String response = "";
        try {
            response = String.format("%,.2f", weatherData.get(weatherData.size() - 1).getTempInF());
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Reads in the getTemp method from Weather class
     *
     * @return the latest temperature for display on dashboard
     */

    public String getLatestTemp() {
        String response = "";
        try {
            response = String.format("%,.2f", weatherData.get(weatherData.size() - 1).getTemp());
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Reads in the getWindInBeaufort method from Weather class
     *
     * @return the latest wind in beaufort for display on dashboard
     */

    public String getLatestBeaufort() {
        String response = "";
        try {
            response = weatherData.get(weatherData.size() - 1).getWindInBeaufort();
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Reads in the getWDir method from Weather class
     *
     * @return the latest wind direction for display on dashboard
     */

    public String getLatestWDir() {
        String response = "";
        try {
            response = weatherData.get(weatherData.size() - 1).getWDir();
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Reads in the getWChill method from Weather class
     *
     * @return the latest wind chill for display on dashboard
     */
    public String getLatestWindChill() {
        String response = "";
        try {
            response = String.format("%,.2f", weatherData.get(weatherData.size() - 1).getWChill());
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

}


