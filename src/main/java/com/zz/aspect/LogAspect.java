package com.zz.aspect;

import com.alibaba.fastjson.JSON;
import com.zz.model.RequestErrorInfo;
import com.zz.model.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogAspect {

//    public static final String REQUEST_HEADER = "x-forwarded-for";
//
//    /**
//     *  切入点，其中execution用于使用切面的连接点。使用方法：execution(方法修饰符(可选)
//     *  返回类型 方法名 参数 异常模式(可选)) ，可以使用通配符匹配字符，*可以匹配任意字符。
//     */
//    @Pointcut("execution(public * com.zz.controller.*.*(..))")
//    public void logAspect() {
//    }
//
//    /**
//     *  环绕通知，就是可以在执行前后都使用，这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，
//     * 上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息
//     */
//
//    @Around("logAspect()")
//    public Object deAround(ProceedingJoinPoint pjp) throws Throwable {
//
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        String token = request.getHeader("token");
//        String ipaddress;
//        if (request.getHeader(REQUEST_HEADER) == null) {
//            ipaddress = request.getRemoteAddr();
//        } else {
//            ipaddress = request.getHeader(REQUEST_HEADER);
//        }
//
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String queryString = request.getQueryString();
//        Object[] args = pjp.getArgs();
//        String params = "";
//        /**
//         * 获取请求参数集合并进行遍历拼接
//         */
//        if (args.length > 0) {
//            /**
//             *  入参时ServletRequest，ServletResponse 不能序列化，从入参里排除，否则报异常
//             */
//            for (int i = 0; i < args.length; i++) {
//                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
//                    continue;
//                } else {
//                    if ("POST".equals(method)) {
//                        Object object = args[i];
//                        Map map = getKeyAndValue(object);
//                        params = JSON.toJSONString(map);
//
//                    } else if ("GET".equals(method)) {
//                        params = queryString;
//                    }
//                }
//            }
//        }
//
//
//
//
//        /**
//         * result的值就是被拦截方法的返回值
//         */
//
//        Object result = pjp.proceed();
//        if (null == result ){
//            //log.info("请求结束的返回值为空");
//        }
//
//        log.info("请求结束的返回值:" + JSON.toJSONString(new ClassCastException()));
//        log.info("--------------------------------");
//        log.info("请求地址:{},请求ip:{},请求类型:{},请求参数:{},请求返回值:{}",url, ipaddress,method,params,JSON.toJSONString(result));
//        return result;
//
//
//    }
//
//
//    public static Map<String, Object> getKeyAndValue(Object obj) {
//        Map<String, Object> map = new HashMap<>(16);
//        /**
//         * 得到类对象
//         */
//        Class userCla = (Class) obj.getClass();
//        /**
//         * 得到类中的所有属性集合
//         */
//        Field[] fs = userCla.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            /**
//             * 设置些属性是可以访问的
//             */
//            f.setAccessible(true);
//            Object val;
//            try {
//                /**
//                 *  得到此属性的值
//                 */
//                val = f.get(obj);
//                map.put(f.getName(), val);
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return map;
//    }

    @Pointcut("execution(public * com.zz.controller.*.*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()));
        requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
        requestInfo.setResult(result);
        requestInfo.setTimeCost(System.currentTimeMillis() - start);
        log.info("Request Info      : {}", JSON.toJSONString(requestInfo));

        return result;
    }


    @AfterThrowing(pointcut = "requestServer()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setIp(request.getRemoteAddr());
        requestErrorInfo.setUrl(request.getRequestURL().toString());
        requestErrorInfo.setHttpMethod(request.getMethod());
        requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        requestErrorInfo.setException(e);
        log.info("Error Request Info      : {}", JSON.toJSONString(requestErrorInfo));
    }

    /**
     * 获取入参
     * @param proceedingJoinPoint
     *
     * @return
     * */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        // 参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        // 参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            // 如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                // 获取文件名
                value = file.getOriginalFilename();
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

}
