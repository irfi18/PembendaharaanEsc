package com.example.asus.pembendaharaanesc.transaksi;

/**
 * Created by ASUS on 7/22/2018.
 */

public class transaksi {
    private int idMember;
    public int getIdMember(){return idMember;};
    public void setIdMember(int idMember){this.idMember = idMember;};

    private String namaMember;
    private String tanggal;

    public String getNamaMember() {
        return namaMember;
    }

    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getUang() {
        return uang;
    }

    public void setUang(String uang) {
        this.uang = uang;
    }

    private String bulan;
    private String uang;

}
