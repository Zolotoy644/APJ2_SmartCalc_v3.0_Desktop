package edu.school21.smartcalc.model;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = "QueueWrapper.h")
@Namespace("s21")
public class StringQueue extends Pointer {
    static {
        Loader.load();
    }

    public StringQueue() {
        allocate();
    }

    private native void allocate();

    public native void push(@Const @ByRef @StdString String str);

    public native @ByVal @StdString String front();

    public native void pop();

    public native @Cast("bool") boolean empty();
}