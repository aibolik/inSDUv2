package tk.aibolik.app.insdu;

import com.vk.sdk.VKSdk;

import org.litepal.LitePalApplication;

/**
 * Created by Aibol Kussain on Jun 14, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class App extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
    }
}
