import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBirdGame {

    public static void main(String[] args) {

        //membuat tampilan untuk menu
        JFrame menuFrame = new JFrame("Flappy Bird - Menu");
        menuFrame.setSize(400, 300);        // ukuran bagian menu
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(null); // pakai absolute positioning
        menuFrame.setLocationRelativeTo(null); // biar muncul di tengah layar

        // Tombol Play
        JButton playButton = new JButton("Play");               // buat tombol play
        playButton.setBounds(150, 80, 100, 40);   // atur posisi dan ukuran tombol
        menuFrame.add(playButton);      // tambahkan tombol ke bagian menu

        // Tombol Exit
        JButton exitButton = new JButton("Exit");       // buat tombol exit
        exitButton.setBounds(150, 140, 100, 40);        // atur posisi dan ukuran tombol
        menuFrame.add(exitButton);        // // tambahkan tombol ke bagian menu

        // tampilkan bagian menu di layar
        menuFrame.setVisible(true);


        // Aksi tombol Play
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose(); // tutup menu
                startGame();        // jalankan game FlappyBird
            }
        });

        // Aksi tombol Exit
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // keluar program
            }
        });
    }

    private static void startGame() {
        // bikin objek logic buat mengatur jalannya game
        Logic logic = new Logic();

        // buat view dan bubungkan dengan logic
        View view = new View(logic);
        logic.setView(view);        // agar logic bisa memanggil view.repaint() untuk memperbarui tampilan

        JFrame gameFrame = new JFrame("Flappy Bird");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(view);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null); // tengah layar
        gameFrame.setVisible(true);
    }
}
