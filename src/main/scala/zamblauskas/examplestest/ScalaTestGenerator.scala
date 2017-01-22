package zamblauskas.examplestest

trait TestGenerator {
  /**
   * @param baseName name of the input file without the extension,
   *                 should be used in test name to specify where the code example came from
   * @return generated test code
   */
  def generate(baseName: String, examples: Seq[CodeExample]): String
}

object ScalaTestGenerator extends TestGenerator {

  def generate(baseName: String, examples: Seq[CodeExample]): String = {
    s"""import org.scalatest.{FunSpec, Matchers}
       |
       |class ${baseName}Test extends FunSpec with Matchers {
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
