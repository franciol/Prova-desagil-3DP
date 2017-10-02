package br.pro.hashi.ensino.desagil.lucianogic.model;

public class OrGate extends Gate {
	private NandGate nandUp;
	private NandGate nandDown;
	private NandGate nandRight;
	
	public OrGate() {
		super(2,1);
		
		name = "OR";
		nandRight = new NandGate();
		nandDown = new NandGate();
		nandUp = new NandGate();
		nandRight.connect(nandUp, 0);
		nandRight.connect(nandDown, 1);
	}
	public boolean read() {
		return nandRight.read();
	}

	protected void doConnect(Emitter emitter, int index) {
		if(index == 0){
		nandUp.connect(emitter, 0);
		nandUp.connect(emitter, 1);}
		else{
		nandDown.connect(emitter, 0);
		nandDown.connect(emitter, 1);}}
	@Override
	protected boolean doRead(int index) {
		// TODO Auto-generated method stub
		return read();
	}
		}
