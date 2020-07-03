package domain;

public class Student {
    private int sid;
    private String sname;
    private int sage;
    private String ssex;

    public Student(int sid, String sname, int sage, String ssex) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.ssex = ssex;
    }

    public Student() {
    }

    public Student(String sname, int sage, String ssex) {
        this.sname = sname;
        this.sage = sage;
        this.ssex = ssex;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", ssex='" + ssex + '\'' +
                '}';
    }
}
