package jdk.nashorn.internal.runtime.regexp.joni.constants;

public enum TokenType {
      EOT,            /* end of token */
      RAW_BYTE,
      CHAR,
      STRING,
      CODE_POINT,
      ANYCHAR,
      CHAR_TYPE,
      BACKREF,
      CALL,
      ANCHOR,
      OP_REPEAT,
      INTERVAL,
      ANYCHAR_ANYTIME,  /* SQL '%' == .* */
      ALT,
      SUBEXP_OPEN,
      SUBEXP_CLOSE,
      CC_OPEN,
      QUOTE_OPEN,
      CHAR_PROPERTY,    /* \p{...}, \P{...} */
      /* in cc */
      CC_CLOSE,
      CC_RANGE,
      POSIX_BRACKET_OPEN,
      CC_AND,             /* && */
      CC_CC_OPEN          /* [ */
}
