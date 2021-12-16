import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {

    List<Integer> list;
//    Stack <Integer> stack;
    public MinStack() {
        list = new ArrayList<>();
//        stack = new Stack<>();
    }

    public void push(int x) {
        list.add(x);
    }

    public void pop() {
        list.remove(list.size() -1);
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i : list) {
            if (i < min) min = i;
        }
        return min;
    }
}
