package com.lb.test;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //声明一个数组，用来接收7个球的数字
        int[] balls = new int[7]; //提升作用域
        //购买注数
        int count = 0; //提升作用域

        //定义一个变量，用来设定是否购买彩票
        boolean isBuy = false; //默认情况下没有买彩票


        while (true) {
            //写菜单
            System.out.println("------欢迎进入双色球彩票系统------");
            System.out.println("1.购买彩票");
            System.out.println("2.查看猜奖");
            System.out.println("3.退出");
            System.out.println("请选择你需要的功能：");

            //扫描器
            Scanner scanner = new Scanner(System.in);
            //从键盘接收一个int类型的数据
            int choice = scanner.nextInt();
            //做过滤
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("输出错误，请重新输入：");
                int newChoice = scanner.nextInt();
                choice = newChoice;
            }
            switch (choice) {
                case 1:
                    System.out.println("双色球彩票系统--->购买彩票");
                    System.out.println("你要购买几注：");
                    count = scanner.nextInt(); //购买注数
//                    //声明一个数组，用来接收7个球的数字
//                    int[] balls = new int[7];
                    for (int i = 1; i <= 7; i++) {
                        if (i != 7) { //输入红色球
                            System.out.println("请输入第"+ i +"个红色球：");
                            int redBall = scanner.nextInt();
                            balls[i-1] = redBall;
                        }else { //i==7输入蓝色球
                            System.out.println("请输入第1个蓝色球：");
                            int blueBall = scanner.nextInt();
                            balls[6] = blueBall;
                        }
                    }
                    System.out.println("你一共买了"+ count +"注，一共花费"+ count * 2 +"元：所选号码为：");
                    //遍历数组
                    for (int num :balls) {
                        System.out.print(num +"\t");
                    }
                    //换行
                    System.out.println();
                    //彩票购买
                    isBuy = true;
                    break;
                case 2:
                    if (isBuy) {
                        //1.购买号码-->balls

                        //2.中奖号码
                        int[] luckBall = getLuckBall();
                        //3.两组号码进行比对
                        int level = getLevel(balls, luckBall);
                        //4.根据level的结果执行后面的逻辑
                        switch (level) {
                            case 1:
                                System.out.println("恭喜你，中了一等奖，1注100万元，你一共获得："+count * 100+"万元");
                                break;
                            case 2:
                                System.out.println("恭喜你，中了二等奖，1注50万元，你一共获得："+count * 50+"万元");
                                break;
                            case 3:
                                System.out.println("恭喜你，中了三等奖，1注1万元，你一共获得："+count * 1+"万元");
                                break;
                            case 4:
                                System.out.println("恭喜你，中了四等奖，1注1000你一共获得："+count * 1000+"元");
                                break;
                            case 5:
                                System.out.println("恭喜你，中了五等奖，1注10元，你一共获得："+count * 10+"元");
                                break;
                            case 6:
                                System.out.println("恭喜你，中了六等奖，谢谢惠顾！");
                                break;
                        }
                        System.out.println("双色球彩票系统--->查看猜奖");
                    }else { //没买彩票，提示
                        System.out.println("请先购买！");
                    }
                    break;
                case 3:
                    System.out.println("双色球彩票系统--->退出");
                    return; //遇到return结束方法
            }
        }
    }

    //定义一个方法，用来生成中奖号码
    public static int[] getLuckBall () {
        //测试：固定中奖号码
        //int[] luckBall = {1,2,3,6,5,4,7};
        /*
        红球：1-33
        篮球：1=16
         */
        int[] luckBall = new int[7];


        for (int i = 1; i <= 7; i++) {
            if (i != 7) { //给红色球赋值
                luckBall[i - 1] = (int) (Math.random()*33+1);
            }else { //i==7给蓝色球赋值
                luckBall[6] = (int) (Math.random()*16+1);
            }
        }
        return luckBall;
    }

    //定义一个方法，用来比对购买号码和中奖号码
    public static int getLevel(int[] balls, int[] luckBall) {
        int level = 1;
        //计数器：用来计红球有几个相等
        int redCount = 0;
        //计数器：用来计红球有几个相等
        int blueCount = 0;

        //将我们所选的号码一个一个与中奖号码比对
        //遍历购买的号码
        for (int i = 0; i <= 6; i++) {
            if (i != 6) { //i：0-5 比对红球
                for (int j = 0; j <= 5; j++) {
                    if (balls[i] == luckBall[j]) {
                        redCount++;
                    }
                }
            }else { //i：6 蓝色球
                if (balls[6] == luckBall[6]) {
                    blueCount++;
                }
            }
        }

        //输出比对结果
        System.out.println("红球有"+ redCount +"个相等");
        System.out.println("蓝球有"+ blueCount +"个相等");

        //根据红球和蓝球的相等数量得到level的具体结果
        if (redCount == 6 && blueCount == 1) {
            level = 1;
        }else if (redCount == 6 || (redCount == 5 && blueCount == 1)) {
            level = 2;
        }else if (redCount == 5 || (redCount == 4 && blueCount == 1)) {
            level = 3;
        }else if (redCount == 4 || (redCount == 3 && blueCount == 1)) {
            level = 4;
        }else if ((redCount == 3 || (redCount == 2 && blueCount == 1)) || (redCount == 2 || (redCount == 1 && blueCount == 1)) ){
            level = 5;
        } else {
            level = 6;
        }
        return level;
    }
}
