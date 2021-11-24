package com.example.demo.lambda;

import com.example.demo.issue.entity.Issue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import sun.reflect.generics.parser.SignatureParser;
import sun.reflect.generics.tree.FormalTypeParameter;
import sun.reflect.generics.tree.MethodTypeSignature;
import sun.reflect.generics.tree.TypeSignature;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */

public class LambdaTests {
    private ObjectMapper om = new ObjectMapper();

    @Test
    public void testSerializeLambda() throws Exception {
        SimpleChain<Issue> simpleChain = SimpleChain.of(Issue::getAssignTo);
        List<SerializableFunction<Issue, ?>> funcList = simpleChain.getFuncList();
        SerializableFunction<Issue, ?> sf = funcList.get(0);
        Method writeReplace = sf.getClass().getDeclaredMethod("writeReplace");
        boolean accessible = writeReplace.isAccessible();
        System.out.println("可访问："+accessible);
        writeReplace.setAccessible(true);

        SerializedLambda slambda = (SerializedLambda)writeReplace.invoke(sf);
        System.out.println(om.writeValueAsString(slambda));
        System.out.println(slambda);
        Class<?> scl = slambda.getClass();
        String implMethodSignature = slambda.getImplMethodSignature();
        String implMethodName = slambda.getImplMethodName();
        System.out.println(implMethodName);
        System.out.println(implMethodSignature);
//        soutInfo(scl);
//        parseMethodSig(slambda);
//        System.out.println(writeReplace);
    }

    private void parseMethodSig(SerializedLambda slambda) {
        System.out.println("解析方法签名：");
        String signature = slambda.getImplMethodSignature();
        MethodTypeSignature methodSig = SignatureParser.make().parseMethodSig(signature);
        TypeSignature[] parameterTypes = methodSig.getParameterTypes();
        for (TypeSignature parameterType : parameterTypes) {
            System.out.println(parameterType.getClass());
        }
        System.out.println("正式参数：");
        FormalTypeParameter[] formalTypeParameters = methodSig.getFormalTypeParameters();
        for (FormalTypeParameter formalTypeParameter : formalTypeParameters) {

            System.out.println(formalTypeParameter.getName());
            System.out.println(formalTypeParameter.getClass());
        }
    }

    private void soutInfo(Class<?> scl) {
        String name = scl.getName();
        System.out.println("名字："+name);
        System.out.println("是否虚拟机生成："+ scl.isSynthetic());
        System.out.println("方法：");
        Method[] declaredMethods = scl.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("成员变量：");
        Field[] declaredFields = scl.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    @Test
    public void testReflect() throws Exception {
        LambdaMeta<Issue> lambdaMeta = LambdaMeta.of(Issue::getAssignTo);
        Class<? extends Issue> targetClass = lambdaMeta.getTargetClass();
        Method referenceMethod = lambdaMeta.getReferenceMethod();
        System.out.println(targetClass);
        System.out.println(referenceMethod);
        Signature signature = ReflectUtils.getSignature(referenceMethod);

        System.out.println(signature);
    }


}
