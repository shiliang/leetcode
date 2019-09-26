package leetcode.list;

import java.util.*;

public class ListAlgo {

    //链表反转

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backTrack(lists, new ArrayList<Integer>(), nums, 0);
        return lists;

    }

    private void backTrack(List<List<Integer>> lists, ArrayList<Integer> tempList, int[] nums, int start) {
        lists.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            tempList.add(nums[i]);
            backTrack(lists, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //最小的k个数
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = input.length;
        if (k > length || k == 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });
        for (int i = 0; i < length; i++) {
            if (maxHeap.size() != k) {

                maxHeap.offer(input[i]);
            } else if (maxHeap.peek() > input[i]) {
                Integer temp = maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }

        for (Integer val : maxHeap) {
            result.add(val);
        }

        return  result;
    }

    //栈压入弹出序列
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || popA == null) return false;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0, j = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> outList = new ArrayList<>();
        int tR = 0, tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--, outList);
        }
        return outList;
    }

    public void printEdge(int[][] matrix, int tR, int tC, int dR, int dC,ArrayList<Integer> outList) {
        if (tR == dR) {  //只有一行
            for (int i = tC; i <= dC; i++) {
                outList.add(matrix[tR][i]);
            }
        } else if (tC == dC) { //只有一列
            for (int i = tR; i <= dR; i++) {
                outList.add(matrix[i][tC]);
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                outList.add(matrix[tR][curC]);
                curC++;
            }

            while (curR != dR) {
                outList.add(matrix[curR][dC]);
                curR++;
            }

            while (curC != tC) {
                outList.add(matrix[dR][curC]);
                curC--;
            }

            while (curR != tR) {
                outList.add(matrix[curR][tC]);
                curR--;
            }
        }
    }


    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        int begin = 0;
        Deque<Integer> deque = new LinkedList<>();
        //找到第一个窗口的最大元素
        for (int i = 0; i < size; i++) {
            while (!deque.isEmpty() &&
                    num[i] >= num[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

        }

        for (int i = size; i < num.length; i++) {
            res.add(num[deque.peekFirst()]);
            //删除比当前元素小的队尾元素
            while (!deque.isEmpty()
                    && num[i] >= num[deque.peekLast()]) {
                deque.pollLast();
            }

            //判断队首的最大元素是不是还在窗口内
            if (!deque.isEmpty()
                    && deque.peekFirst() < (i - size + 1)) {
                deque.pollFirst();
            }
            deque.offerLast(i);

        }
        res.add(num[deque.peekFirst()]);
        return res;

    }

    //寻找丑数,维护3个队列p2, p3, p5
    public int GetUglyNumber_Solution(int index) {
        LinkedList<Integer> res = new LinkedList<>();
        res.add(0,1);
        if (index < 7) return index;
        int p2 = 0, p3 = 0, p5 = 0, i;
        for (i = 1; i < index; i++) {
            int minVal = getMin(res.get(p2) * 2, res.get(p3) * 3, res.get(p5) * 5);
            res.add(i, minVal);
            if (minVal == res.get(p2) * 2) p2++;
            if (minVal == res.get(p3) * 3) p3++;
            if (minVal == res.get(p5) * 5) p5++;
        }
        return res.get(index - 1);
    }

    public int getMin(int num1, int num2, int num3) {
        int min1 = Math.min(num1, num2);
        int min2 = Math.min(min1, num3);
        return min2;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= n ; i++) {
            int h = (i == n ? 0 : heights[i]);
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int curHeight = heights[stack.pop()];
                int prevIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = curHeight *(i - 1 - prevIndex);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode tmp = head;
        for (int i = 0; i < k; i++) {
            if (head != null) {
                stack.push(head);
            } else {
                return tmp;  //不满k个节点
            }
            head = head.next;
        }
        ListNode first = stack.pop();
        ListNode res = first;
        for (int i = 0; i < k - 1; i++) {
            first.next = stack.pop();
            first = first.next;
        }
        first.next = reverseKGroup(head, k);
        return res;
    }

    //链表从m到n翻转
    public ListNode reverseBetween(ListNode head, int m, int n) {
       if (head == null) return null;
       ListNode pre = null;
       ListNode cur = head;
       int i = 1;
       for (i = 1; i < m; i++) {
            pre = cur;
            cur = cur.next;
       }
       ListNode start = pre;
        pre = cur;
        cur = cur.next;
        i++;
       while (i < n) {
           pre = cur.next;
           pre = cur;
           cur = cur.next;
       }
       ListNode tail = pre.next;
       return tail;

    }

    //合并k个链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val == o2.val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }


}
