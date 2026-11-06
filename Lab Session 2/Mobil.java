/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSession2;

/**
 *
 * @author aldir
 */
    // SUBCLASS 1
public class Mobil extends Kendaraan implements Perawatan {
    private int jumlahPenumpang;

    public Mobil(String nama, String platNomor, double tarifPerHari, int jumlahPenumpang) {
        super(nama, platNomor, tarifPerHari);
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public int getJumlahPenumpang() { return jumlahPenumpang; }
    public void setJumlahPenumpang(int jumlahPenumpang) { this.jumlahPenumpang = jumlahPenumpang; }

    @Override
    public double hitungBiayaSewa(int hari) {
        return getTarifPerHari() * hari + 50000; // biaya asuransi tetap
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[MOBIL]");
        super.tampilkanInfo();
        System.out.println("Penumpang : " + jumlahPenumpang + " orang");
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis mobil " + getNama() + ": ganti oli, cek rem, dan AC.");
    }

    @Override
    public String getJadwalServis() { return "Setiap 5.000 km"; }
}
