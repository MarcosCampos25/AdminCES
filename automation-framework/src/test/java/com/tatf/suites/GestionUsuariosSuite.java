package com.tatf.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// pruebas de gestión de usuarios desde el Administrador: alta y borrado de Testers.
@Suite
@SelectPackages({"com.tatf.tests"})
@IncludeTags({"usuarios"})
public class GestionUsuariosSuite {
}
