package com.zee.app.extend.swagger.gp;

import com.zee.bll.extend.unity.gp.GpLoginLogUntBll;
import com.zee.bll.extend.unity.gp.GprUserOrganizationUntBll;
import com.zee.ent.custom.ResultModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统用户。 对外接口，扩展自GpUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/index")
public class GpIndexSwgApp {

    @Autowired
    @Qualifier("gpLoginLogUntBll")
    protected GpLoginLogUntBll gpLoginLogUntBll;


    @Autowired
    @Qualifier("gprUserOrganizationUntBll")
    protected GprUserOrganizationUntBll gprUserOrganizationUntBll;


    /**
     * 查询主页数据
     *
     * @param userId
     */
    @ApiOperation(value = "查询主页数据", notes = "查询主页：网站访问数量、文章发布数量、组织机构数量、注册用户数量")
    @RequestMapping(value = "/getIndexData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getIndexData() {
        ResultModel resultModel = new ResultModel();


        return resultModel;
    }

    /**
     * 查询网站访问数量
     *
     * @param userId
     */
    @ApiOperation(value = "查询网站访问数量", notes = "查询不同channel的访问数量")
    @RequestMapping(value = "/getSiteViews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getSiteViews() {
        ResultModel resultModel = new ResultModel();

        return resultModel;
    }

    /**
     * 查询文章发布数量
     *
     * @param type：查询类型 1-人工发布；2-自动采集。
     */
    @ApiOperation(value = "查询文章发布数量", notes = "查询文章发布数量")
    @ApiImplicitParam(paramType = "path", name = "type", value = "查询类型", required = true, dataType = "String")
    @RequestMapping(value = "/getPublishArticles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getPublishArticles() {
        ResultModel resultModel = new ResultModel();


        return resultModel;
    }

}
