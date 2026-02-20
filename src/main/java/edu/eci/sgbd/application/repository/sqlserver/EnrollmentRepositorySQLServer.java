package edu.eci.sgbd.application.repository.sqlserver;

import edu.eci.sgbd.application.model.Enrollment;
import edu.eci.sgbd.application.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("sqlserver")
public class EnrollmentRepositorySQLServer implements EnrollmentRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public EnrollmentRepositorySQLServer(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Enrollment> mapper = (ResultSet rs, int rowNum) -> {
        Enrollment e = new Enrollment();
        e.setId(rs.getInt("id"));
        e.setStudentId(rs.getInt("studentId"));
        e.setSubjectId(rs.getInt("subjectId"));
        e.setClassroomId(rs.getInt("classroomId"));
        e.setSemester(rs.getString("semester"));
        Double grade = rs.getObject("grade", Double.class);
        e.setGrade(grade);
        return e;
    };

    public void save(Enrollment enrollment) {
        jdbc.update(
                "INSERT INTO Inscripciones(estudianteID, materiaID, salonID, Semestre, Calificacion) VALUES(?,?,?,?,?)",
                enrollment.getStudentId(),
                enrollment.getSubjectId(),
                enrollment.getClassroomId(),
                enrollment.getSemester(),
                enrollment.getGrade());
    }

    public List<Enrollment> findAll() {
        return jdbc.query(
                "SELECT id, estudianteID as studentId, materiaID as subjectId, salonID as classroomId, " +
                        "Semestre as semester, Calificacion as grade FROM dbo.Inscripciones",
                mapper);
    }

    public void update(int id, Enrollment e) {
        jdbc.update("UPDATE dbo.Inscripciones SET estudianteID = ?, materiaID = ?, salonID = ?, " +
                "Semestre = ?, Calificacion = ? WHERE id = ?",
                e.getStudentId(),
                e.getSubjectId(),
                e.getClassroomId(),
                e.getSemester(),
                e.getGrade(),
                id);
    }

    public Optional<Enrollment> findById(int id) {
        List<Enrollment> list = jdbc.query(
                "SELECT id, estudianteID as studentId, materiaID as subjectId, salonID as classroomId, " +
                        "Semestre as semester, Calificacion as grade FROM dbo.Inscripciones WHERE id = ?",
                mapper,
                id);
        return list.stream().findFirst();
    }

    public void deleteById(int id) {
        jdbc.update("DELETE FROM dbo.Inscripciones WHERE id = ?", id);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        return jdbc.query(
                "SELECT id, estudianteID as studentId, materiaID as subjectId, salonID as classroomId, " +
                        "Semestre as semester, Calificacion as grade FROM dbo.Inscripciones WHERE estudianteID = ?",
                mapper,
                studentId);
    }

    public List<Enrollment> findBySubjectId(int subjectId) {
        return jdbc.query(
                "SELECT id, estudianteID as studentId, materiaID as subjectId, salonID as classroomId, " +
                        "Semestre as semester, Calificacion as grade FROM dbo.Inscripciones WHERE materiaID = ?",
                mapper,
                subjectId);
    }

    public List<Enrollment> findByClassroomId(int classroomId) {
        return jdbc.query(
                "SELECT id, estudianteID as studentId, materiaID as subjectId, salonID as classroomId, " +
                        "Semestre as semester, Calificacion as grade FROM dbo.Inscripciones WHERE salonID = ?",
                mapper,
                classroomId);
    }
}
