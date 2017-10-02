package students;

public interface Mark {
    Number getMark();
}

class FloatMark implements Mark {
    private float mark;

    public FloatMark(float mark) {
        this.mark = mark;
    }

    @Override
    public Number getMark() {
        return mark;
    }
}

class IntMark implements Mark {
    private int mark;

    public IntMark(int mark) {
        this.mark = mark;
    }

    @Override
    public Number getMark() {
        return mark;
    }
}
