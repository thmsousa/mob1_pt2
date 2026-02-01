package com.example.avaliacaobim2p1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SupervisorLoginFragment extends Fragment {
    private EditText editTextLogin, editTextSenha;

    public SupervisorLoginFragment() {
    }

    public static SupervisorLoginFragment newInstance() {
        SupervisorLoginFragment fragment = new SupervisorLoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_supervisor_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextSenha = view.findViewById(R.id.editTextSenha);
        Button btnLogar = view.findViewById(R.id.btnLogarSupervisor);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (login.equals("supervisor") && senha.equals("123")) {
                    ((MainActivity) getActivity()).substituirFragmento(new CadastroCriterioFragment());
                } else {
                    new android.app.AlertDialog.Builder(getContext())
                            .setTitle("Erro de Acesso")
                            .setMessage("ACESSO NÃO AUTORIZADO")
                            .setPositiveButton("OK", null)
                            .show();
                }

            }
        });
    }


}