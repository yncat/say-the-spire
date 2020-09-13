package sayTheSpire;

import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class OrbBuffer extends Buffer {

  private AbstractOrb orb;

  public OrbBuffer(String name) {
    super(name);
    this.orb = null;
  }

  public Object getObject() {
    return this.orb;
  }

  public void setObject(Object object) {
    this.orb = (AbstractOrb) object;
  }

  public void update() {
    this.clear();
    if (this.orb == null) {
      this.add("No object.");
      return;
    }
    this.add(this.orb.name);
    this.add(TextParser.parse(this.orb.description));
  }
}
