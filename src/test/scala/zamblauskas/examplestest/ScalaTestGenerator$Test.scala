package zamblauskas.examplestest

import org.scalatest.{FunSuite, Matchers}

class ScalaTestGenerator$Test extends FunSuite with Matchers {

  test("generate test code") {
    val examples = Seq(
      CodeExample("type 1", "code line 1\n"),
      CodeExample("type 2", "code line 1\ncode line 2\n")
    )

    val generated = ScalaTestGenerator.generate("GeneratorTest", examples)

    generated shouldBe """import org.scalatest.{FunSpec, Matchers}
                         |
                         |class GeneratorTestTest extends FunSpec with Matchers {
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
