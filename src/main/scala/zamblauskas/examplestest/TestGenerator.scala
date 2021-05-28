package zamblauskas.examplestest

trait TestGenerator {
  /**
   * @param baseName name of the input file without the extension,
   *                 should be used in test name to specify where the code example came from
   * @return generated test code
   */
  def generate(baseName: String, examples: Seq[CodeExample]): String
}
