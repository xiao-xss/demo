@echo on
cd /d %~dp0
java -cp daemon-1.0.jar xiao.xss.study.demo.daemon.Control start
pause