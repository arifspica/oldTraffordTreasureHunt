package tech.itik.magpie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * Created by aliakbars on 11/20/13.
 * Modified by aliakbars on 02/21/16.
 */
public class App {

    public static SharedPreferences preferences;

    public static String host = "http://8c861922.ngrok.io";
    public static final String GET_QUESTION = "/get_question";
    public static final String UPLOAD_IMAGE = "/image";
    public static final String HOST_KEY = "tech.titikkoma.magpie";
    public static final String IMAGE_DIRECTORY_NAME = "Magpie";

    public static String DEBUG_TAG = "tkd";

    public static String get(View v) {
        if (v instanceof EditText) {
            return ((EditText) v).getText().toString();
        } else if (v instanceof TextView) {
            return ((TextView) v).getText().toString();
        } else {
            throw new UnsupportedOperationException("Getting data from " + v.toString() + " is not implemented.");
        }
    }

    public static String convertBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static void showServerSettings(Context c) {
        final EditText ipAddressInput = new EditText(c);
        ipAddressInput.setHint("http://192.168.0.1");
        ipAddressInput.setText(App.host);
        new AlertDialog.Builder(c)
                .setTitle("Server")
                .setMessage("URL or IP address:")
                .setView(ipAddressInput)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.host = String.valueOf(ipAddressInput.getText());
                        App.preferences.edit().putString(App.HOST_KEY, App.host).commit();
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }
}

