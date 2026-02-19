package edu.eci.sgbd.application.mapper;

import edu.eci.sgbd.application.DTO.StudentDTO;
import edu.eci.sgbd.application.DTO.SubjectDTO;
import edu.eci.sgbd.application.model.Student;
import edu.eci.sgbd.application.model.Subject;

public class Mapper {

    public static Student toStudent(StudentDTO dto){
        Student e = new Student();
        e.setName(dto.getName());
        e.setCity(dto.getCity());
        e.setEmail(dto.getEmail());
        e.setBirthDate(dto.getBirthDate());
        return e;
    }

    public static Subject toSubject(SubjectDTO dto){
        Subject s = new Subject();
        s.setName(dto.getName());
        s.setCredits(dto.getCredits());
        s.setDepartment(dto.getDepartment());
        return s;
    }
}
