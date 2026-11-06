/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSession2;

/**
 *
 * @author aldir
 */
// SUBCLASS 3
public class Truk extends Kendaraan implements Perawatan {
    private double kapasitasTon;

    public Truk(String nama, String platNomor, double tarifPerHari, double kapasitasTon) {
        super(nama, platNomor, tarifPerHari);
        this.kapasitasTon = kapasitasTon;
    }

    public double getKapasitasTon() { return kapasitasTon; }
    public void setKapasitasTon(double kapasitasTon) { this.kapasitasTon = kapasitasTon; }

    @Override
    public double hitungBiayaSewa(int hari) {
        return getTarifPerHari() * hari + (kapasitasTon * 25000); // biaya muatan
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[TRUK]");
        super.tampilkanInfo();
        System.out.println("Kapasitas : " + kapasitasTon + " ton");
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis truk " + getNama() + ": cek mesin diesel, rem angin, dan ban.");
    }

    @Override
    public String getJadwalServis() { return "Setiap 10.000 km"; }
}
