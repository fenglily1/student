package service;

import dao.StudentDao;
import domain.Student;
import utils.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class StudentService {
    //添加学生
    public static void addStudent(Student s) {
        try {
            StudentDao.creatStudent(s);
        } catch (SQLException e) {
            System.out.println("添加学生失败");
            e.printStackTrace();
        }
    }

    //根据ID删除单个学生信息
    public static void delete(int sid) {
        try {
            StudentDao.deleteStudent(sid);
        } catch (SQLException e) {
            System.out.println("删除学生失败");
            e.printStackTrace();
        }
    }

    //修改学生姓名
    public static void updateSname(String sname, int sid) {
        try {
            StudentDao.updateSname(sname, sid);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改学生姓名失败");
        }
    }

    //修改学生年龄
    public static void updateSage(int sage, int sid) {
        try {
            StudentDao.updateSage(sage, sid);
        } catch (SQLException e) {
            System.out.println("修改学生年龄失败");
            e.printStackTrace();
        }
    }

    //修改学生性别
    public static void updateSsex(String ssex, int sid) {
        try {
            StudentDao.updateSsex(ssex, sid);
        } catch (SQLException e) {
            System.out.println("修改学生性别失败");
            e.printStackTrace();
        }
    }

    //查询单个学生信息
    public static Student queryStudent(int sid) {
        Student student = null;
        try {
            student = StudentDao.queryStudent(sid);
        } catch (SQLException e) {
            System.out.println("查询学生信息失败");
            e.printStackTrace();
        }
        return student;
    }

    //查询所有学生信息
    public static List<Student> queryAllStudent() {
        List<Student> list = null;
        try {
            list = StudentDao.queryAllStudent();

        } catch (SQLException e) {
            System.out.println("查询所有学生信息失败");
            e.printStackTrace();
        }
        return list;
    }

    //批量删除学生信息
    public static void deleteBatchStudent(List<Integer> list) {
        Connection connection = null;
        //循环调用Dao的删除方法
        try {
            //开启事务
            ConnectionManager.start();
            for (int sid : list
                    ) {
                StudentDao.deleteStudent(sid);
                //System.out.println(1/0);模拟出现问题
            }
            //提交事务
            ConnectionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                //事务回滚
                ConnectionManager.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                ConnectionManager.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
