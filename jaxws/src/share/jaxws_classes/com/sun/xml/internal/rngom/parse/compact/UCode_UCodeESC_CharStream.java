package com.sun.xml.internal.rngom.parse.compact;

import com.sun.xml.internal.rngom.util.Utf16;
import com.sun.xml.internal.rngom.ast.builder.BuildException;

import java.io.IOException;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain 16-bit unicode characters.
 */
public final class UCode_UCodeESC_CharStream {

    public static final boolean staticFlag = false;

    static final int hexval(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;

            case 'a':
            case 'A':
                return 10;
            case 'b':
            case 'B':
                return 11;
            case 'c':
            case 'C':
                return 12;
            case 'd':
            case 'D':
                return 13;
            case 'e':
            case 'E':
                return 14;
            case 'f':
            case 'F':
                return 15;
        }
        return -1;
    }
    public int bufpos = -1;
    int bufsize;
    int available;
    int tokenBegin;
    private int bufline[];
    private int bufcolumn[];
    private int column = 0;
    private int line = 1;
    private java.io.Reader inputStream;
    private boolean closed = false;
    private boolean prevCharIsLF = false;
    private char[] nextCharBuf;
    private char[] buffer;
    private int maxNextCharInd = 0;
    private int nextCharInd = -1;
    private int inBuf = 0;

    private final void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[bufsize + 2048];
        int newbufline[] = new int[bufsize + 2048];
        int newbufcolumn[] = new int[bufsize + 2048];

        if (wrapAround) {
            System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
            System.arraycopy(buffer, 0, newbuffer,
                    bufsize - tokenBegin, bufpos);
            buffer = newbuffer;

            System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
            System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
            bufline = newbufline;

            System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
            System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
            bufcolumn = newbufcolumn;

            bufpos += (bufsize - tokenBegin);
        } else {
            System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
            buffer = newbuffer;

            System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
            bufline = newbufline;

            System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
            bufcolumn = newbufcolumn;

            bufpos -= tokenBegin;
        }

        available = (bufsize += 2048);
        tokenBegin = 0;
    }

    private final void FillBuff() throws EOFException {
        int i;
        if (maxNextCharInd == 4096) {
            maxNextCharInd = nextCharInd = 0;
        }

        if (closed) {
            throw new EOFException();
        }
        try {
            if ((i = inputStream.read(nextCharBuf, maxNextCharInd, 4096 - maxNextCharInd)) == -1) {
                closed = true;
                inputStream.close();
                throw new EOFException();
            } else {
                maxNextCharInd += i;
            }
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private final char ReadChar() throws EOFException {
        if (++nextCharInd >= maxNextCharInd) {
            FillBuff();
        }

        return nextCharBuf[nextCharInd];
    }

    private final char PeekChar() throws EOFException {
        char c = ReadChar();
        --nextCharInd;
        return c;
    }

    public final char BeginToken() throws EOFException {
        if (inBuf > 0) {
            --inBuf;
            return buffer[tokenBegin = (bufpos == bufsize - 1) ? (bufpos = 0)
                    : ++bufpos];
        }

        tokenBegin = 0;
        bufpos = -1;

        return readChar();
    }

    private final void AdjustBuffSize() {
        if (available == bufsize) {
            if (tokenBegin > 2048) {
                bufpos = 0;
                available = tokenBegin;
            } else {
                ExpandBuff(false);
            }
        } else if (available > tokenBegin) {
            available = bufsize;
        } else if ((tokenBegin - available) < 2048) {
            ExpandBuff(true);
        } else {
            available = tokenBegin;
        }
    }

    private final void UpdateLineColumn(char c) {
        column++;

        if (prevCharIsLF) {
            prevCharIsLF = false;
            line += (column = 1);
        }

        switch (c) {
            case NEWLINE_MARKER:
                prevCharIsLF = true;
                break;
            case '\t':
                column--;
                column += (8 - (column & 07));
                break;
            default:
                break;
        }

        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
    }
    private static final char NEWLINE_MARKER = '\u0000';

    public final char readChar() throws EOFException {
        if (inBuf > 0) {
            --inBuf;
            return buffer[(bufpos == bufsize - 1) ? (bufpos = 0) : ++bufpos];
        }

        char c;
        try {
            c = ReadChar();
            switch (c) {
                case '\r':
                    c = NEWLINE_MARKER;
                    try {
                        if (PeekChar() == '\n') {
                            ReadChar();
                        }
                    } catch (EOFException e) {
                    }
                    break;
                case '\n':
                    c = NEWLINE_MARKER;
                    break;
                case '\t':
                    break;
                default:
                    if (c >= 0x20) {
                        if (Utf16.isSurrogate(c)) {
                            if (Utf16.isSurrogate2(c)) {
                                throw new EscapeSyntaxException("illegal_surrogate_pair", line, column + 1);
                            }
                            if (++bufpos == available) {
                                AdjustBuffSize();
                            }
                            buffer[bufpos] = c;
                            // UpdateLineColumn(c);
                            try {
                                c = ReadChar();
                            } catch (EOFException e) {
                                throw new EscapeSyntaxException("illegal_surrogate_pair", line, column + 1);
                            }
                            if (!Utf16.isSurrogate2(c)) {
                                throw new EscapeSyntaxException("illegal_surrogate_pair", line, column + 2);
                            }
                        }
                        break;
                    }
                // fall through
                case '\uFFFE':
                case '\uFFFF':
                    throw new EscapeSyntaxException("illegal_char_code", line, column + 1);
            }
        } catch (EOFException e) {
            if (bufpos == -1) {
                if (++bufpos == available) {
                    AdjustBuffSize();
                }
                bufline[bufpos] = line;
                bufcolumn[bufpos] = column;
            }
            throw e;
        }
        if (++bufpos == available) {
            AdjustBuffSize();
        }
        buffer[bufpos] = c;
        UpdateLineColumn(c);
        try {
            if (c != '\\' || PeekChar() != 'x') {
                return c;
            }
        } catch (EOFException e) {
            return c;
        }

        int xCnt = 1;
        for (;;) {
            ReadChar();
            if (++bufpos == available) {
                AdjustBuffSize();
            }
            buffer[bufpos] = 'x';
            UpdateLineColumn('x');
            try {
                c = PeekChar();
            } catch (EOFException e) {
                backup(xCnt);
                return '\\';
            }
            if (c == '{') {
                ReadChar();
                column++;
                // backup past the 'x's
                bufpos -= xCnt;
                if (bufpos < 0) {
                    bufpos += bufsize;
                }
                break;
            }
            if (c != 'x') {
                backup(xCnt);
                return '\\';
            }
            xCnt++;
        }
        try {
            int scalarValue = hexval(ReadChar());
            column++;
            if (scalarValue < 0) {
                throw new EscapeSyntaxException("illegal_hex_digit", line, column);
            }
            while ((c = ReadChar()) != '}') {
                column++;
                int n = hexval(c);
                if (n < 0) {
                    throw new EscapeSyntaxException("illegal_hex_digit", line, column);
                }
                scalarValue <<= 4;
                scalarValue |= n;
                if (scalarValue >= 0x110000) {
                    throw new EscapeSyntaxException("char_code_too_big", line, column);
                }
            }
            column++; // for the '}'
            if (scalarValue <= 0xFFFF) {
                c = (char) scalarValue;
                switch (c) {
                    case '\n':
                    case '\r':
                    case '\t':
                        break;
                    default:
                        if (c >= 0x20 && !Utf16.isSurrogate(c)) {
                            break;
                        }
                    // fall through
                    case '\uFFFE':
                    case '\uFFFF':
                        throw new EscapeSyntaxException("illegal_char_code_ref", line, column);
                }
                buffer[bufpos] = c;
                return c;
            }
            c = Utf16.surrogate1(scalarValue);
            buffer[bufpos] = c;
            int bufpos1 = bufpos;
            if (++bufpos == bufsize) {
                bufpos = 0;
            }
            buffer[bufpos] = Utf16.surrogate2(scalarValue);
            bufline[bufpos] = bufline[bufpos1];
            bufcolumn[bufpos] = bufcolumn[bufpos1];
            backup(1);
            return c;
        } catch (EOFException e) {
            throw new EscapeSyntaxException("incomplete_escape", line, column);
        }
    }

    /**
     * @deprecated @see #getEndColumn
     */
    public final int getColumn() {
        return bufcolumn[bufpos];
    }

    /**
     * @deprecated @see #getEndLine
     */
    public final int getLine() {
        return bufline[bufpos];
    }

    public final int getEndColumn() {
        return bufcolumn[bufpos];
    }

    public final int getEndLine() {
        return bufline[bufpos];
    }

    public final int getBeginColumn() {
        return bufcolumn[tokenBegin];
    }

    public final int getBeginLine() {
        return bufline[tokenBegin];
    }

    public final void backup(int amount) {

        inBuf += amount;
        if ((bufpos -= amount) < 0) {
            bufpos += bufsize;
        }
    }

    public UCode_UCodeESC_CharStream(java.io.Reader dstream,
            int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        line = startline;
        column = startcolumn - 1;

        available = bufsize = buffersize;
        buffer = new char[buffersize];
        bufline = new int[buffersize];
        bufcolumn = new int[buffersize];
        nextCharBuf = new char[4096];
        skipBOM();
    }

    public UCode_UCodeESC_CharStream(java.io.Reader dstream,
            int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(java.io.Reader dstream,
            int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        closed = false;
        line = startline;
        column = startcolumn - 1;

        if (buffer == null || buffersize != buffer.length) {
            available = bufsize = buffersize;
            buffer = new char[buffersize];
            bufline = new int[buffersize];
            bufcolumn = new int[buffersize];
            nextCharBuf = new char[4096];
        }
        prevCharIsLF = false;
        tokenBegin = inBuf = maxNextCharInd = 0;
        nextCharInd = bufpos = -1;
        skipBOM();
    }

    public void ReInit(java.io.Reader dstream,
            int startline, int startcolumn) {
        ReInit(dstream, startline, startcolumn, 4096);
    }

    public UCode_UCodeESC_CharStream(java.io.InputStream dstream, int startline,
            int startcolumn, int buffersize) {
        this(new java.io.InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public UCode_UCodeESC_CharStream(java.io.InputStream dstream, int startline,
            int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(java.io.InputStream dstream, int startline,
            int startcolumn, int buffersize) {
        ReInit(new java.io.InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public void ReInit(java.io.InputStream dstream, int startline,
            int startcolumn) {
        ReInit(dstream, startline, startcolumn, 4096);
    }
    static private final char BOM = '\ufeff';

    private void skipBOM() {
        try {
            if (PeekChar() == BOM) {
                ReadChar();
            }
        } catch (EOFException e) {
        }
    }

    public final String GetImage() {
        if (bufpos >= tokenBegin) {
            return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
        } else {
            return new String(buffer, tokenBegin, bufsize - tokenBegin)
                    + new String(buffer, 0, bufpos + 1);
        }
    }

    public final char[] GetSuffix(int len) {
        char[] ret = new char[len];

        if ((bufpos + 1) >= len) {
            System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
        } else {
            System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                    len - bufpos - 1);
            System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
        }

        return ret;
    }

    public void Done() {
        nextCharBuf = null;
        buffer = null;
        bufline = null;
        bufcolumn = null;
    }

    /**
     * Method to adjust line and column numbers for the start of a token.<BR>
     */
    public void adjustBeginLineColumn(int newLine, int newCol) {
        int start = tokenBegin;
        int len;

        if (bufpos >= tokenBegin) {
            len = bufpos - tokenBegin + inBuf + 1;
        } else {
            len = bufsize - tokenBegin + bufpos + 1 + inBuf;
        }

        int i = 0, j = 0, k = 0;
        int nextColDiff, columnDiff = 0;

        while (i < len
                && bufline[j = start % bufsize] == bufline[k = ++start % bufsize]) {
            bufline[j] = newLine;
            nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
            bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
            i++;
        }

        if (i < len) {
            bufline[j] = newLine++;
            bufcolumn[j] = newCol + columnDiff;

            while (i++ < len) {
                if (bufline[j = start % bufsize] != bufline[++start % bufsize]) {
                    bufline[j] = newLine++;
                } else {
                    bufline[j] = newLine;
                }
            }
        }

        line = bufline[j];
        column = bufcolumn[j];
    }
}
