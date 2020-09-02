package Interpreter.AbstractParseTree;

import Parser.*;

import java.util.Stack;

public class ASTBuilder extends littleBaseListener {

    public AbstractParseTree abstractParseTree = new AbstractParseTree();
    private Stack<String> path = new Stack<>();
    private Node currentNode = null;

    public void addTypeAndID(String type, String id) {
        addNode(type);
        up();
        addNode(id);
        up();
    }
    
    public void addNode(String value) {
        currentNode = new Node(value, currentNode);
        abstractParseTree.allNodes.add(currentNode);
        path.push(value);
    }
    
    public void up() {
        currentNode = currentNode.parent;
    }

    @Override public void enterProgram(littleParser.ProgramContext ctx) {
        currentNode = new Node("PROGRAM");
        path.push("PROGRAM");
        abstractParseTree.root = currentNode;
        addNode(ctx.id().getText());
    }

    @Override public void exitProgram(littleParser.ProgramContext ctx) {
        up();
    }

    @Override public void enterFunc_decl(littleParser.Func_declContext ctx) {
        addNode("FUNCTION");
        addNode(ctx.id().getText());
        up();
    }

    @Override public void exitFunc_decl(littleParser.Func_declContext ctx) {
        up();
    }

    @Override public void enterAny_type(littleParser.Any_typeContext ctx) {
        if (ctx.var_type() != null) {
            addNode(ctx.var_type().getText());
        } else {
            addNode(ctx.getText());
        }
        up();
    }

    @Override public void enterParam_decl_list(littleParser.Param_decl_listContext ctx) {
        addNode("PARAMS");
    }

    @Override public void exitParam_decl_list(littleParser.Param_decl_listContext ctx) {
        up();
    }

    @Override public void enterParam_decl(littleParser.Param_declContext ctx) {
        addNode("PARAM");
        addTypeAndID(ctx.var_type().getText(), ctx.id().getText());
        up();
    }

    @Override public void enterFunc_body(littleParser.Func_bodyContext ctx) {
        addNode("BODY");
    }

    @Override public void exitFunc_body(littleParser.Func_bodyContext ctx) {
        up();
    }

    @Override public void enterVar_decl(littleParser.Var_declContext ctx) {
        addNode("VARIABLE DECLARATION");
        addNode(ctx.var_type().getText());
        up();
    }

    @Override public void exitVar_decl(littleParser.Var_declContext ctx) {
        up();
    }
    
    @Override public void enterString_decl(littleParser.String_declContext ctx) {
        addNode("STRING DECLARATION");
        addNode(ctx.id().getText());
        up();
        addNode(ctx.str().getText());
        up();
        up();
    }

    @Override public void enterAssign_expr(littleParser.Assign_exprContext ctx) {
        addNode("ASSIGN");
        addNode(ctx.id().getText());
        up();
    }

    @Override public void exitAssign_expr(littleParser.Assign_exprContext ctx) {
        up();
    }

    @Override public void enterCall_expr(littleParser.Call_exprContext ctx) {
        addNode("FUNCTION CALL");
        addNode(ctx.id().getText());
        up();
    }

    @Override public void exitCall_expr(littleParser.Call_exprContext ctx) {
        up();
    }

    @Override public void enterExpr_list(littleParser.Expr_listContext ctx) {
        addNode("EXPRESSIONS");
    }

    @Override public void exitExpr_list(littleParser.Expr_listContext ctx) {
        up();
    }

    @Override public void enterExpr(littleParser.ExprContext ctx) {
        addNode("EXPR");
    }

    @Override public void exitExpr(littleParser.ExprContext ctx) {
        up();
    }

    @Override public void enterPrimary(littleParser.PrimaryContext ctx) {
        if (ctx.expr() == null) {
            if (ctx.id() != null) {
                addNode(ctx.id().getText());
            } else {
                addNode(ctx.getText());
            }
            up();
        }
    }

    @Override public void enterExpr_prefix(littleParser.Expr_prefixContext ctx) {
        if (ctx.empty() == null) {
            addNode(ctx.addop().getText());
        }
    }

    @Override public void exitExpr_prefix(littleParser.Expr_prefixContext ctx) {
        if (ctx.empty() == null) {
            up();
        }
    }

    @Override public void enterFactor_prefix(littleParser.Factor_prefixContext ctx) {
        if (ctx.empty() == null) {
            addNode(ctx.mulop().getText());
        }
    }

    @Override public void exitFactor_prefix(littleParser.Factor_prefixContext ctx) {
        if (ctx.empty() == null) {
            up();
        }
    }

    @Override public void enterIf_stmt(littleParser.If_stmtContext ctx) {
        addNode("IF");
    }

    @Override public void exitIf_stmt(littleParser.If_stmtContext ctx) {
        up();
    }

    @Override public void enterCond(littleParser.CondContext ctx) {
        addNode(ctx.compop().getText());
    }

    @Override public void exitCond(littleParser.CondContext ctx) {
        up();
    }

    @Override public void enterElse_part(littleParser.Else_partContext ctx) {
        if (ctx.empty() == null) {
            addNode("ELSE");
        }
    }

    @Override public void exitElse_part(littleParser.Else_partContext ctx) {
        if (ctx.empty() == null) {
            up();
        }
    }

    @Override public void enterWhile_stmt(littleParser.While_stmtContext ctx) {
        addNode("WHILE");
    }

    @Override public void exitWhile_stmt(littleParser.While_stmtContext ctx) {
        up();
    }

    @Override public void enterReturn_stmt(littleParser.Return_stmtContext ctx) {
        addNode("RETURN");
    }

    @Override public void exitReturn_stmt(littleParser.Return_stmtContext ctx) {
        up();
    }

    @Override public void enterRead_stmt(littleParser.Read_stmtContext ctx) {
        addNode("READ");
    }

    @Override public void exitRead_stmt(littleParser.Read_stmtContext ctx) {
        up();
    }

    @Override public void enterWrite_stmt(littleParser.Write_stmtContext ctx) {
        addNode("WRITE");
    }

    @Override public void exitWrite_stmt(littleParser.Write_stmtContext ctx) {
        up();
    }

    @Override public void enterId_list(littleParser.Id_listContext ctx) {
        addNode(ctx.id().getText());
        up();
    }

    @Override public void enterId_tail(littleParser.Id_tailContext ctx) {
        if (ctx.empty() == null) {
            addNode(ctx.id().getText());
            up();
        }
    }

}
