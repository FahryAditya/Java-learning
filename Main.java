import java.util.ArrayList;
import java.util.Scanner;

// Kelas Buku
class Buku {
    private String judul;
    private String pengarang;
    private boolean statusDipinjam;

    // Constructor
    public Buku(String judul, String pengarang) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.statusDipinjam = false;
    }

    // Getter dan Setter
    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public boolean isDipinjam() {
        return statusDipinjam;
    }

    public void pinjam() {
        statusDipinjam = true;
    }

    public void kembalikan() {
        statusDipinjam = false;
    }
}

// Kelas Perpustakaan
class Perpustakaan {
    private ArrayList<Buku> daftarBuku;

    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public void tampilkanBuku() {
        System.out.println("Daftar Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            Buku buku = daftarBuku.get(i);
            String status = buku.isDipinjam() ? "Dipinjam" : "Tersedia";
            System.out.println((i + 1) + ". " + buku.getJudul() + " - " + buku.getPengarang() + " [" + status + "]");
        }
    }

    public void pinjamBuku(int nomor) {
        if (nomor >= 1 && nomor <= daftarBuku.size()) {
            Buku buku = daftarBuku.get(nomor - 1);
            if (!buku.isDipinjam()) {
                buku.pinjam();
                System.out.println("Berhasil meminjam buku: " + buku.getJudul());
            } else {
                System.out.println("Buku sudah dipinjam.");
            }
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }

    public void kembalikanBuku(int nomor) {
        if (nomor >= 1 && nomor <= daftarBuku.size()) {
            Buku buku = daftarBuku.get(nomor - 1);
            if (buku.isDipinjam()) {
                buku.kembalikan();
                System.out.println("Buku dikembalikan: " + buku.getJudul());
            } else {
                System.out.println("Buku ini belum dipinjam.");
            }
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        // Tambah beberapa buku awal
        perpustakaan.tambahBuku(new Buku("Harry Potter", "J.K. Rowling"));
        perpustakaan.tambahBuku(new Buku("Laskar Pelangi", "Andrea Hirata"));
        perpustakaan.tambahBuku(new Buku("The Lord of The Rings", "J.R.R. Tolkien"));

        int pilihan = 0;
        do {
            System.out.println("\n=== Sistem Perpustakaan ===");
            System.out.println("1. Lihat Daftar Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    perpustakaan.tampilkanBuku();
                    break;
                case 2:
                    System.out.print("Masukkan nomor buku yang ingin dipinjam: ");
                    int pinjamNomor = sc.nextInt();
                    perpustakaan.pinjamBuku(pinjamNomor);
                    break;
                case 3:
                    System.out.print("Masukkan nomor buku yang ingin dikembalikan: ");
                    int kembaliNomor = sc.nextInt();
                    perpustakaan.kembalikanBuku(kembaliNomor);
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);

        sc.close();
    }
}