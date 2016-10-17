package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.R;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Capitulos;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.DAO.CapitulosDAO;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Livros;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.DAO.LivrosDAO;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.util.CronometroUtil;

public class MainActivity extends AppCompatActivity {

    private LivrosDAO livrosDAO;
    private CapitulosDAO capitulosDAO;

    private EditText edtQuantidadeRegistroLivros;
    private EditText edtQuantidadeRegistroCapitulos;
    private Button btnInserts;
    private TextView lblTempoInserts;
    private Button btnDeleteAll;
    private TextView lblTempoDeleteAll;
    private Button btnContadorRegistro;
    private TextView lblRegistroCount;
    private Button btnSelectLivros;
    private TextView lblTempoSelectsLivros;
    private Button btnSelectCapitulos;
    private TextView lblTempoSelectsCapitulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frame);
        this.livrosDAO = LivrosDAO.getInstance();
        this.capitulosDAO = CapitulosDAO.getInstance();

        this.edtQuantidadeRegistroLivros = (EditText) findViewById(R.id.edtQuantidadeRegistroLivros);
        this.edtQuantidadeRegistroCapitulos = (EditText) findViewById(R.id.edtQuantidadeRegistroCapitulos);
        this.btnInserts = (Button) findViewById(R.id.btnInserts);
        this.lblTempoInserts = (TextView) findViewById(R.id.lblTempoInserts);
        this.btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        this.lblTempoDeleteAll = (TextView) findViewById(R.id.lblTempoDeleteAll);
        this.btnContadorRegistro = (Button) findViewById(R.id.btnContadorRegistro);
        this.lblRegistroCount = (TextView) findViewById(R.id.lblRegistroCount);
        this.btnSelectLivros = (Button) findViewById(R.id.btnSelectLivros);
        this.lblTempoSelectsLivros = (TextView) findViewById(R.id.lblTempoSelectsLivros);
        this.btnSelectCapitulos = (Button) findViewById(R.id.btnSelectCapitulos);
        this.lblTempoSelectsCapitulos = (TextView) findViewById(R.id.lblTempoSelectsCapitulos);

        configAction();
    }

    private void configAction(){
        this.btnInserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtdLivrosString = edtQuantidadeRegistroLivros.getText().toString();
                String qtdCapitulosString = edtQuantidadeRegistroCapitulos.getText().toString();
                Long qtdLivros = new Long(Strings.isNullOrEmpty(qtdLivrosString)? "0" : qtdLivrosString);
                Long qtdCapitulos = new Long(Strings.isNullOrEmpty(qtdCapitulosString)? "0" : qtdCapitulosString);

                if(qtdLivros.compareTo(new Long("0")) > 0) {
                    CronometroUtil c = CronometroUtil.getInstance().iniciar();
                    List<Livros> livrosList = new ArrayList<Livros>();
                    Long id = livrosDAO.getNextId();
                    for (int i = 0; i < qtdLivros; i++) {
                        String tituloString = "Titulo ";
                        String autorString = "Autor ";
                        String editoraString = "Editora ";
                        RealmList<Capitulos> capitulosList = new RealmList<Capitulos>();

                        for(int j = 0; j < qtdCapitulos; j++){
                            String titulo = "Capitulo Titulo";
                            String subtitulo = "Capitulo Subtitulo";

                            Capitulos capitulo = new Capitulos();
                            capitulo.setTitulo(titulo);
                            capitulo.setSubtitulo(subtitulo);

                            capitulosList.add(capitulo);
                        }

                        Livros livro = new Livros();
                        livro.setId(id);
                        livro.setTitulo(tituloString);
                        livro.setAutor(autorString);
                        livro.setEditora(editoraString);
                        livro.setCapitulos(capitulosList);

                        livrosList.add(livro);
                        id++;
                    }
                    livrosDAO.add(livrosList);
                    Long tempo = c.parar().getDiferenca();
                    atualizarTempoInserts(qtdLivros, tempo);
                } else {
                    Toast.makeText(getBaseContext(), R.string.msgNecessarioInformarQuantidadeRegistro, Toast.LENGTH_LONG).show();
                }
            }
        });

        this.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long livrosSize = livrosDAO.getCount();
                CronometroUtil c = CronometroUtil.getInstance().iniciar();
                livrosDAO.deleteAll();
                Long tempo = c.parar().getDiferenca();
                atualizarTempoDeleteAll(livrosSize, tempo);
            }
        });

        this.btnContadorRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long livrosSize = livrosDAO.getCount();
                Long capitulosSize = livrosDAO.getCountCapitulos();
                atualizarRegistrosCount(livrosSize, capitulosSize);
            }
        });

        this.btnSelectLivros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CronometroUtil c = CronometroUtil.getInstance().iniciar();
                List<Livros> livrosList = livrosDAO.getList();
                Long tempo = c.parar().getDiferenca();

                atualizarTempoSelectsLivros(Long.valueOf(livrosList.size()), tempo);
            }
        });

        this.btnSelectCapitulos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CronometroUtil c = CronometroUtil.getInstance().iniciar();
                List<Capitulos> capitulosList = capitulosDAO.getList();
                Long tempo = c.parar().getDiferenca();

                atualizarTempoSelectsCapitulos(Long.valueOf(capitulosList.size()), tempo);
            }
        });
    }

    public void atualizarTempoInserts(final Long count, final Long tempo){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTempoInserts.setText(String.format(getResources().getString(R.string.lblTempoInserts), count.toString(), tempo.toString()));
            }
        });
    }

    public void atualizarTempoDeleteAll(final Long count, final Long tempo){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTempoDeleteAll.setText(String.format(getResources().getString(R.string.lblTempoDeleteAll), count.toString(), tempo.toString()));
            }
        });
    }

    public void atualizarRegistrosCount(final Long livros, final Long capitulos){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblRegistroCount.setText(String.format(getResources().getString(R.string.lblRegistroCount), livros.toString(), capitulos.toString()));
            }
        });
    }

    public void atualizarTempoSelectsLivros(final Long livros, final Long tempo){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTempoSelectsLivros.setText(String.format(getResources().getString(R.string.lblTempoSelectsLivros), livros.toString(), tempo.toString()));
            }
        });
    }

    public void atualizarTempoSelectsCapitulos(final Long capitulos, final Long tempo){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTempoSelectsCapitulos.setText(String.format(getResources().getString(R.string.lblTempoSelectsCapitulos), capitulos.toString(), tempo.toString()));
            }
        });
    }
}
