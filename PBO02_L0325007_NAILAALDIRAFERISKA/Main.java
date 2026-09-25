/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO02_L0325007_NAILAALDIRAFERISKA;

import java.util.Scanner;

/**
 *
 * @author aldir
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Masukkan jumlah siswa: ");
        int jumlahSiswa = Integer.parseInt(scanner.nextLine().trim());
 
        Student[] siswaList = new Student[jumlahSiswa];
 
        // Input data siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            System.out.println();
            System.out.println("Data siswa ke-" + (i + 1) + ":");
 
            System.out.print("Masukkan nama: ");
            String nama = scanner.nextLine();
 
            System.out.print("Masukkan nilai: ");
            double nilai = Double.parseDouble(scanner.nextLine().trim());
 
            siswaList[i] = new Student(nama, nilai);
        }
 
        // Loop untuk mengecek kelulusan semua siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            siswaList[i].checkPassed();
        }
 
        // Output data siswa
        System.out.println();
        double totalNilai = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
 
        for (int i = 0; i < jumlahSiswa; i++) {
            Student s = siswaList[i];
            String status = s.passed ? "Lulus" : "Tidak Lulus";
 
            System.out.println("Siswa: " + s.name + " - Nilai: " + s.score + " - " + status);
 
            totalNilai += s.score;
 
            if (s.passed) {
                jumlahLulus++;
            } else {
                jumlahTidakLulus++;
            }
        }
 
        double rataRata = totalNilai / jumlahSiswa;
 
        System.out.println();
        System.out.printf("Rata-rata nilai kelas: %.2f%n", rataRata);
        System.out.println("Jumlah siswa lulus: " + jumlahLulus);
        System.out.println("Jumlah siswa tidak lulus: " + jumlahTidakLulus);
 
        scanner.close();
    }
}
