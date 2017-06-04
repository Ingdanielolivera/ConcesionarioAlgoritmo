package com.example.ingdaniel.concesionarioalgoritmo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ING DANIEL on 20/0/2017.
 */

public class AdaptadorAuto extends RecyclerView.Adapter<AdaptadorAuto.AutoViewHolder> {

    private ArrayList<Auto> autos;

    private OnAutoClickListener clickListener;

    public AdaptadorAuto(ArrayList<Auto> autos, OnAutoClickListener clickListener){
        this.autos=autos;
        this.clickListener=clickListener;
    }

    @Override
    public AdaptadorAuto.AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //la forma
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_auto,parent,false);
        return new AutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorAuto.AutoViewHolder holder, int position) {
        //datos
        final Auto a = autos.get(position);
        holder.foto.setImageResource(Integer.parseInt(a.getUrlfoto()));
        holder.placa.setText(a.getPlaca());
        holder.kilometraje.setText(a.getKilometraje());
        holder.color.setText(a.getColor());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnAutoClick(a);
            }
        });

    }



    @Override
    public int getItemCount() {
        return autos.size();
    }


    public static class AutoViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView placa;
        private TextView kilometraje;
        private TextView color;
        private View view;

        public AutoViewHolder(View itemView) {
            super(itemView);
            view =(itemView);
            foto =(ImageView)itemView.findViewById(R.id.foto);
            placa =(TextView)itemView.findViewById(R.id.txtPlaca);
            kilometraje=(TextView)itemView.findViewById(R.id.txtKilometraje);
            color=(TextView)itemView.findViewById(R.id.txtColor);
        }
    }

    public interface OnAutoClickListener{
        void OnAutoClick(Auto p);
    }

}


