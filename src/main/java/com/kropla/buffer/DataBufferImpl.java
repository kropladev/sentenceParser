package com.kropla.buffer;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * Created by kropla on 11.11.16.
 */
@Component
public class DataBufferImpl implements DataBuffer {
    private StringBuffer  buffer;

    @PostConstruct
    public void init(){
        buffer = new StringBuffer();
    }

    @Override
    public void appendNewData(String data) {
        buffer.append(data);
    }

    @Override
    public StringBuffer getBufferData() {
        return buffer;
    }

    @Override
    public void removeSentenceFromBuffer(int length) {
        buffer.delete(0,length+1);
        buffer.trimToSize();
        trimBuffer();
    }

    @Override
    public boolean hasEndSymbolAtIndex(int index) {
        if(buffer.length() > index){
            char sign = buffer.charAt(index);
            if (sign == '.' || sign == '!' || sign == '?') return true;
        }
        return false;
    }

    private void trimBuffer() {
        int i = 0;
        while (i < buffer.length()) {
            if (buffer.charAt(i) == ' '){
                buffer.delete(i,1);
                i++;
            } else {
                return;
            }
        }
    }
}
