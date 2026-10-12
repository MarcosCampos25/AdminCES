package com.tatf.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// chequeo rápido de que la aplicación está accesible: solo las pruebas etiquetadas como smoke.
@Suite
@SelectPackages({"com.tatf.tests"})
@IncludeTags({"smoke"})
public class SmokeSuite {
}
