package zamblauskas.examplestest

import java.io.File

import com.google.common.io.Files
import org.apache.commons.io.FileUtils

/**
 * Helper dealing with temporary directories.
 */
trait Tmp {

  def withTempDir[T](f: File => T): T = {
    val dir = Files.createTempDir()
    try{ f(dir) } finally { FileUtils.forceDelete(dir) }
  }

  def withTempDir2[T](f: (File, File) => T): T = withTempDir(d1 => withTempDir(d2 => f(d1, d2)))

}