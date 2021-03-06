package com.example.eliaspaulino.gileade.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eliaspaulino.gileade.R;
import com.example.eliaspaulino.gileade.models.Evento;
import com.example.eliaspaulino.gileade.models.Lider;
import com.example.eliaspaulino.gileade.utilitarios.Buscador;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Elias on 31/10/2017.
 */

public class EventosAdaptador extends RecyclerView.Adapter<PgAdaptador.Segurador>{
    private static final String SERVER_END_POINT = "eventos";

    private ArrayList<Evento> dados;
    private Context ctx;

    public EventosAdaptador(ArrayList<Evento> dados, Context ctx) {
        this.dados = dados;
        this.ctx = ctx;
    }

    @Override
    public PgAdaptador.Segurador onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_item_eventos, parent, false);
        return new PgAdaptador.Segurador(view);
    }

    @Override
    public void onBindViewHolder(PgAdaptador.Segurador holder, int position) {
        View view = holder.view;
        Buscador<Evento> buscador = new Buscador<>(ctx, SERVER_END_POINT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M - HH:mm");
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descricao = (TextView) view.findViewById(R.id.descricao);
        TextView inicio = (TextView) view.findViewById(R.id.inicio);
        TextView fim = (TextView) view.findViewById(R.id.fim);
        ImageView imagem = (ImageView) view.findViewById(R.id.imagem);

        titulo.setText(dados.get(position).getTitulo().toUpperCase());
        descricao.setText(dados.get(position).getDescricao().toUpperCase());
        inicio.setText(dateFormat.format(dados.get(position).getInicio()));
        fim.setText(dateFormat.format(dados.get(position).getFim()));

        buscador.findImage(dados.get(position).getUrlimagem(), imagem);
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    static class Segurador extends RecyclerView.ViewHolder{
        View view;
        public Segurador(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
