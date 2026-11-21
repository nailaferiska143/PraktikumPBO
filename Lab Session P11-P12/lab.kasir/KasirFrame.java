package lab.kasir;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/** Tahap 3 (GUI) dan Tahap 4 (cetak struk dengan thread). */
public class KasirFrame extends JFrame {
    private JTextField txtMenu, txtHarga, txtJumlah;
    private JTextArea areaStruk;
    private JLabel lblTotal;
    private JLabel lblStatus;
    private JButton btnTambah, btnHitung, btnCetak;
    private Pesanan pesanan = new Pesanan();
    private DecimalFormat rupiah = new DecimalFormat("#,##0",
            new DecimalFormatSymbols(Locale.forLanguageTag("id-ID")));

    public KasirFrame() {
        super("Kasir Kantin UNS");
        setSize(400, 450);
        setLayout(null);

        // --- Tahap 3: komponen ---
        JLabel lblJudul = new JLabel("Kasir Kantin UNS");
        lblJudul.setBounds(20, 10, 300, 25);

        JLabel lblMenu = new JLabel("Nama Menu :");
        lblMenu.setBounds(20, 50, 90, 25);
        txtMenu = new JTextField();
        txtMenu.setBounds(120, 50, 240, 25);

        JLabel lblHarga = new JLabel("Harga     :");
        lblHarga.setBounds(20, 85, 90, 25);
        txtHarga = new JTextField();
        txtHarga.setBounds(120, 85, 240, 25);

        JLabel lblJumlah = new JLabel("Jumlah    :");
        lblJumlah.setBounds(20, 120, 90, 25);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(120, 120, 240, 25);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(20, 160, 100, 30);
        btnHitung = new JButton("Hitung Total");
        btnHitung.setBounds(130, 160, 120, 30);
        btnCetak = new JButton("Cetak Struk");
        btnCetak.setBounds(260, 160, 110, 30);

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaStruk);
        scroll.setBounds(20, 205, 350, 120);

        lblTotal = new JLabel("Total Bayar : Rp 0");
        lblTotal.setBounds(20, 335, 350, 25);

        lblStatus = new JLabel("Status : -");
        lblStatus.setBounds(20, 370, 350, 25);

        add(lblJudul); add(lblMenu); add(txtMenu);
        add(lblHarga); add(txtHarga);
        add(lblJumlah); add(txtJumlah);
        add(btnTambah); add(btnHitung); add(btnCetak);
        add(scroll); add(lblTotal); add(lblStatus);

        // --- Tombol Tambah ---
        btnTambah.addActionListener(e -> {
            String menu = txtMenu.getText().trim();
            String hargaStr = txtHarga.getText().trim();
            String jumlahStr = txtJumlah.getText().trim();

            if (menu.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi.");
                return;
            }
            try {
                double harga = Double.parseDouble(hargaStr);
                int jumlah = Integer.parseInt(jumlahStr);
                pesanan.tambahItem(harga, jumlah);
                areaStruk.append(menu + "  " + rupiah.format(harga) + " x " + jumlah
                        + " = " + rupiah.format(harga * jumlah) + "\n");
                txtMenu.setText("");
                txtHarga.setText("");
                txtJumlah.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka.");
            }
        });

        // --- Tombol Hitung Total ---
        btnHitung.addActionListener(e ->
                lblTotal.setText("Total Bayar : Rp " + rupiah.format(pesanan.hitungTotal())));

        // --- Tahap 4: Cetak Struk di thread baru ---
        btnCetak.addActionListener(e -> {
            new Thread(() -> {
                SwingUtilities.invokeLater(() -> lblStatus.setText("Status : Mencetak struk..."));
                try { Thread.sleep(2000); } catch (InterruptedException ex) { return; }
                SwingUtilities.invokeLater(() -> lblStatus.setText("Status : Struk selesai"));
            }).start();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
