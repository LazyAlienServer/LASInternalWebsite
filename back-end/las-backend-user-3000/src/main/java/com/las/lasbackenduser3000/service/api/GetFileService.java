package com.las.lasbackenduser3000.service.api;

/**
 * @author sunyinuo
 */
public interface GetFileService {


    /**
     * 根据id获取图片
     * @param id id
     * @return 图片Byte
     */
    byte[] getFile(String id);

}
