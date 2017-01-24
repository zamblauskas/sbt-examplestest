package zamblauskas

import java.io.File
import java.nio.charset.StandardCharsets

import com.google.common.io.Files
import org.apache.commons.io.FileUtils

package object examplestest {

  val charset = StandardCharsets.UTF_8

  implicit class FileExtensions(val f: File) extends AnyVal {
    /**
     * Get child file with given name.
     */
    def / (child: String): File = new File(f, child)

    def baseName: String = Files.getNameWithoutExtension(f.getName)

    def read: String = FileUtils.readFileToString(f, charset)

    def write(content: String): File = {
      FileUtils.writeStringToFile(f, content, charset)
      f
    }
  }
}
