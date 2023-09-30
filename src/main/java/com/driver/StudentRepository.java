package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String,Student> studentDb=new HashMap<>();
    Map<String,Teacher> teacherDb=new HashMap<>();
    Map<String,String> teacherStudentDB=new HashMap<>();
    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        teacherStudentDB.put(teacher,student);
    }

    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> ans=new ArrayList<>();

        for(Map.Entry<String,String> entry:teacherStudentDB.entrySet()){
            String teacherName= entry.getKey();
            String studentName= entry.getValue();
            if(teacherName.equals(teacher)){
                ans.add(studentName);
            }
        }
        return ans;
    }

    public List<String> getAllStudent() {
        List<String>  ans=new ArrayList<>();
        for(String studentName:studentDb.keySet()){
            ans.add(studentName);
        }
        return ans;
    }

    public void deleteTeacherByName(String teacher) {
        for(Map.Entry<String,String> entry:teacherStudentDB.entrySet()){
            String teacherName=entry.getKey();
            String studentName= entry.getValue();

            if(teacherName.equals(teacher)){
                teacherStudentDB.remove(teacher);
                studentDb.remove(studentName);
                teacherDb.remove(teacher);
            }
        }
    }

    public void deleteAllTeachers() {
        for(String teacherName:teacherDb.keySet()){
            teacherDb.remove(teacherName);
            String studentName=teacherStudentDB.get(teacherName);
            studentDb.remove(studentName);
            teacherStudentDB.remove(teacherName);
        }
    }
}
