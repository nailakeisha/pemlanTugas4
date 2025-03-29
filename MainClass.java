import java.util.Scanner; // Mengimpor kelas Scanner untuk membaca input dari pengguna

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk menerima input dari pengguna
        
        // Membuat array dari objek Akun dengan 3 akun yang memiliki nama, nomor pelanggan, saldo, dan PIN
        Akun[] akunList = {
            new Akun("Raihanah", "7892541298", 5000000, "8543"),
            new Akun("Celacl", "8125469872", 2000000, "7895"),
            new Akun("Vidynr", "0124987354", 3000000, "6541")
        };
        
        System.out.print("Masukkan nomor pelanggan: "); // Meminta pengguna memasukkan nomor pelanggan
        String nomor = scanner.next(); // Membaca nomor pelanggan dari input pengguna
        
        Akun akun = null; // Variabel untuk menyimpan akun yang cocok dengan nomor pelanggan
        for (Akun a : akunList) { // Loop untuk mencari akun dengan nomor pelanggan yang sesuai
            if (a.getNomorPelanggan().equals(nomor)) { // Mengecek apakah nomor pelanggan cocok
                akun = a; // Jika cocok, menyimpan akun ke variabel 'akun'
                break; // Keluar dari loop setelah menemukan akun
            }
        }
        
        if (akun == null) { // Jika akun tidak ditemukan, menampilkan pesan dan menghentikan program
            System.out.println("Nomor pelanggan tidak ditemukan!");
            scanner.close(); // Menutup Scanner untuk menghindari kebocoran sumber daya
            return; // Menghentikan eksekusi program
        }

        boolean autentikasi = false; // Variabel untuk menyimpan status autentikasi
        for (int i = 0; i < 3; i++) { // Loop maksimal 3 kali untuk memasukkan PIN
            System.out.print("Masukkan PIN: "); // Meminta pengguna memasukkan PIN
            String pin = scanner.next(); // Membaca PIN dari input pengguna
            if (akun.autentikasi(pin)) { // Mengecek apakah PIN benar dengan metode autentikasi di kelas Akun
                autentikasi = true; // Jika benar, set autentikasi menjadi true
                break; // Keluar dari loop karena autentikasi berhasil
            }
        }

        if (!autentikasi) { // Jika setelah 3 kali percobaan autentikasi gagal
            System.out.println("Autentikasi gagal! Akun diblokir."); // Menampilkan pesan kegagalan
            scanner.close(); // Menutup Scanner
            return; // Menghentikan eksekusi program
        }

        // Jika autentikasi berhasil, masuk ke menu transaksi
        while (true) { // Loop terus menerus sampai program dihentikan secara manual
            System.out.println("\n=== MENU TRANSAKSI ==="); // Menampilkan menu transaksi
            System.out.println("1. Pembelian"); // Opsi pertama: pembelian
            System.out.println("2. Top-Up Saldo"); // Opsi kedua: menambah saldo
            System.out.print("Pilih menu: "); // Meminta pengguna memilih menu
            int pilihan = scanner.nextInt(); // Membaca pilihan menu dari input pengguna

            System.out.print("Masukkan jumlah: "); // Meminta pengguna memasukkan jumlah transaksi
            int jumlah = scanner.nextInt(); // Membaca jumlah transaksi dari input pengguna

            if (pilihan == 1) { // Jika pengguna memilih opsi 1 (pembelian)
                TransaksiPengguna.beli(akun, jumlah); // Memanggil metode beli pada kelas TransaksiPengguna
            } else if (pilihan == 2) { // Jika pengguna memilih opsi 2 (top-up saldo)
                TransaksiPengguna.topUp(akun, jumlah); // Memanggil metode topUp pada kelas TransaksiPengguna
            } else { // Jika pengguna memasukkan pilihan yang tidak valid
                System.out.println("Pilihan tidak valid!"); // Menampilkan pesan error
            }
        }
    }
}
