

import java.util.ArrayList;

public class Map {

    static ArrayList<ArrayList> map = new ArrayList<>();

    static ArrayList<ArrayList> blocks = new ArrayList<>();
    static ArrayList<ArrayList> hiddenBlocks = new ArrayList<>();
    static ArrayList<ArrayList> blueBlocks = new ArrayList<>();
    static ArrayList<ArrayList> redBlocks = new ArrayList<>();
    static ArrayList<ArrayList> unHiddenBlocks = new ArrayList<>();
    static ArrayList<Integer> bonusBlocks = new ArrayList<>();
    static ArrayList<ArrayList> timedBlocks = new ArrayList<>();
    ArrayList<Integer> finish = new ArrayList<>();
    ArrayList<Integer> movingBlock = new ArrayList<>(); //ilk 4 eleman ilk koordinatları, sonraki 4 eleman gideceği koordinat.


    ArrayList<Integer> timedBlock1 = new ArrayList<>();
    ArrayList<Integer> timedBlock2 = new ArrayList<>();
    ArrayList<Integer> timedBlock3 = new ArrayList<>();
    ArrayList<Integer> timedBlock4 = new ArrayList<>();
    ArrayList<Integer> timedBlock5 = new ArrayList<>();
    ArrayList<Integer> timedBlock6 = new ArrayList<>();
    ArrayList<Integer> timedBlock7 = new ArrayList<>();
    ArrayList<Integer> timedBlock8 = new ArrayList<>();
    ArrayList<Integer> block1 = new ArrayList<>();
    ArrayList<Integer> block2 = new ArrayList<>();
    ArrayList<Integer> block3 = new ArrayList<>();
    ArrayList<Integer> block4 = new ArrayList<>();
    ArrayList<Integer> block5 = new ArrayList<>();
    ArrayList<Integer> layout6 = new ArrayList<>();
    ArrayList<Integer> layout7 = new ArrayList<>();
    ArrayList<Integer> layout8 = new ArrayList<>();
    ArrayList<Integer> layout9 = new ArrayList<>();
    ArrayList<Integer> layout10 = new ArrayList<>();
    ArrayList<Integer> layout11 = new ArrayList<>();
    ArrayList<Integer> layout12 = new ArrayList<>();
    ArrayList<Integer> layout13 = new ArrayList<>();
    ArrayList<Integer> layout14 = new ArrayList<>();
    ArrayList<Integer> layout15 = new ArrayList<>();
    ArrayList<Integer> layout16 = new ArrayList<>();
    ArrayList<Integer> layout17 = new ArrayList<>();
    ArrayList<Integer> layout18 = new ArrayList<>();


    ArrayList<Integer> hidden1 = new ArrayList<>();
    ArrayList<Integer> hidden2 = new ArrayList<>();
    ArrayList<Integer> hidden3 = new ArrayList<>();


    ArrayList<Integer> red1 = new ArrayList<>();
    ArrayList<Integer> red2 = new ArrayList<>();

    ArrayList<Integer> blue1 = new ArrayList<>();
    ArrayList<Integer> blue2 = new ArrayList<>();

    final static int bonusBlockSize = GamePanel.tileSize/4;

    static int mapSize;





    public Map() {


        block1.add(GamePanel.tileSize * 0); // x koordinatı
        block1.add(GamePanel.screenHeight - GamePanel.tileSize); // y koordinatı
        block1.add(GamePanel.screenWidth); // genişlik
        block1.add(GamePanel.tileSize); // yükseklik

        block2.add(GamePanel.tileSize * 30); // x koordinatı
        block2.add(GamePanel.tileSize * 20); // y koordinatı
        block2.add(GamePanel.tileSize * 3); // genişlik
        block2.add(GamePanel.tileSize * 1); // yükseklik

        block3.add(GamePanel.tileSize * 29); // x koordinatı
        block3.add(GamePanel.tileSize * 15); // y koordinatı
        block3.add(GamePanel.tileSize * 1); // genişlik
        block3.add(GamePanel.tileSize * 6); // yükseklik

        block4.add(GamePanel.tileSize * 33); // x koordinatı
        block4.add(GamePanel.tileSize * 19); // y koordinatı
        block4.add(GamePanel.tileSize * 1); // genişlik
        block4.add(GamePanel.tileSize * 2); // yükseklik

        block5.add(GamePanel.tileSize * 0); // x koordinatı
        block5.add(GamePanel.tileSize * 17); // y koordinatı
        block5.add(GamePanel.tileSize * 24); // genişlik
        block5.add(GamePanel.tileSize * 1); // yükseklik

        layout7.add(GamePanel.tileSize * 3); // x koordinatı
        layout7.add(GamePanel.tileSize * 8); // y koordinatı
        layout7.add(GamePanel.tileSize * 1); // genişlik
        layout7.add(GamePanel.tileSize * 8); // yükseklik

        /*
        layout8.add(GamePanel.tileSize * 0); // x koordinatı
        layout8.add(GamePanel.tileSize * 14); // y koordinatı
        layout8.add(GamePanel.tileSize * 1); // genişlik
        layout8.add(GamePanel.tileSize * 1); // yükseklik

        layout9.add(GamePanel.tileSize * 2); // x koordinatı
        layout9.add(GamePanel.tileSize * 12); // y koordinatı
        layout9.add(GamePanel.tileSize * 1); // genişlik
        layout9.add(GamePanel.tileSize * 1); // yükseklik

        layout10.add(GamePanel.tileSize * 0); // x koordinatı
        layout10.add(GamePanel.tileSize * 10); // y koordinatı
        layout10.add(GamePanel.tileSize * 1); // genişlik
        layout10.add(GamePanel.tileSize * 1); // yükseklik
        */

        layout11.add(GamePanel.tileSize * 3); // x koordinatı
        layout11.add(GamePanel.tileSize * 8); // y koordinatı
        layout11.add(GamePanel.tileSize * 2); // genişlik
        layout11.add(GamePanel.tileSize * 1); // yükseklik

        layout12.add(GamePanel.tileSize * 10); // x koordinatı
        layout12.add(GamePanel.tileSize * 10); // y koordinatı
        layout12.add(GamePanel.tileSize * 17); // genişlik
        layout12.add(GamePanel.tileSize * 1); // yükseklik

        layout13.add(GamePanel.tileSize * 9); // x koordinatı
        layout13.add(GamePanel.tileSize * 9); // y koordinatı
        layout13.add(GamePanel.tileSize * 1); // genişlik
        layout13.add(GamePanel.tileSize * 2); // yükseklik

        layout14.add(GamePanel.tileSize * 27); // x koordinatı
        layout14.add(GamePanel.tileSize * 7); // y koordinatı
        layout14.add(GamePanel.tileSize * 1); // genişlik
        layout14.add(GamePanel.tileSize * 4); // yükseklik

        layout15.add(GamePanel.tileSize * 30); // x koordinatı
        layout15.add(GamePanel.tileSize * 2); // y koordinatı
        layout15.add(GamePanel.tileSize * 6); // genişlik
        layout15.add(GamePanel.tileSize * 1); // yükseklik

        timedBlock1.add(GamePanel.screenWidth - GamePanel.tileSize * 1 / 2); // x koordinatı
        timedBlock1.add(GamePanel.screenHeight - GamePanel.tileSize * 5 / 2); // y koordinatı
        timedBlock1.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock1.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock1.add(GamePanel.FPS*3);
        timedBlock1.add(0);


        timedBlock2.add(GamePanel.screenWidth - GamePanel.tileSize * 8 / 2); // x koordinatı
        timedBlock2.add(GamePanel.screenHeight - GamePanel.tileSize * 13 / 2); // y koordinatı
        timedBlock2.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock2.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock2.add(GamePanel.FPS*3);
        timedBlock2.add(0);

        timedBlock3.add(GamePanel.screenWidth - GamePanel.tileSize * 12 / 2); // x koordinatı
        timedBlock3.add(GamePanel.screenHeight - GamePanel.tileSize * 15 / 2); // y koordinatı
        timedBlock3.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock3.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock3.add(GamePanel.FPS*3);
        timedBlock3.add(0);

        timedBlock4.add(GamePanel.screenWidth - GamePanel.tileSize * 4 / 2); // x koordinatı
        timedBlock4.add(GamePanel.screenHeight - GamePanel.tileSize * 8 / 2); // y koordinatı
        timedBlock4.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock4.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock4.add(GamePanel.FPS*3);
        timedBlock4.add(0);

        timedBlock5.add(GamePanel.screenWidth - GamePanel.tileSize * 19 / 2); // x koordinatı
        timedBlock5.add(GamePanel.tileSize * 16 / 2); // y koordinatı
        timedBlock5.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock5.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock5.add(GamePanel.FPS*3);
        timedBlock5.add(0);

        timedBlock6.add(GamePanel.screenWidth - GamePanel.tileSize * 41 / 2); // x koordinatı
        timedBlock6.add(GamePanel.tileSize * 16 / 2); // y koordinatı
        timedBlock6.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock6.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock6.add(GamePanel.FPS*3);
        timedBlock6.add(0);

        timedBlock7.add(GamePanel.screenWidth - GamePanel.tileSize * 34 / 2); // x koordinatı
        timedBlock7.add(GamePanel.tileSize * 11 / 2); // y koordinatı
        timedBlock7.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock7.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock7.add(GamePanel.FPS*3);
        timedBlock7.add(0);

        timedBlock8.add(GamePanel.screenWidth - GamePanel.tileSize * 18 / 2); // x koordinatı
        timedBlock8.add(GamePanel.tileSize * 6 / 2); // y koordinatı
        timedBlock8.add(GamePanel.tileSize * 1 / 2); // genişlik
        timedBlock8.add(GamePanel.tileSize * 1 / 2); // yükseklik
        timedBlock8.add(GamePanel.FPS*3);
        timedBlock8.add(0);

        timedBlocks.add(timedBlock1);
        timedBlocks.add(timedBlock2);
        timedBlocks.add(timedBlock3);
        timedBlocks.add(timedBlock4);
        timedBlocks.add(timedBlock5);
        timedBlocks.add(timedBlock6);
        timedBlocks.add(timedBlock7);
        timedBlocks.add(timedBlock8);




        hidden1.add(GamePanel.tileSize * 17); // x koordinatı
        hidden1.add(GamePanel.tileSize * 7); // y koordinatı
        hidden1.add(GamePanel.tileSize * 1); // genişlik
        hidden1.add(GamePanel.tileSize * 1); // yükseklik
        // gizlenmiş bloğu normal bloğa çevirecek bloğun koordinatları
        hidden1.add(GamePanel.tileSize * 27 + GamePanel.tileSize / 4); // x koordinatı
        hidden1.add(GamePanel.tileSize * 7 - 2); // y koordinatı
        hidden1.add(GamePanel.tileSize * 1 / 2); // genişlik
        hidden1.add(2); // yükseklik
        hidden1.add(0);

        hidden2.add(GamePanel.tileSize * 13); // x koordinatı
        hidden2.add(GamePanel.tileSize * 7); // y koordinatı
        hidden2.add(GamePanel.tileSize * 1); // genişlik
        hidden2.add(GamePanel.tileSize * 1); // yükseklik
        // gizlenmiş bloğu normal bloğa çevirecek bloğun koordinatları
        hidden2.add(GamePanel.tileSize * 17 + GamePanel.tileSize / 4); // x koordinatı
        hidden2.add(GamePanel.tileSize * 7 - 2); // y koordinatı
        hidden2.add(GamePanel.tileSize * 1 / 2); // genişlik
        hidden2.add(2); // yükseklik
        hidden2.add(0);

        hidden3.add(GamePanel.tileSize * 21); // x koordinatı
        hidden3.add(GamePanel.tileSize * 4); // y koordinatı
        hidden3.add(GamePanel.tileSize * 4); // genişlik
        hidden3.add(GamePanel.tileSize * 1); // yükseklik
        // gizlenmiş bloğu normal bloğa çevirecek bloğun koordinatları
        hidden3.add(GamePanel.tileSize * 13 + GamePanel.tileSize / 4); // x koordinatı
        hidden3.add(GamePanel.tileSize * 7 - 2); // y koordinatı
        hidden3.add(GamePanel.tileSize * 1 / 2); // genişlik
        hidden3.add(2); // yükseklik
        hidden3.add(0);

        //mevcut yeri
        movingBlock.add(GamePanel.tileSize * 0); // x koordinatı
        movingBlock.add(GamePanel.tileSize * 17); // y koordinatı
        movingBlock.add(GamePanel.tileSize * 3); // genişlik
        movingBlock.add(GamePanel.tileSize * 1 / 2); // yükseklik

        //gideceği yer
        movingBlock.add(GamePanel.tileSize * 0); // x koordinatı
        movingBlock.add(GamePanel.tileSize * 8); // y koordinatı
        movingBlock.add(GamePanel.tileSize * 3); // genişlik
        movingBlock.add(GamePanel.tileSize * 1 / 2); // yükseklik

        //döneceği yer
        movingBlock.add(GamePanel.tileSize * 0); // x koordinatı
        movingBlock.add(GamePanel.tileSize * 17); // y koordinatı
        movingBlock.add(GamePanel.tileSize * 3); // genişlik
        movingBlock.add(GamePanel.tileSize * 1 / 2); // yükseklik

        red1.add(GamePanel.tileSize * 9); // x koordinatı
        red1.add(GamePanel.tileSize * 17); // y koordinatı
        red1.add(GamePanel.tileSize * 3); // genişlik
        red1.add(GamePanel.tileSize * 1 / 2); // yükseklik

        red2.add(GamePanel.tileSize * 0); // x koordinatı
        red2.add(GamePanel.tileSize * 13); // y koordinatı
        red2.add(GamePanel.tileSize * 3); // genişlik
        red2.add(GamePanel.tileSize * 1 / 2); // yükseklik

        blue1.add(GamePanel.tileSize * 14); // x koordinatı
        blue1.add(GamePanel.tileSize * 17); // y koordinatı
        blue1.add(GamePanel.tileSize * 3); // genişlik
        blue1.add(GamePanel.tileSize * 1 / 2); // yükseklik

        blue2.add(GamePanel.tileSize * 0); // x koordinatı
        blue2.add(GamePanel.tileSize * 9); // y koordinatı
        blue2.add(GamePanel.tileSize * 3); // genişlik
        blue2.add(GamePanel.tileSize * 1 / 2); // yükseklik

        finish.add(GamePanel.screenWidth - GamePanel.playerSize * 2);
        finish.add(0);
        finish.add(GamePanel.playerSize * 2);
        finish.add(GamePanel.tileSize * 2);






        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        //blocks.add(layout6);
        blocks.add(layout7);
        //blocks.add(layout8);
        //blocks.add(layout9);
        //blocks.add(layout10);
        blocks.add(layout11);
        blocks.add(layout12);
        blocks.add(layout13);
        blocks.add(layout14);
        blocks.add(layout15);
        //blocks.add(layout16);
        //blocks.add(layout17);
        //blocks.add(layout18);
        blocks.add(movingBlock);

        hiddenBlocks.add(hidden1);
        hiddenBlocks.add(hidden2);
        hiddenBlocks.add(hidden3);

        redBlocks.add(red1);
        redBlocks.add(red2);
        blueBlocks.add(blue1);
        blueBlocks.add(blue2);

        map.add(blocks);
        map.add(timedBlocks);
        map.add(unHiddenBlocks);
        map.add(redBlocks);
        map.add(blueBlocks);

        for (int i = 0; i<blocks.size(); i++) {

            for (int k = (int) blocks.get(i).get(0) + GamePanel.playerSize * 2;k < (int) blocks.get(i).get(0) + (int) blocks.get(i).get(2); k += GamePanel.playerSize * 2) {
                // i blocks arrayinde dönerken k blockların üstüne bonus block ekleyecek
                bonusBlocks.add(k); // x koordinatı
                bonusBlocks.add((int) blocks.get(i).get(1) - GamePanel.tileSize/4 * 2); // y koordinatı
                bonusBlocks.add(GamePanel.tileSize/4); // genişlik
                bonusBlocks.add(GamePanel.tileSize/4); // kalınlık
            }
        }
        mapSize = blocks.size();
    }
}

