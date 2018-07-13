# Springbooot-weibo

## 7月13号更新说明

### 添加Shiro安全框架以及Redis

1. 文件分布在config和shiro包下
2. 访问链拦截在ShiroConfig中
3. redis配置暂无密码

### 更改模型转换框架

1. 该版本之前通过Spring自带的BenaUtils进行简单的实体类转换。
2. 现在使用MapStruct框架对模型转换，可以减少大量无用代码的编写，以及更加方便地对转换进行自定义扩展。
3. [MapStruct官方文档](http://mapstruct.org/documentation/stable/reference/html/#mapping-configuration-inheritance)
4. 具体编写时可以参考dto包下mapper包的文件以及*编译出来的target目录下generated-sources*中的文件，MapStruct的原理实际上是在
  编译时自动为两个转换类生成对应的get/set函数进行赋值。

### 枚举类配置

1. 枚举类的使用请自行了解。
2. 已将常用的枚举类配置到entity下的enums包中。

### 返回结果封装

1. 在原有ResultUtils基础上用ResponseEntity<>进行二次封装，携带头部信息和返回码，具体可以参考AuthController的一些使用。
2. 成功结果的返回用ResultUtils.onSuccess封装，已经将data从原有的String更改为Object，由ResponseEntity解析返回。
  注意参数类型必须为Map类型或者类对象。
3. 失败的结果通过抛出异常 throw new ResultException让全局异常类自己处理。可以看看ResultException的定义，该填的信息参数要填进去。
4. ResultException的处理可能会不能正确地返回至接口，可以先编写好，通过调试确认返回信息符合要求即可，具体我再处理


# 任务：根据以上变更适配自己负责编写的接口所涉及到的DTO类，以及Controller的返回值。DTO类的设计可以根据自己的页面设计需求进行修改。
    实际上DTO就是后端数据与前端数据之间的衔接。
