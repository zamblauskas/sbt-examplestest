package zamblauskas.examplestest

import sbt._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ExamplesTestPlugin$Test extends AnyFunSuite with Matchers {
  test("munit cats effect 3 generator") {
    val dependencies = Seq("org.typelevel" %% "munit-cats-effect-3" % "1.0.3" % "test")
    ExamplesTestPlugin.generatorFromDependencies(dependencies) shouldBe Some(MunitCatsEffect3Generator)
  }

  test("scalatest generator") {
    val dependencies = Seq("org.scalatest" %% "scalatest" % "3.1.0" % Test)
    ExamplesTestPlugin.generatorFromDependencies(dependencies) shouldBe Some(ScalaTestGenerator)
  }
}
