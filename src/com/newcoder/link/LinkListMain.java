package com.newcoder.link;

public class LinkListMain {

    public static void main(String[] args) {
        LinkListDao linkListDao = new LinkListDao();
        //创建链表
        LinkList list = linkListDao.Init_LinkList();
        //数据插入链表
        linkListDao.Insert_LinkList(list, 0, "A");
        linkListDao.Insert_LinkList(list, 1, "B");
        linkListDao.Insert_LinkList(list, 2, "C");
        linkListDao.Insert_LinkList(list, 3, "D");
        linkListDao.Insert_LinkList(list, 4, "D");
        //打印链表
        System.out.println("插入数据之后的链表为:");
        linkListDao.Print_LinkList(list);
        System.out.println();
        //删除指定位置的值
        linkListDao.RemoveByPos_LinkList(list, 2);
        //打印链表
        System.out.println("删除元素C之后的链表为:");
        linkListDao.Print_LinkList(list);
        System.out.println();
        //获得链表长度
        System.out.println("链表长度为:");
        System.out.println(linkListDao.Size_LinkList(list));
        //查找值为3的位置
        System.out.println("值为D的位置为:");
        linkListDao.Find_LinkList(list, "D");
        System.out.println();
        //返回第一个结点元素的值
        System.out.println("第一个结点元素为:");
        System.out.println(linkListDao.Front_LinkList(list));
    }

    static class LinkNode {
        //链表结点的数据域
        public Object data;
        //链表结点的指针域
        public LinkNode next;

        public LinkNode() {
            super();
        }

        //构造方法
        public LinkNode(Object data, LinkNode next) {
            super();
            this.data = data;
            this.next = next;
        }
    }

    static class LinkList {
        //链表的头结点
        public LinkNode head;
        //链表的元素个数
        public int size;

        public LinkList() {
            super();
        }

        //构造方法
        public LinkList(LinkNode head, int size) {
            super();
            this.head = head;
            this.size = size;
        }
    }

    static class LinkListDao {
        //初始化链表
        public LinkList Init_LinkList() {
            //设置头结点的指针域和数据域
            LinkNode node = new LinkNode(0, null);
            return new LinkList(node, 0);
        }

        //指定位置插入
        public void Insert_LinkList(LinkList list, int pos, Object data) {
            //判断list是否有效
            if (list == null) {
                return;
            }
            //判断data是否有效
            if (data == null) {
                return;
            }
            //判断位置pos是否有效
            if (pos < 0 || pos > list.size) {
                //在链表的尾部插入
                pos = list.size;
            }
            //第一步，创建新的结点，也就是待插入的结点
            LinkNode newNode = new LinkNode(data, null);
            //第二步，找到待插入结点前面一个结点pCurrent，并使其等于list的头结点
            LinkNode pCurrent = list.head;
            for (int i = 0; i < pos; i++) {
                pCurrent = pCurrent.next;
            }
            //第三步，新结点入链表，进行插入操作
            newNode.next = pCurrent.next;
            pCurrent.next = newNode;
            //第四步，链表的size要加1
            list.size++;
        }

        //删除指定位置的值
        public void RemoveByPos_LinkList(LinkList list, int pos) {
            if (list == null) {
                return;
            }
            if (pos < 0 || pos >= list.size) {
                return;
            }
            //第一步，找到待删除结点的前面一个结点pCurrent
            LinkNode pCurrent = list.head;
            for (int i = 0; i < pos; i++) {
                pCurrent = pCurrent.next;
            }
            //第二步，进行删除操作
            pCurrent.next = pCurrent.next.next;
            //第三步，链表的size要减1
            list.size--;
        }

        //获得链表的长度
        public int Size_LinkList(LinkList list) {
            return list.size;
        }

        //查找指定元素的位置
        public void Find_LinkList(LinkList list, Object data) {
            //注意这里要从头结点的下一个结点开始，因为头结点不存放数据信息
            LinkNode pCurrent = list.head.next;
            for (int i = 0; i < list.size; i++) {
                if (pCurrent.data == data) {
                    System.out.print(i + ",");
                }
                pCurrent = pCurrent.next;
            }
        }

        //返回第一个结点元素的值
        public Object Front_LinkList(LinkList list) {
            return list.head.next.data;
        }

        //打印链表结点
        public void Print_LinkList(LinkList list) {
            if (list == null) {
                return;
            }
            LinkNode pCurrent = list.head.next;
            for (int i = 0; i < list.size; i++) {
                System.out.print(pCurrent.data + ",");
                pCurrent = pCurrent.next;
            }
        }
    }
}
