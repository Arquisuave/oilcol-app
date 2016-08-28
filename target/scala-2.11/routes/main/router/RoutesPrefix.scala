
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/andfoy/Documentos/Universidad/2016/VI_Semestre/Arquisoft/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 11:24:36 COT 2016


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
