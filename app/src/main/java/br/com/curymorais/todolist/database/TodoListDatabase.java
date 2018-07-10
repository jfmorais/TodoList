package br.com.curymorais.todolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.curymorais.todolist.data.Tarefa;
import br.com.curymorais.todolist.data.source.local.TodoListDao;

/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {Tarefa.class}, version = 2 , exportSchema = false)
public abstract class TodoListDatabase extends RoomDatabase {

    private static TodoListDatabase INSTANCE;

    public abstract TodoListDao todoListDao();

    private static final Object sLock = new Object();

    public static TodoListDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodoListDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoListDatabase.class, "todolist_database")//.allowMainThreadQueries().fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
