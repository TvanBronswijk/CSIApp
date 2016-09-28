package school.csiapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import static android.location.LocationManager.GPS_PROVIDER;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final Switch sca = (Switch)findViewById(R.id.SCA);


        CriminalProvider CP = new CriminalProvider(this);
        final Criminal selected = CP.GetCriminal(getIntent().getIntExtra("criminal", 0));

        final LocationManager LM = (LocationManager) getSystemService(LOCATION_SERVICE);

        sca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationListener LL = new MyLocationListener(getApplicationContext(), selected.lastKnownLocation);
                if(sca.isChecked()){
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    LM.requestLocationUpdates(GPS_PROVIDER, 350, 10, LL);
                }else{
                    LM.removeUpdates(LL);
                }
            }
        });



        Button button = (Button)findViewById(R.id.exit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportActivity.this.finish();
            }
        });
    }

    final class MyLocationListener implements LocationListener {

        Location LastKnown;
        Context context;

        public MyLocationListener(Context context, Location LastKnown){
            this.LastKnown = LastKnown;
            this.context = context;
        }

        @Override
        public void onLocationChanged(Location locFromGps) {
            if(locFromGps.distanceTo(LastKnown) < 10000000000000000000000000000000000000f){
                Toast.makeText(context, "He's close!", Toast.LENGTH_LONG).show();


                Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                v.vibrate(500);

            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // called when the GPS provider is turned off (user turning off the GPS on the phone)
        }

        @Override
        public void onProviderEnabled(String provider) {
            // called when the GPS provider is turned on (user turning on the GPS on the phone)
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // called when the status of the GPS provider changes
        }
    }
}

