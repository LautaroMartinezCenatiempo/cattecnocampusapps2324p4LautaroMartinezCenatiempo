package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;

public class GlideUtil {

    // Método para cargar una imagen desde una URL a un ImageView
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.errorimage) // Imagen de carga (mientras se carga la imagen)
                .error(R.drawable.defaultimage) // Imagen de error (si no se puede cargar la imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Estrategia de almacenamiento en caché
                .into(imageView);
    }

    // Método para cargar una imagen desde una URL a un ImageView con opciones personalizadas
    public static void loadImageWithCustomOptions(Context context, String imageUrl, ImageView imageView, RequestOptions options) {
        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(imageView);
    }

    // Método para cargar una imagen circular desde una URL a un ImageView
    public static void loadCircularImage(Context context, String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .circleCrop();

        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(imageView);
    }
}
