package sun.text.normalizer;

import java.text.CharacterIterator;

/**
 * This class is a wrapper around CharacterIterator and implements the
 * UCharacterIterator protocol
 */
public class CharacterIteratorWrapper extends UCharacterIterator {

    private CharacterIterator iterator;

    public CharacterIteratorWrapper(CharacterIterator iter){
        if(iter==null){
            throw new IllegalArgumentException();
        }
        iterator     = iter;
    }

    /**
     * @see UCharacterIterator#current()
     */
    public int current() {
        int c = iterator.current();
        if(c==CharacterIterator.DONE){
          return DONE;
        }
        return c;
    }

    /**
     * @see UCharacterIterator#getLength()
     */
    public int getLength() {
        return (iterator.getEndIndex() - iterator.getBeginIndex());
    }

    /**
     * @see UCharacterIterator#getIndex()
     */
    public int getIndex() {
        return iterator.getIndex();
    }

    /**
     * @see UCharacterIterator#next()
     */
    public int next() {
        int i = iterator.current();
        iterator.next();
        if(i==CharacterIterator.DONE){
          return DONE;
        }
        return i;
    }

    /**
     * @see UCharacterIterator#previous()
     */
    public int previous() {
        int i = iterator.previous();
        if(i==CharacterIterator.DONE){
            return DONE;
        }
        return i;
    }

    /**
     * @see UCharacterIterator#setIndex(int)
     */
    public void setIndex(int index) {
        iterator.setIndex(index);
    }

    //// for StringPrep
    /**
     * @see UCharacterIterator#getText(char[])
     */
    public int getText(char[] fillIn, int offset){
        int length =iterator.getEndIndex() - iterator.getBeginIndex();
        int currentIndex = iterator.getIndex();
        if(offset < 0 || offset + length > fillIn.length){
            throw new IndexOutOfBoundsException(Integer.toString(length));
        }

        for (char ch = iterator.first(); ch != CharacterIterator.DONE; ch = iterator.next()) {
            fillIn[offset++] = ch;
        }
        iterator.setIndex(currentIndex);

        return length;
    }

    /**
     * Creates a clone of this iterator.  Clones the underlying character iterator.
     * @see UCharacterIterator#clone()
     */
    public Object clone(){
        try {
            CharacterIteratorWrapper result = (CharacterIteratorWrapper) super.clone();
            result.iterator = (CharacterIterator)this.iterator.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            return null; // only invoked if bad underlying character iterator
        }
    }
}
