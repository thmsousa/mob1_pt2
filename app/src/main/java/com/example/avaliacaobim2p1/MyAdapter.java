package com.example.avaliacaobim2p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.avaliacaobim2p1.Model.Criterio;

import java.util.List;


public class MyAdapter extends ArrayAdapter<Criterio> {

    public MyAdapter(Context context, List<Criterio> criterios) {
        super(context, 0, criterios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Criterio criterio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_criterio, parent, false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNomeCriterio);
        TextView txtDesc = convertView.findViewById(R.id.txtDescricaoCriterio);
        TextView txtPeso = convertView.findViewById(R.id.txtPesoCriterio);

        if (criterio != null) {
            txtNome.setText(criterio.nome);
            txtDesc.setText(criterio.descricao);
            txtPeso.setText("Peso Máximo: " + criterio.pesoMaximo);
        }

        return convertView;
    }
}