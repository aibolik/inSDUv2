package tk.aibolik.app.insdu.fragments.publics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.post.Attachment;
import tk.aibolik.app.insdu.models.post.Story;

/**
 * Created by Aibol Kussain on Jun 18, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class StoryAdapter
        extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    ViewHolder.ViewHolderClickListener mListener;
    private String mTitle;
    private List<Story> mStories;
    private LinkedHashMap<Integer, List<Attachment>> mMap;

    public StoryAdapter(String storyTitle, ViewHolder.ViewHolderClickListener listener) {
        mTitle = storyTitle;
        mListener = listener;
        mStories = new ArrayList<>();
        mMap = new LinkedHashMap<>();
    }

    public void setStories(List<Story> stories, LinkedHashMap<Integer, List<Attachment>> map) {
        mStories = stories;
        mMap = map;
    }

    public void addStories(List<Story> stories, LinkedHashMap<Integer, List<Attachment>> map) {
        mStories.addAll(stories);
        mMap.putAll(map);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.story_list_item,
                parent,
                false
        );

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = mStories.get(position);
        List<Attachment> attachments = mMap.get(story.getId());


        holder.title.setText(mTitle);
        if(story.getText().isEmpty()) {
            holder.text.setVisibility(View.GONE);
        }
        else {
            holder.text.setVisibility(View.VISIBLE);
            holder.text.setText(story.getText());
        }
        holder.likeCount.setText(String.valueOf(story.getLikeCount()));
        holder.repostCount.setText(String.valueOf(story.getRepostCount()));
        holder.commentCount.setText(String.valueOf(story.getCommentCount()));

        if(attachments.size() != 0) {
            holder.image.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            if(attachments.size() > 1) {
                holder.metaInfo.setVisibility(View.VISIBLE);
                holder.metaInfo.setText(attachments.size() + " attachments");
            }
            Picasso.with(holder.image.getContext()).
                    load(attachments.get(0).getCover_url())
                    .error(R.drawable.image_error)
                    .into(holder.image);
        }
        else {
            holder.image.setVisibility(View.GONE);
            holder.title.setVisibility(View.GONE);
            holder.metaInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case

        @Bind(R.id.card_title)
        public TextView title;
        @Bind(R.id.card_meta_info)
        public TextView metaInfo;
        @Bind(R.id.card_text)
        public TextView text;
        @Bind(R.id.like_count)
        public TextView likeCount;
        @Bind(R.id.repost_count)
        public TextView repostCount;
        @Bind(R.id.comment_count)
        public TextView commentCount;
        @Bind(R.id.card_image)
        public ImageView image;

        public ViewHolderClickListener mListener;

        public ViewHolder(View v, ViewHolderClickListener listener) {
            super(v);
            v.setOnClickListener(this);
            mListener = listener;
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }

        public interface ViewHolderClickListener {
            void onClick(int position);
        }
    }
}
