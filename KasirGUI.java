import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Kelas Barang
class Barang {
    private String nama;
    private double harga;
    private int stok;

    public Barang(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
    public void kurangiStok(int jumlah) { stok -= jumlah; }
}

// Kelas Kasir
class Kasir {
    private ArrayList<Barang> daftarBarang;
    public Kasir() { daftarBarang = new ArrayList<>(); }
    public void tambahBarang(Barang b) { daftarBarang.add(b); }
    public ArrayList<Barang> getDaftarBarang() { return daftarBarang; }
}

// Main GUI
public class KasirGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private Kasir kasir;

    public KasirGUI() {
        kasir = new Kasir();
        kasir.tambahBarang(new Barang("Indomie Goreng", 3500, 20));
        kasir.tambahBarang(new Barang("Teh Botol", 5000, 15));
        kasir.tambahBarang(new Barang("Roti Tawar", 12000, 10));

        frame = new JFrame("Mesin Kasir GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        model = new DefaultTableModel(new Object[]{"No", "Nama Barang", "Harga", "Stok"}, 0);
        table = new JTable(model);
        tampilkanBarang();

        JButton beliButton = new JButton("Beli Barang");
        beliButton.addActionListener(e -> beliBarang());

        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        frame.getContentPane().add(beliButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void tampilkanBarang() {
        model.setRowCount(0);
        ArrayList<Barang> daftar = kasir.getDaftarBarang();
        for (int i = 0; i < daftar.size(); i++) {
            Barang b = daftar.get(i);
            model.addRow(new Object[]{i + 1, b.getNama(), b.getHarga(), b.getStok()});
        }
    }

    private void beliBarang() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih barang terlebih dahulu!");
            return;
        }
        Barang b = kasir.getDaftarBarang().get(row);
        String jumlahStr = JOptionPane.showInputDialog(frame, "Masukkan jumlah:");
        try {
            int jumlah = Integer.parseInt(jumlahStr);
            if (jumlah <= 0) throw new NumberFormatException();
            if (jumlah > b.getStok()) {
                JOptionPane.showMessageDialog(frame, "Stok tidak cukup!");
            } else {
                b.kurangiStok(jumlah);
                JOptionPane.showMessageDialog(frame, "Berhasil membeli " + jumlah + " " + b.getNama() +
                        "\nTotal: Rp " + (b.getHarga() * jumlah));
                tampilkanBarang();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KasirGUI::new);
    }
}