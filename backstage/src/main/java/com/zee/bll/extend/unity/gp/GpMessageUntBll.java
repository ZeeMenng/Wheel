package com.zee.bll.extend.unity.gp;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.unity.gp.GpMessageGenUntBll;


/**
 * @author Zee
 * @createDate 2017/05/22 14:43:59
 * @updateDate 2018/5/7 15:00:36
 * @description 消息表。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。 业务逻辑处理类，扩展自GpMessageGenUntBll ，可手动更改。
 */
@Service("gpMessageUntBll")
public class GpMessageUntBll extends GpMessageGenUntBll {

}





