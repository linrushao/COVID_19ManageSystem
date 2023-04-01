package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/16 22:06
 * Desc
 */
@Data
@TableName("library_data")
public class LibraryManager {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String library;
    private String name;
    private String phone;
    private String disinfect;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disinfectTime;

}
