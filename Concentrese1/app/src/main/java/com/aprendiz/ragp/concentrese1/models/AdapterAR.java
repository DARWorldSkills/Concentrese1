package com.aprendiz.ragp.concentrese1.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aprendiz.ragp.concentrese1.R;
import com.aprendiz.ragp.concentrese1.controllers.Juego;

public class AdapterAR extends RecyclerView.Adapter<AdapterAR.Holder>{
    int [] imagenesJuego;
    int item;
    Context context;
    boolean arreglo = Juego.arreglo1;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void itemClick(int position, ImageView imagen);
    }

    public AdapterAR(int[] imagenesJuego, int item, Context context) {
        this.imagenesJuego = imagenesJuego;
        this.item = item;
        this.context = context;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(imagenesJuego[position]);

    }

    @Override
    public int getItemCount() {
        return imagenesJuego.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView item = itemView.findViewById(R.id.itemJuego);
        public Holder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arreglo = Juego.arreglo1;
                    if (arreglo) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.itemClick(position, item);
                            }
                        }
                    }
                }
            });
        }


        public void connectData(int imagen){
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inSampleSize=1;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),imagen,op);
            item.setImageBitmap(bitmap);
        }



    }
}
