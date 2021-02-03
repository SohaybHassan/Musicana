package com.prography.musicana.feature.bottomNavigationViewFragment.profile.view;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prography.musicana.BuildConfig;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentEditProfileBinding;
import com.prography.musicana.custem.SWInterface.OnFragmentInteractionListener;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.viewmodel.ProfileViewModel;
import com.prography.musicana.feature.forgotPassword.ForgotPasswordActivity;
import com.prography.musicana.utils.SWStaticMethods;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment {
    private static final int REQUEST_PERMISSION_CODE_CAMERA = 799;
    private static final int REQUEST_CODE_CAMERA = 999;
    private static final int REQUEST_PERMISSION_CODE_GALLERY = 788;
    private static final int REQUEST_CODE_GALLERY = 888;

    private File file;
    private int count = 0;
    private Uri outputFileUri = null;
    private static final String TAG = "AddCategoryActivity";

    private FragmentEditProfileBinding binding;
    private OnFragmentInteractionListener mListener;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editProfileBackArrow.setOnClickListener(v -> {
            // go back
            mListener.onFragmentInteraction(0);
        });

        binding.edEmail.setEnabled(false);
        binding.edPassword.setEnabled(false);
        binding.edEmail.setOnClickListener(v -> {
            SWStaticMethods.intentWithoutDataAndFinish(getActivity(), ForgotPasswordActivity.class);
        });
        binding.editProfileUserImage.setOnClickListener(v -> {
            chooseUserPhotoFromGallery();
        });

        getdata();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public void getdata() {
        profileViewModel.getData().observe(getActivity(), new Observer<ProfileData>() {
            @Override
            public void onChanged(ProfileData profileData) {
                if (profileData != null) {

                    binding.edName.setText(profileData.getResponse().getData().getUser().getFirstname());
                    binding.edEmail.setText(profileData.getResponse().getData().getUser().getEmail());
                    binding.edPhone.setText(profileData.getResponse().getData().getUser().getPhone());
                    binding.edCountry.setText(profileData.getResponse().getData().getUser().getCountry());
                    binding.edGender.setText(profileData.getResponse().getData().getUser().getGender());
                    binding.edLastName.setText(profileData.getResponse().getData().getUser().getLastname());

                    Log.d(TAG, "onChanged: " + profileData.getResponse().getData().getUser().getFirstname());
                    Log.d(TAG, "onChanged:  we have data here");
                } else {
                    Log.d(TAG, "onChanged:  we  dont have data here");
                }
            }
        });
    }

    public void chooseUserPhotoFromGallery() {
        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE_GALLERY);
            } else {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_CODE_GALLERY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void chooseUserPhotoFromCamera() {
        try {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE_CAMERA);
            } else {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String imageFileName = "JPG_" + timeStamp + "_";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, imageFileName);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                    outputFileUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                } else {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        String imageFileName = timeStamp + ".jpg";
                        File storageDir = Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES);
                        String pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
//                        file = new File(pictureImagePath);

                        outputFileUri = Uri.fromFile(file);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    } else {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        String imageFileName = "JPEG_" + timeStamp + "_";
                        //This is the directory in which the file will be created. This is the default location of Camera photos
                        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DCIM), "Camera");
                        File image = File.createTempFile(
                                imageFileName,  /* prefix */
                                ".jpg",         /* suffix */
                                storageDir      /* directory */
                        );
                        // Save a file: path for using again
                        outputFileUri = Uri.fromFile(image);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", image));

                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    }


                    // cameraFilePath = "file://" + image.getAbsolutePath();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //  Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
                if (outputFileUri != null) {
                    Log.e(TAG, "CapturePhoto: " + outputFileUri + "");
                    String destinationFileName = "DubaiLadPP" + count + ".jpg";
//                    ivUserAvatar.setImageURI(data.getData());
                    count++;
                    Uri destinationUri = Uri.fromFile(new File(getActivity().getCacheDir(), destinationFileName));
                    UCrop.Options options = new UCrop.Options();
                    options.setCompressionQuality(50);
                    options.setCircleDimmedLayer(false);
                    options.setShowCropFrame(false);
                    options.setShowCropGrid(false);
                    options.setToolbarTitle(getString(R.string.crop_image_text));
                    options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                    options.setToolbarColor(getResources().getColor(R.color.colorPrimaryDark));
                    UCrop.of(outputFileUri, destinationUri)
                            .withOptions(options)
                            .withAspectRatio(1, 1)
                            .start(getActivity());
                }

            } else if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    Log.d(TAG, "Image Uri " + imageUri + "");
//                    ivUserAvatar.setImageURI(imageUri);
                    String destinationFileName = "DubaiLadPP" + count + ".jpg";
                    count++;
                    outputFileUri = imageUri;
                    Uri destinationUri = Uri.fromFile(new File(getActivity().getCacheDir(), destinationFileName));
                    UCrop.Options options = new UCrop.Options();
                    options.setCircleDimmedLayer(false);
                    options.setCompressionQuality(100);
                    options.setToolbarTitle(getString(R.string.crop_image_text));
                    options.setShowCropFrame(false);
                    options.setShowCropGrid(false);
                    options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                    options.setToolbarColor(getResources().getColor(R.color.colorPrimaryDark));
                    UCrop.of(imageUri, destinationUri)
                            .withOptions(options)
                            .withAspectRatio(1, 1)
                            .start(getActivity());
                }
            } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
                Uri resultUri = UCrop.getOutput(data);
                if (resultUri != null) {

                    outputFileUri = resultUri;
                    file = new File(outputFileUri.getPath());
                    Bitmap bm = BitmapFactory.decodeFile(resultUri.getPath());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                    binding.editProfileUserImage.setImageBitmap(bm);
                    Glide.with(getContext()).setDefaultRequestOptions(new RequestOptions())
                            .load(bm).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.editProfileUserImage);
                }
            } else if (resultCode == UCrop.RESULT_ERROR) {
                final Throwable cropError = UCrop.getError(data);
                Log.e(TAG, cropError.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        try {
            switch (requestCode) {
                case REQUEST_PERMISSION_CODE_CAMERA: {
                    // If request is cancelled, the result arrays are empty.
                    Log.e("grantResults", grantResults.length + "");
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        // permission was granted.
                        chooseUserPhotoFromCamera();
                    } else {
                        Toast.makeText(getContext(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
                    }
                }
                break;

                case REQUEST_PERMISSION_CODE_GALLERY: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        // permission was granted.
                        chooseUserPhotoFromGallery();
                    } else {
                        Toast.makeText(getContext(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                // other 'case' lines to check for other
                // permissions this app might request.
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}