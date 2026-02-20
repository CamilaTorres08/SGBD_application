package edu.eci.sgbd.application.repository.sqlserver;

import edu.eci.sgbd.application.model.Classroom;
import edu.eci.sgbd.application.repository.ClassroomRepository;
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
public class ClassroomRepositorySQLServer implements ClassroomRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public ClassroomRepositorySQLServer(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Classroom> mapper = (ResultSet rs, int rowNum) -> {
        Classroom c = new Classroom();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setBuilding(rs.getString("building"));
        c.setCapacity(rs.getInt("capacity"));
        c.setHasProjector(rs.getBoolean("hasProjector"));
        return c;
    };

    public void save(Classroom classroom) {
        jdbc.update("INSERT INTO Salones(NumeroSalon, Edificio, Capacidad, TieneProyector) VALUES(?,?,?,?)",
                classroom.getName(), classroom.getBuilding(), classroom.getCapacity(), classroom.isHasProjector());
    }

    public List<Classroom> findAll() {
        return jdbc.query(
                "SELECT id, NumeroSalon as name, Edificio as building, Capacidad as capacity, TieneProyector as hasProjector FROM dbo.Salones",
                mapper);
    }

    public void update(int id, Classroom c) {
        jdbc.update(
                "UPDATE dbo.Salones SET NumeroSalon = ?, Edificio = ?, Capacidad = ?, TieneProyector = ? WHERE id = ?",
                c.getName(), c.getBuilding(), c.getCapacity(), c.isHasProjector(), id);
    }

    public Optional<Classroom> findById(int id) {
        List<Classroom> list = jdbc.query(
                "SELECT id, NumeroSalon as name, Edificio as building, Capacidad as capacity, TieneProyector as hasProjector FROM dbo.Salones WHERE id = ?",
                mapper,
                id);
        return list.stream().findFirst();
    }

    public void deleteById(int id) {
        jdbc.update("DELETE FROM dbo.Salones WHERE id = ?", id);
    }
}
