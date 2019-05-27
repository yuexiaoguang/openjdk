package sun.security.ssl;

import java.security.SecureRandom;

/**
 * Encapsulates an SSL session ID.  SSL Session IDs are not reused by
 * servers during the lifetime of any sessions it created.  Sessions may
 * be used by many connections, either concurrently (for example, two
 * connections to a web server at the same time) or sequentially (over as
 * long a time period as is allowed by a given server).
 */
final
class SessionId
{
    private byte sessionId [];          // max 32 bytes

    /** Constructs a new session ID ... perhaps for a rejoinable session */
    SessionId (boolean isRejoinable, SecureRandom generator)
    {
        if (isRejoinable)
            // this will be unique, it's a timestamp plus much randomness
            sessionId = new RandomCookie (generator).random_bytes;
        else
            sessionId = new byte [0];
    }

    /** Constructs a session ID from a byte array (max size 32 bytes) */
    SessionId (byte sessionId [])
        { this.sessionId = sessionId; }

    /** Returns the length of the ID, in bytes */
    int length ()
        { return sessionId.length; }

    /** Returns the bytes in the ID.  May be an empty array.  */
    byte [] getId ()
    {
        return sessionId.clone ();
    }

    /** Returns the ID as a string */
    @Override
    public String toString ()
    {
        int             len = sessionId.length;
        StringBuffer    s = new StringBuffer (10 + 2 * len);

        s.append ("{");
        for (int i = 0; i < len; i++) {
            s.append (0x0ff & sessionId [i]);
            if (i != (len - 1))
                s.append (", ");
        }
        s.append ("}");
        return s.toString ();
    }


    /** Returns a value which is the same for session IDs which are equal */
    @Override
    public int hashCode ()
    {
        int     retval = 0;

        for (int i = 0; i < sessionId.length; i++)
            retval += sessionId [i];
        return retval;
    }

    /** Returns true if the parameter is the same session ID */
    @Override
    public boolean equals (Object obj)
    {
        if (!(obj instanceof SessionId))
            return false;

        SessionId s = (SessionId) obj;
        byte b [] = s.getId ();

        if (b.length != sessionId.length)
            return false;
        for (int i = 0; i < sessionId.length; i++) {
            if (b [i] != sessionId [i])
                return false;
        }
        return true;
    }
}
