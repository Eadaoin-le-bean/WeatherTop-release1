package controllers;

import java.util.List;

import models.Station;
import models.Weather;
import play.Logger;
import play.mvc.Controller;

/**
 * The Dashboard class is used primarily to display all information on the dashboard
 *
 * @return all weather data, also adding a station and adding a reading methods are contained here
 */

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Admin");

        List<Station> station = Station.findAll();
        Logger.info("checking the weather: " + station.get(0).weatherData.get(0).getWeatherFromCode());

        render("dashboard.html", station);
    }

    /**
     * Reads in the place string and create a new station object
     *
     * @return the new station object which can be added using the button at end of form
     */
    public static void addStation(String place) {
        Station station = new Station(place);
        station.save();
        Logger.info("Adding Station" + place);
        redirect("/dashboard");
    }

    /**
     * Reads in the instance values set in weather to create a new wetaher object
     *
     * @return new weather object which can be seen in data readings
     */
    public static void addReading(int code, float temp, float wSpeed, float pressure, float direction) {
        Weather reading = new Weather(code, temp, wSpeed, pressure, direction);
        reading.save();
        redirect("/dashboard");
    }

}
