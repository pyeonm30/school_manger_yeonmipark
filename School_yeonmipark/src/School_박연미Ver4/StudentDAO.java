package School_박연미Ver4;

import java.util.ArrayList;

public class StudentDAO {

	private ArrayList<Student> stuList;
	private int maxNo;

	public StudentDAO() {
		stuList = new ArrayList<Student>();
		maxNo = 1001;
	}

	public void addStuFromData(String data) {
		if(data.isEmpty()) return;
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String info[] = temp[i].split("/");
			stuList.add(new Student(Integer.parseInt(info[0]), info[1], info[2]));
		}
		maxNo = getMaxNum();
	}

	private int getMaxNum() {
		if (stuList.size() == 0)
			return maxNo;
		int maxNo = 0;
		for (Student s : stuList) {
			if (maxNo < s.getStuNo()) {
				maxNo = s.getStuNo();
			}
		}
		return maxNo;
	}

	public void printAllStudents() {
		for (Student s : stuList) {
			System.out.println(s);
		}
	}

	public void addStudent() {
		String id = Utils.getValue("아이디");
		Student stu = getAStudentById(id);
		if (stu != null) {
			System.out.println("이미 존재하는 아이디가 있습니다");
			return;
		}
		String name = Utils.getValue("이름");
		Student student = new Student(++maxNo, name, id);
		stuList.add(student);
		System.out.println(student + " \n 학생 추가 완료");
	}

	public void deleteAStudent(SubjectDAO subDAO) {
		if (!hasData())
			return;

		String id = Utils.getValue("삭제 학생 아이디");
		Student stu = getAStudentById(id);
		if (stu == null) {
			System.out.println("존재하지 않는 아이디 입니다");
			return;
		}
		subDAO.deleteAllSubjectFromAStudent(stu);
		stuList.remove(stu);

		System.out.println(stu + " \n 학생 삭제 완료");
	}

	private boolean hasData() {
		if (stuList.size() == 0) {
			System.out.println("[no student data ]");
			return false;
		}
		return true;
	}

	private Student getAStudentById(String id) {
		if (stuList.size() == 0)
			return null;
		for (Student s : stuList) {
			if (s.getStuId().equals(id))
				return s;

		}
		return null;
	}

	private Student findAStudentByStuNo(int stuNo) {
		for (Student s : stuList) {
			if (s.getStuNo() == stuNo) {
				return s;
			}
		}
		return null;
	}

	private Student getAStudentByStuNo(SubjectDAO subDAO) {
		int num = Utils.getValue("학번 ", 1001, maxNo);
		Student stu = findAStudentByStuNo(num);
		if (stu == null) {
			System.out.println(" 해당 학번은 존재하지 않습니다");
			return null;
		}
		System.out.println(stu);
		subDAO.printAStudentSubjects(stu);
		return stu;
	}

	public void addSujectToOneStudent(SubjectDAO subDAO) {
		if (!hasData())
			return;
		Student stu = getAStudentByStuNo(subDAO);
		if (stu == null) {
			return;
		}
		if (!subDAO.addOneSubject(stu))
			return;

		subDAO.printAStudentSubjects(stu);
		System.out.println("과목 추가 완료 ");
	}

	public void deleteAStudentOneSubject(SubjectDAO subDAO) {
		if (!hasData())
			return;
		Student stu = getAStudentByStuNo(subDAO);
		if (stu == null)
			return;

		if (!subDAO.deleteASubject(stu))
			return;
		subDAO.printAStudentSubjects(stu);
		System.out.println("과목 삭제 완료");

	}

	public void printAllStudentByScore(SubjectDAO subDAO) {
		if (!hasData())
			return;
		ArrayList<Student> list = new ArrayList<Student>();
		for (Student s : stuList) {
			list.add(s);
		}

		ArrayList<Double> scores = new ArrayList<Double>();
		for (Student s : stuList) {
			scores.add(subDAO.getScoreAvgByStudent(s));
		}

		for (int i = 0; i < list.size(); i += 1) {
			double max = scores.get(i);
			for (int k = i; k < list.size(); k += 1) {
				if (max < scores.get(k)) {
					max = scores.get(k);
					
					Student temp = list.get(i);
					list.set(i, list.get(k));
					list.set(k, temp);

					double score = scores.get(i);
					scores.set(i, scores.get(k));
					scores.set(k, score);

				}
			}
		}
		for (int i = 0; i < list.size(); i += 1) {
			System.out.println(list.get(i));
			subDAO.printAStudentSubjects(list.get(i));
			if (scores.get(i) != 0) {
				System.out.println(scores.get(i) + "점 ");
			}
			System.out.println("---------------------------");

		}

	}

	public void getStudentsBySubjectName(ArrayList<Integer> stuNoList) {
		if (!hasData())
			return;
		ArrayList<Student> list = new ArrayList<Student>();
		for (int num : stuNoList) {
			for (Student s : stuList) {
				if (num == s.getStuNo()) {
					list.add(s);
					break;
				}
			}
		}
		for (int i = 0; i < list.size(); i += 1) {
			String name = list.get(i).getStuName();
			for (int k = i; k < list.size(); k += 1) {
				if (list.get(k).getStuName().compareTo(name) < 0) {
					name = list.get(k).getStuName();
					Student temp = list.get(i);
					list.set(i, list.get(k));
					list.set(k, temp);

				}
			}

		}
		
		for (Student s : list) {
			System.out.println(s);
		}
	}
	
	public String saveAsFileData() {
		if (!hasData())
			return "";
		String data = "";
		for (Student s : stuList) {
			data += s.saveToData();
		}
		return data;
	}


}
