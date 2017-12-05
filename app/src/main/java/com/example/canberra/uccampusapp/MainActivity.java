package com.example.canberra.uccampusapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private Polygon outlineUC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set icon
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_uclogo);
        toolbar.setOverflowIcon(drawable);

        // Set title
        setTitle("UC Bruce Campus Map");

        // Initialize our map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // Alter map mode based on action selected
        if (id == R.id.action_normal) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        if (id == R.id.action_hybrid) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        if (id == R.id.action_satellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        if (id == R.id.action_terrain) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        if (id == R.id.action_none) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add viewWebsite marker in Sydney and move the camera
        LatLng UC = new LatLng(-35.238165, 149.084119);

        // Initialize our polygon
        outlineUC = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-35.231057, 149.080463),
                        new LatLng(-35.231950, 149.084028),
                        new LatLng(-35.233685, 149.087275),
                        new LatLng(-35.234874, 149.091827),
                        new LatLng(-35.239559, 149.090113),
                        new LatLng(-35.241975, 149.090280),
                        new LatLng(-35.242322, 149.087928),
                        new LatLng(-35.242446, 149.084483),
                        new LatLng(-35.242421, 149.079795),
                        new LatLng(-35.242496, 149.077398),
                        new LatLng(-35.240934, 149.074743),
                        new LatLng(-35.237898, 149.076927),
                        new LatLng(-35.235544, 149.077701),
                        new LatLng(-35.235556, 149.077732),
                        new LatLng(-35.234019, 149.078566),
                        new LatLng(-35.232408, 149.079765)
                )
                .strokeColor(0x2200FFFF)
                .fillColor(0x2200FFFF));

        // Set our polygon to clickable
        outlineUC.setClickable(true);

        // Set our polygon's onClick listener
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {
                // Alter the colour of the outline and make a toast
                outlineUC.setStrokeColor(Color.BLACK);
                Toast.makeText(MainActivity.this, "University of Canberra", Toast.LENGTH_SHORT).show();
            }
        });

        // Create our markers
        final Marker libraryMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.237934, 149.083486))
                .title("UC Library")
                .snippet("24 Hr Access for all Students and Staff.")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_library))
        );

        final Marker ucRefectory = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.238553, 149.084515))
                .title("The Hub")
                .snippet("Below Concourse level between Building 1 and Building 8.")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_hub))
        );

        final Marker ucStudentCentre = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.235980, 149.084029))
                .title("UC Student Centre")
                .snippet("Your gateway to access support and advice.")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_askuc))
        );

        final Marker coffeeGrounds = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.238626, 149.082090))
                .title("UC Student Centre")
                .snippet("The Best Coffe on campus, underneath Cooper Lodge.")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_coffee))
        );

        final Marker ucGym = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.238517, 149.087336))
                .title("UC Gym")
                .snippet("Open to students, staff and the general public")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gym))
        );

        final Marker mainParking = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.240712, 149.084493))
                .title("Main Parking")
                .snippet("Several hundred parks available")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );

        final Marker NATSEM = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.240690, 149.086919))
                .title("NATSEM Centre")
                .snippet("The National Centre for Social and Economic Modelling")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_natsem))
        );

        final Marker gininderraDr = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.233694, 149.087301))
                .title("Gininderra Drive")
                .snippet("Library")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street))
        );

        final Marker universityDrive = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-35.238382, 149.089384))
                .title("University Drive")
                .snippet("Library")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street))
        );

        // Move the camera to selected location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UC, 15));

        // Set our onClick listener for markers
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                // Change to street view of Gininderra drive
                if (marker.getId().equals(gininderraDr.getId())) {
                    viewStreet(marker.getPosition(), "Ginninderra Drive");
                    return true;
                }

                // Change to street view of University drive
                if (marker.getId().equals(universityDrive.getId())) {
                    viewStreet(marker.getPosition(), "University Drive");
                    return true;
                }

                return false;
            }
        });

        // Set the info window adapter
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Setup our info window
                View infoWindow = getLayoutInflater().inflate(R.layout.infowindow_with_image, null);
                TextView title = (TextView) infoWindow.findViewById(R.id.textViewTitle);
                TextView snippet = (TextView) infoWindow.findViewById(R.id.textViewSnippet);
                ImageView image = (ImageView) infoWindow.findViewById(R.id.imageView);

                // Set our info window data based on marker selected
                if (marker.getId().equals(libraryMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_library, getTheme()));
                }

                if (marker.getId().equals(ucRefectory.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_hub, getTheme()));
                }

                if (marker.getId().equals(ucStudentCentre.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_askuc, getTheme()));
                }

                if (marker.getId().equals(coffeeGrounds.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_coffee, getTheme()));
                }

                if (marker.getId().equals(ucGym.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_gym, getTheme()));
                }

                if (marker.getId().equals(mainParking.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_parking, getTheme()));
                }

                if (marker.getId().equals(NATSEM.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_natsem, getTheme()));
                }

                return infoWindow;
            }
        });

        // Set our onClick listener for info windows
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                // Change to website based on marker selected
                if (marker.getId().equals(libraryMarker.getId())) {
                    viewWebsite("http://www.canberra.edu.au/library");
                }
                if (marker.getId().equals(ucStudentCentre.getId())) {
                    viewWebsite("http://www.canberra.edu.au/current-students/canberra-students/student-centre");
                }
                if (marker.getId().equals(coffeeGrounds.getId())) {
                    viewWebsite("http://www.canberra.edu.au/on-campus/accommodation/campus-accommodation#cooper_lodge");
                }
                if (marker.getId().equals(ucRefectory.getId())) {
                    viewWebsite("https://www.canberra.edu.au/maps/buildings-directory/the-hub");
                }
                if (marker.getId().equals(ucGym.getId())) {
                    viewWebsite("http://www.ucunion.com.au/fitness-centre/");
                }
                if (marker.getId().equals(mainParking.getId())) {
                    viewWebsite("https://www.canberra.edu.au/maps/parking");
                }
                if (marker.getId().equals(NATSEM.getId())) {
                    viewWebsite("http://www.natsem.canberra.edu.au/");
                }
            }

        });

    }

    // Change to our WebActivity class with a given url
    private void viewWebsite(String url) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    // Change to our StreetActivity class with a given Latitude/Longitude and title
    private void viewStreet(LatLng latLong, String title) {
        Intent intent = new Intent(this, StreetActivity.class);
        intent.putExtra("lat", latLong.latitude);
        intent.putExtra("lng", latLong.longitude);
        intent.putExtra("title", title);
        startActivity(intent);
    }


}
