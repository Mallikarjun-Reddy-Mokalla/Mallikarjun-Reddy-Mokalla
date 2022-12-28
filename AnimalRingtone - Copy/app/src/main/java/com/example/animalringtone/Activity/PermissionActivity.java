package com.example.animalringtone.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalringtone.R;


public class PermissionActivity extends AppCompatActivity {

    private static final String TAG = "PermissionActivity";
    ImageView next;
    TextView textView;
    private static final int READ_WRITE_REQUEST_CODE = 10001;
    private static final int WRITE_SETTINGS_REQUEST_CODE = 10002;
    private Dialog dialog;

    String writeText = "<p>The permission to<strong> access photos, media, and files</strong> is requested because the ringtones are downloaded and stored in the device's storage.</p>";
    // String settingsText = "<p>The <strong>setting ringtone permission</strong> is requested to set the selected tone/song as the mobile phone ringtone.</p>";
//    String permission= "<br></br> <h2>Setup the permissions</h2>\n" +
//            "<br></br><p>The <strong><u><em>FreeRingToneApp</em></u></strong> requires the following permissions, without the access of the permissions app functionality does not work.</p>\n" +
//            "<br></br><p>The permission to<strong> access photos, media, and files</strong> is requested because the ringtones are downloaded and stored in the device's storage.</p>\n" +
//            "<br></br><p>The <strong>setting ringtone permission</strong> is requested to set the selected tone/song as the mobile phone ringtone.</p>";

    //  String permission= "<br></br><p>The permission to<strong> access photos, media, and files</strong> is requested because the ringtones are downloaded and stored in the device's storage.</p>\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        textView = findViewById(R.id.textview2);
//text
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            textView.setText(Html.fromHtml(writeText, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            textView.setText(Html.fromHtml(writeText));
//        }

//        if (ContextCompat.checkSelfPermission(PermissionActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            goToMain();
//        }

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PermissionActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(PermissionActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    if (!Settings.System.canWrite(PermissionActivity.this)) {
                        showSettingsPermissionDialog();
                    } else {
                        goToMain();
                    }
                } else {
                    requestStoragePermission();
                }
            }
        });

    }

    private void goToMain() {
        Intent intent = new Intent(PermissionActivity.this, AnimalRingtone.class);
        startActivity(intent);
        finish();
    }

    protected void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PermissionActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, READ_WRITE_REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, READ_WRITE_REQUEST_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_WRITE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                if (!Settings.System.canWrite(PermissionActivity.this)) {
                    showSettingsPermissionDialog();
                } else goToMain();

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == WRITE_SETTINGS_REQUEST_CODE) {
            goToMain();
        }
    }

    public void showSettingsPermissionDialog() {
        this.dialog = new Dialog(this);
        this.dialog.requestWindowFeature(1);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(R.layout.dialog_write_settings);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);
        //this.squareProgressBar = this.dialog.findViewById(R.id.progress_download_video);
        //this.squareProgressBar.setProgress(0);
//        TextView textView = dialog.findViewById(R.id.tv_permission_txt);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            textView.setText(Html.fromHtml(settingsText, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            textView.setText(Html.fromHtml(settingsText));
//        }
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView btnDeny = dialog.findViewById(R.id.btn_deny);
        btnDeny.setOnClickListener(view -> {
            Toast.makeText(PermissionActivity.this, "This permission is mandatory to set ringtone, please allow in settings!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        });

        ImageView btnAllow = dialog.findViewById(R.id.btn_allow);
        btnAllow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                writePermission();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
//        if (!Settings.System.canWrite(PermissionActivity.this)) {
//            showSettingsPermissionDialog();
//            //writePermission();
//        }
//        else {
//            goToMain();
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void writePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + PermissionActivity.this.getApplicationContext().getPackageName()));
            startActivityForResult(intent, WRITE_SETTINGS_REQUEST_CODE);
        }

    }

}
