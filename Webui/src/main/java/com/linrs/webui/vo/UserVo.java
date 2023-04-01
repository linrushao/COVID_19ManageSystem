package com.linrs.webui.vo;

import com.linrs.webui.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/12 22:40
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
    private Integer page;
    private Integer limit;

}
