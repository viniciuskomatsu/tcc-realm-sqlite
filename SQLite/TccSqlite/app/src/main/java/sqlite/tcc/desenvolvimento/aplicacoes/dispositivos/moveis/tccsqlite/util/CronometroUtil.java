package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.util;

import java.util.Date;

/**
 * Created by Vinicius on 13/08/2016.
 */
public class CronometroUtil {
    private Date tempo1;
    private Long diferenca;
    private static CronometroUtil instance;

    public static CronometroUtil getInstance(){
        if(instance == null)
            instance = new CronometroUtil();
        return instance;
    }

    public synchronized CronometroUtil iniciar(){
        try {
            this.tempo1 = new Date();
        } catch (Exception e){

        }
        return this;
    }

    public synchronized CronometroUtil parar(){
        try{
            Date tempo2 = new Date();
            this.diferenca = tempo2.getTime() - this.tempo1.getTime();
        } catch (Exception e){

        }
        return this;
    }

    public synchronized Long getDiferenca(){
        return this.diferenca;
    }
}