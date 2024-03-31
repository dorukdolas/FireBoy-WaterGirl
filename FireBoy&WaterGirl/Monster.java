
import java.util.ArrayList;

public class Monster {
    ArrayList<ArrayList> monsters = new ArrayList<>();
    ArrayList<Integer> blueMonsters = new ArrayList<>();
    ArrayList<Integer> redMonsters = new ArrayList<>();
    ArrayList<Integer> orangeMonsters = new ArrayList<>();



    public Monster() {
        blueMonsters.add(GamePanel.tileSize * 7); // x koordinatı
        blueMonsters.add(GamePanel.tileSize * 22); // y koordinatı
        blueMonsters.add(GamePanel.tileSize); // size
        blueMonsters.add(GamePanel.tileSize); // size
        blueMonsters.add(0); // 0 = sola hareket, 1 = sağa hareket
        blueMonsters.add(3);

        blueMonsters.add(GamePanel.tileSize * 15);
        blueMonsters.add(GamePanel.tileSize * 9);
        blueMonsters.add(GamePanel.tileSize);
        blueMonsters.add(GamePanel.tileSize);
        blueMonsters.add(0);
        blueMonsters.add(3);

        blueMonsters.add(GamePanel.tileSize * 7);
        blueMonsters.add(GamePanel.tileSize * 16);
        blueMonsters.add(GamePanel.tileSize);
        blueMonsters.add(GamePanel.tileSize);
        blueMonsters.add(0);
        blueMonsters.add(3);

        redMonsters.add(GamePanel.tileSize * 15);
        redMonsters.add(GamePanel.tileSize * 22);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(0);
        redMonsters.add(3);

        redMonsters.add(GamePanel.tileSize * 22);
        redMonsters.add(GamePanel.tileSize * 9);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(1);
        redMonsters.add(3);

        redMonsters.add(GamePanel.tileSize * 26);
        redMonsters.add(GamePanel.tileSize * 22);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(1);
        redMonsters.add(3);

        redMonsters.add(GamePanel.tileSize * 17);
        redMonsters.add(GamePanel.tileSize * 16);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(GamePanel.tileSize);
        redMonsters.add(1);
        redMonsters.add(3);

        orangeMonsters.add(GamePanel.tileSize * 30);
        orangeMonsters.add(GamePanel.tileSize * 19);
        orangeMonsters.add(GamePanel.tileSize);
        orangeMonsters.add(GamePanel.tileSize);
        orangeMonsters.add(1);
        orangeMonsters.add(-1);



        monsters.add(blueMonsters);
        monsters.add(redMonsters);
        monsters.add(orangeMonsters);
    }

    public void MonsterMove() {
        for (ArrayList monster : monsters) {
            for (int j = 0; j < monster.size(); j += 6) {

                if ((int) monster.get(j + 4) == 0) {
                    monster.set(j, (int) monster.get(j) - 1);
                } else monster.set(j, (int) monster.get(j) + 1);
            }
        }
    }

    public void MonsterBorderCheck() {
        for (ArrayList monster : monsters) {
            for (int j = 0; j < monster.size(); j += 6) {
                if ((int) monster.get(j) < 0) {
                    monster.set(j + 4, 1);
                }
                if ((int) monster.get(j) + (int) monster.get(j + 2) > GamePanel.screenWidth) {
                    monster.set(j + 4, 0);
                }
            }
        }
    }

    public String MonsterBlockRightLeftCheck(int x, int y, int direction) {
        for (int i = 0; i < Map.blocks.size(); i++) {
            ArrayList<Integer> tmp = Map.blocks.get(i);
            if (direction == 1) {
                if (x + GamePanel.tileSize + 1 > tmp.get(0) && x < tmp.get(0) + tmp.get(2)) {
                    if (y < tmp.get(1) + tmp.get(3) && y + GamePanel.tileSize > tmp.get(1)) {
                        return "Sağ";
                    }
                }
            }
            else if (x < tmp.get(0) + tmp.get(2) + 1 && x + 1> tmp.get(0)) {
                if (y < tmp.get(1) + tmp.get(3) && y + 1 > tmp.get(1)) {
                    return "Sol";
                }
            }
        }
        return "Boş";
    }

    public boolean isMonsterOnBlocks(int x, int y) {
        for (int i = 0; i < Map.blocks.size(); i++) {
            ArrayList<Integer> tmp = Map.blocks.get(i);
            if (x + GamePanel.tileSize > tmp.get(0) && x < tmp.get(0) + tmp.get(2)) {
                if (y + GamePanel.tileSize == tmp.get(1)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean decreaseHealth;

    public void MonsterHit() {

        for (int j = 0; j < monsters.size(); j++) {
            for (int k = 0; k < monsters.get(j).size(); k+=6) {
                for (int i = 0; i < GamePanel.bullets.size(); i+=4) {
                    if (GamePanel.bullets.get(i + 1) == 0) { // kırmızı mermi
                        decreaseHealth = j == 1;
                    }
                    else { // mavi mermi
                        decreaseHealth = j == 0;
                    }
                    if (j < monsters.size() && k < monsters.get(j).size()) {
                        if (GamePanel.bullets.get(i + 2) + GamePanel.bulletSize > (int) monsters.get(j).get(k)
                                && GamePanel.bullets.get(i + 2) < (int) monsters.get(j).get(k) + (int) monsters.get(j).get(k + 2)
                                && GamePanel.bullets.get(i + 3) < (int) monsters.get(j).get(k + 1) + (int) monsters.get(j).get(k + 3)
                                && GamePanel.bullets.get(i + 3) + GamePanel.bulletSize > (int) monsters.get(j).get(k + 1)) {
                            GamePanel.bullets.remove(i);
                            GamePanel.bullets.remove(i);
                            GamePanel.bullets.remove(i);
                            GamePanel.bullets.remove(i);
                            if (decreaseHealth) {
                                monsters.get(j).set(k + 5, (int) monsters.get(j).get(k + 5) - 1);
                                if ((int) monsters.get(j).get(k + 5) == 0) {
                                    monsters.get(j).remove(k);
                                    monsters.get(j).remove(k);
                                    monsters.get(j).remove(k);
                                    monsters.get(j).remove(k);
                                    monsters.get(j).remove(k);
                                    monsters.get(j).remove(k);
                                    GamePanel.bonusPoint += 10;
                                }
                            } else {
                                monsters.get(j).set(k + 5, (int) monsters.get(j).get(k + 5) + 1);
                            }
                        }
                    }

                }
            }
        }
    }



    int spawnerWait = GamePanel.FPS * 15 - 1; // fps * x ----> x saniye kadar bekle
    int spawnerTmp = spawnerWait + 1;
    int spawnerX = GamePanel.tileSize * 26, spawnerY = GamePanel.tileSize * 5;
    public void MonsterSpawner() {
        if (spawnerWait == spawnerTmp) {
            int randomColor = (int) (Math.random() * 3); // 0 = mavi, 1 = kırmızı, 2 = turuncu

            if (randomColor == 0) {
                blueMonsters.add(spawnerX);
                blueMonsters.add(spawnerY);
                blueMonsters.add(GamePanel.tileSize);
                blueMonsters.add(GamePanel.tileSize);
                blueMonsters.add(0);
                blueMonsters.add(3);
            }
            if (randomColor == 1) {
                redMonsters.add(spawnerX);
                redMonsters.add(spawnerY);
                redMonsters.add(GamePanel.tileSize);
                redMonsters.add(GamePanel.tileSize);
                redMonsters.add(0);
                redMonsters.add(3);
            }
            if (randomColor == 2) {
                orangeMonsters.add(spawnerX);
                orangeMonsters.add(spawnerY);
                orangeMonsters.add(GamePanel.tileSize);
                orangeMonsters.add(GamePanel.tileSize);
                orangeMonsters.add(0);
                orangeMonsters.add(3);
            }

        }
        spawnerWait--;
        if (spawnerWait == 0) spawnerWait = spawnerTmp;
    }
}

