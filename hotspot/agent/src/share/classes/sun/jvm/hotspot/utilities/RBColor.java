package sun.jvm.hotspot.utilities;

/** Type-safe enum for the colors in a red-black tree. */
public class RBColor {
  public static final RBColor RED   = new RBColor("red");
  public static final RBColor BLACK = new RBColor("black");

  public String getName() {
    return name;
  }

  private RBColor(String name) {
    this.name = name;
  }
  private String name;
}
