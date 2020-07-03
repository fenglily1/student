package utils;

import java.sql.Connection;
import java.sql.SQLException;

//连接管理类，负责获取连接，开启事务，提交事务，回滚事务
public class ConnectionManager {
    //定义一个ThreadLocal类对象来保存当前线程的连接
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    //获取连接
    public static Connection getConnection() throws SQLException {
        //先从tl中获取连接
        Connection connection = tl.get();
        //判断连接是否为空，
        if (connection == null){
            connection = C3P0Utils.getConnection();
            tl.set(connection);
        }
        return connection;
    }
    //开启事务
    public static void start() throws SQLException {
        ConnectionManager.getConnection().setAutoCommit(false);
    }
    //提交事务
    public static void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
    }
    //回滚事务
    public static void rollback() throws SQLException {
        ConnectionManager.getConnection().rollback();
    }
    //关闭连接
    public static void close() throws SQLException {
        ConnectionManager.getConnection().close();
    }

}
