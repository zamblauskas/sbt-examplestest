package zamblauskas.examplestest

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSuite, Matchers}
import zamblauskas.examplestest.Util._

class TestWriter$Test extends FunSuite with Matchers with Tmp with MockFactory {

  test("generate tests for given code block types") { withTempDir2 { (inputDir, outputDir) =>
    val extractor = mock[CodeExampleExtractor]
    val generator = mock[TestGenerator]
    val writer = new TestWriter(outputDir, List("type1", "type2"), extractor, generator)

    (extractor.extract _).expects("input data").returning(List(
      CodeExample("type0", "block0"),
      CodeExample("type1", "block1"),
      CodeExample("type2", "block2"),
      CodeExample("type3", "block3")
    ))

    (generator.generate _).expects("name", List(
      CodeExample("type1", "block1"),
      CodeExample("type2", "block2"))
    ).returning("test content")

    writer.write(inputDir / "name.extension" write "input data")

    outputDir.listFiles should have length 1
    outputDir / "nameTest.scala" should haveContent("test content")
  }}

  test("do not create test file if none of given code block types present") { withTempDir2 { (inputDir, outputDir) =>
    val extractor = mock[CodeExampleExtractor]
    val generator = mock[TestGenerator]
    val writer = new TestWriter(outputDir, List("type1", "type2"), extractor, generator)

    (extractor.extract _).expects("input data").returning(List(
      CodeExample("type0", "block0"),
      CodeExample("type3", "block3")
    ))

    (generator.generate _).expects(*, *).never

    writer.write(inputDir / "name.extension" write "input data")

    outputDir.list shouldBe empty
  }}
}
