package com.example.alg_lib.link;

public class LinkTest {

   public Node head = null;
   //环的入口节点
   private Node cycleNode = null;

   public Node l1 = null;
   public Node l2 = null;
    public Node l3 = null;

    /**
     * 像链表中加入节点,添加节点是在最尾部加
     * */
    public void addNode(int data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            return;
        }
        Node tmp = head;
        //查找最后一个节点
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = newNode;
//        //设置第二个为环的入口
//        if (data == 2){
//            cycleNode = newNode;
//        }
//
//        if (data == 6){
//            newNode.next = cycleNode;
//        }
    }


    //打印链表
    public int length(Node head){
        int length = 0;
        Node tmp = head;
        System.out.print("打印链表：");
        while (tmp != null){
            System.out.print(tmp.data + "->");
            tmp = tmp.next;
            length ++;
        }
        System.out.print("\n");
        return length;
    }


    /**
     * 删除index处的节点
     * */
    public boolean deleteNode(Node header, int index){
        if (index < 1 || index > length(head)){
            return false;
        }
        if (index == 1){
            this.head = header.next;
            return true;
        }
        Node preNode = head;
        Node curNode = preNode.next;
        int i = 1;
        while (curNode != null){
            if (i == index){
                preNode.next = curNode.next;
                length(head);
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    //反转链表
    public Node reverseLink(Node head){
        if(head == null)return null;
        Node pre = head;
        Node cur = head.next;
        Node tmp = null;
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = tmp;
        }
        head.next = null;
        length(pre);
        return pre;
    }


    //查找链表倒数第k个节点
    public Node findLastKFromend(Node head, int k){
        if (head == null || k == 0 || k > length(head))return null;
        Node header = head;

        Node behind = header; //后出发的指针
        Node pre = header; //先出发的指针

        //将先行节点移动k-1步
        for (int i = 0; i < k - 1; i ++){
            pre = pre.next;
        }

        //查找到倒数第k个节点
        while (pre != null && pre.next != null){
            pre = pre.next;
            behind = behind.next;
        }
        return behind;
    }

    //删除链表倒数第k个节点
    public void removeLastKFromend(Node head, int k){
        if (head == null || k == 0)return;
        Node dummy = new Node(0);
        dummy.next = head;
       //1.查找倒数第k+1个节点
        Node cur = findLastKFromend(dummy, k + 1);
        cur.next = cur.next.next;
        length(dummy.next);
    }

    //判断是否有环,返回相遇节点
    public Node hasCycle(Node head){
        if (head == null || head.next == null)return null;
        Node fastN = head;
        Node slowN = head;
        while (fastN != null && slowN != null){
            if (fastN.next == null){
                return null;
            }
            fastN = fastN.next.next;
            slowN = slowN.next;
            if (fastN == slowN){
                System.out.println("has cycle");
                return fastN;
            }
        }
        return null;
    }


    // 查找链表环的入口
    public void findCycleNodeEntry(Node head){
        Node firstMetting = hasCycle(head);
        if (firstMetting == null)return; //没有环，
        //找环中节点的个数
        Node p = firstMetting;
        int count = 0;
        p = p.next;
        count++;
        while (p != firstMetting ){
            p = p.next;
            count++;
        }

        //从表头开始，快指针先走count步
        Node fast = head;
        Node slow = head;

        for (int i = 0; i < count; i++){
            fast = fast.next;
        }

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        System.out.println(fast.data);

    }


    //查找中间节点，
    public Node findMiddleNode(Node head){
        if (head == null)return null;
        Node fast = head; // 快指针
        Node slow = head; //慢指针
        while (fast != null){
            fast = fast.next; //快指针每次前进2步，慢指针进1步
            if (fast != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow.next;
//        System.out.println(slow.data);
    }


    public Node mergeTwoLists(Node l1, Node l2){
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            while (l1 == null || l2 == null){
                l1 = l1.next;
                l2 = l2.next;
                if (l1.data >= l2.data){
                    addNode1(l2);
                }else {
                    addNode1(l1);
                }

            }
            while(l2 != null){
                l2 = l2.next;
                addNode1(l2);
            }

            while(l1 != null){
                l1 = l1.next;
                addNode1(l1);
            }
            return l3;
    }

    public void addNode1(Node data){
        if (l3 == null){
            l3 = data;
            return;
        }
        Node tmp = l3;
        //查找最后一个节点
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = data;
    }

    public Node deleteDuplicates(Node head){
        if (head == null)return null;
        Node hunmp = head;
        Node p1 = head;
        Node p2 = p1.next;

        while (p2 != null){
            if (p1.data == p2.data){
                if (p2.next == null){
                    p1.next = null;
                }
                p2 = p2.next;
            }else {
                p1.next = p2;

                p1 = p1.next;
                p2 = p2.next;
            }
        }

        return hunmp;
    }
}