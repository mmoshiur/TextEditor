package editors;

import java.awt.Rectangle;

/**
* A generic model interface which defines an underlying component with line numbers.
*
*/

public interface ILineNumModel {

        /**
        *
        * @return
        */

	public int getNumberLines();

        /**
         * Returns a Rectangle defining the location in the view of the parameter line. Only the y and height fields are required by callers.
         * @param line
         * @return A Rectangle defining the view coordinates of the line.
        */

	public Rectangle getLineRect(int line);
        
}

//    getNumberLines - this method returns how many total lines the underlying model contains, allowing the caller to iterate over each line.
//    getLineRect: this method returns a rectangle defining the location in the view the parameter line is located. The caller (below) is only interested in the y and height fields of the Rectangle.
