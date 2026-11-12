/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author aldir
 */

public class ManajemenNilaiSiswa {

    private static final String NAMA_FILE = "laporan_nilai.txt";
    private static final double NILAI_MINIMAL_LULUS = 70.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int jumlahSiswa = bacaJumlahSiswa(input);

        // Langkah 1: input console & penulisan file
        tulisLaporan(input, jumlahSiswa);

        // Langkah 2: pembacaan file & tampilkan ke console
        bacaLaporan();

        input.close();
    }

    // Input 

    /** Meminta jumlah siswa sampai pengguna memasukkan bilangan bulat > 0. */
    private static int bacaJumlahSiswa(Scanner input) {
        while (true) {
            System.out.print("Masukkan jumlah siswa: ");
            String teks = input.nextLine().trim();
            try {
                int jumlah = Integer.parseInt(teks);
                if (jumlah > 0) {
                    return jumlah;
                }
                System.out.println("Jumlah siswa harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan bilangan bulat.");
            }
        }
    }

    // Meminta nilai (0 - 100) sampai pengguna memasukkan angka yang valid.
    private static double bacaNilai(Scanner input, int nomor) {
        while (true) {
            System.out.print("  Nilai siswa ke-" + nomor + " (0-100): ");
            String teks = input.nextLine().trim().replace(',', '.');
            try {
                double nilai = Double.parseDouble(teks);
                if (nilai >= 0 && nilai <= 100) {
                    return nilai;
                }
                System.out.println("  Nilai harus antara 0 dan 100.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid, masukkan angka.");
            }
        }
    }

    // Meminta nama siswa sampai tidak kosong.
    private static String bacaNama(Scanner input, int nomor) {
        while (true) {
            System.out.print("  Nama siswa ke-" + nomor + "  : ");
            String nama = input.nextLine().trim();
            if (!nama.isEmpty()) {
                return nama;
            }
            System.out.println("  Nama tidak boleh kosong.");
        }
    }

    // Logika 

    // Menentukan status kelulusan: nilai >= 70 Lulus, selain itu Tidak Lulus. 
    private static String tentukanStatus(double nilai) {
        return (nilai >= NILAI_MINIMAL_LULUS) ? "Lulus" : "Tidak Lulus";
    }

    // Penulisan file
    private static void tulisLaporan(Scanner input, int jumlahSiswa) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {

            writer.write("================ LAPORAN NILAI SISWA ================");
            writer.newLine();
            writer.write(String.format("%-4s %-25s %-8s %-12s", "No", "Nama", "Nilai", "Status"));
            writer.newLine();
            writer.write("-----------------------------------------------------");
            writer.newLine();

            double totalNilai = 0;
            int jumlahLulus = 0;
            int jumlahTidakLulus = 0;

            for (int i = 1; i <= jumlahSiswa; i++) {
                System.out.println("\nData siswa ke-" + i);
                String nama = bacaNama(input, i);
                double nilai = bacaNilai(input, i);

                // Status ditentukan langsung di dalam loop
                String status = tentukanStatus(nilai);
                if (status.equals("Lulus")) {
                    jumlahLulus++;
                } else {
                    jumlahTidakLulus++;
                }
                totalNilai += nilai;

                writer.write(String.format("%-4d %-25s %-8.2f %-12s", i, nama, nilai, status));
                writer.newLine();
            }

            double rataRata = totalNilai / jumlahSiswa;

            writer.write("-----------------------------------------------------");
            writer.newLine();
            writer.write("RINGKASAN");
            writer.newLine();
            writer.write(String.format("Rata-rata nilai kelas : %.2f", rataRata));
            writer.newLine();
            writer.write("Jumlah siswa lulus    : " + jumlahLulus);
            writer.newLine();
            writer.write("Jumlah siswa tidak lulus : " + jumlahTidakLulus);
            writer.newLine();

            System.out.println("\nData berhasil ditulis ke file " + NAMA_FILE);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }

    // Pembacaan file
    private static void bacaLaporan() {
        System.out.println("\n============ ISI FILE " + NAMA_FILE + " ============");
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }
}
