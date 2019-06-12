package xiao.xss.study.demo.daemon;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java守护进程
 *
 * @author xiaoliang
 * @since 2019-03-04 9:47
 */
class Daemon {
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private boolean started = false;
    private Properties conf;

    Daemon(Properties conf) {
        this.conf = conf;
    }

    /**
     * 安装并启动守护进程服务
     */
    void init() {
        installDaemon();
        startDaemon();
    }

    /**
     * 守护进程开始监控应用进程
     */
    void watch() {
        if(!started) {
            executor.scheduleWithFixedDelay(this::run, Integer.valueOf(conf.getProperty("initDelay")),
                    Integer.valueOf(conf.getProperty("interval")), TimeUnit.SECONDS);
            started = true;
        }
    }

    /**
     * 守护进程停止监控应用进程
     */
    void shutdown() {
        executor.shutdown();
        started = false;
    }

    // 安装守护进程服务
    private void installDaemon() {
        installService(Tool.copyTool("tool.bin", "daemon.exe"),
                Tool.copyDaemonConf(conf, "daemon.conf", "daemon.xml"),
                conf.getProperty("daemonId"));
    }

    // 启动守护进程服务
    private void startDaemon() {
        startService(conf.getProperty("daemonId"));
    }

    // 安装应用服务
    private void installApp() {
        installService(Tool.copyTool("tool.bin", "service.exe"),
                Tool.copyServiceConf(conf, "service.conf", "service.xml"),
                conf.getProperty("serviceId"));
    }

    // 启动应用服务
    private void startApp() {
        startService(conf.getProperty("serviceId"));
    }

    // 监控应用服务
    private void run() {
        Status status = getServiceStatus(conf.getProperty("serviceId"), 5);
        switch(status) {
            case NOT_EXIST: installApp(); startApp(); break;
            case STOP: startApp(); break;
            case RUNNING: break;
            default: throw new DaemonException("未知错误");
        }
    }

    // 根据指定参数安装服务
    private void installService(File serviceFile, File confFile, String serviceId) {
        Status status = getServiceStatus(serviceId, 5);
        if(Status.NOT_EXIST.equals(status)) {
            try {
                Tool.installService(serviceId, serviceFile);
            } catch(InterruptedException | IOException e) {
                // TODO
                e.printStackTrace();
            } finally {
                Tool.hideFile(confFile.getParentFile());
            }
        }
    }

    // 根据服务id启动服务
    private void startService(String serviceId) {
        Status status = getServiceStatus(serviceId, 5);
        if(Status.STOP.equals(status)) {
            try {
                Tool.startService(serviceId);
            } catch(IOException | InterruptedException e) {
                // TODO
                e.printStackTrace();
            }
        }
    }

    // 根据服务id查询服务状态
    private Status getServiceStatus(String serviceId, int retry) {
        Status status = Status.UNKNOWN;
        if(retry <= 0) retry = 1;
        else if(retry > 5) retry = 5;
        int count = 1;
        while(count++ <= retry && Status.UNKNOWN.equals(status)) {
            try {
                status = Tool.getServiceStatus(serviceId);
                if(Status.UNKNOWN.equals(status)) {
                    Thread.sleep(200L);
                }
            } catch(InterruptedException e) {
                // just wakeup early, do nothing
            } catch(IOException e) {
                // TODO do nothing?
                e.printStackTrace();
            }
        }
        if(Status.UNKNOWN.equals(status)) {
            throw new DaemonException("未知异常");
        }
        return status;
    }
}
