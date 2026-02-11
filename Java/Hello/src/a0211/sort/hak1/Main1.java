package a0211.sort.hak1;

// import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // System.out.print("학생 수 입력 :");
        // int n = sc.nextInt();
        // sc.nextLine();

        // Student[] students = new Student[n];

        // // 학생 정보 입력
        // for (int i = 0; i < n; i++) {
        //     System.out.print("학생 이름: ");
        //     String name = sc.nextLine();
        //     System.out.print("학생 나이: ");
        //     int age = sc.nextInt();
        //     System.out.print("학생 학번: ");
        //     int studentId = sc.nextInt();
        //     sc.nextLine(); // 개행 문자 소비

        //     students[i] = new Student(name, age, studentId);
        // }

        // 더미데이터 (학생 5명)
        Student[] students = {
            new Student("김철수", 20, 2023003),
            new Student("홍길동", 21, 2023001),
            new Student("박민수", 19, 2023005),
            new Student("최지은", 22, 2023002),
            new Student("이영희", 20, 2023004)
        };

        // 삽입 정렬
        InsertionSort(students);

        // 정렬된 결과 출력
        System.out.println("정렬된 학생 목록:");
        for (Student student : students) {
            System.out.println(student);
        }
        
    }

    private static void InsertionSort(Student[] students) {
        int n = students.length;
        for(int i = 1; i < n; i++){
            Student currentStudent = students[i];
            int j = i - 1;
            // 이름 알파벳 순 정렬
            while (j >= 0 && students[j].getName().compareTo(currentStudent.getName()) > 0) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = currentStudent;
        }
    }
}

// j >= 0 && students[j].getName().compareTo(currentStudent.getName()) > 0
// "홍길동".compareTo("김철수") : 
// "앞문자".compareTo("뒷문자") 결과: 
// 양수 -> 앞의 문자열이 사전순으로 뒤에 위치
// 0 -> 두 문자열 같음
// 음수 -> 앞의 문자열이 사전순으로 앞에 위치


class Student {
    private String name;
    private int age;
    private int studentId;

    public Student(String name, int age, int studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + "]";
    }
    
}