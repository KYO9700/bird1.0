package com.KYO9700.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//鼠标单击事件监听器需要的包
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 游戏界面
 */
//1 创建游戏面板

//java.lang.Object
// java.awt.Component
//  java.awt.Container
//   javax.swing.JComponent
//    javax.swing.JPanel
//JPanel是一个通用的轻量级容器。
//public class JPanel extends JComponent implements Accessible
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

        // 绘制地面
        g.drawImage(ground.image, ground.x, ground.y, null);

        // 绘制柱子
        g.drawImage(column1.image, column1.x - column1.width / 2, column1.y
                - column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2, column2.y
                - column2.height / 2, null);

        // 绘制小鸟（旋转坐标系）
        //java.lang.Object
        // java.awt.Graphics
        //  java.awt.Graphics2D
        //该Graphics2D类扩展了Graphics类，以提供对几何，坐标变换，颜色管理和文本布局的更复杂的控制。 这是在Java（tm）平台上呈现二维形状，文字和图像的基础类。
        Graphics2D g2 = (Graphics2D) g;
        //随后的渲染绕旋转中心以指定弧度旋转，画出的小鸟的旋转角度由bird对象的属性alpha确定
        g2.rotate(-bird.alpha, bird.x, bird.y);
        g.drawImage(bird.image,
                bird.x - bird.width / 2, bird.y - bird.height / 2, null);
        //将之前的渲染旋转角度恢复
        g2.rotate(bird.alpha, bird.x, bird.y);

        // 绘制分数
        //java.lang.Object
        // java.awt.Font
        //Font类表示字体，用于以可见的方式呈现文本。 一种字体提供映射字符 字形的序列的序列和上呈现字形序列所需的信息Graphics个Component对象。
        //从指定的名称，样式和点大小创建一个新的 Font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);//BOLD-大胆的风格常数。SANS_SERIF-逻辑字体“SansSerif”的规范系列名称的字符串常量。
        g.setFont(f);
        g.drawString("" + score, 40, 60);
        g.setColor(Color.WHITE);
        g.drawString("" + score, 40 - 3, 60 - 3);

        // 绘制开始与结束界面
        switch (state) {
            case START:
                g.drawImage(startImage, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOverImage, 0, 0, null);
                break;
        }
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
        //调用开始游戏的方法
        game.action();
    }

    // 开始游戏的方法
    public void action() throws Exception {
        // 鼠标监听器
        // 适配器类已经实现相应接口，MouseAdapter类实现了MouseListener接口，
        // 因此可以使用MouseAdapter的子类创建的对象做监视器，只需重写需要的接口方法即可。
        MouseListener l = new MouseAdapter() {
            // 鼠标按下事件
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    switch (state) {
                        case START:
                            // 在开始状态，按下鼠标则转为运行状态。
                            state = RUNNING;
                            break;
                        case RUNNING:
                            // 在运行状态，按下鼠标则小鸟向上飞行。
                            bird.flappy();
                            break;
                        case GAME_OVER:
                            // 在结束状态，按下鼠标则重置数据，再次转为开始态。
                            column1 = new Column(1);
                            column2 = new Column(2);
                            bird = new Bird();
                            score = 0;
                            state = START;
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        // 将监听器添加到当前的面板上
        addMouseListener(l);

        // 不断的移动与重绘
        while (true) {
            switch (state) {
                case START:
                    // 小鸟做出飞行动作
                    bird.fly();
                    // 地面向左移动一步
                    ground.step();
                    break;
                case RUNNING:
                    // 地面向左移动一步
                    ground.step();
                    // 柱子向左移动一步
                    column1.step();
                    column2.step();
                    // 小鸟做出飞行动作
                    bird.fly();
                    // 小鸟上下移动一步
                    bird.step();
                    // 计算分数
                    if (bird.x == column1.x || bird.x == column2.x) {
                        score++;
                    }
                    // 检测是否发生碰撞
                    if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
                        state = GAME_OVER;
                    }
                    break;
            }
            // 调用repaint方法重新绘制界面
            //public void repaint(long tm)重写组件。
            //public void repaint()重新编辑这个组件。
            //如果此组件是轻量级组件，则此方法将尽快调用此组件的paint方法。
            //否则，该方法会尽快调用此组件的update方法。
            repaint();
            // 休眠 1000/60 毫秒
            Thread.sleep(1000 / 60);
        }
    }

}
