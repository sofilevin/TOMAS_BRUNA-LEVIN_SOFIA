package service;

import dao.IDao;
import dao.implemetation.OdontologoDAOEnMemoria;
import dao.implemetation.OdontologoDAOH2;
import model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService() {
        this.odontologoIDao = new OdontologoDAOH2();
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return odontologoIDao.listarTodos();
    }

}
