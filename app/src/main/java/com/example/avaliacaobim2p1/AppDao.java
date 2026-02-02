package com.example.avaliacaobim2p1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import com.example.avaliacaobim2p1.Model.Criterio;
import com.example.avaliacaobim2p1.Model.Usuario;
import com.example.avaliacaobim2p1.Model.Relatorio;

@Dao
public interface AppDao {
    @Insert
    void cadastrarUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE login = :login AND senha = :senha LIMIT 1")
    Usuario login(String login, String senha);

    @Insert
    void cadastrarCriterio(Criterio criterio);

    @Query("SELECT * FROM Criterio")
    List<Criterio> listarTodosCriterios();

    @Insert
    void inserirRelatorio(Relatorio relatorio);

    @Query("SELECT * FROM relatorio ORDER BY relatorio_id DESC")
    List<Relatorio> listarTodosRelatorios();

}