import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Logic implements ActionListener, KeyListener {

    //ukuran frame utama game
    int frameWidth = 360;
    int frameHeight = 640;

    //posisi dan ukuran awal burung
    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    //tambahkan atribut posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;


    View view;              // panel tampilan game
    Image birdImage;        // gambar burung
    Player player;          // objek burung pemain
    Timer gameLoop;         // timer untuk menggerakan burung game
    Timer pipesCooldown;    // timer untuk membuat pipa secara berkala


    double gravity = 0.5;          //gaya gravitasi
    int pipeVelocityX = -2;        //kecepatan gerak pipa ke kiri
    boolean gameStarted = false;    // tanda game dimulai
    boolean gameOver = false;       // tanda game berakhir

    // tambahkan list pipa dan gambarnya
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    //variabel untuk skor dan label yg ditampilkan dilayar
    int score = 0;
    JLabel scoreLabel;

    public Logic() {

        // menampilkan gambar burung
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        // membuat objek burung dengan posisi awal
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        //untuk menampung semua pipa yg muncul
        pipes = new ArrayList<>();

        //timer untuk menambahkan pipa setiap 1,5 detik
        pipesCooldown = new Timer(1500, e -> {
            if (gameStarted && !gameOver) placePipes();
        });
        pipesCooldown.start();

        // timer utama game
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    //getter setter
    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }


    // bikin pipa baru
    public void placePipes() {
        int openingSpace = 300; // jarak antar pipa
        int minY = 50;           // jarak minimal pipa atas dari atas layar
        int maxY = frameHeight - openingSpace - 50; // jarak maksimal untuk pipa bawah

        //tentukan posisi celah acak di antara pipa atas dan bawah
        int gapY = minY + (int) (Math.random() * (maxY - minY)); // posisi celah acak tapi masih wajar

        //buat pipa atas dan bawah, lalu tambahkan ke array list
        Pipe upperPipe = new Pipe(pipeStartPosX, gapY - pipeHeight, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, gapY + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    //cek tabrakan
    private boolean checkCollision(Player p, Pipe pipe) {
        // buat objek persegi panjang untuk cek tabrakan burung dan viva
        Rectangle playerRect = new Rectangle(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

        // kalo bersinggungan berarti nabrak
        return playerRect.intersects(pipeRect);
    }

    // gerak utama game
    public void move() {
        // jika game belum dimulai atau sudah game over, hentikan smua pergerakan
        if (!gameStarted || gameOver) return;  //burung diam dulu sebelum spasi ditekan

        // gerak burung
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + (int) player.getVelocityY());

        // jika burung jatuh sampai bawah layar, game over
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            player.setPosY(frameHeight - player.getHeight());
            gameOver = true;
        }

        // cek tabrakan burung dan smua pipa
        for (Pipe pipe : pipes) {
            if (checkCollision(player, pipe)) {
                gameOver = true;
            }
        }

        //gerakan semua pipa ke kiri
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        //hapus pipa yg keluar dari layar dan tambah skornya jika burung berhasil melewati pipa
        ArrayList<Pipe> removePipes = new ArrayList<>();
        for (Pipe pipe : pipes) {
            // hapus pipa yg sudah keluar dari layar,
            if (pipe.getPosX() + pipe.getWidth() < 0) {
                removePipes.add(pipe);

                //jika pipa sudah dilewati burung dan belum dihitung skor
            } else if (!pipe.passed && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                if (pipe.isLower()) { // hitung skor sekali per pasangan pipa
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                pipe.passed = true;
            }
        }
        // hapus pipa yg sudah tidak terlihat dilayar
        pipes.removeAll(removePipes);
    }

    //loop utama
    @Override
    public void actionPerformed(ActionEvent e) {
        move();     // jalankan logika setiap frame
        if (view != null) {     // jika sudah ada
            view.repaint();     // gambar ulang tampilan setiap frame
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // jika tombol spasi di tekan dan belum game over
        if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameStarted = true;     // mulai game
            player.setVelocityY(-8);    // burung melompat keatas
        }

        // restart game jika game over dan tekan 'R'
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    private void restartGame() {
        // Reset posisi dan kecepatan player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        // Reset pipa
        pipes.clear();

        // reset skor dan perbarui label nya
        score = 0;
        if (scoreLabel != null) scoreLabel.setText("Score : 0");
        // Reset status game
        gameOver = false;
        gameStarted = false;
    }


}
