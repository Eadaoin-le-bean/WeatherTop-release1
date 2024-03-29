package controllers;

import java.util.List;

import models.Station;
import models.Weather;
import play.Logger;
import play.mvc.Controller;

public class WeatherDataCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        render("placeWeatherData.html", station);
    }


}

