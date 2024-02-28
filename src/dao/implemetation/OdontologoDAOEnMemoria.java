package dao.implemetation;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOEnMemoria implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOEnMemoria.class);

    List<Odontologo> odontologoList = new ArrayList<>();


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        int id = odontologoList.size() + 1;
        odontologoList.add(odontologo);
        LOGGER.info("El id es: " + id + ". El odontologo llamado " + odontologo.getNombre() + " " + odontologo.getApellido() + " y su matricula es: " + odontologo.getNumeroMatricula());
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estos son los odontologos disponibles: " + odontologoList);
        return odontologoList;
    }
}
