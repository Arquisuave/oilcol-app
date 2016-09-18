import models.RegistroSensorTempEntity;
import play.Logger;
import play.api.inject.ApplicationLifecycle;
import play.libs.F;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.lang.Thread.sleep;

/**
 * Created by joseg on 18/09/2016.
 */
@Singleton
public class BackgroundTasks {

    @Inject
    public BackgroundTasks(ApplicationLifecycle lifecycle)
    {
        Runnable r = new Runnable() {
            public void run()
            {
                while(true)
                {
                    RegistroSensorTempEntity.FINDER.
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println(" Ya esta corriendo en background ");
    }
}