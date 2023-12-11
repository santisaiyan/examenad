package cesur.examen.domain.client;

import cesur.examen.domain.car.Car;
import cesur.examen.domain.car.CarDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Santiago Campoy Reviriego
 * Fecha: 11/12/23
 */

public class ClientService {
    private final ClientDAO clientDAO;
    private final CarDAO carDAO;

    public ClientService(ClientDAO clientDAO, CarDAO carDAO) {
        this.clientDAO = clientDAO;
        this.carDAO = carDAO;
    }

    public List<Client> hasManufacturer(String manufacturer) {
        List<Client> clientsWithManufacturer = new ArrayList<>();
        List<Client> allClients = clientDAO.getAll();

        for (Client client : allClients) {
            List<Car> cars = carDAO.getAllByManufacturer(manufacturer);
            if (cars != null && !cars.isEmpty()) {
                clientsWithManufacturer.add(client);
            }
        }

        return clientsWithManufacturer;
    }
}