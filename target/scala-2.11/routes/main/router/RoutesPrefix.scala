
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/andfoy/Documentos/Universidad/2016/VI_Semestre/Arquisoft/oilcol-app-play/play-java/conf/routes
// @DATE:Sun Aug 28 00:40:21 COT 2016


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
