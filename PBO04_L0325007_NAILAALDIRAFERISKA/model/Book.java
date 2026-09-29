/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aldir
 */
public class Book {
    
    // ===== Variabel (atribut) =====
    private String judul;            // reference type
    private String penulis;          // reference type
    private int tahunTerbit;         // primitive type
    private String kategori;         // reference type
    private boolean statusKetersediaan; // primitive type -> true = tersedia, false = dipinjam
    private int jumlahDipinjam;      // primitive type -> counter untuk analisis buku terpopuler
 
    // ===== Constructor =====
    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.statusKetersediaan = true; // saat buku baru ditambahkan, otomatis tersedia
        this.jumlahDipinjam = 0;
    }
 
    // ===== Getter & Setter =====
    public String getJudul() {
        return judul;
    }
 
    public String getPenulis() {
        return penulis;
    }
 
    public int getTahunTerbit() {
        return tahunTerbit;
    }
 
    public String getKategori() {
        return kategori;
    }
 
    public boolean isStatusKetersediaan() {
        return statusKetersediaan;
    }
 
    public void setStatusKetersediaan(boolean statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }
 
    public int getJumlahDipinjam() {
        return jumlahDipinjam;
    }
 
    // Method untuk menambah counter setiap kali buku dipinjam
    public void tambahHitunganPinjam() {
        this.jumlahDipinjam++;
    }
 
    /**
     * Method pencocokan judul menggunakan manipulasi String:
     * toLowerCase() dan contains() agar pencarian tidak case-sensitive.
     */
    public boolean cocokJudul(String keyword) {
        return this.judul.toLowerCase().contains(keyword.toLowerCase());
    }
 
    /**
     * Method pencocokan kategori, juga memakai toLowerCase() & contains().
     */
    public boolean cocokKategori(String keyword) {
        return this.kategori.toLowerCase().contains(keyword.toLowerCase());
    }
 
    @Override
    public String toString() {
        String status = statusKetersediaan ? "Tersedia" : "Dipinjam";
        // Manipulasi String & Character: memastikan huruf pertama judul selalu kapital saat ditampilkan
        String judulTampil = judul;
        if (!judul.isEmpty()) {
            char hurufPertama = Character.toUpperCase(judul.charAt(0));
            judulTampil = hurufPertama + judul.substring(1);
        }
        return String.format("\"%s\" oleh %s (%d) - Kategori: %s - Status: %s - Dipinjam: %d kali",
                judulTampil, penulis, tahunTerbit, kategori, status, jumlahDipinjam);
    }
}
