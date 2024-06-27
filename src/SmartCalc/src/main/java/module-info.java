module edu.school21.smartcalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.bytedeco.javacpp;

    opens edu.school21.smartcalc.serializer to javafx.base;
    opens edu.school21.smartcalc to javafx.fxml;
    exports edu.school21.smartcalc;
    exports edu.school21.smartcalc.model;
    opens edu.school21.smartcalc.model to javafx.fxml;


}