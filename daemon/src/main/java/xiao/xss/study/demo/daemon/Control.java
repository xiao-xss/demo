package xiao.xss.study.demo.daemon;

/**
 * 守护进程控制器
 *
 * @author xiaoliang
 * @since 2019-06-11 14:10
 */
public class Control {
    public static void main(String[] args) {
        if(args.length < 1) {
            throw new IllegalArgumentException("The first parameter must be start or stop.");
        }

        String mode = args[0];
        if("start".equalsIgnoreCase(mode)) {
            if(!Tool.isStarted()) {
                // 安装、启动守护进程
                Tool.initDaemon();
            } else {
                // 守护进程开始监控应用进程
                Tool.startWatch();
            }
        } else if("watch".equalsIgnoreCase(mode)){
            // 守护进程开始监控应用进程
            Tool.startWatch();
        } else if("stop".equalsIgnoreCase(mode)) {
            // 守护进程停止应用进程 TODO
            Tool.stop();
        } else {
            // 无效指令
            throw new IllegalArgumentException("The first parameter must be start or stop.");
        }
    }
}
