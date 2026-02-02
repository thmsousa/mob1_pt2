package com.example.avaliacaobim2p1.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "relatorio")
public class Relatorio implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "relatorio_id")
    public long id;

    @ColumnInfo(name = "pontuacao_final")
    public double pontuacaoFinal;

    @ColumnInfo(name = "data_avaliacao")
    public String data;

    public Relatorio(double pontuacaoFinal, String data) {
        this.pontuacaoFinal = pontuacaoFinal;
        this.data = data;
    }
}