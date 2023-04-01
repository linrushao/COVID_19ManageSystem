package com.linrs.webui.vo;

import com.linrs.webui.bean.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/11 17:05
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo extends Menu {
    private Integer page;
    private Integer limit;
}
