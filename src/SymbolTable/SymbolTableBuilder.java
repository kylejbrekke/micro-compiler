package SymbolTable;

import Parser.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SymbolTableBuilder extends littleBaseListener {

    protected HashMap<String, SymbolTable> symbolTables = new HashMap<>();
    private Stack<String> scope = new Stack<>();
    private ArrayList<String> keyOrder = new ArrayList<>();
    private ArrayList<String> conflicts = new ArrayList<>();
    private int blockCount = 1;

    public void printSymbolTables() {
        if (conflicts.isEmpty()) {
            for (String key : keyOrder) {
                SymbolTable table = symbolTables.get(key);
                System.out.println("Symbol table " + table.id);
                for (Symbol symbol : table.entries) {
                    if (symbol.value != null) {
                        System.out.println("name " + symbol.id + " type " + symbol.typeId + " value " + symbol.value);
                    } else {
                        System.out.println("name " + symbol.id + " type " + symbol.typeId);
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("DECLARATION ERROR " + conflicts.get(0));
        }
    }

    public HashMap<String, SymbolTable> getSymbolTables() {
        return symbolTables;
    }

    public void checkForDuplicates(SymbolTable table, String id) {
        if (table.contains(id)) {
            conflicts.add(id);
        }
    }

    @Override public void enterProgram(littleParser.ProgramContext ctx) {
        scope.push("GLOBAL");
        keyOrder.add("GLOBAL");
        symbolTables.put(scope.peek(), new SymbolTable(scope.peek()));
    }

    @Override public void exitProgram(littleParser.ProgramContext ctx) {
        scope.pop();
    }

    @Override public void enterFunc_decl(littleParser.Func_declContext ctx) {
        scope.push(ctx.id().getText());
        keyOrder.add(ctx.id().getText());
        symbolTables.put(scope.peek(), new SymbolTable(scope.peek()));
    }

    @Override public void enterParam_decl(littleParser.Param_declContext ctx) {
        SymbolTable table = symbolTables.get(scope.peek());
        checkForDuplicates(table, ctx.id().getText());
        table.addEntry(new Symbol(ctx.id().getText(), ctx.var_type().getText()));
    }

    @Override public void exitFunc_decl(littleParser.Func_declContext ctx) {
        scope.pop();
    }

    @Override public void enterIf_stmt(littleParser.If_stmtContext ctx) {
        scope.push("BLOCK " + blockCount);
        keyOrder.add("BLOCK " + blockCount);
        blockCount++;
        symbolTables.put(scope.peek(), new SymbolTable(scope.peek()));
    }

    @Override public void exitIf_stmt(littleParser.If_stmtContext ctx) {
        scope.pop();
    }

    @Override public void enterElse_part(littleParser.Else_partContext ctx) {
        if (ctx.empty() == null) {
            scope.push("BLOCK " + blockCount);
            keyOrder.add("BLOCK " + blockCount);
            blockCount++;
            symbolTables.put(scope.peek(), new SymbolTable(scope.peek()));
        }
    }

    @Override public void exitElse_part(littleParser.Else_partContext ctx) {
        if (ctx.empty() == null) {
            scope.pop();
        }
    }

    @Override public void enterWhile_stmt(littleParser.While_stmtContext ctx) {
        scope.push("BLOCK " + blockCount);
        keyOrder.add("BLOCK " + blockCount);
        blockCount++;
        symbolTables.put(scope.peek(), new SymbolTable(scope.peek()));
    }

    @Override public void exitWhile_stmt(littleParser.While_stmtContext ctx) {
        scope.pop();
    }

    @Override public void enterString_decl(littleParser.String_declContext ctx) {
        SymbolTable table = symbolTables.get(scope.peek());
        checkForDuplicates(table, ctx.id().getText());
        table.addEntry(new Symbol(ctx.id().getText(), "STRING", ctx.str().getText()));
    }

    @Override public void enterVar_decl(littleParser.Var_declContext ctx) {
        SymbolTable table = symbolTables.get(scope.peek());
        checkForDuplicates(table, ctx.id_list().id().getText());
        littleParser.Id_tailContext id_tail = ctx.id_list().id_tail();
        table.addEntry(new Symbol(ctx.id_list().id().getText(), ctx.var_type().getText()));

        while (id_tail.id() != null) {
            checkForDuplicates(table, id_tail.id().getText());
            table.addEntry(new Symbol(id_tail.id().getText(), ctx.var_type().getText()));
            id_tail = id_tail.id_tail();
        }
    }
}
