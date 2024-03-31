import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{
    final static int originalTileSize = 16;
    final static int scale = 1;
    final static int tileSize = originalTileSize * scale;
    final static int playerSize = tileSize/2;
    final static int maxScreenCol = 36;
    final static int maxScreenRow = 24;
    final static int screenWidth = tileSize * maxScreenCol;
    final static int screenHeight = tileSize * maxScreenRow;

    static ArrayList<Integer> bullets = new ArrayList<>();

    static int bulletSize = 5;

    static int FPS = 90;

    private boolean godMode = false ;

    Monster monster = new Monster();

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    int playerX = 0, playerY = screenHeight - playerSize * 3 ;

    int playerSpeed = 1;

    int rightBound = screenWidth - playerSize, underBound = screenHeight - playerY, leftBound = playerX, upperBound = 0;

    Player player = new Player(playerX, playerY, playerSpeed);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            if (!keyH.pause) {
                update();

                repaint();
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep( (long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isIncreased = false;
    Map map = new Map();

    int fireWaitCount = FPS / 3;
    int tmpFire = fireWaitCount;
    boolean fired = false;

    public boolean isFalling() {
        ArrayList<Integer> tmp;
        for (int i = 0; i < map.map.size(); i++) {
            if (i != 3 && i != 4) {
                for (int j = 0; j < map.map.get(i).size(); j++) {
                    tmp = (ArrayList<Integer>) map.map.get(i).get(j);
                    if (player.getPlayerX() < tmp.get(0) + tmp.get(2) && player.getPlayerX() + playerSize > tmp.get(0) && player.getPlayerY() + playerSize == tmp.get(1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isLeftClear() {
        ArrayList<Integer> tmp;
        for (int i = 0; i < map.map.size(); i++) {
            for (int j = 0; j < map.map.get(i).size(); j++) {
                tmp = (ArrayList<Integer>) map.map.get(i).get(j);
                if (i != 3 && i != 4) {
                    if (player.getPlayerX() < tmp.get(0) + tmp.get(2) + playerSpeed && player.getPlayerX() + playerSpeed > tmp.get(0)) {
                        if (player.getPlayerY() < tmp.get(1) + tmp.get(3) && player.getPlayerY() + playerSize > tmp.get(1)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isRightClear() {
        ArrayList<Integer> tmp;
        for (int i = 0; i < map.map.size(); i++) {
            for (int j = 0; j < map.map.get(i).size(); j++) {
                tmp = (ArrayList<Integer>) map.map.get(i).get(j);
                if (i != 3 && i != 4) {
                    if (player.getPlayerX() + playerSize + playerSpeed > tmp.get(0) && player.getPlayerX() < tmp.get(0) + tmp.get(2)) {
                        if (player.getPlayerY() < tmp.get(1) + tmp.get(3) && player.getPlayerY() + playerSize > tmp.get(1)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isUpClear() {
        ArrayList<Integer> tmp;
        for (int i = 0; i < map.map.size(); i++) {
            for (int j = 0; j < map.map.get(i).size(); j++) {
                tmp = (ArrayList<Integer>) map.map.get(i).get(j);
                if (player.getPlayerX() + playerSize > tmp.get(0) && player.getPlayerX() < tmp.get(0) + tmp.get(2)) {
                    if (player.getPlayerY() > tmp.get(1) && player.getPlayerY() - playerSpeed < tmp.get(1) + tmp.get(3)) {
                        jumpCount = 0;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void unHide() {
        for (int i = 0; i < map.hiddenBlocks.size(); i++) {
            ArrayList<Integer> tmp = map.hiddenBlocks.get(i);
            if (i == 0 || (i > 0 && (int) map.hiddenBlocks.get(i - 1).get(8) == 1)) {
                if (tmp.get(8) == 0) {
                    if (tmp.get(4) < player.getPlayerX() + playerSize && tmp.get(4) + tmp.get(6) > player.getPlayerX()) {
                        if (tmp.get(5) < player.getPlayerY() + playerSize && tmp.get(5) + tmp.get(7) == player.getPlayerY() + playerSize) {
                            tmp.set(8, 1);
                            Map.blocks.add(tmp);
                        }
                    }
                }
            }
        }
    }

    public void timedBlocksPlayerDetector() {
        for (int i = 0; i < Map.timedBlocks.size(); i++) {
            if (player.getPlayerX() <= (int) Map.timedBlocks.get(i).get(0) +(int) Map.timedBlocks.get(i).get(2)
            && player.getPlayerX() + playerSize >= (int) Map.timedBlocks.get(i).get(0)
            && player.getPlayerY() + playerSize == (int) Map.timedBlocks.get(i).get(1)) {
                Map.timedBlocks.get(i).set(5,1);
            }

        }
    }
    public void timedBlocksDestroyer() {
        for (int i = 0; i < Map.timedBlocks.size(); i++) {
            if ((int) Map.timedBlocks.get(i).get(5) == 1) {
                Map.timedBlocks.get(i).set(4, (int) Map.timedBlocks.get(i).get(4)-1);
            }
            if ((int) Map.timedBlocks.get(i).get(4) == 0) {
                Map.timedBlocks.remove(i);
            }
        }
    }

    public boolean restartCheck () {
        if (player.getColor() != Color.red) {
            for (int i = 0; i < Map.redBlocks.size(); i++) {
                for (int j = 0; j < Map.redBlocks.get(i).size(); j += 4) {
                    int blockLeft = (int) Map.redBlocks.get(i).get(j), blockRight = (int) Map.redBlocks.get(i).get(j + 2) + blockLeft;
                    if (player.getPlayerX() < blockRight && player.getPlayerX() + playerSize > blockLeft
                            && (player.getPlayerY() + playerSize == (int) Map.redBlocks.get(i).get(j + 1)
                    || player.getPlayerY() == (int) Map.redBlocks.get(i).get(j+1) + (int) Map.redBlocks.get(i).get(j+3))) {
                        return true;
                    }
                }
            }
        }
        if (player.getColor() != Color.blue) {
            for (int i = 0; i < Map.blueBlocks.size(); i++) {
                for (int j = 0; j < Map.blueBlocks.get(i).size(); j += 4) {
                    int blockLeft = (int) Map.blueBlocks.get(i).get(j), blockRight = (int) Map.blueBlocks.get(i).get(j + 2) + blockLeft;
                    if (player.getPlayerX() < blockRight && player.getPlayerX() + playerSize > blockLeft
                    && (player.getPlayerY() + playerSize == (int) Map.blueBlocks.get(i).get(j + 1)
                    || player.getPlayerY() == (int) Map.blueBlocks.get(i).get(j+1) + (int) Map.blueBlocks.get(i).get(j+3))) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < monster.monsters.size();i++) {
            for (int j = 0; j < monster.monsters.get(i).size(); j+=6) {
                if (player.getPlayerX() < (int) monster.monsters.get(i).get(j) + (int) monster.monsters.get(i).get(j+2)
                && player.getPlayerX() + playerSize > (int) monster.monsters.get(i).get(j)
                && player.getPlayerY() < (int) monster.monsters.get(i).get(j+1) + (int) monster.monsters.get(i).get(j+3)
                && player.getPlayerY() + playerSize > (int) monster.monsters.get(i).get(j+1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void restart() {
        bonusPoint = 0;
        isPlayerOn = false;
        keyH.right = true;
        keyH.left = false;
        player.setPlayerX(0);
        player.setPlayerY(screenHeight - playerSize*3);
        keyH.color = Color.red;
        keyH.blue = false; keyH.red = true; keyH.magenta = false;
        player.setCanShoot(true);
        keyH.upPressed = false;

        jumpCount = tileSize * 4 / 2;
        tmpJump = jumpCount;


        for (int i = 0; i < monster.monsters.size(); i++) {
            for (int j = 0; j < monster.monsters.get(i).size();) {
                monster.monsters.get(i).remove(j);
            }
        }
        for (int i = 0; i < Map.hiddenBlocks.size();i++) {
            Map.hiddenBlocks.get(i).set(8,0);
        }
        for (int i = 0; i < Map.bonusBlocks.size();){
            Map.bonusBlocks.remove(i);
        }
        for (int i = 0;i < Map.timedBlocks.size();) {
            Map.timedBlocks.remove(0);
        }


        Map.timedBlocks.add(map.timedBlock1);
        Map.timedBlocks.add(map.timedBlock2);
        Map.timedBlocks.add(map.timedBlock3);
        Map.timedBlocks.add(map.timedBlock4);
        Map.timedBlocks.add(map.timedBlock5);
        Map.timedBlocks.add(map.timedBlock6);
        Map.timedBlocks.add(map.timedBlock7);
        Map.timedBlocks.add(map.timedBlock8);

        for (int i = 0; i < Map.timedBlocks.size(); i++) {
            Map.timedBlocks.get(i).set(5,0);
            Map.timedBlocks.get(i).set(4,FPS*3);
        }


        map.movingBlock.set(0, map.movingBlock.get(8));
        map.movingBlock.set(1, map.movingBlock.get(9));
        map.movingBlock.set(2, map.movingBlock.get(10));
        map.movingBlock.set(3, map.movingBlock.get(11));

        monster.spawnerWait = monster.spawnerTmp - 1;



        for (int i = 0; i<Map.blocks.size(); i++) {

            for (int k = (int) Map.blocks.get(i).get(0) + GamePanel.playerSize * 2;k < (int) Map.blocks.get(i).get(0) + (int) Map.blocks.get(i).get(2); k += GamePanel.playerSize * 2) {
                // i blocks arrayinde dönerken k blockların üstüne bonus block ekleyecek
                Map.bonusBlocks.add(k); // x koordinatı
                Map.bonusBlocks.add((int) Map.blocks.get(i).get(1) - GamePanel.tileSize/4 * 2); // y koordinatı
                Map.bonusBlocks.add(GamePanel.tileSize/4); // genişlik
                Map.bonusBlocks.add(GamePanel.tileSize/4); // kalınlık
            }
        }
        monster.blueMonsters.add(GamePanel.tileSize * 7); // x koordinatı
        monster.blueMonsters.add(GamePanel.tileSize * 22); // y koordinatı
        monster.blueMonsters.add(GamePanel.tileSize); // size
        monster.blueMonsters.add(GamePanel.tileSize); // size
        monster.blueMonsters.add(0); // 0 = sola hareket, 1 = sağa hareket
        monster.blueMonsters.add(3);

        monster.blueMonsters.add(GamePanel.tileSize * 15);
        monster.blueMonsters.add(GamePanel.tileSize * 9);
        monster.blueMonsters.add(GamePanel.tileSize);
        monster.blueMonsters.add(GamePanel.tileSize);
        monster.blueMonsters.add(0);
        monster.blueMonsters.add(3);

        monster.blueMonsters.add(GamePanel.tileSize * 7);
        monster.blueMonsters.add(GamePanel.tileSize * 16);
        monster.blueMonsters.add(GamePanel.tileSize);
        monster.blueMonsters.add(GamePanel.tileSize);
        monster.blueMonsters.add(0);
        monster.blueMonsters.add(3);

        monster.redMonsters.add(GamePanel.tileSize * 15);
        monster.redMonsters.add(GamePanel.tileSize * 22);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(0);
        monster.redMonsters.add(3);

        monster.redMonsters.add(GamePanel.tileSize * 22);
        monster.redMonsters.add(GamePanel.tileSize * 9);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(1);
        monster.redMonsters.add(3);

        monster.redMonsters.add(GamePanel.tileSize * 26);
        monster.redMonsters.add(GamePanel.tileSize * 22);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(1);
        monster.redMonsters.add(3);

        monster.redMonsters.add(GamePanel.tileSize * 17);
        monster.redMonsters.add(GamePanel.tileSize * 16);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(GamePanel.tileSize);
        monster.redMonsters.add(1);
        monster.redMonsters.add(3);

        monster.orangeMonsters.add(GamePanel.tileSize * 30);
        monster.orangeMonsters.add(GamePanel.tileSize * 19);
        monster.orangeMonsters.add(GamePanel.tileSize);
        monster.orangeMonsters.add(GamePanel.tileSize);
        monster.orangeMonsters.add(1);
        monster.orangeMonsters.add(-1);


        while (Map.blocks.size() > Map.mapSize) {
            Map.blocks.remove(Map.blocks.size()-1);
        }


    }

    static int bonusPoint = 0;
    public void eatBonus() {
        for (int i = 0; i < map.bonusBlocks.size(); i+=4) {
            if (player.getPlayerX() <= map.bonusBlocks.get(i) + map.bonusBlockSize
            && player.getPlayerX() + playerSize >= map.bonusBlocks.get(i)
            && player.getPlayerY() <= map.bonusBlocks.get(i+1) + map.bonusBlockSize
            && player.getPlayerY() + playerSize >= map.bonusBlocks.get(i+1) + map.bonusBlockSize) {
                bonusPoint += 5;
                map.bonusBlocks.remove(i);
                map.bonusBlocks.remove(i);
                map.bonusBlocks.remove(i);
                map.bonusBlocks.remove(i);
                i-=4;
            }
        }
    }

    public void bulletBlockCollapse() {
        for (int i = 0; i < Map.blocks.size(); i++) {
            for (int j = 0; j < bullets.size(); j+=4) {
                if (bullets.get(j+2) + bulletSize > (int) Map.blocks.get(i).get(0)
                && bullets.get(j+2) < (int) Map.blocks.get(i).get(0) + (int) Map.blocks.get(i).get(2)
                && bullets.get(j+3) + bulletSize > (int) Map.blocks.get(i).get(1)
                && bullets.get(j+3) < (int) Map.blocks.get(i).get(1) + (int) Map.blocks.get(i).get(3)) {

                    bullets.remove(j);
                    bullets.remove(j);
                    bullets.remove(j);
                    bullets.remove(j);
                }
            }
        }
    }

    boolean goDown = false;
    boolean goUp = true;

    public void MovingPlayerWithBlock() {
        if (player.getPlayerX() < map.movingBlock.get(0)+map.movingBlock.get(2)
        && player.getPlayerX() + playerSize > map.movingBlock.get(0)
        && player.getPlayerY() + playerSize == map.movingBlock.get(1)) {
            player.setPlayerY(player.getPlayerY()-1);
            isPlayerOn = true;
        }
    }

    boolean isPlayerOn = false;
    public void MovingBlock() {
        if (isPlayerOn) {
            if (goUp && !Objects.equals(map.movingBlock.get(1), map.movingBlock.get(5))) {
                map.movingBlock.set(1, map.movingBlock.get(1) - 1);
                goUp = true;
            }
            if (Objects.equals(map.movingBlock.get(1), map.movingBlock.get(5))) {
                goDown = true;
                goUp = false;
            }
            if (goDown && !Objects.equals(map.movingBlock.get(1), map.movingBlock.get(9))) {
                map.movingBlock.set(1, map.movingBlock.get(1) + 1);
            }
            if (Objects.equals(map.movingBlock.get(1), map.movingBlock.get(9))) {
                goDown = false;
                goUp = true;
                isPlayerOn = false;
            }
        }
    }

    public boolean gameFinish() {
        if (player.getPlayerX() + playerSize > map.finish.get(0)
        && player.getPlayerY() >= map.finish.get(1)
        && player.getPlayerY() + playerSize <= map.finish.get(3)) {
            JOptionPane.showMessageDialog(null, "Tebrikler! Oyun bitti. Skorunuz: " + bonusPoint);
            gameThread = null;
            System.exit(0);
            return true;
        }
        return false;
    }

    int jumpCount = tileSize * 4 / 2; // 2.5 blok zıplar
    int tmpJump = jumpCount;

    boolean jumpBlocker = false;

    int monsterMoveDivider = 3; // zaten canavarlar 1 pixel hareket ediyor bunu daha da yavaşlatmak için kullanıyorum.
    int monsterMoveCounter = 0;


    public void update() {
        if (restartCheck() && !godMode) {
            restart();
        }
        timedBlocksPlayerDetector();
        timedBlocksDestroyer();
        gameFinish();
        bulletBlockCollapse();
        unHide();
        eatBonus();
        if (monsterMoveCounter % monsterMoveDivider == 0) {
            monster.MonsterMove();
            monster.MonsterBorderCheck();
            MovingPlayerWithBlock();
            MovingBlock();
            for (int i = 0; i < monster.monsters.size(); i++) {
                for (int j = 0; j < monster.monsters.get(i).size(); j+=6) {
                    if (monster.MonsterBlockRightLeftCheck((int) monster.monsters.get(i).get(j),
                            (int) monster.monsters.get(i).get(j+1),
                            (int) monster.monsters.get(i).get(j+4)).equals("Sağ"))
                    {
                        monster.monsters.get(i).set(j+4, 0);
                    }

                    else if (monster.MonsterBlockRightLeftCheck((int) monster.monsters.get(i).get(j),
                            (int) monster.monsters.get(i).get(j+1),
                            (int) monster.monsters.get(i).get(j+4)).equals("Sol"))
                    {
                        monster.monsters.get(i).set(j+4, 1);
                    }

                    if (!monster.isMonsterOnBlocks(
                            (int) monster.monsters.get(i).get(j),
                            (int) monster.monsters.get(i).get(j+1)))
                    {
                        monster.monsters.get(i).set(j+1, (int) monster.monsters.get(i).get(j+1) + 1);
                    }
                }
            }
        }
        monster.MonsterHit();
        monster.MonsterSpawner();
        monsterMoveCounter++;

        if (keyH.upPressed && player.getPlayerY() > upperBound && jumpCount > 0 && isUpClear() && !jumpBlocker) { // yukarı zıplama süreci.
            player.setPlayerY(player.getPlayerY() - player.getPlayerSpeed());
            //playerY -= playerSpeed*3/2;
            jumpCount -= player.getPlayerSpeed();
            keyH.upPressed = true;
        }

        else if (isFalling()) { // aşağı inme süreci.
            jumpCount -= player.getPlayerSpeed();
            jumpBlocker = true;
            keyH.upPressed = false;
        }

        if (!isFalling()) { // ancak yere indikten sonra tekrar zıplanabilir.
            jumpCount = tmpJump;
            jumpBlocker = false;
            keyH.upPressed = false;
        }

        if (!keyH.upPressed && player.getPlayerY() < (screenHeight - underBound) && isFalling()) {
            player.setPlayerY(player.getPlayerY() + player.getPlayerSpeed());
        }

        if (keyH.leftPressed && player.getPlayerX() > leftBound && isLeftClear()) {
            player.setPlayerX(player.getPlayerX() - player.getPlayerSpeed());
        }

        if (keyH.rightPressed && player.getPlayerX() < rightBound && isRightClear()) {
            player.setPlayerX(player.getPlayerX() + player.getPlayerSpeed());
        }

        if (keyH.blue || keyH.red){
            player.setCanShoot(true);
            if (isIncreased) {
                jumpCount = tmpJump; // havada renk değiştirirse buga girmesin diye
                jumpCount = tileSize * 4 / 2;
                tmpJump = jumpCount;
                isIncreased = false;
            }

        }

        if (keyH.magenta) {
            player.setCanShoot(false);
            if (!isIncreased) {
                jumpCount = tmpJump;
                jumpCount = tileSize * 5 / 2;
                tmpJump = jumpCount;
                isIncreased = true;
            }
        }

        if (keyH.shooting && player.CanShoot() && fireWaitCount == tmpFire)
        {
            //her dört elemanda bir
            //ilk eleman sağ veya sola ateş
            //ikinci eleman mavi veya kırmızı ateş
            //3. ve 4. elemanlar koordinatları


            if (keyH.left) bullets.add(0); // sola ateş
            else bullets.add(1); // sağa ateş


            if (keyH.blue) bullets.add(0); // mavi mermi
            else bullets.add(1); // kırmızı mermi


            bullets.add(player.getPlayerX());
            bullets.add(player.getPlayerY() + playerSize/4);

            fired = true;

        }
        if (fired)
            fireWaitCount--;
        if (fireWaitCount == 0) {
            fired = false;
            fireWaitCount = tmpFire;
        }

        //mermi yerleri güncellemesi

        for (int i = 0; i < bullets.size(); i += 4) {

            if (bullets.get(i) == 0) { //sola gidecek
                bullets.set(i+2, bullets.get(i+2) - 3);
            }
            else
                bullets.set(i+2, bullets.get(i+2) + 3);

        }

        //ekran dışı
        for (int i = 0; i < bullets.size(); ) {

            if (bullets.get(i) == 0 && bullets.get(i+2) < 0) {
                bullets.remove(i);
                bullets.remove(i);
                bullets.remove(i);
                bullets.remove(i);
            }

            else if (bullets.get(i) == 1 && bullets.get(i+2) > screenWidth) {
                bullets.remove(i);
                bullets.remove(i);
                bullets.remove(i);
                bullets.remove(i);
            }
            else i = i+4;
        }
        player.setColor(keyH.color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < bullets.size(); i=i+4) {
            if (bullets.get(i+1) == 0) { // mavi
                g2.setColor(Color.blue);
            }
            else g2.setColor(Color.red);

            g2.fillRect(bullets.get(i+2), bullets.get(i+3), bulletSize, bulletSize);
        }

        for (int i = 0; i < monster.monsters.size(); i++) {
            if (monster.monsters.get(i) == monster.blueMonsters) g2.setColor(Color.blue);
            else if (monster.monsters.get(i) == monster.redMonsters) g2.setColor(Color.red);
            else g2.setColor(Color.orange);

            for (int j = 0; j < monster.monsters.get(i).size(); j+=6) {
                g2.fillRect((int) monster.monsters.get(i).get(j),(int) monster.monsters.get(i).get(j+1),(int) monster.monsters.get(i).get(j+2),(int) monster.monsters.get(i).get(j+3));
            }
        }

        g2.setColor(Color.yellow);
        for (int i = 0; i < map.bonusBlocks.size(); i+=4) {
            g2.fillRect(map.bonusBlocks.get(i), map.bonusBlocks.get(i+1), map.bonusBlocks.get(i+2), map.bonusBlocks.get(i+3));
        }

        g2.setColor(Color.gray);

        for (int i = 0; i < map.blocks.size(); i++) {
            g2.fillRect((Integer) map.blocks.get(i).get(0), (Integer) map.blocks.get(i).get(1), (Integer) map.blocks.get(i).get(2), (Integer) map.blocks.get(i).get(3));
        }
        for (int i = 0; i < Map.timedBlocks.size(); i ++) {
            g2.fillRect((int) Map.timedBlocks.get(i).get(0), (int) Map.timedBlocks.get(i).get(1), (int) Map.timedBlocks.get(i).get(2), (int) Map.timedBlocks.get(i).get(3));
        }

        g2.setColor(Color.lightGray);
        for (int i = 0; i < map.hiddenBlocks.size(); i++) {
            if ((int) map.hiddenBlocks.get(i).get(8) == 0)
                g2.setColor(Color.lightGray);
            else g2.setColor(Color.gray);
            g2.fillRect((Integer) map.hiddenBlocks.get(i).get(0), (Integer) map.hiddenBlocks.get(i).get(1), (Integer) map.hiddenBlocks.get(i).get(2), (Integer) map.hiddenBlocks.get(i).get(3));
        }

        g2.setColor(Color.orange);
        g2.fillRect(map.movingBlock.get(0), map.movingBlock.get(1), map.movingBlock.get(2), map.movingBlock.get(3));

        g2.setColor(Color.red);
        for (int i = 0; i < map.redBlocks.size(); i++) {
            g2.fillRect((Integer) map.redBlocks.get(i).get(0), (Integer) map.redBlocks.get(i).get(1), (Integer) map.redBlocks.get(i).get(2), (Integer) map.redBlocks.get(i).get(3));
        }

        g2.setColor(Color.blue);
        for (int i = 0; i < map.blueBlocks.size(); i++) {
            g2.fillRect((Integer) map.blueBlocks.get(i).get(0), (Integer) map.blueBlocks.get(i).get(1), (Integer) map.blueBlocks.get(i).get(2), (Integer) map.blueBlocks.get(i).get(3));
        }
        g2.setColor(Color.pink);
        g2.fillRect(map.finish.get(0), map.finish.get(1), map.finish.get(2), map.finish.get(3));

        //player bloğu
        g2.setColor(player.getColor());

        g2.fillRect(player.getPlayerX(),player.getPlayerY(),playerSize,playerSize);

        g2.setColor(Color.white);
        g2.drawString("Your Score: " + bonusPoint, 10, 20);

        g2.dispose();
    }
}

