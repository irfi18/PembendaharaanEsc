package com.example.asus.pembendaharaanesc.transaksi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 7/22/2018.
 */

public class transaksiDaoImp extends SQLiteOpenHelper implements transaksiDao {

public transaksiDaoImp(Context context){super(context,"db_bendahara",null,1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_transaksi(idMember INTEGER PRIMARY KEY, namaMember TEXT,tanggal TEXT,bulan TEXT,uang double)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE tbl_transaksi");
    }

    @Override
    public Cursor read() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM tbl_transaksi", null);
    }

    @Override
    public boolean create(transaksi transaksi) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_transaksi VALUES ('" + transaksi.getIdMember() + "','" + transaksi.getNamaMember() + "','" + transaksi.getTanggal() + "','" + transaksi.getBulan() + "','" + transaksi.getUang() +  "')");
        return true;
    }

    @Override
    public boolean update(transaksi transaksi) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tbl_transaksi SET namaMember='" + transaksi.getNamaMember() + "', tanggal='" + transaksi.getTanggal() + "', bulan='" + transaksi.getBulan() + "', uang='" + transaksi.getUang() + "'  WHERE idMember='" + transaksi.getIdMember() + "'");
        return true;
    }

    @Override
    public boolean delete(int idMember) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM tbl_transaksi WHERE idMember='" + idMember + "'");
        return true;
    }
}
