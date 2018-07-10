package br.com.curymorais.todolist.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.curymorais.todolist.data.Tarefa;

@Dao
public interface TodoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tarefa tarefa);

    @Query("DELETE FROM tarefas_table WHERE nomeTarefa = :tarefaId")
    void delete(String tarefaId);

    @Query("SELECT * from tarefas_table")
    LiveData<List<Tarefa>> getTarefas();
}
