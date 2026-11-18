package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    // 접속 정보는 코드에 두지 않는다.
    // 환경 변수(POS_DB_URL, POS_DB_USER, POS_DB_PASSWORD)가 있으면 그 값을,
    // 없으면 app/src/main/resources/db.properties(git에 올리지 않음)의 값을 쓴다.
    private static final String CONFIG_FILE = "db.properties";
    private static Properties fileConfig;

    public static Connection getConnection() {
            try {
                String url = read("POS_DB_URL", "db.url");
                String user = read("POS_DB_USER", "db.user");
                String password = read("POS_DB_PASSWORD", "db.password");

                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(url, user, password);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }

    private static String read(String envKey, String fileKey) {
        String value = System.getenv(envKey);
        if (value != null) {
            return value;
        }
        value = loadFileConfig().getProperty(fileKey);
        if (value != null) {
            return value.trim();
        }
        throw new IllegalStateException(
                "DB 접속 정보 " + fileKey + "가 없습니다. "
                        + "app/src/main/resources/db.properties.example을 db.properties로 복사해 값을 채우거나, "
                        + "환경 변수 " + envKey + "를 설정하세요.");
    }

    private static synchronized Properties loadFileConfig() {
        if (fileConfig == null) {
            Properties props = new Properties();
            try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
                if (in != null) {
                    props.load(in);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileConfig = props;
        }
        return fileConfig;
    }

    public static void close(AutoCloseable... resources){
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
