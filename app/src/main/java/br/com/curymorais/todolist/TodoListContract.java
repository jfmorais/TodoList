package br.com.curymorais.todolist;

import android.arch.lifecycle.LiveData;
import android.view.View;

import java.util.List;

import br.com.curymorais.todolist.data.Tarefa;

public interface TodoListContract {

    interface View {
        void onButtonClicked(android.view.View v);
    }

    interface ViewModel{
        void addTarefaToList(Tarefa t);
        void removeTarefa(String t);
        LiveData<List<Tarefa>> getTarefasList ();
    }
}
