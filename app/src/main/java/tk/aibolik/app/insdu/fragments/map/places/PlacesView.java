package tk.aibolik.app.insdu.fragments.map.places;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public interface PlacesView extends MvpView {

    void setPlaces(List<Place> places);

}
