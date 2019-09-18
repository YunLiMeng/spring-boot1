package com.example.demo.base;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ author: limeng
 * @ date: Created in 2019/9/2 19:54
 * @ description：自定义check注解
 */
@Target({ ElementType.FIELD})
//只允许用在类的字段上
@Retention(RetentionPolicy.RUNTIME)
//注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
//@Constraint(validatedBy = ParamConstraintValidated.class)
public @interface Check {

}
