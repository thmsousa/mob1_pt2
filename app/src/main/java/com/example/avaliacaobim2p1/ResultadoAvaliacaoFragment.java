package com.example.avaliacaobim2p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoAvaliacaoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_resultado_avaliacao, container, false);
        TextView txtNota = v.findViewById(R.id.txtNotaFinal);
        Button btnVoltar = v.findViewById(R.id.btnVoltarMenu);

        if (getArguments() != null) {
            double nota = getArguments().getDouble("nota_final");
            txtNota.setText(String.valueOf(nota) + " / 10.0");
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).substituirFragmento(new MenuFragment());
            }
        });
        return v;
    }
}