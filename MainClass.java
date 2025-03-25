import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Akun> akunMap = new HashMap<>();

        // Menambahkan beberapa akun contoh dengan nomor pelanggan 8 digit
        akunMap.put("38123456", new Akun("rere", "38123456", 5000000, "4569"));
        akunMap.put("56123456", new Akun("sila", "56123456", 2000000, "8123"));
        akunMap.put("74123456", new Akun("renaz", "74123456", 3000000, "7465"));

        // Meminta input nomor pelanggan
        System.out.print("Masukkan nomor pelanggan: ");
        String nomor = scanner.next();
        Akun akun = akunMap.get(nomor);

        // Jika nomor pelanggan tidak ditemukan
        if (akun == null) {
            System.out.println("Nomor pelanggan tidak ditemukan!");
            return;
        }

        // Periksa apakah akun sudah diblokir sebelum meminta PIN
        if (akun.isDiblokir()) {
            System.out.println("Akun ini telah diblokir dan tidak bisa digunakan.");
            return;
        }

        // Looping autentikasi PIN (maksimal 3 kali)
        boolean isAuthenticated = false;
        for (int i = 0; i < 3; i++) {
            System.out.print("Masukkan PIN: ");
            String pin = scanner.next();
            if (akun.autentikasi(pin)) {
                isAuthenticated = true;
                break;
            }
        }

        // Jika gagal autentikasi 3 kali, akun diblokir
        if (!isAuthenticated) {
            System.out.println("Autentikasi gagal! Akun diblokir.");
            return;
        }

        // Looping menu transaksi
        while (true) {
            System.out.println("\n=== MENU TRANSAKSI ===");
            System.out.println("1. Beli Barang");
            System.out.println("2. Top-Up Saldo");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();

            if (pilihan == 3) {
                System.out.println("Terima kasih! Program selesai.");
                break;
            }

            System.out.print("Masukkan jumlah: ");
            int jumlah = scanner.nextInt();

            if (pilihan == 1) {
                TransaksiPengguna.beli(akun, jumlah);
            } else if (pilihan == 2) {
                TransaksiPengguna.topUp(akun, jumlah);
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
}