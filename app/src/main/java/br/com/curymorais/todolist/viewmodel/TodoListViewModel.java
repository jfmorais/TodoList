package br.com.curymorais.todolist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.curymorais.todolist.TodoListContract;
import br.com.curymorais.todolist.data.Tarefa;
import br.com.curymorais.todolist.data.source.TodoListDataSource;
import br.com.curymorais.todolist.data.source.local.TodoListLocalDataSource;

public class TodoListViewModel extends AndroidViewModel implements TodoListContract.ViewModel {

//    private final TodoListContract.View mView;
    private final TodoListDataSource mDataSource;
    private LiveData<List<Tarefa>> mAllTarefas;

    public TodoListViewModel(Application application) {
        super(application);
        this.mDataSource = new TodoListLocalDataSource(application);
        mAllTarefas = mDataSource.getTarefas();
    }

    public void addTarefaToList(Tarefa t){
        this.mDataSource.saveTarefa(t);
    }

    public void removeTarefa(String t){
        this.mDataSource.deleteTarefa(t);
    }

    public LiveData<List<Tarefa>> getTarefasList (){
        return mAllTarefas;
    }
}
