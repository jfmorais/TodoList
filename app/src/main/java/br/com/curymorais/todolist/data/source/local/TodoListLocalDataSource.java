package br.com.curymorais.todolist.data.source.local;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;
import br.com.curymorais.todolist.data.Tarefa;
import br.com.curymorais.todolist.data.source.TodoListDataSource;
import br.com.curymorais.todolist.database.TodoListDatabase;

// * Concrete implementation to load tasks from the data sources into a DATABASE.
public class TodoListLocalDataSource implements TodoListDataSource {
    private TodoListDao mTodoListDao;
    private LiveData<List<Tarefa>> mAllTarefas;

    public TodoListLocalDataSource(Application application) {
        TodoListDatabase db = TodoListDatabase.getDatabase(application);
        mTodoListDao = db.todoListDao();
        mAllTarefas =  mTodoListDao.getTarefas();
    }

    @Override
    public LiveData<List<Tarefa>> getTarefas() {
        return mAllTarefas;
    }

    @Override
    public Tarefa getTarefa(String taskId) {
        return null;
    }

    @Override
    public void saveTarefa(Tarefa task) {
        new insertAsyncTask(mTodoListDao).execute(task);
    }

    @Override
    public void deleteTarefas() {

    }

    @Override
    public void deleteTarefa(String taskId) {
        new deleteAsyncTask(mTodoListDao).execute(taskId);
    }

    private static class insertAsyncTask extends AsyncTask<Tarefa, Void, Void> {

        private TodoListDao mAsyncTaskDao;

        insertAsyncTask(TodoListDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Tarefa... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void> {

        private TodoListDao mAsyncTaskDao;

        deleteAsyncTask(TodoListDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
