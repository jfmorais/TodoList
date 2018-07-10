package br.com.curymorais.todolist.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tarefas_table")
public class Tarefa {

    @NonNull
    @PrimaryKey
    private String nomeTarefa;

    public Tarefa(){

    }

    public Tarefa(@NonNull String word) {this.nomeTarefa = word;}


    public String getNomeTarefa() {
        return this.nomeTarefa;
    }

    public void setNomeTarefa(@NonNull String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
