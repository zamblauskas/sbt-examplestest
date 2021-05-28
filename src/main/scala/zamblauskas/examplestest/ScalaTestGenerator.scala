package zamblauskas.examplestest

object ScalaTestGenerator extends TestGenerator {

  def generate(baseName: String, examples: Seq[CodeExample]): String = {
    s"""import org.scalatest.funspec.AnyFunSpec
       |import org.scalatest.matchers.should.Matchers
       |
       |class ${baseName}Test extends AnyFunSpec with Matchers {
       |  describe("$baseName") {
       |${examples.zipWithIndex.map(generateExample).mkString("\n")}
       |  }
       |}""".stripMargin
  }

  private def generateExample: ((CodeExample, Int)) => String = { case (example, idx) =>
     s"""    it("code block #$idx") {
       |${example.block}    }""".stripMargin
  }

}
