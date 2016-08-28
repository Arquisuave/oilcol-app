
// @GENERATOR:play-routes-compiler
<<<<<<< HEAD
// @SOURCE:C:/Users/USUARIO/Documents/AQuinto/ArquiSoft/oilcol-app-play/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 11:19:47 COT 2016
=======
// @SOURCE:/Users/camilagarciahernandez/Documents/Universidad/Semestre7/Arquisoft/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 12:06:40 COT 2016
>>>>>>> 5842afacd2fce3ee893a79cb26b26fcf4f63fc01

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAsyncController AsyncController = new controllers.ReverseAsyncController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCountController CountController = new controllers.ReverseCountController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAsyncController AsyncController = new controllers.javascript.ReverseAsyncController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCountController CountController = new controllers.javascript.ReverseCountController(RoutesPrefix.byNamePrefix());
  }

}
