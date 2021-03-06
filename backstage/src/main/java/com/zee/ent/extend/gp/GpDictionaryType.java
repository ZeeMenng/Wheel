package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpDictionaryTypeGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpDictionaryTypeGenEnt，可手动更改。字典类型。
 */

@ApiModel(value = "GpDictionaryType", description = "字典类型。")
public class GpDictionaryType extends GpDictionaryTypeGenEnt {

    @ApiModelProperty(value = "字典项列表 ", required = false)
    private ArrayList<GpDictionary> dictionaryList = new ArrayList<GpDictionary>();


    public ArrayList<GpDictionary> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(ArrayList<GpDictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }
}







