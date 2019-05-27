#ifndef NETWORK_INTERFACE_H
#define NETWORK_INTERFACE_H

#include <iphlpapi.h>
#include "net_util.h"

/*
 * Structures used when enumerating interfaces and addresses
 */
typedef struct _netaddr  {
    SOCKETADDRESS    addr;                  /* IPv4 or IPv6 address */
    SOCKETADDRESS    brdcast;
    short            mask;
    struct _netaddr *next;
} netaddr;

typedef struct _netif {
    char *name;
    char *displayName;
    DWORD dwIndex;              /* Internal index */
    DWORD ifType;               /* Interface type */
    int index;                  /* Friendly index */
    struct _netif *next;

    /* Following fields used on Windows XP when IPv6 is used only */
    jboolean hasIpv6Address;    /* true when following fields valid */
    jboolean dNameIsUnicode;    /* Display Name is Unicode */
    int naddrs;                 /* Number of addrs */
    DWORD ipv6Index;
    struct _netaddr *addrs;     /* addr list for interfaces */
} netif;

extern void free_netif(netif *netifP);
extern void free_netaddr(netaddr *netaddrP);

/* various JNI ids */
extern jclass ni_class;             /* NetworkInterface */

extern jmethodID ni_ctor;           /* NetworkInterface() */

extern jfieldID ni_indexID;         /* NetworkInterface.index */
extern jfieldID ni_addrsID;         /* NetworkInterface.addrs */
extern jfieldID ni_bindsID;         /* NetworkInterface.bindings */
extern jfieldID ni_nameID;          /* NetworkInterface.name */
extern jfieldID ni_displayNameID;   /* NetworkInterface.displayName */
extern jfieldID ni_childsID;        /* NetworkInterface.childs */

extern jclass ni_iacls;             /* InetAddress */

extern jclass ni_ia4cls;            /* Inet4Address */
extern jmethodID ni_ia4Ctor;        /* Inet4Address() */

extern jclass ni_ia6cls;            /* Inet6Address */
extern jmethodID ni_ia6ctrID;       /* Inet6Address() */
extern jfieldID ni_ia6ipaddressID;
extern jfieldID ni_ia6ipaddressID;

extern jclass ni_ibcls;             /* InterfaceAddress */
extern jmethodID ni_ibctrID;        /* InterfaceAddress() */
extern jfieldID ni_ibaddressID;     /* InterfaceAddress.address */
extern jfieldID ni_ibbroadcastID;   /* InterfaceAddress.broadcast */
extern jfieldID ni_ibmaskID;        /* InterfaceAddress.maskLength */

int enumInterfaces(JNIEnv *env, netif **netifPP);

// Windows Visa (and later) only.....
#ifndef IF_TYPE_IEEE80211
#define IF_TYPE_IEEE80211     71
#endif

#endif
