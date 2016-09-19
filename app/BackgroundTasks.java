import controllers.PozoController;
import models.PozoEntity;
import models.RegistroSensorTempEntity;
import play.Logger;
import play.api.inject.ApplicationLifecycle;
import play.libs.F;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by joseg on 18/09/2016.
 */
@Singleton
public class BackgroundTasks {

    @Inject
    public BackgroundTasks(ApplicationLifecycle lifecycle)
    {
        System.out.println(" llega hasta antes de background");
        Runnable r = new Runnable() {
            public void run()
            {
                while(true)
                {
                    double promedio = 0;


                    for(long j=1;j<= 1000;j++)// revisa por cada pozo
                    {
                        PozoEntity pozo = PozoEntity.FINDER.byId(j);
                        RegistroSensorTempEntity nuevo = new RegistroSensorTempEntity( pozo, null,0);

                        List<RegistroSensorTempEntity> tempEntities = RegistroSensorTempEntity.FINDER.where().eq("ENTRADAS",  1).eq("pozo", pozo).orderBy("id_sensor_temp desc").setMaxRows(3600).findList();
                        for(int i=0;i< tempEntities.size(); i++)
                        {
                            RegistroSensorTempEntity actual = tempEntities.get(i);

                            promedio= nuevo.getInfo()*nuevo.getNumEntradas()+ actual.getInfo();
                            promedio/=(nuevo.getNumEntradas()+1);

                            nuevo.setInfo(promedio);
                            nuevo.setNumEntradas(nuevo.getNumEntradas()+1);

                            if(i==tempEntities.size()-1){nuevo.setTimeStamp(actual.getTimeStamp());}
                        }
                        if(nuevo.getInfo() != 0)
                        {
                            nuevo.save();
                        }
                        for(int i=0; i< tempEntities.size();i++)
                        {
                            RegistroSensorTempEntity actual = tempEntities.get(i);
                            actual.delete();
                        }

                    }


                    try {
                        sleep(10000);// segundos
                        System.out.println("Se despierta a hacer cosas");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println(" Ya esta corriendo en background el promediador");
    }
}