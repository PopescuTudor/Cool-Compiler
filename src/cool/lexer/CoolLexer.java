// Generated from /home/tudor/IdeaProjects/Tema1Compilatoare/src/cool/lexer/CoolLexer.g4 by ANTLR 4.13.2

    package cool.lexer;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, ELSE=2, FALSE=3, FI=4, IF=5, IN=6, INHERITS=7, ISVOID=8, LET=9, 
		LOOP=10, POOL=11, THEN=12, WHILE=13, CASE=14, ESAC=15, NEW=16, OF=17, 
		NOT=18, TRUE=19, SEMICOLON=20, COLON=21, COMMA=22, DOT=23, AT=24, LPAREN=25, 
		RPAREN=26, LBRACE=27, RBRACE=28, PLUS=29, MINUS=30, STAR=31, SLASH=32, 
		TILDE=33, LT=34, LE=35, GE=36, GT=37, EQUALS=38, DARROW=39, ASSIGN=40, 
		TYPE_ID=41, OBJECT_ID=42, INT=43, STRING=44, UNTERMINATED_STRING=45, EOF_STRING=46, 
		LINE_COMMENT=47, UNMATCHED_COMMENT=48, BLOCK_COMMENT=49, WS=50, ERROR=51;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", "ISVOID", "LET", 
			"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", 
			"TRUE", "SEMICOLON", "COLON", "COMMA", "DOT", "AT", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "PLUS", "MINUS", "STAR", "SLASH", "TILDE", "LT", 
			"LE", "GE", "GT", "EQUALS", "DARROW", "ASSIGN", "TYPE_ID", "OBJECT_ID", 
			"INT", "STRING", "ESC", "NEW_LINE", "UNTERMINATED_STRING", "EOF_STRING", 
			"LINE_COMMENT", "UNMATCHED_COMMENT", "BLOCK_COMMENT", "WS", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'else'", "'false'", "'fi'", "'if'", "'in'", "'inherits'", 
			"'isvoid'", "'let'", "'loop'", "'pool'", "'then'", "'while'", "'case'", 
			"'esac'", "'new'", "'of'", "'not'", "'true'", "';'", "':'", "','", "'.'", 
			"'@'", "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'~'", 
			"'<'", "'<='", "'>='", "'>'", "'='", "'=>'", "'<-'", null, null, null, 
			null, null, null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
			"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", 
			"NOT", "TRUE", "SEMICOLON", "COLON", "COMMA", "DOT", "AT", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "STAR", "SLASH", "TILDE", 
			"LT", "LE", "GE", "GT", "EQUALS", "DARROW", "ASSIGN", "TYPE_ID", "OBJECT_ID", 
			"INT", "STRING", "UNTERMINATED_STRING", "EOF_STRING", "LINE_COMMENT", 
			"UNMATCHED_COMMENT", "BLOCK_COMMENT", "WS", "ERROR"
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


	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 43:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 46:
			UNTERMINATED_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			EOF_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			UNMATCHED_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			        String content = getText().substring(1, getText().length() - 1);
			        if (content.indexOf('\u0000') != -1) {
			            raiseError("String contains null character");
			        } else if (content.length() > 1024) {
			            raiseError("String constant too long");
			        } else {
			            StringBuilder processed = new StringBuilder();
			            boolean escaped = false;
			            for (int i = 0; i < content.length(); i++) {
			                char c = content.charAt(i);
			                if (escaped) {
			                    switch (c) {
			                        case 't': processed.append('\t'); break;
			                        case 'n': processed.append('\n'); break;
			                        case 'b': processed.append('\b'); break;
			                        case 'f': processed.append('\f'); break;
			                        case '\\': processed.append('\\'); break;
			                        default: processed.append(c); break;
			                    }
			                    escaped = false;
			                } else if (c == '\\') {
			                    escaped = true;
			                } else {
			                    processed.append(c);
			                }
			            }
			            setText(processed.toString());
			        }
			    
			break;
		}
	}
	private void UNTERMINATED_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			    raiseError("Unterminated string constant");

			break;
		}
	}
	private void EOF_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

			    raiseError("EOF in string constant");

			break;
		}
	}
	private void UNMATCHED_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:

			        if (_input.LA(1) == EOF) {
			            raiseError("EOF in comment");
			        } else {
			            skip();
			        }
			    
			break;
		}
	}
	private void ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 raiseError("Invalid character: " + getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00003\u015f\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0001(\u0001(\u0005(\u00f9\b(\n(\f(\u00fc\t(\u0001"+
		")\u0001)\u0005)\u0100\b)\n)\f)\u0103\t)\u0001*\u0004*\u0106\b*\u000b*"+
		"\f*\u0107\u0001+\u0001+\u0001+\u0005+\u010d\b+\n+\f+\u0110\t+\u0001+\u0001"+
		"+\u0001+\u0001,\u0001,\u0001,\u0003,\u0118\b,\u0001-\u0003-\u011b\b-\u0001"+
		"-\u0001-\u0001.\u0001.\u0001.\u0005.\u0122\b.\n.\f.\u0125\t.\u0001.\u0001"+
		".\u0001.\u0001/\u0001/\u0001/\u0005/\u012d\b/\n/\f/\u0130\t/\u0001/\u0001"+
		"/\u0001/\u00010\u00010\u00010\u00010\u00050\u0139\b0\n0\f0\u013c\t0\u0001"+
		"0\u00010\u00011\u00011\u00011\u00011\u00011\u00012\u00012\u00012\u0001"+
		"2\u00012\u00052\u014a\b2\n2\f2\u014d\t2\u00012\u00012\u00012\u00032\u0152"+
		"\b2\u00012\u00012\u00013\u00043\u0157\b3\u000b3\f3\u0158\u00013\u0001"+
		"3\u00014\u00014\u00014\u0001\u014b\u00005\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y\u0000[\u0000"+
		"]-_.a/c0e1g2i3\u0001\u0000\t\u0001\u0000AZ\u0004\u000009AZ__az\u0001\u0000"+
		"az\u0001\u000009\u0002\u0000\"\"\\\\\u0006\u0000\"\"\\\\bbffnntt\u0003"+
		"\u0000\n\n\"\"\\\\\u0002\u0000\n\n\r\r\u0003\u0000\t\n\f\r  \u016c\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000"+
		"\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000"+
		"\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K"+
		"\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000"+
		"\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000]"+
		"\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000"+
		"\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000"+
		"\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0001k"+
		"\u0001\u0000\u0000\u0000\u0003q\u0001\u0000\u0000\u0000\u0005v\u0001\u0000"+
		"\u0000\u0000\u0007|\u0001\u0000\u0000\u0000\t\u007f\u0001\u0000\u0000"+
		"\u0000\u000b\u0082\u0001\u0000\u0000\u0000\r\u0085\u0001\u0000\u0000\u0000"+
		"\u000f\u008e\u0001\u0000\u0000\u0000\u0011\u0095\u0001\u0000\u0000\u0000"+
		"\u0013\u0099\u0001\u0000\u0000\u0000\u0015\u009e\u0001\u0000\u0000\u0000"+
		"\u0017\u00a3\u0001\u0000\u0000\u0000\u0019\u00a8\u0001\u0000\u0000\u0000"+
		"\u001b\u00ae\u0001\u0000\u0000\u0000\u001d\u00b3\u0001\u0000\u0000\u0000"+
		"\u001f\u00b8\u0001\u0000\u0000\u0000!\u00bc\u0001\u0000\u0000\u0000#\u00bf"+
		"\u0001\u0000\u0000\u0000%\u00c3\u0001\u0000\u0000\u0000\'\u00c8\u0001"+
		"\u0000\u0000\u0000)\u00ca\u0001\u0000\u0000\u0000+\u00cc\u0001\u0000\u0000"+
		"\u0000-\u00ce\u0001\u0000\u0000\u0000/\u00d0\u0001\u0000\u0000\u00001"+
		"\u00d2\u0001\u0000\u0000\u00003\u00d4\u0001\u0000\u0000\u00005\u00d6\u0001"+
		"\u0000\u0000\u00007\u00d8\u0001\u0000\u0000\u00009\u00da\u0001\u0000\u0000"+
		"\u0000;\u00dc\u0001\u0000\u0000\u0000=\u00de\u0001\u0000\u0000\u0000?"+
		"\u00e0\u0001\u0000\u0000\u0000A\u00e2\u0001\u0000\u0000\u0000C\u00e4\u0001"+
		"\u0000\u0000\u0000E\u00e6\u0001\u0000\u0000\u0000G\u00e9\u0001\u0000\u0000"+
		"\u0000I\u00ec\u0001\u0000\u0000\u0000K\u00ee\u0001\u0000\u0000\u0000M"+
		"\u00f0\u0001\u0000\u0000\u0000O\u00f3\u0001\u0000\u0000\u0000Q\u00f6\u0001"+
		"\u0000\u0000\u0000S\u00fd\u0001\u0000\u0000\u0000U\u0105\u0001\u0000\u0000"+
		"\u0000W\u0109\u0001\u0000\u0000\u0000Y\u0114\u0001\u0000\u0000\u0000["+
		"\u011a\u0001\u0000\u0000\u0000]\u011e\u0001\u0000\u0000\u0000_\u0129\u0001"+
		"\u0000\u0000\u0000a\u0134\u0001\u0000\u0000\u0000c\u013f\u0001\u0000\u0000"+
		"\u0000e\u0144\u0001\u0000\u0000\u0000g\u0156\u0001\u0000\u0000\u0000i"+
		"\u015c\u0001\u0000\u0000\u0000kl\u0005c\u0000\u0000lm\u0005l\u0000\u0000"+
		"mn\u0005a\u0000\u0000no\u0005s\u0000\u0000op\u0005s\u0000\u0000p\u0002"+
		"\u0001\u0000\u0000\u0000qr\u0005e\u0000\u0000rs\u0005l\u0000\u0000st\u0005"+
		"s\u0000\u0000tu\u0005e\u0000\u0000u\u0004\u0001\u0000\u0000\u0000vw\u0005"+
		"f\u0000\u0000wx\u0005a\u0000\u0000xy\u0005l\u0000\u0000yz\u0005s\u0000"+
		"\u0000z{\u0005e\u0000\u0000{\u0006\u0001\u0000\u0000\u0000|}\u0005f\u0000"+
		"\u0000}~\u0005i\u0000\u0000~\b\u0001\u0000\u0000\u0000\u007f\u0080\u0005"+
		"i\u0000\u0000\u0080\u0081\u0005f\u0000\u0000\u0081\n\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0005i\u0000\u0000\u0083\u0084\u0005n\u0000\u0000\u0084"+
		"\f\u0001\u0000\u0000\u0000\u0085\u0086\u0005i\u0000\u0000\u0086\u0087"+
		"\u0005n\u0000\u0000\u0087\u0088\u0005h\u0000\u0000\u0088\u0089\u0005e"+
		"\u0000\u0000\u0089\u008a\u0005r\u0000\u0000\u008a\u008b\u0005i\u0000\u0000"+
		"\u008b\u008c\u0005t\u0000\u0000\u008c\u008d\u0005s\u0000\u0000\u008d\u000e"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0005i\u0000\u0000\u008f\u0090\u0005"+
		"s\u0000\u0000\u0090\u0091\u0005v\u0000\u0000\u0091\u0092\u0005o\u0000"+
		"\u0000\u0092\u0093\u0005i\u0000\u0000\u0093\u0094\u0005d\u0000\u0000\u0094"+
		"\u0010\u0001\u0000\u0000\u0000\u0095\u0096\u0005l\u0000\u0000\u0096\u0097"+
		"\u0005e\u0000\u0000\u0097\u0098\u0005t\u0000\u0000\u0098\u0012\u0001\u0000"+
		"\u0000\u0000\u0099\u009a\u0005l\u0000\u0000\u009a\u009b\u0005o\u0000\u0000"+
		"\u009b\u009c\u0005o\u0000\u0000\u009c\u009d\u0005p\u0000\u0000\u009d\u0014"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0005p\u0000\u0000\u009f\u00a0\u0005"+
		"o\u0000\u0000\u00a0\u00a1\u0005o\u0000\u0000\u00a1\u00a2\u0005l\u0000"+
		"\u0000\u00a2\u0016\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005t\u0000\u0000"+
		"\u00a4\u00a5\u0005h\u0000\u0000\u00a5\u00a6\u0005e\u0000\u0000\u00a6\u00a7"+
		"\u0005n\u0000\u0000\u00a7\u0018\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005"+
		"w\u0000\u0000\u00a9\u00aa\u0005h\u0000\u0000\u00aa\u00ab\u0005i\u0000"+
		"\u0000\u00ab\u00ac\u0005l\u0000\u0000\u00ac\u00ad\u0005e\u0000\u0000\u00ad"+
		"\u001a\u0001\u0000\u0000\u0000\u00ae\u00af\u0005c\u0000\u0000\u00af\u00b0"+
		"\u0005a\u0000\u0000\u00b0\u00b1\u0005s\u0000\u0000\u00b1\u00b2\u0005e"+
		"\u0000\u0000\u00b2\u001c\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005e\u0000"+
		"\u0000\u00b4\u00b5\u0005s\u0000\u0000\u00b5\u00b6\u0005a\u0000\u0000\u00b6"+
		"\u00b7\u0005c\u0000\u0000\u00b7\u001e\u0001\u0000\u0000\u0000\u00b8\u00b9"+
		"\u0005n\u0000\u0000\u00b9\u00ba\u0005e\u0000\u0000\u00ba\u00bb\u0005w"+
		"\u0000\u0000\u00bb \u0001\u0000\u0000\u0000\u00bc\u00bd\u0005o\u0000\u0000"+
		"\u00bd\u00be\u0005f\u0000\u0000\u00be\"\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c0\u0005n\u0000\u0000\u00c0\u00c1\u0005o\u0000\u0000\u00c1\u00c2\u0005"+
		"t\u0000\u0000\u00c2$\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005t\u0000"+
		"\u0000\u00c4\u00c5\u0005r\u0000\u0000\u00c5\u00c6\u0005u\u0000\u0000\u00c6"+
		"\u00c7\u0005e\u0000\u0000\u00c7&\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005"+
		";\u0000\u0000\u00c9(\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005:\u0000"+
		"\u0000\u00cb*\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005,\u0000\u0000\u00cd"+
		",\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005.\u0000\u0000\u00cf.\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0005@\u0000\u0000\u00d10\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d3\u0005(\u0000\u0000\u00d32\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0005)\u0000\u0000\u00d54\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005"+
		"{\u0000\u0000\u00d76\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005}\u0000"+
		"\u0000\u00d98\u0001\u0000\u0000\u0000\u00da\u00db\u0005+\u0000\u0000\u00db"+
		":\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005-\u0000\u0000\u00dd<\u0001"+
		"\u0000\u0000\u0000\u00de\u00df\u0005*\u0000\u0000\u00df>\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0005/\u0000\u0000\u00e1@\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e3\u0005~\u0000\u0000\u00e3B\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005"+
		"<\u0000\u0000\u00e5D\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005<\u0000"+
		"\u0000\u00e7\u00e8\u0005=\u0000\u0000\u00e8F\u0001\u0000\u0000\u0000\u00e9"+
		"\u00ea\u0005>\u0000\u0000\u00ea\u00eb\u0005=\u0000\u0000\u00ebH\u0001"+
		"\u0000\u0000\u0000\u00ec\u00ed\u0005>\u0000\u0000\u00edJ\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0005=\u0000\u0000\u00efL\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f1\u0005=\u0000\u0000\u00f1\u00f2\u0005>\u0000\u0000\u00f2N\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0005<\u0000\u0000\u00f4\u00f5\u0005-\u0000"+
		"\u0000\u00f5P\u0001\u0000\u0000\u0000\u00f6\u00fa\u0007\u0000\u0000\u0000"+
		"\u00f7\u00f9\u0007\u0001\u0000\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000"+
		"\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000"+
		"\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fbR\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fd\u0101\u0007\u0002\u0000\u0000\u00fe"+
		"\u0100\u0007\u0001\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100"+
		"\u0103\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101"+
		"\u0102\u0001\u0000\u0000\u0000\u0102T\u0001\u0000\u0000\u0000\u0103\u0101"+
		"\u0001\u0000\u0000\u0000\u0104\u0106\u0007\u0003\u0000\u0000\u0105\u0104"+
		"\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0105"+
		"\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108V\u0001"+
		"\u0000\u0000\u0000\u0109\u010e\u0005\"\u0000\u0000\u010a\u010d\u0003Y"+
		",\u0000\u010b\u010d\b\u0004\u0000\u0000\u010c\u010a\u0001\u0000\u0000"+
		"\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d\u0110\u0001\u0000\u0000"+
		"\u0000\u010e\u010c\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000"+
		"\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u010e\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0005\"\u0000\u0000\u0112\u0113\u0006+\u0000\u0000"+
		"\u0113X\u0001\u0000\u0000\u0000\u0114\u0117\u0005\\\u0000\u0000\u0115"+
		"\u0118\u0007\u0005\u0000\u0000\u0116\u0118\t\u0000\u0000\u0000\u0117\u0115"+
		"\u0001\u0000\u0000\u0000\u0117\u0116\u0001\u0000\u0000\u0000\u0118Z\u0001"+
		"\u0000\u0000\u0000\u0119\u011b\u0005\r\u0000\u0000\u011a\u0119\u0001\u0000"+
		"\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000"+
		"\u0000\u0000\u011c\u011d\u0005\n\u0000\u0000\u011d\\\u0001\u0000\u0000"+
		"\u0000\u011e\u0123\u0005\"\u0000\u0000\u011f\u0122\u0003Y,\u0000\u0120"+
		"\u0122\b\u0006\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0121\u0120"+
		"\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000\u0123\u0121"+
		"\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0126"+
		"\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0126\u0127"+
		"\u0003[-\u0000\u0127\u0128\u0006.\u0001\u0000\u0128^\u0001\u0000\u0000"+
		"\u0000\u0129\u012e\u0005\"\u0000\u0000\u012a\u012d\u0003Y,\u0000\u012b"+
		"\u012d\b\u0006\u0000\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012b"+
		"\u0001\u0000\u0000\u0000\u012d\u0130\u0001\u0000\u0000\u0000\u012e\u012c"+
		"\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0131"+
		"\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0131\u0132"+
		"\u0005\u0000\u0000\u0001\u0132\u0133\u0006/\u0002\u0000\u0133`\u0001\u0000"+
		"\u0000\u0000\u0134\u0135\u0005-\u0000\u0000\u0135\u0136\u0005-\u0000\u0000"+
		"\u0136\u013a\u0001\u0000\u0000\u0000\u0137\u0139\b\u0007\u0000\u0000\u0138"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a"+
		"\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b"+
		"\u013d\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u00060\u0003\u0000\u013eb\u0001\u0000\u0000\u0000\u013f\u0140\u0005"+
		"*\u0000\u0000\u0140\u0141\u0005)\u0000\u0000\u0141\u0142\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u00061\u0004\u0000\u0143d\u0001\u0000\u0000\u0000\u0144"+
		"\u0145\u0005(\u0000\u0000\u0145\u0146\u0005*\u0000\u0000\u0146\u014b\u0001"+
		"\u0000\u0000\u0000\u0147\u014a\u0003e2\u0000\u0148\u014a\t\u0000\u0000"+
		"\u0000\u0149\u0147\u0001\u0000\u0000\u0000\u0149\u0148\u0001\u0000\u0000"+
		"\u0000\u014a\u014d\u0001\u0000\u0000\u0000\u014b\u014c\u0001\u0000\u0000"+
		"\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014c\u0151\u0001\u0000\u0000"+
		"\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e\u014f\u0005*\u0000\u0000"+
		"\u014f\u0152\u0005)\u0000\u0000\u0150\u0152\u0005\u0000\u0000\u0001\u0151"+
		"\u014e\u0001\u0000\u0000\u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0152"+
		"\u0153\u0001\u0000\u0000\u0000\u0153\u0154\u00062\u0005\u0000\u0154f\u0001"+
		"\u0000\u0000\u0000\u0155\u0157\u0007\b\u0000\u0000\u0156\u0155\u0001\u0000"+
		"\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u0156\u0001\u0000"+
		"\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000"+
		"\u0000\u0000\u015a\u015b\u00063\u0003\u0000\u015bh\u0001\u0000\u0000\u0000"+
		"\u015c\u015d\t\u0000\u0000\u0000\u015d\u015e\u00064\u0006\u0000\u015e"+
		"j\u0001\u0000\u0000\u0000\u0011\u0000\u00fa\u0101\u0107\u010c\u010e\u0117"+
		"\u011a\u0121\u0123\u012c\u012e\u013a\u0149\u014b\u0151\u0158\u0007\u0001"+
		"+\u0000\u0001.\u0001\u0001/\u0002\u0006\u0000\u0000\u00011\u0003\u0001"+
		"2\u0004\u00014\u0005";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}