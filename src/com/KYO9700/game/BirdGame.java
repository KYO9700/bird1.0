package com.KYO9700.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 游戏界面
 */
//1 创建游戏面板

public class BirdGame extends JPanel {

    // 背景图片
    static BufferedImage background;
    // 开始图片
    BufferedImage startImage;
    // 结束图片
    BufferedImage gameOverImage;

    // 地面
    Ground ground;
    // 柱子
    Column column1, column2;
    // 小鸟
    Bird bird;

    // 游戏分数
    int score;

    // 游戏状态
    int state;
    // 状态常量
    public static final int START = 0; //开始
    public static final int RUNNING = 1; //运行
    public static final int GAME_OVER = 2; //结束

    /**
     * 初始化游戏
     */
    public BirdGame() throws Exception {
        // 初始化背景图片
        //java.lang.Object
        // javax.imageio.ImageIO
        background = ImageIO.read(getClass().getResource("/resources/bg.png"));
        // 初始化开始、结束图片
        startImage = ImageIO.read(getClass().getResource("/resources/start.png"));
        gameOverImage = ImageIO.read(getClass().getResource("/resources/gameover.png"));

        // 初始化地面、柱子、小鸟
        ground = new Ground();
        column1 = new Column(1);
        column2 = new Column(2);
        bird = new Bird();

        // 初始化分数
        score = 0;

        // 初始化状态
        state = START;
    }

    /**
     * 绘制界面
     */
    //重写实现paint方法
    //java绘图时，最常使用到的就是paint(Graphics g)｛...内容...｝方法获取画笔，
    //然后利用JPanel等容器作为画布,在JFrame内呈现出内容
    @Override
    public void paint(Graphics g) {
        // 绘制背景
        g.drawImage(background, 0, 0, null);

    }

    /**
     * 启动方法
     */
    public static void main(String[] args) throws Exception {
        //JFrame()创建无标题的初始不可见框架
        JFrame frame = new JFrame();
        //将子组件添加到框架中，frame.add(child); 子组件将被添加到contentPane
        //将指定的组件附加到此容器的末尾。
        //该方法更改布局相关信息，因此使组件层次结构无效。 如果容器已经被显示，则此后必须验证层次结构，以显示添加的组件。
        BirdGame game = new BirdGame();
        frame.add(game);
        //调整此组件的大小，使其宽度为width ，高度为height 。
        //该方法更改布局相关信息，因此使组件层次结构无效。
        frame.setSize(440, 670);
        //设置组件的位置
        //如果组件是null ，或GraphicsConfiguration与此组件关联是null时，窗口被放置在屏幕的中心。 中心点可以使用GraphicsEnvironment.getCenterPoint方法获得。
        //如果组件不是null ，但它当前没有显示，则该窗口将放置在与此组件GraphicsConfiguration GraphicsConfiguration定义的目标屏幕的中心。
        //如果组件不是null并显示在屏幕上，则窗口的位置使得窗口的中心与组件的中心重合。
        frame.setLocationRelativeTo(null);
        //设置用户在此框架上启动“关闭”时默认执行的操作。 您指定以下选项之一：
        //DO_NOTHING_ON_CLOSE （定义在WindowConstants ）：不要做任何事情; 要求程序处理WindowListener对象的windowClosing方法的操作。
        //HIDE_ON_CLOSE （在WindowConstants定义）：在调用任何已注册的WindowListener对象后自动隐藏框架。
        //DISPOSE_ON_CLOSE （在WindowConstants定义）：在调用任何已注册的WindowListener对象后自动隐藏和处理该框架。
        //EXIT_ON_CLOSE （在JFrame定义）：使用System exit方法退出exit程序。 仅在应用程序中使用。
        //该值默认设置为HIDE_ON_CLOSE 。 对此属性的值的更改导致触发属性更改事件，属性名称为“defaultCloseOperation”。
        //注意 ：当Java虚拟机（VM）中的最后一个可显示的窗口被丢弃时，VM可能会终止。
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示窗口
        frame.setVisible(true);
    }

}
