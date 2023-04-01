package com.linrs.webui.vo;

import com.linrs.webui.bean.LibraryManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/16 22:16
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LibraryManagerVo extends LibraryManager {
    private Integer page;
    private Integer limit;
}
