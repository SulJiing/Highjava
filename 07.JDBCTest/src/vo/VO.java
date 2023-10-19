package vo;

public class VO {
	private String title;
	private String name;
	private String text;
	private String date;
	private String no;
	
	public VO(String title, String name, String text, String date, String no) {
		super();
		this.title = title;
		this.name = name;
		this.text = text;
		this.date = date;
		this.no = no;
	}

	public VO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VO [title=" + title + ", name=" + name + ", text=" + text + ", date=" + date + ", no=" + no + "]";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
}