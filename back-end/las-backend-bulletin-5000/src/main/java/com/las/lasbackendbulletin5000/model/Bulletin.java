package com.las.lasbackendbulletin5000.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunyinuo

 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bulletin {
    /**id**/
    public int id;
    /**发布人**/
    public String publisher;
    /**等级**/
    public int level;
    /**发布时间**/
    public String time;
    /**标题**/
    public String title;
    /**body**/
    public String body;
    /**置顶**/
    public boolean sticky;
}
