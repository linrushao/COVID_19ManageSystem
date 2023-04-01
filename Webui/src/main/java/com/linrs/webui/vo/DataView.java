package com.linrs.webui.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/8 12:30
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataView {
    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public DataView(Long count,Object data){
        this.count = count;
        this.data = data;
    }

    public DataView(Object data){
        this.data = data;
    }
}
