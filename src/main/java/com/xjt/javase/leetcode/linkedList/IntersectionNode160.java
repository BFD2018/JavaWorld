package com.xjt.javase.leetcode.linkedList;

public class IntersectionNode160 {
    public static void main(String[] args) {
        //intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4]
        ListNode node10 = new ListNode(1);
        ListNode node11 = new ListNode(9);
        ListNode node12 = new ListNode(1);
        ListNode node13 = new ListNode(2);
        ListNode node14 = new ListNode(4);
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        ListNode headA = node10;

        ListNode node20 = new ListNode(3);
        ListNode node21 = new ListNode(2);
        ListNode node22 = new ListNode(4);
        node20.next = node21;
        node21.next = node22;
        ListNode headB = node20;

        ListNode node = getIntersectionNode(headA,headB);
        System.out.println(node.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }

        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb){
            pa = pa==null ? pb:pa.next;
            pb = pb==null ? pa:pb.next;
        }

        return pa;

    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}