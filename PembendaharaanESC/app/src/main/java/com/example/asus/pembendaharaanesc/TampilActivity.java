package com.example.asus.pembendaharaanesc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TampilActivity extends AppCompatActivity {
private TextView tvId, tvNama, tvTanggal, tvBulan, tvUang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        tvId = (TextView) findViewById(R.id.tvId);
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvTanggal = (TextView) findViewById(R.id.tvTanggal);
        tvBulan = (TextView) findViewById(R.id.tvBulan);
        tvUang = (TextView) findViewById(R.id.tvUang);

        Intent i = getIntent();
        tvId.setText(i.getStringExtra("idMember"));
        tvNama.setText(i.getStringExtra("namaMember"));
        tvTanggal.setText(i.getStringExtra("tanggal"));
        tvBulan.setText(i.getStringExtra("bulan"));
        tvUang.setText(i.getStringExtra("uang"));
    }
}
