package tk.aibolik.app.insdu.models.post;

/**
 * Created by Aibol Kussain on Jun 14, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class Attachment {

    public static final String TYPE_PHOTO = "photo";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_LINK = "link";

    String type;

    String cover_url;

    String title;
    String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
