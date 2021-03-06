// Generated from src/Parser/little.g4 by ANTLR 4.7.2
package Parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class littleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PROGRAM=1, BEGIN=2, END=3, FUNCTION=4, READ=5, WRITE=6, IF=7, ELSE=8, 
		ENDIF=9, WHILE=10, ENDWHILE=11, CONTINUE=12, BREAK=13, RETURN=14, INT=15, 
		VOID=16, STRING=17, FLOAT=18, IDENTIFIER=19, INTLITERAL=20, FLOATLITERAL=21, 
		STRINGLITERAL=22, COMMENT=23, ADD_OP=24, SUB_OP=25, MUL_OP=26, DIV_OP=27, 
		LES_OP=28, GRE_OP=29, EQU_OP=30, NEQ_OP=31, LEQ_OP=32, GEQ_OP=33, LIS_OP=34, 
		END_OP=35, LPAREN=36, RPAREN=37, ASSIGN=38, WHITESPACE=39, ERROR=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PROGRAM", "BEGIN", "END", "FUNCTION", "READ", "WRITE", "IF", "ELSE", 
			"ENDIF", "WHILE", "ENDWHILE", "CONTINUE", "BREAK", "RETURN", "INT", "VOID", 
			"STRING", "FLOAT", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
			"COMMENT", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "LES_OP", "GRE_OP", 
			"EQU_OP", "NEQ_OP", "LEQ_OP", "GEQ_OP", "LIS_OP", "END_OP", "LPAREN", 
			"RPAREN", "ASSIGN", "WHITESPACE", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PROGRAM'", "'BEGIN'", "'END'", "'FUNCTION'", "'READ'", "'WRITE'", 
			"'IF'", "'ELSE'", "'ENDIF'", "'WHILE'", "'ENDWHILE'", "'CONTINUE'", "'BREAK'", 
			"'RETURN'", "'INT'", "'VOID'", "'STRING'", "'FLOAT'", null, null, null, 
			null, null, "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='", "'!='", 
			"'<='", "'>='", "','", "';'", "'('", "')'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAM", "BEGIN", "END", "FUNCTION", "READ", "WRITE", "IF", "ELSE", 
			"ENDIF", "WHILE", "ENDWHILE", "CONTINUE", "BREAK", "RETURN", "INT", "VOID", 
			"STRING", "FLOAT", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
			"COMMENT", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "LES_OP", "GRE_OP", 
			"EQU_OP", "NEQ_OP", "LEQ_OP", "GEQ_OP", "LIS_OP", "END_OP", "LPAREN", 
			"RPAREN", "ASSIGN", "WHITESPACE", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public littleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "little.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u011a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\7\24\u00c5"+
		"\n\24\f\24\16\24\u00c8\13\24\3\25\6\25\u00cb\n\25\r\25\16\25\u00cc\3\26"+
		"\7\26\u00d0\n\26\f\26\16\26\u00d3\13\26\3\26\3\26\6\26\u00d7\n\26\r\26"+
		"\16\26\u00d8\3\27\3\27\3\27\7\27\u00de\n\27\f\27\16\27\u00e1\13\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\7\30\u00e9\n\30\f\30\16\30\u00ec\13\30\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3"+
		"\'\3(\6(\u0113\n(\r(\16(\u0114\3(\3(\3)\3)\2\2*\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*\3\2\t\3\2C|\4\2\62;C|\3\2\62;\3\2$$\3\2^^\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\2\u0121\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\3S\3\2\2\2\5[\3\2\2\2\7a\3\2\2\2\te\3\2\2\2\13n\3\2\2\2\rs\3\2\2\2"+
		"\17y\3\2\2\2\21|\3\2\2\2\23\u0081\3\2\2\2\25\u0087\3\2\2\2\27\u008d\3"+
		"\2\2\2\31\u0096\3\2\2\2\33\u009f\3\2\2\2\35\u00a5\3\2\2\2\37\u00ac\3\2"+
		"\2\2!\u00b0\3\2\2\2#\u00b5\3\2\2\2%\u00bc\3\2\2\2\'\u00c2\3\2\2\2)\u00ca"+
		"\3\2\2\2+\u00d1\3\2\2\2-\u00da\3\2\2\2/\u00e4\3\2\2\2\61\u00ef\3\2\2\2"+
		"\63\u00f1\3\2\2\2\65\u00f3\3\2\2\2\67\u00f5\3\2\2\29\u00f7\3\2\2\2;\u00f9"+
		"\3\2\2\2=\u00fb\3\2\2\2?\u00fd\3\2\2\2A\u0100\3\2\2\2C\u0103\3\2\2\2E"+
		"\u0106\3\2\2\2G\u0108\3\2\2\2I\u010a\3\2\2\2K\u010c\3\2\2\2M\u010e\3\2"+
		"\2\2O\u0112\3\2\2\2Q\u0118\3\2\2\2ST\7R\2\2TU\7T\2\2UV\7Q\2\2VW\7I\2\2"+
		"WX\7T\2\2XY\7C\2\2YZ\7O\2\2Z\4\3\2\2\2[\\\7D\2\2\\]\7G\2\2]^\7I\2\2^_"+
		"\7K\2\2_`\7P\2\2`\6\3\2\2\2ab\7G\2\2bc\7P\2\2cd\7F\2\2d\b\3\2\2\2ef\7"+
		"H\2\2fg\7W\2\2gh\7P\2\2hi\7E\2\2ij\7V\2\2jk\7K\2\2kl\7Q\2\2lm\7P\2\2m"+
		"\n\3\2\2\2no\7T\2\2op\7G\2\2pq\7C\2\2qr\7F\2\2r\f\3\2\2\2st\7Y\2\2tu\7"+
		"T\2\2uv\7K\2\2vw\7V\2\2wx\7G\2\2x\16\3\2\2\2yz\7K\2\2z{\7H\2\2{\20\3\2"+
		"\2\2|}\7G\2\2}~\7N\2\2~\177\7U\2\2\177\u0080\7G\2\2\u0080\22\3\2\2\2\u0081"+
		"\u0082\7G\2\2\u0082\u0083\7P\2\2\u0083\u0084\7F\2\2\u0084\u0085\7K\2\2"+
		"\u0085\u0086\7H\2\2\u0086\24\3\2\2\2\u0087\u0088\7Y\2\2\u0088\u0089\7"+
		"J\2\2\u0089\u008a\7K\2\2\u008a\u008b\7N\2\2\u008b\u008c\7G\2\2\u008c\26"+
		"\3\2\2\2\u008d\u008e\7G\2\2\u008e\u008f\7P\2\2\u008f\u0090\7F\2\2\u0090"+
		"\u0091\7Y\2\2\u0091\u0092\7J\2\2\u0092\u0093\7K\2\2\u0093\u0094\7N\2\2"+
		"\u0094\u0095\7G\2\2\u0095\30\3\2\2\2\u0096\u0097\7E\2\2\u0097\u0098\7"+
		"Q\2\2\u0098\u0099\7P\2\2\u0099\u009a\7V\2\2\u009a\u009b\7K\2\2\u009b\u009c"+
		"\7P\2\2\u009c\u009d\7W\2\2\u009d\u009e\7G\2\2\u009e\32\3\2\2\2\u009f\u00a0"+
		"\7D\2\2\u00a0\u00a1\7T\2\2\u00a1\u00a2\7G\2\2\u00a2\u00a3\7C\2\2\u00a3"+
		"\u00a4\7M\2\2\u00a4\34\3\2\2\2\u00a5\u00a6\7T\2\2\u00a6\u00a7\7G\2\2\u00a7"+
		"\u00a8\7V\2\2\u00a8\u00a9\7W\2\2\u00a9\u00aa\7T\2\2\u00aa\u00ab\7P\2\2"+
		"\u00ab\36\3\2\2\2\u00ac\u00ad\7K\2\2\u00ad\u00ae\7P\2\2\u00ae\u00af\7"+
		"V\2\2\u00af \3\2\2\2\u00b0\u00b1\7X\2\2\u00b1\u00b2\7Q\2\2\u00b2\u00b3"+
		"\7K\2\2\u00b3\u00b4\7F\2\2\u00b4\"\3\2\2\2\u00b5\u00b6\7U\2\2\u00b6\u00b7"+
		"\7V\2\2\u00b7\u00b8\7T\2\2\u00b8\u00b9\7K\2\2\u00b9\u00ba\7P\2\2\u00ba"+
		"\u00bb\7I\2\2\u00bb$\3\2\2\2\u00bc\u00bd\7H\2\2\u00bd\u00be\7N\2\2\u00be"+
		"\u00bf\7Q\2\2\u00bf\u00c0\7C\2\2\u00c0\u00c1\7V\2\2\u00c1&\3\2\2\2\u00c2"+
		"\u00c6\t\2\2\2\u00c3\u00c5\t\3\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7(\3\2\2\2\u00c8\u00c6"+
		"\3\2\2\2\u00c9\u00cb\t\4\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd*\3\2\2\2\u00ce\u00d0\t\4\2\2"+
		"\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d6\7\60\2\2"+
		"\u00d5\u00d7\t\4\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6"+
		"\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9,\3\2\2\2\u00da\u00df\7$\2\2\u00db\u00de"+
		"\n\5\2\2\u00dc\u00de\t\6\2\2\u00dd\u00db\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2"+
		"\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7$\2\2\u00e3.\3\2\2\2\u00e4\u00e5"+
		"\7/\2\2\u00e5\u00e6\7/\2\2\u00e6\u00ea\3\2\2\2\u00e7\u00e9\n\7\2\2\u00e8"+
		"\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\b\30\2\2\u00ee"+
		"\60\3\2\2\2\u00ef\u00f0\7-\2\2\u00f0\62\3\2\2\2\u00f1\u00f2\7/\2\2\u00f2"+
		"\64\3\2\2\2\u00f3\u00f4\7,\2\2\u00f4\66\3\2\2\2\u00f5\u00f6\7\61\2\2\u00f6"+
		"8\3\2\2\2\u00f7\u00f8\7>\2\2\u00f8:\3\2\2\2\u00f9\u00fa\7@\2\2\u00fa<"+
		"\3\2\2\2\u00fb\u00fc\7?\2\2\u00fc>\3\2\2\2\u00fd\u00fe\7#\2\2\u00fe\u00ff"+
		"\7?\2\2\u00ff@\3\2\2\2\u0100\u0101\7>\2\2\u0101\u0102\7?\2\2\u0102B\3"+
		"\2\2\2\u0103\u0104\7@\2\2\u0104\u0105\7?\2\2\u0105D\3\2\2\2\u0106\u0107"+
		"\7.\2\2\u0107F\3\2\2\2\u0108\u0109\7=\2\2\u0109H\3\2\2\2\u010a\u010b\7"+
		"*\2\2\u010bJ\3\2\2\2\u010c\u010d\7+\2\2\u010dL\3\2\2\2\u010e\u010f\7<"+
		"\2\2\u010f\u0110\7?\2\2\u0110N\3\2\2\2\u0111\u0113\t\b\2\2\u0112\u0111"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\u0117\b(\2\2\u0117P\3\2\2\2\u0118\u0119\13\2\2\2"+
		"\u0119R\3\2\2\2\13\2\u00c6\u00cc\u00d1\u00d8\u00dd\u00df\u00ea\u0114\3"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}