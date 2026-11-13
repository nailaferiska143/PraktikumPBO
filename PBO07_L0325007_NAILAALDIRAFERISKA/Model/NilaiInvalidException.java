/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aldir
 */
public class NilaiInvalidException extends Exception {

    private final double nilai;

    public NilaiInvalidException(double nilai) {
        super("Nilai tidak valid: " + nilai + " (harus antara 0 sampai 100)");
        this.nilai = nilai;
    }

    public double getNilai() {
        return nilai;
    }
}
