package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.app.TccSqliteHelper;

/**
 * Created by Vinicius on 21/07/2016.
 */
public class SqliteController {
    private SQLiteDatabase db;
    private TccSqliteHelper banco;

    public SqliteController(Context context){
        banco = TccSqliteHelper.getInstance(context);
    }

    public SQLiteDatabase getWritableDataBase(){
        return banco.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDataBase(){
        return banco.getReadableDatabase();
    }
}
