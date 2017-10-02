package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
  private XorGate xorLeft;
  private XorGate xorRight;
  private AndGate andTop;
  private AndGate andBottom;
  private OrGate orRight;
  
  public FullGate() {
    super(3, 2);
    
    name = "FULL";
    
    xorLeft = new XorGate();
    
    xorRight = new XorGate();
    xorRight.connect(xorLeft, 0);
    
    andTop = new AndGate();
    andTop.connect(xorLeft, 0);
    
    andBottom = new AndGate();
    
    orRight = new OrGate();
    orRight.connect(andTop, 0);
    orRight.connect(andBottom, 1);
    
  }
  
  @Override
  public boolean doRead(int index) {
    if (index == 0) {
      return xorRight.read();
    }
    
    return orRight.read();
  }
  
  @Override
  protected void doConnect(Emitter emitter, int index) {
    switch(index) {
      case 0:
      xorLeft.connect(emitter, 0);
      andBottom.connect(emitter, 0);
      break;
      case 1:
      xorLeft.connect(emitter, 1);
      andBottom.connect(emitter, 1);
      break;
      case 2:
      xorRight.connect(emitter, 1);
      andTop.connect(emitter, 1);
      break;
    }
  }
  
}
