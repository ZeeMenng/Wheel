package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpOrganizationGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpOrganizationGenEnt，可手动更改。组织机构。
 */

@ApiModel(value = "GpOrganization", description = "组织机构。")
public class GpOrganization extends GpOrganizationGenEnt {

    @ApiModelProperty(value = "岗位列表 ", required = false)
    private ArrayList<GpStation> stationList = new ArrayList<GpStation>();

    public ArrayList<GpStation> getStationList() {
        return stationList;
    }

    public void setStationList(ArrayList<GpStation> stationList) {
        this.stationList = stationList;
    }
}







