package sun.security.krb5.internal;

import sun.security.krb5.PrincipalName;
import sun.security.krb5.EncryptedData;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Realm;
import sun.security.krb5.RealmException;
import sun.security.util.*;
import java.io.IOException;

public class ASRep extends KDCRep {

    public ASRep(
            PAData[] new_pAData,
            PrincipalName new_cname,
            Ticket new_ticket,
            EncryptedData new_encPart) throws IOException {
        super(new_pAData, new_cname, new_ticket,
                new_encPart, Krb5.KRB_AS_REP);
    }

    public ASRep(byte[] data) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(new DerValue(data));
    }

    public ASRep(DerValue encoding) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(encoding);
    }

    private void init(DerValue encoding) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(encoding, Krb5.KRB_AS_REP);
    }
}
