package org.omg.CORBA;


/**
* The Holder for <tt>LongLongSeq</tt>.  For more information on
* Holder files, see <a href="doc-files/generatedfiles.html#holder">
* "Generated Files: Holder Files"</a>.<P>
* org/omg/CORBA/LongLongSeqHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from streams.idl
* 13 May 1999 22:41:36 o'clock GMT+00:00
*/
public final class LongLongSeqHolder implements org.omg.CORBA.portable.Streamable
{
    public long value[] = null;

    public LongLongSeqHolder ()
    {
    }

    public LongLongSeqHolder (long[] initialValue)
    {
        value = initialValue;
    }

    public void _read (org.omg.CORBA.portable.InputStream i)
    {
        value = org.omg.CORBA.LongLongSeqHelper.read (i);
    }

    public void _write (org.omg.CORBA.portable.OutputStream o)
    {
        org.omg.CORBA.LongLongSeqHelper.write (o, value);
    }

    public org.omg.CORBA.TypeCode _type ()
    {
        return org.omg.CORBA.LongLongSeqHelper.type ();
    }

}
