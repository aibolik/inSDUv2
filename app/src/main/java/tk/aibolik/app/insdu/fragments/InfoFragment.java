package tk.aibolik.app.insdu.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.InfoDetailActivity;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.info.InfoItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView infoList;
    private InfoAdapter mInfoAdapter;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfoAdapter = new InfoAdapter(InfoItem.getInfoItemsList(getActivity()), mItemListener);
    }

    InfoAdapter.InfoItemListener mItemListener = new InfoAdapter.InfoItemListener() {
        @Override
        public void onItemClick(InfoItem item) {
            Context context = getActivity();
            Intent intent = new Intent(context, InfoDetailActivity.class);
            intent.putExtra(InfoDetailActivity.EXTRA_INFO_ITEM, item);
            context.startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);

        infoList.setAdapter(mInfoAdapter);
        infoList.setHasFixedSize(true);
        infoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private static class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

        private List<InfoItem> mInfoItems;
        private InfoItemListener mItemListener;

        public InfoAdapter(List<InfoItem> infoItems, InfoItemListener itemListener) {
            mInfoItems = infoItems;
            mItemListener = itemListener;
        }

        public InfoItem getItem(int position) {
            return mInfoItems.get(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.item_info, parent, false);

            return new ViewHolder(itemView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            InfoItem item = mInfoItems.get(position);

            holder.title.setText(item.getTitle());
            holder.description.setText(item.getDescription());
            holder.image.setImageResource(item.getImageResource());
        }

        @Override
        public int getItemCount() {
            return mInfoItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView title;
            public TextView description;
            public ImageView image;

            private InfoItemListener mItemListener;

            public ViewHolder(View itemView, InfoItemListener listener) {
                super(itemView);
                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.card_title);
                description = (TextView) itemView.findViewById(R.id.card_text);
                image = (ImageView) itemView.findViewById(R.id.card_image);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                InfoItem item = getItem(position);
                mItemListener.onItemClick(item);
            }
        }

        public interface InfoItemListener {
            void onItemClick(InfoItem item);
        }
    }

}
