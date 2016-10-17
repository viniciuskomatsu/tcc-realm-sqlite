package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vinicius on 21/07/2016.
 */
public class TccSqliteHelper extends SQLiteOpenHelper {
    public static TccSqliteHelper instance;
    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 1;

    public static TccSqliteHelper getInstance(Context context){
        if(instance == null)
            instance = new TccSqliteHelper(context);
        return instance;
    }

    private TccSqliteHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE livros ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo TEXT, "
                + "autor TEXT, "
                + "editora TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE capitulos ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo TEXT, "
                + "subtitulo TEXT, "
                + "idLivro INTEGER,"
                + "FOREIGN KEY (idLivro) REFERENCES livros(_id))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS livros");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS capitulos  ");
        onCreate(sqLiteDatabase);
    }
}
