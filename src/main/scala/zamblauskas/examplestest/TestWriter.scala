package zamblauskas.examplestest

import java.io.File
import java.nio.charset.StandardCharsets

import com.google.common.io.Files
import zamblauskas.examplestest.TestWriter._

class TestWriter(
  destinationDir: File,
  codeBlockTypes: Seq[String],
  extractor: CodeExampleExtractor,
  generator: TestGenerator
) {

  def write(input: File): Option[File] = {
    val codeBlocks = extractor.extract(Files.toString(input, charset)).filter(e => codeBlockTypes.contains(e.`type`))
    if(codeBlocks.nonEmpty) {
      val baseName = Files.getNameWithoutExtension(input.getName)
      val testContent = generator.generate(baseName, codeBlocks)
      val writeFile = new File(destinationDir, s"${baseName}Test.scala")
      Files.write(testContent, writeFile, charset)
      Some(writeFile)
    } else {
      None
    }
  }
}

object TestWriter {
  val charset = StandardCharsets.UTF_8
}
