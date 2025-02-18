public class Student {

    private String studentId;
    private String studentName;
    private int average;
    private int total;
    private Module module;

    public Student( String studentId , String studentName){
        this.studentId = studentId;
        this.studentName = studentName;
    }
    public Student( String studentName){
        this.studentName = studentName;
    }

    public Student( String studentId , String studentName, Module module){
        this.studentId = studentId;
        this.studentName = studentName;
        this.module = module;
    }

    public Student( String studentId , String studentName, Module module, int average, int total){
        this.studentId = studentId;
        this.studentName = studentName;
        this.module = module;
        this.average = average;
        this.total= total;
    }

    public String getstudentId(){
        return this.studentId;
    }

    public String getstudentName(){
        return this.studentName;
    }

    public void setStudentName( String studentName){
        this.studentName = studentName;
    }

    public int getAverage(){
        return this.average;
    }

    public void setAverage( int average){
        this.average = average;
    }

    public int getTotal(){
        return this.total;
    }

    public void setTotal( int total){
        this.total = total;
    }

    public Module getmodule(){
        return this.module;
    }

    public int compareTo(Student other) {
        return this.studentName.compareTo(other.studentName);
    }
}
