package dispatchers;

import akka.dispatch.MessageDispatcher;
import play.libs.Akka;

/**
 * Created by c.garcia11 on 20/08/2016.
 */
public class AkkaDispatcher {

    public static MessageDispatcher jdbcDispatcher =  Akka.system().dispatchers().lookup("contexts.jdbc-dispatcher");
}