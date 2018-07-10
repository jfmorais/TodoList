package br.com.curymorais.todolist.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.curymorais.todolist.R;
import br.com.curymorais.todolist.TodoListContract;
import br.com.curymorais.todolist.viewmodel.TodoListViewModel;
import br.com.curymorais.todolist.adapter.TodoListAdapter;
import br.com.curymorais.todolist.data.Tarefa;

public class MainActivity extends AppCompatActivity implements TodoListContract.View {

    EditText tarefa;
    Button adicionarTarefa;

    private RecyclerView tarefasReciclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    TodoListAdapter adapter;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private TodoListContract.ViewModel mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtendo os objetos do xml
        tarefa = findViewById(R.id.tarefaId);
        tarefasReciclerView = findViewById(R.id.listaTarefaId);
        adicionarTarefa = findViewById(R.id.botaoAdicionarId);

        //criando o Presenter
        mPresenter = ViewModelProviders.of(this).get(TodoListViewModel.class);
        mPresenter.getTarefasList().observe(this, new Observer<List<Tarefa>>() {
            @Override
            public void onChanged(@Nullable final List<Tarefa> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setTarefas(words);
            }
        });

        //Configurando a recyclerView e o adapter
        mLayoutManager = new LinearLayoutManager(this);
        tarefasReciclerView.setLayoutManager(mLayoutManager);
        adapter = new TodoListAdapter(this, listaDeTarefas,this, mPresenter);//TODO
        tarefasReciclerView.setAdapter(adapter);

    }

    public void onButtonClicked(View v){
        Tarefa t = new Tarefa(tarefa.getText().toString());
        listaDeTarefas.add(t);
        adapter.notifyDataSetChanged();
        mPresenter.addTarefaToList(t);
    }

}
