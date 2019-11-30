package zamblauskas.examplestest

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ScalaTestGenerator$Test extends AnyFunSuite with Matchers {

  test("generate test code") {
    val examples = Seq(
      CodeExample("type 1", "code line 1\n"),
      CodeExample("type 2", "code line 1\ncode line 2\n")
    )

    val generated = ScalaTestGenerator.generate("GeneratorTest", examples)

    generated shouldBe """import org.scalatest.funspec.AnyFunSpec
                         |import org.scalatest.matchers.should.Matchers
                         |
                         |class GeneratorTestTest extends AnyFunSpec with Matchers {
                         |  describe("GeneratorTest") {
                         |    it("code block #0") {
                         |code line 1
                         |    }
                         |    it("code block #1") {
                         |code line 1
                         |code line 2
                         |    }
                         |  }
                         |}""".stripMargin
  }
}
