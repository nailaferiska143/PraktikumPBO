**DESKRIPSI PROGRAM**



Dalam kegiatan belajar mengajar, guru perlu mencatat nilai siswa, menentukan siapa yang lulus, dan membuat rekap untuk satu kelas. Program ini mensimulasikan proses tersebut dalam bentuk aplikasi konsol. Pengguna (misalnya guru) cukup mengetik jumlah siswa, nama, dan nilai. Program lalu menghasilkan laporan nilai berupa file teks yang bisa dibuka dengan aplikasi apa pun (Notepad, VS Code, dan sebagainya), lengkap dengan status kelulusan tiap siswa dan ringkasan kelas. Setelah laporan tersimpan, program tidak langsung berhenti. Program membuka kembali file yang baru dibuat dan menampilkan isinya di layar. Langkah ini menjadi bukti bahwa data benar-benar tersimpan ke file dan dapat dibaca lagi, bukan sekadar dicetak dari variabel di memori. Program ini dibuat untuk mempraktikkan tiga hal dasar pada Java I/O:

1. Input console dengan Scanner dan pengolahan data dalam loop.
2. Penulisan file dengan FileWriter + BufferedWriter, dan pembacaan file dengan FileReader + BufferedReader.
3. Exception handling dan resource management dengan try-catch dan try-with-resources.



**MASALAH YANG DISELESAIKAN**



|**Masalah**|**Cara program menyelesaikannya**|
|-|-|
|Data nilai hilang begitu program ditutup|Data disimpan permanen ke laporan\_nilai.txt|
|Menentukan kelulusan satu per satu secara manual|Status dihitung otomatis dari nilai (batas 70)|
|Menghitung rata-rata dan rekap kelas secara manual|Rata-rata, jumlah lulus, dan jumlah tidak lulus dihitung otomatis|
|Salah ketik saat input membuat program berhenti|Input divalidasi dan diminta ulang bila salah|
|File yang tidak tertutup bisa rusak atau datanya belum tertulis|Resource ditutup otomatis dengan try-with-resources|



**DATA YANG DIPROSES**



Setiap siswa memiliki dua data masukan dan satu data turunan:



|**Data**|**Tipe Java**|**Sumber**|
|-|-|-|
|Nama|String |Diketik pengguna|
|Nilai|double|Diketik pengguna|
|Status kelulusan|String ("Lulus" / "Tidak Lulus")|Dihitung program dari nilai|



Data tingkat kelas yang dihasilkan adalah total nilai (hanya dipakai sementara), rata-rata nilai, jumlah lulus, dan jumlah tidak lulus.



**DESKRIPSI TIAP LANGKAH PENGERJAAN**



**Langkah 1: Input console dan penulisan file**

1. Program meminta jumlah siswa memakai Scanner. Jika input tidak valid, pengguna diminta mengulang.
2. File laporan\_nilai.txt dibuka dengan BufferedWriter yang membungkus FileWriter. Judul laporan dan header tabel ditulis lebih dulu.
3. Sebuah loop for berjalan sebanyak jumlah siswa. Pada tiap putaran program membaca nama dan nilai, menentukan status kelulusan langsung di dalam loop, memperbarui total nilai serta penghitung lulus/tidak lulus, lalu menulis satu baris data ke file.
4. Setelah loop selesai, rata-rata dihitung (totalNilai/jumlahSiswa) dan ringkasan ditulis di akhir file.
5. Writer ditutup otomatis, sehingga seluruh data pasti sudah tersimpan.



**Langkah 2: Pembacaan file**

1. File laporan\_nilai.txt dibuka kembali dengan BufferedReader yang membungkus FileReader.
2. Isi dibaca baris demi baris dengan readLine() di dalam loop while. Loop berhenti ketika readLine() mengembalikan null, yang menandakan akhir file.
3. Setiap baris yang terbaca langsung dicetak ke konsol.



**Langkah 3: Exception handling dan resource management**

1. Proses tulis dan proses baca masing-masing dibungkus try-with-resources, sehingga writer/reader ditutup otomatis walaupun terjadi error.
2. IOException ditangkap dengan catch dan ditampilkan sebagai pesan yang mudah dipahami.
3. Kesalahan input (angka tidak valid) ditangani terpisah dengan NumberFormatException pada method input.



**ASUMSI DAN KEPUTUSAN**



* Batas kelulusan 70 mengikuti ketentuan tugas dan disimpan sebagai konstanta agar mudah diubah.
* Rentang nilai 0–100 dipakai sebagai batas wajar nilai ujian; nilai di luar rentang ditolak.
* File ditimpa setiap kali program dijalankan, sehingga laporan selalu mencerminkan sesi terakhir.
* Satu baris per siswa dengan kolom rata agar laporan mudah dibaca langsung sebagai teks biasa.
* Pembacaan setelah penulisan selesai: file ditutup dulu sebelum dibaca ulang, supaya isinya lengkap dan tidak ada data yang masih tertahan di buffer.



**FITUR**



1. Input jumlah siswa, lalu nama (String) dan nilai (double) tiap siswa.
2. Status kelulusan ditentukan langsung di dalam loop:
a. Nilai ≥ 70 → Lulus
b. Nilai < 70 → Tidak Lulus
3. Data tiap siswa ditulis ke laporan\_nilai.txt, diikuti ringkasan di akhir file:
a. Rata-rata nilai kelas
b. Jumlah siswa lulus
c. Jumlah siswa tidak lulus
4. File dibaca kembali baris demi baris dengan `readLine()` sampai `null`, lalu ditampilkan ke konsol.
5. Validasi input sehingga program tidak crash saat pengguna salah ketik.
6. Seluruh proses file aman dari kebocoran resource (ditutup otomatis).



**ATURAN DAN VALIDASI INPUT**



|**Input**|**Aturan**|**Jika tidak valid**|
|-|-|-|
|Jumlah siswa|Bilangan bulat > 0|Pesan kesalahan, diminta ulang|
|Nama siswa|Tidak boleh kosong (spasi di awal/akhir dibuang)|Pesan kesalahan, diminta ulang|
|Nilai siswa|Angka 0 sampai 100 (boleh desimal)|Pesan kesalahan, diminta ulang|



Catatan:

* Nilai desimal boleh memakai titik atau koma. Contohnya adalah 85.5 dan 85,5 sama-sama diterima).
* Batas kelulusan disimpan pada konstanta NILAI\_MINIMAL\_LULUS = 70.0, jadi mudah diubah.
* Nilai tepat 70 dinyatakan Lulus, sedangkan 69.99 dinyatakan Tidak Lulus.



**FORMAT FILE LAPORAN**



File laporan\_nilai.txt dibuat di direktori tempat program dijalankan. Strukturnya:



```

================ LAPORAN NILAI SISWA ================

No   Nama                      Nilai    Status      

\-----------------------------------------------------

1    Lala                      98,00    Lulus       

2    Bagas                     67,00    Tidak Lulus 

3    Nafa                      88,00    Lulus       

\-----------------------------------------------------

RINGKASAN

Rata-rata nilai kelas : 84,33

Jumlah siswa lulus    : 2

Jumlah siswa tidak lulus : 1

```



**CONTOH PENGGUNAAN**



Input di konsol:



```

Masukkan jumlah siswa: 3



Data siswa ke-1

&#x20; Nama siswa ke-1  : Lala

&#x20; Nilai siswa ke-1 (0-100): 98



Data siswa ke-2

&#x20; Nama siswa ke-2  : Bagas

&#x20; Nilai siswa ke-2 (0-100): 67



Data siswa ke-3

&#x20; Nama siswa ke-3  : Nafa

&#x20; Nilai siswa ke-3 (0-100): 88



Data berhasil ditulis ke file laporan\_nilai.txt

```



Output di konsol (isi file hasil pembacaan ulang):



```

============ ISI FILE laporan\_nilai.txt ============

================ LAPORAN NILAI SISWA ================

No   Nama                      Nilai    Status      

\-----------------------------------------------------

1    Lala                      98,00    Lulus       

2    Bagas                     67,00    Tidak Lulus 

3    Nafa                      88,00    Lulus       

\-----------------------------------------------------

RINGKASAN

Rata-rata nilai kelas : 84,33

Jumlah siswa lulus    : 2

Jumlah siswa tidak lulus : 1

```

Perhitungan rata-rata: (98 + 67 + 88) / 3 = 84,33.



**KONSEP JAVA YANG DIGUNAKAN**



|**Konsep**|**Kegunaan**|
|-|-|
|Scanner|Membaca input keyboard|
|FileWriter|Menulis karakter ke file|
|BufferedWriter|Membungkus FileWriter agar penulisan lebih efisien (buffering), dan menyediakan newLine()|
|FileReader|Membaca karakter dari file|
|BufferedReader|Membungkus FileReader, menyediakan readLine()|
|String.format|Merapikan kolom laporan dan membatasi desimal|
|try-with-resources|Menutup writer/reader otomatis|
|IOException|Menangani kegagalan baca/tulis file|
|NumberFormatException|Menangani input angka yang tidak valid|
|static final|Konstanta nama file dan batas kelulusan|





