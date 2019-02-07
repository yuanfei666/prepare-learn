package top.forethought.practice;



import java.util.Stack;

/**
 * @author wangwei
 * @date 2019/1/27 20:42
 * @classDescription 迷宫寻找出口路径:
 * 1,使用栈记录正确路径,同时也是方便回退
 */
class mapNode {
    int x;
    int y;

    public mapNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "mapNode{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class MazeStack {
    //使用 10*10 的矩阵  12*12  最外层是墙
    // 1 表示墙
    // 0 表示可走
    static final int N_WIDTH = 12;
    static final int N_HEIGHT = 12;
    int[][] map = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
//    boolean[][] reachable = new boolean[N_WIDTH][N_HEIGHT];

//    //初始化reachable 数组,标记是否可以到达,辅助数组,能通过为true,已访问,不能通过为false,此代码展示未使用
//    public void init() {
//        for (int i = 0; i < N_WIDTH; i++) {
//            for (int j = 0; j < N_HEIGHT; j++) {
//                boolean reach = map[i][j] == 0;
//                reachable[i][j] = reach;
//            }
//        }
//    }
  /**
   * @author  wangwei
   * @date     2019/1/28 10:44
   * @methodDescription  非递归方式实现
   *
   *   从起点开始:顺时针方向扫描元素,如果可达,标记为已经扫描,压栈,以该位置继续顺时针方向扫
                                 如果不可达:更改扫描方向
                                如果所有方向都已经扫描,不可达, ,退栈

         保存当前节点到栈中,继续探测该节点周围节点
   */
    public boolean searchWay(Stack<mapNode> way,int currX, int currY, int outX, int outY) {


        way.push(new mapNode(currX, currY));
        map[currX][currY] = 2;
        while (!way.isEmpty()) {

            mapNode currNode = way.peek();//取栈顶元素作为当次扫描的中间位置
            if (outX == currNode.getX() && outY == currNode.getY()) {
                System.out.println("找到出路");
                return true;
            }
            // 下一个节点:如果是 0 ,则入栈
            currX = currNode.getX();
            currY = currNode.getY();
            int failed = 0;
            if (map[currX][currY + 1] == 0) {
                way.push(new mapNode(currX, currY + 1));
                map[currX][currY + 1] = 2;//标记为已经到过
            } else {
                failed++;
            }
            if (map[currX + 1][currY] == 0) {
                way.push(new mapNode(currX + 1, currY));
                map[currX + 1][currY] = 2;//标记为已经到过
            } else {
                failed++;
            }
            if (map[currX][currY - 1] == 0) {
                way.push(new mapNode(currX, currY + 1));
                map[currX][currY - 1] = 2;//标记为已经到过
            } else {
                failed++;
            }
            if (map[currX - 1][currY] == 0) {
                way.push(new mapNode(currX - 1, currY));
                map[currX - 1][currY] = 2;//标记为已经到过
            } else {
                failed++;
            }
            //四个方向全部失败,说明栈顶元素周围被堵死,或者都被访问过
            if (failed > 3) {
                way.pop();
            }

        }
        return false;

    }

    //使用递归的方式查找出路,
    public boolean findWay(Stack<mapNode> way,  int outX, int outY) {
        int curX = way.peek().getX();
        int curY = way.peek().getY();
        //成功找到出口
        if (curX == outX && curY == outY) {
            return true;
        }
        //四周加上了一堵墙,这行判断就可以去掉了
//        if (curX < 0 || curX > N_WIDTH - 1 || curY < 0 || curY > N_HEIGHT - 1) {
//            return false;
//        }
        //栈中元素一定是可以到达的,接下来是检测其周围元素
        //右,下,左,上
        if (map[curX][curY + 1] == 0) {
            map[curX][curY + 1]=2;//标记为已访问
          way.push(new mapNode(curX,curY+1));
          return  findWay(way,outX,outY);
        }
        if (map[curX+1][curY ] == 0) {
            map[curX+1][curY]=2;
            way.push(new mapNode(curX+1,curY));
            return  findWay(way,outX,outY);
        }
        if (map[curX][curY -1] == 0) {
            map[curX][curY-1]=2;
            way.push(new mapNode(curX,curY-1));
            return  findWay(way,outX,outY);
        }
        if (map[curX-1][curY ] == 0) {
            way.push(new mapNode(curX-1,curY));
            return  findWay(way,outX,outY);
        }
        //四个方向都不可通过,弹出栈顶元素,
         way.pop();
        return findWay(way,outX,outY);
    }



    public static void main(String[] args) {
        Stack<mapNode> throughWay = new Stack<>();
        MazeStack mazeStack = new MazeStack();
//        mazeStack.searchWay(throughWay,1,1,10,10);
         throughWay.push(new mapNode(1,1));
        mazeStack.findWay(throughWay,  10, 10);
        System.out.println("找到的通路:");
        while (!throughWay.empty()) {
            System.out.println(throughWay.pop());
        }

    }

}
