package com.linrs.webui.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.province_data;
import com.linrs.webui.service.service.chinaDataAdminService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.province_dataVo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LRS
 * @Date 2022/8/8 11:55
 * Desc
 */
@RestController
@RequestMapping("/china")
public class ChinaDataAdminController {

    @Autowired
    private chinaDataAdminService indexservice;

    /**
     * 模糊查询 带有分页
     * @return
     */
    @RequestMapping("/listDataByPage")
    public DataView listDataByPage(province_dataVo provice_datavo){

        //1.创建分页的对象
        IPage<province_data> page = new Page<>(provice_datavo.getPage(),provice_datavo.getLimit());

        //2.创建模糊查询条件
        QueryWrapper<province_data> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!(provice_datavo.getProvince()==null),"province",provice_datavo.getProvince());

        //3.疫情数据确诊最多的排在上面
        queryWrapper.orderByDesc("dead");
        //4.查询数据库
        indexservice.page(page,queryWrapper);

        //5.返回数据封装
        DataView dataView = new DataView(page.getTotal(),page.getRecords());
        return dataView;
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public DataView deleteById(Integer id){
        indexservice.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除数据成功！！！");
        return dataView;
    }


    /**
     * 新增或者修改（ID）
     * id：province_data_data 有值修改，没有值就新增
     * update province_data_data set name = "" where id = ?
     * insert into
     * @param province_data_data
     * @return
     */
    @RequestMapping("/addOrUpdateChina")
    public DataView addOrUpdateChina(province_data province_data_data){
        boolean save = indexservice.saveOrUpdate(province_data_data);
        DataView dataView = new DataView();
        if(save){
            dataView.setCode(Constant.SUCCEED_STATUS);
            dataView.setMsg("新增中国数据成功");
            return dataView;
        }
        dataView.setData(100);
        dataView.setMsg("新增中国数据失败");
        return dataView;
    }
    /**
     * EXCEL的拖拽或点击上传
     * 1.前台页面发送一个请求，上传文件mutilpartFile Http
     * 2.Controller ,上传文件mutilpartFile参数
     * 3.POI 解析文件，里面的数据一行一行全部解析出来
     * 4.每一条数据插入数据库
     */

    @RequestMapping(value = "/excelImportChina",method = RequestMethod.POST)
    public DataView excelImportChina(@RequestParam("file") MultipartFile file) throws Exception {
        DataView dataView = new DataView();
        //1.文件不能为空
        if(file.isEmpty()){
            dataView.setMsg("文件为空，不能上传！！");
        }

        //2.POI获取Excel的解析数据
//        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = wb.getSheetAt(0);

        //3.插入数据库
        List<province_data> list = new ArrayList<>();
        XSSFRow row = null;

        //4.解析数据，装到集合里面
        for(int i = 0;i<sheet.getPhysicalNumberOfRows();i++){
            //4.1 定义实体
            province_data province_data_data = new province_data();
            //4.2 每一行的数据放到实体类里面
            row = sheet.getRow(i);
            //4.3 解析数据
            province_data_data.setProvince(row.getCell(0).getStringCellValue());
            province_data_data.setLastUpdateTime((Date)row.getCell(1).getDateCellValue());
            province_data_data.setCofirm((int)row.getCell(2).getNumericCellValue());
            province_data_data.setSuspect((int)row.getCell(3).getNumericCellValue());
            province_data_data.setCured((int)row.getCell(4).getNumericCellValue());
            province_data_data.setDead((int)row.getCell(5).getNumericCellValue());
            //[循环插入数据库]
//            indexservice.save(province_data_data);
            //5.添加list集合
            list.add(province_data_data);
        }
        //6.插入数据库
        indexservice.saveBatch(list);
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("excel插入成功！！！");
        return dataView;

    }

    /**
     * 导出Excel数据，中国疫情数据
     * 1.查询数据库，拿到数据
     * 2.建立Excel对象，封装数据
     * 3.建立输出流，输出文件
     */
    @RequestMapping("/excelOutPortChina")
    public void excelOutPortChina(HttpServletResponse response) {

        //1.查询数据库【查询所有，符合人条件的数据给你查询来】
        List<province_data> list = indexservice.list();
        //2.建立Excel对象，封装数据
        response.setCharacterEncoding("UTF-8");
        //2.1创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
        //2.2创建sheet对象
        XSSFSheet sheet = wb.createSheet("中国疫情数据");
        //2.3 创建表头
        XSSFRow xssfRow = sheet.createRow(0);
        xssfRow.createCell(0).setCellValue("城市名称");
        xssfRow.createCell(1).setCellValue("时间");
        xssfRow.createCell(2).setCellValue("确诊");
        xssfRow.createCell(3).setCellValue("疑似");
        xssfRow.createCell(4).setCellValue("治愈");
        xssfRow.createCell(5).setCellValue("死亡");

        //3 遍历数据，封装Excel公共对象
        for (province_data data:list){
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(data.getProvince());
            dataRow.createCell(1).setCellValue(data.getLastUpdateTime());
            dataRow.createCell(2).setCellValue(data.getCofirm());
            dataRow.createCell(3).setCellValue(data.getSuspect());
            dataRow.createCell(4).setCellValue(data.getCured());
            dataRow.createCell(5).setCellValue(data.getDead());
        }
        //4.建立输出流，输出浏览器文件
        OutputStream os = null;
        //4.1 设置一下excel名字

        try{
            response.setContentType("application/octet-stream;chartset=utf8");
            response.setHeader("Content-Disposition","attachment;filename="+new String("中国疫情数据表".getBytes(),"iso-8859-1")+".xlsx");

            //4.2 输出文件
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(os!=null){
                    //5 关闭输出流
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
