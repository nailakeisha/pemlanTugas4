public class Akun { // Kelas yang merepresentasikan akun pelanggan
    private String nama; // Menyimpan nama pelanggan
    private String nomorPelanggan; // Menyimpan nomor pelanggan
    private int saldo; // Menyimpan saldo akun pelanggan
    private String pin; // Menyimpan PIN akun untuk autentikasi
    private boolean diblokir; // Menyimpan status akun (diblokir atau tidak)
    private int percobaanGagal; // Menyimpan jumlah percobaan autentikasi yang gagal

    // Konstruktor untuk menginisialisasi objek Akun saat dibuat
    public Akun(String nama, String nomorPelanggan, int saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false; // Secara default akun tidak diblokir saat pertama kali dibuat
        this.percobaanGagal = 0; // Jumlah percobaan gagal diinisialisasi ke 0
    }

    // Metode autentikasi berdasarkan PIN
    public boolean autentikasi(String pinInput) {
        if (diblokir) { // Jika akun sudah diblokir, langsung tolak akses
            System.out.println("Akun telah diblokir.");
            return false;
        }

        if (this.pin.equals(pinInput)) { // Jika PIN yang dimasukkan benar
            percobaanGagal = 0; // Reset percobaan gagal
            return true; // Autentikasi berhasil
        } else { // Jika PIN salah
            percobaanGagal++; // Tambah hitungan percobaan gagal

            if (percobaanGagal >= 3) { // Jika sudah gagal 3 kali, blokir akun
                diblokir = true;
                System.out.println("Akun diblokir karena 3x salah memasukkan PIN.");
            } else { // Jika masih di bawah 3 kali salah, beri peringatan
                System.out.println("PIN salah! Percobaan ke-" + percobaanGagal);
            }
            return false; // Autentikasi gagal
        }
    }

    // Getter untuk mendapatkan nomor pelanggan
    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    // Getter untuk mendapatkan saldo akun
    public int getSaldo() {
        return saldo;
    }

    // Metode untuk menambah saldo akun
    public void tambahSaldo(int jumlah) {
        saldo += jumlah;
    }

    // Metode untuk mengurangi saldo akun, dengan batas saldo minimum 10.000
    public boolean kurangiSaldo(int jumlah) {
        if (saldo - jumlah < 10000) { // Jika saldo setelah transaksi kurang dari 10.000, tolak transaksi
            System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            return false;
        }
        saldo -= jumlah; // Jika saldo cukup, kurangi saldo
        return true;
    }

    // Metode untuk mengecek apakah akun dalam kondisi diblokir
    public boolean isDiblokir() {
        return diblokir;
    }
}
