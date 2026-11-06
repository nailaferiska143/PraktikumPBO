/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSession2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aldir
 */
public class Main {

    private static final Scanner input = new Scanner(System.in);
    // RUNTIME POLYMORPHISM: objek subclass disimpan dalam referensi superclass
    private static final ArrayList<Kendaraan> daftar = new ArrayList<>();

    public static void main(String[] args) {
        // Data awal (4 objek)
        daftar.add(new Mobil("Toyota Avanza", "AD 1234 AB", 350000, 7));
        daftar.add(new Motor("Honda Vario", "AD 5678 CD", 80000, true));
        daftar.add(new Truk("Mitsubishi Colt Diesel", "AD 9012 EF", 600000, 4.5));
        daftar.add(new Mobil("Honda Brio", "AD 3456 GH", 300000, 5));

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            System.out.println();
            switch (pilihan) {
                case 1: lihatDaftar(); break;
                case 2: tambahKendaraan(); break;
                case 3: sewaKendaraan(); break;
                case 4: kembalikanKendaraan(); break;
                case 5: servisKendaraan(); break;
                case 6: ubahTarif(); break;
                case 0: System.out.println("Terima kasih telah menggunakan program ini!"); break;
                default: System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 0);
    }

    private static void tampilkanMenu() {
        System.out.println("\n========= RENTAL KENDARAAN =========");
        System.out.println("1. Lihat daftar kendaraan");
        System.out.println("2. Tambah kendaraan baru");
        System.out.println("3. Sewa kendaraan");
        System.out.println("4. Kembalikan kendaraan");
        System.out.println("5. Servis kendaraan");
        System.out.println("6. Ubah tarif kendaraan");
        System.out.println("0. Keluar");
        System.out.println("====================================");
    }

    private static void lihatDaftar() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada kendaraan.");
            return;
        }
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println("No. " + (i + 1));
            daftar.get(i).tampilkanInfo(); // polymorphism
            System.out.println("----------------------------");
        }
    }

    private static void tambahKendaraan() {
        System.out.println("Jenis kendaraan: 1. Mobil  2. Motor  3. Truk");
        int jenis = bacaInt("Pilih jenis: ");
        if (jenis < 1 || jenis > 3) {
            System.out.println("Jenis tidak valid!");
            return;
        }
        String nama = bacaString("Nama kendaraan : ");
        String plat = bacaString("Plat nomor     : ");
        double tarif = bacaDouble("Tarif per hari : ");

        Kendaraan baru;
        if (jenis == 1) {
            int penumpang = bacaInt("Jumlah penumpang: ");
            baru = new Mobil(nama, plat, tarif, penumpang);
        } else if (jenis == 2) {
            String helm = bacaString("Termasuk helm? (y/n): ");
            baru = new Motor(nama, plat, tarif, helm.equalsIgnoreCase("y"));
        } else {
            double ton = bacaDouble("Kapasitas (ton): ");
            baru = new Truk(nama, plat, tarif, ton);
        }
        daftar.add(baru);
        System.out.println("Kendaraan " + nama + " berhasil ditambahkan.");
    }

    private static void sewaKendaraan() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        if (!k.isTersedia()) {
            System.out.println(k.getNama() + " sedang tidak tersedia.");
            return;
        }
        int hari = bacaInt("Lama sewa (hari): ");
        if (hari <= 0) {
            System.out.println("Lama sewa harus lebih dari 0!");
            return;
        }
        double diskon = bacaDouble("Diskon % (0 jika tidak ada): ");

        double total;
        if (diskon > 0) {
            total = k.hitungBiayaSewa(hari, diskon); // overloading (2 parameter)
        } else {
            total = k.hitungBiayaSewa(hari);         // overloading (1 parameter)
        }
        k.sewa();
        System.out.println("Total biaya " + hari + " hari: Rp" + String.format("%,.0f", total));
    }

    private static void kembalikanKendaraan() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        if (k.isTersedia()) {
            System.out.println(k.getNama() + " sudah tersedia (tidak sedang disewa).");
        } else {
            k.kembalikan();
        }
    }

    private static void servisKendaraan() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        Perawatan p = (Perawatan) k; // lewat interface
        p.lakukanServis();
        System.out.println("Jadwal servis berikutnya: " + p.getJadwalServis());
    }

    private static void ubahTarif() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        double tarifBaru = bacaDouble("Tarif baru per hari: ");
        k.setTarifPerHari(tarifBaru); // setter dengan validasi
        System.out.println("Tarif " + k.getNama() + " sekarang: Rp"
                + String.format("%,.0f", k.getTarifPerHari()));
    }

    // ===== Helper =====
    private static Kendaraan pilihKendaraan() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada kendaraan.");
            return null;
        }
        for (int i = 0; i < daftar.size(); i++) {
            Kendaraan k = daftar.get(i);
            System.out.println((i + 1) + ". " + k.getNama() + " (" + k.getPlatNomor() + ") - "
                    + (k.isTersedia() ? "Tersedia" : "Disewa"));
        }
        int no = bacaInt("Pilih nomor kendaraan: ");
        if (no < 1 || no > daftar.size()) {
            System.out.println("Nomor tidak valid!");
            return null;
        }
        return daftar.get(no - 1);
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        System.out.print(pesan);
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private static double bacaDouble(String pesan) {
        System.out.print(pesan);
        try {
            return Double.parseDouble(input.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
