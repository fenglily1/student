package dao;

import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.C3P0Utils;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentDao {
    //创建QueryRunner对象
    private static QueryRunner qr = new QueryRunner(C3P0Utils.geDataSource());
    //新增学生
    public static void creatStudent(Student s) throws SQLException {
        qr.update("insert into student values (null,?,?,?)",s.getSname(),s.getSage(),s.getSsex());
    }
    //删除学生
    public static void deleteStudent(int sid) throws SQLException {
        QueryRunner qr1 = new QueryRunner();
        qr1.update(ConnectionManager.getConnection(),"delete from student where sid =?",sid);
    }
    //修改学生姓名
    public static void updateSname(String sname,int sid) throws SQLException {
        qr.update("update student set sname = ? where sid = ?",sname,sid);
    }
    //修改学生年龄
    public static void updateSage(int sage,int sid) throws SQLException {
        qr.update("update student set sage = ? where sid = ?",sage,sid);
    }
    //修改学生性别
    public static void updateSsex(String ssex,int sid) throws SQLException {
        qr.update("update student set ssex = ? where sid = ?",ssex,sid);
    }
    //查询学生信息
    public static Student queryStudent(int sid) throws SQLException {
        Student student = qr.query("select * from student where sid = ?",new BeanHandler<Student>(Student.class),sid);
        return student;
    }
    //查询所有学生信息
    public static List<Student> queryAllStudent() throws SQLException {
        List<Student> list = qr.query("select * from student ",new BeanListHandler<Student>(Student.class));
        return list;
    }
}
