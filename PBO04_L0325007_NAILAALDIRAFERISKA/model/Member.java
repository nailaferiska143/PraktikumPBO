/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aldir
 */
public class Member {
    
    private String id;                     // reference type
    private String nama;                   // reference type
    private List<Book> daftarPinjaman;     // reference type (ArrayList of Book)
    private int totalRiwayatPinjam;        // primitive type -> total sepanjang waktu (untuk laporan anggota paling aktif)
 
    public static final int BATAS_PINJAM = 3; // primitive constant
 
    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
        this.totalRiwayatPinjam = 0;
    }
 
    public String getId() {
        return id;
    }
 
    public String getNama() {
        return nama;
    }
 
    public List<Book> getDaftarPinjaman() {
        return daftarPinjaman;
    }
 
    public int getTotalRiwayatPinjam() {
        return totalRiwayatPinjam;
    }
 
    /**
     * Mengecek apakah anggota ini valid untuk melakukan transaksi.
     * Manipulasi String: trim() dipakai memastikan id/nama tidak hanya berisi spasi.
     */
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() && nama != null && !nama.trim().isEmpty();
    }
 
    public boolean sudahMencapaiBatasPinjam() {
        return daftarPinjaman.size() >= BATAS_PINJAM;
    }
 
    public void tambahPinjaman(Book buku) {
        daftarPinjaman.add(buku);
        totalRiwayatPinjam++;
    }
 
    public void hapusPinjaman(Book buku) {
        daftarPinjaman.remove(buku);
    }
 
    @Override
    public String toString() {
        return String.format("ID: %s | Nama: %s | Sedang dipinjam: %d buku | Total riwayat pinjam: %d",
                id, nama, daftarPinjaman.size(), totalRiwayatPinjam);
    }
}
