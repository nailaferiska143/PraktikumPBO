/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NailaAldira;

import java.util.ArrayList;
import java.util.Scanner;

// CLASS UTAMA PERPUSTAKAAN
public class Perpustakaan {

    // Poin 3 KONSTANTA
    static final int BATAS_TAHUN = 2020;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // POIN 8 - ARRAY / COLLECTION
        ArrayList<Buku> daftarBuku = new ArrayList<>();

        System.out.println("========================================");
        System.out.println("     SISTEM PERPUSTAKAAN SEDERHANA");
        System.out.println("========================================");

        // Poin 6 EXCEPTION HANDLING
        try {

            System.out.print("Masukkan jumlah buku: ");
            int jumlahBuku = input.nextInt();

            // Poin 4 KONDISIONAL
            if (jumlahBuku <= 0) {
                System.out.println("Jumlah buku harus lebih dari 0.");
                return;
            }

            input.nextLine();

            // POIN 5 - LOOPING
            for (int i = 0; i < jumlahBuku; i++) {

                System.out.println("\nBuku ke-" + (i + 1));

                System.out.print("Judul buku       : ");
                String judul = input.nextLine();

                System.out.print("Nama penulis     : ");
                String penulis = input.nextLine();

                System.out.print("Tahun terbit     : ");
                int tahun = input.nextInt();

                input.nextLine();

                // Poin 1 dan 9 MEMBUAT OBJECT
                Buku buku = new Buku(judul, penulis, tahun);

                // Menyimpan object ke ArrayList
                daftarBuku.add(buku);
            }

            // MENAMPILKAN DATA BUKU
            System.out.println("\n========================================");
            System.out.println("       DAFTAR BUKU PERPUSTAKAAN");
            System.out.println("========================================");

            for (int i = 0; i < daftarBuku.size(); i++) {

                System.out.println("\nBuku ke-" + (i + 1));

                daftarBuku.get(i).tampilkanBuku();

                // Poin 4 KONDISIONAL
                if (daftarBuku.get(i).tahunTerbit >= BATAS_TAHUN) {
                    System.out.println("Kategori    : Buku terbaru");

                } else {
                    System.out.println("Kategori    : Buku lama");
                }
            }

            // Poin 7 CHARACTER & STRING
            String namaPerpustakaan = "Perpustakaan Kampus";

            char kode = namaPerpustakaan.charAt(0);

            System.out.println("\n========================================");

            System.out.println("Nama Perpustakaan : "
                    + namaPerpustakaan.toUpperCase());

            System.out.println("Kode Perpustakaan : " + kode);

            System.out.println("Jumlah Buku       : "
                    + daftarBuku.size());

            System.out.println("========================================");

        } catch (Exception e) {

            // Poin 6 EXCEPTION HANDLING
            System.out.println("\nTerjadi kesalahan!");
            System.out.println("Pastikan data yang dimasukkan benar.");
        }

        input.close();
    }
}