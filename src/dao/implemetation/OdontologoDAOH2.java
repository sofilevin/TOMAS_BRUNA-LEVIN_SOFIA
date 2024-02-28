package dao.implemetation;

import dao.DB;
import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);

    private static final String INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;

        try {

            connection = DB.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGOS, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                odontologo.setId(resultSet.getInt(1));
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("El id es: " + odontologo.getId() + ". El odontologo llamado " + odontologo.getNombre() + " " + odontologo.getApellido() + " y su matricula es: " + odontologo.getNumeroMatricula());
        return odontologo;
    }


    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = null;
        List<Odontologo> odontologoList = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = DB.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));

                odontologoList.add(odontologo);
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Estos son los odontologos disponibles: " + odontologoList);
        return odontologoList;
    }

    }
