**Deskripsi Program**



Program ini adalah aplikasi console berbasis Java yang dibuat dengan pendekatan pemrograman berorientasi objek untuk mengelola data nilai mahasiswa. Pengguna memasukkan jumlah mahasiswa, kemudian mengisi nama, NIM, dan nilai setiap mahasiswa melalui console menggunakan Scanner. Setiap data yang masuk dirapikan terlebih dahulu dengan metode String seperti trim() dan toUpperCase(), lalu divalidasi menggunakan Assertion untuk memastikan NIM tidak kosong, kelas Character untuk memastikan NIM hanya berisi angka, serta Custom Exception NilaiInvalidException untuk menolak nilai di luar rentang 0 sampai 100. Data yang lolos validasi disimpan sebagai objek Mahasiswa di dalam ArrayList, sehingga jumlah data bisa bertambah secara dinamis.



Setelah seluruh data terkumpul, program menentukan grade huruf (A, B, C, D) dan status kelulusan setiap mahasiswa berdasarkan standar kelulusan 70, kemudian menghitung statistik kelas berupa rata-rata nilai, jumlah mahasiswa yang lulus, dan jumlah yang tidak lulus. Hasil rekapitulasi ditampilkan di layar dan disimpan secara permanen ke file teks laporan\_nilai.txt menggunakan BufferedWriter dan FileWriter dengan mekanisme try-with-resources. Program juga dirancang tahan terhadap input yang salah: kesalahan input tidak menghentikan program, tetapi menampilkan pesan error dan meminta pengguna mengulang input data yang sama.



**Fitur** 

1. Input dinamis jumlah mahasiswa berapa pun (bilangan bulat > 0).
2. Perapihan nama (trim() + toUpperCase()) dan NIM (trim()).
3. Validasi NIM: tidak boleh kosong dan hanya boleh berisi angka.
4. Validasi nilai: harus angka dan berada pada rentang 0 sampai 100 (termasuk menolak NaN).
5. Pemetaan nilai ke grade A, B, C, D dan status LULUS / TIDAK LULUS.
6. Statistik kelas: jumlah mahasiswa, rata-rata, jumlah lulus, jumlah tidak lulus.
7. Penyimpanan laporan ke file teks dengan penanganan IOException.
8. Pemisahan tanggung jawab lewat package model dan app.



**Struktur Class**

|**Class**|**Kegunaan**|
|-|-|
|Mahasiswa|Kelas data yang menyimpan nama, nim, dan nilai. Merapikan dan memvalidasi data di constructor, serta menentukan grade huruf dan status lulus.|
|NilaiInvalidException	|Custom exception yang dilempar saat nilai kurang dari 0 atau lebih dari 100.|
|Main|Kelas utama: menerima input lewat Scanner, menyimpan data ke ArrayList, menghitung rata-rata dan jumlah lulus/tidak lulus, lalu menulis laporan\_nilai.txt.|



**Penjelasan Tiap Class**

1. **Class Mahasiswa**
Class Mahasiswa merepresentasikan seorang mahasiswa. Kelas ini memiliki tiga atribut, yaitu nama dan nim bertipe String (reference type) serta nilai bertipe double (primitive type), ditambah konstanta FINAL\_PASSING\_GRADE bernilai 70.0 yang dideklarasikan public static final sebagai batas kelulusan. Ketiga atribut dibuat private final sehingga setelah objek terbentuk datanya tidak dapat diubah dan selalu berada dalam kondisi valid.
Seluruh proses validasi dilakukan di dalam constructor Mahasiswa(String nama, String nim, double nilai). Pertama, nama dirapikan dengan trim() dan toUpperCase(), sedangkan NIM dirapikan dengan trim(), dan keduanya aman terhadap nilai null. Kedua, constructor menjalankan assert !nimRapi.isEmpty() : "NIM tidak boleh kosong!"; untuk memastikan NIM tidak kosong. Ketiga, karena assertion hanya aktif bila program dijalankan dengan flag -ea, nama dan NIM yang kosong juga dicek secara eksplisit dan akan melempar IllegalArgumentException. Keempat, setiap karakter NIM diperiksa dengan Character.isDigit() dan program melempar IllegalArgumentException bila ditemukan karakter selain angka. Kelima, nilai diperiksa dan program melempar NilaiInvalidException bila nilainya NaN, kurang dari 0, atau lebih dari 100. Atribut baru diisi hanya jika semua pemeriksaan tersebut lolos, sehingga objek Mahasiswa yang tidak valid tidak pernah terbentuk.
Selain constructor, kelas ini menyediakan getter getNama(), getNim(), dan getNilai() untuk membaca data. Method getGrade() memetakan nilai ke grade huruf dengan struktur if-else: A untuk nilai 85 ke atas, B untuk 75 ke atas, C untuk nilai yang mencapai batas kelulusan 70, dan D untuk nilai di bawahnya. Method isLulus() mengembalikan true bila nilai lebih besar atau sama dengan FINAL\_PASSING\_GRADE, dan getStatus() mengubah hasil tersebut menjadi teks "LULUS" atau "TIDAK LULUS". Terakhir, toString() menghasilkan satu baris data yang sudah diformat dengan String.format() agar dapat langsung dicetak sebagai baris tabel.
2. **Class NilaiInvalidException**
NilaiInvalidException adalah custom exception yang dibuat dengan mewarisi kelas Exception, sehingga termasuk checked exception. Artinya, setiap kode yang membuat objek Mahasiswa wajib menangani atau mendeklarasikan exception ini, jadi pelanggaran aturan nilai tidak mungkin terlewat. Kelas ini menyimpan nilai yang ditolak dalam atribut nilai dan menyusun pesan error otomatis lewat super(...) dengan format "Nilai tidak valid: (nilai) (harus antara 0 sampai 100)". Nilai yang menyebabkan error tersebut dapat diambil kembali melalui method getNilai().
3. **Class Main**
Class main digunakan untuk mengatur jalannya program. Di method main, program membuat ArrayList<Mahasiswa>, membaca jumlah mahasiswa lewat bacaJumlah(), lalu menjalankan perulangan while untuk menerima nama, NIM, dan nilai setiap mahasiswa dengan Scanner. Input dibaca dengan nextLine() lalu diubah ke angka dengan Double.parseDouble(), agar terhindar dari masalah sisa baris baru di buffer. Setiap pembuatan objek Mahasiswa dibungkus try-catch yang menangkap NumberFormatException, NilaiInvalidException, IllegalArgumentException, dan AssertionError. Bila terjadi kesalahan, program menampilkan pesan error dan meminta pengguna mengulang data yang sama, karena penghitung ke baru bertambah setelah data berhasil ditambahkan ke daftar.
Method bacaJumlah() terus meminta jumlah mahasiswa sampai pengguna memasukkan bilangan bulat lebih dari 0. Method hitungRataRata() menjumlahkan seluruh nilai dengan for-each lalu membaginya dengan size(), sedangkan hitungLulus() menelusuri daftar dan menghitung mahasiswa yang isLulus()-nya bernilai true. Method header() menyediakan baris judul kolom yang dipakai bersama oleh tampilan layar dan file laporan, dan tampilkanRekap() mencetak tabel data beserta statistik kelas ke layar.
Method simpanKeFile() menulis laporan ke laporan\_nilai.txt menggunakan BufferedWriter yang membungkus FileWriter dalam try-with-resources, sehingga file otomatis ditutup walaupun terjadi error. Jika penulisan gagal, blok catch (IOException) menampilkan pesan kesalahan, dan blok finally selalu mencetak pemberitahuan bahwa proses penyimpanan selesai.



**Materi yang Digunakan**

1. **Package, Class, Constructor, dan Variabel**
Program ini memisahkan kode ke dalam dua package, yaitu model yang menampung kelas data (Mahasiswa dan NilaiInvalidException) dan app yang menampung kelas utama Main. Kelas Mahasiswa memiliki atribut nama (String), nim (String), dan nilai (double) yang diinisialisasi melalui constructor Mahasiswa(String, String, double). Kata kunci final dipakai pada konstanta public static final double FINAL\_PASSING\_GRADE = 70.0 sebagai standar kelulusan, dan juga pada ketiga atribut yang dibuat private final agar datanya tidak dapat diubah setelah objek terbentuk.
2. **Kondisional, Looping, serta Primitive dan Reference Types**
Pemetaan nilai ke grade huruf dilakukan oleh method getGrade() dengan struktur if-else, sedangkan status kelulusan ditentukan oleh isLulus() dan getStatus(). Perulangan digunakan di dua tempat: while pada Main untuk menerima data mahasiswa satu per satu, dan for-each pada hitungRataRata() dan hitungLulus() untuk menelusuri daftar mahasiswa. Perbedaan tipe data terlihat jelas pada kode, karena double (nilai), boolean (hasil isLulus()), dan int (jumlah mahasiswa) adalah tipe primitif, sedangkan objek Mahasiswa, String, dan ArrayList adalah reference type.
3. **Exception Handling, Assertion, Character, dan String**
Custom exception diwujudkan dalam kelas NilaiInvalidException yang mewarisi Exception dan dilempar dengan throw oleh constructor Mahasiswa ketika nilai kurang dari 0 atau lebih dari 100. Assertion digunakan pada constructor yang sama melalui assert !nimRapi.isEmpty() : "NIM tidak boleh kosong!"; untuk memastikan NIM tidak berupa string kosong. Manipulasi String dilakukan dengan trim() dan toUpperCase() untuk merapikan nama dan NIM, serta isEmpty() dan String.format() untuk pengecekan dan pemformatan. Kelas Character dipakai melalui Character.isDigit() di dalam perulangan atas toCharArray() untuk memastikan NIM hanya berisi karakter angka.
4. **Array dan Collection Framework**
Kumpulan objek Mahasiswa disimpan dalam struktur data dinamis ArrayList<Mahasiswa>, bukan array statis, sehingga jumlah data dapat bertambah sesuai input pengguna. Method ArrayList yang digunakan adalah add() untuk menambah data, size() untuk mengetahui jumlah data dan menghitung rata-rata, serta isEmpty() untuk memeriksa apakah daftar kosong. Penelusuran elemen dilakukan dengan for-each, misalnya for (Mahasiswa m : daftar).
5. **Input/Output dan File Handling**
Untuk Console I/O, program memakai Scanner dengan System.in dan method nextLine() untuk menerima input dinamis dari pengguna, dan objek Scanner tersebut dibungkus try-with-resources sehingga otomatis ditutup. Untuk File I/O, rekapitulasi daftar mahasiswa beserta statistik kelas (rata-rata, jumlah lulus, dan jumlah tidak lulus) ditulis ke file laporan\_nilai.txt memakai BufferedWriter yang membungkus FileWriter. Kemungkinan kegagalan penulisan file ditangani dengan try-with-resources, catch (IOException) untuk menampilkan pesan kesalahan, dan blok finally yang selalu mencetak pemberitahuan bahwa proses penyimpanan selesai.



**Aturan Penilaian**

|Rentang Nilai|Grade|Status|
|-|-|-|
|85 ≤ nilai ≤ 100|A|LULUS|
|75 ≤ nilai < 85|B|LULUS|
|70 ≤ nilai < 75|C|LULUS|
|0 ≤ nilai < 70|D|TIDAK LULUS|

Batas lulus mengacu ke konstanta Mahasiswa.FINAL\_PASSING\_GRADE = 70.0. Nilai tepat 70 dinyatakan lulus (operator >=). Untuk mengubah standar, cukup ubah satu konstanta tersebut.



**Output**



```

Masukkan jumlah mahasiswa: 3



--- Data Mahasiswa ke-1 ---

Nama  : Mario

NIM   : L007

Nilai : 88

NIM hanya boleh berisi angka, ditemukan karakter 'L' Ulangi input.



--- Data Mahasiswa ke-1 ---

Nama  : Mario

NIM   : 251202

Nilai : 88

Data berhasil ditambahkan.



--- Data Mahasiswa ke-2 ---

Nama  : Nana

NIM   : 252401

Nilai : 69

Data berhasil ditambahkan.



--- Data Mahasiswa ke-3 ---

Nama  : Lala

NIM   : 250309

Nilai : 98

Data berhasil ditambahkan.



================= REKAPITULASI NILAI MAHASISWA ================

NIM          NAMA                       NILAI   GRADE STATUS

251202       MARIO                      88,00   A     LULUS

252401       NANA                       69,00   D     TIDAK LULUS

250309       LALA                       98,00   A     LULUS



Jumlah mahasiswa : 3

Rata-rata kelas  : 85,00

Lulus            : 2

Tidak lulus      : 1



Laporan berhasil disimpan ke file: laporan\_nilai.txt

Proses penyimpanan file selesai.

```

**Format File Laporan**

```

LAPORAN NILAI MAHASISWA

Standar kelulusan: 70.0

==============================================================

NIM          NAMA                       NILAI   GRADE STATUS

--------------------------------------------------------------

251202       MARIO                      88,00   A     LULUS

252401       NANA                       69,00   D     TIDAK LULUS

250309       LALA                       98,00   A     LULUS

--------------------------------------------------------------

Jumlah mahasiswa : 3

Rata-rata kelas  : 85,00

Lulus            : 2

Tidak lulus      : 1

```

