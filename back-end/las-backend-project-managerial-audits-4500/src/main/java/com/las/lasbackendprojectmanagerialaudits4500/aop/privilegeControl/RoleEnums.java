package com.las.lasbackendprojectmanagerialaudits4500.aop.privilegeControl;

/**
 * @author sunyinuo
 */
public enum RoleEnums {


    /**普通用户**/
    NORMAL(1, "普通用户"),
    /**系统管理员**/
    ADMIN(2,"系统管理员");

    private final Integer value;
    private final String name;

    RoleEnums(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public Integer getValue(){
        return value;
    }
    public String getName(){
        return name;
    }

    /**
     * 通过属性值获取属性名
     * @param value 要查询的属性值
     * @return 属性名
     */
    public static String getName(Integer value){
        for(RoleEnums item :values()){
            if (item.getValue().equals(value))
            {
                return item.getName();
            }
        }
        return "";
    }


}
