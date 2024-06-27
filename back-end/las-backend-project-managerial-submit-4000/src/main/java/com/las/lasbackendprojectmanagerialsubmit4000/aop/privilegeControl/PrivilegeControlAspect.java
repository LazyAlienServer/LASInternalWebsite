package com.las.lasbackendprojectmanagerialsubmit4000.aop.privilegeControl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.service.db.redis.impl.RedisToolsImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import result.ResultEnum;
import result.ResultUtil;

import java.lang.reflect.Method;


/**
 * @author sunyinuo
 */
@Component
@Aspect
public record PrivilegeControlAspect(RedisToolsImpl redisTools) {

    @Pointcut("@annotation(com.las.lasbackendprojectmanagerialsubmit4000.aop.privilegeControl.PrivilegeControl)")
    public void privilegeControlPointCut() {}

    @Around("privilegeControlPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //返回
        Object result = "";

        //获取token,默认写在第一个参数
        Object[] args = proceedingJoinPoint.getArgs();
        String token = (String) args[0];

        //获得PrivilegeControl注解的roleIdList参数
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method executionMethod = methodSignature.getMethod();
        PrivilegeControl annotation = executionMethod.getAnnotation(PrivilegeControl.class);
        long[] roleIdList = annotation.roleIdList();

        //如果token不为空,即在登陆状态
        if (redisTools.getByKey(token) != null){
            //根据token查redis
            String byKeyString = (String) redisTools.getByKey(token);
            JSONObject userJson = JSONObject.parseObject(byKeyString);
            User byKey = JSON.toJavaObject(userJson,User.class);

            //遍历roleIdList
            for (long roleId : roleIdList) {
                switch ((int) roleId){
                    //普通用户
                    case 1:
                        //直接返回
                        result = proceedingJoinPoint.proceed();
                        break;
                    case 2:
                        //如果用户是管理员
                        if (byKey.getAdministrator() == 1){
                            //返回
                            result = proceedingJoinPoint.proceed();
                        }else {
                            //不是管理员,返回信息
                            result = ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(), "无权限,拒绝访问");
                        }
                }
            }
        }else {
            //不在登陆状态,返回信息
            result = ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(), "未登陆,拒绝访问");
        }
        return result;
    }

}