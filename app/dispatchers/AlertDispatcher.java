package dispatchers;

import akka.dispatch.MessageDispatcher;
import play.libs.Akka;

/**
 * Created by c.garcia11 on 20/08/2016.
 */
public class AlertDispatcher {

    public static MessageDispatcher alertDispatcher =  Akka.system().dispatchers().lookup("alerts.alert-dispatcher");
}