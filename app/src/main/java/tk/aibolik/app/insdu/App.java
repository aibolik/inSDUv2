package tk.aibolik.app.insdu;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by Aibol Kussain on Jun 14, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
    }
}
