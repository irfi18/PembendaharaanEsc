package com.example.asus.pembendaharaanesc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.asus.pembendaharaanesc.transaksi.transaksiDao;
import com.example.asus.pembendaharaanesc.transaksi.transaksiDaoImp;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String daftar[];
    private transaksiDao transaksiDao;
    private ListView lvTransaksi;
    private FloatingActionButton btnTambah;
    ArrayList<HashMap<String, String>> listTransaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = (FloatingActionButton) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intent, 404);
            }
        });

        transaksiDao = new transaksiDaoImp(this);
        lvTransaksi = (ListView) findViewById(R.id.lvTransaksi);

        ReadTransaksi();

        lvTransaksi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                Intent in = new Intent(getApplicationContext(), TampilActivity.class);
                in.putExtra("idMember", listTransaksi.get(i).get("idMember"));
                in.putExtra("namaMember", listTransaksi.get(i).get("namaMember"));
                in.putExtra("tanggal", listTransaksi.get(i).get("tanggal"));
                in.putExtra("bulan", listTransaksi.get(i).get("bulan"));
                in.putExtra("uang", listTransaksi.get(i).get("uang"));
                startActivity(in);

            }

        });

        lvTransaksi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String[] pilihan = {"Ubah", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                final AlertDialog.Builder konfirmasi = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                                intent.putExtra("idMember", listTransaksi.get(i).get("idMember"));
                                intent.putExtra("namaMember", listTransaksi.get(i).get("namaMember"));
                                intent.putExtra("tanggal", listTransaksi.get(i).get("tanggal"));
                                intent.putExtra("bulan", listTransaksi.get(i).get("bulan"));
                                intent.putExtra("uang", listTransaksi.get(i).get("uang"));

                                startActivity(intent);
                                break;
                            case 1:
                                konfirmasi.setMessage("Yakin ingin hapus ?");
                                konfirmasi.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                konfirmasi.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int posision) {
                                        boolean cek = transaksiDao.delete(Integer.valueOf(listTransaksi.get(i).get("idMember")));
                                        if (cek) {
                                            Toast.makeText(MainActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            ReadTransaksi();
                                        }
                                    }
                                });

                                konfirmasi.show();

                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public void ReadTransaksi() {
        listTransaksi = new ArrayList<>();

        Cursor cursor = transaksiDao.read();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("idMember", cursor.getString(0));
                hm.put("namaMember", cursor.getString(1));
                hm.put("tanggal", cursor.getString(2));
                hm.put("bulan", cursor.getString(3));
                hm.put("uang", cursor.getString(4));

                listTransaksi.add(hm);
            } while (cursor.moveToNext());
        }

        String[] key = {"namaMember", "tanggal"};
        int[] komponen = {R.id.tvNamaMember, R.id.tvTanggal};
        SimpleAdapter adapter = new SimpleAdapter(this, listTransaksi, R.layout.items, key, komponen);
        lvTransaksi.setAdapter(adapter);
    }
}
