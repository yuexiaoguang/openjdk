package com.sun.tools.corba.se.idl.toJavaPortable;

public class GenFactory implements com.sun.tools.corba.se.idl.GenFactory
{

  public com.sun.tools.corba.se.idl.AttributeGen createAttributeGen ()
  {
    if (Util.corbaLevel (2.4f, 99.0f)) // <d60023>
      return new AttributeGen24 ();
    else
      return new AttributeGen ();
  } // createAttributeGen

  public com.sun.tools.corba.se.idl.ConstGen createConstGen ()
  {
    return new ConstGen ();
  } // createConstGen

  public com.sun.tools.corba.se.idl.NativeGen createNativeGen ()
  {
    return new NativeGen ();
  } // createNativeGen

  public com.sun.tools.corba.se.idl.EnumGen createEnumGen ()
  {
    return new EnumGen ();
  } // createEnumGen

  public com.sun.tools.corba.se.idl.ExceptionGen createExceptionGen ()
  {
    return new ExceptionGen ();
  } // createExceptionGen

  public com.sun.tools.corba.se.idl.ForwardGen createForwardGen ()
  {
    return null;
  } // createForwardGen

  public com.sun.tools.corba.se.idl.ForwardValueGen createForwardValueGen ()
  {
    return null;
  } // createForwardValueGen

  public com.sun.tools.corba.se.idl.IncludeGen createIncludeGen ()
  {
    return null;
  } // createIncludeGen

  public com.sun.tools.corba.se.idl.InterfaceGen createInterfaceGen ()
  {
    return new InterfaceGen ();
  } // createInterfaceGen

  public com.sun.tools.corba.se.idl.ValueGen createValueGen ()
  {
    if (Util.corbaLevel (2.4f, 99.0f)) // <d60023>
      return new ValueGen24 ();
    else
      return new ValueGen ();
  } // createValueGen

  public com.sun.tools.corba.se.idl.ValueBoxGen createValueBoxGen ()
  {
    if (Util.corbaLevel (2.4f, 99.0f)) // <d60023>
      return new ValueBoxGen24 ();
    else
      return new ValueBoxGen ();
  } // createValueBoxGen

  public com.sun.tools.corba.se.idl.MethodGen createMethodGen ()
  {
    if (Util.corbaLevel (2.4f, 99.0f)) // <d60023>
      return new MethodGen24 ();
    else
      return new MethodGen ();
  } // createMethodGen

  public com.sun.tools.corba.se.idl.ModuleGen createModuleGen ()
  {
    return new ModuleGen ();
  } // createModuleGen

  public com.sun.tools.corba.se.idl.ParameterGen createParameterGen ()
  {
    return null;
  } // createParameterGen

  public com.sun.tools.corba.se.idl.PragmaGen createPragmaGen ()
  {
    return null;
  } // createPragmaGen

  public com.sun.tools.corba.se.idl.PrimitiveGen createPrimitiveGen ()
  {
    return new PrimitiveGen ();
  } // createPrimitiveGen

  public com.sun.tools.corba.se.idl.SequenceGen createSequenceGen ()
  {
    return new SequenceGen ();
  } // createSequenceGen

  public com.sun.tools.corba.se.idl.StringGen createStringGen ()
  {
    return new StringGen ();
  } // createSequenceGen

  public com.sun.tools.corba.se.idl.StructGen createStructGen ()
  {
    return new StructGen ();
  } // createStructGen

  public com.sun.tools.corba.se.idl.TypedefGen createTypedefGen ()
  {
    return new TypedefGen ();
  } // createTypedefGen

  public com.sun.tools.corba.se.idl.UnionGen createUnionGen ()
  {
    return new UnionGen ();
  } // createUnionGen
} // class GenFactory
