package com.example.asus.pembendaharaanesc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.pembendaharaanesc.transaksi.transaksi;
import com.example.asus.pembendaharaanesc.transaksi.transaksiDao;
import com.example.asus.pembendaharaanesc.transaksi.transaksiDaoImp;

public class InputActivity extends AppCompatActivity {
    private transaksiDao dao;
    private transaksi transaksi;
    private EditText etIdMember, etNamaMember, etTanggal, etBulan, etUang;
    private String idMember, namaMember, tanggal, bulan, uang;
    private boolean TAG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dao = new transaksiDaoImp(this);
        transaksi = new transaksi();
        etIdMember = (EditText) findViewById(R.id.etidMember);
        etNamaMember = (EditText) findViewById(R.id.etnamaMember);
        etTanggal = (EditText) findViewById(R.id.ettanggal);
        etBulan = (EditText) findViewById(R.id.etbulan);
        etUang = (EditText) findViewById(R.id.etuang);

        Intent i = getIntent();
        if (!TextUtils.isEmpty(i.getStringExtra("idMember"))) {
            idMember = i.getStringExtra("idMember");
            namaMember = i.getStringExtra("namaMember");
            tanggal = i.getStringExtra("tanggal");
            bulan = i.getStringExtra("bulan");
            uang = i.getStringExtra("uang");

            etIdMember.setEnabled(false);

            etIdMember.setText(idMember);
            etNamaMember.setText(namaMember);
            etTanggal.setText(tanggal);
            etBulan.setText(bulan);
            etUang.setText(uang);

            TAG = false;
        }
    }

    public void btnSimpan(View v){
        String idMember= etIdMember.getText().toString();
        String namaMember = etNamaMember.getText().toString();
        String tanggal = etTanggal.getText().toString();
        String bulan = etBulan.getText().toString();
        String uang = etUang.getText().toString();

        if (idMember.length() > 0 && namaMember.length() > 0 && tanggal.length() > 0 && bulan.length() > 0 && uang.length() > 0 ) {
            transaksi.setIdMember(Integer.valueOf(idMember));
            transaksi.setNamaMember(namaMember);
            transaksi.setTanggal(tanggal);
            transaksi.setBulan(bulan);
            transaksi.setUang(uang);
            if (TAG) {
                dao.create(transaksi);
                Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InputActivity.this, MainActivity.class));
                finish();
                finishActivity(404);
            } else {
                boolean cek = dao.update(transaksi);
                if(cek){
                    Toast.makeText(this, "Berhasil ubah data", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputActivity.this, MainActivity.class));
                }
                finish();
                finishActivity(404);
            }

        } else {
            Toast.makeText(this, "Inputan masih kosong!", Toast.LENGTH_SHORT).show();
        }
    }
}
