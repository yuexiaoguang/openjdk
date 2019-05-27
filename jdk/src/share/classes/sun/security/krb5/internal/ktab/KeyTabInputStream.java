package sun.security.krb5.internal.ktab;

import sun.security.krb5.internal.*;
import sun.security.krb5.PrincipalName;
import sun.security.krb5.Realm;
import sun.security.krb5.RealmException;
import sun.security.krb5.internal.util.KrbDataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class implements a buffered input stream. It is used for parsing key table
 * data to memory.
 */
public class KeyTabInputStream extends KrbDataInputStream implements KeyTabConstants {

    boolean DEBUG = Krb5.DEBUG;
    int index;

    public KeyTabInputStream(InputStream is) {
        super(is);
    }
    /**
     * Reads the number of bytes this entry data occupy.
     */
    int readEntryLength() throws IOException {
        return read(4);
    }


    KeyTabEntry readEntry(int entryLen, int ktVersion) throws IOException, RealmException {
        index = entryLen;
        if (index == 0) {    //in native implementation, when the last entry is deleted, a byte 0 is left.
            return null;
        }
        if (index < 0) {    //in native implementation, when one of the entries is deleted, the entry length turns to be negative, and
            skip(Math.abs(index));                //the fields are left with 0 bytes
            return null;
        }
        int principalNum = read(2);     //the number of service names.
        index -= 2;
        if (ktVersion == KRB5_KT_VNO_1) {   //V1 includes realm in the count.
            principalNum -= 1;
        }
        Realm realm = new Realm(readName());
        String[] nameParts = new String[principalNum];
        for (int i = 0; i < principalNum; i++) {
            nameParts[i] = readName();
        }
        int nameType = read(4);
        index -= 4;
        PrincipalName service = new PrincipalName(nameType, nameParts, realm);
        KerberosTime timeStamp = readTimeStamp();

        int keyVersion = read() & 0xff;
        index -= 1;
        int keyType = read(2);
        index -= 2;
        int keyLength = read(2);
        index -= 2;
        byte[] keyblock = readKey(keyLength);
        index -= keyLength;
        // There might be a 32 bit kvno here.
        // If index is zero, assume that the 8 bit key version number was
        // right, otherwise trust the new nonzero value.
        if (index >= 4) {
            int extKvno = read(4);
            if (extKvno != 0) {
                keyVersion = extKvno;
            }
            index -= 4;
        }

        // if index is negative, the keytab format must be wrong.
        if (index < 0) {
            throw new RealmException("Keytab is corrupted");
        }

        // ignore the left bytes.
        skip(index);

        return new KeyTabEntry(service, realm, timeStamp, keyVersion, keyType, keyblock);
    }

    byte[] readKey(int length) throws IOException {
        byte[] bytes = new byte[length];
        read(bytes, 0, length);
        return bytes;
    }

    KerberosTime readTimeStamp() throws IOException {
        index -= 4;
        return new KerberosTime((long)read(4) * 1000);
    }

    String readName() throws IOException {
        String name;
        int length = read(2);   //length of the realm name or service name
        index -= 2;
        byte[] bytes = new byte[length];
        read(bytes, 0, length);
        index -= length;
        name = new String(bytes);
        if (DEBUG) {
            System.out.println(">>> KeyTabInputStream, readName(): " + name);
        }
        return name;
    }
}
