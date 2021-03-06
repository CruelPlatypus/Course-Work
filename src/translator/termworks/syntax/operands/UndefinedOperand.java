package translator.termworks.syntax.operands;

import java.util.ArrayList;
import java.util.Stack;

import translator.table.OperandKind;
import translator.table.SymbolTable;
import translator.table.tablecomponents.Atom;
import translator.table.tablecomponents.AtomType;
import translator.table.tablecomponents.userdefined.Identifier;

public class UndefinedOperand extends Operand {

	public UndefinedOperand(ArrayList<Atom> atoms) {
		super(atoms);
	}

	public UndefinedOperand updateSymbolsFromTab(SymbolTable symTab) {
		int i = 0;
		for ( Atom atom : operandAtoms ) {
			if ( atom.getType() == AtomType.Identifier )
				operandAtoms.set(i, tryUpdate((Identifier) atom,symTab) );
			i++;
		}
		return this;
	}
	
	private Atom tryUpdate(Identifier ident,SymbolTable symTab) {
		if ( symTab.Search(ident.getName()) != null)  {
			((Identifier) symTab.Search(ident.getName())).usageFound();
			return symTab.Search(ident.getName());
		}
		return ident;
	}

	public Stack < Identifier > findIdentifiers() {
		Stack < Identifier > idents = new Stack < Identifier > () ;
		for (Atom atom : operandAtoms ) 
			if ( atom.getType() == AtomType.Identifier ) 
				idents.push((Identifier) atom);
				
		return idents;
	}
	
	@Override
	public int calcSizeInBytes() {
		return -1;
	}
	
	@Override
	public OperandKind getOperandKind() {
		return null;
	}

	@Override
	public AtomType getType() {
		return null;
	}

}
