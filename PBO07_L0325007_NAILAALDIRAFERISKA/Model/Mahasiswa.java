/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aldir
 */
public class Mahasiswa {

    // Konstanta standar kelulusan (final + static = konstanta kelas)
    public static final double FINAL_PASSING_GRADE = 70.0;

    // Atribut
    private final String nama;   // reference type
    private final String nim;    // reference type
    private final double nilai;  // primitive type

    public Mahasiswa(String nama, String nim, double nilai) throws NilaiInvalidException {
        // Perapihan String
        String namaRapi = (nama == null) ? "" : nama.trim().toUpperCase();
        String nimRapi  = (nim == null)  ? "" : nim.trim();

        // Assertion: NIM tidak boleh kosong (aktif dengan java -ea)
        assert !nimRapi.isEmpty() : "NIM tidak boleh kosong!";

        // Validasi eksplisit (tetap berjalan walau assertion dimatikan)
        if (namaRapi.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!");
        }
        if (nimRapi.isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong!");
        }
        // Character: NIM hanya boleh berisi angka
        for (char c : nimRapi.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(
                        "NIM hanya boleh berisi angka, ditemukan karakter '" + c + "'");
            }
        }
        // Custom Exception: rentang nilai
        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(nilai);
        }

        this.nama = namaRapi;
        this.nim = nimRapi;
        this.nilai = nilai;
    }

    public String getNama() { return nama; }
    public String getNim()  { return nim; }
    public double getNilai() { return nilai; }

    // Memetakan nilai angka ke grade huruf (If-Else)
    public String getGrade() {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 75) {
            return "B";
        } else if (nilai >= FINAL_PASSING_GRADE) {
            return "C";
        } else {
            return "D";
        }
    }

    // Status kelulusan berdasarkan FINAL_PASSING_GRADE (primitive boolean)   
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    public String getStatus() {
        return isLulus() ? "LULUS" : "TIDAK LULUS";
    }

    @Override
    public String toString() {
        return String.format("%-12s %-25s %6.2f   %-5s %s",
                nim, nama, nilai, getGrade(), getStatus());
    }
}
