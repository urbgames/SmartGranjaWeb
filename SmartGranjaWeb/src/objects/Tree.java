package objects;

public class Tree {

	private String errorRate;
	private String revision;
	private String kappa;
	private String kBInformation;
	private String kbRelativeInformation;
	private String kBMeanInformation;
	private String meanAbsoluteError;
	private String rootMeanPriorSquaredError;
	private String classDetails;
	private String cumulativeMarginDistribution;
	private String matrix;
	private String summary;
	private String weightedAreaUnderROC;
	private String weightedFMeasure;
	private String tree;
	private String treeGraph;
	
	public String getErrorRate() {
		return errorRate;
	}
	public void setErrorRate(String errorRate) {
		this.errorRate = errorRate;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getKappa() {
		return kappa;
	}
	public void setKappa(String kappa) {
		this.kappa = kappa;
	}
	public String getkBInformation() {
		return kBInformation;
	}
	public void setkBInformation(String kBInformation) {
		this.kBInformation = kBInformation;
	}
	public String getKbRelativeInformation() {
		return kbRelativeInformation;
	}
	public void setKbRelativeInformation(String kbRelativeInformation) {
		this.kbRelativeInformation = kbRelativeInformation;
	}
	public String getMeanAbsoluteError() {
		return meanAbsoluteError;
	}
	public void setMeanAbsoluteError(String meanAbsoluteError) {
		this.meanAbsoluteError = meanAbsoluteError;
	}
	public String getRootMeanPriorSquaredError() {
		return rootMeanPriorSquaredError;
	}
	public void setRootMeanPriorSquaredError(String rootMeanPriorSquaredError) {
		this.rootMeanPriorSquaredError = rootMeanPriorSquaredError;
	}
	public String getClassDetails() {
		return classDetails;
	}
	public void setClassDetails(String classDetails) {
		this.classDetails = classDetails;
	}
	public String getCumulativeMarginDistribution() {
		return cumulativeMarginDistribution;
	}
	public void setCumulativeMarginDistribution(String cumulativeMarginDistribution) {
		this.cumulativeMarginDistribution = cumulativeMarginDistribution;
	}
	public String getMatrix() {
		return matrix;
	}
	public void setMatrix(String matrix) {
		this.matrix = matrix;
	}
	public String getWeightedAreaUnderROC() {
		return weightedAreaUnderROC;
	}
	public void setWeightedAreaUnderROC(String weightedAreaUnderROC) {
		this.weightedAreaUnderROC = weightedAreaUnderROC;
	}
	public String getWeightedFMeasure() {
		return weightedFMeasure;
	}
	public void setWeightedFMeasure(String weightedFMeasure) {
		this.weightedFMeasure = weightedFMeasure;
	}
	public String getTree() {
		return tree;
	}
	public void setTree(String tree) {
		this.tree = tree;
	}
	public String getTreeGraph() {
		return treeGraph.replaceAll("[\n\r]", "");
	}
	public void setTreeGraph(String treeGraph) {
		this.treeGraph = treeGraph;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getkBMeanInformation() {
		return kBMeanInformation;
	}
	public void setkBMeanInformation(String kBMeanInformation) {
		this.kBMeanInformation = kBMeanInformation;
	}

}
