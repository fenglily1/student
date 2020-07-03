package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {
    //创建C3P0连接池
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    //获取连接池对象
    public static DataSource geDataSource(){
        return ds;
    }
    //获取一个连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
