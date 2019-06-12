package xiao.xss.study.demo.daemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 *
 * @author xiaoliang
 * @since 2019-06-11 14:15
 */
final class Holder {
    private static final Properties CONF;
    private static final String JAR_FOLDER;
    private static final String JAR_PATH;
    private static Daemon daemon;

    static {
        JAR_PATH = Holder.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(JAR_PATH);
        JAR_FOLDER = jarFile.getParent();
        CONF = new Properties();
        try {
            CONF.load(new InputStreamReader(new FileInputStream(new File(JAR_FOLDER, "daemon.properties")), StandardCharsets.UTF_8));
            checkConf();
        } catch(IOException e) {
            System.out.println("Cannot load the config file daemon.properties.");
            e.printStackTrace();
            System.exit(1);
        }
        daemon = new Daemon(CONF);
    }

    private Holder() {}

    static Daemon daemon() {
        return daemon;
    }
    static String jarFolder() {
        return JAR_FOLDER;
    }
    static String jarPath() {
        return JAR_PATH;
    }

    private static void checkConf() {
        if(isEmpty(CONF.getProperty("serviceId")) || isEmpty(CONF.getProperty("runScript"))){
            throw new DaemonException("serviceId and runScript need to be configured in the config file daemon.properties.");
        }
        if(!isNumeric(CONF.getProperty("initDelay"))) {
            throw new DaemonException("Cannot load config file daemon.properties, initDelay must be number.");
        }
        if(!isNumeric(CONF.getProperty("interval"))) {
            throw new DaemonException("Cannot load config file daemon.properties, interval must be number.");
        }

        if(isEmpty(CONF.getProperty("serviceName"))) {
            CONF.setProperty("serviceName", CONF.getProperty("serviceId"));
        }
        if(isEmpty(CONF.getProperty("serviceDescription"))) {
            CONF.setProperty("serviceDescription", CONF.getProperty("serviceName"));
        }
        if(isEmpty(CONF.getProperty("daemonId"))) {
            CONF.setProperty("daemonId", CONF.getProperty("serviceId") + "Daemon");
        }
        if(isEmpty(CONF.getProperty("daemonName"))) {
            CONF.setProperty("daemonName", CONF.getProperty("serviceName") + "守护进程");
        }
        if(isEmpty(CONF.getProperty("daemonDescription"))) {
            CONF.setProperty("daemonDescription", CONF.getProperty("daemonName"));
        }
        if(isEmpty(CONF.getProperty("initDelay"))) {
            CONF.setProperty("initDelay", "10");
        }
        if(isEmpty(CONF.getProperty("interval"))) {
            CONF.setProperty("interval", "300");
        }
    }
    private static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
    private static boolean isNumeric(String value) {
        return isEmpty(value) || Pattern.compile("[\\d]+").matcher(value).matches();
    }
}
