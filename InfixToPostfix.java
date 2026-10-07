import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {

    static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        if (ch == '*' || ch == '/')
            return 2;
        if (ch == '^')
            return 3;

        return 0;
    }

    static String convertToPostfix(String infix) {

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {

                while (!stack.isEmpty()
                        && stack.peek() != '('
                        && precedence(stack.peek()) >= precedence(ch)) {

                    postfix.append(stack.pop());
                }

                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();

        String postfix = convertToPostfix(infix);

        System.out.println("Postfix expression: " + postfix);

        sc.close();
    }
}