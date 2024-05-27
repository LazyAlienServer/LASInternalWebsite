package com.las.lasbackenduser3000.service.api;

import result.Result;

/**
 * @author sunyinuo
 */
public interface LoginService {

    /**
     * login
     * @param userName userName
     * @param password password
     * @return 统一返回
     */
    Result login(String userName,String password);

}
