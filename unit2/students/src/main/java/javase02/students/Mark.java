package javase02.students;

import lombok.AllArgsConstructor;

public interface Mark {
    Number getMark();
}

@AllArgsConstructor
class FloatMark implements Mark {

    private float mark;

    @Override
    public Number getMark() {
        return mark;
    }
}

@AllArgsConstructor
class IntMark implements Mark {

    private int mark;

    @Override
    public Number getMark() {
        return mark;
    }
}
