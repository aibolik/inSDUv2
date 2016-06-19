package tk.aibolik.app.insdu.fragments.publics;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.LinkedHashMap;
import java.util.List;

import tk.aibolik.app.insdu.models.post.Attachment;
import tk.aibolik.app.insdu.models.post.Story;

/**
 * Created by Aibol Kussain on Jun 10, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public interface PublicView extends MvpView {

    void showMessage(String msg);
    void setProgress(boolean active);
    void showErrorText(String text);
    void setStories(List<Story> storiesList, LinkedHashMap<Integer, List<Attachment>> map, boolean loadMore);

}
