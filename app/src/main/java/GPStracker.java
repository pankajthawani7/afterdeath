import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Pankaj Thawani on 25-02-2018.
 */

public class GPStracker implements LocationListener{
    Context context;
    public  GPStracker(Context c){
        context=c;
    }
    @SuppressLint("MissingPermission")
    public Location getLocation() {
        LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean gpsenable = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsenable) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            Toast.makeText(context, "Please Enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
