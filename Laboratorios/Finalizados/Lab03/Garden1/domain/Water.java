package domain;

public final class Water implements Thing {

    private Garden garden;
    private int row, column;

    /**
     * execute and action
     */
    @Override
    public void act() {}

    /**
     * Returns the column
     *
     * @return column
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Returns the row
     *
     * @return row
     */
    @Override
    public int getRow() {
        return row;
    }
}
