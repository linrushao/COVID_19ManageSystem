package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.bean.globeData;
import com.linrs.webui.service.service.globeDataAdminService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.globeDataVo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author LRS
 * @Date 2022/8/16 13:02
 * Desc
 */
@RestController
@RequestMapping("/globe")
public class globeDataAdminController {

    @Autowired
    private globeDataAdminService globeDataAdminService;


    /**
     * 分页查询
     * @param globeDataVo
     * @return
     */
    @RequestMapping("/globeEpidemicData")
    public DataView globeEpidemicData(globeDataVo globeDataVo){

        //1.创建分页的对象
        IPage<globeData> page = new Page<>(globeDataVo.getPage(),globeDataVo.getLimit());

        //2.创建模糊查询条件
        QueryWrapper<globeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!(globeDataVo.getCountry()==null),"country",globeDataVo.getCountry());

        //3.疫情数据确诊最多的排在上面
        queryWrapper.orderByDesc("dead");
        //4.查询数据库
        globeDataAdminService.page(page,queryWrapper);

        //5.返回数据封装
        DataView dataView = new DataView(page.getTotal(),page.getRecords());
        return dataView;
    }

    /**
     * 导出Excel数据，全球疫情数据
     * 1.查询数据库，拿到数据
     * 2.建立Excel对象，封装数据
     * 3.建立输出流，输出文件
     */
    @RequestMapping("/excelOutPortGlobe")
    public void excelOutPortGlobe(HttpServletResponse response) {

        //1.查询数据库【查询所有，符合人条件的数据给你查询来】
        List<globeData> list = globeDataAdminService.list();
        //2.建立Excel对象，封装数据
        response.setCharacterEncoding("UTF-8");
        //2.1创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
        //2.2创建sheet对象
        XSSFSheet sheet = wb.createSheet("全球疫情数据");
        //2.3 创建表头
        XSSFRow xssfRow = sheet.createRow(0);
        xssfRow.createCell(0).setCellValue("国家名称");
        xssfRow.createCell(1).setCellValue("历史时间");
        xssfRow.createCell(2).setCellValue("新增确诊");
        xssfRow.createCell(3).setCellValue("累积确诊");
        xssfRow.createCell(4).setCellValue("现存确诊");
        xssfRow.createCell(5).setCellValue("累积死亡");
        xssfRow.createCell(6).setCellValue("新增死亡");
        xssfRow.createCell(7).setCellValue("累积治愈");
        xssfRow.createCell(8).setCellValue("新增治愈");
        xssfRow.createCell(9).setCellValue("疑似病例");
        xssfRow.createCell(10).setCellValue("新增疑似");

        //3 遍历数据，封装Excel公共对象
        for (globeData data:list){
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(data.getCountry());
            dataRow.createCell(1).setCellValue(data.getHistoryTime());
            dataRow.createCell(2).setCellValue(data.getCofirmAdd());
            dataRow.createCell(3).setCellValue(data.getCofirm());
            dataRow.createCell(4).setCellValue(data.getCofirmNow());
            dataRow.createCell(5).setCellValue(data.getDead());
            dataRow.createCell(6).setCellValue(data.getDeadAdd());
            dataRow.createCell(7).setCellValue(data.getCured());
            dataRow.createCell(8).setCellValue(data.getCuredAdd());
            dataRow.createCell(9).setCellValue(data.getSuspected());
            dataRow.createCell(10).setCellValue(data.getSuspectedAdd());
        }
        //4.建立输出流，输出浏览器文件
        OutputStream os = null;
        //4.1 设置一下excel名字

        try{
            response.setContentType("application/octet-stream;chartset=utf8");
            response.setHeader("Content-Disposition","attachment;filename="+new String("全球疫情数据表".getBytes(),"iso-8859-1")+".xlsx");

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
