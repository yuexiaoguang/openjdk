package com.sun.org.apache.xml.internal.utils;

import java.util.ArrayList;

import com.sun.org.apache.xml.internal.res.XMLErrorResources;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;


/**
 * Pool of object of a given type to pick from to help memory usage
 */
public class ObjectPool implements java.io.Serializable
{
    static final long serialVersionUID = -8519013691660936643L;

  /** Type of objects in this pool.
   *  @serial          */
  private final Class objectType;

  /** Stack of given objects this points to.
   *  @serial          */
  private final ArrayList freeStack;

  /**
   * Constructor ObjectPool
   *
   * @param type Type of objects for this pool
   */
  public ObjectPool(Class type)
  {
    objectType = type;
    freeStack = new ArrayList();
  }

  /**
   * Constructor ObjectPool
   *
   * @param className Fully qualified name of the type of objects for this pool.
   */
  public ObjectPool(String className)
  {
    try
    {
      objectType = ObjectFactory.findProviderClass(className, true);
    }
    catch(ClassNotFoundException cnfe)
    {
      throw new WrappedRuntimeException(cnfe);
    }
    freeStack = new ArrayList();
  }


  /**
   * Constructor ObjectPool
   *
   *
   * @param type Type of objects for this pool
   * @param size Size of vector to allocate
   */
  public ObjectPool(Class type, int size)
  {
    objectType = type;
    freeStack = new ArrayList(size);
  }

  /**
   * Constructor ObjectPool
   *
   */
  public ObjectPool()
  {
    objectType = null;
    freeStack = new ArrayList();
  }

  /**
   * Get an instance of the given object in this pool if available
   *
   *
   * @return an instance of the given object if available or null
   */
  public synchronized Object getInstanceIfFree()
  {

    // Check if the pool is empty.
    if (!freeStack.isEmpty())
    {

      // Remove object from end of free pool.
      Object result = freeStack.remove(freeStack.size() - 1);
      return result;
    }

    return null;
  }

  /**
   * Get an instance of the given object in this pool
   *
   *
   * @return An instance of the given object
   */
  public synchronized Object getInstance()
  {

    // Check if the pool is empty.
    if (freeStack.isEmpty())
    {

      // Create a new object if so.
      try
      {
        return objectType.newInstance();
      }
      catch (InstantiationException ex){}
      catch (IllegalAccessException ex){}

      // Throw unchecked exception for error in pool configuration.
      throw new RuntimeException(XMLMessages.createXMLMessage(XMLErrorResources.ER_EXCEPTION_CREATING_POOL, null)); //"exception creating new instance for pool");
    }
    else
    {

      // Remove object from end of free pool.
      Object result = freeStack.remove(freeStack.size() - 1);
      return result;
    }
  }

  /**
   * Add an instance of the given object to the pool
   *
   *
   * @param obj Object to add.
   */
  public synchronized void freeInstance(Object obj)
  {

    // Make sure the object is of the correct type.
    // Remove safety.  -sb
    // if (objectType.isInstance(obj))
    // {
    freeStack.add(obj);
    // }
    // else
    // {
    //  throw new IllegalArgumentException("argument type invalid for pool");
    // }
  }
}
