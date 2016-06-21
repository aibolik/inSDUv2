package tk.aibolik.app.insdu.navigation.map.places;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
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
    private PlacesClickListener mListener;

    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public PlacesAdapter(Context context,
                         @NonNull List<? extends ParentListItem> parentItemList,
                         PlacesClickListener listener) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
        mListener = listener;
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
    public void onBindParentViewHolder(CategoryViewHolder holder, int position, ParentListItem item) {
        final Category category = (Category) item;
        holder.name.setText(category.name);
        holder.desc.setText(category.description);
        holder.icon.setImageResource(category.drawableId);
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCategoryClick(category.category);
            }
        });
    }

    @Override
    public void onBindChildViewHolder(PlaceViewHolder holder, int i, Object o) {
        final Place place = (Place) o;
        holder.name.setText(place.name);
        holder.desc.setText(place.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlaceClick(place.id);
            }
        });
    }


    public class CategoryViewHolder extends ParentViewHolder {

        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.desc)
        public TextView desc;
        @Bind(R.id.icon)
        public ImageView icon;
        @Bind(R.id.map)
        public ImageView map;

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

    public interface PlacesClickListener {
        void onCategoryClick(int categoryId);
        void onPlaceClick(int placeId);
    }
}
