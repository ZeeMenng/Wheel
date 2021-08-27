#### 引言

十年开发历程中，都是在反复创造轮子，仿如西西弗斯的梦魇。

具体产品展现上各不相同，具体技术实现上日新月异，但做为开发者，总想悟出“道”的部分，提取“术”的部分，打破梦魇。

冥冥之中，好想有了些想法，至少是对于传统的信息化系统、对于Web系统，悟到了一些东西。

这个项目的目标很明确，就是解决传统信息化系统中反复创造轮子的问题，所以我给它起名“风火轮”。


#### 项目介绍

从产品角度讲，这个项目首先是一个通用的后台管理系统，包括但不限于用户、权限、登录、组织机构、字典、日志、系统配置、附件、树形分类等等功能，把日常项目开发中的轮子一次制造出来。详细内容请参考本项目中的“需求分析文档”。

从技术角度讲，在数据库设计上，这里面沉淀了一套关系型数据库设计的方案，汇总梳理了日常开发中数据库设计的种种细节问题和注意事项。详细内容请参考本项目中的“数据库设计注意事项文档”。

从技术角度讲，在架构的设计上，这里面设计了一套功能完整而又小巧灵活的前后端分离架构，整合了开发过程中一些常见问题的解决方案。详细内容请参考本项目中的“详细设计文档”。

从技术角度讲，在程序的编写上，这里面编写了一套通用的代码模板，将日常增删改查操作的共性部分加以提取。数据库设计完成后，在上面架构设计的基础上，可以通过此代码模板自动快速生成一个新项目。详细内容请参考本项目中的“通用代码模板文档”。

你可以提取当前项目中可用的思路、技术实现到自己的项目中，也可以直接复用当前项目中的内容，在此基础上结合自己的实际情况扩展开发。

“风火轮”是一个产品，是一种技术实现，更是一套解决方案，它同时从产品设计和技术实现的角度上用各种方式达成我们的终极目标——消灭反复创造轮子的劳力。

#### 目录说明


``` lua

wheel
├── backstage -- 后端工程
├── database -- 数据库文件
├── document -- 说明文档
├── frontpage -- 前端工程
├── installation -- 软件安装包
└── template -- 代码模板

```

#### 技术选型


##### 后端技术

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| C#                     |编写CodeSmith模板| https://docs.microsoft.com/en-us/dotnet/csharp/|
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot         |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security     |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatisGenerator     | 数据层代码生成      | http://www.mybatis.org/generator/index.html    |
| Redis                | 分布式缓存          | https://redis.io/                              |
| MongoDB              | NoSql数据库         | https://www.mongodb.com                        |
| Nginx                | 静态资源服务器      | https://www.nginx.com/                         |
| Docker               | 应用容器引擎        | https://www.docker.com                         |
| Jenkins              | 自动化部署工具      | https://github.com/jenkinsci/jenkins           |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid               |
| User-agent-utils    | UserAgentUtils    | https://www.bitwalker.eu/software/user-agent-utils              |
| Commons-FileUpload    | 用于支持MultipartResolver处理文件上传    | https://commons.apache.org/proper/commons-fileupload/               |
| Commons-BeanUtils    | Map和Bean的相互转换    | http://commons.apache.org/proper/commons-beanutils/            |
| JSqlParser    | 开源SQL解析器，用于数据级权限控制  | https://github.com/JSQLParser/JSqlParser             |
| ZooKeeper    | 开源分布式协调系统  | https://zookeeper.apache.org/             |
| JWT                  | JWT登录支持         | https://github.com/jwtk/jjwt                   |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok         |
| Hutool               | Java工具类库        | https://github.com/looly/hutool                |
| PageHelper           | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |
| Swagger-UI           | 文档生成工具        | https://github.com/swagger-api/swagger-ui      |

##### 前端技术

| 技术       | 说明                  | 官网                                   |
| ---------- | --------------------- | -------------------------------------- |
| Metronic | 前端后台管理模板           |https://keenthemes.com/metronic/             |
| jQuery    | 前端JS框架              | https://jquery.com/                     |
| jQuery-UI | 建立在 jQuery库上的一组用户界面交互、特效、小部件及主题   | https://jqueryui.com/             |
| JS-Cookie | 前端Cookie处理插件| https://github.com/js-cookie/js-cookie|
| jQuery-Validation | 前端表单验证插件| https://jqueryvalidation.org/               |
| jQuery-File-Upload | 前端附件上传插件         | https://github.com/blueimp/jQuery-File-Upload               |
| CKEditor | 前端富文本编辑器| https://ckeditor.com/          |
| Select2  | 前端下拉框组件| https://select2.org/    
| Bootstrap | 前端CSS框架          | https://getbootstrap.com/               | 
| Bootstrap-Fileinput | 前端附件上传插件         | https://github.com/kartik-v/bootstrap-fileinput       |
| FontAwesome | 前端图标样式| https://astronautweb.co/snippet/font-awesome/ |
| zTree       | 前端树形插件          | http://www.treejs.cn | 
| Layui   | 前端框架，主要用于处理弹出框、提示框 | https://www.layui.com/              |
| jQuery-JSONView| 前端JSON数据展示插件      | https://github.com/yesmeck/jquery-jsonview |


#### 依赖环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| .NET          | 4.0    | https://dotnet.microsoft.com/|
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 5.0    | https://redis.io/download                                    |
| MongoDB    | 4.2.5  | https://www.mongodb.com/download-center                      |
| RabbitMQ      | 3.7.14 | http://www.rabbitmq.com/download.html                        |
| Nginx         | 1.10   | http://nginx.org/en/download.html                            |


#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
|CodeSmithTools | 代码模板开发工具  |https://www.codesmithtools.com/|
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| RedisDesktop  | redis客户端连接工具 | https://github.com/qishibo/AnotherRedisDesktopManager  |
| Robomongo     | mongo客户端连接工具 | https://robomongo.org/download                  |
| X-shell       | Linux远程连接工具   | http://www.netsarang.com/download/software.html |
| Navicat       | 数据库连接工具      | http://www.formysql.com/xiazai.html             |
| PowerDesigner | 数据库设计工具      | http://powerdesigner.de/                        |
| Axure         | 原型设计工具        | https://www.axure.com/                          |
| MindMaster    | 思维导图设计工具    | http://www.edrawsoft.cn/mindmaster              |
| Postman       | API接口调试工具      | https://www.postman.com/                        |
| Evernote        | Markdown编辑器      |https://www.yinxiang.com/                             |

#### 安装部署

如果要搭建开发环境，需要依次将依赖环境、开发工具中列的软件安装部署。之后运行数据库初始化脚本，将前后端工程文件分别导入IDEA，并修改数据库连接、修改前端调用后台接口的配置。


如果仅搭建演示环境，只需要安装依赖环境中列的软件即可。之后运行数据库初始化脚本，同时编辑工程包配置文件、修改数据库连接并启动，再将前端工程包放入Nginx、修改前端调用后台接口的配置即可。
