public class TransaksiPengguna { // Kelas untuk menangani transaksi pengguna
    
    // Metode untuk menambah saldo akun
    public static void topUp(Akun akun, int jumlah) {
        akun.tambahSaldo(jumlah); // Memanggil metode tambahSaldo pada objek akun
        System.out.println("Top-up berhasil! Saldo anda sekarang: " + akun.getSaldo()); // Menampilkan saldo setelah top-up
    }

    // Metode untuk melakukan pembelian
    public static void beli(Akun akun, int jumlah) {
        if (akun.kurangiSaldo(jumlah)) { // Memeriksa apakah saldo cukup dan berhasil dikurangi
            int cashback = hitungCashback(akun.getNomorPelanggan(), jumlah); // Menghitung cashback berdasarkan nomor pelanggan dan jumlah transaksi
            akun.tambahSaldo(cashback); // Menambahkan cashback ke saldo akun
            System.out.println("Pembelian berhasil! Anda Mendapatkan Cashback Sebesar: " + cashback + ", Saldo sekarang: " + akun.getSaldo()); // Menampilkan jumlah cashback dan saldo terbaru
        }
    }

    // Metode privat untuk menghitung cashback berdasarkan nomor pelanggan dan jumlah transaksi
    private static int hitungCashback(String nomorPelanggan, int jumlah) {
        int cashback = 0; // Inisialisasi nilai cashback
        String jenis = nomorPelanggan.substring(0, 2); // Mengambil 2 digit pertama dari nomor pelanggan
        
        // Jika jumlah transaksi lebih dari 1 juta, cashback dihitung berdasarkan jenis nomor pelanggan
        if (jumlah > 1000000) {
            switch (jenis) {
                case "38": cashback = (int) (jumlah * 0.05); break; // Jika nomor pelanggan diawali "38", cashback 5%
                case "56": cashback = (int) (jumlah * 0.07); break; // Jika nomor pelanggan diawali "56", cashback 7%
                case "74": cashback = (int) (jumlah * 0.10); break; // Jika nomor pelanggan diawali "74", cashback 10%
            }
        } 
        // Jika jumlah transaksi kurang dari atau sama dengan 1 juta, aturan cashback berbeda
        else {
            switch (jenis) {
                case "56": cashback = (int) (jumlah * 0.02); break; // Jika nomor pelanggan diawali "56", cashback 2%
                case "74": cashback = (int) (jumlah * 0.05); break; // Jika nomor pelanggan diawali "74", cashback 5%
            }
        }
        return cashback; // Mengembalikan nilai cashback yang dihitung
    }
}
