package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;

/**
 * Created by Vinicius on 09/08/2016.
 */
public class RealmController {
    private static RealmController instance;
    private final Realm REALM;

    private RealmController(){
        this.REALM = Realm.getDefaultInstance();
    }

    public static RealmController getInstance() {
        if (instance == null) {
            instance = new RealmController();
        }
        return instance;
    }

    public Realm getRealm() {
        return REALM;
    }
}
