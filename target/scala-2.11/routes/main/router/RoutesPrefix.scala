
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/USUARIO/Documents/AQuinto/ArquiSoft/oilcol-app-play/oilcol-app-play/conf/routes
// @DATE:Sun Aug 28 11:19:47 COT 2016


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
