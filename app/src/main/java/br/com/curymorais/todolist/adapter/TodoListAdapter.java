package br.com.curymorais.todolist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.curymorais.todolist.R;
import br.com.curymorais.todolist.TodoListContract;
import br.com.curymorais.todolist.data.Tarefa;


public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    public static TodoListContract.View todoListView;
    TodoListContract.ViewModel todoListViewModel;
    Context mctx;
    private List<Tarefa> mList;


    protected class TodoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView viewNome;
        TodoListContract.ViewModel todoListViewModel;

        public TodoListViewHolder(final View itemView, TodoListContract.ViewModel todoListViewModel) {
            super(itemView);
            this.todoListViewModel = todoListViewModel;
            itemView.setOnClickListener(this);
            viewNome = (TextView) itemView.findViewById(R.id.tarefaNome);
        }

        @Override
        public void onClick(View v) {
            Log.i("Teste", mList.get(getAdapterPosition()).getNomeTarefa());
            todoListViewModel.removeTarefa(mList.get(getAdapterPosition()).getNomeTarefa());
            notifyDataSetChanged();
        }
    }

    public TodoListAdapter(Context ctx, List<Tarefa> list, TodoListContract.View todoListView, TodoListContract.ViewModel todoListViewModel) {
        this.mctx = ctx;
        this.mList = list;
        this.todoListView = todoListView;
        this.todoListViewModel = todoListViewModel;
    }

    @Override
    public TodoListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recicler_view_row, viewGroup, false);
        return new TodoListViewHolder(itemView, todoListViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder viewHolder, int i) {
        Tarefa tarefa = mList.get(i);
        viewHolder.viewNome.setText(tarefa.getNomeTarefa());
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else return 0;
    }

    public void setTarefas(List<Tarefa> words){
        mList = words;
        notifyDataSetChanged();
    }

}
