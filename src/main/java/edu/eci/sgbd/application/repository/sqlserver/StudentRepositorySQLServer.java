package edu.eci.sgbd.application.repository.sqlserver;
import edu.eci.sgbd.application.model.Student;
import edu.eci.sgbd.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Profile("sqlserver")
@Repository
public class StudentRepositorySQLServer implements StudentRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public StudentRepositorySQLServer(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private final RowMapper<Student> mapper = (ResultSet rs, int rowNum) -> {
        Student e = new Student();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setEmail(rs.getString("email"));
        var date = rs.getDate("birthDate");
        e.setBirthDate(date != null ? date.toLocalDate() : null);
        e.setCity(rs.getString("city"));
        return e;
    };

    public void save(Student student){
        jdbc.update("INSERT INTO estudiantes(Nombre, Email, FechaNacimiento, Ciudad) VALUES(?,?,?,?)", student.getName(), student.getEmail(), student.getBirthDate(), student.getCity());
    }
    public List<Student> findAll(){
        return jdbc.query("SELECT id, nombre as name, email, fechaNacimiento as birthDate,ciudad as city from dbo.estudiantes",mapper);
    }
    public void update(int id, Student e){
        jdbc.update("UPDATE dbo.estudiantes SET nombre = ?, email = ?,fechaNacimiento = ?, ciudad = ?  WHERE id = ?", e.getName(), e.getEmail(), e.getBirthDate(),e.getCity(),id);
    }
    public Optional<Student> findById(int id){
        List<Student> list = jdbc.query(
                "SELECT id, Nombre as name, Email, FechaNacimiento as birthDate, Ciudad as city FROM dbo.Estudiantes WHERE id = ?",
                mapper,
                id
        );
        return list.stream().findFirst();
    }
    public void deleteById(int id){
        jdbc.update("DELETE FROM dbo.estudiantes WHERE id = ?",id);
    }
    public Optional<Student> findByEmail(String email){
        List<Student> list = jdbc.query("SELECT id,nombre as name,email,fechaNacimiento as birthDate,ciudad as city from dbo.estudiantes WHERE email = ?",mapper,email);
        return list.stream().findFirst();
    }
}
