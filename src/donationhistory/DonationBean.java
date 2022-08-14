package donationhistory;

public class DonationBean 
{
	String mobile;
	String bgroup;
	String date;
	
	public DonationBean() {}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DonationBean(String mobile, String bgroup, String date) {
		super();
		this.mobile = mobile;
		this.bgroup = bgroup;
		this.date = date;
	}
	
	
	
}
