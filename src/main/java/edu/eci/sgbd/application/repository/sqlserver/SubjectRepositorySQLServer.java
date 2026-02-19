package edu.eci.sgbd.application.repository.sqlserver;

import edu.eci.sgbd.application.model.Subject;
import edu.eci.sgbd.application.repository.SubjectRepository;
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
public class SubjectRepositorySQLServer implements SubjectRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public SubjectRepositorySQLServer(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private final RowMapper<Subject> mapper = (ResultSet rs, int rowNum) -> {
        Subject s = new Subject();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setCredits(rs.getInt("credits"));
        s.setDepartment(rs.getString("department"));
        return s;
    };

    public void save(Subject subject){
        jdbc.update("INSERT INTO materias(Nombre, Creditos, Departamento) VALUES(?,?,?)", subject.getName(), subject.getCredits(), subject.getDepartment());
    }
    public List<Subject> findAll(){
        return jdbc.query("SELECT id, nombre as name, creditos as credits, departamento as department from dbo.materias",mapper);
    }
    public void update(int id, Subject s){
        jdbc.update("UPDATE dbo.materias SET nombre = ?, creditos = ?,departamento = ? WHERE id = ?", s.getName(), s.getCredits(),s.getDepartment(),id);
    }
    public Optional<Subject> findById(int id){
        List<Subject> list = jdbc.query(
                "SELECT id, nombre as name, creditos as credits, departamento as department from dbo.materias WHERE id = ?",
                mapper,
                id
        );
        return list.stream().findFirst();
    }
    public void deleteById(int id){
        jdbc.update("DELETE FROM dbo.materias WHERE id = ?",id);
    }
}
