package cesur.examen.core.worker;

import cesur.examen.core.common.DAO;
import cesur.examen.core.common.JDBCUtils;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Santiago Campoy Reviriego
 * Fecha: 11/12/23
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
@Log public class WorkerDAO implements DAO<Worker> {

    /* Please, use this constants for the queries */
    private final String UPDATE_BY_ID = "update trabajador SET nombre = ?, dni = ?, desde = ? WHERE trabajador.id = ?";
    private final String QUERY_BY_DNI = "Select * from trabajador where dni=?";
    private final String QUERY_ORDER_BY = "SELECT * FROM `trabajador` order by desde";

    @Override
    public Worker save(Worker worker) {
        return null;
    }


    @Override
    public Worker update(Worker worker) {

        if( JDBCUtils.getConn()==null){
            throw new RuntimeException("Connection is not created!");
        }

        Worker out = null;

        try( PreparedStatement st = JDBCUtils.getConn().prepareStatement(UPDATE_BY_ID) ){
            st.setString(1,worker.getName());
            st.setString(2,worker.getDni());
            st.setDate( 3, ( Date ) worker.getFrom() );
            st.setLong(4,worker.getId());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Worker w = new Worker();
                w.setId( rs.getLong("id") );
                w.setName( rs.getString("nombre"));
                w.setDni( rs.getString("dni"));
                w.setFrom( rs.getDate("desde"));
                out=w;
            }
        } catch (SQLException e) {
            log.severe("Error in getWorkerById()");
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public boolean remove(Worker worker) {
        return false;
    }

    @Override
    public Worker get(Long id) {
        return null;
    }


    public Worker getWorkerByDNI(String dni) {

        /* Implemented for your pleasure */

        if( JDBCUtils.getConn()==null){
            throw new RuntimeException("Connection is not created!");
        }

        Worker out = null;

        try( PreparedStatement st = JDBCUtils.getConn().prepareStatement(QUERY_BY_DNI) ){
            st.setString(1,dni);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Worker w = new Worker();
                w.setId( rs.getLong("id") );
                w.setName( rs.getString("nombre"));
                w.setDni( rs.getString("dni"));
                w.setFrom( rs.getDate("desde"));
                out=w;
            }
        } catch (SQLException e) {
            log.severe("Error in getWorkerById()");
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public List<Worker> getAll() {
        return null;
    }


    public List<Worker> getAllOrderByFrom(){

        if( JDBCUtils.getConn()==null){
            throw new RuntimeException("Connection is not created!");
        }
        Connection con = JDBCUtils.getConn();
        ArrayList<Worker> out = new ArrayList<>(0);


        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(QUERY_ORDER_BY);

            while (rs.next()) {
                Worker worker = new Worker();
                worker.setId( rs.getLong( "id" ) );
                worker.setName( rs.getString( "nombre" ) );
                worker.setDni( rs.getString( "dni" ) );
                worker.setFrom( rs.getDate( "desde" ) );
                out.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return out;
    }
}
