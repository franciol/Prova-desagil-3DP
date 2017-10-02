package br.pro.hashi.ensino.desagil.lucianogic.model;

public class XorGate extends Gate{
		private NandGate nandRight;
		private NandGate nandLeft;
		private NandGate nandUp;
		private NandGate nandDown;
		
		public XorGate() {
			super(2,1);
			
			name = "XOR";
			nandRight = new NandGate();
			nandLeft = new NandGate();
			nandUp = new NandGate();
			nandDown = new NandGate();
			
			nandUp.connect(nandLeft, 1);
			nandDown.connect(nandLeft, 0);
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
			nandLeft.connect(emitter, 0);
			nandUp.connect(emitter, 0);}
			else{
			nandDown.connect(emitter, 1);
			nandLeft.connect(emitter, 1);}
		}

		@Override
		protected boolean doRead(int index) {
			// TODO Auto-generated method stub
			return read();
		}
	}
