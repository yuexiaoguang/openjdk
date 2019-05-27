package com.sun.tools.corba.se.idl;

/**
 * To extend this compiler framework to generate something other than
 * the default, this factory interface must be implemented and the name
 * of it must be set in the main method (see idl.Compile).
 * <p>
 * The implementation of each method should be quite simple.  Take
 * createAttributeGen, for instance.  If the interface AttributeGen is
 * implemented by a class called MyAttributeGen, then createAttributeGen
 * will be the following:
 * <pre>
 * public AttributeGen createAttributeGen ()
 * {
 *   return new MyAttributeGen ();
 * }
 * </pre>
 * <p>
 * If it is desired that a generator do nothing, it is not necessary to
 * implement one which does nothing; you may simply write that particular
 * create method so that it returns null.
 * <p>
 * Note that this class MUST have a public default constructor (one which
 * takes no parameters).
 **/
public interface GenFactory
{
  public AttributeGen    createAttributeGen ();
  public ConstGen        createConstGen ();
  public EnumGen         createEnumGen ();
  public ExceptionGen    createExceptionGen ();
  public ForwardGen      createForwardGen ();
  public ForwardValueGen createForwardValueGen ();
  public IncludeGen      createIncludeGen ();
  public InterfaceGen    createInterfaceGen ();
  public ValueGen        createValueGen ();
  public ValueBoxGen     createValueBoxGen ();
  public MethodGen       createMethodGen ();
  public ModuleGen       createModuleGen ();
  public NativeGen       createNativeGen ();
  public ParameterGen    createParameterGen ();
  public PragmaGen       createPragmaGen ();
  public PrimitiveGen    createPrimitiveGen ();
  public SequenceGen     createSequenceGen ();
  public StringGen       createStringGen ();
  public StructGen       createStructGen ();
  public TypedefGen      createTypedefGen ();
  public UnionGen        createUnionGen ();
} // interface GenFactory
