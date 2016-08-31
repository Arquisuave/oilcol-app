package logic;

import models.RegistroSensorEnerEntity;

/**
 * Created by mmgomez on 28/08/2016.
 */
public class RegistroSensorEnerLogic {


    public static RegistroSensorEnerEntity promediar (RegistroSensorEnerEntity reg){
        boolean necesita = false;
        RegistroSensorEnerEntity regActual = RegistroSensorEnerEntity.FINDER
                .where().eq("pozo",reg.getPozo()).orderBy("timestamp desc").setFirstRow(1).findUnique();
        if (regActual != null && reg.getTimeStamp().getHours() == regActual.getTimeStamp().getHours()){
            double promedioActual = regActual.getInfo();
            int numEntradas = regActual.getNumEntradas();
            double infoNueva = reg.getInfo();
            double promedioPonderado = ((promedioActual * numEntradas) + infoNueva)/(numEntradas+1);
            regActual.setNumEntradas(numEntradas+1);
            regActual.setInfo(promedioPonderado);
            regActual.update();
            return regActual;
        }
        else{
            reg.setNumEntradas(1);
            reg.save();
            return reg;
        }

    }


}
