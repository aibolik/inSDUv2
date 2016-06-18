package tk.aibolik.app.insdu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.SocketException;

/**
 * Created by Aibol Kussain on Jun 18, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class Utility {

    public static void checkInternetConnection(Context context) throws SocketException {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (!(activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting())) {
            throw new SocketException(context.getString(R.string.error_message_no_network_connection));
        }
    }

}
