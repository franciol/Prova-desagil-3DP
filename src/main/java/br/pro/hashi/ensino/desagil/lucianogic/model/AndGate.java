package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate {
	private NandGate nandRight;
	private NandGate nandLeft;
	
	public AndGate() {
		super(2,1);
		name = "AND";
		nandRight = new NandGate();
		nandLeft = new NandGate();
		nandRight.connect(nandLeft, 0);
		nandRight.connect(nandLeft, 1);
	}

	@Override
	public boolean read() {
		return nandRight.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		nandLeft.connect(emitter, index);
	}

	@Override
	protected boolean doRead(int index) {
		// TODO Auto-generated method stub
		return read();
	}
}
