package sun.security.krb5;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.crypto.KeyUsage;
import sun.security.krb5.internal.crypto.EType;
import sun.security.util.*;
import java.io.IOException;
import java.util.Objects;
import javax.security.auth.kerberos.KeyTab;
import sun.security.jgss.krb5.Krb5Util;

/**
 * This class encapsulates a AS-REP message that the KDC sends to the
 * client.
 */
class KrbAsRep extends KrbKdcRep {

    private ASRep rep;  // The AS-REP message
    private Credentials creds;  // The Credentials provide by the AS-REP
                                // message, created by initiator after calling
                                // the decrypt() method

    private boolean DEBUG = Krb5.DEBUG;

    KrbAsRep(byte[] ibuf) throws
            KrbException, Asn1Exception, IOException {
        DerValue encoding = new DerValue(ibuf);
        try {
            rep = new ASRep(encoding);
        } catch (Asn1Exception e) {
            rep = null;
            KRBError err = new KRBError(encoding);
            String errStr = err.getErrorString();
            String eText = null; // pick up text sent by the server (if any)

            if (errStr != null && errStr.length() > 0) {
                if (errStr.charAt(errStr.length() - 1) == 0)
                    eText = errStr.substring(0, errStr.length() - 1);
                else
                    eText = errStr;
            }
            KrbException ke;
            if (eText == null) {
                // no text sent from server
                ke = new KrbException(err);
            } else {
                if (DEBUG) {
                    System.out.println("KRBError received: " + eText);
                }
                // override default text with server text
                ke = new KrbException(err, eText);
            }
            ke.initCause(e);
            throw ke;
        }
    }

    // KrbAsReqBuilder need to read back the PA for key generation
    PAData[] getPA() {
        return rep.pAData;
    }

    /**
     * Called by KrbAsReqBuilder to resolve a AS-REP message using a keytab.
     * @param ktab the keytab, not null
     * @param asReq the original AS-REQ sent, used to validate AS-REP
     * @param cname the user principal name, used to locate keys in ktab
     */
    void decryptUsingKeyTab(KeyTab ktab, KrbAsReq asReq, PrincipalName cname)
            throws KrbException, Asn1Exception, IOException {
        EncryptionKey dkey = null;
        int encPartKeyType = rep.encPart.getEType();
        Integer encPartKvno = rep.encPart.kvno;
            try {
                dkey = EncryptionKey.findKey(encPartKeyType, encPartKvno,
                        Krb5Util.keysFromJavaxKeyTab(ktab, cname));
            } catch (KrbException ke) {
                if (ke.returnCode() == Krb5.KRB_AP_ERR_BADKEYVER) {
                    // Fallback to no kvno. In some cases, keytab is generated
                    // not by sysadmin but Java's ktab command
                    dkey = EncryptionKey.findKey(encPartKeyType,
                            Krb5Util.keysFromJavaxKeyTab(ktab, cname));
                }
            }
            if (dkey == null) {
                throw new KrbException(Krb5.API_INVALID_ARG,
                    "Cannot find key for type/kvno to decrypt AS REP - " +
                    EType.toString(encPartKeyType) + "/" + encPartKvno);
            }
        decrypt(dkey, asReq);
    }

    /**
     * Called by KrbAsReqBuilder to resolve a AS-REP message using a password.
     * @param password user provided password. not null
     * @param asReq the original AS-REQ sent, used to validate AS-REP
     * @param cname the user principal name, used to provide salt
     */
    void decryptUsingPassword(char[] password,
            KrbAsReq asReq, PrincipalName cname)
            throws KrbException, Asn1Exception, IOException {
        int encPartKeyType = rep.encPart.getEType();
        EncryptionKey dkey = EncryptionKey.acquireSecretKey(
                cname,
                password,
                encPartKeyType,
                PAData.getSaltAndParams(encPartKeyType, rep.pAData));
        decrypt(dkey, asReq);
    }

    /**
     * Decrypts encrypted content inside AS-REP. Called by initiator.
     * @param dkey the decryption key to use
     * @param asReq the original AS-REQ sent, used to validate AS-REP
     */
    private void decrypt(EncryptionKey dkey, KrbAsReq asReq)
            throws KrbException, Asn1Exception, IOException {
        byte[] enc_as_rep_bytes = rep.encPart.decrypt(dkey,
            KeyUsage.KU_ENC_AS_REP_PART);
        byte[] enc_as_rep_part = rep.encPart.reset(enc_as_rep_bytes);

        DerValue encoding = new DerValue(enc_as_rep_part);
        EncASRepPart enc_part = new EncASRepPart(encoding);
        rep.encKDCRepPart = enc_part;

        ASReq req = asReq.getMessage();
        check(true, req, rep);

        creds = new Credentials(
                                rep.ticket,
                                req.reqBody.cname,
                                rep.ticket.sname,
                                enc_part.key,
                                enc_part.flags,
                                enc_part.authtime,
                                enc_part.starttime,
                                enc_part.endtime,
                                enc_part.renewTill,
                                enc_part.caddr);
        if (DEBUG) {
            System.out.println(">>> KrbAsRep cons in KrbAsReq.getReply " +
                               req.reqBody.cname.getNameString());
        }
    }

    Credentials getCreds() {
        return Objects.requireNonNull(creds, "Creds not available yet.");
    }

    sun.security.krb5.internal.ccache.Credentials getCCreds() {
        return new sun.security.krb5.internal.ccache.Credentials(rep);
    }
}
