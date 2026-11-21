package lab.kasir;

/** Tahap 1: class logika (tanpa kode GUI). */
public class Pesanan {
    private double subtotal;

    public void tambahItem(double harga, int jumlah) {
        subtotal += harga * jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    /** Diskon 10% jika subtotal lebih dari 50.000. */
    public double hitungTotal() {
        if (subtotal > 50000) {
            return subtotal - (subtotal * 0.10);
        }
        return subtotal;
    }

    public void reset() {
        subtotal = 0;
    }
}
