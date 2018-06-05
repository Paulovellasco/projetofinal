package br.iesb.grupo1.projetofinal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.iesb.grupo1.projetofinal.R;

public class JobMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    Double lat;
    Double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_map);

        Bundle b = getIntent().getExtras();
        if (b == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lat = b.getDouble("lat");
            lng = b.getDouble("lng");
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapJob);
        mapFragment.getMapAsync(JobMapActivity.this);
    }

    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.


        LatLng local = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(local)
                .title("Vaga Aqui!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(local));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }


}
