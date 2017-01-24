package zamblauskas.examplestest

import java.io.File

import org.scalatest.matchers.{MatchResult, Matcher}

import scala.io.Source

object Util {

  class FileContentMatcher(expectedContent: String) extends Matcher[File] {
    def apply(file: File): MatchResult = {
      val actualContent = file.read
      MatchResult(
        actualContent == expectedContent,
        s"Expected $file to contain '$expectedContent', but it contains '$actualContent'",
        s"$file contains $expectedContent"
      )
    }
  }

  def haveContent(expectedContent: String) = new FileContentMatcher(expectedContent)

  def readResource(name: String): String = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(name)).mkString
}
