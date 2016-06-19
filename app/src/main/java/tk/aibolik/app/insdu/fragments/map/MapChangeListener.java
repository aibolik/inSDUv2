package tk.aibolik.app.insdu.fragments.map;

import java.util.List;

import tk.aibolik.app.insdu.models.places.Pin;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public interface MapChangeListener {

    void addPins(List<Pin> pins);
    void showPlace(Pin pin);

}
