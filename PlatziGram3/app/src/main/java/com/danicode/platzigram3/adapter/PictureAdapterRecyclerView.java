package com.danicode.platzigram3.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danicode.platzigram3.R;
import com.danicode.platzigram3.model.Picture;
import com.danicode.platzigram3.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    // Arreglo de mi clase Picture
    private ArrayList<Picture> pictures;
    private int resource; // layout - cardView
    private Activity activity; // activity de donde se llama (ayuda para imagen)

    // Constructor
    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    // Método que permite acceder a todos los layouts
    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        // Lista de elementos - datos
        Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikesNumber());
        Picasso.get().load(picture.getPicture()).into(holder.pictureCard);
        // Cuando se haga clic en la imagen, ir al detalle
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                // Transiciones
                // Validación ==> desde android 5.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                                    view, activity.getString(R.string.transitionname_picture)).toBundle());
                } else {
                    activity.startActivity(intent);
                }
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        // Los views del cardView
        private ImageView pictureCard; // La imagen
        private TextView usernameCard; // usuario
        private TextView timeCard; // Hace tantos días
        private TextView likeNumberCard; // likes

        public PictureViewHolder(View itemView) {
            super(itemView);
            // Asociar los views
            pictureCard = itemView.findViewById(R.id.image_pictureCard);
            usernameCard = itemView.findViewById(R.id.usernameCard);
            timeCard = itemView.findViewById(R.id.timeCard);
            likeNumberCard = itemView.findViewById(R.id.likesNumberCard);
        }
    }
}
