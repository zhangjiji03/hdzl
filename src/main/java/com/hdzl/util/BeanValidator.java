package com.hdzl.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hdzl.error.BasicException;
import com.hdzl.result.ResultUtil;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;


/**
 * 类名称:        BeanUtil
 * 创建时间:      2020/9/29 10:46
 * 版本:          1.0
 * 说明:
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 校验方法
     * @param t 校验对象
     * @param groups XXX
     * @param <T> X
     * @return 返回错误字段和信息
     */
    public static <T>Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set result = validator.validate(t, groups);
        if(result.isEmpty()){
            return Collections.emptyMap();
        }else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = result.iterator();
            while (iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }

    /**
     * 校验集合
     * @param collection XX
     * @return Map
     */
    public static Map<String,String> validateList(Collection<?>collection){
        //校验collection是否为空
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map errors;
        do{
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }else{
                Object next = iterator.next();
                errors = validate(next,new Class[0]);
            }
        }while(errors.isEmpty());
        return errors;
    }

    /**
     * 通用的校验
     * @param first XX
     * @param objects XX
     * @return Map
     */
    public static Map<String,String>validateObject(Object first,Object... objects){
        if(objects != null && objects.length > 0){
            return validateList(Lists.asList(first,objects));
        }else{
            return validate(first,new Class[0]);
        }
    }

    /**
     * 校验，抛出异常
     * @param first XX
     * @param objects XX
     * @throws BasicException 异常
     */
    public static void check(Object first,Object... objects) throws BasicException {
        Map<String, String> result = validateObject(first);
        if(!MapUtils.isEmpty(result)){
            while(result.entrySet().iterator().hasNext()){
                Map.Entry<String, String> next = result.entrySet().iterator().next();
                throw new BasicException(ResultUtil.error().getCode(),next.getValue());
            }
        }
    }

}
