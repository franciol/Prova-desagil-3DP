package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {
  private AndGate andGate;
  private XorGate xorGate;
  
  public HalfGate() {
    super(2, 2);
    
    name = "HALF";
    
    andGate= new AndGate();
    xorGate = new XorGate();
  }
  
  @Override
  public boolean doRead(int index) {
    if (index == 0) {
      return xorGate.read();
    }
    
    return andGate.read();
  }
  
  @Override
  protected void doConnect(Emitter emitter, int index) {
    switch(index) {
      case 0:
      xorGate.connect(emitter, 0);
      andGate.connect(emitter, 0);
      break;
      case 1:
      xorGate.connect(emitter, 1);
      andGate.connect(emitter, 1);
      break;
    }
  }
}
