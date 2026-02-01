package com.example.avaliacaobim2p1.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "criterio",
        indices = {@Index(value = {"criterio_nome"}, unique = true)}
)
public class Criterio implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "criterio_id")
    public long id;

    @ColumnInfo(name = "criterio_nome")
    public String nome;

    @ColumnInfo(name = "criterio_descricao")
    public String descricao;

    @ColumnInfo(name = "peso_maximo")
    public double pesoMaximo;

    public Criterio() {
    }

    @Ignore
    public Criterio(@NonNull String nome, String descricao, double pesoMaximo) {
        this.nome = nome;
        this.descricao = descricao;
        this.pesoMaximo = pesoMaximo;
    }
}