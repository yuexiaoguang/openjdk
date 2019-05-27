package sun.security.krb5;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.crypto.KeyUsage;
import java.io.IOException;
import sun.security.util.DerValue;

/**
 * This class encapsulates the KRB-CRED message that a client uses to
 * send its delegated credentials to a server.
 *
 * Supports delegation of one ticket only.
 */
public class KrbCred {

    private static boolean DEBUG = Krb5.DEBUG;

    private byte[] obuf = null;
    private KRBCred credMessg = null;
    private Ticket ticket = null;
    private EncKrbCredPart encPart = null;
    private Credentials creds = null;
    private KerberosTime timeStamp = null;

         // Used in InitialToken with null key
    public KrbCred(Credentials tgt,
                   Credentials serviceTicket,
                   EncryptionKey key)
        throws KrbException, IOException {

        PrincipalName client = tgt.getClient();
        PrincipalName tgService = tgt.getServer();
        PrincipalName server = serviceTicket.getServer();
        if (!serviceTicket.getClient().equals(client))
            throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                "Client principal does not match");

        // XXX Check Windows flag OK-TO-FORWARD-TO

        // Invoke TGS-REQ to get a forwarded TGT for the peer

        KDCOptions options = new KDCOptions();
        options.set(KDCOptions.FORWARDED, true);
        options.set(KDCOptions.FORWARDABLE, true);

        HostAddresses sAddrs = null;
        // XXX Also NT_GSS_KRB5_PRINCIPAL can be a host based principal
        // GSSName.NT_HOSTBASED_SERVICE should display with KRB_NT_SRV_HST
        if (server.getNameType() == PrincipalName.KRB_NT_SRV_HST)
            sAddrs=  new HostAddresses(server);

        KrbTgsReq tgsReq = new KrbTgsReq(options, tgt, tgService,
                                         null, null, null, null, sAddrs, null, null, null);
        credMessg = createMessage(tgsReq.sendAndGetCreds(), key);

        obuf = credMessg.asn1Encode();
    }

    KRBCred createMessage(Credentials delegatedCreds, EncryptionKey key)
        throws KrbException, IOException {

        EncryptionKey sessionKey
            = delegatedCreds.getSessionKey();
        PrincipalName princ = delegatedCreds.getClient();
        Realm realm = princ.getRealm();
        PrincipalName tgService = delegatedCreds.getServer();

        KrbCredInfo credInfo = new KrbCredInfo(sessionKey,
                                               princ, delegatedCreds.flags, delegatedCreds.authTime,
                                               delegatedCreds.startTime, delegatedCreds.endTime,
                                               delegatedCreds.renewTill, tgService,
                                               delegatedCreds.cAddr);

        timeStamp = KerberosTime.now();
        KrbCredInfo[] credInfos = {credInfo};
        EncKrbCredPart encPart =
            new EncKrbCredPart(credInfos,
                               timeStamp, null, null, null, null);

        EncryptedData encEncPart = new EncryptedData(key,
            encPart.asn1Encode(), KeyUsage.KU_ENC_KRB_CRED_PART);

        Ticket[] tickets = {delegatedCreds.ticket};

        credMessg = new KRBCred(tickets, encEncPart);

        return credMessg;
    }

    // Used in InitialToken, NULL_KEY might be used
    public KrbCred(byte[] asn1Message, EncryptionKey key)
        throws KrbException, IOException {

        credMessg = new KRBCred(asn1Message);

        ticket = credMessg.tickets[0];

        if (credMessg.encPart.getEType() == 0) {
            key = EncryptionKey.NULL_KEY;
        }
        byte[] temp = credMessg.encPart.decrypt(key,
            KeyUsage.KU_ENC_KRB_CRED_PART);
        byte[] plainText = credMessg.encPart.reset(temp);
        DerValue encoding = new DerValue(plainText);
        EncKrbCredPart encPart = new EncKrbCredPart(encoding);

        timeStamp = encPart.timeStamp;

        KrbCredInfo credInfo = encPart.ticketInfo[0];
        EncryptionKey credInfoKey = credInfo.key;
        PrincipalName pname = credInfo.pname;
        TicketFlags flags = credInfo.flags;
        KerberosTime authtime = credInfo.authtime;
        KerberosTime starttime = credInfo.starttime;
        KerberosTime endtime = credInfo.endtime;
        KerberosTime renewTill = credInfo.renewTill;
        PrincipalName sname = credInfo.sname;
        HostAddresses caddr = credInfo.caddr;

        if (DEBUG) {
            System.out.println(">>>Delegated Creds have pname=" + pname
                               + " sname=" + sname
                               + " authtime=" + authtime
                               + " starttime=" + starttime
                               + " endtime=" + endtime
                               + "renewTill=" + renewTill);
        }
        creds = new Credentials(ticket, pname, sname, credInfoKey,
                                flags, authtime, starttime, endtime, renewTill, caddr);
    }

    /**
     * Returns the delegated credentials from the peer.
     */
    public Credentials[] getDelegatedCreds() {

        Credentials[] allCreds = {creds};
        return allCreds;
    }

    /**
     * Returns the ASN.1 encoding that should be sent to the peer.
     */
    public byte[] getMessage() {
        return obuf;
    }
}
