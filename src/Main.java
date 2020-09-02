import Interpreter.AbstractParseTree.ASTBuilder;
import Interpreter.AbstractParseTree.AbstractParseTree;
import Interpreter.CodeGeneration.AssemblyConverter;
import Interpreter.CodeGeneration.TACBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import SymbolTable.SymbolTableBuilder;
import Parser.littleParser;
import Parser.littleLexer;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = contentBuilder.toString();
        CharStream stream = CharStreams.fromString(content);
        littleLexer lexer = new littleLexer(stream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        littleParser parser = new littleParser(commonTokenStream);

        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(symbolTableBuilder, tree);
        ASTBuilder astBuilder = new ASTBuilder();
        walker.walk(astBuilder, tree);
        AbstractParseTree abstractParseTree = astBuilder.abstractParseTree;
        abstractParseTree.adjust();
        //abstractParseTree.printTree();
        new TACBuilder(astBuilder.abstractParseTree);
        new AssemblyConverter("interim.3ac");
    }
}
