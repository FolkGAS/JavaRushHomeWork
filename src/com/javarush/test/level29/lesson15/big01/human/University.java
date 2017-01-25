package com.javarush.test.level29.lesson15.big01.human;

import java.util.List;

public class University {
    private int age;
    private String name;
    private List<Student> students;

    public University(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student averageStudent = students.get(0);
        for (Student student: students)
            if (Double.compare(student.getAverageGrade(), averageGrade) == 0){
                averageStudent = student;
                break;
        }
        return averageStudent;
    }

    public Student getStudentWithMaxAverageGrade() {
        double averageGrade = students.get(0).getAverageGrade();
        Student averageStudent = students.get(0);
        for (Student student: students)
            if (Double.compare(student.getAverageGrade(), averageGrade) > 0){
                averageStudent = student;
                averageGrade = student.getAverageGrade();
            }
        return averageStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        double averageGrade = students.get(0).getAverageGrade();
        Student averageStudent = students.get(0);
        for (Student student: students)
            if (Double.compare(student.getAverageGrade(), averageGrade) < 0){
                averageStudent = student;
                averageGrade = student.getAverageGrade();
            }
        return averageStudent;
    }

    public void expel(Student student){
        students.remove(student);
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }
}
