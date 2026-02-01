package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.avaliacaobim2p1.Model.Criterio;

public class CadastroCriterioFragment extends Fragment {
    private EditText edNome, edDesc, edPeso;
    private Button btnSalvar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cadastro_criterio, container, false);

        edNome = v.findViewById(R.id.editNomeCriterio);
        edDesc = v.findViewById(R.id.editDescricaoCriterio);
        edPeso = v.findViewById(R.id.editPesoCriterio);
        btnSalvar = v.findViewById(R.id.btnSalvarCriterio);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeText = edNome.getText().toString();
                String descText = edDesc.getText().toString();
                String pesoStr = edPeso.getText().toString();

                if (!nomeText.isEmpty() && !pesoStr.isEmpty()) {
                    try {
                        double pesoValue = Double.parseDouble(pesoStr);

                        if (pesoValue >= 0.0 && pesoValue <= 2.0) {
                            final Criterio novoCriterio = new Criterio(nomeText, descText, pesoValue);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    AppDataBase.getInstance(getContext()).appDao().cadastrarCriterio(novoCriterio);

                                    if (getActivity() != null) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getContext(), "Critério Salvo!", Toast.LENGTH_SHORT).show();
                                                edNome.setText("");
                                                edDesc.setText("");
                                                edPeso.setText("");
                                            }
                                        });
                                    }
                                }
                            }).start();
                        } else {
                            Toast.makeText(getContext(), "O peso deve ser entre 0 e 2", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Peso inválido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}