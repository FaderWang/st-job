# st-job
> a simple project for schedule and manage task base on quartz

![build status](https://img.shields.io/badge/build-passing-green)
![licence](https://img.shields.io/badge/License-MIT-red)


主要特色：
- 操作简单，只需要编写一个简单的job，即可实现调度
- 与spring整合，job中可注入spring对象
- 支持多线程
- 支持分布式 
- docker部署

## 快速开始
### 部署项目
1. git clone 下载项目到本地或服务器
```shell
git clone https://github.com/FaderWang/st-job.git
```
2. 定义job，继承`AbstractJob`，实现`executeInternal()`方法
```java
public class HelloJob extends AbstractJob {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Params params = getParams(Params.class);
        log.info("[execute job] params {}", JSON.toJSONString(params));
        System.out.println("execute hello world");
    }
}
```

2. maven 编译
```shell
cd st-job
mvn clean package
```

3. docker 部署
```shell
docker-compose up -d
```
**使用docker-compose编排，会出现项目启动而mysql还没启动完成，日志打印连接异常是正常现象，这里会不断重试重启**

4. 等待部署完成，curl命令测试，返回200即部署成功
```shell
curl -X GET http://localhost:8080/cli/ 
```

4. 配置job参数，并注册job，这里是注册到数据库。调用`/job`接口进行注册
```shell
curl -X POST \
  http://localhost:8080/cli/job \
  -H 'Content-Type: application/json' \
  -d '{"cronExpression":"0/10 * * * * ? *","jobClass":"com.faderw.stjob.schedule.job.HelloJob","jobName":"Hello Quartz","param":"{}"}'
```
参数说明具体参照`ScheduleJob`类

5. 执行job，获取第四步添加成功后的jobId，使用curl触发执行。
```shell
curl -X GET http://localhost:8080/cli/schedule/id
```

