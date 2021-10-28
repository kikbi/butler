package com.example.demo.el;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author Tan Ke
 * @date 2021/10/26
 */
public class SpELTests {



    @Test
    public void testParseSpEL() throws Exception {
        ExpressionParser ep = new SpelExpressionParser();
        EvaluationContext ec = new StandardEvaluationContext();
        ParameterNameDiscoverer pnd = new StandardReflectionParameterNameDiscoverer();

    }



}
