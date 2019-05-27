package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;

// jmx imports
//
import javax.management.MBeanServer;
import com.sun.jmx.snmp.SnmpCounter;
import com.sun.jmx.snmp.SnmpCounter64;
import com.sun.jmx.snmp.SnmpGauge;
import com.sun.jmx.snmp.SnmpInt;
import com.sun.jmx.snmp.SnmpUnsignedInt;
import com.sun.jmx.snmp.SnmpIpAddress;
import com.sun.jmx.snmp.SnmpTimeticks;
import com.sun.jmx.snmp.SnmpOpaque;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpNull;
import com.sun.jmx.snmp.SnmpValue;
import com.sun.jmx.snmp.SnmpVarBind;
import com.sun.jmx.snmp.SnmpStatusException;

// jdmk imports
//
import com.sun.jmx.snmp.agent.SnmpMib;
import com.sun.jmx.snmp.agent.SnmpMibGroup;
import com.sun.jmx.snmp.agent.SnmpStandardObjectServer;
import com.sun.jmx.snmp.agent.SnmpStandardMetaServer;
import com.sun.jmx.snmp.agent.SnmpMibSubRequest;
import com.sun.jmx.snmp.agent.SnmpMibTable;
import com.sun.jmx.snmp.EnumRowStatus;
import com.sun.jmx.snmp.SnmpDefinitions;

/**
 * The class is used for representing SNMP metadata for the "JvmMemory" group.
 * The group is defined with the following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.2.
 */
public class JvmMemoryMeta extends SnmpMibGroup
     implements Serializable, SnmpStandardMetaServer {
    private static final long serialVersionUID = 9047644262627149214L;

    /**
     * Constructor for the metadata associated to "JvmMemory".
     */
    public JvmMemoryMeta(SnmpMib myMib, SnmpStandardObjectServer objserv) {
        objectserver = objserv;
        try {
            registerObject(120);
            registerObject(23);
            registerObject(22);
            registerObject(21);
            registerObject(110);
            registerObject(20);
            registerObject(13);
            registerObject(12);
            registerObject(3);
            registerObject(11);
            registerObject(2);
            registerObject(101);
            registerObject(10);
            registerObject(1);
            registerObject(100);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Get the value of a scalar variable
     */
    public SnmpValue get(long var, Object data)
        throws SnmpStatusException {
        switch((int)var) {
            case 120: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 23:
                return new SnmpCounter64(node.getJvmMemoryNonHeapMaxSize());

            case 22:
                return new SnmpCounter64(node.getJvmMemoryNonHeapCommitted());

            case 21:
                return new SnmpCounter64(node.getJvmMemoryNonHeapUsed());

            case 110: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 20:
                return new SnmpCounter64(node.getJvmMemoryNonHeapInitSize());

            case 13:
                return new SnmpCounter64(node.getJvmMemoryHeapMaxSize());

            case 12:
                return new SnmpCounter64(node.getJvmMemoryHeapCommitted());

            case 3:
                return new SnmpInt(node.getJvmMemoryGCCall());

            case 11:
                return new SnmpCounter64(node.getJvmMemoryHeapUsed());

            case 2:
                return new SnmpInt(node.getJvmMemoryGCVerboseLevel());

            case 101: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 10:
                return new SnmpCounter64(node.getJvmMemoryHeapInitSize());

            case 1:
                return new SnmpGauge(node.getJvmMemoryPendingFinalCount());

            case 100: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            default:
                break;
        }
        throw new SnmpStatusException(SnmpStatusException.noSuchObject);
    }

    /**
     * Set the value of a scalar variable
     */
    public SnmpValue set(SnmpValue x, long var, Object data)
        throws SnmpStatusException {
        switch((int)var) {
            case 120: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 23:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 22:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 21:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 110: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 20:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 13:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 12:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 3:
                if (x instanceof SnmpInt) {
                    try  {
                        node.setJvmMemoryGCCall( new EnumJvmMemoryGCCall (((SnmpInt)x).toInteger()));
                    } catch(IllegalArgumentException e)  {
                        throw new SnmpStatusException(SnmpStatusException.snmpRspWrongValue);
                    }
                    return new SnmpInt(node.getJvmMemoryGCCall());
                } else {
                    throw new SnmpStatusException(SnmpStatusException.snmpRspWrongType);
                }

            case 11:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 2:
                if (x instanceof SnmpInt) {
                    try  {
                        node.setJvmMemoryGCVerboseLevel( new EnumJvmMemoryGCVerboseLevel (((SnmpInt)x).toInteger()));
                    } catch(IllegalArgumentException e)  {
                        throw new SnmpStatusException(SnmpStatusException.snmpRspWrongValue);
                    }
                    return new SnmpInt(node.getJvmMemoryGCVerboseLevel());
                } else {
                    throw new SnmpStatusException(SnmpStatusException.snmpRspWrongType);
                }

            case 101: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 10:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 1:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 100: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            default:
                break;
        }
        throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
    }

    /**
     * Check the value of a scalar variable
     */
    public void check(SnmpValue x, long var, Object data)
        throws SnmpStatusException {
        switch((int) var) {
            case 120: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 23:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 22:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 21:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 110: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 20:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 13:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 12:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 3:
                if (x instanceof SnmpInt) {
                    try  {
                        node.checkJvmMemoryGCCall( new EnumJvmMemoryGCCall (((SnmpInt)x).toInteger()));
                    } catch(IllegalArgumentException e)  {
                        throw new SnmpStatusException(SnmpStatusException.snmpRspWrongValue);
                    }
                } else {
                    throw new SnmpStatusException(SnmpStatusException.snmpRspWrongType);
                }
                break;

            case 11:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 2:
                if (x instanceof SnmpInt) {
                    try  {
                        node.checkJvmMemoryGCVerboseLevel( new EnumJvmMemoryGCVerboseLevel (((SnmpInt)x).toInteger()));
                    } catch(IllegalArgumentException e)  {
                        throw new SnmpStatusException(SnmpStatusException.snmpRspWrongValue);
                    }
                } else {
                    throw new SnmpStatusException(SnmpStatusException.snmpRspWrongType);
                }
                break;

            case 101: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            case 10:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 1:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);

            case 100: {
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
                }

            default:
                throw new SnmpStatusException(SnmpStatusException.snmpRspNotWritable);
        }
    }

    /**
     * Allow to bind the metadata description to a specific object.
     */
    protected void setInstance(JvmMemoryMBean var) {
        node = var;
    }


    // ------------------------------------------------------------
    //
    // Implements the "get" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Javadoc API for more details.
    //
    // ------------------------------------------------------------

    public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStatusException {
        objectserver.get(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "set" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Javadoc API for more details.
    //
    // ------------------------------------------------------------

    public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStatusException {
        objectserver.set(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "check" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Javadoc API for more details.
    //
    // ------------------------------------------------------------

    public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStatusException {
        objectserver.check(this,req,depth);
    }

    /**
     * Returns true if "arc" identifies a scalar object.
     */
    public boolean isVariable(long arc) {

        switch((int)arc) {
            case 23:
            case 22:
            case 21:
            case 20:
            case 13:
            case 12:
            case 3:
            case 11:
            case 2:
            case 10:
            case 1:
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * Returns true if "arc" identifies a readable scalar object.
     */
    public boolean isReadable(long arc) {

        switch((int)arc) {
            case 23:
            case 22:
            case 21:
            case 20:
            case 13:
            case 12:
            case 3:
            case 11:
            case 2:
            case 10:
            case 1:
                return true;
            default:
                break;
        }
        return false;
    }


    // ------------------------------------------------------------
    //
    // Implements the "skipVariable" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Javadoc API for more details.
    //
    // ------------------------------------------------------------

    public boolean  skipVariable(long var, Object data, int pduVersion) {
        switch((int)var) {
            case 23:
            case 22:
            case 21:
            case 20:
            case 13:
            case 12:
            case 11:
            case 10:
                if (pduVersion==SnmpDefinitions.snmpVersionOne) return true;
                break;
            default:
                break;
        }
        return super.skipVariable(var,data,pduVersion);
    }

    /**
     * Return the name of the attribute corresponding to the SNMP variable identified by "id".
     */
    public String getAttributeName(long id)
        throws SnmpStatusException {
        switch((int)id) {
            case 120: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 23:
                return "JvmMemoryNonHeapMaxSize";

            case 22:
                return "JvmMemoryNonHeapCommitted";

            case 21:
                return "JvmMemoryNonHeapUsed";

            case 110: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 20:
                return "JvmMemoryNonHeapInitSize";

            case 13:
                return "JvmMemoryHeapMaxSize";

            case 12:
                return "JvmMemoryHeapCommitted";

            case 3:
                return "JvmMemoryGCCall";

            case 11:
                return "JvmMemoryHeapUsed";

            case 2:
                return "JvmMemoryGCVerboseLevel";

            case 101: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            case 10:
                return "JvmMemoryHeapInitSize";

            case 1:
                return "JvmMemoryPendingFinalCount";

            case 100: {
                throw new SnmpStatusException(SnmpStatusException.noSuchInstance);
                }

            default:
                break;
        }
        throw new SnmpStatusException(SnmpStatusException.noSuchObject);
    }

    /**
     * Returns true if "arc" identifies a table object.
     */
    public boolean isTable(long arc) {

        switch((int)arc) {
            case 120:
                return true;
            case 110:
                return true;
            case 101:
                return true;
            case 100:
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * Returns the table object identified by "arc".
     */
    public SnmpMibTable getTable(long arc) {

        switch((int)arc) {
            case 120:
                return tableJvmMemMgrPoolRelTable;
            case 110:
                return tableJvmMemPoolTable;
            case 101:
                return tableJvmMemGCTable;
            case 100:
                return tableJvmMemManagerTable;
        default:
            break;
        }
        return null;
    }

    /**
     * Register the group's SnmpMibTable objects with the meta-data.
     */
    public void registerTableNodes(SnmpMib mib, MBeanServer server) {
        tableJvmMemMgrPoolRelTable = createJvmMemMgrPoolRelTableMetaNode("JvmMemMgrPoolRelTable", "JvmMemory", mib, server);
        if ( tableJvmMemMgrPoolRelTable != null)  {
            tableJvmMemMgrPoolRelTable.registerEntryNode(mib,server);
            mib.registerTableMeta("JvmMemMgrPoolRelTable", tableJvmMemMgrPoolRelTable);
        }

        tableJvmMemPoolTable = createJvmMemPoolTableMetaNode("JvmMemPoolTable", "JvmMemory", mib, server);
        if ( tableJvmMemPoolTable != null)  {
            tableJvmMemPoolTable.registerEntryNode(mib,server);
            mib.registerTableMeta("JvmMemPoolTable", tableJvmMemPoolTable);
        }

        tableJvmMemGCTable = createJvmMemGCTableMetaNode("JvmMemGCTable", "JvmMemory", mib, server);
        if ( tableJvmMemGCTable != null)  {
            tableJvmMemGCTable.registerEntryNode(mib,server);
            mib.registerTableMeta("JvmMemGCTable", tableJvmMemGCTable);
        }

        tableJvmMemManagerTable = createJvmMemManagerTableMetaNode("JvmMemManagerTable", "JvmMemory", mib, server);
        if ( tableJvmMemManagerTable != null)  {
            tableJvmMemManagerTable.registerEntryNode(mib,server);
            mib.registerTableMeta("JvmMemManagerTable", tableJvmMemManagerTable);
        }

    }


    /**
     * Factory method for "JvmMemMgrPoolRelTable" table metadata class.
     *
     * You can redefine this method if you need to replace the default
     * generated metadata class with your own customized class.
     *
     * @param tableName Name of the table object ("JvmMemMgrPoolRelTable")
     * @param groupName Name of the group to which this table belong ("JvmMemory")
     * @param mib The SnmpMib object in which this table is registered
     * @param server MBeanServer for this table entries (may be null)
     *
     * @return An instance of the metadata class generated for the
     *         "JvmMemMgrPoolRelTable" table (JvmMemMgrPoolRelTableMeta)
     *
     **/
    protected JvmMemMgrPoolRelTableMeta createJvmMemMgrPoolRelTableMetaNode(String tableName, String groupName, SnmpMib mib, MBeanServer server)  {
        return new JvmMemMgrPoolRelTableMeta(mib, objectserver);
    }


    /**
     * Factory method for "JvmMemPoolTable" table metadata class.
     *
     * You can redefine this method if you need to replace the default
     * generated metadata class with your own customized class.
     *
     * @param tableName Name of the table object ("JvmMemPoolTable")
     * @param groupName Name of the group to which this table belong ("JvmMemory")
     * @param mib The SnmpMib object in which this table is registered
     * @param server MBeanServer for this table entries (may be null)
     *
     * @return An instance of the metadata class generated for the
     *         "JvmMemPoolTable" table (JvmMemPoolTableMeta)
     *
     **/
    protected JvmMemPoolTableMeta createJvmMemPoolTableMetaNode(String tableName, String groupName, SnmpMib mib, MBeanServer server)  {
        return new JvmMemPoolTableMeta(mib, objectserver);
    }


    /**
     * Factory method for "JvmMemGCTable" table metadata class.
     *
     * You can redefine this method if you need to replace the default
     * generated metadata class with your own customized class.
     *
     * @param tableName Name of the table object ("JvmMemGCTable")
     * @param groupName Name of the group to which this table belong ("JvmMemory")
     * @param mib The SnmpMib object in which this table is registered
     * @param server MBeanServer for this table entries (may be null)
     *
     * @return An instance of the metadata class generated for the
     *         "JvmMemGCTable" table (JvmMemGCTableMeta)
     *
     **/
    protected JvmMemGCTableMeta createJvmMemGCTableMetaNode(String tableName, String groupName, SnmpMib mib, MBeanServer server)  {
        return new JvmMemGCTableMeta(mib, objectserver);
    }


    /**
     * Factory method for "JvmMemManagerTable" table metadata class.
     *
     * You can redefine this method if you need to replace the default
     * generated metadata class with your own customized class.
     *
     * @param tableName Name of the table object ("JvmMemManagerTable")
     * @param groupName Name of the group to which this table belong ("JvmMemory")
     * @param mib The SnmpMib object in which this table is registered
     * @param server MBeanServer for this table entries (may be null)
     *
     * @return An instance of the metadata class generated for the
     *         "JvmMemManagerTable" table (JvmMemManagerTableMeta)
     *
     **/
    protected JvmMemManagerTableMeta createJvmMemManagerTableMetaNode(String tableName, String groupName, SnmpMib mib, MBeanServer server)  {
        return new JvmMemManagerTableMeta(mib, objectserver);
    }

    protected JvmMemoryMBean node;
    protected SnmpStandardObjectServer objectserver = null;
    protected JvmMemMgrPoolRelTableMeta tableJvmMemMgrPoolRelTable = null;
    protected JvmMemPoolTableMeta tableJvmMemPoolTable = null;
    protected JvmMemGCTableMeta tableJvmMemGCTable = null;
    protected JvmMemManagerTableMeta tableJvmMemManagerTable = null;
}
