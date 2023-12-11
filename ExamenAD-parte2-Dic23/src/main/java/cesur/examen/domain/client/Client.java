package cesur.examen.domain.client;

import cesur.examen.domain.car.Car;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Santiago Campoy Reviriego
 * Fecha: 11/12/23
 */


@Data
public class Client implements Serializable {

    private Long id;

    private String name;

    private String email;

    private List<Car> cars = new ArrayList<Car>(0);

    /**
     * Utility to add a car to a client.
     * This allows to maintain bidirectional consistency over the relationship,
     * providing client information to the car added.
     *
     * @param c Car to be added to the client.
     */
    public void addCar(Car c){
        c.setClient(this);
        cars.add(c);
    }

}