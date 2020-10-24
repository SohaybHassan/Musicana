package com.prography.musicana.feature.bottomNavigationViewFragment.home.mapFragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

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

        View mCustomMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_custom, null);
        //marker 1
        LatLng latLngObj = new LatLng(31.283598, 34.252791);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLngObj)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.niki)))
                .anchor(0.5f, 0.5f);
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngObj));

        //marker 2
        LatLng latLngObj2 = new LatLng(40.283598, 28.252791);
        MarkerOptions markerOptions2 = new MarkerOptions()
                .position(latLngObj2)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.niki)))
                .anchor(0.5f, 0.5f);
        mMap.addMarker(markerOptions2);

        // custom widow info
        mMap.setInfoWindowAdapter(this);

        //info window listener
        mMap.setOnInfoWindowClickListener(this);

        // marker listener
        mMap.setOnMarkerClickListener(this);

    }

    private Bitmap getMarkerBitmapFromView(View view, @DrawableRes int resId) {
        ImageView mMarkerImageView = view.findViewById(R.id.image_music);
        mMarkerImageView.setImageResource(resId);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    // custom widow info
    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.map_custom_info, null);

        TextView title = view.findViewById(R.id.map_custom_info_title);
        TextView name = view.findViewById(R.id.map_custom_info_name);
        TextView location = view.findViewById(R.id.map_custom_info_location);
        title.setText("title");
        name.setText("name");
        location.setText("location");

        return view;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.map_custom_info, null);
        return view;
    }

    //info window listener
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), "info window clicked", Toast.LENGTH_SHORT).show();
    }

    // marker listener
    @Override
    public boolean onMarkerClick(Marker marker) {
        Animator animator = (Animator) AnimatorInflater.
                loadAnimator(getActivity(), R.animator.map_marker_animation);

        animator.setTarget(marker);
        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                marker.showInfoWindow();
            }
        });

        return true;
    }


}