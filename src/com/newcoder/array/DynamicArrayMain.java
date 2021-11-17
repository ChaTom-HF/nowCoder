package com.newcoder.array;

import static com.newcoder.array.DynamicArrayMain.DynamicArray.capacity;

public class DynamicArrayMain {

    public static void main(String[] args) {
        DynamicArrayDao dynamicArrayDao = new DynamicArrayDao();
        //初始化动态数组
        DynamicArray myArray = dynamicArrayDao.Init_Array();
        System.out.println("初始化动态数组:");
        //获取容量
        System.out.println("数组容量:" + capacity);
        System.out.println("数组实际大小:" + dynamicArrayDao.Size_Array(myArray));
        //插入元素
        for (int i = 0; i < 10; i++) {
            dynamicArrayDao.PushBack_Array(myArray, i);
        }
        System.out.println();
        System.out.println("插入元素之后:");
        //获取容量
        System.out.println("数组容量:" + capacity);
        System.out.println("数组实际大小:" + dynamicArrayDao.Size_Array(myArray));
        System.out.println();
        //打印插入元素
        System.out.println("打印插入的元素:");
        dynamicArrayDao.Print_Array(myArray);
        System.out.println();
        //根据元素位置删除元素
        dynamicArrayDao.RemoveByPos_Array(myArray, 2);
        //根据元素值删除元素
        dynamicArrayDao.RemoveByValue_Array(myArray, 7);
        System.out.println();
        //打印删除后的数组
        System.out.println("打印删除后的元素:");
        dynamicArrayDao.Print_Array(myArray);
        System.out.println();
        //查找元素为5的位置
        System.out.println();
        System.out.print("元素5的位置为: ");
        int pos = dynamicArrayDao.Find_Array(myArray, 5);
        System.out.println(pos);
        //查找位置为7的元素值
        System.out.println();
        System.out.print("位置为7的元素为: ");
        int value = dynamicArrayDao.At_Array(myArray, 7);
        System.out.println(value);
        //获取容量
        System.out.println();
        System.out.println("此时的数组容量:" + capacity);
        System.out.println("此时的数组实际大小:" + dynamicArrayDao.Size_Array(myArray));
        System.out.println();
    }

    static class DynamicArray {
        //动态数组最大容量
        public final static int capacity = 100;
        //顺序表的结点数据
        public int[] data;
        //顺序表的长度，用来标识数组中的元素个数
        public int size;

        //构造函数
        public DynamicArray(int[] data, int size) {
            this.data = data;
            this.size = size;
        }
    }


    static class DynamicArrayDao {
        //初始化数组
        public DynamicArray Init_Array() {
            //数组数据域初始化
            int[] data1 = new int[capacity];
            //DynamicArray初始化
            DynamicArray myArray = new DynamicArray(data1, 0);
            //数组赋值
            for (int i = 0; i < capacity; i++) {
                myArray.data[i] = 0;
            }
            return myArray;
        }

        //插入指定值
        public void PushBack_Array(DynamicArray array, int value) {
            if (array == null) {
                return;
            }
            //如果线性表容量小于或等于数组容量
            if (array.size == capacity) {
                return;
            }
            //插入元素
            array.data[array.size] = value;
            array.size++;
        }

        //根据位置删除
        public void RemoveByPos_Array(DynamicArray array, int pos) {
            if (array == null) {
                return;
            }
            //判断位置是否有效
            if (pos < 0 || pos >= array.size) {
                return;
            }
            //删除元素
            for (int i = pos; i < array.size - 1; i++) {
                array.data[i] = array.data[i + 1];
            }
            array.size--;
        }

        //查找元素,返回该值第一次出现时对应的下标位置
        public int Find_Array(DynamicArray array, int value) {
            if (array == null) {
                return -1;
            }
            //找到该值第一次出现的位置，-1表示没有找到；
            int pos = -1;
            for (int i = 0; i < array.size; i++) {
                if (array.data[i] == value) {
                    pos = i;
                    break;
                }
            }
            return pos;
        }

        //根据位置查找到某个元素
        public int At_Array(DynamicArray array, int pos) {
            if (array == null) {
                return -1;
            }
            return array.data[pos];
        }

        //根据值删除
        public void RemoveByValue_Array(DynamicArray array, int value) {
            if (array == null) {
                return;
            }
            //首先找到该值对应的数组下标
            int pos = Find_Array(array, value);
            //调用根据位置删除的方法
            RemoveByPos_Array(array, pos);
        }

        //打印
        public void Print_Array(DynamicArray array) {
            if (array == null) {
                return;
            }
            for (int i = 0; i < array.size; i++) {
                System.out.print(array.data[i] + ",");
            }
        }

        //清空数组
        public void Clear_Array(DynamicArray array) {
            if (array == null) {
                return;
            }
            for (int i = 0; i < array.size; i++) {
                array.data[i] = 0;
            }
            array.size = 0;
        }

        //获得动态数组当前元素个数
        public int Size_Array(DynamicArray array) {
            if (array == null) {
                return -1;
            }
            return array.size;
        }

    }
}
