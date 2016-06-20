package tk.aibolik.app.insdu.models.places;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import tk.aibolik.app.insdu.R;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class Category implements ParentListItem {

    public static final int EAT_OUT = 0;
    public static final int ATM_BANKS = 1;
    public static final int PHARMACIES = 2;
    public static final int HAIRSHOPS = 3;
    public static final int CAR_SERVICES = 4;
    public static final int ENTERTAINMENT = 0;

    public int category;
    public String name;
    public String description;
    public int drawableId;
    public static List<Place> sPlaces;

    public Category(int category, String name, String description, int drawableId) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.drawableId = drawableId;
    }

    public static void setPlaces(List<Place> places) {
        sPlaces = places;
    }

    public static final List<ParentListItem> createCategories() {
        List<ParentListItem> categories = new ArrayList<>();

        categories.add(new Category(0, "Eat out", "Places to eat out around SDU", R.drawable.ic_local_dining));
        categories.add(new Category(1, "ATM and Banks", "ATMs to withdraw money and banks for money services", R.drawable.ic_atm));
        categories.add(new Category(2, "Pharmacies", "Places to buy medicine and consult about your health", R.drawable.ic_pharmacy));
        categories.add(new Category(3, "Hair shops", "Places to have a hair cut", R.drawable.ic_pharmacy));
        categories.add(new Category(4, "Car services", "Gas stations and car washes for auto owners", R.drawable.ic_car));
        categories.add(new Category(5, "Entertainment", "Places to have a rest with friends", R.drawable.ic_entertainment));

        return categories;
    }



    @Override
    public List<Place> getChildItemList() {
        List<Place> childPlaces = new ArrayList<>();
        for(Place place : sPlaces) {
            if(place.categoryId == category) {
                childPlaces.add(place);
            }
        }
        return childPlaces;
    }



    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
