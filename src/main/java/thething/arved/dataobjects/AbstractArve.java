package thething.arved.dataobjects;

import java.math.BigDecimal;
import java.util.Date;

import thething.arved.utils.AbstractArvedFilter.ArvedType;

public class AbstractArve implements Comparable<AbstractArve>{

	private long id;
	private String  objekt;
	private String arveNumber;
	private Date kuuPaev;
	private BigDecimal summaIlmaKM;
	private BigDecimal summaKM;
	private boolean tasutud;
	private String pdfLocation;
	private String muugiMees;				//ainult M��GI
	private String klient;					//ainult M��GI
	private String tarnija;					//ainult OSTU
	private ArvedType type;
	
	/*
	 create table arvedtest2.arved (
	 id SERIAL,
	 objekt VARCHAR(255),
	 arveNumber VARCHAR(255),
	 kuuPaev DATE,
	 summaIlmaKM DECIMAL(19,2),
	 summaKM DECIMAL(19,2),
	 tasutud BOOLEAN,
	 pdfLocation VARCHAR(1000),
	 muugiMees VARCHAR(255),
	 klient VARCHAR(255),
	 tarnija VARCHAR(255),
	 type VARCHAR(2)
	
	 );
	 */
	
	
	
	public ArvedType getType() {
		return type;
	}
	public AbstractArve(long id, String objekt, String arveNumber,
			Date kuuPaev, BigDecimal summaIlmaKM, BigDecimal summaKM,
			boolean tasutud, String pdfLocation, String muugiMees,
			String klient, String tarnija, ArvedType type) {
		super();
		this.id = id;
		this.objekt = objekt;
		this.arveNumber = arveNumber;
		this.kuuPaev = kuuPaev;
		this.summaIlmaKM = summaIlmaKM;
		this.summaKM = summaKM;
		this.tasutud = tasutud;
		this.pdfLocation = pdfLocation;
		this.muugiMees = muugiMees;
		this.klient = klient;
		this.tarnija = tarnija;
		this.type = type;
	}
	
	public AbstractArve() {
		// TODO Auto-generated constructor stub
	}
	public void setType(ArvedType type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getObjekt() {
		return objekt;
	}
	public void setObjekt(String objekt) {
		this.objekt = objekt;
	}
	public String getArveNumber() {
		return arveNumber;
	}
	public void setArveNumber(String arveNumber) {
		this.arveNumber = arveNumber;
	}
	public Date getKuuPaev() {
		return kuuPaev;
	}
	public void setKuuPaev(Date kuuPaev) {
		this.kuuPaev = kuuPaev;
	}
	public BigDecimal getSummaIlmaKM() {
		return summaIlmaKM;
	}
	public void setSummaIlmaKM(BigDecimal summaIlmaKM) {
		this.summaIlmaKM = summaIlmaKM;
	}
	public BigDecimal getSummaKM() {
		return summaKM;
	}
	public void setSummaKM(BigDecimal summaKM) {
		this.summaKM = summaKM;
	}
	public boolean isTasutud() {
		return tasutud;
	}
	public void setTasutud(boolean tasutud) {
		this.tasutud = tasutud;
	}
	public String getPdfLocation() {
		return pdfLocation;
	}
	public void setPdfLocation(String pdfLocation) {
		this.pdfLocation = pdfLocation;
	}
	
	
	
	public String getMuugiMees() {
		return muugiMees;
	}
	public void setMuugiMees(String muugiMees) {
		this.muugiMees = muugiMees;
	}
	public String getKlient() {
		return klient;
	}
	public void setKlient(String klient) {
		this.klient = klient;
	}
	public String getTarnija() {
		return tarnija;
	}
	public void setTarnija(String tarnija) {
		this.tarnija = tarnija;
	}
	public String toString(){
		
		return "Arve: id= " + id + "; type= " + type + "; arveNumber= " + arveNumber + "; objekt= " + objekt + "; kuuPaev= " + kuuPaev +
				"; tasutud= " + tasutud + "; pdfLocation= " + pdfLocation + "; summaKM= " + summaKM + "; summaIlmaKM= " + summaIlmaKM;
	}
	
	public int compareTo(AbstractArve o) {
		if(this.getKuuPaev().compareTo(o.getKuuPaev()) != 0){
			return this.getKuuPaev().compareTo(o.getKuuPaev());
		}else{
			return this.getArveNumber().compareTo(o.getArveNumber());
		}
		 
		
	}
	
	
}
