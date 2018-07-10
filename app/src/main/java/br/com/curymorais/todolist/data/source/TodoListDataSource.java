package br.com.curymorais.todolist.data.source;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.curymorais.todolist.data.Tarefa;

public interface TodoListDataSource {
    LiveData<List<Tarefa>> getTarefas();

    Tarefa getTarefa(String taskId);

    void saveTarefa(Tarefa task);

    void deleteTarefas();

    void deleteTarefa(String taskId);
}

