**Sistem Manajemen Perpustakaan Mini**



Sistem Manajemen Perpustakaan Mini adalah aplikasi berbasis Java yang digunakan untuk mengelola data buku, anggota, serta proses peminjaman dan pengembalian buku. Aplikasi ini dibuat untuk menerapkan beberapa konsep dasar Pemrograman Berorientasi Objek (PBO), seperti Class, Object, Constructor, Method, Encapsulation, Primitive Data Type, Reference Data Type, ArrayList, HashMap, Conditional, Looping, Exception Handling, Assertion, Character, dan String. Aplikasi dijalankan melalui console/terminal dengan menu utama yang memungkinkan pengguna melakukan berbagai aktivitas perpustakaan. Menu tersebut terdiri dari tambah buku, melihat daftar buku, mencari buku, meminjam buku, mengembalikan buku, melihat laporan, dan keluar dari aplikasi.



1\. Struktur Package

```

PBO04\_L0325007\_NAILAALDIRAFERISKA

├── model

│   ├── Book.java

│   └── Member.java

├── service

│   └── LibraryService.java

├── exception

│   ├── BookNotFoundException.java

│   ├── BookAlreadyBorrowedException.java

│   ├── BorrowLimitExceededException.java

│   └── MemberNotFoundException.java

└── main

&#x20;   └── MainApp.java

```

2\. Penjelasan 

a. Class Book digunakan untuk merepresentasikan data buku. Atributnya meliputi judul, penulis, tahun terbit, kategori, status ketersediaan, dan jumlah peminjaman.

b. Class Member digunakan untuk menyimpan data anggota dan daftar buku yang sedang dipinjam. Daftar pinjaman disimpan menggunakan ArrayList.

c. Class LibraryService menjadi pusat pengelolaan data dan proses utama perpustakaan. Data buku disimpan menggunakan ArrayList, sedangkan data anggota disimpan menggunakan HashMap.

d. Class BookNotFoundException digunakan ketika buku yang dicari tidak ditemukan atau buku yang akan dikembalikan tidak tercatat dalam daftar pinjaman anggota.

e. Class MemberNotFoundException digunakan ketika ID anggota yang dimasukkan tidak ditemukan dalam data anggota.

f. Class BookAlreadyBorrowedException digunakan ketika pengguna mencoba meminjam buku yang sedang dipinjam oleh anggota lain.

g. Class BorrowLimitExceededException digunakan ketika anggota sudah mencapai batas maksimal peminjaman buku.

h. Class MainApp adalah class utama yang digunakan untuk menjalankan aplikasi. Class ini menangani input pengguna, menampilkan menu, dan memanggil method dari LibraryService.



3\. Konsep yang Diterapkan

a. OOP: class \& object (Book, Member, LibraryService), constructor untuk inisialisasi wajib, method (getter/setter + method bisnis), package per lapisan tanggung jawab, encapsulation (atribut private + getter/setter).

b. Tipe data: 

* Primitive: int (tahunTerbit, jumlahDipinjam, totalTransaksiPinjam), boolean (statusKetersediaan, berjalan), char (Character.toUpperCase).
* Reference: String, ArrayList, HashMap, objek Book/Member.

c. Struktur Kontrol

* Kondisional: if/else (validasi input, cek status buku), switch-case (routing menu).
* Looping: while (loop menu, validasi input tahun), for-each (tampil buku, hitung per kategori, cari buku/anggota terbaik).

d. Exception dan Assertion

* Multi-catch di MainApp: catch (BookNotFoundException | MemberNotFoundException | ...).
* Assertion dipakai di pinjamBuku()/kembalikanBuku():

```

&#x20; assert anggota.isValid() : "Data anggota tidak valid (id/nama kosong)!";

```

e. Manipulasi Character \& String

* toLowerCase() + contains() — pencarian buku tidak case-sensitive.
* equalsIgnoreCase() — pencocokan judul buku secara persis saat transaksi.
* trim() — membersihkan input pengguna dan validasi id/nama.
* Character.toUpperCase(judul.charAt(0)) + substring() — kapitalisasi huruf pertama judul saat ditampilkan.



4.Alur Program

1\. Tambah Buku          -> LibraryService.tambahBuku()

2\. Daftar Buku          -> LibraryService.tampilkanDaftarBuku()

3\. Cari Buku            -> LibraryService.cariBuku() \[judul / kategori]

4\. Pinjam Buku          -> LibraryService.pinjamBuku()  (auto-daftar anggota baru)

5\. Kembalikan Buku      -> LibraryService.kembalikanBuku()

6\. Laporan Perpustakaan -> LibraryService.cetakLaporan()

7\. Keluar



5\. Output

```================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 1

Judul buku       : Laut Bercerita

Penulis          : Leila S. Chudori

Tahun terbit     : 2017

Kategori         : Novel

Buku "Laut Bercerita" berhasil ditambahkan ke koleksi.



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 2

1\. "Laskar Pelangi" oleh Andrea Hirata (2005) - Kategori: Novel - Status: Tersedia - Dipinjam: 0 kali

2\. "Bumi Manusia" oleh Pramoedya Ananta Toer (1980) - Kategori: Novel - Status: Tersedia - Dipinjam: 0 kali

3\. "Filosofi Teras" oleh Henry Manampiring (2018) - Kategori: Pengembangan Diri - Status: Tersedia - Dipinjam: 0 kali

4\. "Belajar Java Dasar" oleh Budi Santoso (2021) - Kategori: Teknologi - Status: Tersedia - Dipinjam: 0 kali

5\. "Algoritma \& Pemrograman" oleh Rinaldi Munir (2019) - Kategori: Teknologi - Status: Tersedia - Dipinjam: 0 kali

6\. "Laut Bercerita" oleh Leila S. Chudori (2017) - Kategori: Novel - Status: Tersedia - Dipinjam: 0 kali



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 3

Cari berdasarkan (1) Judul atau (2) Kategori? 1

Masukkan kata kunci: Bumi Manusia

Ditemukan 1 buku:

&#x20; 1. "Bumi Manusia" oleh Pramoedya Ananta Toer (1980) - Kategori: Novel - Status: Tersedia - Dipinjam: 0 kali



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 3

Cari berdasarkan (1) Judul atau (2) Kategori? 2

Masukkan kata kunci: Teknologi

Ditemukan 2 buku:

&#x20; 1. "Belajar Java Dasar" oleh Budi Santoso (2021) - Kategori: Teknologi - Status: Tersedia - Dipinjam: 0 kali

&#x20; 2. "Algoritma \& Pemrograman" oleh Rinaldi Munir (2019) - Kategori: Teknologi - Status: Tersedia - Dipinjam: 0 kali



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 4

ID Anggota    : 143

Anggota belum terdaftar. Masukkan nama untuk mendaftar (kosongkan untuk batal): Nana

Anggota baru "Nana" berhasil didaftarkan.

Judul buku yang dipinjam: Laut Bercerita

Peminjaman berhasil! Selamat membaca.



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 5

ID Anggota    : 143

Judul buku yang dikembalikan: Laut Bercerita

Pengembalian berhasil, terima kasih!



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 6

========== LAPORAN PERPUSTAKAAN ==========

Total buku dalam koleksi   : 6

Total anggota terdaftar    : 3

Total transaksi peminjaman : 1



\-- Jumlah Buku per Kategori --

&#x20; Pengembangan Diri : 1 buku

&#x20; Novel : 3 buku

&#x20; Teknologi : 2 buku



\-- Buku Paling Sering Dipinjam --

&#x20; "Laut Bercerita" oleh Leila S. Chudori (2017) - Kategori: Novel - Status: Tersedia - Dipinjam: 1 kali



\-- Anggota Paling Aktif --

&#x20; ID: 143 | Nama: Nana | Sedang dipinjam: 0 buku | Total riwayat pinjam: 1



\-- Kategori Paling Populer --

&#x20; Novel

===========================================



================ MENU ================

1\. Tambah Buku

2\. Daftar Buku

3\. Cari Buku

4\. Pinjam Buku

5\. Kembalikan Buku

6\. Laporan Perpustakaan

7\. Keluar

Pilih menu (1-7): 7

Terima kasih telah menggunakan sistem perpustakaan. Sampai jumpa!

```

