package com.tatf.suites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// ejecuta todas las pruebas de AdminCes.
@Suite
@SelectPackages({"com.tatf.tests"})
public class RegresionSuite {
}
