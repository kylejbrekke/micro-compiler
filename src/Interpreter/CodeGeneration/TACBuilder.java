package Interpreter.CodeGeneration;

import Interpreter.AbstractParseTree.AbstractParseTree;
import Interpreter.AbstractParseTree.Node;
import SymbolTable.SymbolTable;
import SymbolTable.Symbol;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class TACBuilder {

    private static final Pattern INTEGER = Pattern.compile("[0-9]+");
    private static final Pattern FLOAT = Pattern.compile("[0-9]*\\.[0-9]+");

    private static final List<String> OPERATOR_LIST =
            new ArrayList<>(Arrays.asList("+", "-", "*", "/", "<", "<=", ">", ">=", "=", "!="));
    private HashMap<String, ArrayList<String>> functionParameters = new HashMap<>();
    private HashMap<String, String> functionReturnTypes = new HashMap<>();
    private String currentType;
    private SymbolTable symbolTable;
    private ArrayList<Node> visited = new ArrayList<>();
    private String code = "";
    private String returnType;
    private Stack<String> fids = new Stack<>();
    private int functionIDCount = 0;
    private int ifCount = 0;
    private int whileCount = 0;
    private int tmpCount = 0;
    private Node currentNode;
    private ArrayList<Node> children;

    public TACBuilder(AbstractParseTree abstractParseTree) {
        this.currentNode = abstractParseTree.getRoot();
        this.children = currentNode.getChildren();
        this.symbolTable = new SymbolTable("ALL");
        build();
    }

    private void build() {
        visited.add(currentNode);
        if (!currentNode.getValue().equals("PROGRAM")) {
            System.out.println("Invalid Start State");
            return;
        }

        code += "PROGRAM ";
        step();
        code += currentNode.getValue() + "\n";
        while (!currentNode.getValue().equals("PROGRAM")) {
            step();
            buildOperation();
            buildFunction();
            buildReturn();
            buildRead();
            buildWrite();
            buildVariable();
            buildAssignment();
            buildWhile();
            buildIf();
            buildElse();
            buildString();
            buildFunctionCall();
        }

        //System.out.println(code + "\n\n\n");
        //for (Symbol symbol : symbolTable.getEntries()) {
        //    System.out.println(symbol.id + "\t" + symbol.typeId);
        //}

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("interim.3ac"));
            writer.write(code);
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader("interim.3ac"));
            String line = reader.readLine();
            code = "";
            ArrayList<String> existingDefinitions = new ArrayList<>();
            while (line != null) {
                String[] components = line.split(" ");
                if (components[0].equals("DEF")) {
                    if (existingDefinitions.contains(components[1])) {
                        while (line != null && !line.equals("~")) {
                            line = reader.readLine();
                        }
                    } else {
                        existingDefinitions.add(components[1]);
                        code += line + "\n";
                    }
                } else if (components[0].equals("+")) {
                    if (getType(components[1]).equals("INT")) {
                        code += "ADDI " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    } else {
                        code += "ADDF " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    }
                } else if (components[0].equals("-")) {
                    if (getType(components[1]).equals("INT")) {
                        code += "SUBI " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    } else {
                        code += "SUBF " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    }
                } else if (components[0].equals("*")) {
                    if (getType(components[1]).equals("INT")) {
                        code += "MULTI " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    } else {
                        code += "MULTF " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    }
                } else if (components[0].equals("/")) {
                    if (getType(components[1]).equals("INT")) {
                        code += "DIVI " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    } else {
                        code += "DIVF " + components[1] + " " + components[2] + " " + components[3] + "\n";
                    }
                } else {
                    if (line.equals("~")) {
                        code += "\n";
                    } else {
                        code += line + "\n";
                    }
                }
                line = reader.readLine();
            }
            reader.close();

            writer = new BufferedWriter(new FileWriter("interim.3ac"));
            writer.write(code);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildString() {
        if (currentNode.getValue().equals("STRING DECLARATION") && !visited.contains(currentNode)) {
            String type = "STR", id, val;
            stepInto(children.get(0));
            id = currentNode.getValue();
            stepUp();
            stepInto(children.get(1));
            val = currentNode.getValue();
            stepUp();
            symbolTable.addEntry(new Symbol(id, "STRING", val));
            code += type + " " + id + " " + val + "\n";
        }
    }

    private String getType(String target) {
        if (target.matches(INTEGER.pattern())) {
            return "INT";
        } else if (target.matches(FLOAT.pattern())) {
            return "FLOAT";
        } else {
            return symbolTable.getEntry(target).getTypeId();
        }
    }

    private void buildFunctionCall() {
        if (currentNode.getValue().equals("FUNCTION CALL") && !visited.contains(currentNode)) {
            stepInto(children.get(0));
            String id = currentNode.getValue();
            ArrayList<String> parameters = functionParameters.get(id);
            stepUp();

            stepInto(children.get(1));
            for (int i = 0; i < parameters.size(); i++) {
                String toAdd = "LOAD " + getOperationTarget(children.get(i)) + " " + parameters.get(i) + "\n";
                code += toAdd;
            }

            code += "JMP " + id + "0\n";
            String ret;
            if (functionReturnTypes.get(id).equals("FLOAT")) {
                ret = "st(0)";
                String operationTarget = tmp();
                code += "LOAD " + ret + " " + operationTarget + "\n";
                tmpCount++;
                symbolTable.addEntry(new Symbol(operationTarget, functionReturnTypes.get(id)));
                stepUp();
                stepUp();
            } else if (functionReturnTypes.get(id).equals("INT")) {
                ret = "%eax";
                String operationTarget = tmp();
                code += "LOAD " + ret + " " + operationTarget + "\n";
                tmpCount++;
                symbolTable.addEntry(new Symbol(operationTarget, functionReturnTypes.get(id)));
                stepUp();
                stepUp();
            } else {
                stepUp();
                stepUp();
            }
        }
    }

    private void buildElse() {
        if (currentNode.getValue().equals("ELSE") && !visited.contains(currentNode)) {
            String y = fids.pop();
            String x = fids.pop();
            code += "JMP " + fids.peek() + "\n~\n";
            fids.add(x);
            code += "DEF " + fids.peek() + "\n";
            fids.add(y);
        }
    }

    private void buildIf() {
        if (currentNode.getValue().equals("IF") && !visited.contains(currentNode)) {
            visited.add(currentNode);
            String ifID = "if" + ifCount;
            String elseID = "else" + ifCount;

            stepInto(children.get(0));
            String conditionType = currentNode.getValue();
            switch (conditionType) {
                case "=":
                    conditionType = "JNE";
                    break;
                case "!=":
                    conditionType = "JE";
                    break;
                case ">":
                    conditionType = "JNG";
                    break;
                case ">=":
                    conditionType = "JNGE";
                    break;
                case "<":
                    conditionType = "JNL";
                    break;
                case "<=":
                    conditionType = "JNLE";
                    break;
                default:
                    break;
            }

            fids.add(fids.peek() + "@" + functionIDCount);
            stepUp();
            if (children.get(children.size() - 1).getValue().equals("ELSE")) {
                fids.add(elseID);
            }

            stepInto(children.get(0));
            code += conditionType + " " + getOperationTarget(children.get(0))
                    + " " + getOperationTarget(children.get(1)) + " " + fids.peek() + "\n";
            fids.add(ifID);
            ifCount++;
            functionIDCount++;
            stepUp();
        }
    }

    private void buildWhile() {
        if (currentNode.getValue().equals("WHILE") && !visited.contains(currentNode)) {
            visited.add(currentNode);
            String loopID = "while" + whileCount;
            whileCount++;
            code += "JMP " + loopID + "\n~\n";
            code += "DEF " + loopID + "\n";
            stepInto(children.get(0));

            String conditionType = currentNode.getValue();
            switch (conditionType) {
                case "=":
                    conditionType = "JNE";
                    break;
                case "!=":
                    conditionType = "JE";
                    break;
                case ">":
                    conditionType = "JNG";
                    break;
                case ">=":
                    conditionType = "JNGE";
                    break;
                case "<":
                    conditionType = "JNL";
                    break;
                case "<=":
                    conditionType = "JNLE";
                    break;
                default:
                    break;
            }

            fids.add(fids.peek() + "@" + functionIDCount);
            String condition = conditionType + " " + getOperationTarget(children.get(0))
                             + " " + getOperationTarget(children.get(1)) + " " + fids.peek() + "\n";
            code += condition;
            fids.add(loopID);
            whileCount++;
            functionIDCount++;
            stepUp();
        }
    }

    private void buildAssignment() {
        if (currentNode.getValue().equals("ASSIGN") && !visited.contains(currentNode)) {
            if (!OPERATOR_LIST.contains(children.get(1).getChildren().get(0).getValue())) {
                stepInto(children.get(0));
                String target = currentNode.getValue();
                stepUp();
                stepInto(children.get(1));
                stepInto(children.get(0));
                String value = getOperationTarget(currentNode);

                String typeInitial = "";
                String type = getType(value);
                switch (type) {
                    case "INT":
                        typeInitial = "I";
                        break;
                    case "FLOAT":
                        typeInitial = "F";
                        break;
                }

                stepUp();
                stepUp();
                code += "STORE" + typeInitial + " " + value + " " + target + "\n";
            }
        }
    }

    private void buildVariable() {
        if (currentNode.getValue().equals("VARIABLE DECLARATION") && !visited.contains(currentNode)) {
            stepInto(children.get(0));
            String type = currentNode.getValue();
            stepUp();


            for (int i = 1; i < children.size(); i++) {
                //noinspection StringConcatenationInLoop
                code += type + " " + children.get(i).getValue() + "\n";
                symbolTable.addEntry(new Symbol(children.get(i).getValue(), type));
                visited.add(children.get(i));
            }
        }
    }

    private void buildRead() {
        if (currentNode.getValue().equals("READ") && !visited.contains(currentNode)) {
            for (Node child : children) {
                String typeInitial = "";
                String id = child.getValue();
                switch (getType(id)) {
                    case "INT":
                        typeInitial = "I";
                        break;
                    case "FLOAT":
                        typeInitial = "F";
                        break;
                }
                //noinspection StringConcatenationInLoop
                code += "READ" + typeInitial + " " + child.getValue() + "\n";
                visited.add(child);
            }
        }
    }

    private void buildWrite() {
        if (currentNode.getValue().equals("WRITE") && !visited.contains(currentNode)) {
            for (Node child : children) {
                String typeInitial = "";
                String id = child.getValue();
                String type = getType(id);
                switch (type) {
                    case "INT":
                        typeInitial = "I";
                        break;
                    case "FLOAT":
                        typeInitial = "F";
                        break;
                    case "STRING":
                        typeInitial = "S";
                        break;
                }
                //noinspection StringConcatenationInLoop
                code += "WRITE" + typeInitial + " " + child.getValue() + "\n";
                visited.add(child);
            }
        }
    }

    private void buildReturn() {
        if (currentNode.getValue().equals("RETURN") && !visited.contains(currentNode)) {
            String returnID = getOperationTarget(children.get(0));

            String returnTarget;
            if (returnType.equals("FLOAT")) {
                returnTarget = "st(0)";
            } else {
                returnTarget = "%eax";
            }

            code += "LOAD " + returnID + " " + returnTarget +"\n";
        }
    }

    private void buildFunction() {
        if (currentNode.getValue().equals("FUNCTION") && !visited.contains(currentNode)) {
            functionIDCount = 0;
            visited.add(currentNode);
            stepInto(children.get(0));
            String id = currentNode.getValue();
            fids.add(id + functionIDCount);
            functionParameters.put(id, new ArrayList<>());
            functionIDCount++;
            stepUp();
            stepInto(children.get(1));
            returnType = currentNode.getValue();
            functionReturnTypes.put(id, returnType);
            stepUp();
            stepInto(children.get(2));
            for (Node child : children) {
                functionParameters.get(id).add(child.getChildren().get(1).getValue());
                symbolTable.addEntry(new Symbol(child.getChildren().get(1).getValue(), child.getChildren().get(0).getValue()));
                code += child.getChildren().get(0).getValue() + " " + child.getChildren().get(1).getValue() + "\n";
            }
            code += "\n" + "DEF " + fids.peek() + "\n";
        }
    }

    private void buildOperation() {
        if (OPERATOR_LIST.contains(currentNode.getValue()) && !visited.contains(currentNode)) {
            if (currentNode.getParent().getParent().getValue().equals("ASSIGN")) {
                String target = currentNode.getParent().getParent().getChildren().get(0).getValue();
                currentType = symbolTable.getEntry(target).getTypeId();
                String toAdd = currentNode.getValue() + " " + getOperationTarget(children.get(0)) + " "
                             + getOperationTarget(children.get(1)) + " " + target + "\n";
                code += toAdd;
            } else {
                String toAdd = currentNode.getValue() + " " + getOperationTarget(children.get(0)) + " "
                             + getOperationTarget(children.get(1)) + " " + tmp() + "\n";
                code += toAdd;
                symbolTable.addEntry(new Symbol(tmp(), currentType));
                if (getOperationTarget(children.get(0)).matches(INTEGER.pattern())) {
                    currentType = "INT";
                } else if (getOperationTarget(children.get(0)).matches(FLOAT.pattern())) {
                    currentType = "FLOAT";
                } else {
                    currentType = symbolTable.getEntry(getOperationTarget(children.get(0))).getTypeId();
                }
                tmpCount++;
            }
        }
    }

    private String getOperationTarget(Node node) {
        stepInto(node);
        String operationTarget;
        if (OPERATOR_LIST.contains(currentNode.getValue())) {
            String term1 = getOperationTarget(children.get(0));
            String term2 = getOperationTarget(children.get(1));

            String toAdd = currentNode.getValue() + " " + term1 + " " + term2 + " " + tmp() + "\n";
            code += toAdd;
            operationTarget = tmp();
            currentType = getType(term1);
            symbolTable.addEntry(new Symbol(tmp(), currentType));
            tmpCount++;
            stepUp();

        } else if (currentNode.getValue().equals("EXPR")) {
            operationTarget = getOperationTarget(children.get(0));
            stepUp();

        } else if (currentNode.getValue().equals("FUNCTION CALL")) {
            stepInto(children.get(0));
            String id = currentNode.getValue();
            ArrayList<String> parameters = functionParameters.get(id);
            stepUp();

            stepInto(children.get(1));
            for (int i = 0; i < parameters.size(); i++) {
                String toAdd = "LOAD " + getOperationTarget(children.get(i)) + " " + parameters.get(i) + "\n";
                code += toAdd;
            }

            code += "JMP " + id + "0\n";
            String ret;
            if (functionReturnTypes.get(id).equals("FLOAT")) {
                ret = "st(0)";
            } else {
                ret = "%eax";
            }

            operationTarget = tmp();
            code += "LOAD " + ret + " " + operationTarget + "\n";
            tmpCount++;
            symbolTable.addEntry(new Symbol(operationTarget, functionReturnTypes.get(id)));
            stepUp();
            stepUp();

        } else {
            operationTarget = currentNode.getValue();
            stepUp();
        }

        return operationTarget;
    }

    private String tmp() {
        return "$tmp" + tmpCount;
    }

    private void stepInto(Node node) {
        //System.out.println("Step Into");
        //System.out.println(node.getValue());
        currentNode = node;
        children = node.getChildren();
    }

    private void stepUp() {

        if (currentNode.getValue().equals("FUNCTION")) {
            while (!fids.isEmpty()) {
                fids.pop();
            }
            code += "~";
        }

        if (currentNode.getValue().equals("ELSE")) {
            fids.pop();
            fids.pop();
            String x = fids.pop();
            code += "JMP " + fids.peek() + "\n~\n";
            fids.add(x);
            code += "DEF " + fids.peek() + "\n";
            fids.pop();
        }

        if (currentNode.getValue().equals("IF")) {
            fids.pop();
            if (fids.size() != 1) {
                code += "JMP " + fids.peek() + "\n~\nDEF " + fids.peek() + "\n";
            }
        }

        if (currentNode.getValue().equals("WHILE")) {
            code += "JMP " + fids.pop() + "\n~\nDEF " + fids.peek() + "\n";
        }

        //System.out.println("Step Up");
        visited.add(currentNode);
        currentNode = currentNode.getParent();
        //System.out.println(currentNode.getValue());
        children = currentNode.getChildren();
    }

    private void step() {
        for (Node node: children) {
            if (!visited.contains(node)) {
                stepInto(node);
                return;
            }
        }
        stepUp();
    }

}
