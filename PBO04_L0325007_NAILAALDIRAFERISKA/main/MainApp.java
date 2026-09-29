/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;
import java.util.Scanner;
import model.Book;
import service.BookAlreadyBorrowedException;
import service.BookNotFoundException;
import service.BorrowLimitExceededException;
import service.LibraryService;
import service.MemberNotFoundException;

/**
 *
 * @author aldir
 */
public class MainApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryService service = new LibraryService();
 
    public static void main(String[] args) {
        seedDataAwal(); // data contoh agar aplikasi langsung bisa dicoba
        boolean berjalan = true; // primitive boolean, mengontrol looping utama
 
        System.out.println("=== SELAMAT DATANG DI SISTEM MANAJEMEN PERPUSTAKAAN MINI ===");
 
        // Looping utama menu (while) dipadukan dengan percabangan (switch-case)
        while (berjalan) {
            tampilkanMenu();
            String pilihan = scanner.nextLine().trim();
 
            switch (pilihan) {
                case "1":
                    tambahBuku();
                    break;
                case "2":
                    service.tampilkanDaftarBuku();
                    break;
                case "3":
                    cariBuku();
                    break;
                case "4":
                    pinjamBuku();
                    break;
                case "5":
                    kembalikanBuku();
                    break;
                case "6":
                    service.cetakLaporan();
                    break;
                case "7":
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak dikenali. Silakan masukkan angka 1-7.");
            }
        }
 
        scanner.close();
    }
 
    private static void tampilkanMenu() {
        System.out.println("\n================ MENU ================");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Daftar Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Pinjam Buku");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Laporan Perpustakaan");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu (1-7): ");
    }
 
    private static void tambahBuku() {
        System.out.print("Judul buku       : ");
        String judul = scanner.nextLine().trim();
        System.out.print("Penulis          : ");
        String penulis = scanner.nextLine().trim();
 
        int tahun = 0;
        boolean tahunValid = false; // kondisional validasi input angka
        while (!tahunValid) {
            System.out.print("Tahun terbit     : ");
            String inputTahun = scanner.nextLine().trim();
            try {
                tahun = Integer.parseInt(inputTahun);
                tahunValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Tahun tidak valid, masukkan angka. Contoh: 2020");
            }
        }
 
        System.out.print("Kategori         : ");
        String kategori = scanner.nextLine().trim();
 
        // Validasi sederhana dengan kondisional & manipulasi String (isEmpty)
        if (judul.isEmpty() || penulis.isEmpty() || kategori.isEmpty()) {
            System.out.println("Data buku tidak lengkap. Buku gagal ditambahkan.");
            return;
        }
 
        service.tambahBuku(judul, penulis, tahun, kategori);
        System.out.println("Buku \"" + judul + "\" berhasil ditambahkan ke koleksi.");
    }
 
    private static void cariBuku() {
        System.out.print("Cari berdasarkan (1) Judul atau (2) Kategori? ");
        String tipe = scanner.nextLine().trim();
        boolean cariKategori = tipe.equals("2");
 
        System.out.print("Masukkan kata kunci: ");
        String keyword = scanner.nextLine().trim();
 
        List<Book> hasil = service.cariBuku(keyword, cariKategori);
 
        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan buku yang cocok dengan kata kunci \"" + keyword + "\".");
        } else {
            System.out.println("Ditemukan " + hasil.size() + " buku:");
            int nomor = 1;
            for (Book buku : hasil) {
                System.out.println("  " + nomor + ". " + buku);
                nomor++;
            }
        }
    }
 
    private static void pinjamBuku() {
        System.out.print("ID Anggota    : ");
        String idAnggota = scanner.nextLine().trim();
 
        // Jika anggota belum terdaftar, tawarkan pendaftaran cepat
        if (!service.getDaftarAnggota().containsKey(idAnggota)) {
            System.out.print("Anggota belum terdaftar. Masukkan nama untuk mendaftar (kosongkan untuk batal): ");
            String nama = scanner.nextLine().trim();
            if (nama.isEmpty()) {
                System.out.println("Peminjaman dibatalkan.");
                return;
            }
            service.tambahAnggota(idAnggota, nama);
            System.out.println("Anggota baru \"" + nama + "\" berhasil didaftarkan.");
        }
 
        System.out.print("Judul buku yang dipinjam: ");
        String judul = scanner.nextLine().trim();
 
        try {
            service.pinjamBuku(idAnggota, judul);
            System.out.println("Peminjaman berhasil! Selamat membaca.");
        } catch (BookNotFoundException | MemberNotFoundException
                | BookAlreadyBorrowedException | BorrowLimitExceededException e) {
            System.out.println("Peminjaman gagal: " + e.getMessage());
        } catch (AssertionError e) {
            System.out.println("Transaksi dibatalkan, data anggota tidak valid: " + e.getMessage());
        }
    }
 
    private static void kembalikanBuku() {
        System.out.print("ID Anggota    : ");
        String idAnggota = scanner.nextLine().trim();
        System.out.print("Judul buku yang dikembalikan: ");
        String judul = scanner.nextLine().trim();
 
        try {
            service.kembalikanBuku(idAnggota, judul);
            System.out.println("Pengembalian berhasil, terima kasih!");
        } catch (BookNotFoundException | MemberNotFoundException e) {
            System.out.println("Pengembalian gagal: " + e.getMessage());
        } catch (AssertionError e) {
            System.out.println("Transaksi dibatalkan, data anggota tidak valid: " + e.getMessage());
        }
    }
 
    /**
     * Mengisi beberapa data awal agar aplikasi langsung dapat dicoba tanpa input manual.
     */
    private static void seedDataAwal() {
        service.tambahBuku("Laskar Pelangi", "Andrea Hirata", 2005, "Novel");
        service.tambahBuku("Bumi Manusia", "Pramoedya Ananta Toer", 1980, "Novel");
        service.tambahBuku("Filosofi Teras", "Henry Manampiring", 2018, "Pengembangan Diri");
        service.tambahBuku("Belajar Java Dasar", "Budi Santoso", 2021, "Teknologi");
        service.tambahBuku("Algoritma & Pemrograman", "Rinaldi Munir", 2019, "Teknologi");
 
        service.tambahAnggota("A001", "Sinta");
        service.tambahAnggota("A002", "Rudi");
    }
}
