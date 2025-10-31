import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {

    int width = 360;  // lebar layar game
    int height = 640; // tinggi layar game

    //logic untuk menghubungkan tampilan dengan logika permainan
    private Logic logic;

    //constuctor
    public View(Logic logic) {
        this.logic = logic;
        // simpan objek logic supaya bisa dipakai di sini

        // atur ukuran panel sesuai ukuran game
        setPreferredSize(new Dimension(width, height));
        // atur warna background
        setBackground(Color.cyan);

        // untuk panel ini bisa menerima input dari keyboard
        setFocusable(true);
        addKeyListener(logic);
        requestFocusInWindow();     //biar bisa langsung deteksi tombol spasi

        // bagian menampilkan skor
        logic.scoreLabel = new JLabel("Score: 0"); // teks awal skor
        logic.scoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // font besar dan tebal
        logic.scoreLabel.setForeground(Color.white); // warna tulisan putih
        logic.scoreLabel.setBounds(10, 10, 150, 30); // posisi label di kiri atas
        add(logic.scoreLabel); // tambahkan label ke panel (View)
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // bersihkan layar sebelum menggambar ulang
        draw(g); // panggil fungsi menggambar semua objek di game
    }

    public void draw(Graphics g) {
        // gambar burung
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        //gambar pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // tampilkan tulisan game over
        if (logic.gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 27));
            g.drawString("GAME OVER! ", 90, 300);
            g.drawString("Press R to Restart", 70, 345);
        }

    }
}
