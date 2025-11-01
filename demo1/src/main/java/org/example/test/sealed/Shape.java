package org.example.test.sealed;

import org.example.test.sealed.Circle;
import org.example.test.sealed.Rectangle;

public sealed class Shape permits Circle, Rectangle {

}
