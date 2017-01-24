package zamblauskas.examplestest

import java.io.File

class TestWriter(
  destinationDir: File,
  codeBlockTypes: Seq[String],
  extractor: CodeExampleExtractor,
  generator: TestGenerator
) {

  def write(input: File): Option[File] = {
    val codeBlocks = extractor.extract(input.read).filter(e => codeBlockTypes.contains(e.`type`))
    if(codeBlocks.nonEmpty) {
      val testContent = generator.generate(input.baseName, codeBlocks)
      Some((destinationDir / s"${input.baseName}Test.scala").write(testContent))
    } else {
      None
    }
  }
}
