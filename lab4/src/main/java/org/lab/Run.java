package org.lab;

import org.lab.controller.DepartmentCreator;
import org.lab.controller.FacultyCreator;
import org.lab.controller.GroupCreator;
import org.lab.controller.HumanCreator;
import org.lab.controller.StudentCreator;
import org.lab.controller.UniversityCreator;
import org.lab.model.Department;
import org.lab.model.Faculty;
import org.lab.model.Group;
import org.lab.model.Student;
import org.lab.model.University;

public class Run {
    private static final int CHILDREN_COUNT = 2;

    public static void main(String[] args) {
        University university = createTypycalUniversity();
        printUniversity(university);
    }

    public static University createTypycalUniversity() {
        HumanCreator humanCreator = new HumanCreator();
        StudentCreator studentCreator = new StudentCreator(humanCreator);
        GroupCreator groupCreator = new GroupCreator(studentCreator, humanCreator, CHILDREN_COUNT);
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator, humanCreator, CHILDREN_COUNT);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator, humanCreator, CHILDREN_COUNT);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator, humanCreator, CHILDREN_COUNT);
        return universityCreator.create();
    }

    private static void printUniversity(University university) {
        System.out.println(university.getName() + ", керівник: " + university.getHead());
        for (Faculty faculty : university.getFaculties()) {
            System.out.println("  " + faculty.getName() + ", керівник: " + faculty.getHead());
            for (Department department : faculty.getDepartments()) {
                System.out.println("    " + department.getName() + ", керівник: " + department.getHead());
                for (Group group : department.getGroups()) {
                    System.out.println("      " + group.getName() + ", керівник: " + group.getHead());
                    for (Student student : group.getStudents()) {
                        System.out.println("        Студент: " + student);
                    }
                }
            }
        }
    }
}