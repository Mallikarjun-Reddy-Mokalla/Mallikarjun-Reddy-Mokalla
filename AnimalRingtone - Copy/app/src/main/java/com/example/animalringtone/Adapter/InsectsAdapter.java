package com.example.animalringtone.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalringtone.Data.Insectsadata;
import com.example.animalringtone.Activity.Playscreen;
import com.example.animalringtone.R;

import java.io.IOException;
import java.util.ArrayList;

public class InsectsAdapter extends RecyclerView.Adapter<InsectsAdapter.ViewHolder> {
    Context context;
    ArrayList<Insectsadata> insectsadata;
    MediaPlayer mediaPlayer;
    MediaPlayer oldmediaPlayer;
    InsectsAdapter.ViewHolder oldHolder;
    Activity activity;
    Dialog dialog;

    public InsectsAdapter(Context context, ArrayList<Insectsadata> insectsadata) {
        this.context = context;
        this.insectsadata = insectsadata;
        this.activity = (Activity) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView insect_image, play_icon, pause_icon;
        TextView insect_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            insect_image = itemView.findViewById(R.id.animal_image);
            insect_name = itemView.findViewById(R.id.animal_name);
            play_icon = itemView.findViewById(R.id.play_icon);
            pause_icon = itemView.findViewById(R.id.pause_icon);
        }
    }

    @NonNull
    @Override
    public InsectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.animals_soundsview_list_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsectsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int i) {

        holder.itemView.setTag(insectsadata.get(i));
        holder.insect_name.setText(insectsadata.get(i).getInsectname());
        holder.insect_image.setImageResource(insectsadata.get(i).getInsectimage());
        holder.play_icon.setImageDrawable(context.getDrawable(R.drawable.play_icon));
        holder.pause_icon.setImageDrawable(context.getDrawable(R.drawable.pause_icon));
        holder.pause_icon.setVisibility(View.INVISIBLE);
        holder.play_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaypopDialig();
                mediaPlayer = new MediaPlayer();
                if (oldHolder != null && oldHolder != holder) {
                    oldHolder.pause_icon.setVisibility(View.GONE);
                    oldHolder.play_icon.setVisibility(View.VISIBLE);
                    if (oldmediaPlayer != null) {
                        try {
                            if (oldmediaPlayer.isPlaying()) {
                                oldmediaPlayer.stop();
                            }
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }

                    }
                }
                oldHolder = holder;
                oldmediaPlayer = mediaPlayer;
                try {
                    mediaPlayer.setDataSource("https://wirralvideos.s3.us-west-2.amazonaws.com/animal-sounds/insects/" + insectsadata.get(i).getInsectsound());
//                    PlaypopDialig();
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    holder.play_icon.setVisibility(View.GONE);
                    holder.pause_icon.setVisibility(View.VISIBLE);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            dialog.dismiss();
                            holder.pause_icon.setVisibility(View.GONE);
                            holder.play_icon.setVisibility(View.VISIBLE);
                        }
                    });
                    oldmediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            dialog.dismiss();
                            oldHolder.pause_icon.setVisibility(View.GONE);
                            oldHolder.play_icon.setVisibility(View.VISIBLE);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        });
        holder.pause_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                PauseSound();


            }
        });
        holder.insect_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = ("https://wirralvideos.s3.us-west-2.amazonaws.com/animal-sounds/insects/" + insectsadata.get(i).getInsectsound());

                Intent intent = new Intent(context, Playscreen.class);

                intent.putExtra("name", insectsadata.get(i).getInsectname());
                intent.putExtra("image", insectsadata.get(i).getInsectimage());
                intent.putExtra("Urls", Url);

                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
    }

    private void PauseSound() {
        if (oldmediaPlayer != null) {
            if (mediaPlayer != null) {
//                    oldmediaPlayer.stop();
//                    oldmediaPlayer.release();
                if (oldmediaPlayer.isPlaying()) {
                    oldmediaPlayer.pause();
                    oldmediaPlayer.release();
                    oldmediaPlayer = null;
                }
                oldHolder.play_icon.setVisibility(View.VISIBLE);
                oldHolder.pause_icon.setVisibility(View.INVISIBLE);

                Toast.makeText(context, "Audio is paused", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Audio is not playing", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public int getItemCount() {
        return insectsadata.size();
    }

    public void onBackPressedStopPlayer(boolean backpressed) {
        if (backpressed) {
            try {
                if (mediaPlayer != null || oldmediaPlayer != null) {
                    if (mediaPlayer.isPlaying() || oldmediaPlayer.isPlaying()) {
                        oldmediaPlayer.reset();
                        oldmediaPlayer.stop();
                        oldmediaPlayer.release();
                        oldmediaPlayer = null;
                        oldHolder.pause_icon.setVisibility(View.GONE);
                        oldHolder.play_icon.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception e) {
                //oldMediaPlayer = null;
                e.printStackTrace();
            }
        }

    }

    public void stop(boolean swipe) {
        if (swipe) {
            try {
                if (mediaPlayer != null || oldmediaPlayer != null) {
                    if (mediaPlayer.isPlaying() || oldmediaPlayer.isPlaying()) {
                        oldmediaPlayer.stop();
                        oldmediaPlayer.release();
//                        oldmediaPlayer.pause();
                        oldmediaPlayer = null;
                        oldHolder.pause_icon.setVisibility(View.GONE);
                        oldHolder.play_icon.setVisibility(View.VISIBLE);
                    }
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

        }
    }

    private void PlaypopDialig() {
        dialog = new Dialog(context);
        this.dialog.requestWindowFeature(1);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(R.layout.play_dialog_pop_up);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView stop_sound = dialog.findViewById(R.id.stop_song);
        stop_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null || oldmediaPlayer.isPlaying()) {
                    oldmediaPlayer.pause();
                    oldHolder.play_icon.setVisibility(View.VISIBLE);
                    oldHolder.pause_icon.setVisibility(View.INVISIBLE);
                    dialog.dismiss();

                    Toast.makeText(context, "Audio is paused", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Audio is not playing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
