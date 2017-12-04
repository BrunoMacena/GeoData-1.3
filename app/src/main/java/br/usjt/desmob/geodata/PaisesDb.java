package br.usjt.desmob.geodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bruno Macena RA 201512094 on 02/12/2017.
 */

public class PaisesDb {
    PaisesDbHelper dbHelper;

    public PaisesDb(Context contexto){
        dbHelper = new PaisesDbHelper(contexto);
    }

    public void inserePaises(Pais[] paises){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();

        for (inserePaises(selecionaPaises()); {
            valores.put(db.Tabela.COLUNA_NAME_NOME, paises.getNome());
            valores.put(bd.Tabela.COLUNA_NAME_REGIAO, paises.getRegiao());
            valores.put(bd.Tabela.COLUNA_NAME_CAPITAL, paises.getCapital());
            valores.put(bd.Tabela.COLUNA_NAME_BANDEIRA, paises.getBandeira());
            valores.put(bd.Tabela.COLUNA_NAME_CODIGO3, paises.getCodigo3());

            long id = bd.insert(BancoDados.Tabela.TABLE_NAME, null, valores);
        }

        db.delete(PaisesContract.PaisEntry.TABLE_NAME, null, null);

        for(Pais pais:paises){
            ContentValues values = new ContentValues();
            values.put(PaisesContract.PaisEntry.COLUMN_NAME_NOME, pais.getNome());
            values.put(PaisesContract.PaisEntry.COLUMN_NAME_REGIAO, pais.getRegiao());
            values.put(PaisesContract.PaisEntry.COLUMN_NAME_CAPITAL, pais.getCapital());
            values.put(PaisesContract.PaisEntry.COLUMN_NAME_BANDEIRA, pais.getBandeira());
            values.put(PaisesContract.PaisEntry.COLUMN_NAME_CODIGO3, pais.getCodigo3());

            db.insert(PaisesContract.PaisEntry.TABLE_NAME, null, values);
        }
    }

    public Pais[] selecionaPaises(){
        ArrayList<Pais> paises = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = { PaisesContract.PaisEntry.COLUMN_NAME_NOME,
                PaisesContract.PaisEntry.COLUMN_NAME_REGIAO,
                PaisesContract.PaisEntry.COLUMN_NAME_CAPITAL,
                PaisesContract.PaisEntry.COLUMN_NAME_BANDEIRA,
                PaisesContract.PaisEntry.COLUMN_NAME_CODIGO3};
        String ordem = PaisesContract.PaisEntry.COLUMN_NAME_NOME;

        Cursor c = db.query(PaisesContract.PaisEntry.TABLE_NAME, colunas, null, null,
                ordem, null, null);
        while(c.moveToNext()) {
            Pais pais = new Pais();
            pais.setNome(c.getString(c.getColumnIndex(PaisesContract.PaisEntry.COLUMN_NAME_NOME)));
            pais.setRegiao(c.getString(c.getColumnIndex(PaisesContract.PaisEntry.COLUMN_NAME_REGIAO)));
            pais.setCapital(c.getString(c.getColumnIndex(PaisesContract.PaisEntry.COLUMN_NAME_CAPITAL)));
            pais.setBandeira(c.getString(c.getColumnIndex(PaisesContract.PaisEntry.COLUMN_NAME_BANDEIRA)));
            pais.setCodigo3(c.getString(c.getColumnIndex(PaisesContract.PaisEntry.COLUMN_NAME_CODIGO3)));

            paises.add(pais);
        }
        c.close();
        if(paises.size()> 0) {
            return paises.toArray(new Pais[0]);
        } else {
            return new Pais[0];
        }
    }
}
