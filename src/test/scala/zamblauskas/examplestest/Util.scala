package zamblauskas.examplestest

import java.io.File
import java.nio.charset.StandardCharsets

import org.apache.commons.io.FileUtils
import org.scalatest.matchers.{MatchResult, Matcher}

import scala.io.Source

object Util {

  class FileContentMatcher(expectedContent: String) extends Matcher[File] {
    def apply(file: File): MatchResult = {
      val actualContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8)
      MatchResult(
        actualContent == expectedContent,
        s"Expected $file to contain '$expectedContent', but it contains '$actualContent'",
        s"$file contains $expectedContent"
      )
    }
  }

  def haveContent(expectedContent: String) = new FileContentMatcher(expectedContent)

  implicit class FileExtensions(val file: File) extends AnyVal {
    /**
     * Get child file with given name.
     */
    def / (child: String): File = new File(file, child)

    def write(content: String): File = {
      FileUtils.writeStringToFile(file, "input data", StandardCharsets.UTF_8)
      file
    }
  }

  def readResource(name: String): String = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(name)).mkString
}
