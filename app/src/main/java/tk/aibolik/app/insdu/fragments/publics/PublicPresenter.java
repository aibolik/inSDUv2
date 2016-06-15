package tk.aibolik.app.insdu.fragments.publics;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

import tk.aibolik.app.insdu.data.post.Attachment;
import tk.aibolik.app.insdu.data.post.Story;

/**
 * Created by Aibol Kussain on Jun 10, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PublicPresenter extends MvpBasePresenter<PublicView> {

    private static final String TAG = PublicPresenter.class.getSimpleName();

    public void getPosts() {
        Log.d(TAG, "getPosts: ");
        VKRequest request = VKApi.wall().get(
                VKParameters.from(VKApiConst.OWNER_ID, "-53746469", VKApiConst.COUNT, 10)
        );
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                try {
                    Log.d(TAG, "onComplete: " + response.json.toString());
                    JSONArray items = response.json.getJSONObject("response").getJSONArray("items");

                    for(int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        JSONArray attachments = item.getJSONArray("attachments");
                        Story story = new Story();
                        for(int j = 0; j < attachments.length(); i++) {
                            JSONObject attachment = attachments.getJSONObject(j);
                            Attachment a = new Attachment();
                            switch (attachment.getString("type")) {
                                case Attachment.TYPE_PHOTO: {
                                    a.setType(Attachment.TYPE_PHOTO);
                                    a.setCover_url(getBiggestPhotoUrl(attachment.getJSONObject("photo")));
                                    break;
                                }
                                case Attachment.TYPE_VIDEO: {
                                    a.setType(Attachment.TYPE_VIDEO);
                                    a.setCover_url(getBiggestVideoCoverUrl(attachment.getJSONObject("video")));
                                    break;
                                }
                            }

                            a.setStory(story);
                            a.save();
                        }
                        story.setId(item.getInt("id"));
                        story.setDate(item.getLong("date"));
                        story.setText(item.getString("text"));
                        story.setLikeCount(item.getJSONObject("likes").getInt("count"));
                        story.setRepostCount(item.getJSONObject("reposts").getInt("count"));
                        if(story.save()) {
                            Log.d(TAG, "Successfully saved");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getLocalPosts() {
        Log.d(TAG, "getLocalPosts: ");
        List<Story> allStories = DataSupport.findAll(Story.class);
        Log.d(TAG, "getLocalPosts: " + allStories.size());
        for(int i = 0; i < allStories.size(); i++) {
            Story story = allStories.get(i);
            Log.d(TAG, "Story: " + story.getId() + "|" + story.getDate());
            List<Attachment> attachments = story.getAttachments();
            for(int j = 0; j < attachments.size(); j++) {
                Attachment a = attachments.get(j);
                Log.d(TAG, "Attachment: " + a.getType() + "|" + a.getCover_url());
            }
        }
    }

    private String getBiggestPhotoUrl(JSONObject json) {
        String url;
        url = json.optString("photo_604",
                json.optString("photo_130",
                        json.optString("photo_75"))
        );
        return url;
    }

    private String getBiggestVideoCoverUrl(JSONObject json) {
        String url;
        url = json.optString("photo_800",
                json.optString("photo_640",
                        json.optString("photo_320",
                                json.optString("photo_130")))
        );
        return url;
    }

}
