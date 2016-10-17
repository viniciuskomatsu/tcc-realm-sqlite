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
 * Created by Vinicius on 27/07/2016.
 */
public class LivrosDAO implements IPrimaryKeyDAO<Livros> {
    private static LivrosDAO instance;
    private final SqliteController controller;

    private LivrosDAO(Context context){
        this.controller = new SqliteController(context);
    }

    public static LivrosDAO getInstance(Context context){
        if(instance == null)
            instance = new LivrosDAO(context);
        return instance;
    }

    @Override
    public void add(List<Livros> objects) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        for(Livros livro : objects){
            ContentValues valores = new ContentValues();
            valores.put("titulo", livro.getTitulo());
            valores.put("autor", livro.getAutor());
            valores.put("editora", livro.getEditora());

            Long idLivro = db.insert("livros", null, valores);
            for(Capitulos capitulo : livro.getCapitulosList()){
                ContentValues valoresCapitulo = new ContentValues();
                valoresCapitulo.put("titulo", capitulo.getTitulo());
                valoresCapitulo.put("subtitulo", capitulo.getSubtitulo());
                valoresCapitulo.put("idLivro", idLivro);
                db.insert("capitulos", null, valoresCapitulo);
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    @Override
    public void add(Livros livro) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        ContentValues valores = new ContentValues();
        valores.put("titulo", livro.getTitulo());
        valores.put("autor", livro.getAutor());
        valores.put("editora", livro.getEditora());

        Long idLivro = db.insert("livros", null, valores);
        for(Capitulos capitulo : livro.getCapitulosList()){
            ContentValues valoresCapitulo = new ContentValues();
            valoresCapitulo.put("titulo", capitulo.getTitulo());
            valoresCapitulo.put("subtitulo", capitulo.getSubtitulo());
            valoresCapitulo.put("idLivro", idLivro);
            db.insert("capitulos", null, valoresCapitulo);
        }

        db.endTransaction();
        db.close();
    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase db = this.controller.getWritableDataBase();
        db.beginTransaction();

        db.delete("livros", "_id = ?", new String[]{id.toString()});
        db.delete("capitulos", "idLivro = ?", new String[]{id.toString()});

        db.endTransaction();
        db.close();
    }

    @Override
    public Livros getById(Long _id) {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        String[] columns = {"_id", "titulo", "autor", "editora"};
        String where = "_id = ?";
        String[] whereArgs = {_id.toString()};
        Cursor cursor = db.query("livros", columns, where, whereArgs, null, null, null);
        Livros livro = null;
        if(cursor != null) {
            cursor.moveToFirst();

            Long id = cursor.getLong(0);
            String titulo = cursor.getString(1);
            String autor = cursor.getString(2);
            String editora = cursor.getString(3);
            livro = new Livros(id, titulo, autor, editora, null);
        }
        db.close();
        return livro;
    }

    @Override
    public List<Livros> getList() {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        String[] columns = {"_id", "titulo", "autor", "editora"};
        Cursor cursor = db.query("livros", columns, null, null, null, null, null);
        List<Livros> livrosList = new ArrayList<Livros>();
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Livros livro = new Livros(cursor.getLong(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), null);
                livrosList.add(livro);
                livro = null;
            } while (cursor.moveToNext());
        }
        db.close();
        return livrosList;
    }

    @Override
    public Long getCount() {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        Long cnt  = DatabaseUtils.queryNumEntries(db, "livros");
        db.close();
        return cnt;
    }

    public Long getCountCapitulos() {
        SQLiteDatabase db = this.controller.getReadableDataBase();
        Long cnt = DatabaseUtils.queryNumEntries(db, "capitulos");
        db.close();
        return cnt;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.controller.getWritableDataBase();

        db.delete("livros", null, null);
        db.delete("capitulos", null, null);

        db.close();
    }
}
