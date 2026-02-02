package com.example.avaliacaobim2p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;

import com.example.avaliacaobim2p1.Model.Criterio;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Criterio> {

    public MyAdapter(Context context, List<Criterio> criterios) {
        super(context, 0, criterios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Criterio criterio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_criterio, parent, false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNomeCriterio);
        TextView txtDesc = convertView.findViewById(R.id.txtDescricaoCriterio);
        TextView txtPeso = convertView.findViewById(R.id.txtPesoCriterio);

        RadioGroup rgNotas = convertView.findViewById(R.id.rgNotas);
        final RadioButton rb0 = convertView.findViewById(R.id.rbNota0);
        final RadioButton rb1 = convertView.findViewById(R.id.rbNota1);
        final RadioButton rb2 = convertView.findViewById(R.id.rbNota2);

        if (criterio != null) {
            txtNome.setText(criterio.nome);
            txtDesc.setText(criterio.descricao);
            txtPeso.setText("Peso Máximo: " + criterio.pesoMaximo);

            rgNotas.setOnCheckedChangeListener(null);
            rgNotas.clearCheck();

            if (criterio.notaAtribuida == 1.0) {
                rb1.setChecked(true);
            } else if (criterio.notaAtribuida == 2.0) {
                rb2.setChecked(true);
            } else {
                rb0.setChecked(true);
            }

            rgNotas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    double notaDesejada = 0.0;

                    if (checkedId == R.id.rbNota1) {
                        notaDesejada = 1.0;
                    } else if (checkedId == R.id.rbNota2) {
                        notaDesejada = 2.0;
                    }

                    if (notaDesejada > criterio.pesoMaximo) {
                        criterio.notaAtribuida = 0.0;
                        rb0.setChecked(true);

                        new AlertDialog.Builder(getContext())
                                .setTitle("Nota Excedida")
                                .setMessage("A nota máxima permitida para " + criterio.nome + " é " + criterio.pesoMaximo)
                                .setPositiveButton("OK", null)
                                .show();
                    } else {
                        criterio.notaAtribuida = notaDesejada;
                    }
                }
            });
        }

        return convertView;
    }
}