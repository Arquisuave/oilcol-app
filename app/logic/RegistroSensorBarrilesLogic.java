package logic;

import models.RegistroSensorBarrilesEntity;

/**
 * Created by jg.tamura10 on 28/08/2016.
 */
public class RegistroSensorBarrilesLogic
{
    //Metodo estatico para que se actualize o se cree un nuevo registro de sensor

    public static RegistroSensorBarrilesEntity crearActualizarRegistro(RegistroSensorBarrilesEntity entityBarril)
    {
        RegistroSensorBarrilesEntity ultimoRegistro = RegistroSensorBarrilesEntity.FINDER.where().eq("pozo", entityBarril.getPozo()).orderBy("id_sensor_barriles desc").setMaxRows(1).findUnique();

        // si entra actualiza
        if (ultimoRegistro !=null && (ultimoRegistro.getTimeStamp().getHours() == entityBarril.getTimeStamp().getHours()))
        {
            double promedio =  ultimoRegistro.getInfo()* ultimoRegistro.getNumEntradas()+ ultimoRegistro.getInfo();

            promedio= promedio/(ultimoRegistro.getNumEntradas()+1);

            ultimoRegistro.setNumEntradas(ultimoRegistro.getNumEntradas()+1);
            ultimoRegistro.setInfo(promedio);
            ultimoRegistro.update();
            return ultimoRegistro;
        }
        else
        {
            //crea el nuevo registro
            entityBarril.setNumEntradas(1);
            entityBarril.save();
            return entityBarril;
        }
    }
}
