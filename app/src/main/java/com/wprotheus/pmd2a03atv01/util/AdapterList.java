package com.wprotheus.pmd2a03atv01.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.wprotheus.pmd2a03atv01.R;
import com.wprotheus.pmd2a03atv01.model.Estudante;

import java.util.List;

public class AdapterList extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Estudante> estudantes;

    public AdapterList(Context context, List<Estudante> estudantes) {
        this.inflater = LayoutInflater.from(context);
        this.estudantes = estudantes;
    }

    @Override
    public int getCount() {
        return estudantes.size();
    }

    @Override
    public Object getItem(int position) {
        return estudantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estudante estudante = estudantes.get(position);
        convertView = inflater.inflate(R.layout.list_layout, null);

        ((TextView) convertView.findViewById(R.id.tvNome)).setText(String.valueOf(estudante.getNome()));
        ((TextView) convertView.findViewById(R.id.tvIdade)).setText(String.valueOf(estudante.getIdade()));
        ((TextView) convertView.findViewById(R.id.tvMedia)).setText(String.valueOf(estudante.getMedia()));
        if (estudante.isSituacao()) {
            ((TextView) convertView.findViewById(R.id.tvSituacao)).setText("Aprovado");
            ((LottieAnimationView) convertView.findViewById(R.id.lavSituacao)).setAnimation(estudante.getImagem());
        } else {
            ((TextView) convertView.findViewById(R.id.tvSituacao)).setText("Reprovado");
            ((LottieAnimationView) convertView.findViewById(R.id.lavSituacao)).setAnimation(estudante.getImagem());
        }
        return convertView;
    }
}