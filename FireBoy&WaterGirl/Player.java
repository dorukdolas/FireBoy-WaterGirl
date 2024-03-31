

import java.awt.*;

public class Player {
    private Color color;
    private int playerX, playerY;
    private int playerSpeed;

    public Player(int x, int y, int speed) {
        playerX = x;
        playerY = y;
        playerSpeed = speed;
        color = Color.red; //default renk
    }

    private boolean canShoot = true; // default mavi olduğu için default olarak ateş edebilir

    public boolean CanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}

