
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/camilagarciahernandez/Documents/Universidad/Semestre7/Arquisoft/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 12:06:40 COT 2016


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
