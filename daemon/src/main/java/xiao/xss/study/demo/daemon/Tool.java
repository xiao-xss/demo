package xiao.xss.study.demo.daemon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 工具类
 *
 * @author xiaoliang
 * @since 2019-06-10 17:07
 */
final class Tool {
    private static boolean started = false;

    /**
     * 初始化守护进程服务
     */
    static void initDaemon() {
        Holder.daemon().init();
        started = true;
    }

    /**
     * 守护进程开始监控应用服务
     */
    static void startWatch() {
        Holder.daemon().watch();
    }

    /**
     * 守护进程停止监控应用服务
     */
    static void stop() {
        Holder.daemon().shutdown();
        started = false;
        // TODO 停止并删除应用服务？停止监听？停止并删除守护进程服务？
    }

    /**
     * 守护进程是否已经安装并启动
     * @return True：已经启动，False：未安装启动
     */
    static boolean isStarted() {
        return started;
    }

    /**
     * 安装服务
     * @param serviceId 服务id
     * @param serviceFile 安装工具
     * @throws InterruptedException 命令中断异常
     * @throws IOException 命令执行异常
     */
    static void installService(String serviceId, File serviceFile) throws InterruptedException, IOException {
        String[] cmd = new String[]{serviceFile.getAbsolutePath(), "install"};
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        process.destroy();
    }

    /**
     * 根据服务id启动服务
     * @param serviceId 服务id
     * @throws IOException 命令执行异常
     * @throws InterruptedException 命令中断异常
     */
    static void startService(String serviceId) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("net start " + serviceId);
        process.waitFor();
        process.destroy();
    }

    /**
     * 根据服务id查询服务状态
     * @param serviceId 服务id
     * @return 服务状态
     * @throws IOException 命令执行异常
     * @throws InterruptedException 命令中断异常
     */
    static Status getServiceStatus(String serviceId) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("sc query " + serviceId);
        int rtn = process.waitFor();
        if(rtn != 0) return Status.NOT_EXIST;

        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        boolean running = br.lines().anyMatch(line -> line.contains("STATE") && line.contains("RUNNING"));
        br.close();
        process.destroy();
        return running ? Status.RUNNING : Status.STOP;
    }

    /**
     * 复制文件
     * @param sourceName 源文件名
     * @param targetName 目标文件名
     * @return 目标文件
     */
    static File copyTool(String sourceName, String targetName) {
        File service = getOutFile(targetName);
        try(OutputStream dos = new FileOutputStream(service);
            InputStream is = Tool.class.getClassLoader().getResourceAsStream(sourceName)) {
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) > 0) {
                dos.write(bytes, 0, len);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return service;
    }

    /**
     * 生成应用服务配置文件
     * @param conf 配置参数
     * @param sourceName 模板配置
     * @param targetName 配置文件名称
     * @return 配置文件
     */
    static File copyServiceConf(Properties conf, String sourceName, String targetName) {
        File confFile = getOutFile(targetName);
        try(BufferedWriter out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(confFile),StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(Tool.class.getClassLoader().getResourceAsStream(sourceName), StandardCharsets.UTF_8))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            String content = sb.toString().replace("${SERVICE_ID}", conf.getProperty("serviceId"))
                    .replace("${SERVICE_NAME}", conf.getProperty("serviceName"))
                    .replace("${SERVICE_DESCRIPTION}", conf.getProperty("serviceDescription"))
                    .replace("${EXEC_SCRIPT}", conf.getProperty("runScript"))
                    .replace("${LOG_PATH}", getLogPath())
                    ;
            out.write(content);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return confFile;
    }
    /**
     * 生成守护进程服务配置文件
     * @param conf 配置参数
     * @param sourceName 模板配置
     * @param targetName 配置文件名称
     * @return 配置文件
     */
    static File copyDaemonConf(Properties conf, String sourceName, String targetName) {
        File file = getOutFile(targetName);
        try(BufferedWriter out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file),StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(Tool.class.getClassLoader().getResourceAsStream(sourceName), StandardCharsets.UTF_8))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            String content = sb.toString().replace("${DAEMON_ID}", conf.getProperty("daemonId"))
                    .replace("${DAEMON_NAME}", conf.getProperty("daemonName"))
                    .replace("${DAEMON_DESCRIPTION}", conf.getProperty("daemonDescription"))
                    .replace("${DAEMON_JAR}", Holder.jarPath())
                    .replace("${LOG_PATH}", getLogPath())
                    ;
            out.write(content);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 将指定得文件设为隐藏
     * @param base 指定文件
     */
    static void hideFile(File base) {
        // 运行命令
        try {
            String sets = "attrib +H \"" + base.getAbsolutePath() + "\"";
            System.out.println(sets);
            Runtime.getRuntime().exec(sets);
        } catch(IOException e) {
            // do nothing
        }
    }

    // 根据文件名称创建配置文件
    private static File getOutFile(String name) {
        File temp = new File(Holder.jarFolder(), "conf");
        temp.mkdirs();
        return new File(temp, name);
    }
    // 创建日志文件夹
    private static String getLogPath() {
        File path = new File(Holder.jarFolder(), "log");
        path.mkdirs();
        return path.getPath();
    }
}
