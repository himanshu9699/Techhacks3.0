package issuebloodhistory;

public class IssueBean 
{
	String name;
	String mobile;
	String bgroup;
	String hospital;
	String reason;
	String doi;
	public IssueBean() {}

	public IssueBean(String name, String mobile, String bgroup, String hospital, String reason, String doi) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.bgroup = bgroup;
		this.hospital = hospital;
		this.reason = reason;
		this.doi = doi;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
}
