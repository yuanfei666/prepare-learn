package top.forethought.linklist;



    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
        public String toString(){
            Node current=this;
            StringBuffer sb=new StringBuffer();
            while(current!=null){
                sb.append(current.val+" ");
                current=current.next;
            }
            return sb.toString();
        }
    }
    public class Main {

        public static void main(String[] args) {
            String num1 = "2";
            String num2 = "99";
            Main obj = new Main();
           // Node n1 = obj.convert(num1.toCharArray());
           // Node n2 = obj.convert(num2.toCharArray());
           // System.out.println("num1:" + n1);
           // System.out.println("num2:" + n2);
           // System.out.println(obj.multi(n1, n2));
            System.out.println(obj.multi(new StringBuffer(num1).reverse(),new StringBuffer(num2).reverse()));
        }
        // 字符串反转版乘法
        private String multi(StringBuffer reverse1, StringBuffer reverse2) {
             int []result=new int[reverse1.length()+reverse2.length()];
             int zeroCount=0;

            int res=0;
            for(int i=0;i< reverse1.length();i++){

              int multi=0;
              int  resultStart=zeroCount;// 此次循环 乘积结果最低位应放在result 中的位置
                res=0;// 进位
                for(int j=0;j<reverse2.length();j++){
                     multi=res+ (reverse1.charAt(i)-'0')*(reverse2.charAt(j)-'0');
                     res=multi/10;
                     multi%=10;
                     result[resultStart++]+=multi;
                     if(resultStart>=1){
                         result[resultStart]+=result[resultStart-1]/10;
                         result[resultStart-1]%=10;
                     }

                }
                zeroCount++;
            }
            StringBuffer resultStr=new  StringBuffer();
            for(int i=0;i<result.length-1;i++){
                resultStr.append(result[i]);
            }
            //表示最高位有进位
            if(res!=0){
                resultStr.append(res);
            }


         return  resultStr.reverse().toString();
        }

        // 将字符串转为  个位,百位,依次排列的链表
        public Node convert(char[] array) {
            Node head = new Node(0);
            Node preNode = head;
            Node newNode = head;
            for (int i = array.length - 1; i >= 0; i--) {
                preNode = newNode;
                newNode = new Node(array[i] - '0');
                preNode.next = newNode;
            }
            return head.next;
        }

        // 将两个 链表相加后得到的链表返回
        public Node addList(Node node1, Node node2) {
            Node curr1 = node1;
            Node curr2 = node2;
            Node resultHead = new Node(0);
            Node newNode = resultHead;
            Node preNode = null;
            int res = 0;// 进位

            while (curr1 != null && curr2 != null) {
                preNode = newNode;
                int sum = 0;
                sum = curr1.val + curr2.val + res;
                res += sum / 10;
                sum = sum % 10;
                newNode=new Node(sum);
                preNode.next = newNode;
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            //curr1 比较长
            while (curr1 != null) {
                preNode = newNode;
                int sum = res + curr1.val;
                res = sum / 10;
                sum = sum % 10;
                newNode = new Node(sum);
                preNode.next = newNode;
                curr1 = curr1.next;
            }
            //curr2 比较长
            while (curr2 != null) {
                preNode = newNode;
                int sum = res + curr2.val;
                res = sum / 10;
                sum = sum % 10;
                newNode = new Node(sum);
                preNode.next = newNode;
                curr2 = curr2.next;
            }
            // 考虑最终进位导致最高位进位
            if (res != 0) {
                preNode = newNode;
                newNode = new Node(res);
                preNode.next = newNode;
            }
            return resultHead.next;
        }

        // 将乘法转为多个链表相加,返回一个链表
        public Node multi(Node node1, Node node2) {
            Node tempNode = new Node(0);
            Node resultNode = new Node(0);
            Node n1 = node1;
            Node n2 = node2;
            int zeroCount = 0;// 标记结果有多少个0(用以生成最低位的0的节点个数)
            // 双层循环,从一个数的个位开始
            while (n1 != null) {
                n2 = node2;
                Node preNode = tempNode;
                Node newNode = tempNode;
                int zeroCount2 = zeroCount;
                //补充0
                while (zeroCount2-- > 0) {
                    preNode = newNode;
                    newNode = new Node(0);
                    preNode.next = newNode;
                }
                int res = 0;// 记录进位
                while (n2 != null) {
                    preNode = newNode;
                    int mul = n1.val * n2.val + res;
                    res = mul / 10;
                    mul = mul % 10;
                    newNode = new Node(mul);
                    preNode.next = newNode;
                    n2 = n2.next;
                }
                if (res != 0) {
                    newNode.next = new Node(res);
                }
                // 计算当前结果
                resultNode = addList(resultNode, tempNode.next);
                zeroCount++;
                n1 = n1.next;
            }



            return resultNode;
        }
    }
