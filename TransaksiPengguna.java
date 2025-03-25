public class TransaksiPengguna {
    // Metode untuk menambahkan saldo ke akun pengguna
    public static void topUp(Akun akun, int jumlah) {
        akun.tambahSaldo(jumlah);
        System.out.println("Top-up berhasil! Saldo sekarang: " + akun.getSaldo());
    }

    // Metode untuk menghitung cashback berdasarkan jenis pelanggan dan jumlah transaksi
    private static int hitungCashback(String nomorPelanggan, int jumlah) {
        int cashback = 0;
        String jenis = nomorPelanggan.substring(0, 2); // Ambil 2 digit pertama untuk tipe pelanggan

        if (jumlah > 1000000) { // Jika pembelian di atas 1 juta
            switch (jenis) {
                case "38": cashback = (int) (jumlah * 0.05); break; // Silver mendapatkan 5%
                case "56": cashback = (int) (jumlah * 0.07); break; // Gold mendapatkan 7%
                case "74": cashback = (int) (jumlah * 0.10); break; // Platinum mendapatkan 10%
            }
        } else { // Jika pembelian di bawah atau sama dengan 1 juta
            switch (jenis) {
                case "56": cashback = (int) (jumlah * 0.02); break; // Gold mendapatkan 2%
                case "74": cashback = (int) (jumlah * 0.05); break; // Platinum mendapatkan 5%
            }
        }
        return cashback;
    }

    // Metode untuk melakukan pembelian dan memberikan cashback jika memenuhi syarat
    public static void beli(Akun akun, int jumlah) {
        if (akun.kurangiSaldo(jumlah)) { // Jika saldo cukup untuk transaksi
            int cashback = hitungCashback(akun.getNomorPelanggan(), jumlah);
            akun.tambahSaldo(cashback); // Tambahkan cashback ke saldo
            System.out.println("Pembelian berhasil! Cashback: " + cashback + ", Saldo sekarang: " + akun.getSaldo());
        }
    }
}