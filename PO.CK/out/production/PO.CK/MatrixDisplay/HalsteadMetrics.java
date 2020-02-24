package MatrixDisplay;


/**
 * @author  Ahmed Metwally
 */

public class HalsteadMetrics {

	private int DistOperators, DistOperands, TotOperators, TotOperands;
	public int getDistOperators() {
		return DistOperators;
	}
	public int getDistOperands() {
		return DistOperands;
	}
	public int getTotOperators() {
		return TotOperators;
	}
	public int getTotOperands() {
		return TotOperands;
	}
	private int Vocabulary=0;
	private int Proglen=0; 
	private double CalcProgLen=0; 
	private double Volume=0; 
	private double Difficulty=0;
	private double Effort=0;  
	private double TimeReqProg=0;
	private double TimeDelBugs=0;

	public HalsteadMetrics() {
		DistOperators=0;
		DistOperands=0;
		TotOperators=0;
		TotOperands=0;
	}

	public void setParameters(int DistOprt, int DistOper, int TotOprt, int TotOper) {
		DistOperators=DistOprt;
		DistOperands=DistOper;
		TotOperators=TotOprt;
		TotOperands=TotOper;
	}

	public int getVocabulary() {
		Vocabulary=DistOperators+DistOperands;
		return Vocabulary;
	}

	public int getProglen() {
		Proglen=TotOperators+TotOperands;
		return Proglen;
	}

	public double getCalcProgLen() {
		CalcProgLen = DistOperators*(Math.log(DistOperators) / Math.log(2)) + DistOperands*(Math.log(DistOperands) / Math.log(2));
		return CalcProgLen;
	}

	public double getVolume() {
		Volume=(TotOperators+TotOperands)*(Math.log(DistOperators+DistOperands)/Math.log(2));
		return Volume;
	}

	public double getDifficulty() {
		Difficulty=(DistOperators/2)*(TotOperands/(double)DistOperands);
		return Difficulty;
	}

	public double getEffort() {
		Effort=((DistOperators/2)*(TotOperands/(double)DistOperands)) * ((TotOperators+TotOperands)*(Math.log(DistOperators+DistOperands)/Math.log(2)));
		return Effort;
	}

	public double getTimeReqProg() {
		TimeReqProg=(((DistOperators/2)*(TotOperands/(double)DistOperands)) * ((TotOperators+TotOperands)*(Math.log(DistOperators+DistOperands)/Math.log(2)))) /18;
		return TimeReqProg;
	}

	public double getTimeDelBugs() {
		TimeDelBugs = ((TotOperators+TotOperands)*(Math.log(DistOperators+DistOperands)/Math.log(2))) / 3000;
		return TimeDelBugs;
	}	
}