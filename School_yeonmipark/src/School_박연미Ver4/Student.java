package School_박연미Ver4;

public class Student {
	private int stuNo;
	private String stuName;
	private String stuId;
	
	public Student(int stuNo, String stuName ,String stuId ) {
		this.stuNo = stuNo;
		this.stuId = stuId;
		this.stuName = stuName;
	}

	public int getStuNo() {
		return stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public String getStuId() {
		return stuId;
	}


	@Override
	public String toString() {
		String data = stuNo + "\t" + stuName + "\t" + stuId ;
		return data;
	}
	public String saveToData(){
		return "%d/%s/%s\n".formatted(stuNo,stuName,stuId);
	}

}
