package tech.itik.magpie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue queue;
    private BeaconManager beaconManager;

    private TextView question;
    private Button captureButton;
    private ProgressDialog loadingDialog;

    private Retrofit retrofit;

    private Uri fileUri;

    int themeId;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        question = (TextView) findViewById(R.id.question);
        captureButton = (Button) findViewById(R.id.capture_button);

        captureButton.setOnClickListener(this);
        Intent intent = getIntent();
        themeId = intent.getIntExtra("themeId", 0);

        loadingDialog = new ProgressDialog(MainActivity.this);
        loadingDialog.setCancelable(false);
        loadingDialog.setMessage("Fetching the question...");
        loadingDialog.show();

        retrofit = new Retrofit.Builder().baseUrl(App.host).build();

//        initialiseBeacon();

        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        getQuestion(themeId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }


    public void getQuestion(final int themeId) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, App.host + App.GET_QUESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        loadingDialog.dismiss();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String q = jsonObject.getString("q");
                                    if (!jsonObject.getBoolean("finished")) {
                                        question.setText(q);
                                    } else {
                                        showFinished();
                                    }
                                } catch (Exception e) {
                                    Log.e(App.DEBUG_TAG, e.getMessage());
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Error").setMessage("Something went wrong")
                                            .setCancelable(false).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    }).show();
                                }
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error").setMessage("Something went wrong while fetching the question.")
                        .setCancelable(false).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("username", "aliakbars");
                params.put("themeId", String.valueOf(themeId));

                //returning parameters
                return params;
            }
        };
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri();

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    public void uploadImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, App.host,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loadingDialog.dismiss();
                        //Showing toast message of the response
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String status = jsonObject.getString("status");

                            if (status.equals("correct")) {
                                if (!jsonObject.getBoolean("finished")) {
                                    question.setText(jsonObject.getString("nextQuestion"));
                                } else {
                                    showFinished();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Oops! That is incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            showNotification("Error", ex.getMessage(), false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loadingDialog.dismiss();
                        //Showing toast
                        try {
                            JSONObject jsonObject = new JSONObject(volleyError.getMessage());
                            int responseCode = Integer.parseInt(jsonObject.getString("responseCode"));
                            String response = jsonObject.getString("response");
                            if (responseCode == 1) {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Error: " + response, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Log.e(App.DEBUG_TAG, ex.getMessage());
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Error").setMessage("Something went wrong while uploading your image.")
                                    .setCancelable(false).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).show();
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.25), (int)(bitmap.getHeight()*0.25), true);
                Log.d(App.DEBUG_TAG, String.valueOf(resized.getWidth()));
                Log.d(App.DEBUG_TAG, String.valueOf(resized.getHeight()));
                String image = App.convertBitmap(resized);

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("username", "aliakbars");
                params.put("themeId", "1");
                params.put("image", image);

                //returning parameters
                return params;
            }
        };

        //Adding request to the queue
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            loadingDialog.setMessage("Submitting your answer");
            loadingDialog.show();
            uploadImage();
        }
    }

    private void initialiseBeacon() {
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion region, List<Beacon> list) {
                showNotification("Aha!", "You have found the next question. Ready?", false);
            }

            @Override
            public void onExitedRegion(BeaconRegion region) {

            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new BeaconRegion(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        36593, 63260));
            }
        });
    }

    public void showNotification(String title, String message, boolean showProgress) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        if (showProgress) {
                notification.setProgress(100, 0, true);
        }
//        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }


    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                App.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(App.DEBUG_TAG, "Oops! Failed create "
                        + App.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");



        Log.v(App.DEBUG_TAG, mediaFile.getAbsolutePath());

        return mediaFile;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.capture_button) {
            captureImage();
        }
    }

    public void showFinished() {
        question.setText("You have answered all the questions in this theme. Congrats!");
        captureButton.setVisibility(View.INVISIBLE);
    }
}
