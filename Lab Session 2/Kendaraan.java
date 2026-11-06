/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSession2;

/**
 *
 * @author aldir
 */
    // ABSTRACT CLASS (superclass) + ENCAPSULATION
public abstract class Kendaraan {
    private String nama;
    private String platNomor;
    private double tarifPerHari;
    private boolean tersedia;

    public Kendaraan(String nama, String platNomor, double tarifPerHari) {
        this.nama = nama;
        this.platNomor = platNomor;
        setTarifPerHari(tarifPerHari);
        this.tersedia = true;
    }

    // Getter
    public String getNama() { return nama; }
    public String getPlatNomor() { return platNomor; }
    public double getTarifPerHari() { return tarifPerHari; }
    public boolean isTersedia() { return tersedia; }

    // Setter (dengan validasi)
    public void setNama(String nama) { this.nama = nama; }
    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari > 0) {
            this.tarifPerHari = tarifPerHari;
        } else {
            System.out.println("Tarif harus lebih dari 0!");
        }
    }

    // Perubahan status hanya lewat method ini
    public void sewa() {
        if (tersedia) {
            tersedia = false;
            System.out.println(nama + " berhasil disewa.");
        } else {
            System.out.println(nama + " sedang tidak tersedia.");
        }
    }

    public void kembalikan() {
        tersedia = true;
        System.out.println(nama + " telah dikembalikan.");
    }

    // ABSTRACT METHOD
    public abstract double hitungBiayaSewa(int hari);

    // OVERLOADING: nama sama, parameter berbeda
    public double hitungBiayaSewa(int hari, double diskonPersen) {
        double total = hitungBiayaSewa(hari);
        return total - (total * diskonPersen / 100);
    }

    public void tampilkanInfo() {
        System.out.println("Kendaraan : " + nama + " (" + platNomor + ")");
        System.out.println("Tarif/hari: Rp" + String.format("%,.0f", tarifPerHari));
        System.out.println("Status    : " + (tersedia ? "Tersedia" : "Disewa"));
    }
}