package com.example.avaliacaobim2p1;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.avaliacaobim2p1.Model.Criterio;
import com.example.avaliacaobim2p1.Model.Relatorio;
import com.example.avaliacaobim2p1.Model.Usuario;

@Database(entities = {Usuario.class, Criterio.class, Relatorio.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract AppDao appDao();
    private static AppDataBase instancia;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDataBase.class,
                    "banco_inspecao.db"
            ).build();
        }
        return instancia;
    }
}