package com.example.avaliacaobim2p1.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "usuario",
        indices = {@Index(value = {"login"}, unique = true)}
)
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usuario_id")
    public long id;

    @NonNull
    @ColumnInfo(name = "nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "login")
    public String login;

    @NonNull
    @ColumnInfo(name = "senha")
    public String senha;

    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;

    public Usuario() {
    }

    @Ignore
    public Usuario(@NonNull String nome, @NonNull String login, @NonNull String senha, @NonNull String tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }



}
