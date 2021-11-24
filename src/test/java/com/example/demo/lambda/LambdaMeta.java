package com.example.demo.lambda;

import org.springframework.cglib.core.ReflectUtils;

import java.io.*;
import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
public class LambdaMeta<T> {

    private SerializedLambda serializedLambda;

    private Class<T> targetClass;

    private Method method;

    public LambdaMeta(SerializedLambda serializedLambda) {
        this.serializedLambda = serializedLambda;
    }

    public static <T> LambdaMeta<T> of(SerializableFunction<T, ?> sf) {
        SerializedLambda serializedLambda;
        try {
            Method writeReplace = sf.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            serializedLambda = (SerializedLambda) writeReplace.invoke(sf);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            serializedLambda = serializeLambda(sf);
        }
        return new LambdaMeta<>(serializedLambda);
    }

    private static <T> SerializedLambda serializeLambda(SerializableFunction<T, ?> sf) throws RuntimeException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(sf);
            oos.flush();
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
                return (SerializedLambda) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取方法
     * @return
     */
    public Method getReferenceMethod() {
        if (method != null) {
            return method;
        }
        int implMethodKind = serializedLambda.getImplMethodKind();
        if (implMethodKind != MethodHandleInfo.REF_invokeVirtual){
            throw new RuntimeException("could only get the reference method from 'method reference' lambda , current method kind: " + MethodHandleInfo.referenceKindToString(implMethodKind));
        }
        String implMethodName = serializedLambda.getImplMethodName();
        String implClass = serializedLambda.getImplClass();
        String implMethodSignature = serializedLambda.getImplMethodSignature();
        method = ReflectUtils.findMethod(implClass.replace("/", ".")+"." + implMethodName + implMethodSignature);
        return method;
    }

    /**
     * 获取目标类
     * @return
     */
    @SuppressWarnings("unchecked")
    public Class<T> getTargetClass(){
        if (targetClass != null) {
            return targetClass;
        }
        try {
            return targetClass = (Class<T>) Class.forName(serializedLambda.getImplClass().replace("/","."));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
