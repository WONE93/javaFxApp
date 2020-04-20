package co.yedam.test.model;

public class DailyDO {
	
	int idx;
	String dDate;
	String weather;
	String title;
	String contents;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "DailyDO [dDate=" + dDate + ", weather=" + weather + ", title=" + title + ", contents=" + contents + "]";
	}

	
}
