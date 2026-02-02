package com.example.avaliacaobim2p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.avaliacaobim2p1.Model.Relatorio;
import java.util.List;

public class RelatorioAdapter extends ArrayAdapter<Relatorio> {
    public RelatorioAdapter(Context context, List<Relatorio> relatorios) {
        super(context, 0, relatorios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Relatorio r = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView t1 = convertView.findViewById(android.R.id.text1);
        TextView t2 = convertView.findViewById(android.R.id.text2);

        t1.setText("Pontuação: " + r.pontuacaoFinal + " / 10.0");
        t2.setText("Data: " + r.data);

        return convertView;
    }
}