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

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public void getPosts(String pageId, int offset, final boolean loadMore) {
        if (isViewAttached() && !loadMore) {
            getView().setProgress(true);
        }

        final VKRequest request = VKApi.wall().get(
                VKParameters.from(VKApiConst.OWNER_ID, pageId,
                                    VKApiConst.COUNT, 10,
                                    VKApiConst.OFFSET, offset)
        );

        Log.d(TAG, "getPosts: ");
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
                Log.d(TAG, "attemptFailed: " + attemptNumber);
            }

            @Override
            public void onComplete(VKResponse response) {
                if (isViewAttached()) {
                    getView().setProgress(false);
                }
                try {
                    Log.d(TAG, "onComplete: " + response.json);
                    JSONArray items = response.json.getJSONObject("response").getJSONArray("items");
                    List<Story> stories = new ArrayList<Story>();
                    LinkedHashMap<Integer, List<Attachment>> attachmentsMap = new LinkedHashMap<>();

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        JSONArray attachments = item.optJSONArray("attachments");
                        Story story = new Story();
                        List<Attachment> attachmentsList = new ArrayList<Attachment>();
                        for (int j = 0; attachments != null && j < attachments.length(); j++) {
                            JSONObject attachment = attachments.getJSONObject(j);
                            Attachment a = new Attachment();
                            a.setType(attachment.getString("type"));
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
                            if (a.getType().equals(Attachment.TYPE_PHOTO)
                                    || a.getType().equals(Attachment.TYPE_VIDEO)) {
                                attachmentsList.add(a);
                            }
                        }
                        story.setId(item.getInt("id"));
                        story.setDate(item.getLong("date"));
                        story.setText(item.getString("text"));
                        story.setLikeCount(item.getJSONObject("likes").getInt("count"));
                        story.setRepostCount(item.getJSONObject("reposts").getInt("count"));
                        story.setCommentCount(item.getJSONObject("comments").getInt("count"));

                        attachmentsMap.put(story.getId(), attachmentsList);
                        stories.add(story);
                    }

                    if (isViewAttached()) {
                        getView().setStories(stories, attachmentsMap, loadMore);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    public void getLocalPosts() {
//        Log.d(TAG, "getLocalPosts: ");
//        List<Story> allStories = DataSupport.findAll(Story.class);
//        Log.d(TAG, "getLocalPosts: " + allStories.size());
//        for(int i = 0; i < allStories.size(); i++) {
//            Story story = allStories.get(i);
//            Log.d(TAG, "Story: " + story.getId() + "|" + story.getDate());
//            List<Attachment> attachments = story.getAttachments();
//            for(int j = 0; j < attachments.size(); j++) {
//                Attachment a = attachments.get(j);
//                Log.d(TAG, "Attachment: " + a.getType() + "|" + a.getCover_url());
//            }
//        }
//    }

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
