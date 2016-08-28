
// @GENERATOR:play-routes-compiler
<<<<<<< HEAD
// @SOURCE:C:/Users/USUARIO/Documents/AQuinto/ArquiSoft/oilcol-app-play/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 11:19:47 COT 2016
=======
// @SOURCE:/Users/camilagarciahernandez/Documents/Universidad/Semestre7/Arquisoft/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 12:06:40 COT 2016
>>>>>>> 5842afacd2fce3ee893a79cb26b26fcf4f63fc01


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
