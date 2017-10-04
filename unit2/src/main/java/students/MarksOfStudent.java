package students;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class MarksOfStudent {
    private Map<Discipline, List<Mark>> marks = new HashMap<>();

    public void add(Discipline discipline, List<Mark> marksList) {
        if (!marks.containsKey(discipline)) {
            marks.put(discipline, marksList);
        } else {
            marks.get(discipline).addAll(marksList);
        }
    }

    public List<Mark> getMarksOfDiscipline(Discipline discipline) {
        return marks.get(discipline);
    }


}
