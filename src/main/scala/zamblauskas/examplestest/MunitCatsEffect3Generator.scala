package zamblauskas.examplestest

object MunitCatsEffect3Generator extends TestGenerator {

  def generate(baseName: String, examples: Seq[CodeExample]): String = {
    s"""import munit.CatsEffectSuite
       |
       |class ${baseName}Suite extends CatsEffectSuite {
       |${examples.zipWithIndex.map(generateExample).mkString("\n")}
       |}""".stripMargin
  }

  private def generateExample: ((CodeExample, Int)) => String = { case (example, idx) =>
     s"""  test("code block #$idx") {
       |${example.block}  }""".stripMargin
  }

}
