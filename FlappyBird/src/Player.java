import java.awt.*;

public class Player {
    // posisi pemain (sumbu X)
    private int posX;
    // posisi  pemain (sumbu Y)
    private int posY;
    // lebar gambar pemain
    private int width;
    // tnggi gambar pemain
    private int height;
    // kecepatan vertikal pemain
    private double velocityY;
    // gambar yang digunakan untuk menampilkan pemain
    private Image image;

    // konstruktor untuk menginisialisasi posisi, ukuran, dan gambar pemain
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;       // menetapkan posisi awal X pemain
        this.posY = posY;       // menetapkan posisi awal Y pemain
        this.width = width;     // menetapkan lebar gambar pemain
        this.height = height;   // menetapkan tinggi gambar pemain
        this.image = image;     // menetapkan gambar pemain
    }

    // getter setter
    public int getPosX() {
        return posX;
    }
    public void setPosX(int x) {
        posX = x;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int y) {
        posY = y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public double getVelocityY() {
        return velocityY;
    }
    public void setVelocityY(double v) {
        velocityY = v;
    }

}
