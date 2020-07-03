package view;

import domain.Student;
import service.StudentService;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class StudentView {
    public static void main(String[] args){
        System.out.println("欢迎来到学生信息管理系统～");
        do {
            managerStudent();
        }while (true);

    }
    public static void managerStudent(){
        //显示菜单
        System.out.println("➡️请选择你要的功能：1.添加学生、2.删除单个学生、3.批量删除学生、4.修改学生信息、5.查询单个学生信息、6.查询所有学生信息、7.退出");
        //获取用户输入的选择
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        //根据用户的选择实现相应的功能
        switch (choice){
            //添加学生信息
            case 1:addStudent();break;
            //删除单个学生
            case 2:deleteStudent();break;
            //批量删除学生
            case 3:deleteBatchStudent();break;
            //修改学生信息
            case 4:updateStudent();break;
            //查询单个学生
            case 5:queryStudent();break;
            //查询所有学生信息
            case 6:queryAllStudent();break;
            //退出
            case 7:System.exit(0);break;
            default:System.out.println("❌输入错误，请重新选择");break;
        }
    }

    //查询单个学生
    private static void queryStudent() {
        System.out.println("请输入你要查询学生的学号：");
        Scanner s = new Scanner(System.in);
        int sid = s.nextInt();
        Student student = StudentService.queryStudent(sid);
        if(student == null){
            System.out.println("查询失败，该学生不存在😢");
        }else {
            System.out.println("😊查询成功，以下为该学生信息：");
            System.out.println(student);
        }
    }
    //批量删除学生
    private static void deleteBatchStudent() {
        //创建一个集合，保存要删除学生的ID
        List<Integer> list = new LinkedList<Integer>();
        while (true){
            System.out.println("请输入你要删除的学生学号（以-1结束）：");
            Scanner scanner = new Scanner(System.in);
            int sid = scanner.nextInt();
            if (sid == -1){
                break;
            }
            //判断该学号学生是否存在
            Student student = StudentService.queryStudent(sid);
            if (student != null) {
                list.add(sid);
                System.out.println("已经标记该学生😊");
                System.out.println(student);
            } else {
                System.out.println("该学生不存在😢");
            }
        }
        System.out.println("您一个选择了"+list.size()+"个学生，是否确认全部删除?(y/n)");
        Scanner scanner = new Scanner(System.in);
        if("y".equals(scanner.nextLine())){
            StudentService.deleteBatchStudent(list);
            System.out.println("🦐批量删除"+list.size()+"个学生成功～");
        }else {
            System.out.println("取消批量删除操作");
        }
    }

    //添加学生信息
    public static void addStudent(){
        System.out.println("请输入你要添加学生的姓名：");
        Scanner scanner = new Scanner(System.in);
        String sname = scanner.nextLine();
        System.out.println("请输入你要添加学生的年龄：");
        int sage = scanner.nextInt();
        scanner.nextLine();//nextInt和netLine不能一起使用，用来接收"\n"，不然没输入性别就自动提交了
        System.out.println("请输入你要添加学生的性别：");
        String ssex = scanner.nextLine();
        //封装成学生对象
        Student s = new Student(sname,sage,ssex);
        //调用Service层的添加学生方法
        StudentService.addStudent(s);
        System.out.println("😊添加学生"+sname+"成功");
    }
    //删除学生信息
    public static void deleteStudent(){
        System.out.println("请输入你要删除的学生学号：");
        Scanner scanner = new Scanner(System.in);
        int sid = scanner.nextInt();
        Student student = StudentService.queryStudent(sid);
        if(student == null){
            System.out.println("😢该学生不存在");
        }else {
            System.out.println("该学生信息如下：");
            System.out.println(student);
            scanner.nextLine();
            System.out.println("⚠️确认删除?(y/n)");
            String choice = scanner.nextLine();
            if(choice.equals("y") || choice.equals("Y")){
                StudentService.delete(sid);
                System.out.println("删除学生"+sid+"成功");
            }else {
                System.out.println("取消删除...");
            }
        }
    }
    //修改学生信息
    public static void updateStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要修改学生的学号：");
        int sid = scanner.nextInt();
        Student student = StudentService.queryStudent(sid);
        if(student == null){
            System.out.println("😢该学生不存在");
        }else{
            System.out.println("你要修改的学生信息如下：");
            System.out.println(student);
            int choice;
            do {
                System.out.println("请选择你要修改的学生信息：1.姓名、2.年龄、3.性别、4.退出修改");
                choice= scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    System.out.println("请输入你要修改的学生姓名:");
                    String sname = scanner.nextLine();
                    StudentService.updateSname(sname, sid);
                    System.out.println("修改成功😊");
                } else if (choice == 2) {
                    System.out.println("请输入你要修改的学生年龄:");
                    int sage = scanner.nextInt();
                    StudentService.updateSage(sage, sid);
                    System.out.println("修改成功😊");
                } else if(choice == 3){
                    System.out.println("请输入你要修改的学生性别:");
                    String ssex = scanner.nextLine();
                    StudentService.updateSsex(ssex, sid);
                    System.out.println("修改成功😊");
                }
            }while (choice != 4);
            System.out.println("你修改后该学生的信息如下：");
            Student s = StudentService.queryStudent(sid);
            System.out.println(s);
        }

    }
    //查询所有学生信息
    public static void queryAllStudent(){
        List<Student> list = StudentService.queryAllStudent();
        if(list.isEmpty()){
            System.out.println("该数据库中暂无学生信息。");
        }else {
            for (Student s:list
                 ) {
                System.out.println(s);
            }
            System.out.println("所有学生信息显示完毕😊");
        }

    }
}
