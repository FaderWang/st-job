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
git clone 下载项目到本地或服务器
```shell
git clone https://github.com/FaderWang/st-job.git
```
maven 编译
```shell
cd st-job
mvn clean package
```
docker 部署
```shell
docker-compose up -d
```