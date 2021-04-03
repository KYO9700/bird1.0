package com.KYO9700.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

//2 设计游戏道具
//2.2 设计柱子
/**
 * 柱子
 */
class Column {

    // 图片
    BufferedImage image;
    // 作图的基本参数，位置和宽高
    // 位置
    int x, y;
    // 宽高
    int width, height;
    // 柱子之间的缝隙，固定的，与素材图片一致
    int gap;
    // 柱子之间的距离，这里设置为固定的
    int distance;
    // 随机数工具，用于确定缝隙的高度偏移
    Random random = new Random();

    /**
     * 初始化第N个柱子
     */
    public Column(int n) throws Exception {
        image = ImageIO.read(getClass().getResource("/resources/column.png"));
        //宽高通过读取素材确定
        width = image.getWidth();
        height = image.getHeight();
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(218) + 132;
    }

    // 柱子地面的动画逻辑，与地面的动画逻辑类似，左移、复位，由step()函数实现
    // 向左移动一步
    public void step() {
        x--;
        //假如柱子已经完全移出画面，再去到画面前方，并重设高度偏移
        if (x == -width / 2) {
            x = distance * 2 - width / 2;
            y = random.nextInt(218) + 132;
        }
    }
}