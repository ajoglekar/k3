package org.xpt.k3;

public record Location(int row, int column, int index) {

    @Override
    public boolean equals(Object other) {
        return (other instanceof Location) && ((Location) other).index == this.index;
    }

    @Override
    public int hashCode() {
        return index;
    }

}

