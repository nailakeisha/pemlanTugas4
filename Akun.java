public class Akun {
    private String nama;
    private String nomorPelanggan;
    private int saldo;
    private String pin;
    private boolean diblokir;
    private int percobaanGagal;

    public Akun(String nama, String nomorPelanggan, int saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false;
        this.percobaanGagal = 0;
    }

    // Metode autentikasi berdasarkan PIN, jika salah 3 kali, akun diblokir
    public boolean autentikasi(String pinInput) {
        if (diblokir) {
            System.out.println("Akun telah diblokir.");
            return false;
        }
        if (this.pin.equals(pinInput)) {
            percobaanGagal = 0;
            return true;
        } else {
            percobaanGagal++;
            if (percobaanGagal >= 3) {
                diblokir = true;
                System.out.println("Akun diblokir karena 3x salah memasukkan PIN.");
            } else {
                System.out.println("PIN salah! Percobaan ke-" + percobaanGagal);
            }
            return false;
        }
    }

    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public int getSaldo() {
        return saldo;
    }

    public void tambahSaldo(int jumlah) {
        saldo += jumlah;
    }

    public boolean kurangiSaldo(int jumlah) {
        if (saldo - jumlah < 10000) {
            System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    public boolean isDiblokir() {
        return diblokir;
    }
} 