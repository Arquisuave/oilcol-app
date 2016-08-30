package logic;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import models.RegistroSensorEmergEntity;
import models.RegistroSensorEnerEntity;
import models.RegistroSensorTempEntity;
import play.libs.Json;

/**
 * Created by cgarciah on 28/08/2016.
 */
public class RegistroSensorTempLogic {

    public static RegistroSensorTempEntity actualizarRegistro(RegistroSensorTempEntity registro){
        RegistroSensorTempEntity regViejo = RegistroSensorTempEntity.FINDER.where().eq("pozo",registro.getPozo()).orderBy("id_sensor_temp desc").setMaxRows(1).findUnique();
        if (regViejo!=null&&registro.getTimeStamp().getHours()==regViejo.getTimeStamp().getHours()){
            double promedio=regViejo.getInfo()*regViejo.getNumEntradas()+registro.getInfo();
            promedio/=(regViejo.getNumEntradas()+1);
            regViejo.setNumEntradas(regViejo.getNumEntradas()+1);
            regViejo.setInfo(promedio);
            regViejo.update();
            return regViejo;
        }else{
            registro.setNumEntradas(1);
            registro.save();
            return registro;
        }
    }
}
