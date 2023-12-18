package School_박연미Ver4;
/*
  프로그램 실행하면 무조건 파일 로드 먼저 
  처음부터 우리 데이터가 연결된 상태 
 */

public class Controller {
	private SubjectDAO subDAO;
	private StudentDAO stuDAO;
	public Controller(){
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		Utils.loadFromFile(stuDAO, subDAO);
	}
	
	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1009 시작) 자동증가 : 학생id 중복 불가  
		System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제 
		System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제 
		System.out.println("[5]전체학생목록"); // 점수로 출력
		System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순) 
		System.out.println("[7]파일저장");
		System.out.println("[8]파일로드");
		System.out.println("[0] 종료");
	}
	
	public void run() {

		mainMenu();
		while(true) {
			int sel = Utils.getValue("메뉴", 0, 8);
			if(sel == 1) {
				System.out.println("[ 학생 추가  ]");
				stuDAO.addStudent();
				
			}else if(sel == 2) {
				System.out.println("[ 한 학생 삭제(과목까지 삭제) ]");
				stuDAO.deleteAStudent(subDAO);
			}else if(sel == 3) {
				System.out.println("[ 한 학생의 한 과목 추가 ]");
				stuDAO.addSujectToOneStudent(subDAO);
			}else if(sel == 4) {
				System.out.println("[ 한 학생의 한 과목 삭제 ]");
				stuDAO.deleteAStudentOneSubject(subDAO);
			}else if(sel == 5) {
				System.out.println("[ 전체 학생 목록  ]");
				stuDAO.printAllStudentByScore(subDAO);
			}else if(sel == 6) {
				System.out.println("[ 한 과목 학생 목록  ]");
				subDAO.getStuListByASubject(stuDAO);
			}else if(sel == 7) {
				System.out.println("[ 파일 저장 ]");
				Utils.saveToFile(stuDAO, subDAO);
			}else if(sel == 8) {
				System.out.println("[ 파일 로드 ]");
				Utils.loadFromFile(stuDAO, subDAO);
			}else {
				System.out.println("프로그램 종료");
				return;
			}
		}
	
	}
}
