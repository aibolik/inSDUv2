package tk.aibolik.app.insdu.models.places;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class Category implements ParentObject {

    public static final int EAT_OUT = 0;
    public static final int ATM_BANKS = 1;
    public static final int PHARMACIES = 2;
    public static final int HAIRSHOPS = 3;
    public static final int CAR_SERVICES = 4;
    public static final int ENTERTAINMENT = 0;

    public int category;
    public String name;
    public String description;

    public Category(int category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public static final List<ParentObject> createCategories() {
        List<ParentObject> categories = new ArrayList<>();

        categories.add(new Category(0, "Eat out", "Places to eat out around SDU"));
        categories.add(new Category(1, "ATM and Banks", "ATMs to withdraw money and banks for money services"));
        categories.add(new Category(2, "Pharmacies", "Places to buy medicine and consult about your health"));
        categories.add(new Category(3, "Hair shops", "Places to have a hair cut"));
        categories.add(new Category(4, "Car services", "Gas stations and car washes for auto owners"));
        categories.add(new Category(5, "Entertainment", "Places to have a rest with friends"));

        return categories;
    }

    @Override
    public List<Object> getChildObjectList() {
        ArrayList<Place> places = Place.createPlaces();
        List<Object> childPlaces = new ArrayList<>();
        for(Place place : places) {
            if(place.categoryId == category) {
                childPlaces.add(place);
            }
        }
        return childPlaces;
    }

    @Override
    public void setChildObjectList(List<Object> list) {

    }
}
