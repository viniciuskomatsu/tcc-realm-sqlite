package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.Capitulos;
import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.Livros;
import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.utils.IPrimaryKeyDAO;
import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.sqlite.SqliteController;

/**
 * Created by vinic on 20/09/2016.
 */
public class CapitulosDAO implements IPrimaryKeyDAO<Capitulos> {
    private static CapitulosDAO instance;
    private final SqliteController controller;

    private CapitulosDAO(Context context){
        this.controller = new SqliteController(context);
    }

    public static CapitulosDAO getInstance(Context context){
        if(instance == null)
            instance = new CapitulosDAO(context);
        return instance;
    }

    @Override
    public void add(List<Capitulos> objects) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        for(Capitulos livro : objects){
            ContentValues valores = new ContentValues();
            valores.put("titulo", livro.getTitulo());
            valores.put("subtitulo", livro.getSubtitulo());
            valores.put("idLivro", livro.getLivro().getId());

            db.insert("capitulos", null, valores);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    @Override
    public void add(Capitulos capitulo) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        ContentValues valores = new ContentValues();
        valores.put("titulo", capitulo.getTitulo());
        valores.put("subtitulo", capitulo.getSubtitulo());
        valores.put("idLivro", capitulo.getLivro().getId());

        db.insert("capitulos", null, valores);
        db.endTransaction();
        db.close();
    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        db.delete("capitulos", "_id = ?", new String[]{id.toString()});
        db.endTransaction();
        db.close();
    }

    @Override
    public Capitulos getById(Long _id) {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        String[] columns = {"_id", "titulo", "subtitulo", "idLivro"};
        String where = "_id = ?";
        String[] whereArgs = {_id.toString()};
        Cursor cursor = db.query("capitulos", columns, where, whereArgs, null, null, null);
        Capitulos capitulo = null;
        if(cursor != null) {
            cursor.moveToFirst();

            Long id = cursor.getLong(0);
            String titulo = cursor.getString(1);
            String subtitulo = cursor.getString(2);
            Long idLivro = cursor.getLong(3);

            capitulo = new Capitulos(id, titulo, subtitulo, null);
        }
        db.close();
        return capitulo;
    }

    @Override
    public List<Capitulos> getList() {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        String[] columns = {"_id", "titulo", "subtitulo", "idLivro"};
        Cursor cursor = db.query("capitulos", columns, null, null, null, null, null);
        List<Capitulos> capitulosList = new ArrayList<Capitulos>();
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                capitulosList.add(new Capitulos(cursor.getLong(0), cursor.getString(1),
                        cursor.getString(2), null));
            } while (cursor.moveToNext());
        }
        db.close();
        return capitulosList;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.controller.getWritableDataBase();

        db.delete("capitulos", null, null);

        db.close();
    }

    @Override
    public Long getCount() {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        Long cnt  = DatabaseUtils.queryNumEntries(db, "capitulos");
        db.close();
        return cnt;
    }
}
