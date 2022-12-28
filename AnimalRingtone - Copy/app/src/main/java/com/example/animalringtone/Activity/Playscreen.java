package com.example.animalringtone.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DirectAction;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalringtone.R;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.function.Consumer;

public class Playscreen extends AppCompatActivity implements View.OnClickListener {
    public static final int TYPE_RINGTONE = 1;
    public static final int TYPE_NOTIFICATION = 2;
    public static final int TYPE_ALARM = 4;
    private final int REQUEST_CODE_PERMISSION_CONTACT = 10004;
    private final int REQUEST_CODE_SELECT_PHONE_NUMBER = 10005;
    TextView sound_name;
    ImageView animal_image_sound_type, play_sound_icon, pause_sound_icon, back_to_activity;
    MediaPlayer mediaPlayer;
    MediaPlayer oldmediaPlayer;
    TextView set_ringtone, set_contact_ringtone, set_alram, set_notification, download_ringtone;
    String current_url;
    String presentsound_path;
    String name;
    Dialog dialog;
    String File_Storage_directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/mweditor" + "/";
    int pass_action_view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscreen);
        sound_name = findViewById(R.id.animal_sound_name);
        animal_image_sound_type = findViewById(R.id.animal_image_soundtype);
        play_sound_icon = findViewById(R.id.player_play_icon);
        pause_sound_icon = findViewById(R.id.player_pause_icon);
        back_to_activity = findViewById(R.id.back_to_frag);
        set_ringtone = findViewById(R.id.set_ringtone);
        set_contact_ringtone = findViewById(R.id.set_contact_ringtone);
        set_alram = findViewById(R.id.set_alram);
        set_notification = findViewById(R.id.set_notification);
        download_ringtone = findViewById(R.id.download_ringtone);
        set_ringtone.setOnClickListener(this);
        set_notification.setOnClickListener(this);
        set_alram.setOnClickListener(this);
        set_contact_ringtone.setOnClickListener(this);


        sound_name.setText(getIntent().getExtras().getString("name"));
        animal_image_sound_type.setImageResource(getIntent().getExtras().getInt("image"));

        current_url = getIntent().getStringExtra("Urls");

        if (!Settings.System.canWrite(Playscreen.this)) {
            showSettingsPermissionDialog();
        }
        getFileName(current_url);

        back_to_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Playscreen.this, AnimalRingtone.class);

                try {
                    if (mediaPlayer != null || oldmediaPlayer != null) {
                        if (mediaPlayer.isPlaying() || oldmediaPlayer.isPlaying()) {
                            oldmediaPlayer.reset();
                            oldmediaPlayer.stop();
                            oldmediaPlayer.release();
                            oldmediaPlayer = null;

                        }
                    }
                } catch (Exception e) {
                    //oldMediaPlayer = null;
                    e.printStackTrace();
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
            }

        });
        pause_sound_icon.setVisibility(View.INVISIBLE);
        play_sound_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(Playscreen.this, Uri.parse(current_url));
                oldmediaPlayer = mediaPlayer;
                try {
                    mediaPlayer.start();
                    play_sound_icon.setVisibility(View.INVISIBLE);
                    pause_sound_icon.setVisibility(View.VISIBLE);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            play_sound_icon.setVisibility(View.VISIBLE);
                            pause_sound_icon.setVisibility(View.INVISIBLE);

                        }
                    });
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }


            }

        });
        pause_sound_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldmediaPlayer.pause();
                pause_sound_icon.setVisibility(View.GONE);
                play_sound_icon.setVisibility(View.VISIBLE);
            }
        });
        download_ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File f = new File(File_Storage_directory_path + name);
                if (f.exists()) {
                    Toast.makeText(Playscreen.this, "File already exists", Toast.LENGTH_SHORT).show();
                } else

                    new DownloadFileAsync(current_url).execute();

            }

        });

//        set_ringtone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File f = new File(File_Storage_directory_path + name);
//                if (f.exists()) {
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_RINGTONE);
//                    Toast.makeText(Playscreen.this, "File Downloaded Sucessfully", Toast.LENGTH_SHORT).show();

//                    Toast.makeText(Playscreen.this, "File already exists", Toast.LENGTH_SHORT).show();
//                } else
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_RINGTONE);
//            }
//        });
//        set_notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File f = new File(File_Storage_directory_path + name);
//                if (f.exists()) {
//
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_NOTIFICATION);
////                    Toast.makeText(Playscreen.this, "Notification set Successfully", Toast.LENGTH_SHORT).show();
//
////                    Toast.makeText(Playscreen.this, "File already exists", Toast.LENGTH_SHORT).show();
//                } else
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_NOTIFICATION);
//            }
//        });
//        set_alram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File f = new File(File_Storage_directory_path + name);
//                if (f.exists()) {
//
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_ALARM);
//                } else
//                    SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_ALARM);
//
//            }
//        });
//        set_contact_ringtone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    private void setDownloadDialog() {


        this.dialog = new Dialog(this);
        this.dialog.requestWindowFeature(1);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(R.layout.dialog_download_file);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView cancel_icon = dialog.findViewById(R.id.ic_down_cancel);
        cancel_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DownloadFileAsync downloadFileAsync = (DownloadFileAsync) new DownloadFileAsync(current_url).execute();
//                downloadFileAsync.cancel(true);
//                new DownloadFileAsync(current_url).cancel(true);
                dialog.dismiss();
            }
        });

    }

    private void showSettingsPermissionDialog() {

        dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_write_settings);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView btnDeny = dialog.findViewById(R.id.btn_deny);
        btnDeny.setOnClickListener(view -> {
            Toast.makeText(Playscreen.this, "This permission is mandatory to set ringtone, please allow in settings!", Toast.LENGTH_LONG).show();
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

    private void writePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + Playscreen.this.getApplicationContext().getPackageName()));
            startActivityForResult(intent, 5);
        }

    }

    private void SetAsRingtoneorNotication(File k, int TYPE_OF_RINGTONE) {
        File f = new File(File_Storage_directory_path + name);
        if (!f.exists()) {
            new DownloadFileAsync(current_url).execute();
        } else {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.TITLE, k.getName());
            values.put(MediaStore.MediaColumns.MIME_TYPE, getMIMEType(k.getAbsolutePath()));//// getMIMEType(k.getAbsolutePath())
            values.put(MediaStore.MediaColumns.SIZE, k.length());
            values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
            values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                Uri newUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);

                System.out.println(newUri);

                try (OutputStream os = getContentResolver().openOutputStream(newUri)) {
                    int size = (int) k.length();
                    byte[] bytes = new byte[size];
                    try {
                        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(k));
                        buf.read(bytes, 0, bytes.length);
                        buf.close();
                        os.write(bytes);
                        os.close();
                        os.flush();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (Exception ignored) {
                    System.out.println(ignored);
                }
                RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), TYPE_OF_RINGTONE, newUri);
                switch (TYPE_OF_RINGTONE) {
                    case TYPE_RINGTONE:

                        Toast.makeText(this, "Ringtone set successful..!", Toast.LENGTH_SHORT).show();
                        break;
                    case TYPE_NOTIFICATION:

                        Toast.makeText(this, "Notification sound set successful..!", Toast.LENGTH_SHORT).show();
                        break;
                    case TYPE_ALARM:
                        Toast.makeText(this, "Alarm ringtone set successful..!", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_ringtone:
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_RINGTONE);
                pass_action_view = R.id.set_ringtone;
                break;
            case R.id.set_notification:
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_NOTIFICATION);
                pass_action_view = R.id.set_notification;
                break;
            case R.id.set_alram:
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + name), TYPE_ALARM);
                pass_action_view = R.id.set_alram;
                break;
            case R.id.set_contact_ringtone:
                pass_action_view = R.id.set_contact_ringtone;
                if (isContactPermissionGranted())
                    getContact();
                else
                    askForContactPermission();
                break;

        }

    }


    private void askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(Playscreen.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(Playscreen.this,
                        Manifest.permission.WRITE_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Playscreen.this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.WRITE_CONTACTS}
                                    , REQUEST_CODE_PERMISSION_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(Playscreen.this,
                            new String[]{Manifest.permission.WRITE_CONTACTS},
                            REQUEST_CODE_PERMISSION_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                getContact();
            }
        } else {
            getContact();
        }

    }

    private void getContact() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.Contacts.CONTENT_TYPE);

        startActivityForResult(i, REQUEST_CODE_SELECT_PHONE_NUMBER);
    }


    private boolean isContactPermissionGranted() {
        return ContextCompat.checkSelfPermission(Playscreen.this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_CODE_PERMISSION_CONTACT == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContact();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_SELECT_PHONE_NUMBER == requestCode) {
            setContactRingtone();
//            setContactRingtone(data);
        }
    }

    private void setContactRingtone() {


        ContentValues values = new ContentValues();

        ContentResolver resolver = getContentResolver();

        File file = new File(File_Storage_directory_path + name);
        if (!file.exists()) {
//            new DownloadFileAsync(current_url).execute();
//        }else if (file.exists()){


            Uri oldUri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
            resolver.delete(oldUri, File_Storage_directory_path + name + file.getAbsolutePath() + "\"", null);


            String contact_number = "CONTACT_NUMBER";
            Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, contact_number);

            // The columns used for `Contacts.getLookupUri`
            String[] projection = new String[]{
                    ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY
            };

            Cursor data = getContentResolver().query(lookupUri, projection, null, null, null);

            if (data != null && data.moveToFirst()) {
                data.moveToFirst();
                // Get the contact lookup Uri
                long contactId = data.getLong(0);
                String lookupKey = data.getString(1);
                Uri contactUri = ContactsContract.Contacts.getLookupUri(contactId, lookupKey);

                values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
                values.put(MediaStore.MediaColumns.TITLE, "Beautiful");
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
                values.put(MediaStore.Audio.Media.IS_RINGTONE, true);

                Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
                Uri newUri = resolver.insert(uri, values);


                if (newUri != null) {
                    String uriString = newUri.toString();
                    values.put(ContactsContract.Contacts.CUSTOM_RINGTONE, uriString);
                    Log.e("Uri String for " + ContactsContract.Contacts.CONTENT_URI, uriString);
                    long updated = resolver.update(contactUri, values, null, null);

                    Toast.makeText(Playscreen.this, "Updated : " + updated, Toast.LENGTH_LONG).show();
                }

                data.close();
            }


        } else {
//            new DownloadFileAsync(current_url).execute();
            Toast.makeText(Playscreen.this, "File does not exist", Toast.LENGTH_LONG).show();
        }
    }

//    private void setContactRingtone(Intent data) {
//
//        try {
//            Uri contactData = data.getData();
//            String contactId = contactData.getLastPathSegment();
//            String[] PROJECTION = new String[]{
//                    ContactsContract.Contacts._ID,
//                    ContactsContract.Contacts.DISPLAY_NAME,
//                    ContactsContract.Contacts.HAS_PHONE_NUMBER,
//                    ContactsContract.Contacts.CUSTOM_RINGTONE,
//            };
//
//            Cursor localCursor = getContentResolver().query(contactData, PROJECTION, null, null, null);
//            localCursor.moveToFirst();
//
//            String str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("_id"));
//            String str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("display_name"));
//            Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str1);
//            ContentValues localContentValues = new ContentValues();
//
//            localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
//
//
//            Uri pathUriL = getImageContentUri(Playscreen.this, new File(File_Storage_directory_path + name).getAbsolutePath());
//            localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE, pathUriL.toString());
//
//            getContentResolver().update(localUri, localContentValues, null, null);
//            Toast.makeText(Playscreen.this, "Ringtone assigned to: " + str2, Toast.LENGTH_LONG).show();
//            localCursor.close();
//
//        } catch (Exception ex) {
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//    }

//    private Uri getImageContentUri(Context context, String absPath) {
//
//        Cursor cursor = context.getContentResolver().query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                , new String[]{MediaStore.Audio.Media._ID}
//                , MediaStore.Audio.Media.DATA + "=? "
//                , new String[]{absPath}, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
//            return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Integer.toString(id));
//
//        } else if (!absPath.isEmpty()) {
//            ContentValues values = new ContentValues();
//            values.put(MediaStore.Audio.Media.DATA, absPath);
//            return context.getContentResolver().insert(
//                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
//        } else {
//            return null;
//        }
//    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {
        String song_file_path;
        String song_path;

        public DownloadFileAsync(String song_path) {
            super();
            this.song_path = song_path;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setDownloadDialog();

            if (!Playscreen.this.dialog.isShowing())
                Playscreen.this.dialog.show();

        }

        @Override
        protected void onPostExecute(String songfile_path) {

//             super.onPostExecute(song_file_path);
            System.out.println(File_Storage_directory_path + song_file_path);


            Toast.makeText(Playscreen.this, "File Downloaded Sucessfully", Toast.LENGTH_SHORT).show();
            if (pass_action_view == R.id.set_ringtone) {
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + song_file_path), TYPE_RINGTONE);
            } else if (pass_action_view == R.id.set_notification) {
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + song_file_path), TYPE_NOTIFICATION);
            } else if (pass_action_view == R.id.set_alram) {
                SetAsRingtoneorNotication(new File(File_Storage_directory_path + song_file_path), TYPE_ALARM);
            } else
                Toast.makeText(Playscreen.this, "something Wrong", Toast.LENGTH_SHORT).show();


//            Toast.makeText(Playscreen.this, "File Downloaded Sucessfully", Toast.LENGTH_SHORT).show();
//            dialog.dismiss();

//            SetAsRingtoneorNotication(new File(File_Storage_directory_path + song_file_path), TYPE_NOTIFICATION);
//            Toast.makeText(Playscreen.this, "Notification set Successfully", Toast.LENGTH_SHORT).show();

            dialog.dismiss();


        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
        }

        @Override
        protected String doInBackground(String... sound_path) {
            try {
                //click on set ringtone
                presentsound_path = song_path;
                song_file_path = getFileName(current_url);
                URL url_sound = new URL(presentsound_path);
                URLConnection connection = url_sound.openConnection();
                connection.connect();
                File path = new File(File_Storage_directory_path);
                File file = new File(path, song_file_path);
                if (!path.exists()) {
                    path.mkdirs();
                }
                int contentLength = connection.getContentLength();
                DataInputStream stream = new DataInputStream(url_sound.openStream());
                byte[] buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();
                DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));
                fos.write(buffer);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(song_file_path);
            return song_file_path;
        }
    }

    private String getFileName(String s) {

        int start, end;
        start = s.lastIndexOf('/');
        end = s.length();
        name = s.substring((start + 1), end);
//        name = name;
        System.out.println("Start:" + start + "\t\tEnd:" + end + "\t\tName:" + name);
        System.out.println(name);
        return name;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            if (mediaPlayer != null || oldmediaPlayer != null) {
                if (mediaPlayer.isPlaying() || oldmediaPlayer.isPlaying()) {
                    oldmediaPlayer.reset();
                    oldmediaPlayer.stop();
                    oldmediaPlayer.release();
                    oldmediaPlayer = null;
                }
            }
        } catch (Exception e) {
            //oldMediaPlayer = null;
            e.printStackTrace();
        }
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }


    private String getMIMEType(String url) {
        String mType = null;
        String mExtension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (mExtension != null) {
            mType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mExtension);
            System.out.println(mType);
        }
        return mType;

    }
}


//"https://wirralvideos.s3.us-west-2.amazonaws.com/animal-sounds/ancient-animals/"