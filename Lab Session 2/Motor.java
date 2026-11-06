/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSession2;

/**
 *
 * @author aldir
 */
// SUBCLASS 2
public class Motor extends Kendaraan implements Perawatan {
    private boolean termasukHelm;

    public Motor(String nama, String platNomor, double tarifPerHari, boolean termasukHelm) {
        super(nama, platNomor, tarifPerHari);
        this.termasukHelm = termasukHelm;
    }

    public boolean isTermasukHelm() { return termasukHelm; }
    public void setTermasukHelm(boolean termasukHelm) { this.termasukHelm = termasukHelm; }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = getTarifPerHari() * hari;
        if (termasukHelm) total += 10000 * hari; // sewa helm per hari
        return total;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[MOTOR]");
        super.tampilkanInfo();
        System.out.println("Helm      : " + (termasukHelm ? "Termasuk" : "Tidak"));
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis motor " + getNama() + ": ganti oli, setel rantai, cek ban.");
    }

    @Override
    public String getJadwalServis() { return "Setiap 2.000 km"; }
}
