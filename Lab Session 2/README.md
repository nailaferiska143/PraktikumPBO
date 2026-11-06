**Deskripsi Program**



Sistem Rental Kendaraan adalah program berbasis konsol yang dibuat dengan bahasa Java untuk mensimulasikan pengelolaan usaha penyewaan kendaraan. Program ini menyelesaikan permasalahan sederhana yang sering dihadapi usaha rental, yaitu mencatat data kendaraan, memantau status ketersediaannya, serta menghitung biaya sewa yang berbeda untuk setiap jenis kendaraan. Program dijalankan secara interaktif melalui menu, sehingga pengguna dapat memasukkan data sendiri lewat keyboard.



Pengguna dapat melihat daftar kendaraan, menambah kendaraan baru (mobil, motor, atau truk), menyewa kendaraan dengan pilihan diskon, mengembalikan kendaraan, melakukan servis, dan mengubah tarif sewa. Program juga memvalidasi input, misalnya menolak tarif yang nol atau negatif, menolak penyewaan kendaraan yang sedang disewa, dan menangani input yang bukan angka tanpa membuat program berhenti. Semua data disimpan di memori selama program berjalan (tidak memakai database atau file). Program ini menerapkan seluruh konsep Pemrograman Berorientasi Objek yang diwajibkan:

* Encapsulation: atribut seperti nama, plat nomor, tarif, dan status ketersediaan dibuat private, dan hanya dapat diakses atau diubah melalui getter, setter, serta method sewa() dan kembalikan().
* Inheritance: abstract class Kendaraan menjadi superclass bagi tiga subclass, yaitu Mobil, Motor, dan Truk, yang memakai extends dan memanggil super() pada constructor-nya.
* Polymorphism: method hitungBiayaSewa() dan tampilkanInfo() di-override dengan @Override di setiap subclass, sedangkan hitungBiayaSewa() juga di-overload dengan versi yang menerima parameter diskon. Runtime polymorphism ditunjukkan lewat objek Mobil, Motor, dan Truk yang disimpan dalam satu ArrayList<Kendaraan>.
* Abstract class: Kendaraan memiliki abstract method hitungBiayaSewa(int hari) yang wajib diimplementasikan setiap subclass sesuai rumus biayanya masing-masing.
* Interface: Perawatan mendeklarasikan method lakukanServis() dan getJadwalServis(), yang diimplementasikan oleh ketiga subclass dengan isi servis dan jadwal yang berbeda.



**Fitur**

|Menu|Fungsi|
|-|-|
|1|Lihat daftar kendaraan beserta status (Tersedia/Disewa)|
|2|Tambah kendaraan baru (Mobil, Motor, atau Truk)|
|3|Sewa kendaraan, hitung total biaya, dengan opsi diskon|
|4|Kembalikan kendaraan yang sedang disewa|
|5|Servis kendaraan dan lihat jadwal servis berikutnya|
|6|Ubah tarif sewa per hari|
|0|Keluar dari program|



**Struktur Class**

|File|Jenis|Keterangan|
|-|-|-|
|Kendaraan.java|Abstract class (superclass)|Atribut umum, getter/setter, abstract method hitungBiayaSewa(int)|
|Mobil.java|Subclass|Biaya sewa + asuransi Rp50.000|
|Motor.java|Subclass|Biaya helm Rp10.000/hari|
|Truk.java|Subclass|Biaya muatan Rp25.000/ton|
|Perawatan.java|Interface|lakukanServis() dan getJadwalServis()|
|Main.java|Class utama|Menu interaktif dengan Scanner|



**Penerapan Konsep OOP (dengan Kode)**

1. Encapsulation
Atribut dibuat `private` sehingga tidak bisa diubah langsung dari luar class. Perubahan hanya lewat method yang menyertakan aturan.

```java
private double tarifPerHari;

public void setTarifPerHari(double tarifPerHari) {
    if (tarifPerHari > 0) {
        this.tarifPerHari = tarifPerHari;
    } else {
        System.out.println("Tarif harus lebih dari 0!");
    }
}
```

Status `tersedia` juga tidak punya setter. Satu-satunya cara mengubahnya adalah lewat `sewa()` dan `kembalikan()`, sehingga tidak mungkin ada kendaraan yang "disewa dua kali":

```java
public void sewa() {
    if (tersedia) {
        tersedia = false;
        System.out.println(nama + " berhasil disewa.");
    } else {
        System.out.println(nama + " sedang tidak tersedia.");
    }
}
```
2. Inheritance
Subclass memakai `extends` dan memanggil constructor superclass dengan `super()` sebagai baris pertama.

```java
public class Mobil extends Kendaraan implements Perawatan {
    private int jumlahPenumpang;

    public Mobil(String nama, String platNomor, double tarifPerHari, int jumlahPenumpang) {
        super(nama, platNomor, tarifPerHari);   // mengisi atribut milik Kendaraan
        this.jumlahPenumpang = jumlahPenumpang; // atribut milik Mobil sendiri
    }
}
```

`Mobil`, `Motor`, dan `Truk` otomatis mewarisi `getNama()`, `sewa()`, `kembalikan()`, dan seterusnya tanpa menulis ulang.
3. Polymorphism
a) Overriding. Subclass menulis ulang method milik superclass dengan anotasi `@Override`.

```java
// di Motor.java
@Override
public double hitungBiayaSewa(int hari) {
    double total = getTarifPerHari() \* hari;
    if (termasukHelm) total += 10000 \* hari;
    return total;
}
```

`tampilkanInfo()` juga di-override. Versi subclass memanggil `super.tampilkanInfo()` lebih dulu, lalu menambahkan data khususnya.

b) Overloading. Dua method bernama sama dengan parameter berbeda di `Kendaraan`.

```java
public abstract double hitungBiayaSewa(int hari);                 // 1 parameter

public double hitungBiayaSewa(int hari, double diskonPersen) {    // 2 parameter
    double total = hitungBiayaSewa(hari);
    return total - (total \* diskonPersen / 100);
}
```

Java memilih versi mana yang dipanggil berdasarkan jumlah dan tipe argumen. Di `Main`, jika diskon > 0 dipakai versi 2 parameter, jika tidak dipakai versi 1 parameter.

c) Runtime polymorphism. Objek subclass disimpan di referensi superclass.

```java
private static final ArrayList<Kendaraan> daftar = new ArrayList<>();

daftar.add(new Mobil("Toyota Avanza", "AD 1234 AB", 350000, 7));
daftar.add(new Motor("Honda Vario", "AD 5678 CD", 80000, true));
daftar.add(new Truk("Mitsubishi Colt Diesel", "AD 9012 EF", 600000, 4.5));

daftar.get(i).tampilkanInfo();   // tipe variabel: Kendaraan
```

Meskipun tipe variabelnya `Kendaraan`, Java memutuskan saat program berjalan (runtime) versi `tampilkanInfo()` mana yang dipakai, yaitu versi milik `Mobil`, `Motor`, atau `Truk` sesuai objek aslinya. Kode di `Main` tidak perlu `if` untuk membedakan jenis kendaraan.
4. Abstract Class
`Kendaraan` dideklarasikan `abstract` dan memiliki satu abstract method tanpa isi.

```java
public abstract class Kendaraan {
    public abstract double hitungBiayaSewa(int hari);
}
```

Alasannya: kendaraan "umum" tidak punya rumus biaya yang jelas, setiap jenis berbeda. Dengan abstract method, Java memaksa setiap subclass menyediakan rumusnya sendiri. Jika lupa, kode tidak bisa di-compile.
5. Interface
```java
public interface Perawatan {
    void lakukanServis();
    String getJadwalServis();
}
```

Ketiga subclass memakai `implements Perawatan` dan wajib menulis kedua method tersebut (dengan `@Override`). Di `Main`, kendaraan diakses lewat tipe interface:

```java
Perawatan p = (Perawatan) k;
p.lakukanServis();
System.out.println("Jadwal servis berikutnya: " + p.getJadwalServis());
```

Perbedaan peran: abstract class menjawab "kendaraan itu apa" (punya nama, plat, tarif), sedangkan interface menjawab "kendaraan itu bisa apa" (bisa diservis).





**Output**

```text



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 1



No. 1

[MOBIL]

Kendaraan : Toyota Avanza (AD 1234 AB)

Tarif/hari: Rp350.000

Status    : Tersedia

Penumpang : 7 orang

----------------------------

No. 2

[MOTOR]

Kendaraan : Honda Vario (AD 5678 CD)

Tarif/hari: Rp80.000

Status    : Tersedia

Helm      : Termasuk

----------------------------

No. 3

[TRUK]

Kendaraan : Mitsubishi Colt Diesel (AD 9012 EF)

Tarif/hari: Rp600.000

Status    : Tersedia

Kapasitas : 4.5 ton

----------------------------

No. 4

[MOBIL]

Kendaraan : Honda Brio (AD 3456 GH)

Tarif/hari: Rp300.000

Status    : Tersedia

Penumpang : 5 orang

----------------------------



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 2



Jenis kendaraan: 1. Mobil  2. Motor  3. Truk

Pilih jenis: 1

Nama kendaraan : Innova Reborn

Plat nomor     : B 2409 AA

Tarif per hari : 750000

Jumlah penumpang: 8

Kendaraan Innova Reborn berhasil ditambahkan.



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 2



Jenis kendaraan: 1. Mobil  2. Motor  3. Truk

Pilih jenis: 2

Nama kendaraan : Yamaha Aerox

Plat nomor     : AA 1203 BF

Tarif per hari : 100000

Termasuk helm? (y/n): y

Kendaraan Yamaha Aerox berhasil ditambahkan.



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 3



1. Toyota Avanza (AD 1234 AB) - Tersedia

2. Honda Vario (AD 5678 CD) - Tersedia

3. Mitsubishi Colt Diesel (AD 9012 EF) - Tersedia

4. Honda Brio (AD 3456 GH) - Tersedia

5. Innova Reborn (B 2409 AA) - Tersedia

6. Yamaha Aerox (AA 1203 BF) - Tersedia

Pilih nomor kendaraan: 6

Lama sewa (hari): 2

Diskon % (0 jika tidak ada): 10

Yamaha Aerox berhasil disewa.

Total biaya 2 hari: Rp198.000



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 3



1. Toyota Avanza (AD 1234 AB) - Tersedia

2. Honda Vario (AD 5678 CD) - Tersedia

3. Mitsubishi Colt Diesel (AD 9012 EF) - Tersedia

4. Honda Brio (AD 3456 GH) - Tersedia

5. Innova Reborn (B 2409 AA) - Tersedia

6. Yamaha Aerox (AA 1203 BF) - Disewa

Pilih nomor kendaraan: 4

Lama sewa (hari): 1

Diskon % (0 jika tidak ada): 0

Honda Brio berhasil disewa.

Total biaya 1 hari: Rp350.000



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 4



1. Toyota Avanza (AD 1234 AB) - Tersedia

2. Honda Vario (AD 5678 CD) - Tersedia

3. Mitsubishi Colt Diesel (AD 9012 EF) - Tersedia

4. Honda Brio (AD 3456 GH) - Disewa

5. Innova Reborn (B 2409 AA) - Tersedia

6. Yamaha Aerox (AA 1203 BF) - Disewa

Pilih nomor kendaraan: 4

Honda Brio telah dikembalikan.



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 5



1. Toyota Avanza (AD 1234 AB) - Tersedia

2. Honda Vario (AD 5678 CD) - Tersedia

3. Mitsubishi Colt Diesel (AD 9012 EF) - Tersedia

4. Honda Brio (AD 3456 GH) - Tersedia

5. Innova Reborn (B 2409 AA) - Tersedia

6. Yamaha Aerox (AA 1203 BF) - Disewa

Pilih nomor kendaraan: 5

Servis mobil Innova Reborn: ganti oli, cek rem, dan AC.

Jadwal servis berikutnya: Setiap 5.000 km



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 6



1. Toyota Avanza (AD 1234 AB) - Tersedia

2. Honda Vario (AD 5678 CD) - Tersedia

3. Mitsubishi Colt Diesel (AD 9012 EF) - Tersedia

4. Honda Brio (AD 3456 GH) - Tersedia

5. Innova Reborn (B 2409 AA) - Tersedia

6. Yamaha Aerox (AA 1203 BF) - Disewa

Pilih nomor kendaraan: 1

Tarif baru per hari: 400000

Tarif Toyota Avanza sekarang: Rp400.000



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 1



No. 1

[MOBIL]

Kendaraan : Toyota Avanza (AD 1234 AB)

Tarif/hari: Rp400.000

Status    : Tersedia

Penumpang : 7 orang

----------------------------

No. 2

[MOTOR]

Kendaraan : Honda Vario (AD 5678 CD)

Tarif/hari: Rp80.000

Status    : Tersedia

Helm      : Termasuk

----------------------------

No. 3

[TRUK]

Kendaraan : Mitsubishi Colt Diesel (AD 9012 EF)

Tarif/hari: Rp600.000

Status    : Tersedia

Kapasitas : 4.5 ton

----------------------------

No. 4

[MOBIL]

Kendaraan : Honda Brio (AD 3456 GH)

Tarif/hari: Rp300.000

Status    : Tersedia

Penumpang : 5 orang

----------------------------

No. 5

[MOBIL]

Kendaraan : Innova Reborn (B 2409 AA)

Tarif/hari: Rp750.000

Status    : Tersedia

Penumpang : 8 orang

----------------------------

No. 6

[MOTOR]

Kendaraan : Yamaha Aerox (AA 1203 BF)

Tarif/hari: Rp100.000

Status    : Disewa

Helm      : Termasuk

----------------------------



========= RENTAL KENDARAAN =========

1. Lihat daftar kendaraan

2. Tambah kendaraan baru

3. Sewa kendaraan

4. Kembalikan kendaraan

5. Servis kendaraan

6. Ubah tarif kendaraan

0. Keluar

====================================

Pilih menu: 0



Terima kasih telah menggunakan program ini!

