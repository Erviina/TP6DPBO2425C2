Saya Ervina Kusnanda dengan NIM 2409082 mengerjakan TP 6 dalam mata kuliah Desain Pemogramana Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

**DESAIN PROGRAM**

1. Kelas App
   - sebagai kelas utama yg mengeksekusi game
   - kode di dalamnya hanya memanggil FlappyBirdGame.main(args) agar program langsung menjalankan menu game.

2. Kelas FlappyBirdGame
   - bertanggung jawab untuk membuat tampilan menu utama yang berisi tombol play dan exit
   - Ketika tombol Play ditekan, program menutup menu (menuFrame.dispose()) dan menjalankan metode startGame()
   - startGame() membuat objek Logic (logika permainan) dan View (tampilan game), lalu menampilkannya dalam jendela baru (JFrame)

3. Kelas Logic
   Mengatur seluruh logika permainan, seperti:
   - menggerakkan pemain (burung)
   - menjalankan gravitasi dan melompat ketika tombol spasi ditekan
   - menampilkan dan menggerakkan pipa secara otomatis
   - mengecek tabrakan antara burung dan pipa
   - mengatur skor dan kondisi game over.
   - logic juga menggunakan Timer untuk menjalankan loop game dengan kecepatan 60 FPS dan menambahkan pipa baru setiap 1,5 detik.

4. Kelas View
   - mengatur tampilan grafis (visual) game.
   - menggambar burung, pipa, skor, dan pesan “Game Over”
   - terhubung langsung ke kelas Logic agar dapat menampilkan data terbaru (posisi burung, pipa, skor, dsb)
   - menggunakan paintComponent(Graphics g) untuk menggambar ulang setiap frame.

5. Kelas Player
   - mewakili objek burung yang dikendalikan pemain
   - menyimpan atribut posisi (posX, posY), ukuran (width, height), kecepatan jatuh velocityY, serta gambar burung
   - digunakan oleh Logic dan View untuk memproses gerakan dan menampilkannya di layar.

6. Kelas Pipe
   - mewakili pipa penghalang dalam game
   - menyimpan atribut posisi, ukuran, gambar, dan status apakah pipa sudah dilewati atau belum (untuk perhitungan skor)
   - objek-objek Pipe disimpan dalam ArrayList<Pipe> di dalam kelas Logic.


**ALUR PROGRAM**

Program Flappy Bird ini dimulai dari kelas App yang bertugas memanggil FlappyBirdGame.main(). Saat dijalankan, pemain akan disuguhi tampilan menu utama yang terdiri dari dua tombol, yaitu Play untuk memulai permainan dan Exit untuk keluar dari program. Ketika tombol Play ditekan, menu akan tertutup dan permainan dimulai. Pada saat permainan berjalan, objek Logic mengatur seluruh logika game termasuk gerakan burung pemain, gravitasi, penambahan pipa secara berkala, serta pengecekan tabrakan. Sementara itu, kelas View bertugas menggambar burung, pipa, skor, dan pesan “Game Over” pada layar sesuai data yang diberikan oleh Logic. Burung akan terus jatuh ke bawah akibat gravitasi, dan pemain dapat menekankan tombol spasi untuk membuat burung melompat ke atas. Pipa bergerak dari kanan ke kiri, dan setiap kali burung berhasil melewati pipa bawah, skor akan bertambah. Jika burung menabrak pipa atau jatuh ke tanah, permainan akan berakhir dan layar menampilkan pesan “Game Over” beserta instruksi untuk menekan tombol R untuk memulai ulang permainan. Saat tombol R ditekan, semua elemen permainan termasuk posisi burung, pipa, dan skor akan di-reset sehingga pemain dapat bermain kembali dari awal. Proses ini terus berlangsung hingga pemain menutup jendela game atau menekan tombol Exit di menu utama.

**DOKUMENTASI**
![Gambar](Dokumentasi/dokumentasi-TP6.mp4)
