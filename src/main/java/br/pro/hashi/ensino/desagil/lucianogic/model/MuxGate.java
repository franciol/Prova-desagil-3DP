package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private NandGate nandUp;
	private NandGate nandDown;
	private NandGate nandLeft;
	private NandGate nandRight;
	
	public MuxGate() {
		super(3,1);
		
		name = "MUX";
		nandUp = new NandGate();
		nandDown = new NandGate();
		nandLeft = new NandGate();
		nandRight = new NandGate();
		
		nandUp.connect(nandLeft, 1);
		nandRight.connect(nandUp, 0);
		nandRight.connect(nandDown, 1);
		
	
	}
	@Override
	public boolean read() {
		return nandRight.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index == 0){
			nandUp.connect(emitter, 0);
		}
		else if(index == 1){
			nandDown.connect(emitter, 1);
		}
		else{
			nandLeft.connect(emitter, 0);
			nandLeft.connect(emitter, 1);
			nandDown.connect(emitter, 0);
		}

	}
	@Override
	protected boolean doRead(int index) {
		// TODO Auto-generated method stub
		return read();
	}

}
