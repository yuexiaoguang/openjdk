package com.sun.tools.internal.ws.processor.model.java;

import com.sun.tools.internal.ws.processor.model.ModelException;

import java.util.*;

public class JavaStructureType extends JavaType {

    public JavaStructureType() {}

    public JavaStructureType(String name, boolean present, Object owner) {
        super(name, present, "null");
        this.owner = owner;
    }

    public void add(JavaStructureMember m) {
        if (membersByName.containsKey(m.getName())) {
            throw new ModelException("model.uniqueness.javastructuretype",
                new Object[] {m.getName(), getRealName()});
        }
        members.add(m);
        membersByName.put(m.getName(), m);
    }


    public JavaStructureMember getMemberByName(String name) {
        if (membersByName.size() != members.size()) {
            initializeMembersByName();
        }
        return membersByName.get(name);
    }

    public Iterator getMembers() {
        return members.iterator();
    }

    public int getMembersCount() {
        return members.size();
    }

    /* serialization */
    public List<JavaStructureMember> getMembersList() {
        return members;
    }

    /* serialization */
    public void setMembersList(List<JavaStructureMember> l) {
        members = l;
    }

    private void initializeMembersByName() {
        membersByName = new HashMap<String, JavaStructureMember>();
        if (members != null) {
            for (JavaStructureMember m : members) {
                if (m.getName() != null &&
                    membersByName.containsKey(m.getName())) {

                    throw new ModelException("model.uniqueness");
                }
                membersByName.put(m.getName(), m);
            }
        }
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public JavaStructureType getSuperclass() {
        return superclass;
    }

    public void setSuperclass(JavaStructureType superclassType) {
        superclass = superclassType;
    }

    public void addSubclass(JavaStructureType subclassType) {
        subclasses.add(subclassType);
        subclassType.setSuperclass(this);
    }

    public Iterator getSubclasses() {
        if (subclasses == null || subclasses.size() == 0) {
            return null;
        }
        return subclasses.iterator();
    }

    public Set getSubclassesSet() {
        return subclasses;
    }

    /* serialization */
    public void setSubclassesSet(Set s) {
        subclasses = s;
        for (Iterator iter = s.iterator(); iter.hasNext();) {
            ((JavaStructureType) iter.next()).setSuperclass(this);
        }
    }

    public Iterator getAllSubclasses() {
        Set subs = getAllSubclassesSet();
        if (subs.size() == 0) {
            return null;
        }
        return subs.iterator();
    }

    public Set getAllSubclassesSet() {
        Set transitiveSet = new HashSet();
        Iterator subs = subclasses.iterator();
        while (subs.hasNext()) {
            transitiveSet.addAll(
                ((JavaStructureType)subs.next()).getAllSubclassesSet());
        }
        transitiveSet.addAll(subclasses);
        return transitiveSet;
    }

    public Object getOwner() {

        // usually a SOAPStructureType
        return owner;
    }

    public void setOwner(Object owner) {

        // usually a SOAPStructureType
        this.owner = owner;
    }

    private List<JavaStructureMember> members = new ArrayList();
    private Map<String, JavaStructureMember> membersByName = new HashMap();

    // known subclasses of this type
    private Set subclasses = new HashSet();
    private JavaStructureType superclass;

    // usually a SOAPStructureType
    private Object owner;
    private boolean isAbstract = false;
}
