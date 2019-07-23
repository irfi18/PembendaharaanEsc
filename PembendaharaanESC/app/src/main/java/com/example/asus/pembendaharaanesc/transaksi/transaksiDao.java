package com.example.asus.pembendaharaanesc.transaksi;

import android.database.Cursor;

/**
 * Created by ASUS on 7/22/2018.
 */

public interface transaksiDao {

    Cursor read();

    boolean create (transaksi transaksi);

    boolean update (transaksi transaksi);

    boolean delete (int idMember);
}
