package tk.aibolik.app.insdu.fragments.map.places;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.places.Category;
import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PlacesAdapter
        extends ExpandableRecyclerAdapter<PlacesAdapter.CategoryViewHolder, PlacesAdapter.PlaceViewHolder> {

    private LayoutInflater mInflater;

    public PlacesAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.category_item, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public PlaceViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.place_item, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CategoryViewHolder holder, int i, Object o) {
        Category category = (Category) o;
        holder.name.setText(category.name);
        holder.desc.setText(category.description);
    }

    @Override
    public void onBindChildViewHolder(PlaceViewHolder holder, int i, Object o) {
        Place place = (Place) o;
        holder.name.setText(place.name);
        holder.desc.setText(place.desc);
    }

    public class CategoryViewHolder extends ParentViewHolder {

        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.desc)
        public TextView desc;
        @Bind(R.id.icon)
        public ImageView icon;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class PlaceViewHolder extends ChildViewHolder {

        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.desc)
        public TextView desc;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
