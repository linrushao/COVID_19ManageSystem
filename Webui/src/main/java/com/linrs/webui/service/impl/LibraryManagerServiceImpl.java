package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.LibraryManager;
import com.linrs.webui.mapper.libraryManagerMapper;
import com.linrs.webui.service.service.LibraryManagerService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/16 22:14
 * Desc
 */
@Service
public class LibraryManagerServiceImpl extends ServiceImpl<libraryManagerMapper, LibraryManager> implements LibraryManagerService {
}
