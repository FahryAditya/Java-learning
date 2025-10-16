import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 📅 To-Do List App - Versi Console
 * 
 * Fitur:
 * 1. Tambah tugas dengan tanggal & waktu
 * 2. Lihat daftar tugas
 * 3. Hapus tugas
 * 4. Keluar
 */

class Task {
    private String title;
    private LocalDate date;
    private LocalTime time;

    public Task(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(
            "📌 %s | 🗓 %s | ⏰ %s",
            title,
            date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            time.format(DateTimeFormatter.ofPattern("HH:mm"))
        );
    }
}

public class TodoListApp {

    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n======= 📋 TO-DO LIST APP =======");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Daftar Tugas");
            System.out.println("3. Hapus Tugas");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            // Cegah input non-angka
            while (!input.hasNextInt()) {
                System.out.print("Masukkan angka 1-4: ");
                input.next();
            }
            pilihan = input.nextInt();
            input.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    tambahTugas();
                    break;
                case 2:
                    tampilkanTugas();
                    break;
                case 3:
                    hapusTugas();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan To-Do List App!");
                    break;
                default:
                    System.out.println("❌ Pilihan tidak valid. Coba lagi.");
            }

        } while (pilihan != 4);
    }

    private static void tambahTugas() {
        System.out.print("\nJudul tugas: ");
        String title = input.nextLine();

        System.out.print("Tanggal (format: yyyy-mm-dd): ");
        String tanggalStr = input.nextLine();
        LocalDate tanggal;
        try {
            tanggal = LocalDate.parse(tanggalStr);
        } catch (Exception e) {
            System.out.println("❌ Format tanggal salah! Gunakan yyyy-mm-dd.");
            return;
        }

        System.out.print("Waktu (format: HH:mm): ");
        String waktuStr = input.nextLine();
        LocalTime waktu;
        try {
            waktu = LocalTime.parse(waktuStr);
        } catch (Exception e) {
            System.out.println("❌ Format waktu salah! Gunakan HH:mm.");
            return;
        }

        Task newTask = new Task(title, tanggal, waktu);
        tasks.add(newTask);
        System.out.println("✅ Tugas berhasil ditambahkan!");
    }

    private static void tampilkanTugas() {
        System.out.println("\n======= 📜 DAFTAR TUGAS =======");
        if (tasks.isEmpty()) {
            System.out.println("Belum ada tugas yang ditambahkan.");
        } else {
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i++ + ". " + task);
            }
        }
    }

    private static void hapusTugas() {
        tampilkanTugas();
        if (tasks.isEmpty()) return;

        System.out.print("\nMasukkan nomor tugas yang ingin dihapus: ");
        int index = input.nextInt();
        input.nextLine();

        if (index > 0 && index <= tasks.size()) {
            Task removed = tasks.remove(index - 1);
            System.out.println("🗑️  Tugas \"" + removed.getTitle() + "\" telah dihapus.");
        } else {
            System.out.println("❌ Nomor tidak valid!");
        }
    }
}
