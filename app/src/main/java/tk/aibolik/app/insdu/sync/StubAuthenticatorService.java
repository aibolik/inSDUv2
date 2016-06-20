package tk.aibolik.app.insdu.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Aibol Kussain on Jun 21, 2016.
 * Working on "inSDUv2". Smart Digital Solutions
 * You can contact me at: aibolikdev@gmail.com
 * <p/>
 * The service which allows the sync adapter framework to access the authenticator.
 */

public class StubAuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private StubAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new StubAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
