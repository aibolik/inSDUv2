package tk.aibolik.app.insdu.models.places;

import java.util.ArrayList;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class Place {

    public int id;
    public int categoryId;
    public String name;
    public String desc;
    public double lon;
    public double lat;

    public Place() {}

    public Place(int id, int categoryId, String name, String desc, double lon, double lat) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.desc = desc;
        this.lon = lon;
        this.lat = lat;
    }

    public static ArrayList<Place> createPlaces() {
        ArrayList<Place> places = new ArrayList<>();


        places.add(new Place(0, Category.EAT_OUT, "Бамбарбия Кергуду", "Бамбарбия Кергуду", 43.213940, 76.687129));
        places.add(new Place(1, Category.EAT_OUT, "Хамле", "Кондитерски изделия от \"Хамле\"", 43.201570, 76.654680));
        places.add(new Place(2, Category.ATM_BANKS, "Банкомат Центр Кредит", "Банкомат Центр Кредит", 43.215020, 76.682317));

        return places;
    }

}
