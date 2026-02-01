package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.avaliacaobim2p1.Model.Usuario;

public class AvaliadorLoginFragment extends Fragment {
    private EditText edLog, edSen;
    private Button btnEntrar;
    private TextView txtCadastro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_avaliador_login, container, false);

        edLog = v.findViewById(R.id.editLoginAvaliador);
        edSen = v.findViewById(R.id.editSenhaAvaliador);
        btnEntrar = v.findViewById(R.id.btnLogarAvaliador);
        txtCadastro = v.findViewById(R.id.txtIrParaCadastro);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = edLog.getText().toString();
                final String senha = edSen.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Usuario logado = AppDataBase.getInstance(getContext()).appDao().login(login, senha);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (logado != null) {
                                    if (logado.tipo.equals("SUPERVISOR")) {
                                        ((MainActivity) getActivity()).substituirFragmento(new CadastroCriterioFragment());
                                    } else {
                                        ((MainActivity) getActivity()).substituirFragmento(new AvaliacaoCriteriosFragment());
                                    }
                                } else {
                                        Toast.makeText(getContext(), "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        });
                    }
                }).start();
            }
        });

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).substituirFragmento(new AvaliadorCadastroFragment());
            }
        });

        return v;
    }
}