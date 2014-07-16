package net.epoxide.mpc.asm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Provides static factory methods for the ASM Tree-API
 * 
 * @author Ghostrec35
 **/

public class ASMHelper
{
    /**
     * Creates a ClassNode using a given byte array supplied by the Class
     * Transformer
     * 
     * @param bytes The byte array containing the Class Representation granted by the Class Transformer
     **/
    public static ClassNode createClassNode(byte[] bytes)
    {
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        return cn;
    }

    /**
     * Constructs a ClassNode given the classpath
     * 
     * @param classpath
     *            Classpath for the class the constructed ClassNode sould
     *            contain.
     **/
    public static ClassNode createClassNode(String classpath)
    {
        try
        {
            ClassReader cr = new ClassReader(ASMHelper.class.getResourceAsStream(classpath));
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);
            return cn;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the proper MethodNode for the given method name from the
     * ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the Class in which the method
     *            is being searched for
     * @param methodName
     *            The name of the method for the retrieved MethodNode
     **/
    public static MethodNode getMethodNode(ClassNode cn, String methodName)
    {
        for (MethodNode method : cn.methods)
        {
            if (method.name.equals(methodName)) return method;
        }
        return null;
    }

    /**
     * Retrieves the proper FieldNode for the given field name from the
     * ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the Class in which the method
     *            is being searched for
     * @param fieldName
     *            The name of the field for the retrieved FieldNode
     **/
    public static FieldNode getFieldNode(ClassNode cn, String fieldName)
    {
        for (FieldNode field : cn.fields)
        {
            if (field.name.equals(fieldName)) return field;
        }
        return null;
    }

    /**
     * Retrieves the proper AnnotationNode for the given field name from the
     * ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the Class in which the method
     *            is being searched for
     * @param annotationName
     *            The name of the annotation for the retrieved AnnotationNode
     **/
    public static AnnotationNode getAnnotationNode(ClassNode cn, String annotationName)
    {
        for (AnnotationNode annotation : cn.visibleAnnotations)
        {
            if (annotation.getClass().getSimpleName().equals(annotationName))
            {
                return annotation;
            }
        }

        for (AnnotationNode annotation : cn.invisibleAnnotations)
        {
            if (annotation.getClass().getSimpleName().equals(annotationName))
            {
                return annotation;
            }
        }
        return null;
    }

    /**
     * Retrieves the proper InnerClassNode for the given inner class name from
     * the ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the Class in which the method
     *            is being searched for
     * @param innerClassName
     *            The name of the Inner Class for the retrieved InnerClassNode
     **/
    public static InnerClassNode getInnerClassNode(ClassNode cn, String innerClassName)
    {
        for (InnerClassNode innerClass : cn.innerClasses)
        {
            if (innerClass.name.equals(innerClassName))
            {
                return innerClass;
            }
        }
        return null;
    }

    /**
     * Constructs the proper ClassNode for the given enclosing class name from
     * the ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the enclosing class in which
     *            the class is being searched for
     **/
    public static ClassNode getOuterClassNode(ClassNode cn)
    {
        // If there is no enclosing class
        if (cn.outerClass == null) return null;

        try
        {
            // Check via exception. Construct new ClassNode for OuterClass
            Class<?> outerClass = Class.forName(cn.outerClass);
            ClassReader cr = new ClassReader(ASMHelper.class.getResourceAsStream(cn.outerClass));
            ClassNode outerCN = new ClassNode();
            cr.accept(outerCN, 0);
            return outerCN;
        }
        catch (ClassNotFoundException e)
        {
            // Discard, issue with given outer class.
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Constructs the proper ClassNode for the given enclosing class name from
     * the ClassNode
     * 
     * @param cn
     *            The ClassNode representation of the super class of which the
     *            current ClassNode represents
     **/
    public static ClassNode getSuperClassNode(ClassNode cn)
    {
        // If there is no enclosing class
        if (cn.superName == null) return null;

        try
        {
            // Check via exception. Construct new ClassNode for OuterClass
            Class<?> outerClass = Class.forName(cn.superName);
            ClassReader cr = new ClassReader(ASMHelper.class.getResourceAsStream(cn.superName));
            ClassNode outerCN = new ClassNode();
            cr.accept(outerCN, 0);
            return outerCN;
        }
        catch (ClassNotFoundException e)
        {
            // Discard, issue with given superclass.
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkClassSignature(ClassNode cn, Class<?>[] signature)
    {
        // TODO Actually complete this method
        boolean isMatch = true;
        String[] classnames = cn.signature.split(":");
        Class<?>[] clazzes = getClassesFromPaths(classnames);
        if (clazzes.length == signature.length)
        {
            for (int i = 0; i < signature.length; i++)
            {
                if (!clazzes[i].equals(signature[i]))
                {
                    isMatch = false;
                }
            }
        }
        return isMatch;
    }

    /**
     * Retrieves the Class object representations of the given String array
     * 
     * @param classpaths
     *            The classpaths for the Class object array
     **/
    private static Class<?>[] getClassesFromPaths(String[] classpaths)
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (String classPath : classpaths)
        {
            try
            {
                classes.add(Class.forName(classPath));
            }
            catch (ClassNotFoundException e)
            {
                // Discard Silently.... Let the signature test fail.
            }
        }
        return classes.toArray(new Class<?>[classes.size()]);
    }

    /**
     * Injects a hook into the class bytes using the given information
     * Invoked by {@link MPCClassTransformer#transform(String, String, byte[])}
     * 
     * @param originalClassBytes
     *            The byte array representation of the class being transformed.
     * @param visitor
     *            The MethodVisitor that performs the hook injection.
     * @param methodName
     *            The method name the hook injection is to occur in.
     * @param methodDesc
     *            The description of the method signature to be injected in.
     * @param classPath
     *            The class path for the class to inject the hook in.
     **/
    public static byte[] injectHook(byte[] originalClassBytes, BaseMethodVisitor visitor, String methodName, String methodDesc, String classPath)
    {
        // TODO Actually complete this method
        ClassReader cr = new ClassReader(originalClassBytes);
        ClassWriter cv = new ClassWriter(0);
        cr.accept(cv, 0);
        return cv.toByteArray();
    }
}