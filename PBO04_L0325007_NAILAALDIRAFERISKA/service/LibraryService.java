/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Book;
import model.Member;

/**
 *
 * @author aldir
 */
public class LibraryService { 

    // Koleksi buku menggunakan ArrayList (reference type)
    private List<Book> daftarBuku;
 
    // Data anggota disimpan dalam HashMap<id, Member> agar pencarian anggota cepat (reference type)
    private Map<String, Member> daftarAnggota;
 
    // Primitive type: total transaksi peminjaman yang pernah terjadi
    private int totalTransaksiPinjam;
 
    public LibraryService() {
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new HashMap<>();
        this.totalTransaksiPinjam = 0;
    }
 
    // ================= MANAJEMEN BUKU =================
 
    public void tambahBuku(String judul, String penulis, int tahunTerbit, String kategori) {
        Book bukuBaru = new Book(judul, penulis, tahunTerbit, kategori);
        daftarBuku.add(bukuBaru);
    }
 
    public List<Book> getDaftarBuku() {
        return daftarBuku;
    }
 
    public void tampilkanDaftarBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku dalam koleksi.");
            return;
        }
        int nomor = 1;
        // Looping: for-each untuk menampilkan seluruh buku
        for (Book buku : daftarBuku) {
            System.out.println(nomor + ". " + buku);
            nomor++;
        }
    }
 
    // ================= MANAJEMEN ANGGOTA =================
 
    public void tambahAnggota(String id, String nama) {
        Member anggotaBaru = new Member(id, nama);
        daftarAnggota.put(id, anggotaBaru);
    }
 
    public Map<String, Member> getDaftarAnggota() {
        return daftarAnggota;
    }
 
    // ================= PENCARIAN =================
 
    /**
     * Mencari buku berdasarkan judul ATAU kategori.
     * Menggunakan manipulasi String: toLowerCase() & contains() (lewat method Book).
     *
     * @param keyword     kata kunci pencarian
     * @param cariKategori jika true, cari berdasarkan kategori; jika false, cari berdasarkan judul
     */
    public List<Book> cariBuku(String keyword, boolean cariKategori) {
        List<Book> hasil = new ArrayList<>();
        for (Book buku : daftarBuku) {
            boolean cocok = cariKategori ? buku.cocokKategori(keyword) : buku.cocokJudul(keyword);
            if (cocok) {
                hasil.add(buku);
            }
        }
        return hasil;
    }
 
    /**
     * Mencari objek Book berdasarkan judul persis (case-insensitive) untuk keperluan transaksi.
     */
    private Book cariBukuPersis(String judul) throws BookNotFoundException {
        for (Book buku : daftarBuku) {
            // manipulasi string: equalsIgnoreCase agar pencocokan tidak sensitif huruf besar/kecil
            if (buku.getJudul().equalsIgnoreCase(judul.trim())) {
                return buku;
            }
        }
        throw new BookNotFoundException("Buku dengan judul \"" + judul + "\" tidak ditemukan di perpustakaan.");
    }
 
    /**
     * Menghitung jumlah buku pada setiap kategori menggunakan looping & HashMap.
     */
    public Map<String, Integer> hitungJumlahPerKategori() {
        Map<String, Integer> hasil = new HashMap<>();
        for (Book buku : daftarBuku) {
            String kategori = buku.getKategori();
            if (hasil.containsKey(kategori)) {
                hasil.put(kategori, hasil.get(kategori) + 1);
            } else {
                hasil.put(kategori, 1);
            }
        }
        return hasil;
    }
 
    // ================= PEMINJAMAN & PENGEMBALIAN =================
 
    /**
     * Proses peminjaman buku oleh anggota.
     * Melempar exception untuk: anggota tidak ditemukan, buku tidak ditemukan,
     * buku sudah dipinjam, dan batas pinjam terlampaui.
     * Menggunakan assertion untuk memvalidasi data anggota sebelum transaksi.
     */
    public void pinjamBuku(String idAnggota, String judulBuku)
            throws BookNotFoundException, BookAlreadyBorrowedException, BorrowLimitExceededException, MemberNotFoundException {
 
        Member anggota = daftarAnggota.get(idAnggota);
        if (anggota == null) {
            throw new MemberNotFoundException("Anggota dengan ID \"" + idAnggota + "\" tidak ditemukan.");
        }
 
        // Assertion: memastikan data anggota benar-benar valid sebelum melanjutkan transaksi.
        // Catatan: assertion hanya aktif jika program dijalankan dengan flag -ea.
        assert anggota.isValid() : "Data anggota tidak valid (id/nama kosong)!";
 
        Book buku = cariBukuPersis(judulBuku);
 
        if (!buku.isStatusKetersediaan()) {
            throw new BookAlreadyBorrowedException("Buku \"" + buku.getJudul() + "\" sedang dipinjam anggota lain.");
        }
 
        if (anggota.sudahMencapaiBatasPinjam()) {
            throw new BorrowLimitExceededException(
                    "Anggota " + anggota.getNama() + " sudah meminjam maksimal " + Member.BATAS_PINJAM + " buku.");
        }
 
        // Jika semua validasi lolos, proses peminjaman dilakukan
        buku.setStatusKetersediaan(false);
        buku.tambahHitunganPinjam();
        anggota.tambahPinjaman(buku);
        totalTransaksiPinjam++;
    }
 
    /**
     * Proses pengembalian buku oleh anggota.
     */
    public void kembalikanBuku(String idAnggota, String judulBuku)
            throws BookNotFoundException, MemberNotFoundException {
 
        Member anggota = daftarAnggota.get(idAnggota);
        if (anggota == null) {
            throw new MemberNotFoundException("Anggota dengan ID \"" + idAnggota + "\" tidak ditemukan.");
        }
 
        assert anggota.isValid() : "Data anggota tidak valid (id/nama kosong)!";
 
        Book buku = cariBukuPersis(judulBuku);
 
        // Kondisional: pastikan buku ini memang sedang dipinjam oleh anggota tersebut
        boolean ditemukanDiPinjaman = false;
        for (Book b : anggota.getDaftarPinjaman()) {
            if (b.getJudul().equalsIgnoreCase(buku.getJudul())) {
                ditemukanDiPinjaman = true;
                break;
            }
        }
 
        if (!ditemukanDiPinjaman) {
            throw new BookNotFoundException(
                    "Buku \"" + buku.getJudul() + "\" tidak tercatat sedang dipinjam oleh anggota ini.");
        }
 
        buku.setStatusKetersediaan(true);
        anggota.hapusPinjaman(buku);
    }
 
    // ================= ANALISIS & LAPORAN =================
 
    /**
     * Mencari buku yang paling sering dipinjam menggunakan looping sederhana.
     */
    public Book bukuPalingSeringDipinjam() {
        if (daftarBuku.isEmpty()) {
            return null;
        }
        Book terpopuler = daftarBuku.get(0);
        for (Book buku : daftarBuku) {
            if (buku.getJumlahDipinjam() > terpopuler.getJumlahDipinjam()) {
                terpopuler = buku;
            }
        }
        return terpopuler;
    }
 
    /**
     * Mencari anggota paling aktif berdasarkan total riwayat peminjaman.
     */
    public Member anggotaPalingAktif() {
        Member teraktif = null;
        for (Member anggota : daftarAnggota.values()) {
            if (teraktif == null || anggota.getTotalRiwayatPinjam() > teraktif.getTotalRiwayatPinjam()) {
                teraktif = anggota;
            }
        }
        return teraktif;
    }
 
    /**
     * Mencari kategori paling populer (kategori dengan jumlahDipinjam gabungan terbanyak).
     */
    public String kategoriPalingPopuler() {
        Map<String, Integer> pinjamPerKategori = new HashMap<>();
        for (Book buku : daftarBuku) {
            String kategori = buku.getKategori();
            int tambahan = buku.getJumlahDipinjam();
            pinjamPerKategori.put(kategori, pinjamPerKategori.getOrDefault(kategori, 0) + tambahan);
        }
 
        String kategoriTerpopuler = "-";
        int maksimum = -1;
        for (Map.Entry<String, Integer> entry : pinjamPerKategori.entrySet()) {
            if (entry.getValue() > maksimum) {
                maksimum = entry.getValue();
                kategoriTerpopuler = entry.getKey();
            }
        }
        return kategoriTerpopuler;
    }
 
    public int getTotalTransaksiPinjam() {
        return totalTransaksiPinjam;
    }
 
    /**
     * Mencetak laporan lengkap perpustakaan ke konsol.
     */
    public void cetakLaporan() {
        System.out.println("========== LAPORAN PERPUSTAKAAN ==========");
        System.out.println("Total buku dalam koleksi   : " + daftarBuku.size());
        System.out.println("Total anggota terdaftar    : " + daftarAnggota.size());
        System.out.println("Total transaksi peminjaman : " + totalTransaksiPinjam);
 
        System.out.println("\n-- Jumlah Buku per Kategori --");
        Map<String, Integer> perKategori = hitungJumlahPerKategori();
        for (Map.Entry<String, Integer> entry : perKategori.entrySet()) {
            System.out.println("  " + entry.getKey() + " : " + entry.getValue() + " buku");
        }
 
        Book bukuTerpopuler = bukuPalingSeringDipinjam();
        System.out.println("\n-- Buku Paling Sering Dipinjam --");
        if (bukuTerpopuler != null && bukuTerpopuler.getJumlahDipinjam() > 0) {
            System.out.println("  " + bukuTerpopuler);
        } else {
            System.out.println("  Belum ada aktivitas peminjaman.");
        }
 
        Member anggotaTeraktif = anggotaPalingAktif();
        System.out.println("\n-- Anggota Paling Aktif --");
        if (anggotaTeraktif != null && anggotaTeraktif.getTotalRiwayatPinjam() > 0) {
            System.out.println("  " + anggotaTeraktif);
        } else {
            System.out.println("  Belum ada aktivitas peminjaman.");
        }
 
        System.out.println("\n-- Kategori Paling Populer --");
        System.out.println("  " + kategoriPalingPopuler());
        System.out.println("===========================================");
    }
}
