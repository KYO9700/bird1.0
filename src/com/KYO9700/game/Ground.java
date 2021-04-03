package com.KYO9700.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//2 设计游戏道具
//2.1 设计地面
/**
 * 地面
 */
class Ground {

    // 图片
    BufferedImage image;
    //地面图片的基本参数：位置、宽高，用于绘图
    // 位置
    int x, y;
    // 宽高
    int width, height;

    // 初始化地面
    public Ground() throws Exception {
        image = ImageIO.read(getClass().getResource("/resources/ground.png"));
        //宽高通过读取素材确定
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 500;
    }

    // 地面的动画逻辑，左移、复位，由step()函数实现
    // 向左移动一步
    public void step() {
        x--;
        if (x == -109) {
            x = 0;
        }
    }

}
