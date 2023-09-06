package ru.Yakovlev.Task6;

public class Repetition implements Comparable{
    
    private Integer[] rep;

    public Repetition(Integer[] rep) {
        this.rep = rep;
    }

    public Integer[] getRep() {
        return rep;
    }

    public void setRep(Integer[] rep) {
        this.rep = rep;
    }

    public Repetition() {
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Repetition){
            int i = 0;
            Repetition repetition = (Repetition) obj;
            boolean found = repetition.getRep().length == this.rep.length;
            while (found & i < this.rep.length){
                found = found & this.rep[i] == repetition.getRep()[i];
                i ++;
            }
            return found;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Repetition){
            int i = 0;
            Repetition repetition = (Repetition) o;
            boolean found = repetition.getRep().length == this.rep.length;
            if(!found){
                return repetition.getRep().length > this.rep.length ? -1 : 1;
            }
            while (found & i < this.rep.length){
                found = found & this.rep[i] == repetition.getRep()[i];
                i ++;
            }
            return found? 0 : -1;
        }
        else {
            return -1;
        }
    }
}
