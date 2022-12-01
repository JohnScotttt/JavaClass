package src;

import java.util.HashMap;

public class AI implements Gobang {
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    static {

        /**
         * 权值
         */
        // 防守权值
        // 活1连
        map.put("010", 20); // map.put(key,value) key=键 value=值
        map.put("0-10", 10);
        // 眠1连
        map.put("-110", 1);
        map.put("1-10", 1);
        // 活2连
        map.put("0110", 200);
        map.put("0-1-10", 100);
        // 眠2连
        map.put("-1110", 20);
        map.put("1-1-10", 10);
        // 活3连
        map.put("01110", 7000);
        map.put("0-1-1-10", 5000);
        // 眠3连
        map.put("-11110", 50);
        map.put("1-1-1-10", 30);
        // 活4连
        map.put("011110", 10000);
        map.put("0-1-1-1-10", 10000);
        // 眠4连
        map.put("-111110", 10000);
        map.put("1-1-1-1-10", 10000);
        // 碰壁眠4连
        map.put("11110", 10000);
        map.put("-1-1-1-10", 10000);

        // 进攻权值
        // 活1连
        map.put("010", 10);
        map.put("0-10", 20);
        // 眠1连
        map.put("-110", 1);
        map.put("1-10", 1);
        // 活2连
        map.put("0110", 100);
        map.put("0-1-10", 200);
        // 眠2连
        map.put("-1110", 10);
        map.put("1-1-10", 20);
        // 活3连
        map.put("01110", 5000);
        map.put("0-1-1-10", 7000);
        // 眠3连
        map.put("-11110", 30);
        map.put("1-1-1-10", 50);
        // 活4连
        map.put("011110", 10000);
        map.put("0-1-1-1-10", 10000);
        // 眠4连
        map.put("-111110", 10000);
        map.put("1-1-1-1-10", 10000);
        // 碰壁眠4连
        map.put("11110", 10000);
        map.put("-1-1-1-10", 15000);
    }

    static String code;
    static Integer weight;

    public static void Quan() {
        for (int r = 0; r < array1.length; r++) {
            for (int c = 0; c < array1[r].length; c++) {
                if (array1[r][c] == 0) {// 如果该位置没有棋子则开始统计
                    code = countHL(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }
                    // 把另外七个方向统计完毕后，就完成权值统计
                    code = countHR(r, c);// 调用水平向右统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHT(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHB(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHZXS(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHYXS(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHZXX(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }

                    code = countHYXX(r, c);// 调用水平向左统计的方法
                    weight = map.get(code);// 根据棋子相连情况获取对应的权值
                    if (weight != null) {// 判断是否有该种棋子相连的情况
                        weightArray[r][c] += weight;// 累加权值
                    }
                    // 针对每种情况都进行权值分析，保证五子棋规则的完善
                    // 判断两个2连在一条直线但中间有一个空位的情况
                    if ((countHL(r, c) + countHR(r, c) == "01100110")
                            || (countHL(r, c) + countHR(r, c) == "0-1-100-1-10")
                            || (countHL(r, c) + countHR(r, c) == "-11100110")
                            || (countHL(r, c) + countHR(r, c) == "1-1-100-1-10")
                            || (countHL(r, c) + countHR(r, c) == "0110-1110")
                            || (countHL(r, c) + countHR(r, c) == "0-1-101-1-10")
                            || (countHL(r, c) + countHR(r, c) == "-1110-1110")
                            || (countHL(r, c) + countHR(r, c) == "1-1-101-1-10")) {
                        weightArray[r][c] = weightArray[r][c] + 5000;
                    }

                    if ((countHT(r, c) + countHB(r, c) == "01100110")
                            || (countHT(r, c) + countHB(r, c) == "0-1-100-1-10")
                            || (countHT(r, c) + countHB(r, c) == "-11100110")
                            || (countHT(r, c) + countHB(r, c) == "1-1-100-1-10")
                            || (countHT(r, c) + countHB(r, c) == "0110-1110")
                            || (countHT(r, c) + countHB(r, c) == "0-1-101-1-10")
                            || (countHT(r, c) + countHB(r, c) == "-1110-1110")
                            || (countHT(r, c) + countHB(r, c) == "1-1-101-1-10")) {
                        weightArray[r][c] = weightArray[r][c] + 5000;
                    }

                    if ((countHZXS(r, c) + countHYXX(r, c) == "01100110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-1-100-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "-11100110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "1-1-100-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0110-1110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-1-101-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "-1110-1110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "1-1-101-1-10")) {
                        weightArray[r][c] = weightArray[r][c] + 5000;
                    }

                    if ((countHYXS(r, c) + countHZXX(r, c) == "01100110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-1-100-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "-11100110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "1-1-100-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0110-1110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-1-101-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "-1110-1110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "1-1-101-1-10")) {
                        weightArray[r][c] = weightArray[r][c] + 5000;
                    }

                    // 判断一个活2连和一个活1连在一条直线上但中间有一个空位的情况
                    if ((countHL(r, c) + countHR(r, c) == "0100110") || (countHL(r, c) + countHR(r, c) == "0-100-1-10")
                            || (countHL(r, c) + countHR(r, c) == "0110010")
                            || (countHL(r, c) + countHR(r, c) == "0-1-100-10")) {
                        weightArray[r][c] = weightArray[r][c] + 3000;
                    }

                    if ((countHT(r, c) + countHB(r, c) == "0100110") || (countHT(r, c) + countHB(r, c) == "0-100-1-10")
                            || (countHT(r, c) + countHB(r, c) == "0110010")
                            || (countHT(r, c) + countHB(r, c) == "0-1-100-10")) {
                        weightArray[r][c] = weightArray[r][c] + 3000;
                    }

                    if ((countHZXS(r, c) + countHYXX(r, c) == "0100110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-100-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0110010")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-1-100-10")) {
                        weightArray[r][c] = weightArray[r][c] + 3000;
                    }

                    if ((countHYXS(r, c) + countHZXX(r, c) == "0100110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-100-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0110010")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-1-100-10")) {
                        weightArray[r][c] = weightArray[r][c] + 3000;
                    }

                    // 眠3连的一端被堵了
                    // "1-1-1-10"&"010" "0-1-1-10"&"010"
                    if ((countHL(r, c) + countHR(r, c) == "1-1-1-10010")
                            || (countHL(r, c) + countHR(r, c) == "0101-1-1-10")
                            || (countHL(r, c) + countHR(r, c) == "1-1-1-100")
                            || (countHL(r, c) + countHR(r, c) == "01-1-1-10")
                            || (countHL(r, c) + countHR(r, c) == "1-1-1-100110")
                            || (countHL(r, c) + countHR(r, c) == "01101-1-1-10")) {
                        weightArray[r][c] = 1;
                    }

                    if ((countHT(r, c) + countHB(r, c) == "1-1-1-10010")
                            || (countHT(r, c) + countHB(r, c) == "0101-1-1-10")
                            || (countHT(r, c) + countHB(r, c) == "1-1-1-100")
                            || (countHT(r, c) + countHB(r, c) == "01-1-1-10")
                            || (countHT(r, c) + countHB(r, c) == "1-1-1-100110")
                            || (countHT(r, c) + countHB(r, c) == "01101-1-1-10")) {
                        weightArray[r][c] = 1;
                        ;
                    }

                    if ((countHZXS(r, c) + countHYXX(r, c) == "1-1-1-10010")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0101-1-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "1-1-1-100")
                            || (countHZXS(r, c) + countHYXX(r, c) == "01-1-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "1-1-1-100110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "01101-1-1-10")) {
                        weightArray[r][c] = 1;
                    }

                    if ((countHYXS(r, c) + countHZXX(r, c) == "1-1-1-10010")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0101-1-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "1-1-1-100")
                            || (countHYXS(r, c) + countHZXX(r, c) == "01-1-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "1-1-1-100110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "01101-1-1-10")) {
                        weightArray[r][c] = 1;
                    }

                    // 3连和1连在一条线上差一个棋位
                    if ((countHL(r, c) + countHR(r, c) == "0-1-1-100-10")
                            || (countHL(r, c) + countHR(r, c) == "0-101-1-1-10")
                            || (countHL(r, c) + countHR(r, c) == "01110010")
                            || (countHL(r, c) + countHR(r, c) == "010-11110")
                            || (countHL(r, c) + countHR(r, c) == "0-100-1-1-10")
                            || (countHL(r, c) + countHR(r, c) == "1-1-1-100-10")
                            || (countHL(r, c) + countHR(r, c) == "01001110")
                            || (countHL(r, c) + countHR(r, c) == "-11110010")) {
                        weightArray[r][c] = weightArray[r][c] + 4000;
                    }

                    if ((countHT(r, c) + countHB(r, c) == "0-1-1-100-10")
                            || (countHT(r, c) + countHB(r, c) == "0-101-1-1-10")
                            || (countHT(r, c) + countHB(r, c) == "01110010")
                            || (countHT(r, c) + countHB(r, c) == "010-11110")
                            || (countHT(r, c) + countHB(r, c) == "0-100-1-1-10")
                            || (countHT(r, c) + countHB(r, c) == "1-1-1-100-10")
                            || (countHT(r, c) + countHB(r, c) == "01001110")
                            || (countHT(r, c) + countHB(r, c) == "-11110010")) {
                        weightArray[r][c] = weightArray[r][c] + 4000;
                    }

                    if ((countHZXS(r, c) + countHYXX(r, c) == "0-1-1-100-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-101-1-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "01110010")
                            || (countHZXS(r, c) + countHYXX(r, c) == "010-11110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "0-100-1-1-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "1-1-1-100-10")
                            || (countHZXS(r, c) + countHYXX(r, c) == "01001110")
                            || (countHZXS(r, c) + countHYXX(r, c) == "-11110010")) {
                        weightArray[r][c] = weightArray[r][c] + 4000;
                    }

                    if ((countHYXS(r, c) + countHZXX(r, c) == "0-1-1-100-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-101-1-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "01110010")
                            || (countHYXS(r, c) + countHZXX(r, c) == "010-11110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "0-100-1-1-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "1-1-1-100-10")
                            || (countHYXS(r, c) + countHZXX(r, c) == "01001110")
                            || (countHYXS(r, c) + countHZXX(r, c) == "-11110010")) {
                        weightArray[r][c] = weightArray[r][c] + 4000;
                    }
                }
            }
        }
    }

    // 水平向左统计的方法
    public static String countHL(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r - 1; r1 >= 0; r1--) {
            if (array1[r1][c] == 0) {// 表示空位沒有棋子
                if (r1 + 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c];// 记录棋子
                    code = array1[r1][c] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c]) {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 水平向右统计的方法
    public static String countHR(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r + 1; r1 < coloum; r1++) {
            if (array1[r1][c] == 0) {// 表示空位沒有棋子
                if (r1 - 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c];// 记录棋子
                    code = array1[r1][c] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c]) {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 竖直向上统计的方法
    public static String countHT(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int c1 = c - 1; c1 >= 0; c1--) {
            if (array1[r][c1] == 0) {// 表示空位沒有棋子
                if (c1 + 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r][c1];// 记录棋子
                    code = array1[r][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r][c1]) {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 竖直向下统计的方法
    public static String countHB(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int c1 = c + 1; c1 < row; c1++) {
            if (array1[r][c1] == 0) {// 表示空位沒没有棋子
                if (c1 - 1 == c) {// 相邻
                    break;
                } else {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r][c1];// 记录棋子
                    code = array1[r][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r][c1]) {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 左斜向上统计的方法
    public static String countHZXS(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r - 1, c1 = c - 1; r1 >= 0 && c1 >= 0; r1--, c1--) {
            if (array1[r1][c1] == 0) {// 表示空位沒有棋子
                if (c1 + 1 == c && r1 + 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c1];// 记录棋子
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c1]) {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 右斜向上统计的方法
    public static String countHYXS(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r + 1, c1 = c - 1; c1 >= 0 && r1 < coloum; r1++, c1--) {
            if (array1[r1][c1] == 0) {// 表示空位沒有棋子
                if (r1 - 1 == r && c1 + 1 == c) {// 相邻
                    break;
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c1];// 记录棋子
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c1]) {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 左斜向下统计的方法
    public static String countHZXX(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r - 1, c1 = c + 1; c1 < row && r1 >= 0; c1++, r1--) {
            if (array1[r1][c1] == 0) {// 表示空位沒有棋子
                if (c1 - 1 == c && r1 + 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c1];// 记录棋子
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c1]) {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }

    // 右斜向下统计的方法
    public static String countHYXX(int r, int c) {
        String code = "0";
        int chess = 0;// 存储第一颗出现的棋子
        // 循环遍历
        for (int r1 = r + 1, c1 = c + 1; r1 < coloum && c1 < row; r1++, c1++) {
            if (array1[r1][c1] == 0) {// 表示空位沒有棋子
                if (c1 - 1 == c && r1 - 1 == r) {// 相邻
                    break;
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            } else {// 表示该位置有棋子
                if (chess == 0) {// 第一次出现棋子
                    chess = array1[r1][c1];// 记录棋子
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else if (chess == array1[r1][c1]) {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                } else {
                    code = array1[r1][c1] + code;// 记录棋子相连情况
                    break;
                }
            }
        }
        return code;
    }
}