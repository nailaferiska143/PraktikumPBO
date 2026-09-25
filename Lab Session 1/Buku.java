/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NailaAldira;

/**
 *
 * @author aldir
 */

// CLASS BUKU
// Poin 1 Class & Object
public class Buku {

    // Atribut
    String judul;
    String penulis;
    int tahunTerbit;
    boolean tersedia;

    // Poin 2 CONSTRUCTOR 
    public Buku(String judul, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = true;
    }

    // Method untuk menampilkan informasi buku
    public void tampilkanBuku() {

        String status;

        if (tersedia) {
            status = "Tersedia";
        } else {
            status = "Sedang dipinjam";
        }

        System.out.println("Judul       : " + judul.toUpperCase());
        System.out.println("Penulis     : " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Status      : " + status);
    }
}