/*******************************************************************************
 * Shashank Sinha
 * Project5.java
 * Creating a binary expression tree that is evaluated through inorder traversal
 *******************************************************************************/

package cmsc256;

import bridges.base.BinTreeElement;
import bridges.connect.Bridges;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Project5 {
    public static bridges.base.BinTreeElement<String> buildParseTree (String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException();
        }

        List<String> tokens = Arrays.asList(expression.split("\\s+"));
        Stack<BinTreeElement<String>> treeStack = new Stack<>();
        BinTreeElement<String> curr = new BinTreeElement<>();

        for (String token : tokens) {
            if (token.equals("(")) {
                BinTreeElement<String> newNode = new BinTreeElement<>();
                treeStack.push(curr);
                curr.setLeft(newNode);
                curr = curr.getLeft();
            } else if (isOperator(token)) {
                BinTreeElement<String> newNode = new BinTreeElement<>();
                curr.setLabel(token);
                curr.setValue(token);
                curr.setRight(newNode);
                treeStack.push(curr);
                curr = curr.getRight();
            } else if (isOperand(token)) {
                curr.setLabel(token);
                curr.setValue(token);
                curr = treeStack.pop();
            } else if (token.equals(")")) {
                if (!treeStack.isEmpty()) {
                    curr = treeStack.pop();
                }
            } else {
                throw new IllegalArgumentException("A character in the expression is not valid.");
            }
        }

        return curr;
    }

    public static double evaluate(bridges.base.BinTreeElement<String> tree) {
        if (tree == null) {
            return Double.NaN;
        } else if (tree.getRight() == null && tree.getLeft() == null){
            return Double.parseDouble(tree.getLabel());
        }

        double left = evaluate(tree.getLeft());
        double right = evaluate(tree.getRight());

        if(tree.getLabel().equals("+")){
            return left + right;
        } else if(tree.getLabel().equals("-")){
            return left - right;
        } else if(tree.getLabel().equals("*")){
            return left * right;
        } else if(tree.getLabel().equals("%")){
            return left % right;
        } else if(tree.getLabel().equals("/")){
            if(right == 0){
                throw new ArithmeticException("Cannot to divide by 0");
            }
            return left / right;
        }
        return 0.0;
    }

    public static String getEquation(bridges.base.BinTreeElement<String> tree) {
        String eq = "";
        if (tree == null){
            return "";
        }
        if(tree.getLeft() == null && tree.getRight() == null){
            return tree.getLabel();
        }
        eq = eq + "( " + getEquation(tree.getLeft()) + " " + tree.getLabel() + " " + getEquation(tree.getRight()) + " )";
        return eq;
    }

    public static boolean isOperator(String string) {
        return (string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") || string.equals("%"));
    }

    public static boolean isOperand(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args){
        String ex1 = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree = buildParseTree(ex1);
        double answer = evaluate(parseTree);
        System.out.println(answer);
        System.out.println(getEquation(parseTree));

        Bridges bridges = new Bridges(5, "shashanksinha", "920515443262");

    }
}





