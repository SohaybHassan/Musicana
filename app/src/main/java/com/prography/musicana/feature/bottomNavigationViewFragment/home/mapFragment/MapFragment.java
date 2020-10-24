package com.prography.musicana.feature.bottomNavigationViewFragment.home.mapFragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.BitmapDescriptorFactory;
import com.google.android.libraries.maps.model.CameraPosition;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.Marker;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;
    private GoogleMap mMap;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //marker 1
        LatLng latLngObj = new LatLng(31.283598, 34.252791);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLngObj)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_default))
                .anchor(0.5f, 0.5f);
        Marker marker1 = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngObj));

        //marker 2
        LatLng latLngObj2 = new LatLng(40.283598, 28.252791);
        MarkerOptions markerOptions2 = new MarkerOptions()
                .position(latLngObj2)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_default))
                .anchor(0.5f, 0.5f);
        Marker marker2 = mMap.addMarker(markerOptions2);

        // marker listener
        mMap.setOnMarkerClickListener(marker -> {

            marker.hideInfoWindow();
            Toast.makeText(getActivity(), marker.isInfoWindowShown()+"", Toast.LENGTH_SHORT).show();

//            if (marker.equals(marker1))
//                Toast.makeText(getActivity(), "marker 1", Toast.LENGTH_SHORT).show();
//            else if (marker.equals(marker2))
//                Toast.makeText(getActivity(), "marker 2", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(getActivity(), "none", Toast.LENGTH_SHORT).show();

            Animator animator = (Animator) AnimatorInflater.
                    loadAnimator(getActivity(), R.animator.map_marker_animation);

            animator.setTarget(marker);
            animator.start();

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    marker.hideInfoWindow();
                    Toast.makeText(getActivity(), marker.isInfoWindowShown() + "", Toast.LENGTH_SHORT).show();
                }
            });

            return false;
        });

        //info window listener
        mMap.setOnInfoWindowClickListener(marker -> {
            Toast.makeText(getActivity(), "gggggggggggggggggggggg", Toast.LENGTH_SHORT).show();
            System.out.println("wejdan is so mad");
            marker.hideInfoWindow();
        });

        // custom widow info
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker marker) {
                return getLayoutInflater().inflate(R.layout.map_custom_info, null);
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker marker) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.map_custom_info, null);

                TextView title = v.findViewById(R.id.map_custom_info_title);
                TextView name = v.findViewById(R.id.map_custom_info_name);
                TextView location = v.findViewById(R.id.map_custom_info_location);
                title.setText("wejdan murad");

                return v;
            }

        });
    }
}