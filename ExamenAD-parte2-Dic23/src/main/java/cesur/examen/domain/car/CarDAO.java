package cesur.examen.domain.car;

import cesur.examen.common.DAO;
import cesur.examen.common.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Santiago Campoy Reviriego
 * Fecha: 11/12/23
 */

@Log
public class CarDAO implements DAO<Car> {
    @Override
    public Car save(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public List<Car> getAllByManufacturer(String manufacturer) {
        try (Session session = sessionFactory.openSession()) {
            Query<Car> query = session.createQuery("FROM Car WHERE manufacturer = :manuf", Car.class);
            query.setParameter("manuf", manufacturer);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}