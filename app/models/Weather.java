/**
 * The Weather Class handles the majority of the
 * methods for the WeatherTop Dashboard
 *
 * @author Eadaoin Doyle
 * @version 1.0 (27.05.2022)
 */

package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.Entity;

import java.util.HashMap;


@Entity
public class Weather extends Model {

    public int code;
    public float temp;
    public float wSpeed;
    public float pressure;
    public float wDir;

    /**
     * The hashmap used to display icons on dashboard
     * <p>
     * int is used to set default values of icons fromagainst the weather code.
     *
     * @return a string which contains the HTML for said icons and displays icons on dashboard
     */
    static HashMap<Integer, String> weatherIcon = new HashMap<Integer, String>() {
        {
            put(100, "large sun outline icon yellow");
            put(200, "large cloud sun icon olive");
            put(300, "large cloud icon teal");
            put(400, "large cloud sun rain icon teal");
            put(500, "large cloud showers heavy icon red");
            put(600, "large cloud rain icon blue");
            put(700, "large snowflake outline icon white");
            put(800, "large bolt icon orange");

        }
    };

    public Weather(int code, float temp, float wSpeed, float pressure, float direction) {

        this.code = code;
        this.temp = temp;
        this.wSpeed = wSpeed;
        this.pressure = pressure;
        this.wDir = direction;
    }

    /**
     * The switch statement used to associate latest weather string with a code of type int
     * using data stored in YAML files
     *
     * @return a string indicating latest weather on the dashboard
     */
    public String getWeatherFromCode() {

        switch (code) {
            case 100:
                return "Clear";
            case 200:
                return "Partial Clouds";
            case 300:
                return "Cloudy";
            case 400:
                return "Light Showers";
            case 500:
                return "Heavy Showers";
            case 600:
                return "Rain";
            case 700:
                return "Snow";
            case 800:
                return "Thunder";
            default:
                return "Code Not Recognised";

        }
    }

    /**
     * Calculate the wind speed from kmph to beaufort
     *
     * @return a string showing wind in beaufort on dashboard
     */
    public String getWindInBeaufort() {
        int w = (int) wSpeed;
        if (w < 1) {
            return "0 bft";
        } else if (w > 1 && w <= 5) {
            return "1 bft";
        } else if (w > 5 && w <= 11) {
            return "2 bft";
        } else if (w > 11 && w <= 19) {
            return "3 bft";
        } else if (w > 19 && w <= 28) {
            return "4 bft";
        } else if (w > 28 && w <= 38) {
            return "5 bft";
        } else if (w > 38 && w <= 49) {
            return "6 bft";
        } else if (w > 49 && w <= 61) {
            return "7 bft";
        } else if (w > 61 && w <= 74) {
            return "8 bft";
        } else if (w > 75 && w <= 88) {
            return "9 bft";
        } else if (w > 88 && w <= 102) {
            return "10 bft";
        } else if (w > 102 && w <= 117) {
            return "11 bft";
        }
        return null;
    }

    /**
     * Display pressure in hpa
     *
     * @return a string showing pressure on dashboard
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * Get temperature which is read in as celsius in fahrenheit
     *
     * @return temperature in fahrenheit
     */
    public float getTempInF() {
        float f;
        f = temp * 9 / 5 + 32;
        return f;
    }

    /**
     * Display temperature in celcius
     *
     * @return temperature in celcius
     */
    public float getTemp() {
        return temp;
    }

    /**
     * Get the wind direction depending on degrees read into data file
     *
     * @return wind direction in North, South, East, West
     */
    public String getWDir() {

        double d = wDir;
        if (d > 11.25 && d <= 33.75) {
            return "North North-East";
        } else if (d > 33.75 && d <= 56.25) {
            return "North-East";
        } else if (d > 56.25 && d <= 78.75) {
            return "East North-East";
        } else if (d > 78.75 && d <= 101.25) {
            return "East";
        } else if (d > 101.25 && d <= 123.75) {
            return "East South-East";
        } else if (d > 123.75 && d <= 146.25) {
            return "South-East";
        } else if (d > 146.25 && d <= 168.75) {
            return "South South-East";
        } else if (d > 168.75 && d <= 191.25) {
            return "South";
        } else if (d > 191.25 && d <= 213.75) {
            return "South South-West";
        } else if (d > 213.75 && d <= 236.25) {
            return "South-West";
        } else if (d > 236.25 && d <= 258.75) {
            return "West South-West";
        } else if (d > 258.75 && d <= 281.25) {
            return "West";
        } else if (d > 281.25 && d <= 303.75) {
            return "West North-West";
        } else if (d > 303.75 && d <= 326.25) {
            return "North-West";
        } else if (d > 325.25 && d <= 346.75) {
            return "North North-West";
        } else {
            return "North";

        }
    }

    /**
     * Get wind chill using speed of wind and current temperature
     *
     * @return wind chill
     */
    public double getWChill() {
        double windChill;
        double vPow = Math.pow(wSpeed, 0.16);
        windChill = ((13.12 + (0.6215 * temp)) - (11.37 * vPow)) + (0.3965 * temp * vPow);
        return windChill;
    }

    /**
     * pulls the icon from the hashmap above
     *
     * @return the icon html which will be displayed on the dashboard
     */
    public String getIcon() {
        Logger.info("Is hashmap null on retrieve " + (weatherIcon == null));
        String iconHTML = weatherIcon.get(code);


        return iconHTML;
    }
}


