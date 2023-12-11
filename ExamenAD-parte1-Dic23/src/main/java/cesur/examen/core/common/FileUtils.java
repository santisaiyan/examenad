package cesur.examen.core.common;

import cesur.examen.core.worker.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Santiago Campoy Reviriego
 * Fecha:
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */


public class FileUtils {

    public static void toCSV(String fileName, List<Worker> workers) {

        try(FileWriter fw = new FileWriter( fileName )){
            BufferedWriter bw = new BufferedWriter( fw );
            for (int i = 0; i < workers.size(); i++){
                bw.append( workers.get( i ).toString() );
                if(i<workers.size()-1) {
                    bw.newLine( );
                }
            }
            bw.close();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }


    }
}


