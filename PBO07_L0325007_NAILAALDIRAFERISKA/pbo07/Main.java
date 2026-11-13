/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbo07;

import Model.Mahasiswa;
import Model.NilaiInvalidException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author aldir
 */
public class Main {

    private static final String NAMA_FILE = "laporan_nilai.txt";

    public static void main(String[] args) throws NilaiInvalidException {
        ArrayList<Mahasiswa> daftar = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            int jumlah = bacaJumlah(scanner);

            // Looping input data mahasiswa
            int ke = 1;
            while (ke <= jumlah) {
                System.out.println("\n--- Data Mahasiswa ke-" + ke + " ---");
                try {
                    System.out.print("Nama  : ");
                    String nama = scanner.nextLine();
                    System.out.print("NIM   : ");
                    String nim = scanner.nextLine();
                    System.out.print("Nilai : ");
                    double nilai = Double.parseDouble(scanner.nextLine().trim());

                    daftar.add(new Mahasiswa(nama, nim, nilai));
                    System.out.println("Data berhasil ditambahkan.");
                    ke++; // maju hanya jika data valid
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Nilai harus berupa angka. Ulangi input.");
                } catch (NilaiInvalidException e) {
                    System.out.println("[ERROR] " + e.getMessage() + ". Ulangi input.");
                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Ulangi input.");
                } catch (AssertionError e) {
                    System.out.println("[ASSERT] " + e.getMessage() + " Ulangi input.");
                }
            }
        }

        if (daftar.isEmpty()) {
            System.out.println("Tidak ada data untuk diproses.");
            return;
        }

        tampilkanRekap(daftar);
        simpanKeFile(daftar);
    }

    // Membaca jumlah mahasiswa; ulangi sampai input berupa bilangan bulat > 0
    private static int bacaJumlah(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jumlah mahasiswa: ");
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("[ERROR] Jumlah harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan bilangan bulat.");
            }
        }
    }

    private static double hitungRataRata(ArrayList<Mahasiswa> daftar) {
        double total = 0;
        for (Mahasiswa m : daftar) {
            total += m.getNilai();
        }
        return total / daftar.size();
    }

    private static int hitungLulus(ArrayList<Mahasiswa> daftar) {
        int lulus = 0;
        for (Mahasiswa m : daftar) {
            if (m.isLulus()) {
                lulus++;
            }
        }
        return lulus;
    }

    private static String header() {
        return String.format("%-12s %-25s %6s   %-5s %s",
                "NIM", "NAMA", "NILAI", "GRADE", "STATUS");
    }

    private static void tampilkanRekap(ArrayList<Mahasiswa> daftar) {
        System.out.println("\n================= REKAPITULASI NILAI MAHASISWA ================");
        System.out.println(header());
        for (Mahasiswa m : daftar) {
            System.out.println(m);
        }
        int lulus = hitungLulus(daftar);
        System.out.printf("%nJumlah mahasiswa : %d%n", daftar.size());
        System.out.printf("Rata-rata kelas  : %.2f%n", hitungRataRata(daftar));
        System.out.printf("Lulus            : %d%n", lulus);
        System.out.printf("Tidak lulus      : %d%n", daftar.size() - lulus);
    }

    // Menulis laporan memakai BufferedWriter + FileWriter (try-with-resources).
    private static void simpanKeFile(ArrayList<Mahasiswa> daftar) {
        int lulus = hitungLulus(daftar);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write("LAPORAN NILAI MAHASISWA");
            writer.newLine();
            writer.write("Standar kelulusan: " + Mahasiswa.FINAL_PASSING_GRADE);
            writer.newLine();
            writer.write("==============================================================");
            writer.newLine();
            writer.write(header());
            writer.newLine();
            writer.write("--------------------------------------------------------------");
            writer.newLine();
            for (Mahasiswa m : daftar) {
                writer.write(m.toString());
                writer.newLine();
            }
            writer.write("--------------------------------------------------------------");
            writer.newLine();
            writer.write("Jumlah mahasiswa : " + daftar.size());
            writer.newLine();
            writer.write(String.format("Rata-rata kelas  : %.2f", hitungRataRata(daftar)));
            writer.newLine();
            writer.write("Lulus            : " + lulus);
            writer.newLine();
            writer.write("Tidak lulus      : " + (daftar.size() - lulus));
            writer.newLine();
            System.out.println("\nLaporan berhasil disimpan ke file: " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis file: " + e.getMessage());
        } finally {
            System.out.println("Proses penyimpanan file selesai.");
        }
    }
}


