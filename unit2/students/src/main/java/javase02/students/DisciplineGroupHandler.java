package javase02.students;

import java.util.*;

public class DisciplineGroupHandler {
    private Map<Discipline, Map<Student, List<Mark>>> journal = new HashMap<>();

    public DisciplineGroupHandler() {
        Arrays.stream(Discipline.values())
                .forEach(discipline -> journal.put(discipline, new HashMap<>()));
    }

    public void addStudent(Discipline discipline, Student student) {
        journal.get(discipline).put(student, new ArrayList<Mark>());
    }

    public void removeStudent(Discipline discipline, Student student) {
        journal.get(discipline).remove(student);
    }

    public void addMarks(Discipline discipline, Student student, List<Mark> marks) {
        journal.get(discipline).get(student).addAll(marks);
    }

    public boolean contains(Discipline discipline, Student student) {
        return journal.get(discipline).containsKey(student);
    }

    public MarksOfStudent getMarksOfStudent(Student student) {
        MarksOfStudent marksOfStudent = new MarksOfStudent();
        for (Discipline discipline : journal.keySet()) {
            if (journal.get(discipline).containsKey(student)) {
                marksOfStudent.add(discipline, journal.get(discipline).get(student));
            }
        }
        return marksOfStudent;
    }

    public Set<Student> getStudentsOfDiscipline(Discipline discipline) {
        return journal.get(discipline).keySet();
    }

    public Map<Student, List<Mark>> getMarksOfStudentsByDiscipline(Discipline discipline) {
        return journal.get(discipline);
    }
}
