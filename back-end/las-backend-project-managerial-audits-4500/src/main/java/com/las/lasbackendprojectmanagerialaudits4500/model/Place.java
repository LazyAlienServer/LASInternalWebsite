package com.las.lasbackendprojectmanagerialaudits4500.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    private String dimension;
    private int x;
    private int y;
    private int z;
}
