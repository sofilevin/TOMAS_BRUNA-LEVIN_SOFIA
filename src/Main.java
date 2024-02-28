import dao.DB;
import model.Odontologo;
import service.OdontologoService;

public class Main {

    public static void main(String[] args) {

        DB.crearTablas();
        OdontologoService odontologoService = new OdontologoService();

        Odontologo odontologo1 = new Odontologo(123, "Pancracio", "Gonzales");
        Odontologo odontologo2 = new Odontologo(456, "Euladia", "Garcia Perez");

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.listarTodos();

    }
}
